/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
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
import project.LOGIC.Customer;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;
import project.LOGIC.FlightLeg;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class BookingSummaryController implements Initializable {
    private DomainController model;
    private Flight selectedFlight;
    private MakeBooking mb = MakeBooking.getInstance();
    
    @FXML
    private TableView<Customer> passengersTableView;
    @FXML
    private TableView<Flight> flightsTableView;
    @FXML
    private TableView<FlightLeg> legTableView;
    
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
    private TableColumn<Flight, String> flightNumberColumn;
    @FXML
    private TableColumn<Flight, String> flightAirlineColumn;
    @FXML
    private TableColumn<Flight, String> flightOriginColumn;
    @FXML
    private TableColumn<Flight, String> flightDestinationColumn;
    @FXML
    private TableColumn<Flight, Duration> flightDurationColumn;
    @FXML
    private TableColumn<Flight, String> flightDepartureDayColumn;
    @FXML
    private TableColumn<Flight, String> flightDepartureTimeColumn;
    @FXML
    private TableColumn<Flight, String> flightArrivalDayColumn;
    @FXML
    private TableColumn<Flight, String> flightArrivalTimeColumn;
    @FXML
    private TableColumn<Flight, Double> flightPriceColumn;
    
    @FXML
    private TableColumn<FlightLeg, Integer> legNumberColumn;
    @FXML
    private TableColumn<FlightLeg, String> legOriginColumn;
    @FXML
    private TableColumn<FlightLeg, String> legDestinationColumn;
    @FXML
    private TableColumn<FlightLeg, String> legDepartureDayColumn;
    @FXML
    private TableColumn<FlightLeg, String> legDepartureTimeColumn;
    @FXML
    private TableColumn<FlightLeg, String> legArrivalDateColumn;
    @FXML
    private TableColumn<FlightLeg, String> legArrivalTimeColumn;
    @FXML
    private TableColumn<FlightLeg, Duration> legDurationColumn;

    private Label totalAmountToInputLbl;
    @FXML
    private Button showLegsBtn;
    @FXML
    private Label serviceFeeToInputLbl;
    @FXML
    private Label promotionToInputLbl;
    @FXML
    private Label netPriceToInputLbl;
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
    
        flightAirlineColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("airline"));
        flightOriginColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("origin"));
        flightDestinationColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        flightDurationColumn.setCellValueFactory(new PropertyValueFactory<Flight,Duration>("duration"));
        flightDepartureDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureDate"));
        flightDepartureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        flightArrivalDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalDate"));
        flightArrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalTime"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        flightPriceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
 
        legNumberColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,Integer>("legNumber"));
        legOriginColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legOrigin"));
        legDestinationColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legDestination"));
        legDepartureDayColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legDuration"));
        legDepartureTimeColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legDepartureDate"));
        legArrivalDateColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legDepartureTime"));
        legArrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,String>("legArrivalDate"));
        legDurationColumn.setCellValueFactory(new PropertyValueFactory<FlightLeg,Duration>("legArrivalTime"));
       
        passengersTableView.setItems(getPassengers());
        flightsTableView.setItems(getFlights());
        
        serviceFeeToInputLbl.setText("€ " + mb.returnServiceFee());
        promotionToInputLbl.setText("€ " + mb.returnPromotion());
        netPriceToInputLbl.setText("€ " + mb.returnNetPrice().toString());
        
    } 
     
    
    public ObservableList<Customer> getPassengers(){
        ObservableList<Customer> passengers = FXCollections.observableArrayList();
        for(Customer c: mb.returnPassengers()){
            passengers.add(c);
        }
        return passengers;
    }
    
    public ObservableList<Flight> getFlights(){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        for(Flight f: mb.returnFlights()){
            flights.add(f);
        }
        return flights;
    }
    
    @FXML
    private void showLegs(ActionEvent event) {
        ObservableList<Flight> flightSelected = flightsTableView.getSelectionModel().getSelectedItems();
        selectedFlight = flightSelected.get(0);
        
        legTableView.setItems(getLegsFromFlight());
        
        ObservableList<FlightLeg> list = legTableView.getItems();
        if(list.isEmpty()){
            alertBox.display("Warning!", "There are no flight legs for this booking.");
        }
    }
    
    public ObservableList<FlightLeg> getLegsFromFlight(){
        ObservableList<FlightLeg> legs = FXCollections.observableArrayList();
        for(FlightLeg l: selectedFlight.getFlightLegs()){
            legs.add(l);
        }
        
        return legs;
    }

    private void showNetPrice(ActionEvent event) {
        totalAmountToInputLbl.setText("€ " + mb.returnNetPrice().toString());
    }
    
}
