package com.mycs.server;

import com.mycs.entities.AccountType;
import com.mycs.entities.Client;

import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

@Service
public class FileService {
    public int getLinesCountInFile(String filePath) {
        int counter = 0;
        try {
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while ((line = reader.readLine()) != null) {
                counter++;
            }
        }
        catch (IOException e) {
            return counter;
        }

        return counter;
    }

    public Client createClient(String line) {
        String splitBy = ",";
        String[] lineParts = line.split(splitBy);
        Client client = new Client();
        setAccountType(lineParts, client);
        setChequeCardFlag(lineParts, client);
        setExistingCustomerFlag(lineParts, client);
        setGrossAnnualIncome(lineParts, client);
        setHomeTelephoneNumber(lineParts, client);
        setInsuranceRequired(lineParts, client);
        setLoanAmount(lineParts, client);
        setLoanPaymentFrequency(lineParts, client);
        setLoanPaymentMethod(lineParts, client);
        setMaritalStatus(lineParts, client);
        setNumberOfDependants(lineParts, client);
        setNumberOfPayments(lineParts, client);
        setOccupationCode(lineParts, client);
        setPromotionType(lineParts, client);
        setResidentialStatus(lineParts, client);
        setTimeAtAddress(lineParts, client);
        setTimeInEmployment(lineParts, client);
        setTimeWithBank(lineParts, client);
        setAgeOfApplicant(lineParts, client);
        setBureauScore(lineParts, client);
        setSP_ER_Reference(lineParts, client);
        setSpNumberOfSearchesL6M(lineParts, client);
        setSpNumberOfCCJs(lineParts, client);
        setLoanToIncome(lineParts, client);
        client.setLastTimeModified(LocalDateTime.now());

        return client;
    }

    private  void setLoanToIncome(String[] lineParts, Client client) {
        try {
            client.setLoanToIncome(Double.parseDouble(lineParts[23]));
        } catch (Exception e) {
            client.setLoanToIncome(null);
        }
    }

    private void setSpNumberOfCCJs(String[] lineParts, Client client) {
        try {
            client.setSpNumberOfCCJs(Integer.parseInt(lineParts[22]));
        } catch (Exception e) {
            client.setSpNumberOfCCJs(null);
        }
    }

    private void setSpNumberOfSearchesL6M(String[] lineParts, Client client) {
        try {
            client.setSpNumberOfSearchesL6M(Integer.parseInt(lineParts[21]));
        } catch (Exception e) {
            client.setSpNumberOfSearchesL6M(null);
        }
    }

    private void setSP_ER_Reference(String[] lineParts, Client client) {
        try {
            client.setSpErReference(Integer.parseInt(lineParts[20]));
        } catch (Exception e) {
            client.setSpErReference(null);
        }
    }

    private void setBureauScore(String[] lineParts, Client client) {
        try {
            client.setBureauScore(Integer.parseInt(lineParts[19]));
        } catch (Exception e) {
            client.setBureauScore(null);
        }
    }

    private void setAgeOfApplicant(String[] lineParts, Client client) {
        try {
            client.setAgeOfApplicant(Integer.parseInt(lineParts[18]));
        } catch (Exception e) {
            client.setAgeOfApplicant(null);
        }
    }

    private void setTimeWithBank(String[] lineParts, Client client) {
        try {
            client.setTimeWithBank(Integer.parseInt(lineParts[17]));
        } catch (Exception e) {
            client.setTimeWithBank(null);
        }
    }

    private void setTimeInEmployment(String[] lineParts, Client client) {
        try {
            client.setTimeInEmployment(Integer.parseInt(lineParts[16]));
        } catch (Exception e) {
            client.setTimeInEmployment(null);
        }
    }

    private void setTimeAtAddress(String[] lineParts, Client client) {
        try {
            client.setTimeAtAddress(Integer.parseInt(lineParts[15]));
        } catch (Exception e) {
            client.setTimeAtAddress(null);
        }
    }

    private void setResidentialStatus(String[] lineParts, Client client) {
        client.setResidentialStatus(lineParts[14]);
        if(client.getResidentialStatus().isEmpty()) {
            client.setResidentialStatus(null);
        }
    }

