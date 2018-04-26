//package decleration
package services;

//import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**superclass for providing common definition for service classes 
 * these classes all access a database, this class provides the definition
 * 
 *
 */
public abstract class ConnectionManager 
{               
             
             
            //--------- connection attribute---------------
             private Connection connection = null;		
	     //------------------------------------------------------
             
             
	     
	    /**makes database connection to MySQL
	     *  
	     * @throws SQLException
	     */
	               
             protected void connectToDatabase() throws SQLException 
	     {
                        
                                               
	          	                         
	          	try 
	          	{
                            String connString = "jdbc:mysql://localhost/lbds?user=root&password=toor";
                            connection = DriverManager.getConnection(connString);
	  	    	
			} 	          	 	
	          	catch (SQLException e)
	          	{          		
	          		/**Unable to connect to database **/
                                    
                            throw new SQLException("Database Connection Error-> Code: 004"+e.toString());
	          		
	          	}
                        
	  	   }//end connection method
		 
		 
		 /**method used for closing database connection 
		  * @throws SQLException
		  */	 
		 protected void close() throws SQLException
		  {
                   
			  
			  try
			  {
                                  if(connection!=null)  connection.close();
			  }
			  catch(SQLException ex)
			  {
				  /**Unable to close database connection**/
                                 
				  throw new SQLException("SQL Error-> Code: 002"+ex.toString());     
			  }
		  }
		 
		
		 /**getter returns the connection
		  * 
		  * @return
		  */
		 protected Connection getConnection()
		 {
			 return this.connection;
		 }

}//end class body
