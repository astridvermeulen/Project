/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.Customer;
import static project.LOGIC.Customer.customersOverview;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class ReportCustomerController implements Initializable {
    private DomainController model;
    private Customer chosenCustomer;
    
    @FXML
    private TableView<Flight> tableViewEmission;
    @FXML
    private TableColumn<Flight, String> flightNumberColumn;
    @FXML
    private TableColumn<Flight, String> departureDateColumn;
    @FXML
    private TableColumn<Flight, String> originAirportColumn;
    @FXML
    private TableColumn<Flight, String> destinationAirportColumn;
    @FXML
    private TableColumn<Flight, Double> emissionColumn;
    @FXML
    private Button chooseThisCustomerBtn;
    @FXML
    private Button donateBtn;
    @FXML
    private Label totalEmissionLbl;
    @FXML
    private Label emissionToInputLbl;
    @FXML
    private TableView<Customer> tableViewCustomers;
    @FXML
    private TableColumn<Customer, String> passportIDColumn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        passportIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("passportNumber"));
        
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureDate"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("origin"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        emissionColumn.setCellValueFactory(new PropertyValueFactory<Flight, Double>("emission"));
    
        tableViewCustomers.setItems(getCustomers());
        
    }    
    
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            for(Customer c: customersOverview()){
            customers.add(c);
        }
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("customers toegevoegd in tabel");
        return customers;
        
    }
    
    
    
    public ObservableList<Flight> getFlightsFromCustomer(){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        ArrayList<Flight>  vlucht = new ArrayList();
        try {
            for(Flight f: chosenCustomer.flightOverview()){
                flights.add(f);
                vlucht.add(f);
            }
        } catch (DBException ex) {
            Logger.getLogger(ReportCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(vlucht.get(0).getOrigin());
           
        return flights;
     
    }
    
    
    @FXML
    private void chooseCustomer(ActionEvent event) {
        ObservableList<Customer> customerSelected = tableViewCustomers.getSelectionModel().getSelectedItems();
        chosenCustomer = customerSelected.get(0);
        System.out.println("gesecteerde customer is " + chosenCustomer.getPassportNumber());
        tableViewEmission.setItems(getFlightsFromCustomer());
    }


    


    
    
}
