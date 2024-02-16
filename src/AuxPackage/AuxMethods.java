package AuxPackage;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schernokov
 */
public class AuxMethods 
{
    
    
    public static double DateDiffSeconds(Date startDate) {


    /** Today's date */
    Date today = new Date();

    // Get msec from each, and subtract.
    long diff = today.getTime() - startDate.getTime();
    
    double seconds = (double) diff / (double)(1000);
    seconds = Math.round(seconds*100)/100D;
    return seconds;
            
  
    }
    
    
    
     public static String wrapHTML (String s)
     {
         String html =  s;
         if (!html.contains("<html>"))
             html =   "<html>" + html;
         
          if (!html.contains("</html>"))
             html =   html + "</html>";
          
        return html;
     }
    
     
     public static void openFile(File file)
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac"))
        {
            try
            {
                 Runtime.getRuntime().exec(new String[]{"/usr/bin/open",file.getAbsolutePath()});
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }
        else
        {
            try 
            {
                Desktop.getDesktop().open(file);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } 
        }
    
    
  }
    
}
