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
import project.LOGIC.BookingNumberGenerator;

/**
 *
 * @author klaas
 */
public class DBBookingNumberGenerator {
    
    //retourneert een boeking 
 public static BookingNumberGenerator getBooking(int bookingNumber) throws DBException{
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
      
      BookingNumberGenerator boeking = new BookingNumberGenerator(bookingDate, serviceFee, bookingNumber);
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
 public static ArrayList<BookingNumberGenerator> getBookings() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
              + "FROM db2019_18.booking";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<BookingNumberGenerator> boekingen = new ArrayList<>();
      
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
     
}
