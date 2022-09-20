package com.mycs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String accountType;
    private String chequeCardFlag;
    private String existingCustomerFlag;
    private int grossAnnualIncome;
    private String homeTelephoneNumber;
    private String insuranceRequired;
    private int loanAmount;
    private String loanPaymentFrequency;
    private String loanPaymentMethod;
    private String maritalStatus;
    private int numberOfDependants;
    private int numberOfPayments;
    private String occupationCode;
    private String promotionType;
    private String residentialStatus;
    private int timeAtAddress;
    private int timeInEmployment;
    private int timeWithBank;
    private int ageOfApplicant;
    private int bureauScore;
    private int SP_ER_Reference;
    private int spNumberOfSearchesL6M;
    private int spNumberOfCCJs;
    private double loanToIncome;
    private int score;

//    public void printInfoToCSV() {
//        System.out.println(accountType + "," + chequeCardFlag + "," + existingCustomerFlag + ","
//                + grossAnnualIncome + "," + homeTelephoneNumber + "," + insuranceRequired + ","
//                + loanAmount + "," + loanPaymentFrequency + "," + loanPaymentMethod + ","
//                + maritalStatus + "," + numberOfDependants + "," + numberOfPayments + ","
//                + occupationCode + "," + promotionType + "," + residentialStatus + ","
//                + timeAtAddress + "," + timeInEmployment + "," + timeWithBank + ","
//                + ageOfApplicant + "," + bureauScore + "," + SP_ER_Reference + ","
//                + spNumberOfSearchesL6M + "," + spNumberOfCCJs + "," + loanToIncome + "," + score);
//    }
}
