/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

/**
 *
 * @author klaas
 */
public class Airport {

    private String airportCode; // final hoe zat da opzoeken 
    private String airportName; // final ook of niet

    public Airport(String airportCode, String airportName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
    }

    //Getter instance variable "airportCode"
    public String getAirportCode() {
        return airportCode;
    }

    //Getter instance variable "airportName"
    public String getAirportName() {
        return airportName;
    }

    // geen setters want je mag ze beiden niet meer kunnenn aanpassen?
}
