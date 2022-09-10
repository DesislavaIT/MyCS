package com.mycs.controller;

import com.mycs.entities.Client;
import com.mycs.exception.ClientValidationException;
import com.mycs.server.ClientService;
import com.mycs.exception.ClientNotFoundException;

import com.mycs.server.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.NoSuchElementException;

import static com.mycs.calculations.CreditScoreCalculator.calculateCreditScore;

@RestController
@RequestMapping("/score")
public class MyCSController {

    @Autowired
    private Environment env;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FileService fileService;

    @PostMapping("singleCheck")
    public ResponseEntity<String> singleCheck(@RequestBody Client client) throws IOException {
        try {
            clientService.isValid(client);
        } catch (ClientValidationException e) {
            String outputFilePath = env.getProperty("csv.output.single.check.path");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true));

            fileService.writeErrorToFile(writer, client.printInfoToCSV(), e.getMessage());
            writer.close();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        calculateCreditScore(client);
        try {
            String outputFilePath = env.getProperty("csv.output.single.check.path");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true));

            fileService.writeClientToFile(writer, client);
            writer.close();
        }
        catch (IOException e) {
            return new ResponseEntity("File processing was unsuccessful!", HttpStatus.BAD_REQUEST);
            //TODO: Change error message to be more informative
        }

        Client newClient = clientService.save(client);

        return new ResponseEntity("Client credit score: " + client.getScore(), HttpStatus.OK);
    }

    @PostMapping("batchProcessing")
    public ResponseEntity<String> batchProcessing(@RequestBody String fileName) {

        String inputFilePath = env.getProperty("csv.input.path") + fileName;
        String outputFilePath = env.getProperty("csv.output.path") + fileName;
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            int counter = -1;
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath));

            while ((line = reader.readLine()) != null) {
                if (counter == -1) {
                    writer.write("Account_Type,Cheque_Card_Flag,Existing_Customer_Flag,Gross_Annual_Income,Home_Telephone_Number,Insurance_Required,Loan_Amount,Loan_Payment_Frequency,Loan_Payment_Method,Marital_Status,Number_of_Dependants,Number_of_Payments,Occupation_Code,Promotion_Type,Residential_Status,Time_at_Address,Time_in_Employment,Time_with_Bank,Age_of_Applicant,Bureau_Score,SP_ER_Reference,SP_Number_Of_Searches_L6M,SP_Number_of_CCJs,loan_to_income, credit_score");

                    counter++;
                    continue;
                }

                Client client = fileService.createClient(line);
                try {
                    clientService.isValid(client);
                    fileService.writeClientToFile(writer, client);
                    Client newClient = clientService.save(client);
                } catch (ClientValidationException e) {
                    fileService.writeErrorToFile(writer, line, e.getMessage());
                }
            }

            writer.close();
        } catch (IOException e) {
            return new ResponseEntity("File processing was unsuccessful!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("File has been processed successfully!", HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Client getClientByAccountNumber(@PathVariable Long accountNumber) throws ClientNotFoundException {
        try {
            return clientService.getClientByAccountNumber(accountNumber);
        } catch (NoSuchElementException e){
            throw new ClientNotFoundException(String.format("Can not find client with account number: {%d}", accountNumber));
        }
    }
}
