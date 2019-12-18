/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.LOGIC.Flight;
import javafx.scene.control.TableView;
import project.DB.DBException;
import project.LOGIC.Customer;
import static project.LOGIC.Customer.saveCustomer;
import javafx.scene.control.TextField;
import project.LOGIC.Booking;
import static project.LOGIC.Booking.saveBooking;
/**
 *
 * @author Lars
 */
public class MakeBooking {
    private Flight flight;
    private Booking booking;
    private ArrayList<Flight> selectedFlights = new ArrayList();
    private ArrayList<Customer> customersLinkedToBooking = new ArrayList<>();
    
    public static MakeBooking mb = new MakeBooking();

    public static MakeBooking getInstance() {
        return mb;
    }

    public ArrayList<Flight> getSelectedFlights() {
        return selectedFlights;
    }
    
    public void makeBooking(){
        System.out.println(selectedFlights);
        System.out.println(customersLinkedToBooking);
        try {
            booking = new Booking(selectedFlights, customersLinkedToBooking);
            try {
                System.out.println(booking);
                saveBooking(booking);
            } catch (SQLException ex) {
                Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
                alertBox.display("Warning!", ex.getMessage());
            }
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
        
    }
    
    
    
    public void flightInfo(TableView<Flight> tableView){
        
        try {
            flight = new Flight(tableView.getSelectionModel().getSelectedItem().getOrigin(),tableView.getSelectionModel().getSelectedItem().getDestination(),
                    tableView.getSelectionModel().getSelectedItem().getDepartureDate(), tableView.getSelectionModel().getSelectedItem().getDepartureTime(),
                    tableView.getSelectionModel().getSelectedItem().getArrivalDate(),tableView.getSelectionModel().getSelectedItem().getArrivalTime(),
                    tableView.getSelectionModel().getSelectedItem().getFlightNumber(),tableView.getSelectionModel().getSelectedItem().getPrice());
            selectedFlights.add(flight);
            //test
            System.out.println(selectedFlights);
            System.out.println(selectedFlights.get(0).getAirline());
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
    }
    public void customerInfo(TextField passportNr, TextField firstName, TextField lastName, String birthDate){
        Customer klant = new Customer(passportNr.getText(), firstName.getText(), lastName.getText(), birthDate);
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
        customersLinkedToBooking.add(klant);
    }

    public ArrayList<Customer> returnPassengers(){
        ArrayList<Customer> passengers = new ArrayList();
        for(Customer c: booking.getCustomers()){
            passengers.add(c);
        }
        return passengers;
    }
    
    public ArrayList<Flight> returnFlights(){
        ArrayList<Flight> flights = new ArrayList();
        for(Flight f: booking.getFlight()){
            flights.add(f);
        }
        return flights;
    }
    
    public Double returnNetPrice(){
        Double netPrice = booking.getNetPrice();
        return netPrice;
    }
    
    public Double returnPromotion(){
        Double promotion = booking.getPromotion();
        return promotion;
    }
    public Double returnServiceFee(){
        Double serviceFee = booking.getServiceFee();
        return serviceFee;
    }
    
    public void deleteSelectedFlights(){
        selectedFlights.clear();
    }
    
    public ArrayList<Double> getRevenuesPerMonth(String year){
        ArrayList<Double> revenues = new ArrayList();
        try {   
            for(Double d: booking.calculateRevenuePerMonth(year)){
                revenues.add(d);
            }
          
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }  
        return revenues;
    }
    
            
    
}
