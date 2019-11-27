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
import project.LOGIC.Booking;

/**
 *
 * @author Lenovo
 */
public class DBExecution {
    
    public static ArrayList<Booking> getBookingsPerCustomer(String passportNumber) throws DBException{
         Connection con = null;
          ArrayList<Booking> boek = new ArrayList<>();
     
    try {
      con = DBConnection.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT bookingNumber "
        + "FROM db2019_18.execution "
	+ "WHERE passportNumber = '" + passportNumber + "'";

      ResultSet srs = stmt.executeQuery(sql);

      int index = 0;
      while (srs.next()) {          
          int nummer = srs.getInt("bookingNumber");   
          Booking test =  new Booking(nummer);
          boek.add(index, test);
          index++;
      } 
      
      DBConnection.closeConnection(con);
      return boek;
       
    }
      /*else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      Booking boek = new Booking(bookingNumber);
              DBConnection.closeConnection(con);
      return boek;
    }*/
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }   
     
   
    
}
    
    public static void main(String[] args) throws DBException{
        String x = "BE1207";
        ArrayList<Booking> test = new ArrayList<>();
        
        try {
            test = getBookingsPerCustomer(x);
            int size = test.size();
          for(int position = 0; position < size; position++)
              System.out.println(test.get(position).getBookingNumber());
    
;
    } catch (DBException ex) {
      Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
    }
  
  }
    
        
    
}
