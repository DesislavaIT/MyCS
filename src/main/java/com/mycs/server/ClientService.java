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

    public void save(Client client) { clientRepository.save(client); }

    public Boolean isValid(Client client) throws ClientValidationException {
        if(client.getAccountType() == null || client.getAccountType().isEmpty()) {
            throw new ClientValidationException("Account_Type must be VL or FL!");
        }
        if(client.getChequeCardFlag() == null || client.getChequeCardFlag().isEmpty()) {
            throw new ClientValidationException("Cheque_Card_Flag can not be empty!");
        }
        if(client.getInsuranceRequired() == null || client.getInsuranceRequired().isEmpty()) {
            throw new ClientValidationException("Insurance_Required can not be empty!");
        }
        if(client.getLoanPaymentMethod() == null || client.getLoanPaymentMethod().isEmpty()) {
            throw new ClientValidationException("Loan_Payment_Method can not be empty!");
        }
        if(client.getNumberOfPayments() == null || client.getNumberOfPayments() < 0) {
            throw new ClientValidationException("Number_of_Payments can not be empty or negative number!");
        }
        if(client.getResidentialStatus() == null || client.getResidentialStatus().isEmpty()) {
            throw new ClientValidationException("Residential_Status can not be empty!");
        }
        if(client.getTimeAtAddress() == null || client.getTimeAtAddress() < 0) {
            throw new ClientValidationException("Time_at_Address can not be empty or negative number!");
        }
        if(client.getTimeInEmployment() == null || client.getTimeInEmployment() < 0) {
            throw new ClientValidationException("Time_in_Employment can not be empty or negative number!");
        }
        if(client.getTimeWithBank() == null || client.getTimeWithBank() < 0) {
            throw new ClientValidationException("Time_with_Bank can not be empty or negative number!");
        }
        if(client.getBureauScore() == null || client.getBureauScore() < 0) {
            throw new ClientValidationException("Bureau_Score can not be empty or negative number!");
        }
        if(client.getSpNumberOfSearchesL6M() == null || client.getSpNumberOfSearchesL6M() < 0) {
            throw new ClientValidationException("SP_Number_Of_Searches_L6M can not be empty or negative number!");
        }
        if(client.getSpNumberOfCCJs() == null || client.getSpNumberOfCCJs() < 0) {
            throw new ClientValidationException("SP_Number_of_CCJs can not be empty or negative number!");
        }
        if(client.getLoanToIncome() == null || client.getLoanToIncome() < 0) {
            throw new ClientValidationException("loan_to_income can not be empty or negative number!");
        }

        return true;
    }

    public void cloneClient(Client oldClient, Client newClient) {
        oldClient.setAccountType(newClient.getAccountType());
        oldClient.setChequeCardFlag(newClient.getChequeCardFlag());
        oldClient.setExistingCustomerFlag(newClient.getExistingCustomerFlag());
        oldClient.setGrossAnnualIncome(newClient.getGrossAnnualIncome());
        oldClient.setHomeTelephoneNumber(newClient.getHomeTelephoneNumber());
        oldClient.setInsuranceRequired(newClient.getInsuranceRequired());
        oldClient.setLoanAmount(newClient.getLoanAmount());
        oldClient.setLoanPaymentFrequency(newClient.getLoanPaymentFrequency());
        oldClient.setLoanPaymentMethod(newClient.getLoanPaymentMethod());
        oldClient.setMaritalStatus(newClient.getMaritalStatus());
        oldClient.setNumberOfDependants(newClient.getNumberOfDependants());
        oldClient.setNumberOfPayments(newClient.getNumberOfPayments());
        oldClient.setOccupationCode(newClient.getOccupationCode());
        oldClient.setPromotionType(newClient.getPromotionType());
        oldClient.setResidentialStatus(newClient.getResidentialStatus());
        oldClient.setTimeAtAddress(newClient.getTimeAtAddress());
        oldClient.setTimeInEmployment(newClient.getTimeInEmployment());
        oldClient.setTimeWithBank(newClient.getTimeWithBank());
        oldClient.setAgeOfApplicant(newClient.getAgeOfApplicant());
        oldClient.setBureauScore(newClient.getBureauScore());
        oldClient.setSpErReference(newClient.getSpErReference());
        oldClient.setSpNumberOfSearchesL6M(newClient.getSpNumberOfSearchesL6M());
        oldClient.setSpNumberOfCCJs(newClient.getSpNumberOfCCJs());
        oldClient.setLoanToIncome(newClient.getLoanToIncome());
    }
}
