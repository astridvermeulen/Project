package project.LOGIC;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import project.DB.DBAirline;
import project.DB.DBException;
import project.DB.DBFlight;
import project.DB.DBFlightLeg;

public class Flight {

    //Instance variables 
    private final String airline;
    private final String origin;
    private final String destination;
    private final String departureDate;
    private final String departureTime;
    private final String arrivalDate;
    private final String arrivalTime;
    private final String flightNumber;
    private final double price;
    private final ArrayList<FlightLeg> flightLegs;
    private final double emission;
    private double duration;
    private final int timesBooked;

    //Constructor
    public Flight(String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightNumber, double price) throws DBException, SQLException, ParseException {
        this.airline = DBAirline.getAirlineForFlight(flightNumber, departureDate);
        this.origin = origin;
        this.destination = destination;
        this.duration = 0.0;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightNumber = flightNumber;
        this.price = price;
        this.flightLegs = DBFlightLeg.getFlightLegs(flightNumber, departureDate);
        this.emission = this.calculateEmission();
        this.setDuration(); //Zo blijft de volgorde behouden van de GUI kolommen 
        this.timesBooked = -1;
    }

    public Flight(String origin, String destination, int timesBooked) {
        this.origin = origin;
        this.destination = destination;
        this.timesBooked = timesBooked;
        this.airline = null;
        this.duration = 0.0;
        this.departureDate = null;
        this.departureTime = null;
        this.arrivalDate = null;
        this.arrivalTime = null;
        this.flightNumber = null;
        this.price = 0.0;
        this.flightLegs = null;
        this.emission = 0.0;
    }

    //Getters
    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<FlightLeg> getFlightLegs() {
        return flightLegs;
    }

    public Double getEmission() {
        return emission;
    }

    public double getDuration() {
        return duration;
    }

    public int getTimesBooked() {
        return timesBooked;
    }

    //Setters
    public void setDuration() throws ParseException {
        this.duration = this.calculateDuration();
    }

    //Helping method to calculate the duration of a flight: tested V
    public double calculateDuration() throws ParseException { //methode nog terug naar private 
        String dateStart = departureDate + " " + departureTime;
        String dateStop = arrivalDate + " " + arrivalTime;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d1;
        Date d2;
        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);
        long diff = d2.getTime() - d1.getTime();
        long diffMinutes = (diff / (60 * 1000) % 60);
        double diffMinutesKomma = ((double) diffMinutes) / 100;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long diffDaysInhours = diffDays * 24;
        double output = diffHours + diffDaysInhours + diffMinutesKomma;
        return output;
    }

    //Method to display the duration in hour:minutes: tested V
    public String presentDuration() throws ParseException { //methode nog terug naar private 
        System.out.println("start");
        String dateStart = departureDate + " " + departureTime;
        String dateStop = arrivalDate + " " + arrivalTime;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d1;
        Date d2;
        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);
        long diff = d2.getTime() - d1.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        double diffMinutesKomma = ((double) diffMinutes) / 100;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long diffDaysInhours = diffDays * 24;
        double output = diffHours + diffDaysInhours + diffMinutesKomma;
        return String.valueOf(output).replace(".", ":");
    }

    //Helping method to calculate the emission of a flight 
    private double calculateEmission() throws DBException {
        double em = DBFlight.getEmission(this.flightNumber, this.departureDate);
        return em;
    }

    //Method to calculate the stopovers from a flight, 1 flight leg = 0 stopovers 
    public int numberOfStopovers() {
        int numberOfLegs = this.flightLegs.size();
        return numberOfLegs;
    }

    //Method to give an overview of all the flights 
    public static ArrayList<Flight> flightsOverview() throws DBException {
        ArrayList<Flight> flightsAll = DBFlight.getFlights();
        return flightsAll;
    }

    //Method to give an overview of the most populair booked flights 
    public static ArrayList<Flight> tripsOriginDestinations() throws DBException {
        ArrayList<Flight> populairFlights = DBFlight.getFlights();//DATaboys nog methode doorgeven hier
        return populairFlights;
    }

}
