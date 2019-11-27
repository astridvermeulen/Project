/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBException;
import project.DB.DBFlight;

/**
 *
 * @author klaas
 */
public class DomainController {

    public DomainController() {
    }

    public static DomainController domainController = new DomainController();

    public static DomainController getInstance() {
        return domainController;
    }

    //Method to filter the flights, returns the filtered flights 
    public ArrayList<Flight> searchFlight(Boolean legs, String filter) throws DBException  {
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

}
