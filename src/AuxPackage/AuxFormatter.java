package AuxPackage;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AuxFormatter {
   
    public static String formatDate(Date dDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try 
        {
            String sDate = format.format(dDate);
             return sDate;
        }
        catch(Exception ex){
            return "";
        }
       
        
    }
    
    public static String formatDateTime(Date dDate)
    {
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try 
        {
            String sDate = format.format(dDate);
            return sDate;
        }
        catch(Exception ex){
            return "";
        }
        
    }
    
    public static String formatCurrency(double dVal)
    {
        return NumberFormat.getCurrencyInstance().format(dVal);
    }
      
     public static String formatNumberTwoDecimals(double dVal)
     {
         DecimalFormat fmt = new DecimalFormat("0.##"); 
         return fmt.format(dVal);
     }
     
     public static String formatPercent(double dVal)
     {
         NumberFormat format = NumberFormat.getPercentInstance();
         format.setMaximumFractionDigits(2);
         format.setMinimumFractionDigits(2);
         return format.format(dVal);
     }
     
    public static String formatNumber(double dVal)
    {

       return NumberFormat.getInstance().format(dVal);
    }
    
    public static String convertToHTML(String sVal)
    {
        sVal = sVal.replace("\n", "<br/>");
        sVal = "<html><div style='text-align: center;'>" + sVal + "</div></html>";
        return sVal;
    } 
    
     public static long convertDateToLong(String sDate)
   {
       String year = sDate.substring(6,10);
       String month = sDate.substring(0,2);
       String day = sDate.substring(3,5);
       
       sDate = "" + year + month + day;
       return Long.parseLong(sDate);
   }
    
  public static String convertDateToString(Long lDate)
   {
       String sDate = String.valueOf(lDate);
       
        String year = sDate.substring(0,4);
       String month = sDate.substring(4,6);
       String day = sDate.substring(6,8);
       
       sDate =  month + "/" +  day + "/" + year;
       return sDate;
   }
      
}
