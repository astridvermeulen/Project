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
import project.LOGIC.Traject;

/**
 *
 * @author klaas
 */
public class DBTraject {
     //toont 10 verschillende meest geboekte trips en hoe vaak deze geboekt zijn
    public static ArrayList<Traject> getTopPopularTrips(String jaar) throws DBException{
         Connection con = null;
         ArrayList<Traject> traject = new ArrayList<>();               
        
         
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql =  "SELECT distinct f.origin, f.destination, count(*) AS aantal FROM booking as b " + 
                    "INNER JOIN flight AS f " +
                    "WHERE f.departureDate = b.departureDate " +
                    "AND " + 
                    "f.flightNumber = b.flightNumber " + 
                    "AND f.departureDate LIKE '%" + jaar + "'" + 
                    "GROUP BY f.origin, f.destination " + 
                    "ORDER BY aantal DESC";

      ResultSet srs = stmt.executeQuery(sql);
      String origin, destination;
      int aantal;

      while (srs.next()) {
          origin = srs.getString("origin");
          destination = srs.getString("destination");
          aantal = srs.getInt("aantal");
          int i = 0;
          Traject test = new Traject(origin,destination,aantal);
          traject.add(i, test);
          i++;  
         
	}
      DBConnection.closeConnection(con);
      reverse(traject);
      return traject;
        
         
     }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    }



    
}
