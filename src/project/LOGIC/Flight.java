/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author klaas
 */
public class Flight {

    private ArrayList<FlightLeg> flightLegs = new ArrayList<>();
    private String destination;
    private String origin;
    private String flightNumber;
    private double emission;
    private int price;
    private int departureDate;
    private int arrivalDate;
    private int departureTime;
    private int arrivalTime;
    private int duration;

    public Flight(String destination, String origin, String flightNumber, double emission, int price, int departureDate, int arrivalDate, int departureTime, int arrivalTime, int duration) {
        this.destination = destination;
        this.origin = origin;
        this.flightNumber = flightNumber;
        this.emission = emission;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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

    public int getPrice() {
        return price;
    }

    public int getDepartureDate() {
        return departureDate;
    }

    public int getArrivalDate() {
        return arrivalDate;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }
    
    
    
    public int calculateDuration(){
        int duration = 0;
        return duration;
    }
}
