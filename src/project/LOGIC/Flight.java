/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;

/**
 *
 * @author klaas
 */
public class Flight {

    private ArrayList<FlightLeg> flightLegs;
    private String destination;
    private String origin;
    private String flightNumber;
    private double emission;
    private double price;
    private int departureDate;
    private int arrivalDate;
    private int departureTime;
    private int arrivalTime;
    private int duration;

    public Flight(ArrayList<FlightLeg> flightLegs, String destination, String origin, String flightNumber, double price, int departureDate, int arrivalDate, int departureTime, int arrivalTime) {
        this.flightLegs = flightLegs;
        this.destination = destination;
        this.origin = origin;
        this.flightNumber = flightNumber;
        this.emission = this.calculateEmission();
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

    public double getPrice() {
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
        int dur = arrivalTime - departureTime;
        return dur;
    }
    
    public double calculateEmission(){
        double em =0;
       // em = duration* waarde opzoeken co2 per uur;
        return em;
    }
}
