package project.LOGIC;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import project.DB.DBBooking;
import project.DB.DBBookingNumberGenerator;
import project.DB.DBException;

public class Booking {

    //Instance variables 
    private final String bookingDate;
    private static final double SERVICEFEEPROCENT = 0.05;
    private final ArrayList<Flight> flight;
    private final ArrayList<Customer> customers;
    private final double serviceFee;
    private static final double PROMOTIONPROCENT = 0.10;
    private final double promotion;
    private final double netPrice;

    //Constructor 
    public Booking(ArrayList<Flight> flight, ArrayList<Customer> customer) throws DBException, ParseException {
        this.bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.flight = flight;
        this.customers = customer;
        this.serviceFee = calculateServiceFee();
        this.promotion = calculatePromotion();
        this.netPrice = calculateNetPrice();
    }

    //Getters
    public String getBookingDate() {
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

    public ArrayList<Flight> getFlight() {
        return flight;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    //Helping method to safe a booking: one customer per booking 
    public static void saveBooking(Booking b) throws DBException, SQLException {
        for (int i = 0; i < b.customers.size(); i++) {
            for (int j = 0; j < b.flight.size(); j++) {
                DBBooking.saveBooking(b.bookingDate, b.promotion, b.serviceFee, b.flight.get(j).getFlightNumber(), b.flight.get(j).getDepartureDate(), b.customers.get(i).getPassportNumber());
            }
        }
    }

    //Method to delete a booking 
    public void deleteBooking(int bookingNumber) throws DBException {
        DBBooking.deleteBooking(bookingNumber);// dataBoys type nog aanpassen 
    }

    //Helping method to calculate the promotion: tested V
    private double calculatePromotion() throws DBException, ParseException {
        double prom = 0.0;
        for (Flight vlucht : this.flight) {
            String dateStart = bookingDate + " 00:00:00";
            String dateStop = vlucht.getDepartureDate() + " 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date d1;
            Date d2;
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            int diffDaysDouble = (int) diffDays;
            if (diffDaysDouble > 200 || diffDaysDouble < 20) {
                prom = prom + vlucht.getPrice() * PROMOTIONPROCENT;
            }
        }
        return prom;
    }

    //Helping method to calculate the net price of a booking: tested V
    private double calculateNetPrice() {
        double totalPrice = 0.0;
        for (Flight vlucht : this.flight) {
            totalPrice = totalPrice + vlucht.getPrice();
        }
        double netPr = this.serviceFee - this.promotion + totalPrice;
        return netPr;
    }

    //Method to calculate the service fee of the total booking
    private double calculateServiceFee() {
        double serviceFeeTotal = 0.0;
        for (Flight vlucht : this.flight) {
            serviceFeeTotal = serviceFeeTotal + (SERVICEFEEPROCENT * vlucht.getPrice());
        }
        return serviceFeeTotal;
    }
    
    //Helping method to calculate the revenue of a month: tested V
    public static ArrayList<Double> calculateRevenuePerMonth(String year) throws DBException {
        ArrayList<Double> revenuePerMonth = new ArrayList<>();
        ArrayList<BookingNumberGenerator> allBookings = DBBookingNumberGenerator.getBookings();
        Double revJan = 0.0;
        Double revFeb = 0.0;
        Double revMar = 0.0;
        Double revApr = 0.0;
        Double revMay = 0.0;
        Double revJun = 0.0;
        Double revJul = 0.0;
        Double revAug = 0.0;
        Double revSep = 0.0;
        Double revOkt = 0.0;
        Double revNov = 0.0;
        Double revDec = 0.0;
        for (BookingNumberGenerator booking : allBookings) {
            String jaar = booking.getBookingDate().substring(6);
            if (jaar.equals(year)) {
                switch (booking.getBookingDate().substring(3, 5)) {
                    case "01":
                        revJan = revJan + booking.getServiceFee();
                        break;
                    case "02":
                        revJan = revFeb + booking.getServiceFee();
                        break;
                    case "03":
                        revJan = revMar + booking.getServiceFee();
                        break;
                    case "04":
                        revJan = revApr + booking.getServiceFee();
                        break;
                    case "05":
                        revJan = revMay + booking.getServiceFee();
                        break;
                    case "06":
                        revJan = revJun + booking.getServiceFee();
                        break;
                    case "07":
                        revJan = revJul + booking.getServiceFee();
                        break;
                    case "08":
                        revJan = revAug + booking.getServiceFee();
                        break;
                    case "09":
                        revJan = revSep + booking.getServiceFee();
                        break;
                    case "10":
                        revJan = revOkt + booking.getServiceFee();
                        break;
                    case "11":
                        revJan = revNov + booking.getServiceFee();
                        break;
                    case "12":
                        revJan = revDec + booking.getServiceFee();
                        break;
                }
            }
        }
        revenuePerMonth.add(0, revJan);
        revenuePerMonth.add(1, revFeb);
        revenuePerMonth.add(2, revMar);
        revenuePerMonth.add(3, revApr);
        revenuePerMonth.add(4, revMay);
        revenuePerMonth.add(5, revJun);
        revenuePerMonth.add(6, revJul);
        revenuePerMonth.add(7, revAug);
        revenuePerMonth.add(8, revSep);
        revenuePerMonth.add(9, revOkt);
        revenuePerMonth.add(10, revNov);
        revenuePerMonth.add(11, revDec);
        return revenuePerMonth;
    }


}
