/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuxPackage;

import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;


public class AuxFile {
    
    
    public static File getFileFromChooser()
    {
        File selectedFile = null;
        
        //the the folder where the application is 
        String applicationDir =  getProgramDirectory(); // get this application folder to use as the starting point in the folder chooser.

        //specify the starting directory of the file chooser.
        JFileChooser fileChooser = new JFileChooser(applicationDir);
        
         int returnValue = fileChooser.showOpenDialog(null); //show the dialog window of file chooser and get the return value

          //make sure user selected the file/direcotry properly by comparing return value with approve option
         if (returnValue == JFileChooser.APPROVE_OPTION)
            selectedFile = fileChooser.getSelectedFile(); //get the selected file/directory
           
         return selectedFile;
    }
     
    public static File getFileDirectoryFileChooser()
    {
        File selectedFile = null;
        
        //the the folder where the application is 
        String applicationDir =  getProgramDirectory(); // get this application folder to use as the starting point in the folder chooser.

        //specify the starting directory of the file chooser.
        JFileChooser fileChooser = new JFileChooser(applicationDir);
        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // enforces to get the directory instead of file
         

         int returnValue = fileChooser.showOpenDialog(null); //show the dialog window of file chooser and get the return value

          //make sure user selected the file/direcotry properly by comparing return value with approve option
         if (returnValue == JFileChooser.APPROVE_OPTION)
            selectedFile = fileChooser.getSelectedFile(); //get the selected file/directory
           
         return selectedFile;
    }
    

    
    
    
 //========================== Methods that figure out the application directory ====================================== 
    /***
     * getProgramDirectory() accurately returns the application directory, i.e. the directory where the application is on the user's computer.
     * Since the application path of a JAR and an application running from inside an IDE differs, there is a need to consistently return the correct current directory:
     * @return String with the application directory 
     */
   public static String getProgramDirectory() // 
    {
        if (runningFromJAR())
        {
            return getCurrentJARDirectory();
        } else
        {
            return getCurrentProjectDirectory();
        }
    }
   
   
   private static String getJarName()
    {
        return new File(AuxFile.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath())
                .getName();
    }

    private static boolean runningFromJAR()
    {
        String jarName = getJarName();
        return jarName.contains(".jar");
    }

    

    private static String getCurrentProjectDirectory()
    {
        return new File("").getAbsolutePath();
    }

    private static String getCurrentJARDirectory()
    {
        try
        {
            return new File(AuxFile.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        } catch (URISyntaxException exception)
        {
            exception.printStackTrace();
        }

        return null;
    }
  //========================== END Methods that figure out the application directory ====================================== 
    
    
    
}
