/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.LOGIC.Customer;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class BookingSummaryController implements Initializable {
    private DomainController model;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> passportNumberColumn;
    @FXML
    private TableColumn<Customer, String> homeCountryColumn;
    @FXML
    private TableColumn<Customer, String> bithDateColumn;
    @FXML
    private TableColumn<Flight, String> airlineColumn;
    @FXML
    private TableColumn<Flight, String> originAirportColumn;
    @FXML
    private TableColumn<Flight, String> destinationAirportColumn;
    @FXML
    private TableColumn<Flight, Duration> durationColumn;
    @FXML
    private TableColumn<Flight, String> departureDayColumn;
    @FXML
    private TableColumn<Flight, String> departureTimeColumn;
    @FXML
    private TableColumn<Flight, String> arrivalDayColumn;
    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;
    @FXML
    private TableColumn<Flight, String> flightNumberColumn;
    @FXML
    private TableColumn<Flight, Double> priceColumn;
    @FXML
    private TableColumn<Flight, Integer> numberOfFlightLegsColumn;
    @FXML
    private Label priceFlightToInputLbl;
    @FXML
    private Label promotionToInputLbl;
    @FXML
    private Label serviceFeeToInputLbl;
    @FXML
    private Label totalAmountToInputLbl;
    @FXML
    private TableView<Customer> passengersTableView;
    @FXML
    private TableView<Flight> flightsTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("passportNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        homeCountryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("homeCountry"));
        bithDateColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("birthDate"));      
    
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("airline"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("origin"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Flight,Duration>("duration"));
        departureDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        arrivalDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalTime"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
        numberOfFlightLegsColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("getFlightLegs()"));
        
    }    

    
}
