/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

/**
 *
 * @author klaas
 */
public class FlightLeg {

    private int legNumber;
    private String legOrigin;
    private String legDestination;
    private int departureDate;
    private int arrivalDate;
    private int departureTime;
    private int arrivalTime;
    private int duration;

    public FlightLeg(int legNumber, String legOrigin, String legDestination, int departureDate, int arrivalDate, int departureTime, int arrivalTime) {
        this.legNumber = legNumber;
        this.legOrigin = legOrigin;
        this.legDestination = legDestination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = this.calculateDuration();
    }

    public int getLegNumber() {
        return legNumber;
    }

    public String getLegOrigin() {
        return legOrigin;
    }

    public String getLegDestination() {
        return legDestination;
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
    
    public int calculateDuration() {
        int dur = arrivalTime - departureTime;
        return dur;
    }

}
