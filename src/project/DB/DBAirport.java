/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.LOGIC.Airport;

/**
 *
 * @author Lenovo
 */
public class DBAirport {
     
     // retourneert 1 airport
     public static Airport getAirport(String airportCode) throws DBException{
         Connection con = null;
    try {
      con = DBConnection.getConnection();
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
      con = DBConnection.getConnection();
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
     
   
   
    //nieuwe airport opslaan in de database, of bestaande aanpassen
    public static void saveAirport(Airport s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airportCode "
              + "FROM airport "
              + "WHERE airportCode = '" + s.getAirportCode() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE airport "
                + "SET airportCode = '" + s.getAirportCode() + "'"
		+ ", airportName = '" + s.getAirportName() + "'"
                + " WHERE airportCode = '" + s.getAirportCode() + "'";
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into airport "
                + "(airportCode, airportName) "
		+ "VALUES ('" + s.getAirportCode() + "'"
		+ ", '" + s.getAirportName() + "')";
        
        stmt.executeUpdate(sql);
      }
      DBConnection.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
   
     //airport verwijderen uit de database
   public static void deleteAirport(Airport s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT airportCode "
              + "FROM airport "
              + "WHERE airportCode = '" + s.getAirportCode() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // DELETE
	sql = "DELETE FROM airport "
                + "WHERE airportCode = '" + s.getAirportCode() + "'";
		
        stmt.executeUpdate(sql);
        DBConnection.closeConnection(con);
      } else{
          //DOORGEGEVEN AIRLINE ZAT NIET IN DATABASE
        DBConnection.closeConnection(con);	
      }
      
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
   
   // toont op welke airport er allemaal vertrokken/geland is en hoe vaak 
   public static void getPopularAirport() throws DBException{
       //public static ArrayList<Airport> getPopularAirport(){
         Connection con = null;
         //ArrayList<Airport> haven = new ArrayList<>();               
        
         
    try {
      con = DBConnection.getConnection();
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
                    + "ORDER BY aantal DESC ";
      
      ResultSet srs = stmt.executeQuery(sql);
      String airport;
      int aantal;

      while (srs.next()) {
          airport = srs.getString("airport");
          aantal = srs.getInt("aantal");
          System.out.println(airport + " : " + aantal);
        //int i = 0;
        // Airport test = new Airport(airport, aantal);
         //haven.add(i, test);
         //i++;  
         // return test;
         
          
        
	}
      DBConnection.closeConnection(con);
         
     }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
    //return null;
            }

   
  //test
  public static void main(String[] args) throws DBException {
      
    getPopularAirport();
  
   
}
}
