/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Login;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;
import services.IAuthenticationSvc;


/**
 *
 * @author Kedrian James
 */
public class SocketMgr extends ConnectionMgr
{
   
    private Boolean exit;
    
    static private Logger logger = Logger.getLogger(SocketMgr.class); //class logger
	
    public SocketMgr(){}//default constructor
    
    
   
    public void validateLogin() throws Exception
    {
       exit = true;
       
       logger.info("entering business validateLogin() method");			
       
         
       this.createServerSocket();//creates server socket
      
       while ( exit==true)
       {
           logger.info("Waiting for connections");	
            
           this.waitForConnection(); //wait for a connection 
          
           logger.info("connection made at " + getSocket().getInetAddress().getHostName());
           
           //------------------------------------------------
           ThreadMgr mgr = new ThreadMgr(this.getSocket());
           mgr.start();
           //-------------------------------------------------
           
       }     
        
    }//end validate login method
    
    
    
      
    
       
       
        
    /**closes server connection
     * 
     * @throws IOException 
     */
       public void closeServer() throws IOException
       {
            exit = false;           
            this.closeConnection(); // close socket   
            logger.info("connections closed");
           
       }      
    
    
}
