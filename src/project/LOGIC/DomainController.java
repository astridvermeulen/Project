package project.LOGIC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import project.DB.DBAirport;
import project.DB.DBCustomer;
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
         ArrayList<Flight> flightsDate = this.filterOnDate(departureDate,flightsOriginDestination);
        ArrayList<Flight> flightsFilteredOnLegs = this.fliterOnLegs(legs, flightsDate);
        MergeSort.sort(flightsFilteredOnLegs, filter);
        return flightsFilteredOnLegs;
    }

    //Helping method to filter: stopover possible or not
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

    private ArrayList<Flight> fliterOnOriginDestination(String origin, String destination, ArrayList<Flight> flightsAll) {
        ArrayList<Flight> flightsOriginDestination = new ArrayList<>();
        for (Flight vlucht : flightsAll) {
            if (vlucht.getOrigin().equalsIgnoreCase(origin) && vlucht.getDestination().equalsIgnoreCase(destination)) {
                flightsOriginDestination.add(vlucht);
            }
        }
        return flightsOriginDestination;
    }
    
    private ArrayList<Flight> filterOnDate(String departureDate, ArrayList<Flight> flightsOriginDestination){
        ArrayList<Flight> flightsDate = new ArrayList<>();
        LocalDate departureD = LocalDate.parse(departureDate);
        for (Flight vlucht : flightsOriginDestination) {
            if (vlucht.getDepartureDate().equals(departureD) ) {
                flightsDate.add(vlucht);
            }
        }
        return flightsDate;
    }

    public ArrayList<Customer> customersOverview() throws DBException {
        ArrayList<Customer> customersAll = DBCustomer.getCustomers();
        return customersAll;
    }

    public ArrayList<String> airportsAlphabetic() throws DBException {
        ArrayList<Airport> airportsAll = DBAirport.getAirports();
        ArrayList<String> airportNames = new ArrayList();
        for (int i = 0; i < airportsAll.size(); i++) {
            airportNames.set(i, airportsAll.get(i).getAirportName());
        }
        Collections.sort(airportNames);
        return airportNames;
    }

    public static void main(String[] args) {

    }

}
