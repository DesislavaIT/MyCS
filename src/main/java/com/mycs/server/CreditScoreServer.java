package com.mycs.server;

import com.mycs.entities.Client;
import lombok.SneakyThrows;

import java.io.*;

import static com.mycs.calculations.CreditScoreCalculator.calculateCreditScore;

public class CreditScoreServer {

    //Helper method
    //TODO: Uncomment when needed
//    public static void PrintClients(Client[] clients) {
//        for(int i = 0; i < Arrays.stream(clients).count(); i++) {
//            clients[i].PrintInfo();
//        }
//    }

//    public static void PrintSummaryInformation(int[] creditScores) {
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

    //Helper method
//    public static void PrintCreditScores(int[] creditScores) {
//        for(int i = 0; i < Arrays.stream(creditScores).count(); i++) {
//            System.out.println(creditScores[i]);
//        }
//    }

//    getLinesCountInFile

    public static int getLinesCountInFile(String filePath) {
        int counter = 0;
        try {
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while ((line = reader.readLine()) != null) {
                counter++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return counter;
    }

    public static Client createClient(String line) {
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
        calculateCreditScore(client);

        return client;
    }

    @SneakyThrows
    public static void writeToFile(BufferedWriter writer, Client client) {
        writer.append('\n');
        writer.append(client.printInfoToCSV());
    }
}