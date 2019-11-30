/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import project.DB.DBException;
import project.DB.DBFlight;
import project.DB.DBFlightLeg;

/**
 *
 * @author klaas
 */
public class Flight {

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

    public Flight(String airline, String origin, String destination, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, String flightNumber, double price) throws DBException {
        // this.airline = DBFlight.airportPerFlight;// databoyst moeten deze nog maken 
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightNumber = flightNumber;
        this.price = price;
        // this.flightLegs = this.getFlightLegsDB();// databoyst moeten deze nog maken 
        this.emission = this.calculateEmission();
        this.duration = this.calculateDuration();
    }

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
        double em = DBFlight.getEmission(this.flightNumber, this.departureDate);//dataBoys moeten int veranderen naar varchar 
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
