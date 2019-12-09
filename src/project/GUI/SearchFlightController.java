/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.DB.DBAirport;
import project.DB.DBException;
import static project.LOGIC.Airline.airportsAlphabetic;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    private DomainController model;
    private ArrayList<Flight> filteredFlights = new ArrayList<>();
    private Flight flight;
    
    
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
    @FXML
    private Button displayFlightsBtn;
    @FXML
    private Button clearFlightsBtn;
    @FXML
    private Button bookSelectedFlightsBtn;
      
    private ObservableList<Flight> flightsSelected;
    private ArrayList<Flight> vluchtenGeselecteerd;   
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
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        
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
            filteredFlights.addAll(model.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker()));
        }  catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        tableView.setItems(getFlights());
        
        try {
            filteredFlights.addAll(model.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker()));
            System.out.println(filteredFlights.toString());
        } catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
        
        
    
    
    public ObservableList<Flight> getFlights(){
        
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        
        for(Flight f: filteredFlights){
            flights.add(f);
        }
        
        return flights;
    }

    
    // voor elke choicebox moet een lijst geïmporteerd worden. 
     private void addDataToChoiceBox(){
        
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    
    ArrayList<String> test = new ArrayList<>();
    
      try {
          test = airportsAlphabetic();
          int size = test.size();
          for(int position = 0; position < size; position++)
              list1.add(test.get(position));
    

            } catch (DBException ex) {
        Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      for(int i=1; i<5; i++){
          list2.add(i);
      }

      list3.addAll("Duration", "Price", "Emission");
      
        originCityChoice.getItems().addAll(list1);
        destinationCityChoice.getItems().addAll(list1);
        amountOfPassengersChoice.getItems().addAll(list2);
        sortByChoice.getItems().addAll(list3);
        
    }
    
    @FXML
    private void getFlightInfo(MouseEvent event) {
        try {
            Flight vlucht = new Flight(tableView.getSelectionModel().getSelectedItem().getOrigin(),tableView.getSelectionModel().getSelectedItem().getDestination(),
                    tableView.getSelectionModel().getSelectedItem().getDepartureDate(), tableView.getSelectionModel().getSelectedItem().getDepartureTime(),
                    tableView.getSelectionModel().getSelectedItem().getArrivalDate(),tableView.getSelectionModel().getSelectedItem().getArrivalTime(),
                    tableView.getSelectionModel().getSelectedItem().getFlightNumber(),tableView.getSelectionModel().getSelectedItem().getPrice());
            //test
            System.out.println(vlucht.getAirline());
            
        } catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
            
            public static void main(String[] args) throws DBException {
       
        
       SearchFlightController object = new SearchFlightController();
        
       
    }
   
    
            

    @FXML
    private void displayFllights(ActionEvent event) {
        
        tableView.setItems(getFlights());
        
    }

    @FXML
    private void clearFlights(ActionEvent event) {
        getFlights().clear();
    }

    @FXML
    private void bookSelectedFlights(ActionEvent event) {
        flightsSelected = tableView.getSelectionModel().getSelectedItems();
        
        int size = flightsSelected.size();
        for(int i=0; i<size; i++){
            vluchtenGeselecteerd.add(flightsSelected.get(i));
            System.out.println("geselecteerde vluchten: " + vluchtenGeselecteerd.get(i));
        }
        
        

    }

    
    
    
    
}
