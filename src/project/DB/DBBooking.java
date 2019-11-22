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
}
