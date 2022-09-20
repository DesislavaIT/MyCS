package com.mycs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @TableGenerator(name = "AccountNumber_Gen", table = "AN_GEN",
            pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL",
            pkColumnValue = "AN_Gen", initialValue = 100000, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountNumber_Gen")
    private Long accountNumber; //start from 100 000
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType; //mandatory
    @Column(nullable = false)
    private String chequeCardFlag; //mandatory
    private String existingCustomerFlag;
    private Integer grossAnnualIncome;
    private String homeTelephoneNumber;
    @Column(nullable = false)
    private String insuranceRequired; //mandatory
    private Integer loanAmount;
    private String loanPaymentFrequency;
    @Column(nullable = false)
    private String loanPaymentMethod; //mandatory
    private String maritalStatus;
    private Integer numberOfDependants;
    private Integer numberOfPayments; //mandatory
    private String occupationCode;
    private String promotionType;
    @Column(nullable = false)
    private String residentialStatus; //mandatory
    private Integer timeAtAddress; //mandatory
    private Integer timeInEmployment; //mandatory
    private Integer timeWithBank; //mandatory
    private Integer ageOfApplicant;
    private Integer bureauScore; //mandatory
    private Integer spErReference;
    private Integer spNumberOfSearchesL6M; //mandatory
    private Integer spNumberOfCCJs; //mandatory
    private Double loanToIncome; //mandatory
    private Integer score;
    private LocalDateTime lastTimeModified;

    public String printInfoToCSV() {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String lastTimeModifiedS = lastTimeModified.format(dateTimeFormatter);
        return ((accountType == null || accountType == AccountType.Wrong ? "" : accountType.toString()) + ","
                + (chequeCardFlag == null ? "" : chequeCardFlag) + ","
                + (existingCustomerFlag == null ? "" : existingCustomerFlag) + ","
                + (grossAnnualIncome == null ? "" : grossAnnualIncome) + ","
                + (homeTelephoneNumber == null ? "" : homeTelephoneNumber) + ","
                + (insuranceRequired == null ? "" : insuranceRequired) + ","
                + (loanAmount == null ? "" : loanAmount) + ","
                + (loanPaymentFrequency == null ? "" : loanPaymentFrequency) + ","
                + (loanPaymentMethod == null ? "" : loanPaymentMethod) + ","
                + (maritalStatus == null ? "" : maritalStatus) + ","
                + (numberOfDependants == null ? "" : numberOfDependants) + ","
                + (numberOfPayments == null ? "" : numberOfPayments) + ","
                + (occupationCode == null ? "" : occupationCode) + ","
                + (promotionType == null ? "" : promotionType) + ","
                + (residentialStatus == null ? "" : residentialStatus) + ","
                + (timeAtAddress == null ? "" : timeAtAddress) + ","
                + (timeInEmployment == null ? "" : timeInEmployment) + ","
                + (timeWithBank == null ? "" : timeWithBank) + ","
                + (ageOfApplicant == null ? "" : ageOfApplicant) + ","
                + (bureauScore == null ? "" : bureauScore) + ","
                + (spErReference == null ? "" : spErReference) + ","
                + (spNumberOfSearchesL6M == null ? "" : spNumberOfSearchesL6M) + ","
                + (spNumberOfCCJs == null ? "" : spNumberOfCCJs) + ","
                + (loanToIncome == null ? "" : loanToIncome) + ","
                + lastTimeModifiedS + ","
                + (score == null ? "" : score));
    }
}
