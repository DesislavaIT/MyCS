package com.mycs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String Account_Type;
    private String Cheque_Card_Flag;
    private String Existing_Customer_Flag;
    private int Gross_Annual_Income;
    private String Home_Telephone_Number;
    private String Insurance_Required;
    private int Loan_Amount;
    private String Loan_Payment_Frequency;
    private String Loan_Payment_Method;
    private String Marital_Status;
    private int Number_of_Dependants;
    private int Number_of_Payments;
    private String Occupation_Code;
    private String Promotion_Type;
    private String Residential_Status;
    private int Time_at_Address;
    private int Time_in_Employment;
    private int Time_with_Bank;
    private int Age_of_Applicant;
    private int Bureau_Score;
    private int SP_ER_Reference;
    private int SP_Number_Of_Searches_L6M;
    private int SP_Number_of_CCJs;
    private double loan_to_income;

//    public void PrintInfoToCSV()
//    {
//        System.out.println(Account_Type + "," + Cheque_Card_Flag + "," + Existing_Customer_Flag + ","
//                + Gross_Annual_Income + "," + Home_Telephone_Number + "," + Insurance_Required + ","
//                + Loan_Amount + "," + Loan_Payment_Frequency + "," + Loan_Payment_Method + ","
//                + Marital_Status + "," + Number_of_Dependants + "," + Number_of_Payments + ","
//                + Occupation_Code + "," + Promotion_Type + "," + Residential_Status + ","
//                + Time_at_Address + "," + Time_in_Employment + "," + Time_with_Bank + ","
//                + Age_of_Applicant + "," + Bureau_Score + "," + SP_ER_Reference + ","
//                + SP_Number_Of_Searches_L6M + "," + SP_Number_of_CCJs + "," + loan_to_income);
//    }
}