    private void setPromotionType(String[] lineParts, Client client) {
        client.setPromotionType(lineParts[13]);
        if(client.getPromotionType().isEmpty()) {
            client.setPromotionType(null);
        }
    }

    private void setOccupationCode(String[] lineParts, Client client) {
        client.setOccupationCode(lineParts[12]);
        if(client.getOccupationCode().isEmpty()) {
            client.setOccupationCode(null);
        }
    }

    private void setNumberOfPayments(String[] lineParts, Client client) {
        try {
            client.setNumberOfPayments(Integer.parseInt(lineParts[11]));
        } catch (Exception e) {
            client.setNumberOfPayments(null);
        }
    }

    private void setNumberOfDependants (String[] lineParts, Client client) {
        try {
            client.setNumberOfDependants(Integer.parseInt(lineParts[10]));
        } catch (Exception e) {
            client.setNumberOfDependants(null);
        }
    }

    private void setMaritalStatus(String[] lineParts, Client client) {
        client.setMaritalStatus(lineParts[9]);
        if(client.getMaritalStatus().isEmpty()) {
            client.setMaritalStatus(null);
        }
    }

    private void setLoanPaymentMethod(String[] lineParts, Client client) {
        client.setLoanPaymentMethod(lineParts[8]);
        if(client.getLoanPaymentMethod().isEmpty()) {
            client.setLoanPaymentMethod(null);
        }
    }

    private void setLoanPaymentFrequency(String[] lineParts, Client client) {
        client.setLoanPaymentFrequency(lineParts[7]);
        if(client.getLoanPaymentFrequency().isEmpty()) {
            client.setLoanPaymentFrequency(null);
        }
    }

    private void setLoanAmount(String[] lineParts, Client client) {
        try {
            client.setLoanAmount(Integer.parseInt(lineParts[6]));
        } catch (Exception e) {
            client.setLoanAmount(null);
        }
    }

    private void setInsuranceRequired(String[] lineParts, Client client) {
        client.setInsuranceRequired(lineParts[5]);
        if(client.getInsuranceRequired().isEmpty()) {
            client.setInsuranceRequired(null);
        }
    }

    private void setHomeTelephoneNumber(String[] lineParts, Client client) {
        client.setHomeTelephoneNumber(lineParts[4]);
        if(client.getHomeTelephoneNumber().isEmpty()) {
            client.setHomeTelephoneNumber(null);
        }
    }

    private void setGrossAnnualIncome(String[] lineParts, Client client) {
        try {
            client.setGrossAnnualIncome(Integer.parseInt(lineParts[3]));
        } catch (Exception e) {
            client.setGrossAnnualIncome(null);
        }
    }

    private void setExistingCustomerFlag(String[] lineParts, Client client) {
        client.setExistingCustomerFlag(lineParts[2]);
        if(client.getExistingCustomerFlag().isEmpty()) {
            client.setExistingCustomerFlag(null);
        }
    }

    private void setChequeCardFlag(String[] lineParts, Client client) {
        client.setChequeCardFlag(lineParts[1]);
        if(client.getChequeCardFlag().isEmpty()) {
            client.setChequeCardFlag(null);
        }
    }

    private void setAccountType(String[] lineParts, Client client) {
        if (lineParts[0].equals("VL")) {
            client.setAccountType(AccountType.VL);
        } else if (lineParts[0].equals("FL")) {
            client.setAccountType(AccountType.FL);
        } else {
            client.setAccountType(AccountType.Wrong);
        }
    }

    public void writeClientToFile(PrintWriter writer, Client client) {
        writer.append('\n');
        writer.append(client.printInfoToCSV());
    }

    public void writeErrorToFile(PrintWriter writer, Client client, String errorMessage) {
        writeClientToFile(writer, client);
        writer.append(",");
        writer.append(errorMessage);
    }

    public String getFileExtension(String fileName)
    {
        String fileExtension="";

        // If fileName do not contain "." or starts with "." then it is not a valid file
        if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
        {
            fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
        }

        return fileExtension;
    }
}