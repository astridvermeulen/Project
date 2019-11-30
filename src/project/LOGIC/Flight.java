package project.LOGIC;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import project.DB.DBException;
import project.DB.DBFlight;
import project.DB.DBFlightLeg;

public class Flight {

    //Instance variables 
    private String airline;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String flightNumber;
    private double price;
    private ArrayList<FlightLeg> flightLegs;
    private double emission;
    private Duration duration;

    //Constructor AIRLINE EN FLIGHTLEGS NOG INISTIALIZERN 
    public Flight(String airline, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightNumber, double price) throws DBException {
        // this.airline = DBFlight.airportPerFlight;// databoyst moeten deze nog maken 
        this.origin = origin;
        this.destination = destination;
        //this.duration = this.calculateDuration();
        this.departureDate = LocalDate.parse(departureDate);
        this.departureTime = LocalTime.parse(departureTime);
        this.arrivalDate = LocalDate.parse(arrivalDate);
        this.arrivalTime = LocalTime.parse(arrivalTime);
        this.flightNumber = flightNumber;
        this.price = price;
        // this.flightLegs = this.getFlightLegsDB();// databoyst moeten deze nog maken 
        this.emission = this.calculateEmission();
    }

    //Getters
    public ArrayList<FlightLeg> getFlightLegs() {
        return flightLegs;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public double getEmission() {
        return emission;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public Duration calculateDuration() {
        Duration dur = Duration.between(departureTime, arrivalTime);
        return dur;
    }

    public double calculateEmission() throws DBException {
        double em = DBFlight.getEmission(this.flightNumber, this.departureDate.toString());
        //dataBoys moeten int veranderen naar varchar 
        return em;
    }

    public int numberOfLegs() {
        int numberOfLegs = this.flightLegs.size();
        return numberOfLegs;
    }

    /*public ArrayList<FlightLeg> void getFlightLegsDB()(){
        ArrayList<FlightLeg> flightLegs;
                return flightLegs;
    }*/
}
