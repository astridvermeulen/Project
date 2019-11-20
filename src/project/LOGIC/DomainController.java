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
    public ArrayList<Flight> vluchtenFilteren(int legs, String filter) {
        ArrayList<Flight> vluchten = new ArrayList<>();
        switch (legs) {
            case 0:
                switch (filter) {
                    case "duration":
                        break;
                    case "price":
                        break;

                    case "emission":
                        break;
                }
            case 1:
                switch (filter) {
                    case "duration":
                        break;

                    case "price":
                        break;

                    case "emission":
                        break;
                }
        }
        return vluchten;
    }

    private ArrayList<Flight> noLegs() {
        ArrayList<Flight> vluchten = new ArrayList<>();
        for (int i = 1; i <= vluchten.size(); i++) {
            Flight vlucht = vluchten.get(i);
            if (vlucht.) {
                
            }
        }
        return vluchten;
    }

    private ArrayList<Flight> yesLegs() {
        ArrayList<Flight> vluchten;
        return vluchten;
    }

    private ArrayList<Flight> filterDuration() {
        ArrayList<Flight> vluchten;
        return vluchten;
    }

    private ArrayList<Flight> filterPrice() {
        ArrayList<Flight> vluchten;
        return vluchten;
    }

    private ArrayList<Flight> filterEmission() {
        ArrayList<Flight> vluchten;
        return vluchten;
    }
}
