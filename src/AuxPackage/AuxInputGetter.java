package AuxPackage;


import java.util.Scanner;

/**
* Author: Sergey Y Chernokov
* Date:   10/11/2017
* Description: Class is designed to get various input from user
*/

public class AuxInputGetter {
    
    
     public static String getStringFromUser(String sQuestion)
    {
        
      Scanner scan = new Scanner(System.in);
      System.out.print(sQuestion);

      return scan.nextLine();
     
    }
     
     
     
    public static double getNumberFromUser(String sQuestion)
    {
        
      Scanner scan = new Scanner(System.in);
      System.out.print(sQuestion);

      String sVal = scan.nextLine();
      while(!AuxValidator.isNumber(sVal) )
      {
            System.out.print("============ Invalid number ============ \n" +  sQuestion);
            sVal = scan.nextLine();
      }
      double num = Double.parseDouble(sVal);

      return num;
    }
     
    
    public static int getIntFromUser(String sQuestion)
    {
      Scanner scan = new Scanner(System.in);
      System.out.print(sQuestion);

      String sVal = scan.nextLine();
      while(!AuxValidator.isInt(sVal) )
      {
            System.out.print("============ Invalid Integer ============ \n" +  sQuestion);
            sVal = scan.nextLine();
      }
      int num = Integer.parseInt(sVal);

      return num;
    }
     
}
