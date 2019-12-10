
package project.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TEAM DB
 */
public class DBConnection {
    
  
    private static DBConnection instance = null;
    private static Connection con;
   
    
      private DBConnection() {

    String url = "jdbc:mysql://pdbmbook.com/db2019_18";
    String driver = "com.mysql.cj.jdbc.Driver";
    String userName = "db2019_18"; 
    String password = "jshwuehd";
        try {
                Class.forName(driver);
                con = DriverManager.getConnection(url,userName, password);
                }

                    catch(ClassNotFoundException cnfErr)
                    {cnfErr.printStackTrace();
                    }
                    catch(SQLException err)
                    {err.printStackTrace();
                    }
}


public static DBConnection getInstance() {
if(instance == null)
return new DBConnection();
else
return instance;
}

public static Connection getConnection() {
    return con;
}

    



    public static void closeConnection(Connection con) {
        try {
		 if(con != null)
            	con.close();
   
        } catch (SQLException sqle) {
          sqle.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
    

System.out.println("Begin...\n");

      try
         {
         System.out.println ("one");
         String driver = "com.mysql.cj.jdbc.Driver";
         System.out.println ("two");
         Class.forName(driver).newInstance();
         System.out.println ("three");
         String url    = "jdbc:mysql://pdbmbook.com/db2019_18";
         String userid = "db2019_18";
         String passwd = "jshwuehd";
         Connection DBconn = DriverManager.getConnection(url, userid, passwd);
         System.out.println ("four");

         Statement stmt = DBconn.createStatement();
         System.out.println ("five");
         ResultSet rSet = stmt.executeQuery("select * from flight");
         while(rSet.next() == true)
         {
            double price = rSet.getDouble("price");
            System.out.println("Price: " + price);
         }
         rSet.close();
         stmt.close();

         DBconn.close();
      }
      catch (Exception e)
      {
         System.out.println ("Exception: " + e.toString());
         e.printStackTrace();
      }
      System.out.println ("...End\n");
   }
}
    

