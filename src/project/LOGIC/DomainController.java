package project.LOGIC;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import project.DB.DBException;
import project.DB.DBFlight;

public class DomainController {

    public DomainController() {
    }

    public static DomainController domainController = new DomainController();

    public static DomainController getInstance() {
        return domainController;
    }

    //Method to filter the flights, returns the filtered flights: tested V
    public ArrayList<Flight> searchFlight(Boolean legs, String filter, String origin, String destination, String departureDate) throws DBException {
        ArrayList<Flight> flightsAll = DBFlight.getFlights();
        ArrayList<Flight> flightsOriginDestination = this.fliterOnOriginDestination(origin, destination, flightsAll);
        ArrayList<Flight> flightsDate = this.filterOnDate(departureDate, flightsOriginDestination);
        ArrayList<Flight> flightsFilteredOnLegs = this.fliterOnLegs(legs, flightsDate);
        ArrayList<Flight> output;
        if (filter.equalsIgnoreCase("price")) {
            output = new ArrayList<>(flightsFilteredOnLegs.stream().sorted(Comparator.comparing(Flight::getPrice)).collect(Collectors.toList()));
        } else if (filter.equalsIgnoreCase("duration")) {
            output = new ArrayList<>(flightsFilteredOnLegs.stream().sorted(Comparator.comparing(Flight::getDuration)).collect(Collectors.toList()));
        } else if (filter.equalsIgnoreCase("emission")){
            output = new ArrayList<>(flightsFilteredOnLegs.stream().sorted(Comparator.comparing(Flight::getEmission)).collect(Collectors.toList()));
        } else{
            output = new ArrayList<>();
        }
        return output;
    }

    //Helping method to filter the flights: orignin and destination: tested V
    private ArrayList<Flight> fliterOnOriginDestination(String origin, String destination, ArrayList<Flight> flightsAll) {
        ArrayList<Flight> flightsOriginDestination = new ArrayList<>();
        for (Flight vlucht : flightsAll) {
            if (vlucht.getOrigin().equalsIgnoreCase(origin) && vlucht.getDestination().equalsIgnoreCase(destination)) {
                flightsOriginDestination.add(vlucht);
            }
        }
        return flightsOriginDestination;
    }

    //Helping method to filter the flights: date: tested V
    private ArrayList<Flight> filterOnDate(String departureDate, ArrayList<Flight> flightsOriginDestination) {
        ArrayList<Flight> flightsDate = new ArrayList<>();
        for (Flight vlucht : flightsOriginDestination) {
            if (vlucht.getDepartureDate().equals(departureDate)) {
                flightsDate.add(vlucht);
            }
        }
        return flightsDate;
    }

    //Helping method to filter the flights: stopover possible or not: tested V
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
}
