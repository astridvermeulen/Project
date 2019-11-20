/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

import java.util.ArrayList;
//dit is een tesst 

public class Customer {

    private String passportNumber;
    private String firstName;
    private String lastName;
    private String homeCountry;

    //Constructor 
    public Customer(String passportNumber, String firstName, String lastName, String homeCountry) {
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeCountry = homeCountry; // halen uit passportName??
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

    //Getter instance variable "homeCountry"
    public String getHomeCountry() {
        return homeCountry;
    }

    //Method to delete a customer
    public void deleteCustomer() {
    }

    //Method to get an overview of all the flights booked by a customer 
    public ArrayList<Flight> flightOverview() {
    }
    
    
}
