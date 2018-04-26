/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import domain.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Developer
 */
public class AuthenticationSvcJDBCImpl extends ConnectionManager 
implements IAuthenticationSvc
{
      //---------variable/attribute decelerations---------------
    private ResultSet resultset = null;
    private Statement statement = null;
    private String sql;
    //------------------------------------------------------

    @Override
    public Boolean validateUser(Login login) throws Exception 
    {
        this.connectToDatabase();
       int numDuplicate = 0;//local variable for storing count value returned
       
       Boolean valid=false;
        resultset = null;
        sql = ("SELECT Count(*) AS Duplicates FROM login WHERE username = '" + login.getUsername()+ "' AND password = '" + login.getPassword() + "'");

        try {
            //create statement
            statement = getConnection().createStatement();
            //SQL statement executed
            resultset = statement.executeQuery(sql);

            //gets result from result set
            while (resultset.next()) 
            {
                numDuplicate = resultset.getInt("Duplicates");
            }
            //System.out.println(numDuplicate);
            
            if (numDuplicate==1)
                valid = true;
            //-------			  
        }//end try
        catch (SQLException ex) {
            /**
             * Unable to retrieve duplicate data from database in method
             * testDuplicateDBRecord* *
             */
            throw new SQLException("SQL Error-> Code: 000" + ex);

        } finally {
            //close connections if they were open
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
                close();
        }//end finally
         

        return valid;
    }
    
}
