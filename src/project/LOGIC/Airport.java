package project.LOGIC;

import project.DB.DBAirport;
import project.DB.DBException;

public class Airport {

    //Instance variables 
    private final String airportCode; // final hoe zat da opzoeken 
    private final String airportName; // final ook of niet

    //Constructor 
    public Airport(String airportCode, String airportName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
    }

    //Getters
    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    //Method to save an airport
    public static void saveAirport(Airport airport) throws DBException {
        DBAirport.saveAirport(airport);
    }

    //Method to delete an airport
    public static void deleteAirport(Airport airport) throws DBException {
        DBAirport.deleteAirport(airport);
    }
}
