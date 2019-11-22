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
import java.util.ArrayList;
import project.LOGIC.Flight;

/**
 *
 * @author klaas
 */
public class DBFlight {
     public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.flight("              
    + "flightNumber VARCHAR(45) NOT NULL," 
    +"departureDate DATE NOT NULL," 
    +"departureTime TIME NULL," 
    +"arrivalDate DATE NULL," 
    +"arrivalTime TIME NULL," 
    +"price DOUBLE NULL," 
    +"origin VARCHAR(45) NULL," 
    +"destination VARCHAR(45) NULL," 
    +"airlineCode VARCHAR(45) NOT NULL," 
    +"PRIMARY KEY (flightNumber, departureDate)," 
    +"FOREIGN KEY (airlineCode) "
    +"REFERENCES db2019_18.airline (airlineCode) " 
    +"ON DELETE CASCADE " 
    +"ON UPDATE CASCADE" + ")";
      
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }  
}
 
     private static Flight getFlight(String flightNumber, String departureTime) throws DBException {
        Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate, departureTime, arrivalDate, arrivalTime, price, origin, destination, airlineCode "
	+ "FROM db2019_18.flight "
	+ "WHERE flightNumber = " + flightNumber
        + " AND departureTime = " + departureTime;

      ResultSet srs = stmt.executeQuery(sql);
     
        boolean fullTime, graduate;
      String departureDate, arrivalDate, arrivalTime, origin, destination, airlineCode;
      double price;
      
      if (srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getString("departureDate");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          airlineCode = srs.getString("airlineCode");
          
	} else {// we verwachten slechts 1 rij...
	DBConnector.closeConnection(con);
	return null;
      }
      Flight vlucht = new Flight(flightNumber, departureDate, departureTime, arrivalDate, arrivalTime, price, origin, destination, airlineCode);
      DBConnector.closeConnection(con);
      return vlucht;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
    }
    
     

    public static ArrayList<Flight> getFlights() throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate "
              + "FROM db2019_18.flight";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Flight> vluchten = new ArrayList<>();
      while (srs.next())
        vluchten.add(getFlight(srs.getString("flightNumber"), srs.getString("departureDate")));
      DBConnector.closeConnection(con);
      return vluchten;
    } catch (DBException dbe) {
      dbe.printStackTrace();
      DBConnector.closeConnection(con);
      throw dbe;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
  }

    
}
