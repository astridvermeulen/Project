package project.LOGIC;

import java.util.ArrayList;
import java.util.Collections;
import project.DB.DBAirport;
import project.DB.DBException;
import project.DB.DBPopularAirports;

public class Airport {

    //Instance variables 
    private final String airportCode;
    private final String airportName;

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

    //Method to give an overview of the most populair airports 
    public static ArrayList<String> tenMostPopularAirports() throws DBException {
        ArrayList<PopularAirports> populairAirports = DBPopularAirports.getPopularAirport();//DATaboys nog methode doorgeven hier 
        ArrayList<String> nameOfPopulairAirports = new ArrayList<>();
        for (PopularAirports airport : populairAirports) {
            nameOfPopulairAirports.add(airport.getAirportName());
        }
        return nameOfPopulairAirports;
    }
     
    //Method to order the airports in alpabetic order: tested V 
    public static ArrayList<String> airportsAlphabetic() throws DBException {
        ArrayList<Airport> airportsAll = DBAirport.getAirports();
        ArrayList<String> airportNames = new ArrayList();
        for (int i = 0; i < airportsAll.size(); i++) {
            String naam = airportsAll.get(i).getAirportName();
            airportNames.add(i, naam);
        }
        Collections.sort(airportNames);
        return airportNames;
    }

}
