package com.mycs.controller;

import com.mycs.entities.Client;
import com.mycs.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import static com.mycs.calculations.CreditScoreCalculator.CalculateCreditScore;
import static com.mycs.server.CreditScoreServer.*;

@RestController
@RequestMapping("/score")
public class MyCSController {

    @GetMapping("getHello")
    public ResponseEntity<String> getHelloEndpoint() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("postSomething")
    public ResponseEntity<String> postStringEndpoint() {
        return new ResponseEntity("Something...........", HttpStatus.CREATED);
    }

    @PostMapping("singleCheck")
    public String SingleCheck(@RequestBody Client client) {
        CalculateCreditScore(client);
        return "Client credit score: " + client.getScore();
    }

    @PostMapping("batchProcessing")
    public String BatchProcessing(@RequestBody String fileName) {
        String inputFilePath = "src/main/Input files/" + fileName;
        String outputFilePath = "src/main/Output files/" + fileName;
        int clientsCount = GetLinesCountInFile(inputFilePath) - 1;
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            int counter = -1;
            Client[] clients = new Client[clientsCount];
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            while ((line = reader.readLine()) != null) {
                if (counter == -1) {
                    writer.write("Account_Type,Cheque_Card_Flag,Existing_Customer_Flag,Gross_Annual_Income,Home_Telephone_Number,Insurance_Required,Loan_Amount,Loan_Payment_Frequency,Loan_Payment_Method,Marital_Status,Number_of_Dependants,Number_of_Payments,Occupation_Code,Promotion_Type,Residential_Status,Time_at_Address,Time_in_Employment,Time_with_Bank,Age_of_Applicant,Bureau_Score,SP_ER_Reference,SP_Number_Of_Searches_L6M,SP_Number_of_CCJs,loan_to_income, credit_score");

                    counter++;
                    continue;
                }

                Client client = CreateClient(line);
                WriteToFile(writer,
                        client);
            }

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "File has been processed!";
    }
}
