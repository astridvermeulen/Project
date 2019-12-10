package project.LOGIC;

public class Traject {

    //Instance variables 
    private final String origin;
    private final String destination;
    private final int timesBooked;

    //Constructor
    public Traject(String origin, String destination, int timesBooked) {
        this.origin = origin;
        this.destination = destination;
        this.timesBooked = timesBooked;
    }
    
    //Getters
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getTimesBooked() {
        return timesBooked;
    }
    
}
