package project.LOGIC;

public class Airline {
//ELISE
    //Instance variables 
    private final int airlineCode;
    private final String airlineName;

    //Constructor 
    public Airline(int airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    //Getters
    public int getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

}
