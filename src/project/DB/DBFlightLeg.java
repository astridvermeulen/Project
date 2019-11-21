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
public class DBFlightLeg {
    public static void createTables() throws DBException {
    try {
   
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE db2019_18.flightLeg("              
    + "legNumber VARCHAR(45) NOT NULL,"  
    +"PRIMARY KEY (legNumber)" 
    +"FOREIGN KEY (flightNumber, departureDate)"
    +"REFERENCES db2019_18.flight (flightNumber, departureDate)" 
    +"ON DELETE CASCADE"
    +"ON UPDATE CASCADE"          
    +"FOREIGN KEY (airportCode)"
    +"REFERENCES db2019_18.airport (airportCode)"          
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
