/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;
import services.Factory;
import services.IService;
import services.exceptions.ServiceLoadException;

/**
 *
 * @author Administrator
 */
public abstract class ConnectionMgr 
{
    private ServerSocket server; // server socket     
    private Socket connection; // connection to client    
    static private Logger logger = Logger.getLogger(ConnectionMgr.class); // named logger definition
    private Factory factory = new Factory(); //instantiate factory
	
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

        
        /**creates server socket for connection
         * 
         * @throws IOException 
         */
        protected void createServerSocket() throws IOException
        {
           
             server = new ServerSocket( 8000); // create ServerSocket    
             logger.info("Server Socket Created");	
        }
        
        /**waits for a connection from client
         * 
         * @throws IOException 
         */
        
        protected void waitForConnection() throws IOException
        {   
            logger.info("Waiting for connection");            
            connection = server.accept(); // allow server to accept connection
            //return connection; //returns the connection                    
        }
    
        /**closes server connection
         * 
         * @throws IOException 
         */
        protected void closeConnection() throws IOException
        {            
           if(connection!=null)
               connection.close(); // close socket   
        }
        
        /** returns socket
         * 
         * @return 
         */
        public Socket getSocket()
        {
            return connection;
        }
    
    
    
}
