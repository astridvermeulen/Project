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
        
        this.legDepartureDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.legDepartureTime = LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        this.legArrivalDate = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.legArrivalTime = LocalTime.parse(arrivalTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        
       
        
        
        this.legDepartureDateTime = LocalDateTime.of(this.legDepartureDate, this.legDepartureTime);
        this.legArrivalDateTime = LocalDateTime.of(this.legArrivalDate, this.legArrivalTime);
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

    public int getLegDuration() {
        return legDuration;
    }

    //Helping method to calculate the duration of a flight 
    private int calculateDuration() {
        int dur = Math.toIntExact(ChronoUnit.HOURS.between(legDepartureDateTime, legArrivalDateTime));
        return dur;
    }

}
