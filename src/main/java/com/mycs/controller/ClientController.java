package com.mycs.controller;

import com.mycs.entities.Client;
import com.mycs.entities.DBLog;

import com.mycs.exception.ClientNotFoundException;
import com.mycs.exception.ClientValidationException;

import com.mycs.server.ClientService;
import com.mycs.server.LogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Client getClientByAccountNumber(@PathVariable Long accountNumber) throws ClientNotFoundException {
        try {
            return clientService.getClientByAccountNumber(accountNumber);
        } catch (NoSuchElementException e){
            LOGGER.error("Wrong account number: {}", e.getMessage(), e);

            throw new ClientNotFoundException(String.format("Can not find client with account number: {%d}", accountNumber));
        }
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<String> updateClient(@RequestBody Client newClient, @PathVariable Long accountNumber) {
        DBLog DBLog = new DBLog();
        DBLog.setEndPoint("clients/{accountNumber}");
        //TODO: DBLog.setUserName();

        try {
            Client oldClient = getClientByAccountNumber(accountNumber);
            try {
                clientService.isValid(newClient);
            } catch (ClientValidationException e) {
                DBLog.setTime(LocalDateTime.now());
                DBLog.setMessage("Invalid data: " + e.getMessage());
                logService.save(DBLog);

                LOGGER.error("Wrong client data: {}", e.getMessage(), e);

                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            clientService.cloneClient(oldClient, newClient);
            calculateCreditScore(oldClient);
            oldClient.setLastTimeModified(LocalDateTime.now());

            DBLog.setTime(LocalDateTime.now());
            DBLog.setMessage(String.format("Client with account number %d was updated successfully.", oldClient.getAccountNumber()));
            logService.save(DBLog);

            clientService.save(oldClient);
            return new ResponseEntity(String.format("Client with account number %d was updated successfully.", oldClient.getAccountNumber()), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            LOGGER.error("Wrong account number: {}", e.getMessage(), e);

            return new ResponseEntity(String.format("Can not find client with account number: %d", accountNumber), HttpStatus.NOT_FOUND);
        }
    }
}
