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
import java.util.Date;
import project.LOGIC.Booking;
import project.LOGIC.Customer;
/**
 *
 * @author Lenovo
 */
public class DBBooking {
    public static void createTables() throws DBException {
    try {
   
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.booking("              
    + "bookingNumber INT NOT NULL," 
    + "promotion DOUBLE NULL," 
    + "serviceFee DOUBLE NULL," 
    +"PRIMARY KEY (bookingNumber)," 
    +"FOREIGN KEY (flightNumber, departureDate) "
    +"REFERENCES db2019_18.flight (flightNumber, departureDate) " 
    +"ON DELETE CASCADE " 
    +"ON UPDATE CASCADE" + ")";
      
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
}

 public static Booking getBooking(int bookingNumber) throws DBException{
         Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
        //+ ", promotion, serviceFee, flightNumber, departureDate "
        + "FROM db2019_18.booking "
	+ "WHERE bookingNumber = " + bookingNumber;

      ResultSet srs = stmt.executeQuery(sql);

      double serviceFee, promotion;
      String flightNumber;
      Date departureDate;
      
      if (srs.next()) {
          bookingNumber = srs.getInt("bookingNumber");
          //serviceFee = srs.getDouble("serviceFee");
          //promotion = srs.getDouble("promotion");
          //flightNumber = srs.getString("flightNumber");
          //departureDate = srs.getDate("departureDate");
	} 
      else {// we verwachten slechts 1 rij...
	DBConnector.closeConnection(con);
	return null;
      }
      
      Booking boeking = new Booking(bookingNumber);
              DBConnector.closeConnection(con);
      return boeking;
      
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
    
         
     }
 public static ArrayList<Booking> getBookings() throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
              + "FROM db2019_18.booking";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Booking> boekingen = new ArrayList<>();
      while (srs.next())
        boekingen.add(getBooking(srs.getInt("bookingNumber")));
      DBConnector.closeConnection(con);
      return boekingen;
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
      public static void save(Booking s) throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
              + "FROM booking "
              + "WHERE bookingNumber = " + s.getBookingNumber();
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE booking "
                + "SET bookingNummer = " + s.getBookingNumber()
		+ ", promotion = " + s.getPromotion()
		+ ", serviceFee = " + s.getServiceFee()
		// + ", flightNumber = '" + s.getFlightNumber() + "'"
               // + ", departureDate = " + s.getdepartureDate()
                + " WHERE bookingNumber = '" + s.getBookingNumber() + "'";
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into booking "
                + "(bookingNumber, promotion, serviceFee) "
		+ "VALUES (" + s.getBookingNumber() 
                + ", " + s.getPromotion() 
		+ ", " + s.getServiceFee()
             // + ", '" + s.getFlightNumber() + "'"
             // + ", " + s.getdepartureDate()                
                + ")";
        stmt.executeUpdate(sql);
      }
      DBConnector.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
  }
}
