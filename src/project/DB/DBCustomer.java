/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
    }

