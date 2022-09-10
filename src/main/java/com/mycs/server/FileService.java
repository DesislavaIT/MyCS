package com.mycs.server;

import com.mycs.entities.Client;
import com.mycs.exception.ClientValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.mycs.calculations.CreditScoreCalculator.calculateCreditScore;

@Service
public class FileService {
    @Autowired
    private ClientService clientService;
    //TODO: Uncomment when needed
//    public static void printClients(Client[] clients) {
//        for(int i = 0; i < Arrays.stream(clients).count(); i++) {
//            clients[i].printInfoToCSV();
//        }
//    }

//    public static void printSummaryInformation(int[] creditScores) {
//        int under820 = 0;
//        int under945 = 0;
//        int under1040 = 0;
//        int above1040 = 0;
//
//        for(int i = 0; i < Arrays.stream(creditScores).count(); i++) {
//            if (creditScores[i] < 820) {
//                under820++;
//            }
//            else if (creditScores[i] < 945) {
//                under945++;
//            }
//            else if (creditScores[i] < 1040) {
//                under1040++;
//            }
//            else {
//                above1040++;
//            }
//        }
//
//        System.out.println("(LOW:820) - " + under820);
//        System.out.println("[820:945) - " + under945);
//        System.out.println("[945:1040) - " + under1040);
//        System.out.println("[1040:HIGH) - " + above1040);
//    }

//    public static void printCreditScores(int[] creditScores) {
//        for(int i = 0; i < Arrays.stream(creditScores).count(); i++) {
//            System.out.println(creditScores[i]);
//        }
//    }

//    public static int getLinesCountInFile(String filePath) {
//        int counter = 0;
//        try {
//            String line = "";
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//
//            while ((line = reader.readLine()) != null) {
//                counter++;
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return counter;
//    }

    public Client createClient(String line) {
        String splitBy = ",";
        String[] lineParts = line.split(splitBy);
        Client client = new Client();
        client.setAccountType(lineParts[0]);
        client.setChequeCardFlag(lineParts[1]);
        client.setExistingCustomerFlag(lineParts[2]);
        client.setGrossAnnualIncome(Integer.parseInt(lineParts[3]));
        client.setHomeTelephoneNumber(lineParts[4]);
        client.setInsuranceRequired(lineParts[5]);
        client.setLoanAmount(Integer.parseInt(lineParts[6]));
        client.setLoanPaymentFrequency(lineParts[7]);
        client.setLoanPaymentMethod(lineParts[8]);
        client.setMaritalStatus(lineParts[9]);
        client.setNumberOfDependants(Integer.parseInt(lineParts[10]));
        client.setNumberOfPayments(Integer.parseInt(lineParts[11]));
        client.setOccupationCode(lineParts[12]);
        client.setPromotionType(lineParts[13]);
        client.setResidentialStatus(lineParts[14]);
        client.setTimeAtAddress(Integer.parseInt(lineParts[15]));
        client.setTimeInEmployment(Integer.parseInt(lineParts[16]));
        client.setTimeWithBank(Integer.parseInt(lineParts[17]));
        client.setAgeOfApplicant(Integer.parseInt(lineParts[18]));
        client.setBureauScore(Integer.parseInt(lineParts[19]));
        client.setSP_ER_Reference(Integer.parseInt(lineParts[20]));
        client.setSpNumberOfSearchesL6M(Integer.parseInt(lineParts[21]));
        client.setSpNumberOfCCJs(Integer.parseInt(lineParts[22]));
        client.setLoanToIncome(Double.parseDouble(lineParts[23]));

//        clientService.isValid(client);

        calculateCreditScore(client);

        return client;
    }

    public void writeClientToFile(PrintWriter writer, Client client) {
        writer.append('\n');
        writer.append(client.printInfoToCSV());
    }

    public void writeErrorToFile(PrintWriter writer, String line, String errorMessage) {
        writer.append('\n');
        writer.append(line);
        writer.append(", Error:");
        writer.append(errorMessage);
    }
}