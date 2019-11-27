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
import static project.DB.DBAirport.getAirport;
import project.LOGIC.Airport;
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
      
      String sql = "SELECT firstName, lastName, PassportNumber, homeCountry "
        + "FROM db2019_18.customer "
	+ "WHERE passportNumber = '" + passportNumber + "'";

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
        klanten.add(getCustomer(srs.getString("passportNumber")));
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
     public static void saveCustomer(Customer s) throws DBException {
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
     public static void deleteCustomer(String s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM customer "
              + "WHERE passportNumber = '" + s + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // DELETE
	sql = "DELETE FROM customer "
                + "WHERE PassportNumber = '" + s + "'";
        stmt.executeUpdate(sql);
        DBConnection.closeConnection(con);
    
      } else {
	// DOORGEGEVEN CUSTOMER ZAT NIET IN DATABASE
        DBConnection.closeConnection(con);
    
	      }
      } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
     
    
    public static void main(String[] args) throws DBException {
      
    ArrayList<Customer> test = new ArrayList<>();
    
      try {
          test = getCustomers();
          int size = test.size();
          for(int position = 0; position < size; position++)
              System.out.println(test.get(position).getFirstName() + " " + test.get(position).getLastName() );
    
;
    } catch (DBException ex) {
      Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
    }
  
  }
  
}
   

  

