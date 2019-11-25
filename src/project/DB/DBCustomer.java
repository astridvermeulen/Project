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
import project.LOGIC.Customer;

/**
 *
 * @author klaas
 */
public class DBCustomer {  
   
      // retourneert 1 airport
     public static Customer getCustomer(String passportNumber) throws DBException{
         Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT firstName, lastName, PassportNumber, homeCounrty "
        + "FROM db2019_18.customer "
	+ "WHERE passportNumber = " + passportNumber;

      ResultSet srs = stmt.executeQuery(sql);

      String firstName, lastName, homeCountry;
      
      if (srs.next()) {
          passportNumber = srs.getString("passportNumber");
          firstName = srs.getString("firstName");
          lastName = srs.getString("lastName");
          homeCountry = srs.getString("homeCountry");
	} 
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      Customer klant = new Customer(passportNumber, firstName, lastName, homeCountry);
              DBConnection.closeConnection(con);
      return klant;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
         
     }
     public static ArrayList<Customer> getCustomers() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM db2019_18.customer";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Customer> klanten = new ArrayList<>();
      while (srs.next())
        klanten.add(getCustomer(srs.getString("bookingNumber")));
      DBConnection.closeConnection(con);
      return klanten;
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
     public static void save(Customer s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM customer "
              + "WHERE passportNumber = '" + s.getPassportNumber() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE customer "
                + "SET firstname = '" + s.getFirstName() + "'"
		+ ", lastname = '" + s.getLastName() + "'"
		+ ", PassportNumber = '" + s.getPassportNumber() + "'"
		+ ", homeCountry = '" + s.getHomeCountry() + "'"
                + " WHERE passportNumber = '" + s.getPassportNumber() + "'";
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into customer "
                + "(firstName, lastName, passportNumber, homeCountry) "
		+ "VALUES ('" + s.getFirstName() + "'"
		+ ", '" + s.getLastName() + "'"
		+ ", '" + s.getPassportNumber() + "'"
                + ", '" + s.getHomeCountry() + "'"
		+ ")";
        stmt.executeUpdate(sql);
      }
      DBConnection.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
    }

