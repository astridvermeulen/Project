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
import java.util.logging.Level;
import java.util.logging.Logger;
import project.LOGIC.Airline;

/**
 *
 * @author klaas
 */
public class DBAirline {
    
    
   public static Airline getAirline(int airlineCode) throws DBException{
    Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
            String sql = "SELECT airlineCode, airlineName "
            + "FROM db2019_18.airline "
            + "WHERE airlineCode = " + airlineCode;

            ResultSet srs = stmt.executeQuery(sql);
            String airlineName;
      
                if (srs.next()) {
                    airlineName = srs.getString("airlineName");
                    airlineCode = srs.getInt("airlineCode");
                } 
                
                 else {// we verwachten slechts 1 rij...
                    DBConnection.closeConnection(con);
                    return null;
                }
                
            Airline airline = new Airline(airlineCode, airlineName);
            DBConnection.closeConnection(con);
            return airline;
        }       
    
        catch (Exception ex) {
            ex.printStackTrace();
            DBConnection.closeConnection(con);
            throw new DBException(ex);
        }
         
    }
   
  public static ArrayList<Airline> getAirlines() throws DBException {
    Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
            String sql = "SELECT AirlineCode "
            + "FROM db2019_18.airline";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Airline> vliegmaatschappij = new ArrayList<>();
            
                 while (srs.next())
                    vliegmaatschappij.add(getAirline(srs.getInt("airlineCode")));
                 
            DBConnection.closeConnection(con);
            return vliegmaatschappij;
        } 
        
        catch (DBException dbe) {
            dbe.printStackTrace();
            DBConnection.closeConnection(con);
            throw dbe;
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
            DBConnection.closeConnection(con);
            throw new DBException(ex);
        }
    }
  
   public static void saveAirline(Airline s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airlineCode "
              + "FROM airline "
              + "WHERE airlineCode = '" + s.getAirlineCode() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE airline "
                + "SET airlineCode = '" + s.getAirlineCode() + "'"
		+ ", airlineName = '" + s.getAirlineName() + "'"
                + " WHERE airlineCode = '" + s.getAirlineCode() + "'";
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into Airline "
                + "(airlineCode, airlineName) "
		+ "VALUES ('" + s.getAirlineCode() + "'" 
		+ ", '" + s.getAirlineName() + "')";
        stmt.executeUpdate(sql);
      }
      DBConnection.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
   public static void deleteAirline(Airline s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airlineCode "
              + "FROM airline "
              + "WHERE airlineCode = '" + s.getAirlineCode() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // DELETE
	sql = "DELETE FROM airline "
                + "WHERE airlineCode = '" + s.getAirlineCode() + "'";
		stmt.executeUpdate(sql);
                DBConnection.closeConnection(con);
      } else {
	// DOORGEGEVEN AIRLINE ZAT NIET IN DATABASE
        DBConnection.closeConnection(con);
	}
      
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
   
public static String getAirlineForFlight(String flightNumber, String departureDate) throws DBException{
    Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
            String sql =    "SELECT airlineName FROM airline " +
                            "WHERE airlineCode = " +
                            "(SELECT airlineCode FROM flight WHERE departureDate = '" + departureDate + "'" + 
                            " AND flightNumber = '" + flightNumber + "'";
            
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
   
   public static void main(String[] args) throws DBException {
      
    ArrayList<Airline> test = new ArrayList<>();
    
      try {
          test = getAirlines();
          int size = test.size();
          for(int position = 0; position < size; position++)
              System.out.println(test.get(position).getAirlineName());
    
;
    } catch (DBException ex) {
      Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
    }
  
  }
  
   

    
}
