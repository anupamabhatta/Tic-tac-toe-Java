
package AuxPackage;

import java.io.File;
import java.util.Scanner;

/**
 *
 * Author:  Sergey Y Chernokov
 * Date:    11/28/2017
 * Description: AuxFileReader class contains convenient methods that simplifies working with files
 */
public class AuxFileReader 
{
    private boolean success;
    private String error;
    private String fileLocation;
    private String[] arFile;

    
    public AuxFileReader(String fileLocation) {
        this.fileLocation = fileLocation;
        this.arFile = readFile(fileLocation);
    }

    public String[] getFileArray() {
        return arFile;
    }


    public boolean isSuccess() {
        return success;
    }

    
    public String getError() {
        return "<html><div style='padding:10px;'>" + error + "</div></html>";
    }
    
    


    private  String[] readFile(String sFile)
    {
        error = "";
        Scanner scan = null;
        int iRowCount = getRowCount(sFile);
        
        if (!isSuccess()) //there was a problem reading the file. error is already set
            return null;
        
        if (iRowCount==0)  //perform the check to make sure the file is read and has data
        {
            error += "The file is blank: did not have any records.<br/>";
            success = false;
            return null;
        }
        String[] arRows = new String[iRowCount]; //instantiate the array of the correct length 
         
        try
        { 
           File myFile = new File(sFile);
           scan = new Scanner(myFile); //load the file into the Scanner object
        }
        catch(Exception ex)
        {
           success = false;
           error += ex.getMessage() + "<br/>"; //display error related to the file
        }
        
        iRowCount = 0; //set the loop counter to zero
      
        while (scan.hasNext()) 
        { 
            arRows[iRowCount] = scan.nextLine();
            iRowCount++;
        }
        
        //check if all records are blank:
        int blankCount = 0;
        for(String str : arRows)
        {
            if(str == null || str.isEmpty() || str.length() ==0) 
                blankCount++;
        }
        
        if (blankCount==arRows.length)
        {
            error+= "All "+arRows.length+" records in the file are blank.";
            success = false;
        }


       success = true;
       return arRows; 
    }
     
    
    /***
     * getFileRowCount encapsulates the logic that reads the file using scanner object and returns the number of rows the file has
     * @param sFile - the full path to the file (can be relative)
     * @return int - integer - the number of rows in the file
     */
    private int getRowCount(String sFile)
    {
        File myFile = null;
        Scanner scan = null;
        int iCount = 0;
        try
        {
           myFile = new File(sFile);
           scan = new Scanner(myFile);
        }
        catch(Exception ex)
        {
            error += "There was a problem reading file: '" + fileLocation + "'<br/>Error details: " + ex.getMessage() + "<br/>"; // get the error details
            success = false;
            return iCount;
        }
         
        while (scan.hasNext()) 
        {
            scan.nextLine();
            iCount++;
        }
       success = true;
       return iCount;
    }
    
    
    
    
    
    
    
    
    
     /***
     * readFileReturnStringArray method reads the file and returns array of strings with one row as one array element 
     * This is a convenience method as it is much easier to handle array than the scanner object.
     * Essentially, it converts file into array of strings: one row is one array item.
     * @param sFile - the full path to the file (can be relative)
     * @return String[] - array of Strings
     */
    public static String[] readFileReturnStringArray(String sFile)
    {
       
        Scanner scan = null;
        
        int iRowCount = getFileRowCount(sFile);
        
        if (iRowCount==0)  //perform the check to make sure the file is read and has data
        {
               System.err.print("There was a problem reading the file or the file did not have any records."); //display error related to the file
               return null;
        }
        
        String[] arRows = new String[iRowCount]; //instantiate the array of the correct length 
         
        try
        { 
           File myFile = new File(sFile);
           scan = new Scanner(myFile); //load the file into the Scanner object
        }
        catch(Exception ex)
        {
            System.err.print(ex.getMessage()); //display error related to the file
        }
        
        iRowCount = 0; //set the loop counter to zero
        if (scan!=null) // perform the check and make sure the scanner is not null. If it is null then the file was not read properly.
        {
            while (scan.hasNext()) 
            { 
                arRows[iRowCount] = scan.nextLine();
                iRowCount++;
            }
        }
        else
        {
             System.err.print("There was a problem reading the file."); //display error related to the file
        }
       return arRows; 
    }
    
    /***
     * getFileRowCount encapsulates the logic that reads the file using scanner object and returns the number of rows the file has
     * @param sFile - the full path to the file (can be relative)
     * @return int - integer - the number of rows in the file
     */
    public static int getFileRowCount(String sFile)
    {
        File myFile = null;
        Scanner scan = null;
        int iCount = 0;
        try
        {
           myFile = new File(sFile);
           scan = new Scanner(myFile);
        }
        catch(Exception ex)
        {
            System.err.print(ex.getMessage()); //display error related to the file
        }
         

        while (scan.hasNext()) 
        {
            scan.nextLine();
            iCount++;
        }
        
        return iCount;
    }
}
