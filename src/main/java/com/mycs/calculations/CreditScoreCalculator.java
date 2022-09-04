package com.mycs.calculations;

import com.mycs.entities.Client;

public class CreditScoreCalculator {

    public static int CalculateCreditScore(Client client)
    {
        int intercept = 953;
        int Account_Type_s;
        int Bureau_Score_s;
        int Cheque_Card_Flag_s;
        int Insurance_Required_s;
        int Loan_Payment_Method_s;
        int Number_of_Payments_s;
        int Residential_Status_s;
        int SP_Number_Of_Searches_L6M_s;
        int SP_Number_of_CCJs_s;
        int Time_at_Address_s;
        int Time_in_Employment_s;
        int Time_with_Bank_s;
        int loan_to_income_s;

        if (client.getAccount_Type().equals("VL"))
        {
            Account_Type_s = -57;
        }
        else
        {
            Account_Type_s = 0;
        }

        if (client.getBureau_Score() < 850)
        {
            Bureau_Score_s = -48;
        }
        else if (client.getBureau_Score() < 925)
        {
            Bureau_Score_s = 0;
        }
        else if (client.getBureau_Score() < 1000)
        {
            Bureau_Score_s = 90;
        }
        else
        {
            Bureau_Score_s = 109;
        }

        if (client.getCheque_Card_Flag().equals("N"))
        {
            Cheque_Card_Flag_s = -48;
        }
        else
        {
            Cheque_Card_Flag_s = 0;
        }

        if (client.getInsurance_Required().equals("Y"))
        {
            Insurance_Required_s = -24;
        }
        else
        {
            Insurance_Required_s = 0;
        }

        if (client.getLoan_Payment_Method().equals("B"))
        {
            Loan_Payment_Method_s = 0;
        }
        else
        {
            Loan_Payment_Method_s = -36;
        }

        if (client.getNumber_of_Payments() < 18)
        {
            Number_of_Payments_s = 102;
        }
        else
        {
            Number_of_Payments_s = 0;
        }

        if (client.getResidential_Status().equals("H"))
        {
            Residential_Status_s = 0;
        }
        else
        {
            Residential_Status_s = -18;
        }

        if (client.getSP_Number_Of_Searches_L6M() < 6)
        {
            SP_Number_Of_Searches_L6M_s = 0;
        }
        else
        {
            SP_Number_Of_Searches_L6M_s = -42;
        }

        if (client.getSP_Number_of_CCJs() == 0)
        {
            SP_Number_of_CCJs_s = 0;
        }
        else
        {
            SP_Number_of_CCJs_s = -42;
        }

        if (client.getTime_at_Address() < 6)
        {
            Time_at_Address_s = -21;
        }
        else if (client.getTime_at_Address() < 2500)
        {
            Time_at_Address_s = 0;
        }
        else
        {
            Time_at_Address_s = 30;
        }

        if (client.getTime_in_Employment() < 100)
        {
            Time_in_Employment_s = -24;
        }
        else if (client.getTime_in_Employment() < 1200)
        {
            Time_in_Employment_s = 0;
        }
        else
        {
            Time_in_Employment_s = 15;
        }

        if (client.getTime_with_Bank() < 6)
        {
            Time_with_Bank_s = -33;
        }
        else if (client.getTime_with_Bank() < 200)
        {
            Time_with_Bank_s = -12;
        }
        else if (client.getTime_with_Bank() < 1000)
        {
            Time_with_Bank_s = 0;
        }
        else
        {
            Time_with_Bank_s = 12;
        }

        if (client.getLoan_to_income() == -9999998 || client.getLoan_to_income() == -9999997)
        {
            loan_to_income_s = 0;
        }
        else if (client.getLoan_to_income() < 5)
        {
            loan_to_income_s = 62;
        }
        else if (client.getLoan_to_income() < 10)
        {
            loan_to_income_s = 21;
        }
        else if (client.getLoan_to_income() < 30)
        {
            loan_to_income_s = 0;
        }
        else if (client.getLoan_to_income() < 60)
        {
            loan_to_income_s = -15;
        }
        else if (client.getLoan_to_income() < 100)
        {
            loan_to_income_s = -24;
        }
        else
        {
            loan_to_income_s = -36;
        }

        int score = intercept +
                Account_Type_s +
                Bureau_Score_s +
                Cheque_Card_Flag_s +
                Insurance_Required_s +
                Loan_Payment_Method_s +
                Number_of_Payments_s +
                Residential_Status_s +
                SP_Number_Of_Searches_L6M_s +
                SP_Number_of_CCJs_s +
                Time_at_Address_s +
                Time_in_Employment_s +
                Time_with_Bank_s +
                loan_to_income_s;

        return score;
    }
}