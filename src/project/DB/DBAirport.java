/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import project.LOGIC.Airport;

/**
 *
 * @author Lenovo
 */
public class DBAirport {
     public static void createTables() throws DBException {
    try {
    
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.airport ("
  + "airportCode VARCHAR(45) NOT NULL, "
  + "airportName VARCHAR(45) NULL, "
  + "PRIMARY KEY (airportCode)" + ")";
        stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
     // retourneert 1 airport
     public static Airport getAirport(String airportCode) throws DBException{
         Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airportCode, airportName "
        + "FROM db2019_18.airport "
	+ "WHERE airportCode = " + airportCode;

      ResultSet srs = stmt.executeQuery(sql);

      String airportName;
      
      if (srs.next()) {
          airportCode = srs.getString("airportCode");
          airportName = srs.getString("airportName");
          
	} else {// we verwachten slechts 1 rij...
	DBConnector.closeConnection(con);
	return null;
      }
      
      Airport luchthaven = new Airport(airportCode, airportName);
              DBConnector.closeConnection(con);
      return luchthaven;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
         
     }
  
}
