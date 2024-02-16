package AuxPackage;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Author:      Sergey Chernokov
 * Date:        1/13/2017
 * Description: Method to be used in form validation
 */

public class AuxValidatorForm {
    

//=========================================  Validate Number =============================================================   
  

    public static boolean IsValidNumber(JComponent control, JLabel lblError, double min, double max) 
    {
        String sErr = "Enter valid number between " + AuxFormatter.formatNumber(min) + " and "  +  AuxFormatter.formatNumber(max);
        return IsValidNumber( control,  lblError,  min,  max,  sErr) ;
         
    }
    
    
    
    public static boolean IsValidNumber(JComponent control, JLabel lblError, double min, double max, String sErr) 
    {
       String sInput = "";

        if (control instanceof JTextField)
            sInput = ((JTextField)control).getText();
        
          if (control instanceof JComboBox)
            sInput = ((JComboBox)control).getSelectedItem().toString();
       
         if ( AuxValidator.isNumber(sInput))
         {
             double dNum = Double.parseDouble(sInput);
            
             if (dNum < min)
                sErr =  "The number is too small. " + sErr;                   
             else if (dNum > max)
                   sErr = "The number is too large. " + sErr;
             else 
             {
                styleNormal(control);
                lblError.setText("");
                lblError.setVisible(false);
                return true;
             }         
         }
         

        lblError.setText(sErr);
        styleError(control);
        styleError(lblError);
        return false;
         
    }
    
    
    public static boolean IsValidNumber(JComponent control, JLabel lblError, String sErr) 
    {
        String sVal = "";

        if (control instanceof JTextField)
            sVal = ((JTextField)control).getText();
        
          if (control instanceof JComboBox)
            sVal = ((JComboBox)control).getSelectedItem().toString();
        
        
        if ( AuxValidator.isNumber(sVal)){

             styleNormal(control);
             lblError.setText("");
             return true;
         }
         else
         {
             lblError.setText(sErr);
             styleError(control);
             styleError(lblError);
             return false;
         }
    }
  
    
    
    public static boolean  IsValidNumber(JTextField txtField, JLabel lblError) 
    {
        String  sErr =  "Enter a valid numeric value";
        return IsValidNumber( txtField,  lblError,  sErr) ;

    }
    
      
    
   public static boolean isEmpty(JComponent control, JLabel lblError)
   {
      return isEmpty(control, lblError, "This field is required.");
   }

   public static boolean isEmpty(JComponent control, JLabel lblError, String customError)
   {
       String sVal="";
       if (control instanceof  JComboBox )
       {
           if (((JComboBox)control).getSelectedItem()==null)
               return true;
           sVal = ((JComboBox)control).getSelectedItem().toString();
       }
       
        if (control instanceof  JTextField )
           sVal = ((JTextField)control).getText();
        
        
        if (sVal.equals(""))
        {
            styleError(control);
            styleError(lblError);
            customError = AuxMethods.wrapHTML(customError);
            lblError.setText(customError);
            return true;
        }
        
        
        styleNormal(control);
        styleNormal(lblError);
        lblError.setVisible(false);
        return false;
   }
    
    
    
   
   
   
   
    
  
 //========================================= END Validate Numeric =============================================================   
    

    
     public static boolean folderExistsAndNotEmpty(JTextField txtBox, JLabel lblError, String fieldName)
     {  
       boolean status = true;
       
       String sError = "";
       String sDir = txtBox.getText();
       

       if (sDir.isEmpty())
       {
           sError+= "'" + fieldName + "'" + " field is required.";
           lblError.setText(sError);
           status =  false;
       }
       
       File  seachDir = new File(sDir);
       
       if (!seachDir.exists())
       {
           sError= "'" + fieldName + "'" + " directory does not exist. Please provide correct directory.";
           status =  false;
       }
       
       if (!seachDir.isDirectory())
       {
            sError = "Provided value in " + "'" + fieldName + "' is not a valid directory." ;
            status =  false;
       }
               
               
       if (status)
       {
            AuxValidatorForm.styleNormal(txtBox);
           lblError.setText("");
           lblError.setVisible(false);
       }
       else
       {
           AuxValidatorForm.styleError(txtBox);
           AuxValidatorForm.styleError(lblError);
           lblError.setText(sError);
       }
       return status; 
    }
    
    
    public static void styleError(JComponent control)
    {
        control.setVisible(true);   
        control.setBackground(new java.awt.Color(255, 204, 204));
        control.setForeground(new java.awt.Color(204, 0, 51));
        
        
        if (control instanceof JPanel )
        {
            control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
//            control.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            control.setOpaque(true);
        }
        else {
            //control.setOpaque(false);
        }
        
    }
    
    
    public static void styleNormal(JComponent control)
    {
        control.setVisible(true);   
        
      
        
        if (control instanceof JPanel )
        {
            //control.setLayout(null);
            control.setOpaque(false );
            control.setBorder(null);
            
        }
        else {
            control.setBackground(new java.awt.Color(255, 255, 255));
            control.setForeground(new java.awt.Color(0, 0, 0));
        }
    }
    
    public static void styleInfo(JComponent control)
    {
        control.setVisible(true);   
        control.setBackground(new java.awt.Color(217, 237, 247));
        control.setForeground(new java.awt.Color(49, 112, 143));
        control.setOpaque(true);
    }
    
    
    public static void styleStatus(JComponent control, boolean isSuccess)
    {
        if (isSuccess){
            control.setVisible(true);   
            control.setBackground(new java.awt.Color(255, 255, 255));
            control.setForeground(new java.awt.Color(0, 0, 0));
            control.setOpaque(true);
        }
        else{
              control.setVisible(true);   
            control.setBackground(new java.awt.Color(255, 204, 204));
            control.setForeground(new java.awt.Color(204, 0, 51));
            control.setOpaque(true);
        }
        
    }
    
   
    
}


