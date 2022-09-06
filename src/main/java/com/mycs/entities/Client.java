package com.mycs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private String EGN;
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

    public String PrintInfoToCSV()
    {
        return (accountType + "," + chequeCardFlag + "," + existingCustomerFlag + ","
                + grossAnnualIncome + "," + homeTelephoneNumber + "," + insuranceRequired + ","
                + loanAmount + "," + loanPaymentFrequency + "," + loanPaymentMethod + ","
                + maritalStatus + "," + numberOfDependants + "," + numberOfPayments + ","
                + occupationCode + "," + promotionType + "," + residentialStatus + ","
                + timeAtAddress + "," + timeInEmployment + "," + timeWithBank + ","
                + ageOfApplicant + "," + bureauScore + "," + SP_ER_Reference + ","
                + spNumberOfSearchesL6M + "," + spNumberOfCCJs + "," + loanToIncome + "," + score);
    }
}
