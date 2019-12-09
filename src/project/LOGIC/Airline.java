package project.LOGIC;

import java.util.ArrayList;
import java.util.Collections;
import project.DB.DBAirline;
import project.DB.DBAirport;
import project.DB.DBException;

public class Airline {

    //Instance variables 
    private final int airlineCode;
    private final String airlineName;

    //Constructor 
    public Airline(int airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    //Getters
    public int getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    //Method to save an airline
    public static void saveAirline(Airline airline) throws DBException {
        DBAirline.saveAirline(airline);
    }

    //Method to delete an airline
    public static void deleteAirline(Airline airline) throws DBException {
        DBAirline.deleteAirline(airline);
    }


    //Main method to test the methods 
    public static void main(String[] args) throws DBException {
    }
}
