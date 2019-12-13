
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.LOGIC.Airport;


/**
 *
 * @author TEAM DB
 */

public class DBAirport {
    // retourneert 1 airport
     public static Airport getAirport(String airportCode) throws DBException{
         Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airportCode, airportName "
        + "FROM db2019_18.airport "
	+ "WHERE airportCode = '" + airportCode + "'";

      ResultSet srs = stmt.executeQuery(sql);

      String airportName;
      
      if (srs.next()) {
          airportCode = srs.getString("airportCode");
          airportName = srs.getString("airportName");
          
	} else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      Airport luchthaven = new Airport(airportCode, airportName);
              DBConnection.closeConnection(con);
      return luchthaven;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
         
     }
     
     //retourneert alle airports
     public static ArrayList<Airport> getAirports() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airportCode "
              + "FROM db2019_18.airport";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Airport> luchthavens = new ArrayList<Airport>();
      while (srs.next())
        luchthavens.add(getAirport(srs.getString("airportCode")));
      DBConnection.closeConnection(con);
      return luchthavens;
    } catch (DBException dbe) {
      dbe.printStackTrace();
      DBConnection.closeConnection(con);
      throw dbe;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }  
   
}
