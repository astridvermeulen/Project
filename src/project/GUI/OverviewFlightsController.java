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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<?, ?> airlineColumn;
    @FXML
    private TableColumn<?, ?> flightNumberColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> emissionColumn;
    @FXML
    private TableColumn<?, ?> flightLegColumn;
    @FXML
    private TableColumn<?, ?> originAirportColumn;
    @FXML
    private TableColumn<?, ?> destinationAirportColumn;
    @FXML
    private TableColumn<?, ?> departureTimeColumn;
    @FXML
    private TableColumn<?, ?> destinationTimeColumn;
    @FXML
    private TableColumn<?, ?> durationColumn;
    
    private TableView<Flight> flightsTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance(); 
        //flightsTableView.setItems(getFlights());
        //we zetten allemaal flights in ons programma door de methode searchFlight op de roepen en nu halen we de waarden eruit
        
        
    }    
    
    SearchFlightController zoekVlucht = new SearchFlightController();
    
    public ObservableList<Flight> getFlights(){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        
        for(Flight f: zoekVlucht.getFilteredFlights()){
                flights.add(f);
            }
        
        return flights;
    }
    
    
    
}
