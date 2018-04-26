/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import domain.Login;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import services.Factory;
import services.IAuthenticationSvc;
import services.IService;
import services.exceptions.ServiceLoadException;

/**
 *
 * @author Developer
 */
public class ThreadMgr extends Thread
{
    
   private ObjectOutputStream output; // output stream to client
   private ObjectInputStream input; // input stream from client
   private Socket connection; // connection to client   
   private Factory factory = new Factory(); //instantiate factory

    static private Logger logger = Logger.getLogger(ThreadMgr.class); //class logger
        
   /**primary constructor
     * 
     * @param connection 
     */ 
    public ThreadMgr (Socket connection)
    {
        this.connection = connection;  
       
    }

    @Override
    public void run()    
    {
        try
        {
        IAuthenticationSvc iAuthenticationSvc = (IAuthenticationSvc) this.getService(IAuthenticationSvc.NAME); //l
        output = new ObjectOutputStream( connection.getOutputStream() ); // set up output stream for objects
         output.flush(); // flush output buffer to send header information   
           
           input = new ObjectInputStream( connection.getInputStream());  // set up input stream for objects
           
           logger.info("All Streams Inatilized");
           
           //Login login = new Login();
          
           Login login;
          
           login  = (Login) input.readObject(); // read new object
           
           Boolean msg = iAuthenticationSvc.validateUser(login);
           
           output.writeObject(msg);//sends back message to client
           
           output.flush(); // flush output to client              
           
                       
        }
        catch(Exception e)
        {
           logger.error("Exception caught in thread "+e);  
           JOptionPane.showMessageDialog(null,e.toString(),"Error while processing connection thread",JOptionPane.ERROR_MESSAGE);            
        }
           
        
        
    }
    
    
    /**method for returning service
	 * dynamically loaded by factory 
	 * @param serviceName
	 * @return
	 * @throws ServiceLoadException
	 */
	protected IService getService(String serviceName) throws ServiceLoadException
	{              
            return factory.getService(serviceName);
	}
        
        
         public void  closeConnection() throws IOException
       {
           
           logger.info("Attempting to close threaded connections");
           if(output!=null) output.close(); // close output stream
           if(input!=null) input.close(); // close input stream  
           if(connection!=null) connection.close(); //close connection            
           logger.info("connections closed");
           
       }   
    
    
}
