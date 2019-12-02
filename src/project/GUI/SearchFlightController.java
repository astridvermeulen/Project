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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import project.DB.DBAirport;
import static project.DB.DBAirport.getAirports;
import project.DB.DBException;
import project.LOGIC.Airport;
import project.LOGIC.DomainController;
import static project.LOGIC.DomainController.domainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    private DomainController model;
    private ArrayList<Flight> filteredFlights;
    public String Emission;
    @FXML
    private DatePicker datePicker;

    public SearchFlightController(){
        this.filteredFlights = getFilteredFlights();
    }
    public String getEmission() {
        return Emission;
    }

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

    public DatePicker getDatePicker() {
        return datePicker;
    }

    
    public String getOriginAirport(){
        return originCityChoice.getValue().toString();
    }
    public String getDestinationAirport(){
        return destinationCityChoice.getValue().toString();
    }
    
    
    public int getAmountOfPassengers(){
        return (Integer) amountOfPassengersChoice.getValue();
    }
    public String getSortBy(){
        return sortByChoice.getValue().toString();
    }
    public boolean getIntermediateStopsAllowed(){
        return intermediateStopsAllowedCheck.isSelected();
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    
    public ArrayList<Flight> getFilteredFlights() {
        return filteredFlights;
    }
    
    @FXML
    private void searchFlight(ActionEvent event) {

        try {
            filteredFlights = model.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker().toString() );
        } catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    // voor elke choicebox moet een lijst geïmporteerd worden. 
    
    private void addDataToChoiceBox(){
        
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    ObservableList list4 = FXCollections.observableArrayList();
    ObservableList list5 = FXCollections.observableArrayList();
    ObservableList list6 = FXCollections.observableArrayList();
    
    ArrayList<Airport> test = new ArrayList<>();
    
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
        //departureDayChoice.getItems().addAll(list2);
        //departureMonthChoice.getItems().addAll(list3);
        //departureYearChoice.getItems().addAll(list4);
        amountOfPassengersChoice.getItems().addAll(list5);
        sortByChoice.getItems().addAll(list6);
        
    }
    public static void main(String[] args) throws DBException {
       ArrayList<Flight> list = new ArrayList<>();
        
       SearchFlightController object = new SearchFlightController();
        System.out.println(object.model.searchFlight(Boolean.TRUE, "Emission", "Amsterdam-Schiphol", "Zaventem" , "2019-12-19"));
       
    }
    
    
}
