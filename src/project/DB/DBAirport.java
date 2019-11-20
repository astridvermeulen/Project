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
public class DBAirport {
     public static void createTables() throws DBException {
    try {
    
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE airport ("
  + "airportCode VARCHAR(45) NOT NULL, "
  + "airportName VARCHAR(45) NULL, "
  + "PRIMARY KEY (airportCode)" + ")";
        stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}
