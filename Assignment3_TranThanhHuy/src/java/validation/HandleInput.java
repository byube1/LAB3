/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author admin
 */
public class HandleInput {
      public  boolean handleUser (String userName){
        return userName!=null&&userName.length()>1&&userName.matches("[a-zA-Z0-9]+")&&userName.length()<30;
    }     
      public boolean handleEmail(String email){
          return email.length()>1;
      }
      
      public boolean handlePhone (String phone){
          return phone!=null&&phone.length()==10&&phone.matches("[0-9]+");
      }     
          public boolean handleAmount(String amount){   
          return amount!=null&&amount.matches("[0-9]+")&&amount.length()<10;
    }
    public boolean handleText(String text,int maxlength){   
            return text!=null&&text.length()>1&&text.length()<maxlength;
    }
       
    
}
