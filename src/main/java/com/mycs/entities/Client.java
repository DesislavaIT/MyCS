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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "accountNumber_gen", sequenceName = "accountNumber_gen",  initialValue = 100000, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "accountNumber_gen")
    private Long accountNumber; //start from 100 000
    @Column(nullable = false)
    private String accountType; //mandatory
    @Column(nullable = false)
    private String chequeCardFlag; //mandatory
    private String existingCustomerFlag;
    @Column(nullable = true)
    private int grossAnnualIncome;
    private String homeTelephoneNumber;
    @Column(nullable = false)
    private String insuranceRequired; //mandatory
    @Column(nullable = true)
    private int loanAmount;
    private String loanPaymentFrequency;
    @Column(nullable = false)
    private String loanPaymentMethod; //mandatory
    private String maritalStatus;
    @Column(nullable = true)
    private int numberOfDependants;
    private int numberOfPayments; //mandatory
    private String occupationCode;
    private String promotionType;
    @Column(nullable = false)
    private String residentialStatus; //mandatory
    private int timeAtAddress; //mandatory
    private int timeInEmployment; //mandatory
    private int timeWithBank; //mandatory
    @Column(nullable = true)
    private int ageOfApplicant;
    private int bureauScore; //mandatory
    @Column(nullable = true)
    private int SP_ER_Reference;
    private int spNumberOfSearchesL6M; //mandatory
    private int spNumberOfCCJs; //mandatory
    private double loanToIncome; //mandatory
    @Column(nullable = true)
    private int score;

    //private DateTime data;

    public String printInfoToCSV() {
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
