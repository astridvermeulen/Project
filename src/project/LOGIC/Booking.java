package project.LOGIC;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.bookingNumber = -1;
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

    //Helping method to safe a booking: one customer per booking 
    public void saveBooking(Booking b) throws DBException, SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String bookingDateInString = b.bookingDate.format(formatter);
        String departureDateInString = b.flight.getDepartureDate().format(formatter);
        for (int i = 0; i < b.customers.size(); i++) {
            DBBooking.saveBooking(bookingDateInString, b.promotion, b.serviceFee, b.flight.getFlightNumber(), departureDateInString, b.customers.get(i).getPassportNumber());
        }
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

    //Method to calculate the revenue of a month
    public static double calculateRevenuePerMonth(String month) throws DBException {
        double revenuePerMonth = 0.0;
        ArrayList<Booking> allBookings = DBBooking.getBookings();
        for (Booking booking : allBookings) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String maand = booking.bookingDate.format(formatter).substring(3, 4);
            if(maand.equals(month)){
                revenuePerMonth = revenuePerMonth + booking.calculateNetPrice();
            }
        }
        return revenuePerMonth;
    }

    public static void main(String[] args) throws DBException {
        System.out.println(Booking.calculateRevenuePerMonth("01"));
    }
}
