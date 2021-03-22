/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;


import java.util.Calendar;

/**
 *
 * @author admin
 */
public class MyTool {
    
     public  String autoCreateID(String firstString){
         Calendar cal = Calendar.getInstance();
        int sum = cal.get(Calendar.SECOND)*1000+ cal.get(Calendar.MINUTE)*60+cal.get(Calendar.HOUR_OF_DAY)*120+cal.get(Calendar.DAY_OF_YEAR)+cal.get(Calendar.MILLISECOND);          
         return firstString+"-"+sum;
    }
    public int autoCreateNumberID(){
        Calendar cal = Calendar.getInstance();
        int sum = cal.get(Calendar.SECOND)*1000+ cal.get(Calendar.MINUTE)*60+cal.get(Calendar.HOUR_OF_DAY)*120+cal.get(Calendar.DAY_OF_YEAR)+cal.get(Calendar.MILLISECOND) ;          
        return sum;
    }

}
