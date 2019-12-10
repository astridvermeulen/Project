package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBException;
import project.DB.DBTraject;

public class Traject {

    //Instance variables 
    private final String origin;
    private final String destination;
    private final Integer timesBooked;

    //Constructor
    public Traject(String origin, String destination, Integer timesBooked) {
        this.origin = origin;
        this.destination = destination;
        this.timesBooked = timesBooked;
    }
    
     //Method to give an overview of the most populair booked flights 
    public static ArrayList<Traject> topPopularTrips(String jaar) throws DBException {
        ArrayList<Traject> populairFlights = DBTraject.getTopPopularTrips(jaar);//DATaboys nog methode doorgeven hier
        return populairFlights;
    }
    
    //Getters
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getTimesBooked() {
        return timesBooked;
    }
    
}
