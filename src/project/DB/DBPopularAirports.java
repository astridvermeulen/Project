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
import static java.util.Collections.reverse;
import project.LOGIC.PopularAirports;

/**
 *
 * @author klaas
 */
public class DBPopularAirports {
     // toont op welke airport er allemaal vertrokken/geland is en hoe vaak, top10 
   public static ArrayList<PopularAirports> getPopularAirport() throws DBException{
         Connection con = null;
         ArrayList<PopularAirports> haven = new ArrayList<>();               
        
         
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT distinct f.origin AS airport, count(*) AS aantal FROM booking as b " 
                    + "INNER JOIN flight AS f "
                    + "WHERE f.departureDate = b.departureDate AND "
                    + "f.flightNumber = b.flightNumber "
                    + "GROUP BY f.origin "
                    + "UNION "
                    + "SELECT distinct f.destination AS airport, count(*) AS aantal FROM booking as b "
                    + "INNER JOIN flight AS f "
                    + "WHERE f.departureDate = b.departureDate AND "
                    + "f.flightNumber = b.flightNumber "
                    + "GROUP BY f.destination "
                    + "ORDER BY aantal DESC "
                    + "LIMIT 10";
      
      ResultSet srs = stmt.executeQuery(sql);
      String airportName;
      int aantal;

      while (srs.next()) {
          airportName = srs.getString("airport");
          aantal = srs.getInt("aantal");
          int i = 0;
          PopularAirports test = new PopularAirports(airportName, aantal);
          haven.add(i, test);
          i++;  
	}
      
      DBConnection.closeConnection(con);
      reverse(haven);
      return haven;
     }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
            }

}
