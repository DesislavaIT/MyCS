package com.mycs.server;

import com.mycs.entities.Client;
import com.mycs.exception.ClientValidationException;
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

    public Boolean isValid(Client client) throws ClientValidationException {
        if(client.getAccountType() == null) {
            throw new ClientValidationException("Account_Type can not be empty!");
        }
        if(client.getChequeCardFlag() == null) {
            throw new ClientValidationException("Cheque_Card_Flag can not be empty!");
        }
        if(client.getInsuranceRequired() == null) {
            throw new ClientValidationException("Insurance_Required can not be empty!");
        }
        if(client.getLoanPaymentMethod() == null) {
            throw new ClientValidationException("Loan_Payment_Method can not be empty!");
        }
        if(client.getNumberOfPayments() == null) {
            throw new ClientValidationException("Number_of_Payments can not be empty!");
        }
        if(client.getResidentialStatus() == null) {
            throw new ClientValidationException("Residential_Status can not be empty!");
        }
        if(client.getTimeAtAddress() == null) {
            throw new ClientValidationException("Time_at_Address can not be empty!");
        }
        if(client.getTimeInEmployment() == null) {
            throw new ClientValidationException("Time_in_Employment can not be empty!");
        }
        if(client.getTimeWithBank() == null) {
            throw new ClientValidationException("Time_with_Bank can not be empty!");
        }
        if(client.getBureauScore() == null) {
            throw new ClientValidationException("Bureau_Score can not be empty!");
        }
        if(client.getSpNumberOfSearchesL6M() == null) {
            throw new ClientValidationException("SP_Number_Of_Searches_L6M can not be empty!");
        }
        if(client.getSpNumberOfCCJs() == null) {
            throw new ClientValidationException("SP_Number_of_CCJs can not be empty!");
        }
        if(client.getLoanToIncome() == null) {
            throw new ClientValidationException("loan_to_income can not be null!");
        }

        return true;
    }
}
