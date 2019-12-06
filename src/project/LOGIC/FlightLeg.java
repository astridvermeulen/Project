package project.LOGIC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightLeg {

    //Instance variables 
    private final int legNumber;
    private final String legOrigin;
    private final String legDestination;
    private final String legDepartureDate;
    private final String legArrivalDate;
    private final String legDepartureTime;
    private final String legArrivalTime;
    private final double legDuration;

    //Constructor
    public FlightLeg(int legNumber, String legOrigin, String legDestination, String departureDate, String arrivalDate, String departureTime, String arrivalTime) throws ParseException {
        this.legNumber = legNumber;
        this.legOrigin = legOrigin;
        this.legDestination = legDestination;
        this.legDepartureDate = departureDate;
        this.legDepartureTime = departureTime;
        this.legArrivalDate = arrivalDate;
        this.legArrivalTime = arrivalTime;
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

    public String getLegDepartureDate() {
        return legDepartureDate;
    }

    public String getLegArrivalDate() {
        return legArrivalDate;
    }

    public String getLegDepartureTime() {
        return legDepartureTime;
    }

    public String getLegArrivalTime() {
        return legArrivalTime;
    }

    public double getLegDuration() {
        return legDuration;
    }

    //Helping method to calculate the duration of a flight in hours:minutes: tested V
    private double calculateDuration() throws ParseException {
        String dateStart = legDepartureDate + " " + legDepartureTime;
        String dateStop = legArrivalDate + " " + legArrivalTime;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d1;
        Date d2;
        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);
        long diff = d2.getTime() - d1.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String dur = Long.toString(diffHours) + "." + Long.toString(diffMinutes);
        double output = Double.valueOf(dur);
        return output;
    }

}
