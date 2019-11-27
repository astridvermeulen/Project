/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import project.DB.DBAirport;
import static project.DB.DBAirport.getAirports;
import project.DB.DBException;
import project.LOGIC.Airport;
import project.LOGIC.DomainController;
import static project.LOGIC.DomainController.domainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    private DomainController model;

    @FXML
    private AnchorPane panelToUpdate;
    private ChoiceBox originCitychoice;
    private ChoiceBox destinationCitychoice;
    private ChoiceBox departureDaychoice;
    private ChoiceBox departureMonthchoice;
    private ChoiceBox departureYearchoice;
    private ChoiceBox amountOfPassengerschoice;
    private ChoiceBox sortBychoice;
    private CheckBox intermediateStopsAllowedcheck;
    private CheckBox intermediateStopsNotAllowedcheck;
    @FXML
    private Label fromLbl;
    @FXML
    private Label toLbl;
    @FXML
    private Label chooseDepartureDateLbl;
    @FXML
    private Label numberOfPassengersLbl;
    @FXML
    private Label intermediateStopsAllowedLbl;
    @FXML
    private Label sortByLbl;
    @FXML
    private ChoiceBox<?> originCityChoice;
    @FXML
    private ChoiceBox<?> destinationCityChoice;
    @FXML
    private ChoiceBox<?> departureDayChoice;
    @FXML
    private ChoiceBox<?> departureMonthChoice;
    @FXML
    private ChoiceBox<?> departureYearChoice;
    @FXML
    private ChoiceBox<?> amountOfPassengersChoice;
    @FXML
    private ChoiceBox<?> sortByChoice;
    @FXML
    private Button searchFlightMethode;
    @FXML
    private CheckBox intermediateStopsAllowedCheck;
    @FXML
    private CheckBox intermediateStopsNotAllowedCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance();
        addDataToChoiceBox();  
    }    

    @FXML
    private void handleYesCheckBox(){
        if(intermediateStopsAllowedcheck.isSelected()){
            intermediateStopsNotAllowedcheck.setSelected(false);
            
        }
    }
    @FXML
    private void handleNoCheckBox(){
        if(intermediateStopsNotAllowedcheck.isSelected()){
            intermediateStopsAllowedcheck.setSelected(false);
        }
    }
    
    
    @FXML
    private void searchFlight(ActionEvent event) {
        String originAirport = originCitychoice.getValue().toString();
        String destinationAirport = destinationCitychoice.getValue().toString();
        String departureDay = departureDaychoice.getValue().toString();
        String departureMonth = departureMonthchoice.getValue().toString();
        String departureYear = departureYearchoice.getValue().toString();
        int amountOfPassengers = (Integer) amountOfPassengerschoice.getValue();
        String sortBy = sortBychoice.getValue().toString();
        boolean intermediateStopsAllowed = intermediateStopsAllowedcheck.isSelected();
        boolean intermediateStopsNotAllowed = intermediateStopsNotAllowedcheck.isSelected();
        
     
        boolean toegestaan = false;
        if(intermediateStopsAllowedcheck.isSelected()) toegestaan = true;
        else if(intermediateStopsNotAllowedcheck.isSelected()) toegestaan = false;
            
        
        try {
            model.searchFlight(toegestaan, sortBy);
        } catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
      
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("overviewFlights.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    ObservableList list4 = FXCollections.observableArrayList();
    ObservableList list5 = FXCollections.observableArrayList();
    ObservableList list6 = FXCollections.observableArrayList();
    
    // voor elke choicebox moet een lijst geïmporteerd worden. 
    
    private void addDataToChoiceBox(){
      ArrayList<Airport> test = new ArrayList<>();
    
      try {
          test = getAirports();
          int size = test.size();
          for(int position = 0; position < size; position++)
              list1.add(test.get(position).getAirportName());
    
;
            } catch (DBException ex) {
        Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      for(int i=1; i<32; i++){
          list2.add(i);
      }
      for(int i=1; i<13; i++){
          list3.add(i);
      }
      for(int i=2019; i<2023; i++){
          list4.add(i);
      }
      for(int i=1; i<10; i++){
          list5.add(i);
      }
      list6.addAll("Duration", "Price", "Emission");
      
        originCitychoice.getItems().addAll(list1);
        destinationCitychoice.getItems().addAll(list1);
        departureDaychoice.getItems().addAll(list2);
        departureMonthchoice.getItems().addAll(list3);
        departureYearchoice.getItems().addAll(list4);
        amountOfPassengerschoice.getItems().addAll(list5);
        sortBychoice.getItems().addAll(list6);
        
        
    }
    
    
}
