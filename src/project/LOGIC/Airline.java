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
public class Airline {

    private int airlineCode; // final hoe zat dat, buiten uw klasse defineren etc 
    private String airlineName;

    public Airline(int airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    //Getter instance variable "airlineCode"
    public int getAirlineCode() {
        return airlineCode;
    }
    
    //Getter instance variable "airlineName"
    public String getAirlineName() {
        return airlineName;
    }

    // geen setters want je mag ze beiden niet meer kunnenn aanpassen?

}
