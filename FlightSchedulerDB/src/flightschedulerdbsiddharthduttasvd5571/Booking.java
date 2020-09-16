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
public class Booking
{
    private String PassengerName;
    private Date Date;
    private String FlightNumber;
    private PreparedStatement InsertBooking;
    private static PreparedStatement SelectCustomersBooked;
    private static PreparedStatement SelectCustomerDayBooked;
    private static PreparedStatement DeleteBooking;
    private PreparedStatement getFlightSeats;
    private static DBConnection dbConnection;
    private static Connection connection;
    
    
    
    public Date getDay()
    {
        return Date;
    }
      
    
    public String getFlightNumber()
    {
        return FlightNumber;
    }
    
    public String getPassenger()
    {
        return PassengerName;
    }
    
    
    public Booking() 
    {
        
        
    }
    
    public Booking(String aname, String aflightNo, Date adate) 
    {
        PassengerName = aname;
        Date = adate;
        FlightNumber =aflightNo;
        
    }
    
    public void deleteBookingRecord(String name, String aflightNumber, Date adate) throws SQLException
    {
        connection = dbConnection.getDBConnection();
        DeleteBooking = connection.prepareStatement("DELETE FROM BOOKING WHERE NAME=? AND DAY=?");
        DeleteBooking.setString(1,name);
        DeleteBooking.setDate(2, adate);
        DeleteBooking.executeUpdate();
        
    }
    
    public static List< Booking > getCustomerDayBooked(String Name, Date adate) throws SQLException 
    {
        connection = dbConnection.getDBConnection();
        SelectCustomerDayBooked = connection.prepareStatement("SELECT * FROM BOOKING WHERE NAME=? AND DAY=?");
        List<Booking> results = null;
        ResultSet resultSet = null;
        try
        {
            
            SelectCustomerDayBooked.setString(1, Name);
            SelectCustomerDayBooked.setDate(2, adate);
            resultSet = SelectCustomerDayBooked.executeQuery();
            results = new ArrayList< Booking >();
            while(resultSet.next())
            {
                results.add(new Booking(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3)));
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
    
    public int getSeatsBooked(String flightName, Date adate)
    {
        int seatsBooked = 0;
        ResultSet resultSet;
        connection = dbConnection.getDBConnection();
        try
        {
            connection = dbConnection.getDBConnection();
        getFlightSeats = connection.prepareStatement("select count(flightNumber) from booking where flightNumber = ? and day = ?");
             
            getFlightSeats.setString(1, flightName); getFlightSeats.setDate(2, adate); 
            resultSet = getFlightSeats.executeQuery(); 
            resultSet.next(); 
            seatsBooked = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
        return seatsBooked;
    }
    
    public String ToString()
    {
        return PassengerName;
    }
    
    public String ToString1()
    {
        return ""+PassengerName+","+FlightNumber+","+Date;
    }
    
    public static List< Booking > getCustomersBooked(String flightName, Date adate) throws SQLException 
    {
        connection = dbConnection.getDBConnection();
        SelectCustomersBooked = connection.prepareStatement("SELECT * FROM BOOKING WHERE FLIGHTNUMBER=? AND DAY=?");
        List<Booking> results = null;
        ResultSet resultSet = null;
        try
        {
            
            SelectCustomersBooked.setString(1, flightName);
            SelectCustomersBooked.setDate(2, adate);
            resultSet = SelectCustomersBooked.executeQuery();
            results = new ArrayList< Booking >();
            while(resultSet.next())
            {
                results.add(new Booking(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3)));
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
    
    
    
    public static List< Booking > getCustomersBooked(String passengerName) throws SQLException 
    {
        connection = dbConnection.getDBConnection();
        SelectCustomersBooked = connection.prepareStatement("SELECT * FROM BOOKING WHERE NAME=?");
        List<Booking> results = null;
        ResultSet resultSet = null;
        try
        {
            
            SelectCustomersBooked.setString(1, passengerName);
            
            resultSet = SelectCustomersBooked.executeQuery();
            results = new ArrayList< Booking >();
            while(resultSet.next())
            {
                results.add(new Booking(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3)));
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
    
    
    public int addBooking(String aname, String aflightNumber, Date adate)
    {
        int result = 0;
        
        try
        {
            connection = dbConnection.getDBConnection();
            InsertBooking = connection.prepareStatement("INSERT INTO BOOKING "+"(Name,FlightNumber,Day)"+"VALUES (?,?,?)");
            
            InsertBooking.setString(1, aname);
            InsertBooking.setString(2, aflightNumber);
            InsertBooking.setDate(3, adate);
            result = InsertBooking.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return result;
    }
     
    
}

