/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBCustomer;
import project.DB.DBException;
import project.DB.DBFlight;
//dit is een tesst 

public class Customer {
    
    private String passportNumber;
    private String firstName;
    private String lastName;
    private String homeCountry;

    //Constructor 
    public Customer(String passportNumber, String firstName, String lastName) {
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeCountry = this.calculateHomeCountry(); // halen uit passportName??
    }

    //Getter instance variable "passportNumber"
    public String getPassportNumber() {
        return passportNumber;
    }

    //Getter instance variable "firstName"
    public String getFirstName() {
        return firstName;
    }

    //Getter instance variable "lastName"
    public String getLastName() {
        return lastName;
    }

    public String getHomeCountry() {
        return homeCountry;
    }
    
    public static void saveCustomer(Customer s) throws DBException {
        DBCustomer.saveCustomer(s);
    }

    //Getter instance variable "homeCountry"
    public String calculateHomeCountry() {
        String passportNumber = this.getPassportNumber();
        String homeCountry = "";
        for (int i = 0; i < passportNumber.length(); i++) {
            String s = String.valueOf(homeCountry.charAt(i));
            if (!isInteger(s)) {
                homeCountry = homeCountry + homeCountry.charAt(i);
            }
        }
        return homeCountry;
    }

    //Helping method to determine if a String is an Integer
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    //Method to delete a customer
    public void deleteCustomer(String passportNumber) throws DBException {
        DBCustomer.deleteCustomer(passportNumber);
    }

    //Method to get an overview of all the flights booked by a customer 
    public ArrayList<Flight> flightOverview() throws DBException {
        ArrayList<Flight> flightsOfCustomer = DBFlight.getFlightsPerCustomer(this.passportNumber);
        return flightsOfCustomer;  
    }
    
}
