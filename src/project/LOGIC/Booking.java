package project.LOGIC;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import project.DB.DBBooking;
import project.DB.DBException;
import project.DB.DBFlight;

public class Booking {

    //Instance variables 
    private final int bookingNumber;
    private final LocalDate bookingDate;
    private static final double SERVICEFEEPROCENT = 0.05;
    private final double serviceFee;
    private static final double PROMOTIONPROCENT = 0.10;
    private final double promotion;
    private final double netPrice;
    private final Flight flight;
    private final ArrayList<Customer> customers;

    //Constructor 
    public Booking(Flight flight, ArrayList<Customer> customer) throws DBException {
        this.bookingNumber = calculateBookingNumber();
        this.bookingDate = LocalDate.now();
        this.serviceFee = SERVICEFEEPROCENT * DBFlight.getFlightForBooking(bookingNumber).getPrice();
        this.promotion = calculatePromotion();
        this.netPrice = calculateNetPrice();
        this.flight = flight;
        this.customers = customer;
    }

    //Getters
    public int getBookingNumber() {
        return bookingNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public double getPromotion() {
        return promotion;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    //Method to safe a booking 
    public static void saveBooking(Booking b) throws DBException {
        DBBooking.saveBooking(b);
    }

    //Method to delete a booking 
    public void deleteBooking(int bookingNumber) throws DBException {
        DBBooking.deleteBooking(bookingNumber);// dataBoys type nog aanpassen 
    }

    //Helping method to calculate the promotion 
    private double calculatePromotion() throws DBException {
        double prom = 0.0;
        Duration verschil;
        verschil = Duration.between(DBFlight.getFlightForBooking(bookingNumber).getDepartureDate(), this.bookingDate);
        if (verschil.toDays() > 180 || verschil.toDays() < 20) {
            prom = DBFlight.getFlightForBooking(bookingNumber).getPrice() * PROMOTIONPROCENT;
        }
        return prom;
    }

    //Helping method to calculate the net price of a flight 
    private double calculateNetPrice() throws DBException {
        double netPr;
        netPr = this.serviceFee - this.promotion + DBFlight.getFlightForBooking(bookingNumber).getPrice();
        return netPr;
    }

    //Helping method to calculate the booking number
    private int calculateBookingNumber() {
        int bookingnr = 0;
        return bookingnr;
    }
}
