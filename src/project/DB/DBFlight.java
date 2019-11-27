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
    
     // retourneert 1 vlucht
     private static Flight getFlight(String flightNumber, int departureDate) throws DBException {
        Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate, departureTime, arrivalDate, arrivalTime, price, origin, destination, airlineCode "
	+ "FROM db2019_18.flight "
	+ "WHERE flightNumber = '" + flightNumber + "'"
        + " AND departureDate = " + departureDate;

      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
      String origin, destination, airlineCode;
      double price;
      int arrivalDate, arrivalTime, departureTime;
       
      
      if (srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getInt("departureDate");
          departureTime = srs.getInt("departureTime");
          arrivalDate = srs.getInt("arrivalDate");
          arrivalTime = srs.getInt("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          airlineCode = srs.getString("airlineCode");
                    
	} else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      //aantal flightlegs moet er ook nog bij? 
     // Flight vlucht = new Flight(destination, origin, flightNumber, price, departureDate, arrivalDate, departureTime, arrivalTime);
      DBConnection.closeConnection(con);
     // return vlucht;
      return null; 
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
    
private static Flight getFlightForBooking(int s) throws DBException {
        Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate "
	+ "FROM db2019_18.booking "
	+ "WHERE bookingNumber = " + s;
        
      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
      String flightNumber;
      int departureDate;
      
      int arrivalDate, arrivalTime, departureTime;
       
      
      if (srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getInt("departureDate");
                    
	} else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      //aantal flightlegs moet er ook nog bij? 
     // Flight vlucht = new Flight(destination, origin, flightNumber, price, departureDate, arrivalDate, departureTime, arrivalTime);
      DBConnection.closeConnection(con);
     // return vlucht;
      return null; 
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
     
     // retourneert een arraylist van alle vluchten
    public static ArrayList<Flight> getFlights() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate "
              + "FROM db2019_18.flight";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Flight> vluchten = new ArrayList<>();
      while (srs.next())
        vluchten.add(getFlight(srs.getString("flightNumber"), srs.getInt("departureDate")));
      DBConnection.closeConnection(con);
      return vluchten;
    } catch (DBException dbe) {
      dbe.printStackTrace();
      DBConnection.closeConnection(con);
      throw dbe;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
    public static double getEmission(String flightNumber, int departureDate) throws DBException{
         Connection con = null;
         double CO2 = 0.0;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT CO2 "
        + "FROM db2019_18.flight "
	+ "WHERE flightNumber = '" + flightNumber + "'"
        + " AND departureDate = " + departureDate;

      ResultSet srs = stmt.executeQuery(sql);

      if (srs.next()) {
           CO2 = srs.getDouble("C02");
	}
      
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return CO2;
      }
      DBConnection.closeConnection(con);
      return CO2;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    
    }

    
}
