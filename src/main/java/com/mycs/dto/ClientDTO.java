package com.mycs.dto;

import com.mycs.entities.AccountType;
import com.mycs.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String accountType;
    private String chequeCardFlag;
    private String existingCustomerFlag;
    private Integer grossAnnualIncome;
    private String homeTelephoneNumber;
    private String insuranceRequired;
    private Integer loanAmount;
    private String loanPaymentFrequency;
    private String loanPaymentMethod;
    private String maritalStatus;
    private Integer numberOfDependants;
    private Integer numberOfPayments;
    private String occupationCode;
    private String promotionType;
    private String residentialStatus;
    private Integer timeAtAddress;
    private Integer timeInEmployment;
    private Integer timeWithBank;
    private Integer ageOfApplicant;
    private Integer bureauScore;
    private Integer spErReference;
    private Integer spNumberOfSearchesL6M;
    private Integer spNumberOfCCJs;
    private Double loanToIncome;

    public Client toClient() {
        Client client = new Client();
        if (this.getAccountType() == null) {
            client.setAccountType(AccountType.Wrong);
        } else if (this.getAccountType().equals("VL")) {
            client.setAccountType(AccountType.VL);
        } else if (this.getAccountType().equals("FL")) {
            client.setAccountType(AccountType.FL);
        } else {
            client.setAccountType(AccountType.Wrong);
        }
        client.setChequeCardFlag(this.getChequeCardFlag());
        client.setExistingCustomerFlag(this.getExistingCustomerFlag());
        client.setGrossAnnualIncome(this.getGrossAnnualIncome());
        client.setHomeTelephoneNumber(this.getHomeTelephoneNumber());
        client.setInsuranceRequired(this.getInsuranceRequired());
        client.setLoanAmount(this.getLoanAmount());
        client.setLoanPaymentFrequency(this.getLoanPaymentFrequency());
        client.setLoanPaymentMethod(this.getLoanPaymentMethod());
        client.setMaritalStatus(this.getMaritalStatus());
        client.setNumberOfDependants(this.getNumberOfDependants());
        client.setNumberOfPayments(this.getNumberOfPayments());
        client.setOccupationCode(this.getOccupationCode());
        client.setPromotionType(this.getPromotionType());
        client.setResidentialStatus(this.getResidentialStatus());
        client.setTimeAtAddress(this.getTimeAtAddress());
        client.setTimeInEmployment(this.getTimeInEmployment());
        client.setTimeWithBank(this.getTimeWithBank());
        client.setAgeOfApplicant(this.getAgeOfApplicant());
        client.setBureauScore(this.getBureauScore());
        client.setSpErReference(this.getSpErReference());
        client.setSpNumberOfSearchesL6M(this.getSpNumberOfSearchesL6M());
        client.setSpNumberOfCCJs(this.getSpNumberOfCCJs());
        client.setLoanToIncome(this.getLoanToIncome());

        return client;
    }
}
