package project.LOGIC;

public class BookingNumberGenerator {
    
    //Instance variables 
    private final String bookingDate;
    private final double serviceFee;
    private final int bookingNumber;

    //Constructor
    public BookingNumberGenerator(String bookingDate, double serviceFee, int bookingNumber) {
        this.bookingDate = bookingDate;
        this.serviceFee = serviceFee;
        this.bookingNumber = bookingNumber;
    }
    
    //Getters
    public String getBookingDate() {
        return bookingDate;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }
    

}
