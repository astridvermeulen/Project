/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;

/**
 *
 * @author astridvermeulen
 */
public class MergeSort {

    public static void sort(ArrayList<Flight> flightsFilteredOnLegs) {
        if (flightsFilteredOnLegs.size() >= 2) {
            int halfLenght = flightsFilteredOnLegs.size() / 2;
            ArrayList<Flight> firstHalf = new ArrayList<>(halfLenght);
            ArrayList<Flight> lastHalf = new ArrayList<>(flightsFilteredOnLegs.size() - halfLenght);

            divide(flightsFilteredOnLegs, firstHalf, lastHalf);
            sort(firstHalf);
            sort(lastHalf);
            merge(flightsFilteredOnLegs, firstHalf, lastHalf);

        }
    }

    private static void divide(ArrayList<Flight> flightsFilteredOnLegs, ArrayList<Flight> firstHalf, ArrayList<Flight> lastHalf) {
        for (int i = 0; i < firstHalf.size(); i++) {
            firstHalf.set(i, flightsFilteredOnLegs.get(i));
        }
        for (int i = 0; i < lastHalf.size(); i++) {
            lastHalf.set(i, flightsFilteredOnLegs.get(firstHalf.size() + i));
        }
    }

    private static void merge(ArrayList<Flight> flightsFilteredOnLegs, ArrayList<Flight> firstHalf, ArrayList<Flight> lastHalf) {
        int firstHalfIndex = 0, lastHalfIndex = 0, aIndex = 0;
        while ((firstHalfIndex < firstHalf.size()) && (lastHalfIndex < lastHalf.size())) {
            if (firstHalf.get(firstHalfIndex).getDuration() < lastHalf.get(lastHalfIndex).getDuration()) {
                flightsFilteredOnLegs.set(aIndex, firstHalf.get(firstHalfIndex));
                firstHalfIndex++;

            } else {
                flightsFilteredOnLegs.set(aIndex, lastHalf.get(firstHalfIndex));
                lastHalfIndex++;
            }
            aIndex++;
        }
        while (firstHalfIndex < firstHalf.size()) {
            flightsFilteredOnLegs.set(aIndex, firstHalf.get(firstHalfIndex));
            aIndex++;
            firstHalfIndex++;

        }
        while (lastHalfIndex < lastHalf.size()) {
            flightsFilteredOnLegs.set(aIndex, lastHalf.get(lastHalfIndex));
            aIndex++;
            lastHalfIndex++;

        }
    }

}
