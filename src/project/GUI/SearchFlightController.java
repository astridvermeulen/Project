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
    @FXML
    private AnchorPane panelToUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test");
        model=domainController.getInstance();
        addDataToChoiceBox();  
    }    

    @FXML
    private void handleYesCheckBox(){
        if(intermediateStopsAllowedCheck.isSelected()){
            intermediateStopsNotAllowedCheck.setSelected(false);
            
        }
    }
    @FXML
    private void handleNoCheckBox(){
        if(intermediateStopsNotAllowedCheck.isSelected()){
            intermediateStopsAllowedCheck.setSelected(false);
        }
    }
    
    
    @FXML
    private void searchFlight(ActionEvent event) {
        String originAirport = originCityChoice.getValue().toString();
        String destinationAirport = destinationCityChoice.getValue().toString();
        String departureDay = departureDayChoice.getValue().toString();
        String departureMonth = departureMonthChoice.getValue().toString();
        String departureYear = departureYearChoice.getValue().toString();
        int amountOfPassengers = (Integer) amountOfPassengersChoice.getValue();
        String sortBy = sortByChoice.getValue().toString();
        boolean intermediateStopsAllowed = intermediateStopsAllowedCheck.isSelected();
        boolean intermediateStopsNotAllowed = intermediateStopsNotAllowedCheck.isSelected();
        
     
        boolean toegestaan = false;
        if(intermediateStopsAllowedCheck.isSelected()) toegestaan = true;
        else if(intermediateStopsNotAllowedCheck.isSelected()) toegestaan = false;
            
        
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
      ObservableList list1 = FXCollections.observableArrayList(new ArrayList<String>());
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    ObservableList list4 = FXCollections.observableArrayList();
    ObservableList list5 = FXCollections.observableArrayList();
    ObservableList list6 = FXCollections.observableArrayList();
    
      try {
          test = getAirports();
          System.out.println(test.size());
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
      
        originCityChoice.getItems().addAll(list1);
        destinationCityChoice.getItems().addAll(list1);
        departureDayChoice.getItems().addAll(list2);
        departureMonthChoice.getItems().addAll(list3);
        departureYearChoice.getItems().addAll(list4);
        amountOfPassengersChoice.getItems().addAll(list5);
        sortByChoice.getItems().addAll(list6);
        
        
    }
    
    
}
