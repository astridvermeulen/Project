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
            }
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void customerInfo(TextField passportNr, TextField firstName, TextField lastName, TextField birthDate){
        Customer klant = new Customer(passportNr.getText(), firstName.getText(), lastName.getText(), birthDate.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
    }
    
    
            
    
}
