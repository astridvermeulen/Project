/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.DB;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deze klasse heb ik aangemaakt omdat het openen en sluiten van een connectie
 * maar voor alle klasse in de persistence layer is.
 *
 * @author klaas
 */
public class DBConnector {

    private static final String DB_NAME = "db2019_18";//vul hier uw databank naam in
    private static final String DB_PASS = "jshwuehd";//vul hier uw databank paswoord in

    public static Connection getConnection() throws DBException {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String protocol = "jdbc";
            String subProtocol = "mysql";
            String myDatabase = "//pdbmbook.com/" + DB_NAME;
            String URL = protocol + ":" + subProtocol + ":" + myDatabase;

            con = DriverManager.getConnection(URL, DB_NAME, DB_PASS);
            return con;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            closeConnection(con);
            throw new DBException(sqle);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            closeConnection(con);
            throw new DBException(cnfe);
        } catch (Exception ex) {
            ex.printStackTrace();
            closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static void closeConnection(Connection con) {
        try {
		 if(con != null)
            	con.close();
        } catch (SQLException sqle) {
            //do nothing
        }
        
    }
    
     public static void main(String[] args){
        Connection con = null;
        try {
            con = DBConnector.getConnection();
        } catch (DBException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (con != null){
            System.out.println("gelukt");
            closeConnection(con);            
        }
        else{
            System.out.println("niet gelukt");
            closeConnection(con);       
                   
        }
        
    }
}
