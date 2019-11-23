/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;

/**
 *
 * @author klaas
 */
public class DomainController {

    public DomainController() {
    }

    //Method to filter the flights, returns the filtered flights 
    public ArrayList<Flight> vluchtenFilteren(Boolean legs, String filter) {
        ArrayList<Flight> flightsAll = new ArrayList<>(); //call naar databoys om een arraylist van alle vluchten te krijgen 
        ArrayList<Flight> flightsFilteredOnLegs = this.fliterOnLegs(legs, flightsAll);
        ArrayList<Flight> flightsFiltered = this.filterDurationPriceEmission(flightsFilteredOnLegs, filter);
        return flightsFiltered;
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

    

    //Helping method to filter the flights
    private ArrayList<Flight> filterDurationPriceEmission(ArrayList<Flight> flightsFilteredOnLegs, String filter) {
        MergeSort.sort(flightsFilteredOnLegs, filter);
        return flightsFilteredOnLegs;
    }

    

}
