/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
        private final LocalDateTime legDepartureDateTime;
        private final LocalDateTime legArrivalDateTime;

    private final LocalDate legArrivalDate;
    private final LocalTime legDepartureTime;
    private final LocalTime legArrivalTime;
    private final int legDuration;

    //Constructor
    public FlightLeg(int legNumber, String legOrigin, String legDestination, String departureDate, String arrivalDate, String departureTime, String arrivalTime) {
        this.legNumber = legNumber;
        this.legOrigin = legOrigin;
        this.legDestination = legDestination;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.legDepartureDate = LocalDate.parse(departureDate, formatter);
        this.legArrivalDate = LocalDate.parse(arrivalDate, formatter);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.legDepartureTime = LocalTime.parse(departureTime, dtf);
        this.legArrivalTime = LocalTime.parse(arrivalTime, dtf);
        this.legDuration = this.calculateDuration();
        this.legDepartureDateTime = LocalDateTime.of(this.legDepartureDate, this.legDepartureTime);
        this.legArrivalDateTime = LocalDateTime.of(this.legArrivalDate, this.legArrivalTime);
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

    public int getLegDuration() {
        return legDuration;
    }

    //Helping method to calculate the duration of a flight 
    private int calculateDuration() {
        int dur = Math.toIntExact(ChronoUnit.HOURS.between(legDepartureDateTime, legArrivalDateTime));
        return dur;
    }

}
