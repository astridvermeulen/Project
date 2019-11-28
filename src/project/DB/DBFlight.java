/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.LOGIC.Flight;
import project.LOGIC.FlightLeg;

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
      ArrayList<FlightLeg> legs = new ArrayList<>();
      legs = null;
      
      
      //aantal flightlegs moet er ook nog bij? 
     Flight vlucht = new Flight(legs, destination, origin, flightNumber, price, departureDate, arrivalDate, departureTime, arrivalTime);
      DBConnection.closeConnection(con);
      return vlucht;
       
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
    
private static ArrayList <Flight> getFlightsPerCustomer(String passportNumber) throws DBException { //PER CUSTOMER ALLE GEBOEKTE VLUCHTEN RETOURNERE?
        Connection con = null;
        ArrayList<Flight> vlucht = new ArrayList<>();               
                          
      try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT * " + 
        "FROM flight AS f " + 
        "INNER JOIN booking AS b " +
        "ON (b.flightNumber = f.flightNumber AND b.departureDate = f.departureDate) AND b.bookingNumber IN ( " +   
              "SELECT bookingNumber FROM execution WHERE passportnumber = '" + passportNumber + "')";
       
      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
      String flightNumber, origin, destination, airlineCode;
      int departureDate;
      double price;
      
      int arrivalDate, arrivalTime, departureTime;
         
      
      while (srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getInt("departureDate");
          departureTime = srs.getInt("departureTime");
          arrivalDate = srs.getInt("arrivalDate");
          arrivalTime = srs.getInt("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          airlineCode = srs.getString("airlineCode");
          ArrayList<FlightLeg> legs = new ArrayList<>();
          legs = null;
          
    
         int i = 0;
         Flight test = new Flight(legs, destination, origin, flightNumber, price, departureDate, arrivalDate, departureTime, arrivalTime);
         vlucht.add(i, test);
         i++;              
         DBConnection.closeConnection(con);      
         
	}
      
    }
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    return vlucht; 
         
    }
     
    
public static ArrayList<Flight> getFlights() throws DBException {  // retourneert een arraylist van alle vluchten
   
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
    
    public static void main(String[] args) throws DBException{
        String x = "BE1207";
        ArrayList<Flight> test = new ArrayList<>();
        
        try {
            test = getFlightsPerCustomer(x);
            int size = test.size();
          for(int position = 0; position < size; position++)
              System.out.println(test.get(position).getFlightNumber());
    
;
    } catch (DBException ex) {
      Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}

    

