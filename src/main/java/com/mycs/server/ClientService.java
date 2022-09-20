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

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Boolean isValid(Client client) throws ClientValidationException {
        Boolean isValid = true;
        StringBuilder exceptionMessage = new StringBuilder();

        isValid = getaBooleanAccountType(client, isValid, exceptionMessage);
        isValid = getBooleanChequeCardFlag(client, isValid, exceptionMessage);
        isValid = getaBooleanInsuranceRequired(client, isValid, exceptionMessage);
        isValid = getaBooleanLoanPaymentMethod(client, isValid, exceptionMessage);
        isValid = getaBooleanNumberOfPayments(client, isValid, exceptionMessage);
        isValid = getaBooleanResidentialStatus(client, isValid, exceptionMessage);
        isValid = getaBooleanTimeAtAddress(client, isValid, exceptionMessage);
        isValid = getaBooleanTimeInEmployment(client, isValid, exceptionMessage);
        isValid = getaBooleanTimeWithBank(client, isValid, exceptionMessage);
        isValid = getaBooleanBureauScore(client, isValid, exceptionMessage);
        isValid = getaBooleanSpNumberOfSearchesL6M(client, isValid, exceptionMessage);
        isValid = getaBooleanSpNumberOfCCJs(client, isValid, exceptionMessage);
        isValid = getaBooleanLoanToIncome(client, isValid, exceptionMessage);

        if (!isValid) {
            throw new ClientValidationException(exceptionMessage.toString());
        }

        return true;
    }

    private Boolean getaBooleanLoanToIncome(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getLoanToIncome() == null || client.getLoanToIncome() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("loan_to_income can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanSpNumberOfCCJs(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getSpNumberOfCCJs() == null || client.getSpNumberOfCCJs() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("SP_Number_of_CCJs can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanSpNumberOfSearchesL6M(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getSpNumberOfSearchesL6M() == null || client.getSpNumberOfSearchesL6M() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("SP_Number_Of_Searches_L6M can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanBureauScore(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getBureauScore() == null || client.getBureauScore() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Bureau_Score can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanTimeWithBank(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getTimeWithBank() == null || client.getTimeWithBank() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Time_with_Bank can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanTimeInEmployment(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getTimeInEmployment() == null || client.getTimeInEmployment() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Time_in_Employment can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanTimeAtAddress(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getTimeAtAddress() == null || client.getTimeAtAddress() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Time_at_Address can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanResidentialStatus(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getResidentialStatus() == null || client.getResidentialStatus().isEmpty()) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Residential_Status can not be empty!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanNumberOfPayments(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getNumberOfPayments() == null || client.getNumberOfPayments() < 0) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Number_of_Payments can not be empty or negative number!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanLoanPaymentMethod(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getLoanPaymentMethod() == null || client.getLoanPaymentMethod().isEmpty()) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Loan_Payment_Method can not be empty!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanInsuranceRequired(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getInsuranceRequired() == null || client.getInsuranceRequired().isEmpty()) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Insurance_Required can not be empty!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getBooleanChequeCardFlag(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getChequeCardFlag() == null || client.getChequeCardFlag().isEmpty()) {
            if (!isValid) {
                exceptionMessage.append(System.getProperty("line.separator"));
            }
            exceptionMessage.append("Cheque_Card_Flag can not be empty!");
            isValid = false;
        }
        return isValid;
    }

    private Boolean getaBooleanAccountType(Client client, Boolean isValid, StringBuilder exceptionMessage) {
        if (client.getAccountType() == null || (client.getAccountType().toString().equals("Wrong"))) {
            exceptionMessage.append("Account_Type must be VL or FL!");
            isValid = false;
        }
        return isValid;
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
