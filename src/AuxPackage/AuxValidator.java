package AuxPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* Author: Sergey Y Chernokov
* Date:   10/11/2017
* Description: Class that contains static methods to validate values
 */
public class AuxValidator {
    
        
    /***
     * 
     * @param String value to validate - check to see if it is a number. isNumeric() method uses try-catch block to determine if the string a valid number.
     * @return true or false. True if the string is a valid number; false if the string is not a valid number
     */
    public static boolean isNumeric(String sValue)
    {
       // try parsing the value into integer, if fails then the number is not integer
       try {
           double num = Double.parseDouble(sValue);
           return true;
       }
       catch(Exception ex) //raise an exception if the code fails to execute
       {
           return false; // return false if parsing to an integer fails. It means that the value is not integer.
       }

    }
     
    /***
     * 
     * @param String value to validate - check to see if it is a number. isNumber() uses while loop to inspect the string.
     * @return true or false. True if the string is a valid number; false if the string is not a valid number
     */
    public static boolean isNumber(String sValue) 
    {
        boolean status=true;
           if(sValue.length()<1)
               return false;
           
            
        sValue = sValue.replace("$","");
        sValue = sValue.replace(" ","");
        
        
        
           int iCount=0;
           while (iCount < sValue.length())
           {
               char c=sValue.charAt(iCount);
               if( !Character.isDigit(c) && c !='.' )
               {
                   status=false;
                   break;
               }
               
               iCount++;
           }
           return status;
    }
    
    
    public static boolean isInt(String sValue) 
    {
        boolean status=true;
        if(sValue.length()<1)
            return false;

         sValue = sValue.replace("$","");
         sValue = sValue.replace(" ","");
        
        try 
        {
            int iInt = Integer.parseInt(sValue);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
 
    }
    
    
    
    
}
