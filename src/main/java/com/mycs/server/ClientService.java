package com.mycs.server;

import com.mycs.entities.Client;
import com.mycs.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client getClientByAccountNumber(Long accountNumber) {
        return clientRepository.findById(accountNumber).get();
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Boolean isValid(Client client){
        //TODO: Do the validations

        return true;
    }
}
