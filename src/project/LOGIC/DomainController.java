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

    //Helping method to filter the fligts according to duration price or emission
    private ArrayList<Flight> filterDurationPriceEmission(ArrayList<Flight> flightsFilteredOnLegs, String filter) {
        ArrayList<Flight> flightsFiltered = new ArrayList<>();
        switch (filter) {
            case "duration":
                flightsFiltered = this.filterDuration(flightsFilteredOnLegs);
                break;
            case "price":
                flightsFiltered = this.filterPrice(flightsFilteredOnLegs);
                break;
            case "emission":
                flightsFiltered = this.filterEmission(flightsFilteredOnLegs);
                break;
        }
        return flightsFiltered;
    }

    //Helping method to filter the flights on duratation
    private ArrayList<Flight> filterDuration(ArrayList<Flight> flightsFilteredOnLegs) {
        MergeSort.sort(flightsFilteredOnLegs);
        return flightsFilteredOnLegs;
    }

    //Helping method to filter the flights on price
    private ArrayList<Flight> filterPrice(ArrayList<Flight> flightsFilteredOnLegs) {
        MergeSort.sort(flightsFilteredOnLegs);
        return flightsFilteredOnLegs;
    }

    //Helping method to filter the flights on emission
    private ArrayList<Flight> filterEmission(ArrayList<Flight> flightsFilteredOnLegs) {
        MergeSort.sort(flightsFilteredOnLegs);
        return flightsFilteredOnLegs;
    }

}
