/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerdbsiddharthduttasvd5571;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sid
 */
public class Passenger {
    
    private String PassengerName;
    private PreparedStatement insertPassenger;
    private static PreparedStatement selectAllPassengers;
    private static DBConnection dbConnection;
    private static Connection connection;
    
    public Passenger(String apassengerName)
    {
        PassengerName = apassengerName;
    }
    
    public String getPassenger()
    {
        return PassengerName;
    }
    
    public void setPassenger(String apassengerName)
    {
        PassengerName = apassengerName;
    }
    
    public String toString()
    {
        return PassengerName;
    }
    
    
    
    public Passenger()
    {
        try
        {
            connection = dbConnection.getDBConnection();
            insertPassenger = connection.prepareStatement("INSERT INTO PASSENGER"+"(Name)"+"VALUES (?)");
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public int addPassenger(String aname)
    {
        int result = 0;
        try
        {
            insertPassenger.setString(1, aname);
            result = insertPassenger.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return result;
    }
        
    public static List< Passenger > getAllPassenger() throws SQLException 
    {
        
        selectAllPassengers = connection.prepareStatement("SELECT * FROM PASSENGER");
        List<Passenger> results = null;
        ResultSet resultSet = null;
        try
        {
            resultSet = selectAllPassengers.executeQuery();
            results = new ArrayList< Passenger >();
            while(resultSet.next())
            {
                results.add(new Passenger(resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                dbConnection.close();
            }
        }
        return results;        
    }
    
    
    
  }




