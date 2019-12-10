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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;
import project.LOGIC.Traject;
import static project.LOGIC.Traject.topPopularTrips;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewTripsController implements Initializable {
    private DomainController model;
    
    @FXML
    private TableView<Traject> tableViewTrips;
    @FXML
    private ChoiceBox<String> yearChoiceBox;
    @FXML
    private Label chooseLbl;
    @FXML
    private TableColumn<Traject, String> originColumn;
    @FXML
    private TableColumn<Traject, String> destinationColumn;
    @FXML
    private TableColumn<Traject, Integer> timesBookedColumn;
    @FXML
    private Button showTripsBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        addDataToChoiceBox();
        
        originColumn.setCellValueFactory(new PropertyValueFactory<Traject, String>("origin"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Traject, String>("destination"));
        timesBookedColumn.setCellValueFactory(new PropertyValueFactory<Traject, Integer>("timesBooked"));
        
    } 
    
    private void addDataToChoiceBox(){
        
        ObservableList<String> list = FXCollections.observableArrayList();
    
        for(Integer i=2019; i<2025; i++){
          list.add(i.toString());
        }
        
        yearChoiceBox.getItems().addAll(list);
    }
    
    public String getYear(){
        return yearChoiceBox.getValue();
    }
    
    public ObservableList<Traject> getTrips(){
        ObservableList<Traject> trips = FXCollections.observableArrayList();
        
        try {
            for(Traject t: topPopularTrips(getYear())){
                trips.add(t);
                
            }   
        } catch (DBException ex) {
            Logger.getLogger(OverviewTripsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return trips;
    }

    @FXML
    private void showTrips(ActionEvent event) {
        tableViewTrips.setItems(getTrips());
    }
    
}
