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
public class DBEmission {
    public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE emission ("
    + "CO2 DOUBLE NOT NULL, "
    + "PRIMARY KEY (departureTime, arrivalTime), " 
    + "FOREIGN KEY (departureTime, arrivalTime) "
    + "REFERENCES db2019_18.flight (flight) " 
    + "ON DELETE CASCADE "
    + "ON UPDATE CASCADE" + ")";
        stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}