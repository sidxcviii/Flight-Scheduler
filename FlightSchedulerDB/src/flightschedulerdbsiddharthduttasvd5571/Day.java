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
public class Day {
    
    private Date Date;
    private static PreparedStatement SelectAllDays;
    private static DBConnection dbConnection;
    private static Connection Connection;
    private PreparedStatement InsertDay;
    
    public void setDay(Date adate)
    {
        Date = adate;
    }
    
    public Day(Date adate)
    {
        Date = adate;
    }
    
    public Date getDay()
    {
        return Date;
    }
    
    public Date toDate()
    {
        return Date;
    }
    
    public String toString()
    {
        return Date.toString();
    }
    
    Day()
    {
        
    }
    
    public int addDay(Date adate)
    {
        int result = 0;
        
        try
        {
            Connection = dbConnection.getDBConnection();
            InsertDay = Connection.prepareStatement("INSERT INTO DAY "+"(DATE)"+"VALUES (?)");
            
            InsertDay.setDate(1, adate);
            
            result = InsertDay.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return result;
    }
    
    public static List< Day > getAllDays() throws SQLException 
    {
        Connection = dbConnection.getDBConnection();
        SelectAllDays = Connection.prepareStatement("SELECT * FROM DAY");
        List<Day> results = null;
        ResultSet resultSet = null;
        try
        {
            resultSet = SelectAllDays.executeQuery();
            results = new ArrayList< Day >();
            while(resultSet.next())
            {
                results.add(new Day(resultSet.getDate(1)));
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
