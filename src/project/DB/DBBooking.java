package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static project.DB.DBBookingNumberGenerator.getBooking;
import project.LOGIC.Booking;
import project.LOGIC.BookingNumberGenerator;
/**
 *
 * @author TEAM DB
 */

public class DBBooking {
 
 
    //booking opslaan in de database en deze koppelen aan een customer
    public static void saveBooking(String bookingDate, double promotion, double serviceFee, String flightNumber, String departureDate, String passportNumber) throws DBException, SQLException {
    Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
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
      con = DBConnection.getInstance().getConnection();
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
