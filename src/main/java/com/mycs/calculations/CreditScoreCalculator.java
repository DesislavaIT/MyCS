package com.mycs.calculations;

import com.mycs.entities.Client;

public class CreditScoreCalculator {

    public static void CalculateCreditScore(Client client) {
        int intercept = 953;
        int accountTypeS;
        int bureauScoreS;
        int chequeCardFlagS;
        int insuranceRequiredS;
        int loanPaymentMethodS;
        int numberOfPaymentsS;
        int residentialStatusS;
        int spNumberOfSearchesL6MS;
        int spNumberOfCCJsS;
        int timeAtAddressS;
        int timeInEmploymentS;
        int timeWithBankS;
        int loanToIncomeS;

        if (client.getAccountType().equals("VL")) {
            accountTypeS = -57;
        }
        else {
            accountTypeS = 0;
        }

        if (client.getBureauScore() < 850) {
            bureauScoreS = -48;
        }
        else if (client.getBureauScore() < 925) {
            bureauScoreS = 0;
        }
        else if (client.getBureauScore() < 1000) {
            bureauScoreS = 90;
        }
        else {
            bureauScoreS = 109;
        }

        if (client.getChequeCardFlag().equals("N")) {
            chequeCardFlagS = -48;
        }
        else {
            chequeCardFlagS = 0;
        }

        if (client.getInsuranceRequired().equals("Y")) {
            insuranceRequiredS = -24;
        }
        else {
            insuranceRequiredS = 0;
        }

        if (client.getLoanPaymentMethod().equals("B")) {
            loanPaymentMethodS = 0;
        }
        else {
            loanPaymentMethodS = -36;
        }

        if (client.getNumberOfPayments() < 18) {
            numberOfPaymentsS = 102;
        }
        else {
            numberOfPaymentsS = 0;
        }

        if (client.getResidentialStatus().equals("H")) {
            residentialStatusS = 0;
        }
        else {
            residentialStatusS = -18;
        }

        if (client.getSpNumberOfSearchesL6M() < 6) {
            spNumberOfSearchesL6MS = 0;
        }
        else {
            spNumberOfSearchesL6MS = -42;
        }

        if (client.getSpNumberOfCCJs() == 0) {
            spNumberOfCCJsS = 0;
        }
        else {
            spNumberOfCCJsS = -42;
        }

        if (client.getTimeAtAddress() < 6) {
            timeAtAddressS = -21;
        }
        else if (client.getTimeAtAddress() < 2500) {
            timeAtAddressS = 0;
        }
        else {
            timeAtAddressS = 30;
        }

        if (client.getTimeInEmployment() < 100) {
            timeInEmploymentS = -24;
        }
        else if (client.getTimeInEmployment() < 1200) {
            timeInEmploymentS = 0;
        }
        else {
            timeInEmploymentS = 15;
        }

        if (client.getTimeWithBank() < 6) {
            timeWithBankS = -33;
        }
        else if (client.getTimeWithBank() < 200) {
            timeWithBankS = -12;
        }
        else if (client.getTimeWithBank() < 1000) {
            timeWithBankS = 0;
        }
        else {
            timeWithBankS = 12;
        }

        if (client.getLoanToIncome() == -9999998 || client.getLoanToIncome() == -9999997) {
            loanToIncomeS = 0;
        }
        else if (client.getLoanToIncome() < 5) {
            loanToIncomeS = 62;
        }
        else if (client.getLoanToIncome() < 10) {
            loanToIncomeS = 21;
        }
        else if (client.getLoanToIncome() < 30) {
            loanToIncomeS = 0;
        }
        else if (client.getLoanToIncome() < 60) {
            loanToIncomeS = -15;
        }
        else if (client.getLoanToIncome() < 100) {
            loanToIncomeS = -24;
        }
        else {
            loanToIncomeS = -36;
        }

        int score = intercept +
                accountTypeS +
                bureauScoreS +
                chequeCardFlagS +
                insuranceRequiredS +
                loanPaymentMethodS +
                numberOfPaymentsS +
                residentialStatusS +
                spNumberOfSearchesL6MS +
                spNumberOfCCJsS +
                timeAtAddressS +
                timeInEmploymentS +
                timeWithBankS +
                loanToIncomeS;

        client.setScore(score);
    }
}