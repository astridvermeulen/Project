package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBCustomer;
import project.DB.DBException;
import project.DB.DBFlight;

public class Customer {

    //Instance variables 
    private final String passportNumber;
    private final String firstName;
    private final String lastName;
    private final String homeCountry;

    //Constructor 
    public Customer(String passportNumber, String firstName, String lastName) {
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeCountry = this.calculateHomeCountry(passportNumber); // halen uit passportName??
    }

    //Getters
    public String getPassportNumber() {
        return passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    //Method to safe a customer 
    public static void saveCustomer(Customer s) throws DBException {
        DBCustomer.saveCustomer(s);
    }

    //Method to delete a customer
    public static void deleteCustomer(String passportNumber) throws DBException {
        DBCustomer.deleteCustomer(passportNumber);
    }

    //Helping method to get the home country out of a passportnumber: tested V
    private static String calculateHomeCountry(String passportNumb) {

        String homeCount = "";
        for (int i = 0; i < passportNumb.length(); i++) {
            String s = String.valueOf(passportNumb.charAt(i));
            if (!isInteger(s)) {
                homeCount = homeCount + s;
            }
        }
        return homeCount;
    }

    //Helping method used in the method calculateHomeCountry: to determine if a String is an Integer: tested V
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    //Method to get an overview of all the flights booked by a customer 
    public ArrayList<Flight> flightOverview() throws DBException {
        ArrayList<Flight> flightsOfCustomer = DBFlight.getFlightsPerCustomer(this.passportNumber);
        return flightsOfCustomer;
    }

    //Method to get an overview of al the customers in the database
    public static ArrayList<Customer> customersOverview() throws DBException {
        ArrayList<Customer> customersAll = DBCustomer.getCustomers();
        return customersAll;
    }

}
