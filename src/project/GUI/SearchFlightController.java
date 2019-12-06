/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    
    // Overview of flights matching the criteria
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
    private TableView<Flight> tableView;

    
    
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label fromLbl;
    @FXML
    private Label toLbl;
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
    @FXML
    private ScrollPane scrollPane;
    
    
    //Getters
    public SearchFlightController() {
    }

    public String getDatePicker() {
        return datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
    public ArrayList<Flight> getFilteredFlights() {
        return filteredFlights;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        addDataToChoiceBox();
        
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

    
    //Search flights
    @FXML
    private void searchFlight(ActionEvent event) {

        try {
            filteredFlights = model.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker());
        }  catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        tableView.setItems(getFlights());
        
    }
    public ObservableList<Flight> getFlights(){
        
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        
        for(Flight f: getFilteredFlights()){
            flights.add(f);
        }
        
        return flights;
    }

    
    // voor elke choicebox moet een lijst geïmporteerd worden. 
     private void addDataToChoiceBox(){
        
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    
    ArrayList<Airport> test = new ArrayList<>();
    
      try {
          test = getAirports();
          System.out.println(test.size());
          int size = test.size();
          for(int position = 0; position < size; position++)
              list1.add(test.get(position).getAirportName());
    

            } catch (DBException ex) {
        Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      for(int i=1; i<5; i++){
          list2.add(i);
      }
      for(int i=1; i<13; i++){
          list3.add(i);
      }
      
      list3.addAll("Duration", "Price", "Emission");
      
        originCityChoice.getItems().addAll(list1);
        destinationCityChoice.getItems().addAll(list1);
        amountOfPassengersChoice.getItems().addAll(list2);
        sortByChoice.getItems().addAll(list3);
        
    }
    public static void main(String[] args) throws DBException {
       
        
       SearchFlightController object = new SearchFlightController();
        
       
    }
    
    
}
