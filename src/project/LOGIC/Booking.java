package project.LOGIC;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import project.DB.DBBooking;
import project.DB.DBCustomer;
import project.DB.DBException;
import project.DB.DBFlight;

public class Booking {

    private int bookingNumber;
    private LocalDate bookingDate;
    private double serviceFeeProcent = 0.05;
    private double serviceFee; // final hoe buiten klasse etc OOP final static? 
    private double promotion; // was dit  nu een procent of niet?  vadt procent dan buiten de klasse definieren?
    // en promotie 

    //Constructor 
    public Booking(int bookingNumber) throws DBException {
        this.bookingNumber = bookingNumber;
        this.bookingDate = LocalDate.now();
        this.serviceFee = serviceFeeProcent * DBFlight.getFlightsForBooking(bookingNumber).getPrice();
        this.promotion = calculatePromotion(); // 
    }

    //Getter instance variable "bookingNumber"
    public int getBookingNumber() {
        return bookingNumber;
    }

    //Getter instance variable "bookingDate"
    public LocalDate getBookingDate() {
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

    //Method to calculate the promotion 
    public double calculatePromotion() throws DBException {
        double promotion = 0.0;
        Duration verschil;
        verschil = Duration.between(DBFlight.getFlightsForBooking(bookingNumber).getDepartureDate(), this.bookingDate);
        if (verschil.toDays() > 180 || verschil.toDays() < 20) {
            promotion = DBFlight.getFlightsForBooking(bookingNumber).getPrice() * 0.10;
        }
        return promotion;
    }

    //Method to delete a booking 
    public void deleteBooking(int bookingNumber) throws DBException {
        DBBooking.deleteBooking(bookingNumber);// dataBoys type nog aanpassen 
    }

    public static void saveBooking(Booking b) throws DBException {
        DBBooking.saveBooking(b);
    }

    //Method to calculate the net price of a flight 
    public double calculateNetPrice() throws DBException {
        double netPrice = 0.0;
        netPrice = this.serviceFee - this.promotion + DBFlight.getFlightsForBooking(bookingNumber).getPrice();
        return netPrice;
    }
}
