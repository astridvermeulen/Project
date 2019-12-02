package project.LOGIC;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import project.DB.DBAirline;
import project.DB.DBException;
import project.DB.DBFlight;
import project.DB.DBFlightLeg;

public class Flight {

    //Instance variables 
    private final String airline;
    private final String origin;
    private final String destination;
    private final LocalDateTime departureDateTime;
    private final LocalDate departureDate;
    private final LocalTime departureTime;
    private final LocalDateTime arrivalDateTime;
    private final LocalDate arrivalDate;
    private final LocalTime arrivalTime;
    private final String flightNumber;
    private final double price;
    private final ArrayList<FlightLeg> flightLegs;
    private final double emission;
    private final int duration;

    //Constructor
    public Flight(String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightNumber, double price) throws DBException, SQLException {
        this.airline = this.getAirlineOfFlight();
        this.origin = origin;
        this.destination = destination;
        this.duration = this.calculateDuration();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.departureDate = LocalDate.parse(departureDate, formatter);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.departureTime = LocalTime.parse(departureTime, dtf);
        this.arrivalDate = LocalDate.parse(arrivalDate, formatter);
        this.arrivalTime = LocalTime.parse(arrivalTime, dtf);
        this.flightNumber = flightNumber;
        this.price = price;
        this.flightLegs = DBFlightLeg.getFlightLegs(flightNumber, departureDate);
        this.emission = this.calculateEmission();
        this.departureDateTime = LocalDateTime.of(this.departureDate, this.departureTime);
        this.arrivalDateTime = LocalDateTime.of(this.arrivalDate, this.arrivalTime);
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

    public int getDuration() {
        return duration;
    }

    //Helping method to calculate the duration of a flight 
    private int calculateDuration() {
        int dur = Math.toIntExact(ChronoUnit.HOURS.between(departureDateTime, arrivalDateTime));
        return dur;
    }

    //Helping method to calculate the emission of a flight 
    private double calculateEmission() throws DBException {
        double em = DBFlight.getEmission(this.flightNumber, this.departureDate.toString());
        return em;
    }

    //Method to calculate the stopovers from a flight, 1 flight leg = 0 stopovers 
    public int numberOfStopovers() {
        int numberOfLegs = this.flightLegs.size();
        return numberOfLegs;
    }

    //Method to get the airline of the flight
    private String getAirlineOfFlight() throws DBException {
        String airl = DBAirline.getAirlineForFlight(this.flightNumber, this.destination);
        return airl;

    }
}
