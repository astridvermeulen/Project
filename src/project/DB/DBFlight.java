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
public class DBFlight {
     public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.flight("              
    + "flightNumber VARCHAR(45) NOT NULL," 
    +"departureDate DATE NOT NULL," 
    +"departureTime TIME NULL," 
    +"arrivalDate DATE NULL," 
    +"arrivalTime TIME NULL," 
    +"price DOUBLE NULL," 
    +"origin VARCHAR(45) NULL," 
    +"destination VARCHAR(45) NULL," 
    +"airlineCode VARCHAR(45) NOT NULL,"          
    +"PRIMARY KEY (flightNumber, departureDate, airlineCode)" 
    +"FOREIGN KEY (airlineCode)"
    +"REFERENCES db2019_18.airline (airlineCode)" 
    +"ON DELETE CASCADE" 
    +"ON UPDATE CASCADE" + ")";
      
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
}    
}
