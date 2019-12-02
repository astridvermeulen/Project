/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author klaas
 */
public class FlightLeg {
    
    //Instance variables 
    private final int legNumber;
    private final String legOrigin;
    private final String legDestination;
    private final LocalDate legDepartureDate;
    private final LocalDate legArrivalDate;
    private final LocalTime legDepartureTime;
    private final LocalTime legArrivalTime;
    private final Duration legDuration;

    //Constructor
    public FlightLeg(int legNumber, String legOrigin, String legDestination, String departureDate, String arrivalDate, String departureTime, String arrivalTime) {
        this.legNumber = legNumber;
        this.legOrigin = legOrigin;
        this.legDestination = legDestination;
        this.legDepartureDate = LocalDate.parse(departureDate);
        this.legArrivalDate = LocalDate.parse(arrivalDate);
        this.legDepartureTime = LocalTime.parse(departureTime);
        this.legArrivalTime = LocalTime.parse(arrivalTime);
        this.legDuration = this.calculateDuration();
    }

    //Getters
    public int getLegNumber() {
        return legNumber;
    }

    public String getLegOrigin() {
        return legOrigin;
    }

    public String getLegDestination() {
        return legDestination;
    }

    public LocalDate getLegDepartureDate() {
        return legDepartureDate;
    }

    public LocalDate getLegArrivalDate() {
        return legArrivalDate;
    }

    public LocalTime getLegDepartureTime() {
        return legDepartureTime;
    }

    public LocalTime getLegArrivalTime() {
        return legArrivalTime;
    }

    public Duration getLegDuration() {
        return legDuration;
    }
   
    //Helping method to calculate the duration of a flight 
    private Duration calculateDuration() {
        Duration dur = Duration.between(legDepartureTime, legArrivalTime);
        return dur;
    }

}
