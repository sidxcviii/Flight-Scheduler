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

public class Flight {
    
    private String FlightNumber;
    private int Seats;
    private static PreparedStatement InsertFlight;
    private static PreparedStatement SelectAllFlights;
    private static PreparedStatement getFlightSeats;
    private static DBConnection dbConnection;
    private static Connection connection;
    private static PreparedStatement DeleteFlight;
    
    public String toString()
    {
        return FlightNumber;
    }
    
    public Flight()
    {
        
    }
    
    public int getSeatsAvailable(String flightName)
    {
        int seatsAvailable = 0;
        ResultSet resultSet = null;
        connection = dbConnection.getDBConnection();
        try
        {
            
            getFlightSeats = connection.prepareStatement("SELECT SEATS FROM FLIGHT WHERE flightName = ? "); 
            getFlightSeats.setString(1, flightName); 
            resultSet = getFlightSeats.executeQuery(); 
            resultSet.next(); 
            seatsAvailable = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
        return seatsAvailable;
    }
    
    
    
    public void deleteFlightRecord(String name) throws SQLException
    {
        connection = dbConnection.getDBConnection();
        DeleteFlight = connection.prepareStatement("DELETE FROM FLIGHT WHERE FLIGHTNAME=?");
        DeleteFlight.setString(1,name);
        DeleteFlight.executeUpdate();
    }
    
    
    public int addFlight(String flight, int seats)
    {
        int result = 0;
        connection = dbConnection.getDBConnection();
        
        try
        {
            InsertFlight = connection.prepareStatement("INSERT INTO FLIGHT"+"(FlightName,Seats)"+"VALUES (?,?)");
            InsertFlight.setString(1, flight);
            InsertFlight.setInt(2, seats);
            result = InsertFlight.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return result;
    }
    
    public static List< Flight > getAllFlights() throws SQLException 
    {
        connection = dbConnection.getDBConnection();
        SelectAllFlights = connection.prepareStatement("SELECT * FROM FLIGHT");
        List<Flight> results = null;
        ResultSet resultSet = null;
        try
        {
            resultSet = SelectAllFlights.executeQuery();
            results = new ArrayList< Flight >();
            while(resultSet.next())
            {
                results.add(new Flight(resultSet.getString(1),resultSet.getInt(2)));
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
    
    public Flight(String aflightNumber, int aseats)
    {
        FlightNumber = aflightNumber;
        Seats = aseats;
    }
        
    public String getFlightNumber()
    {
        return FlightNumber;
    }
    
    public int getSeats()
    {
        return Seats;
    }
}

