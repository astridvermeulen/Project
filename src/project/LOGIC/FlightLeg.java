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
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Duration duration;

    public FlightLeg(int legNumber, String legOrigin, String legDestination, String departureDate, String arrivalDate, String departureTime, String arrivalTime) {
        this.legNumber = legNumber;
        this.legOrigin = legOrigin;
        this.legDestination = legDestination;
        this.departureDate = LocalDate.parse(departureDate);
        this.arrivalDate = LocalDate.parse(arrivalDate);
        this.departureTime = LocalTime.parse(departureTime);
        this.arrivalTime = LocalTime.parse(arrivalTime);
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

}
