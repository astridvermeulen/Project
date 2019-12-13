
package project.DB;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author TEAM DB
 */

public class DBAirline {
    
    public static String getAirlineForFlight(String flightNumber, String departureDate) throws DBException{
    Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
            String sql =    "SELECT airlineName FROM airline " +
                            "WHERE airlineCode = " +
                            "(SELECT airlineCode FROM flight WHERE departureDate = '" + departureDate + "'" + 
                            " AND flightNumber = '" + flightNumber + "')";
            
            ResultSet srs = stmt.executeQuery(sql);
            String airlineName;
      
                if (srs.next()) {
                    airlineName = srs.getString("airlineName");
                     DBConnection.closeConnection(con);
                     return airlineName;
                    } 
                
                 else {// we verwachten slechts 1 rij...
                    DBConnection.closeConnection(con);
                    return null;
                }
                
           }       
    
        catch (Exception ex) {
            ex.printStackTrace();
            DBConnection.closeConnection(con);
            throw new DBException(ex);
        }
         
    }   
}
