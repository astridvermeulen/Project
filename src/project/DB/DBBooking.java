package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import project.LOGIC.Booking;
/**
 *
 * @author TEAM DB
 */

public class DBBooking {
  
//retourneert een boeking 
 public static Booking getBooking(int bookingNumber) throws DBException{
         Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT * "
        + "FROM booking "
	+ "WHERE bookingNumber = " + bookingNumber;

      ResultSet srs = stmt.executeQuery(sql);

      double serviceFee;
      String bookingDate;
      
      if (srs.next()) {
          bookingDate = srs.getString("bookingDate");
          serviceFee = srs.getDouble("serviceFee");
          
	} 
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      Booking boeking = new Booking(bookingDate, serviceFee);
              DBConnection.closeConnection(con);
      return boeking;
      
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    
 }
 
 //retourneert alle bookings 
 public static ArrayList<Booking> getBookings() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
              + "FROM db2019_18.booking";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Booking> boekingen = new ArrayList<>();
      while (srs.next())
        boekingen.add(getBooking(srs.getInt("bookingNumber")));
      DBConnection.closeConnection(con);
      return boekingen;
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
 
    //booking opslaan in de database en deze koppelen aan een customer
    public static void saveBooking(String bookingDate, double promotion, double serviceFee, String flightNumber, String departureDate, String passportNumber) throws DBException, SQLException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "INSERT into booking "
		+ "VALUES (null"
                + ", '" + bookingDate + "'"
                + ", " + promotion
                + ", " + serviceFee
                + ", '" + flightNumber + "'"
                + ", '" + departureDate + "'"            
                + ")";
      stmt.executeUpdate(sql);      
      
      String sql2 = "INSERT INTO execution "
                  + "SELECT max(bookingNumber), '" + passportNumber + "'"
                  + "FROM booking";
      stmt.executeUpdate(sql2);      
                    
      DBConnection.closeConnection(con);
      }
      
      catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
    //booking verwijderen uit de database
      public static void deleteBooking(int s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
              + "FROM booking "
              + "WHERE bookingNumber = " + s;
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // DELETE
	sql = "DELETE FROM booking "
                + "WHERE bookingNummer = " + s;
        stmt.executeUpdate(sql);
        DBConnection.closeConnection(con);
    
      } else {
	// DOORGEGEVEN BOOKING ZAT NIET IN DATABASE 
	DBConnection.closeConnection(con);
    
      }
      } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
      
      
 public static void main(String[] args) throws DBException, SQLException{
     String bookingDate, flightNumber, departureDate, passportNumber;
     double promotion, serviceFee;
     bookingDate = "11/02/2021";
     departureDate = "12/02/2021";
     passportNumber = "BE1207";
     flightNumber = "TA0645";
     promotion = 40.0;
     serviceFee = 20.0;
     saveBooking(bookingDate, promotion, serviceFee, flightNumber, departureDate, passportNumber);
     
     
   
 }    
      
}
