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
}
