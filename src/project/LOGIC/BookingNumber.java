package project.LOGIC;

public class BookingNumber {
    
    //Instance variables 
    private final String bookingDate;
    private final double serviceFee;
    private final int bookingNumber;

    //Constructor
    public BookingNumber(String bookingDate, double serviceFee, int bookingNumber) {
        this.bookingDate = bookingDate;
        this.serviceFee = serviceFee;
        this.bookingNumber = bookingNumber;
    }

}
