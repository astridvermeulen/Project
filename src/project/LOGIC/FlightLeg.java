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

    private int legNumber;
    private String legOrigin;
    private String legDestination;
    private LocalDate legDepartureDate;
    private LocalDate legArrivalDate;
    private LocalTime legDepartureTime;
    private LocalTime legArrivalTime;
    private Duration legDuration;

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

    public int getLegNumber() {
        return legNumber;
    }

    public String getLegOrigin() {
        return legOrigin;
    }

    public String getLegDestination() {
        return legDestination;
    }

    public LocalDate getDepartureDate() {
        return legDepartureDate;
    }

    public LocalDate getArrivalDate() {
        return legArrivalDate;
    }

    public LocalTime getDepartureTime() {
        return legDepartureTime;
    }

    public LocalTime getArrivalTime() {
        return legArrivalTime;
    }

    public Duration getDuration() {
        return legDuration;
    }
    
    public Duration calculateDuration() {
        Duration dur = Duration.between(legDepartureTime, legArrivalTime);
        return dur;
    }

}
