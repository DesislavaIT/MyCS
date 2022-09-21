package com.mycs.controller;

import com.mycs.dto.ClientDTO;
import com.mycs.entities.Client;
import com.mycs.entities.DBLog;
import com.mycs.exception.ClientValidationException;
import com.mycs.server.ClientService;

import com.mycs.server.FileService;
import com.mycs.server.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;

import static com.mycs.calculations.CreditScoreCalculator.calculateCreditScore;

@RestController
@RequestMapping("/score")
public class MyCSController {
    @Autowired
    private Environment env;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LogService logService;
    @Autowired
    private FileService fileService;

    private final static Logger LOGGER = LoggerFactory.getLogger(MyCSController.class);

    @PostMapping("singleCheck")
    public ResponseEntity<String> singleCheck(@RequestBody ClientDTO clientDTO) {
        Client client = clientDTO.toClient();

        DBLog DBLog = new DBLog();
        DBLog.setEndPoint("score/singleCheck");
        //TODO: DBLog.setUserName();

        try {
            clientService.isValid(client);
        } catch (Exception e) {
            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage("Invalid data: " + e.getMessage());
            logService.save(DBLog);

            LOGGER.error("Wrong client data: {}", e.getMessage(), e);

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        calculateCreditScore(client);
        client.setLastTimeModified(LocalDateTime.now());
        clientService.save(client);

        DBLog.setTime(LocalDateTime.now());
        DBLog.setMessage(String.format("Client with account number %d saved.", client.getAccountNumber()));
        logService.save(DBLog);

        return new ResponseEntity("Client credit score: " + client.getScore(), HttpStatus.OK);
    }

    @PostMapping("batchProcessing")
    public ResponseEntity<String> batchProcessing(@RequestBody String fileName) {
        DBLog DBLog = new DBLog();
        DBLog.setEndPoint("score/batchProcessing");
        //TODO: DBLog.setUserName();

        if (!fileService.getFileExtension(fileName).equals("csv")) {
            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage(String.format("Wrong file format: File %s is not CSV file!.", fileName));
            logService.save(DBLog);

            return new ResponseEntity(String.format("Wrong file format: File %s should be in CSV format.", fileName), HttpStatus.BAD_REQUEST);
        }

        ResponseEntity responseEntityOutputFile = getResponseEntityOutputFile(fileName, DBLog);
        if (responseEntityOutputFile != null) return responseEntityOutputFile;

        String inputFilePath = env.getProperty("csv.input.path") + fileName;
        String outputFilePath = env.getProperty("csv.output.path") + fileName;
        int[] scores = new int[fileService.getLinesCountInFile(inputFilePath)];
        int counter = 0;
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath));
            reader.readLine();
            writer.write("Account_Type,Cheque_Card_Flag,Existing_Customer_Flag,Gross_Annual_Income,Home_Telephone_Number,Insurance_Required,Loan_Amount,Loan_Payment_Frequency,Loan_Payment_Method,Marital_Status,Number_of_Dependants,Number_of_Payments,Occupation_Code,Promotion_Type,Residential_Status,Time_at_Address,Time_in_Employment,Time_with_Bank,Age_of_Applicant,Bureau_Score,SP_ER_Reference,SP_Number_Of_Searches_L6M,SP_Number_of_CCJs,loan_to_income,Last_Time_Modified,Credit_Score,Error");

            while ((line = reader.readLine()) != null) {
                Client client = fileService.createClient(line);
                try {
                    counter = getCounter(scores, counter, writer, client);
                } catch (ClientValidationException e) {
                    LOGGER.error("Wrong client data: {}", e.getMessage(), e);

                    fileService.writeErrorToFile(writer, client, e.getMessage());
                }
            }

            writer.close();
        } catch (IOException e) {
            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage(String.format("File %s is wrongly formatted or can not be found.", fileName));
            logService.save(DBLog);

            LOGGER.error("Wrong formatted file or file not found: {}", e.getMessage(), e);

            return new ResponseEntity(String.format("File %s is wrongly formatted or can not be found.", fileName), HttpStatus.BAD_REQUEST);
        }

        DBLog.setTime(LocalDateTime.now());
        DBLog.setMessage(String.format("File %s has been processed successfully.", fileName));
        logService.save(DBLog);

        return new ResponseEntity(printSummaryInformation(scores, counter), HttpStatus.OK);
    }

    private int getCounter(int[] scores, int counter, PrintWriter writer, Client client) throws ClientValidationException {
        clientService.isValid(client);
        calculateCreditScore(client);
        client.setLastTimeModified(LocalDateTime.now());
        fileService.writeClientToFile(writer, client);
        clientService.save(client);
        scores[counter++] = client.getScore();
        return counter;
    }

    private ResponseEntity getResponseEntityOutputFile(String fileName, DBLog log) {
        if(outputFileExists(fileName)) {
            log.setTime(LocalDateTime.now());
            log.setMessage(String.format("File with the name %s has already been processed.", fileName));
            logService.save(log);

            return new ResponseEntity(String.format("File with the name %s has already been processed.", fileName), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private Boolean outputFileExists(String fileName)
    {
        String filePathString = env.getProperty("csv.output.path") + fileName;
        File file = new File(filePathString);
        return file.exists() && !file.isDirectory();
    }

    public String printSummaryInformation(int[] creditScores, int count) {
    int under820 = 0;
    int under945 = 0;
    int under1040 = 0;
    int above1040 = 0;

    for(int i = 0; i < count; i++) {
        if (creditScores[i] < 820) {
            under820++;
        }
        else if (creditScores[i] < 945) {
            under945++;
        }
        else if (creditScores[i] < 1040) {
            under1040++;
        }
        else {
            above1040++;
        }
    }

    return "(LOW:820) - " + under820 + '\n' +
            "[820:945) - " + under945 + '\n' +
            "[945:1040) - " + under1040 + '\n' +
            "[1040:HIGH) - " + above1040;
    }
}
