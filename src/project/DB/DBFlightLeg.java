/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import project.LOGIC.Flight;
import project.LOGIC.FlightLeg;

/**
 *
 * @author Lenovo
 */
public class DBFlightLeg {
     
    private static FlightLeg getFlightLeg(String legNumber, String flightNumber, Date departureDate) throws DBException {
        Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT legNumber, flightNumber, departureDate "
	+ "FROM db2019_18.flight "
        + "WHERE legNumber = " + legNumber     
	+ "AND flightNumber = " + flightNumber
        + " AND departureDate = " + departureDate;

      ResultSet srs = stmt.executeQuery(sql);
     
      
      String airportCode;
      
      if (srs.next()) {
          legNumber = srs.getString("legNumber");
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getDate("departureDate");
          airportCode = srs.getString("airportCode");

	} else {
	DBConnection.closeConnection(con);
	return null;
      }
      
     // FlightLeg tussenvlucht = new FlightLeg(legNumber, flightNumber, departureDate, airportCode);
      DBConnection.closeConnection(con);
      //return tussenvlucht;
      return null;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
    
   /* public static ArrayList<FlightLeg> getFlightLegs(String flightNumber, int departureDate) throws SQLException, DBException{
        Connection con = null;
        ArrayList<FlightLeg> vluchtdeel = new ArrayList<>();               
        
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql =  "SELECT * FROM flightleg AS fl " + 
                    "INNER JOIN flight AS f " +  
                    "WHERE fl.departureDate = " + departureDate + "AND fl.flightNumber = '" + flightNumber + "'";
      ResultSet srs = stmt.executeQuery(sql);
     
       int legNumber;
       String airportCode1,airportCode2;
       
       while(srs.next()) {
          legNumber = srs.getInt("legNumber");
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getInt("departureDate");
          airportCode1 = srs.getString("airportCodeOrigin");
          airportCode2 = srs.getString("airportCodeDestination");
          
         int i = 0;
         FlightLeg test = new FlightLeg(legNumber, airportCode1, airportCode2 , departureDate);
         vluchtdeel.add(i, test);
         i++;              
         DBConnection.closeConnection(con);      
         

	}
       return vluchtdeel; 
    
    }
      catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }   
    }*/
        
}
