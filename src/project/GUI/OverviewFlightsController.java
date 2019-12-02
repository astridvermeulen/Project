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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.DomainController;
import static project.LOGIC.DomainController.domainController;
import project.LOGIC.Flight;
import project.LOGIC.FlightLeg;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewFlightsController implements Initializable {
    
    private DomainController model;

    @FXML
    private TableColumn<Flight, String> airlineColumn;
    @FXML
    private TableColumn<Flight, String> flightNumberColumn;
    @FXML
    private TableColumn<Flight, Double> priceColumn;
    @FXML
    private TableColumn<Flight, String> originAirportColumn;
    @FXML
    private TableColumn<Flight, String> destinationAirportColumn;
    @FXML
    private TableColumn<Flight, LocalTime> departureTimeColumn;
    @FXML
    private TableColumn<Flight, Duration> durationColumn;
    @FXML
    private TableColumn<Flight, LocalDate> departureDayColumn;
    @FXML
    private TableColumn<Flight, LocalDate> arrivalDayColumn;
    @FXML
    private TableColumn<Flight, LocalTime> arrivalTimeColumn;
    @FXML
    private TableColumn<Flight, Integer> numberOfFlightLegsColumn;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableView<Flight> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance(); 
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("airline"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("origin"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Flight,Duration>("duration"));
        departureDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,LocalDate>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,LocalTime>("departureTime"));
        arrivalDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,LocalDate>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,LocalTime>("arrivalTime"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
        numberOfFlightLegsColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("getFlightLegs()"));
        
        
        tableView.setItems(getFlights());
        
        
    }    
    
    SearchFlightController zoekVlucht = new SearchFlightController();
    
    public ObservableList<Flight> getFlights(){
        
        SearchFlightController object = new SearchFlightController();
        
        ObservableList<Flight> flights = FXCollections.observableArrayList(object.getFilteredFlights());
        return flights;
        
        
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("hallo ");
        SearchFlightController object = new SearchFlightController();
        System.out.println(object.getFilteredFlights());
        System.out.println("hallo");
    }
    
    
}
