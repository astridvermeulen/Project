package project.LOGIC;

public class PopularAirports {
    
    //Instance variables 
    private final String airportName;
    private final int timesUsed;
    
    //Constuctor 
    public PopularAirports(String airportName, int timesUsed) {
        this.airportName = airportName;
        this.timesUsed = timesUsed;
    }
    
    //Getters
    public String getAirportName() {
        return airportName;
    }

    public int getTimesUsed() {
        return timesUsed;
    }
    
}
