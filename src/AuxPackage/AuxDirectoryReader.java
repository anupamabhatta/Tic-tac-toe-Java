
package AuxPackage;

import java.io.File;

public class AuxDirectoryReader {
    
   private String status;
   private String[] arFilesPath;
    
    
    
   public AuxDirectoryReader(String sDirectory)
    {
        setArFilesPath(sDirectory); 
    }   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getArFilesPath() {
        return arFilesPath;
    }

    public void setArFilesPath(String sDirectory) 
    {
      
        arFilesPath = null; //clear array
       try 
       {
           
            File dir = new File(sDirectory);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) 
            {
                arFilesPath = new String[directoryListing.length];
                for (int i = 0; i < directoryListing.length; i++)
                {
                    arFilesPath[i] = directoryListing[i].getAbsolutePath();
                }
                
                status = "OK";

            } 
            else 
            {
              // Handle the case where dir is not really a directory.
              // Checking dir.isDirectory() above would not be sufficient
              // to avoid race conditions with another process that deletes
              // directories.
                status = "Problem accessing directory. <br/> Directory '"+ sDirectory + "' Could not be accessed.";
                status = "<html>" + status + "</html>";
               
            }
       }
       catch(Exception ex)
       {
           status = "Unexpected Error: " + ex.getMessage();
       }
       
    }
    
    
  
    
    
    
}
