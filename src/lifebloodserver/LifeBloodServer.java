/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lifebloodserver;

import domain.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.AuthenticationSvcJDBCImpl;

/**
 *
 * @author Developer
 */
public class LifeBloodServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        AuthenticationSvcJDBCImpl tst = new AuthenticationSvcJDBCImpl();
        
        Boolean valid = false;
        Login lg = new Login();
       lg.setUsername("JohnDoe");
       lg.setPassword("123456");
        try {
            valid = tst.validateUser(lg);
             System.out.println(valid);
            
// isValidLogin();
        } catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
}
