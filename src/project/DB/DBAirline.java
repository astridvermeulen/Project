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
import project.LOGIC.Airline;

/**
 *
 * @author klaas
 */
public class DBAirline {
    public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = 
    "CREATE TABLE db2019_18.airline("
  + "airlineCode INT NOT NULL,"
  + "airlineName VARCHAR(45) NULL,"
  + "PRIMARY KEY (airlineCode)"+")";
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
    
   public static Airline getAirline(int airlineCode) throws DBException{
         Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airlineCode, airlineName "
        + "FROM db2019_18.airline "
	+ "WHERE airlineCode = " + airlineCode;

      ResultSet srs = stmt.executeQuery(sql);

      String airlineName;
      
      if (srs.next()) {
          airlineName = srs.getString("airlineName");
          airlineCode = srs.getInt("airlineCode");
	
	} 
      else {// we verwachten slechts 1 rij...
	DBConnector.closeConnection(con);
	return null;
      }
      
      Airline airline = new Airline(airlineCode, airlineName);
              DBConnector.closeConnection(con);
      return airline;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
         
     }

    
}
