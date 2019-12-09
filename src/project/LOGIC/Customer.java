package project.LOGIC;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import project.DB.DBCustomer;
import project.DB.DBException;
import project.DB.DBFlight;

public class Customer {

    //Instance variables 
    private final String passportNumber;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private final String homeCountry;
    private SimpleStringProperty birthDate;

    //Constructor 
    public Customer(String passportNumber, String firstName, String lastName, String birthDate) {
        this.passportNumber = passportNumber;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.homeCountry = calculateHomeCountry(passportNumber);
        this.birthDate = new SimpleStringProperty(birthDate);
    }

    public Customer(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = new SimpleStringProperty(birthDate);
    }

    //Getters
    public String getPassportNumber() {
        return passportNumber;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public String getBirthDate() {
        return birthDate.get();
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

    //Method to get the total emission of the booked flights of a customer
    public Double totalEmissionCustomer() throws DBException {
        Double emissionTot = 0.0;
        ArrayList<Flight> flightsOfCustomer = DBFlight.getFlightsPerCustomer(this.passportNumber);
        for (Flight vlucht : this.flightOverview()) {
            emissionTot = emissionTot + vlucht.getEmission();
        }
        return emissionTot;
    }
}
