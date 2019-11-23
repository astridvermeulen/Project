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
import project.LOGIC.Customer;

/**
 *
 * @author klaas
 */
public class DBCustomer {  
    public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.customer("
  + "firstName VARCHAR(45) NULL, "
  + "lastName` VARCHAR(45) NULL,"
  + "passportNumber` VARCHAR(45) NOT NULL,"
  + "homeCountry` VARCHAR(45) NULL,"
  + "PRIMARY KEY (`passportNumber`)" + ")";
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
      // retourneert 1 airport
     public static Customer getCustomer(String passportNumber) throws DBException{
         Connection con = null;
    try {
      con = DBConnector.getConnection();
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
	DBConnector.closeConnection(con);
	return null;
      }
      
      Customer klant = new Customer(passportNumber, firstName, lastName, homeCountry);
              DBConnector.closeConnection(con);
      return klant;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
         
     }
    }

