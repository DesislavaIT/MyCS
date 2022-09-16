package com.mycs.controller;

import com.mycs.entities.Client;
import com.mycs.entities.DBLog;
import com.mycs.exception.ClientNotFoundException;
import com.mycs.exception.ClientValidationException;
import com.mycs.server.ClientService;
import com.mycs.server.FileService;
import com.mycs.server.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.mycs.calculations.CreditScoreCalculator.calculateCreditScore;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private Environment env;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LogService logService;
    @Autowired
    private FileService fileService;

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Client getClientByAccountNumber(@PathVariable Long accountNumber) throws ClientNotFoundException {
        try {
            return clientService.getClientByAccountNumber(accountNumber);
        } catch (NoSuchElementException e){
            throw new ClientNotFoundException(String.format("Can not find client with account number: {%d}", accountNumber));
        }
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<String> updateClient(@RequestBody Client newClient, @PathVariable Long accountNumber) {
        DBLog DBLog = new DBLog();
        DBLog.setEndPoint("clients/{accountNumber}");
        //TODO: DBLog.setUserName();

        try {
            clientService.isValid(newClient);
        } catch (ClientValidationException e) {
            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage("Invalid data: " + e.getMessage());
            logService.save(DBLog);

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            Client oldClient = getClientByAccountNumber(accountNumber);
            clientService.cloneClient(oldClient, newClient);
            calculateCreditScore(oldClient);
            oldClient.setLastTimeModified(LocalDateTime.now());

            try {
                String outputFilePath = env.getProperty("csv.output.single.check.path");
                PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true));
                fileService.writeClientToFile(writer, oldClient);
                writer.close();
            } catch (IOException e){
                return new ResponseEntity("File processing was unsuccessful!", HttpStatus.BAD_REQUEST);
            }

            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage(String.format("Client with account number %d was updated successfully.", oldClient.getAccountNumber()));
            logService.save(DBLog);

            clientService.save(oldClient);
            return new ResponseEntity(String.format("Client with account number %d was updated successfully.", oldClient.getAccountNumber()), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity(String.format("Can not find client with account number: %d", accountNumber), HttpStatus.NOT_FOUND);
        }
    }
}
