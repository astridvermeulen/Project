
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.LOGIC.FlightLeg;

/**
 *
 * @author TEAM DB
 */
public class DBFlightLeg {
     
    //retourneert 1 flightleg 
    public static FlightLeg getFlightLeg(int legNumber, String flightNumber, String departureDate) throws DBException {
        Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
              String sql =  "SELECT * FROM flightleg " + 
                            "WHERE legNumber = " + legNumber + " AND flightNumber = '" + flightNumber + "' " +
                            "AND departureDate = '" + departureDate + "'";
 
      ResultSet srs = stmt.executeQuery(sql);
     
      
      String originAirportCode, destinationAirportCode, departureTime, arrivalDate, arrivalTime;
      
      if (srs.next()) {
          legNumber = srs.getInt("legNumber");
          departureDate = srs.getString("departureDate");
          originAirportCode = srs.getString("originAirportCode");
          destinationAirportCode = srs.getString("destinationAirportCode");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");

	} else {
	DBConnection.closeConnection(con);
	return null;
      }
      
      FlightLeg tussenvlucht = new FlightLeg(legNumber,originAirportCode,destinationAirportCode,departureDate,arrivalDate,departureTime,arrivalTime);
      DBConnection.closeConnection(con);
      return tussenvlucht;
      
    }
    
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
  //retourneert alle flightlegs voor een gegeven vlucht 
    public static ArrayList<FlightLeg> getFlightLegs(String flightNumber, String departureDate) throws SQLException, DBException{
        Connection con = null;
        ArrayList<FlightLeg> vluchtdeel = new ArrayList<>();               
        
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
            String sql =    "SELECT * " +
                            "FROM flightleg " +
                            "WHERE departureDate = '" + departureDate + "' AND flightNumber = '" + flightNumber + "'";
        ResultSet srs = stmt.executeQuery(sql);
     
       int legNumber;
       String originAirportCode, destinationAirportCode, departureTime, arrivalDate, arrivalTime;
     
       while(srs.next()) {
          legNumber = srs.getInt("legNumber");
          departureDate = srs.getString("departureDate");
          originAirportCode = srs.getString("originAirportCode");
          destinationAirportCode = srs.getString("destinationAirportCode");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");

         int i = 0;
         FlightLeg test = new FlightLeg(legNumber,originAirportCode,destinationAirportCode,departureDate,arrivalDate,departureTime,arrivalTime);
         vluchtdeel.add(i, test);
         i++;              
         
	}
       
       DBConnection.closeConnection(con);   
       vluchtdeel.trimToSize();
       return vluchtdeel; 
    
    }
      catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }   
    } 
    
    public static void main(String[] args) throws DBException, SQLException{
        ArrayList<FlightLeg> test = new ArrayList<>();
        String num = "LU0945";
        String date = "19/01/2019";
        
        try {
            test = getFlightLegs(num, date);
            int size = test.size();
          for(int position = 0; position < size; position++)
              System.out.println(test.get(position).getLegNumber() + test.get(position).getLegDestination());
    } catch (DBException ex) {
      Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
    }

        
    }
        
}