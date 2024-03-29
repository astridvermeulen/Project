package project.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.LOGIC.Customer;

/**
 *
 * @author TEAM DB
 */
public class DBCustomer {  
    
      // retourneert 1 klant 
     public static Customer getCustomer(String passportNumber) throws DBException{
         Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT firstName, lastName, PassportNumber, birthDate "
        + "FROM db2019_18.customer "
	+ "WHERE passportNumber = '" + passportNumber + "'";

      ResultSet srs = stmt.executeQuery(sql);

      String firstName, lastName, birthDate;
      
      if (srs.next()) {
          passportNumber = srs.getString("passportNumber");
          firstName = srs.getString("firstName");
          lastName = srs.getString("lastName");
          birthDate = srs.getString("birthDate");
          
      } 
      else {// we verwachten slechts 1 rij...
	DBConnection.closeConnection(con);
	return null;
      }
      
      Customer klant = new Customer(passportNumber, firstName, lastName, birthDate);
              DBConnection.closeConnection(con);
      return klant;
    }
    
    catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
         
     }
     
     //retourneert alle klanten 
     public static ArrayList<Customer> getCustomers() throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM db2019_18.customer";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Customer> klanten = new ArrayList<>();
      while (srs.next())
        klanten.add(getCustomer(srs.getString("passportNumber")));
      DBConnection.closeConnection(con);
      return klanten;
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
   
    
     //customer toevoegen aan de database of bestaande aanpassen 
     public static void saveCustomer(Customer s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM customer "
              + "WHERE passportNumber = '" + s.getPassportNumber() + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE customer "
                + "SET firstname = '" + s.getFirstName() + "'"
		+ ", lastname = '" + s.getLastName() + "'"
		+ ", PassportNumber = '" + s.getPassportNumber() + "'"
		+ ", birthDate = '" + s.getBirthDate() + "'"
                + " WHERE passportNumber = '" + s.getPassportNumber() + "'";
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into customer "
                + "(firstName, lastName, passportNumber, birthDate) "
		+ "VALUES ('" + s.getFirstName() + "'"
		+ ", '" + s.getLastName() + "'"
		+ ", '" + s.getPassportNumber() + "'"
                + ", '" + s.getBirthDate() + "'"
		+ ")";
        stmt.executeUpdate(sql);
      }
      DBConnection.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
     // customer verwijderen uit de database 
     public static void deleteCustomer(String s) throws DBException {
    Connection con = null;
    try {
      con = DBConnection.getInstance().getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT passportNumber "
              + "FROM customer "
              + "WHERE passportNumber = '" + s + "'";
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // DELETE
	sql = "DELETE FROM customer "
                + "WHERE PassportNumber = '" + s + "'";
        stmt.executeUpdate(sql);
        DBConnection.closeConnection(con);
    
      } else {
	// DOORGEGEVEN CUSTOMER ZAT NIET IN DATABASE
        DBConnection.closeConnection(con);
    
	      }
      } catch (Exception ex) {
      ex.printStackTrace();
      DBConnection.closeConnection(con);
      throw new DBException(ex);
    }
  }
     
}
   

  

