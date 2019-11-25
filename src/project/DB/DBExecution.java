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
 * @author Lenovo
 */
public class DBExecution {
    public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnection.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE execution ("
    + "passportNumber VARCHAR(45) NOT NULL, "
    + "bookingNumber INT NOT NULL, "
    + "PRIMARY KEY (passportNumber, bookingNumber), " 
    +"FOREIGN KEY (passportNumber) "
    +"REFERENCES db2019_18.flight (customer) " 
    +"ON DELETE CASCADE "
    +"ON UPDATE CASCADE,"       
    +"FOREIGN KEY (bookingNumber) "
    +"REFERENCES db2019_18.flight (booking) " 
    +"ON DELETE CASCADE "
    +"ON UPDATE CASCADE"  + ")";
        stmt.executeUpdate(sql);
      DBConnection.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}
