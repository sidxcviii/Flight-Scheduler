/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerdbsiddharthduttasvd5571;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sid
 */
public class DBConnection 
{
 
    private static final String URL = "jdbc:derby://localhost:1527/FlightScheduler";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection = null; 
 
    public static Connection getDBConnection()
    {
        if(connection==null)
        {
       
            try
            {
             connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            }
            catch ( SQLException sqlException )
            {
            sqlException.printStackTrace();
            System.exit( 1 );
            }
        }
     return connection;
     }
    
    public void close()
    {
        try
        {
            connection.close();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
