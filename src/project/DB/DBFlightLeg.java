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
import project.LOGIC.FlightLeg;

/**
 *
 * @author Lenovo
 */
public class DBFlightLeg {
    public static void createTables() throws DBException {
    try {
   
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.flightLeg("              
    + "legNumber VARCHAR(45) NOT NULL,"  
    +"PRIMARY KEY (legNumber, flightNumber, departureDate)," 
    +"FOREIGN KEY (flightNumber, departureDate) "
    +"REFERENCES db2019_18.flight (flightNumber, departureDate) " 
    +"ON DELETE CASCADE "
    +"ON UPDATE CASCADE,"          
    +"FOREIGN KEY (airportCode) "
    +"REFERENCES db2019_18.airport (airportCode) "          
    +"ON DELETE CASCADE " 
    +"ON UPDATE CASCADE" + ")";
      
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
}    
    private static FlightLeg getFlightLeg(String legNumber, String flightNumber, Date departureDate) throws DBException {
        Connection con = null;
    try {
      con = DBConnector.getConnection();
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
	DBConnector.closeConnection(con);
	return null;
      }
      
     // FlightLeg tussenvlucht = new FlightLeg(legNumber, flightNumber, departureDate, airportCode);
      DBConnector.closeConnection(con);
      //return tussenvlucht;
      return null;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
    }
}
