package com.mycs.calculations;

import com.mycs.entities.Client;

public class CreditScoreCalculator {
    public static void calculateCreditScore(Client client) {
        int intercept = 953;
        int accountTypeS = getAccountTypeS(client);
        int bureauScoreS = getBureauScoreS(client);
        int chequeCardFlagS = getChequeCardFlagS(client);
        int insuranceRequiredS = getInsuranceRequiredS(client);
        int loanPaymentMethodS = getLoanPaymentMethodS(client);
        int numberOfPaymentsS = getNumberOfPaymentsS(client);
        int residentialStatusS = getResidentialStatusS(client);
        int spNumberOfSearchesL6MS = getSpNumberOfSearchesL6MS(client);
        int spNumberOfCCJsS = getSpNumberOfCCJsS(client);
        int timeAtAddressS = getTimeAtAddressS(client);
        int timeInEmploymentS = getTimeInEmploymentS(client);
        int timeWithBankS = getTimeWithBankS(client);
        int loanToIncomeS = getLoanToIncomeS(client);

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

    private static int getAccountTypeS(Client client) {
        int accountTypeS;
        if (client.getAccountType().toString().equals("VL")) {
            accountTypeS = -57;
        }
        else {
            accountTypeS = 0;
        }
        return accountTypeS;
    }

    private static int getBureauScoreS(Client client) {
        int bureauScoreS;
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
        return bureauScoreS;
    }

    private static int getChequeCardFlagS(Client client) {
        int chequeCardFlagS;
        if (client.getChequeCardFlag().equals("N")) {
            chequeCardFlagS = -48;
        }
        else {
            chequeCardFlagS = 0;
        }
        return chequeCardFlagS;
    }

    private static int getInsuranceRequiredS(Client client) {
        int insuranceRequiredS;
        if (client.getInsuranceRequired().equals("Y")) {
            insuranceRequiredS = -24;
        }
        else {
            insuranceRequiredS = 0;
        }
        return insuranceRequiredS;
    }

    private static int getLoanPaymentMethodS(Client client) {
        int loanPaymentMethodS;
        if (client.getLoanPaymentMethod().equals("B")) {
            loanPaymentMethodS = 0;
        }
        else {
            loanPaymentMethodS = -36;
        }
        return loanPaymentMethodS;
    }

    private static int getNumberOfPaymentsS(Client client) {
        int numberOfPaymentsS;
        if (client.getNumberOfPayments() < 18) {
            numberOfPaymentsS = 102;
        }
        else {
            numberOfPaymentsS = 0;
        }
        return numberOfPaymentsS;
    }

    private static int getResidentialStatusS(Client client) {
        int residentialStatusS;
        if (client.getResidentialStatus().equals("H")) {
            residentialStatusS = 0;
        }
        else {
            residentialStatusS = -18;
        }
        return residentialStatusS;
    }

    private static int getSpNumberOfSearchesL6MS(Client client) {
        int spNumberOfSearchesL6MS;
        if (client.getSpNumberOfSearchesL6M() < 6) {
            spNumberOfSearchesL6MS = 0;
        }
        else {
            spNumberOfSearchesL6MS = -42;
        }
        return spNumberOfSearchesL6MS;
    }

    private static int getSpNumberOfCCJsS(Client client) {
        int spNumberOfCCJsS;
        if (client.getSpNumberOfCCJs() == 0) {
            spNumberOfCCJsS = 0;
        }
        else {
            spNumberOfCCJsS = -42;
        }
        return spNumberOfCCJsS;
    }

    private static int getTimeAtAddressS(Client client) {
        int timeAtAddressS;
        if (client.getTimeAtAddress() < 6) {
            timeAtAddressS = -21;
        }
        else if (client.getTimeAtAddress() < 2500) {
            timeAtAddressS = 0;
        }
        else {
            timeAtAddressS = 30;
        }
        return timeAtAddressS;
    }

    private static int getTimeInEmploymentS(Client client) {
        int timeInEmploymentS;
        if (client.getTimeInEmployment() < 100) {
            timeInEmploymentS = -24;
        }
        else if (client.getTimeInEmployment() < 1200) {
            timeInEmploymentS = 0;
        }
        else {
            timeInEmploymentS = 15;
        }
        return timeInEmploymentS;
    }

    private static int getTimeWithBankS(Client client) {
        int timeWithBankS;
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
        return timeWithBankS;
    }

    private static int getLoanToIncomeS(Client client) {
        int loanToIncomeS;
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
        return loanToIncomeS;
    }
}