package project.LOGIC;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import project.DB.DBException;
import project.DB.DBFlight;

public class DomainController {

    public DomainController() {
    }

    public static DomainController domainController = new DomainController();

    public static DomainController getInstance() {
        return domainController;
    }

    //Method to filter the flights, returns the filtered flights 
    public ArrayList<Flight> searchFlight(Boolean legs, String filter, String origin, String destination, String departureDate) throws DBException {
        ArrayList<Flight> flightsAll = DBFlight.getFlights();
        ArrayList<Flight> flightsOriginDestination = this.fliterOnOriginDestination(origin, destination, flightsAll);
        ArrayList<Flight> flightsDate = this.filterOnDate(departureDate, flightsOriginDestination);
        ArrayList<Flight> flightsFilteredOnLegs = this.fliterOnLegs(legs, flightsDate);
        MergeSort.sort(flightsFilteredOnLegs, filter);
        return flightsFilteredOnLegs;
    }

    //Helping method to filter the flights: orignin and destination 
    private ArrayList<Flight> fliterOnOriginDestination(String origin, String destination, ArrayList<Flight> flightsAll) {
        ArrayList<Flight> flightsOriginDestination = new ArrayList<>();
        for (Flight vlucht : flightsAll) {
            if (vlucht.getOrigin().equalsIgnoreCase(origin) && vlucht.getDestination().equalsIgnoreCase(destination)) {
                flightsOriginDestination.add(vlucht);
            }
        }
        return flightsOriginDestination;
    }

    //Helping method to filter the flights: date
    private ArrayList<Flight> filterOnDate(String departureDate, ArrayList<Flight> flightsOriginDestination) {
        ArrayList<Flight> flightsDate = new ArrayList<>();
        LocalDate departureD = LocalDate.parse(departureDate);
        for (Flight vlucht : flightsOriginDestination) {
            if (vlucht.getDepartureDate().equals(departureD)) {
                flightsDate.add(vlucht);
            }
        }
        return flightsDate;
    }
    
    //Helping method to filter the flights: stopover possible or not
    private ArrayList<Flight> fliterOnLegs(Boolean legs, ArrayList<Flight> flightsAll) {
        ArrayList<Flight> flightsFiltered = new ArrayList<>();
        if (legs) {
            flightsFiltered = flightsAll;
        } else {
            for (Flight vlucht : flightsAll) {
                if (!(vlucht.getFlightLegs().size() > 1)) {
                    flightsFiltered.add(vlucht);
                }
            }
        }
        return flightsFiltered;
    }

    //To test the methods in this class
    public static void main(String[] args) throws DBException, SQLException {
        DomainController dc = new DomainController();
        //System.out.println(DBFlight.getFlight("EM0645", "12/2/2021").getDuration());
       dc.searchFlight(Boolean.TRUE, "price", "Amsterdam-Schiphol", "Dubai International Airport", "12/2/2021");
    }
}
