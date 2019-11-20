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
import java.util.*;

public class Booking {

    private int bookingNumber;
    private double bookingDate;
    private double serviceFee; // final hoe buiten klasse etc OOP final static? 
    private double promotion; // was dit  nu een procent of niet?  vadt procent dan buiten de klasse definieren?
    // en promotie 

    //Constructor 
    public Booking(int bookingNumber) {
        this.bookingNumber = bookingNumber;
        this.bookingDate = System.currentTimeMillis();
        /**
         * uitgedrukt als het aantal milliseconden na de UNIX epoch (als Java
         * long). Deze waarde is een delta van een UTC-tijd-punt, en is
         * onafhankelijk van de lokale tijdzone … in de veronderstelling dat de
         * systeem klok correct is ingesteld.
         */
        this.serviceFee = serviceFee;
        this.promotion = calculatePromotion(); // vast dus moet niet meegegeven worden bij constructor 
    }

    //Getter instance variable "bookingNumber"
    public int getBookingNumber() {
        return bookingNumber;
    }

    //Getter instance variable "bookingDate"
    public double getBookingDate() {
        return bookingDate;
    }

    //Getter instance variable "serviceFee"
    public double getServiceFee() {
        return serviceFee;
    }

    //Getter instance variable "promotion"
    public double getPromotion() {
        return promotion;
    }

    // geen setters want je mag ze allemaal niet kunnenn aanpassen? 
    //Method to calculate the promotion 
    public static double calculatePromotion() {
        double calculate = 0.0;
        // if lus: als het verschil tussen dag van boeking groter is dan of kleinder dan  
        return calculate;
    }

    //Method to delete a booking 
    public void cancelBooking() {
    }

    //Method to calculate the net price of a flight 
    public double calculateNetPrice() {
        double netPrice = 0.0;
        netPrice = this.serviceFee + originele prijs vliegtuig - promotion;
        return netPrice;
    }
}
