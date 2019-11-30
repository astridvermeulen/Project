package project.LOGIC;

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
    public ArrayList<Flight> searchFlight(Boolean legs, String filter) throws DBException {
        ArrayList<Flight> flightsAll = DBFlight.getFlights();
        ArrayList<Flight> flightsFilteredOnLegs = this.fliterOnLegs(legs, flightsAll);
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

    public ArrayList<Customer> customersOverview() throws DBException {
        ArrayList<Customer> customersAll = DBCustomer.getCustomers();
        return customersAll;
    }

    public ArrayList<String> airportsAlphabetic() throws DBException{
        ArrayList<Airport> airportsAll = DBAirport.getAirports();
        ArrayList<String> airportNames = new ArrayList();
        for(int i = 0; i < airportsAll.size(); i++){
            airportNames.set(i, airportsAll.get(i).getAirportName()); }
        Collections.sort(airportNames);
        return airportNames;
    }
    
    
   

}
