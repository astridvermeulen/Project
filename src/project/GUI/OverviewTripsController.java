/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;
import static project.LOGIC.Flight.tripsOriginDestinations;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewTripsController implements Initializable {
    private DomainController model;
    
    @FXML
    private TableView<Flight> tableViewTrips;
    @FXML
    private ChoiceBox<?> yearChoiceBox;
    @FXML
    private Label chooseLbl;
    @FXML
    private TableColumn<Flight, String> originColumn;
    @FXML
    private TableColumn<Flight, String> destinationColumn;
    @FXML
    private TableColumn<Flight, Integer> timesBookedColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        originColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("origin"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        timesBookedColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("timesBooked"));
    
        tableViewTrips.setItems(getTrips());
    } 
    
    public ObservableList<Flight> getTrips(){
        ObservableList<Flight> trips = FXCollections.observableArrayList();
        
        try {
            for(Flight f: tripsOriginDestinations()){
                trips.add(f);
                
                return trips;
                
            }   } catch (DBException ex) {
            Logger.getLogger(OverviewTripsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return trips;
    }
    
}
