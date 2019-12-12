
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.reverse;
import project.LOGIC.Flight;

/**
 *
 * @author TEAM DB
 */
public class DBFlight {
    
     // retourneert 1 vlucht
     public static Flight getFlight(String flightNumber, String departureDate) throws DBException, SQLException {
        Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql =  "SELECT origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightNumber, price " + 
                    "FROM flight " + 
                    "WHERE flightNumber = '" + flightNumber + "' " + 
                    "AND departureDate = '" + departureDate + "'";
                    

      ResultSet srs = stmt.executeQuery(sql);
     
      String origin, destination, arrivalDate, arrivalTime, departureTime;
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
          
          Flight vlucht = new Flight(origin,destination,departureDate,departureTime,arrivalDate,arrivalTime,flightNumber, price);
          DBConnection.closeConnection(con);
          return vlucht;
     
      }     
    else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
       
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }
     
//PER CUSTOMER ALLE GEBOEKTE VLUCHTEN RETOURNEREN   
public static ArrayList <Flight> getFlightsPerCustomer(String passportNumber) throws DBException {  
        Connection con = null;
        ArrayList<Flight> vlucht = new ArrayList<>();               
                          
      try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql =  "SELECT f.flightNumber, f.departureDate,f.departureTime, f.arrivalDate, f.arrivalTime, " +
                    "f.price, f.origin, f.destination, f.airlineCode " + 
                    "FROM flight AS f " + 
                    "INNER JOIN booking AS b " +
                    "ON (b.flightNumber = f.flightNumber AND b.departureDate = f.departureDate) AND b.bookingNumber IN ( " +   
                    "SELECT bookingNumber FROM execution WHERE passportnumber = '" + passportNumber + "')";
              
      ResultSet srs = stmt.executeQuery(sql);
     
      String flightNumber, origin, destination, departureDate, arrivalDate, arrivalTime, departureTime;
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
          
    
         int i = 0;
         Flight test = new Flight(origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightNumber, price);
         vlucht.add(i, test);
         i++;              
         
	}
      
      DBConnection.closeConnection(con);
      return vlucht;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
     
         
    }

//Vlucht retourneren dat bij een booking hoort
public static Flight getFlightForBooking(int bookingNumber) throws DBException {
       Connection con = null;                   
                          
      try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT f.flightNumber, f.departureDate, f.departureTime, f.arrivalDate, f.arrivalTime, " + 
                    "f.price, f.origin, f.destination FROM flight AS f " + 
                   "INNER JOIN booking AS b " +
                   "WHERE b.flightNumber = f.flightNumber AND b.departureDate = f.departureDate " +
                   "AND b.bookingNumber = " + bookingNumber;
                   
      ResultSet srs = stmt.executeQuery(sql);
     
      //werken let LocalDate en LocalTime? zie slide 20 tips project database!!
    String flightNumber, origin, destination,departureDate, arrivalDate, arrivalTime, departureTime;
    double price;
      
      if(srs.next()) {
          flightNumber = srs.getString("flightNumber");
          departureDate = srs.getString("departureDate");
          departureTime = srs.getString("departureTime");
          arrivalDate = srs.getString("arrivalDate");
          arrivalTime = srs.getString("arrivalTime");
          price = srs.getDouble("price");
          origin = srs.getString("origin");
          destination = srs.getString("destination");
                   
	}
       else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
     
      Flight vlucht = new Flight(origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightNumber, price);
      DBConnection.closeConnection(con);      
      return vlucht;
      
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
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT flightNumber, departureDate "
              + "FROM db2019_18.flight";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Flight> vluchten = new ArrayList<>();
      while (srs.next())
        vluchten.add(getFlight(srs.getString("flightNumber"), srs.getString("departureDate")));
      DBConnection.closeConnection(con);
      reverse(vluchten);
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
//geeft de CO2 uitstoot van een bepaalde vlucht 
    public static double getEmission(String flightNumber, String departureDate) throws DBException{
         Connection con = null;
         Double co2 = 0.0;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT co2 "
        + "FROM flight "
	+ "WHERE flightNumber = '" + flightNumber + "'"
        + " AND departureDate = '" + departureDate + "'";

      ResultSet srs = stmt.executeQuery(sql);

      if (srs.next()) {
           co2 = srs.getDouble("co2");
           DBConnection.closeConnection(con);
           return co2;
	}
      
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return co2;
      }

    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    
    }
    
   
        public static void main (String[] args) throws DBException {
            System.out.println(DBFlight.getFlights());
}
}
        
        
    
    


    

