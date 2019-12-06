package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBAirport;
import project.DB.DBException;
import project.DB.DBFlight;

public class Airport {

    //Instance variables 
    private final String airportCode;
    private final String airportName;
    private final int timesUsed; //Needed for the dataBoys

    //Constructor 
    public Airport(String airportCode, String airportName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.timesUsed = -1;
    }

    //Constuctor needed for the dataBoys 
    public Airport(String airportName, int timesUsed) {
        this.airportCode = "";
        this.airportName = airportName;
        this.timesUsed = timesUsed;
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

    //Method to give an overview of the most populair airports 
    public static ArrayList<String> tenMostPopularAirports() throws DBException {
        ArrayList<Airport> populairAirports = DBAirport.getAirports();//DATaboys nog methode doorgeven hier 
        ArrayList<String> nameOfPopulairAirports = new ArrayList<>();
        for(Airport airport:populairAirports){
            nameOfPopulairAirports.add(airport.getAirportName());
        }
        return nameOfPopulairAirports;
    }
}
