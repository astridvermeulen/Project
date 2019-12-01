package project.LOGIC;

import java.sql.SQLException;
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
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final LocalTime departureTime;
    private final LocalDate arrivalDate;
    private final LocalTime arrivalTime;
    private final String flightNumber;
    private final double price;
    private final ArrayList<FlightLeg> flightLegs;
    private final double emission;
    private final Duration duration;

    //Constructor AIRLINE NOG INISTIALIZERN 
    public Flight(String airline, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightNumber, double price) throws DBException, SQLException {
        // this.airline = DBFlight.airportPerFlight;// databoyst moeten deze nog maken 
        this.origin = origin;
        this.destination = destination;
        this.duration = this.calculateDuration();
        this.departureDate = LocalDate.parse(departureDate);
        this.departureTime = LocalTime.parse(departureTime);
        this.arrivalDate = LocalDate.parse(arrivalDate);
        this.arrivalTime = LocalTime.parse(arrivalTime);
        this.flightNumber = flightNumber;
        this.price = price;
        this.flightLegs = DBFlightLeg.getFlightLegs(flightNumber, departureDate);
        this.emission = this.calculateEmission();
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getArrivalTime() {
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

    public double getEmission() {
        return emission;
    }

    public Duration getDuration() {
        return duration;
    }

    //Helping method to calculate the duration of a flight 
    private Duration calculateDuration() {
        Duration dur = Duration.between(departureTime, arrivalTime);
        return dur;
    }

    //Helping method to calculate the emission of a flight 
    private double calculateEmission() throws DBException {
        double em = DBFlight.getEmission(this.flightNumber, this.departureDate.toString());
        return em;
    }

    //Method to calculate the number of legs from a flight 
    public int numberOfLegs() {
        int numberOfLegs = this.flightLegs.size();
        return numberOfLegs;
    }
}
