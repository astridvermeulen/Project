package project.LOGIC;

import java.util.ArrayList;

public class MergeSort {

    public static void sort(ArrayList<Flight> flightsFilteredOnLegs, String filter) {
        if (flightsFilteredOnLegs.size() >= 2) {
            int halfLenght = flightsFilteredOnLegs.size() / 2;
            ArrayList<Flight> firstHalf = new ArrayList<>(halfLenght);
            ArrayList<Flight> lastHalf = new ArrayList<>(flightsFilteredOnLegs.size() - halfLenght);

            divide(flightsFilteredOnLegs, firstHalf, lastHalf);
            sort(firstHalf, filter);
            sort(lastHalf, filter);
            merge(flightsFilteredOnLegs, firstHalf, lastHalf, filter);
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

    private static void merge(ArrayList<Flight> flightsFilteredOnLegs, ArrayList<Flight> firstHalf, ArrayList<Flight> lastHalf, String filter) {
        int firstHalfIndex = 0, lastHalfIndex = 0, aIndex = 0;
        while ((firstHalfIndex < firstHalf.size()) && (lastHalfIndex < lastHalf.size())) {
            if (filter.equalsIgnoreCase("Duration")) {
                if (firstHalf.get(firstHalfIndex).getDuration().compareTo(lastHalf.get(lastHalfIndex).getDuration()) < 0) {
                    flightsFilteredOnLegs.set(aIndex, firstHalf.get(firstHalfIndex));
                    firstHalfIndex++;

                } else {
                    flightsFilteredOnLegs.set(aIndex, lastHalf.get(firstHalfIndex));
                    lastHalfIndex++;
                }
            } else if (filter.equalsIgnoreCase("Price")) {
                if (firstHalf.get(firstHalfIndex).getPrice() < lastHalf.get(lastHalfIndex).getPrice()) {
                    flightsFilteredOnLegs.set(aIndex, firstHalf.get(firstHalfIndex));
                    firstHalfIndex++;

                } else {
                    flightsFilteredOnLegs.set(aIndex, lastHalf.get(firstHalfIndex));
                    lastHalfIndex++;
                }
            } else {
                if (firstHalf.get(firstHalfIndex).getEmission() < lastHalf.get(lastHalfIndex).getEmission()) {
                    flightsFilteredOnLegs.set(aIndex, firstHalf.get(firstHalfIndex));
                    firstHalfIndex++;

                } else {
                    flightsFilteredOnLegs.set(aIndex, lastHalf.get(firstHalfIndex));
                    lastHalfIndex++;
                }
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
