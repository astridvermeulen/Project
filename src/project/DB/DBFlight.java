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
     public static Flight getFlight(String flightNumber, String departureDate) throws DBException, SQLException {
        Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql =  "SELECT f.flightNumber, f.departureDate, f.departureTime, f.arrivalDate, f.arrivalTime, " + 
                    "f.price, f.origin, f.destination, a.airlineName " + 
                    "FROM db2019_18.flight AS f INNER JOIN db2019_18.airline AS a " + 
                    "WHERE flightNumber = '" + flightNumber + "'" + 
                    " AND departureDate = '" + departureDate + "'" + 
                    " AND f.airlineCode = a.airlineCode" ;


      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
      String origin, destination, airlineCode, arrivalDate, arrivalTime, departureTime, airlineName;
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
          airlineName = srs.getString("airlineName");
          
      }     
    else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      
      //aantal flightlegs moet er ook nog bij? 
     Flight vlucht = new Flight(airlineName, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightNumber, price);
      DBConnection.closeConnection(con);
      return vlucht;
       
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
     
    
public static ArrayList <Flight> getFlightsPerCustomer(String passportNumber) throws DBException { //PER CUSTOMER ALLE GEBOEKTE VLUCHTEN RETOURNERE?
        Connection con = null;
        ArrayList<Flight> vlucht = new ArrayList<>();               
                          
      try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT f.flightNumber, f.departureDate, f.departureTime, f.arrivalDate, f.arrivalTime, " + 
                    "f.price, f.origin, f.destination, a.airlineName " + 
        "FROM flight AS f " + 
        "INNER JOIN booking AS b INNER JOIN airline as a " +
        "ON (b.flightNumber = f.flightNumber AND b.departureDate = f.departureDate) AND b.bookingNumber IN ( " +   
              "SELECT bookingNumber FROM execution WHERE passportnumber = '" + passportNumber + "')" + 
              " AND a.airlineCode = f.airlineCode";
       
      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
      String flightNumber, origin, destination, airlineName,departureDate, arrivalDate, arrivalTime, departureTime;
      
      double price;
      

         
      
      while (srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getString("departureDate");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          airlineName = srs.getString("airlineName");
          
    
         int i = 0;
         Flight test = new Flight(airlineName, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightNumber, price);
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
public static Flight getFlightsForBooking(int bookingNumber) throws DBException { //RETURNS A FLIGHT GIVEN A BOOKINGNUMBER
       Connection con = null;
                      
                          
      try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT f.flightNumber, f.departureDate, f.departureTime, f.arrivalDate, f.arrivalTime, " + 
                    "f.price, f.origin, f.destination, a.airlineName FROM flight AS f " + 
                   "INNER JOIN booking AS b INNER JOIN airline as a " +
                   "WHERE b.flightNumber = f.flightNumber AND b.departureDate = f.departureDate " +
                   "AND b.bookingNumber = " + bookingNumber + 
                   " AND a.airlineCode = f.airlineCode";
      
      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
    String flightNumber, origin, destination, airlineName,departureDate, arrivalDate, arrivalTime, departureTime;
    double price;
      ArrayList<FlightLeg> legs = new ArrayList<>();
          
      
      if(srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getString("departureDate");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          airlineName = srs.getString("airlineName");
          legs = null;
          
	}
       else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
     
      //Flight vlucht = new Flight(legs, destination, origin, flightNumber, price, departureDate, arrivalDate, departureTime, arrivalTime);
      DBConnection.closeConnection(con);      
      //return vlucht;
      return null;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
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
        vluchten.add(getFlight(srs.getString("flightNumber"), srs.getString("departureDate")));
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
    public static double getEmission(String flightNumber, String departureDate) throws DBException{
         Connection con = null;
         double co2 = 0.0;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT co2 "
        + "FROM db2019_18.flight "
	+ "WHERE flightNumber = '" + flightNumber + "'"
        + " AND departureDate = " + departureDate;

      ResultSet srs = stmt.executeQuery(sql);

      if (srs.next()) {
           co2 = srs.getDouble("co2");
	}
      
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return co2;
      }
      DBConnection.closeConnection(con);
      return co2;
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

    

