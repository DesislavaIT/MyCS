package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CreditScoreServer {

    //TODO: Uncomment when Client entity is created
//   public static int CalculateCreditScore(Client client)
//    {
//        int intercept = 953;
//        int Account_Type_s;
//        int Bureau_Score_s;
//        int Cheque_Card_Flag_s;
//        int Insurance_Required_s;
//        int Loan_Payment_Method_s;
//        int Number_of_Payments_s;
//        int Residential_Status_s;
//        int SP_Number_Of_Searches_L6M_s;
//        int SP_Number_of_CCJs_s;
//        int Time_at_Address_s;
//        int Time_in_Employment_s;
//        int Time_with_Bank_s;
//        int loan_to_income_s;
//
//        if (client.getAccount_Type().equals("VL"))
//        {
//            Account_Type_s = -57;
//        }
//        else
//        {
//            Account_Type_s = 0;
//        }
//
//        if (client.getBureau_Score() < 850)
//        {
//            Bureau_Score_s = -48;
//        }
//        else if (client.getBureau_Score() < 925)
//        {
//            Bureau_Score_s = 0;
//        }
//        else if (client.getBureau_Score() < 1000)
//        {
//            Bureau_Score_s = 90;
//        }
//        else
//        {
//            Bureau_Score_s = 109;
//        }
//
//        if (client.getCheque_Card_Flag().equals("N"))
//        {
//            Cheque_Card_Flag_s = -48;
//        }
//        else
//        {
//            Cheque_Card_Flag_s = 0;
//        }
//
//        if (client.getInsurance_Required().equals("Y"))
//        {
//            Insurance_Required_s = -24;
//        }
//        else
//        {
//            Insurance_Required_s = 0;
//        }
//
//        if (client.getLoan_Payment_Method().equals("B"))
//        {
//            Loan_Payment_Method_s = 0;
//        }
//        else
//        {
//            Loan_Payment_Method_s = -36;
//        }
//
//        if (client.getNumber_of_Payments() < 18)
//        {
//            Number_of_Payments_s = 102;
//        }
//        else
//        {
//            Number_of_Payments_s = 0;
//        }
//
//        if (client.getResidential_Status().equals("H"))
//        {
//            Residential_Status_s = 0;
//        }
//        else
//        {
//            Residential_Status_s = -18;
//        }
//
//        if (client.getSP_Number_Of_Searches_L6M() < 6)
//        {
//            SP_Number_Of_Searches_L6M_s = 0;
//        }
//        else
//        {
//            SP_Number_Of_Searches_L6M_s = -42;
//        }
//
//        if (client.getSP_Number_of_CCJs() == 0)
//        {
//            SP_Number_of_CCJs_s = 0;
//        }
//        else
//        {
//            SP_Number_of_CCJs_s = -42;
//        }
//
//        if (client.getTime_at_Address() < 6)
//        {
//            Time_at_Address_s = -21;
//        }
//        else if (client.getTime_at_Address() < 2500)
//        {
//            Time_at_Address_s = 0;
//        }
//        else
//        {
//            Time_at_Address_s = 30;
//        }
//
//        if (client.getTime_in_Employment() < 100)
//        {
//            Time_in_Employment_s = -24;
//        }
//        else if (client.getTime_in_Employment() < 1200)
//        {
//            Time_in_Employment_s = 0;
//        }
//        else
//        {
//            Time_in_Employment_s = 15;
//        }
//
//        if (client.getTime_with_Bank() < 6)
//        {
//            Time_with_Bank_s = -33;
//        }
//        else if (client.getTime_with_Bank() < 200)
//        {
//            Time_with_Bank_s = -12;
//        }
//        else if (client.getTime_with_Bank() < 1000)
//        {
//            Time_with_Bank_s = 0;
//        }
//        else
//        {
//            Time_with_Bank_s = 12;
//        }
//
//        if (client.getLoan_to_income() == -9999998 || client.getLoan_to_income() == -9999997)
//        {
//            loan_to_income_s = 0;
//        }
//        else if (client.getLoan_to_income() < 5)
//        {
//            loan_to_income_s = 62;
//        }
//        else if (client.getLoan_to_income() < 10)
//        {
//            loan_to_income_s = 21;
//        }
//        else if (client.getLoan_to_income() < 30)
//        {
//            loan_to_income_s = 0;
//        }
//        else if (client.getLoan_to_income() < 60)
//        {
//            loan_to_income_s = -15;
//        }
//        else if (client.getLoan_to_income() < 100)
//        {
//            loan_to_income_s = -24;
//        }
//        else
//        {
//            loan_to_income_s = -36;
//        }
//
//        int score = intercept +
//                Account_Type_s +
//                Bureau_Score_s +
//                Cheque_Card_Flag_s +
//                Insurance_Required_s +
//                Loan_Payment_Method_s +
//                Number_of_Payments_s +
//                Residential_Status_s +
//                SP_Number_Of_Searches_L6M_s +
//                SP_Number_of_CCJs_s +
//                Time_at_Address_s +
//                Time_in_Employment_s +
//                Time_with_Bank_s +
//                loan_to_income_s;
//
//        return score;
//    }

    //Helper method
    //TODO: Uncomment when Client entity is created
//    public static void PrintClients(Client[] clients)
//    {
//        for(int i = 0; i < Arrays.stream(clients).count(); i++)
//        {
//            clients[i].PrintInfo();
//        }
//    }

    //TODO: Uncomment when Client entity is created
//    public static void ReadCSV(String filePath)
//    {
//        String line = "";
//        String splitBy = ",";
//        try
//        {
//            BufferedReader br = new BufferedReader(new FileReader(filePath));
//            int counter = -1;
//            int[] creditScores = new int[100];
//            Client[] clients = new Client[100];
//
//            while ((line = br.readLine()) != null)
//            {
//                if (counter == -1)
//                {
//                    counter++;
//                    continue;
//                }
//
//                String[] lineParts = line.split(splitBy);
//                Client client = new Client();
//                client.Account_Type = lineParts[0];
//                client.Cheque_Card_Flag = lineParts[1];
//                client.Existing_Customer_Flag = lineParts[2];
//                client.Gross_Annual_Income = Integer.parseInt(lineParts[3]);
//                client.Home_Telephone_Number = lineParts[4];
//                client.Insurance_Required = lineParts[5];
//                client.Loan_Amount = Integer.parseInt(lineParts[6]);
//                client.Loan_Payment_Frequency = lineParts[7];
//                client.Loan_Payment_Method = lineParts[8];
//                client.Marital_Status = lineParts[9];
//                client.Number_of_Dependants = Integer.parseInt(lineParts[10]);
//                client.Number_of_Payments = Integer.parseInt(lineParts[11]);
//                client.Occupation_Code = lineParts[12];
//                client.Promotion_Type = lineParts[13];
//                client.Residential_Status = lineParts[14];
//                client.Time_at_Address = Integer.parseInt(lineParts[15]);
//                client.Time_in_Employment = Integer.parseInt(lineParts[16]);
//                client.Time_with_Bank = Integer.parseInt(lineParts[17]);
//                client.Age_of_Applicant = Integer.parseInt(lineParts[18]);
//                client.Bureau_Score = Integer.parseInt(lineParts[19]);
//                client.SP_ER_Reference = Integer.parseInt(lineParts[20]);
//                client.SP_Number_Of_Searches_L6M = Integer.parseInt(lineParts[21]);
//                client.SP_Number_of_CCJs = Integer.parseInt(lineParts[22]);
//                client.loan_to_income = Double.parseDouble(lineParts[23]);
//
//                creditScores[counter] = GetCreditScore(client);
//                clients[counter++] = client;
//            }
//
//            //TODO: Add clients with credit scores to DB
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public static void PrintSummaryInformation(int[] creditScores)
    {
        int under820 = 0;
        int under945 = 0;
        int under1040 = 0;
        int above1040 = 0;

        for(int i = 0; i < Arrays.stream(creditScores).count(); i++)
        {
            if (creditScores[i] < 820)
            {
                under820++;
            }
            else if (creditScores[i] < 945)
            {
                under945++;
            }
            else if (creditScores[i] < 1040)
            {
                under1040++;
            }
            else
            {
                above1040++;
            }
        }

        System.out.println("(LOW:820) - " + under820);
        System.out.println("[820:945) - " + under945);
        System.out.println("[945:1040) - " + under1040);
        System.out.println("[1040:HIGH) - " + above1040);
    }

    //Helper method
    public static void PrintCreditScores(int[] creditScores)
    {
        for(int i = 0; i < Arrays.stream(creditScores).count(); i++)
        {
            System.out.println(creditScores[i]);
        }
    }

    public static void SortCreditScores(int[] creditScores)
    {
        for (int i = 0; i < Arrays.stream(creditScores).count(); i++)
        {
            for (int j = 0; j < Arrays.stream(creditScores).count(); j++)
            {
                if (creditScores[i] < creditScores[j])
                {
                    int temp = creditScores[i];
                    creditScores[i] = creditScores[j];
                    creditScores[j] = temp;
                }
            }
        }
    }
}