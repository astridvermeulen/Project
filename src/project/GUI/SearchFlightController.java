/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
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

import static project.LOGIC.Airport.airportsAlphabetic;
import project.LOGIC.Booking;

import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    private DomainController model;
    private MakeBooking mb = MakeBooking.getInstance();
    private ArrayList<Flight> filteredFlights = new ArrayList();  
    private Booking booking;
    
   
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
    private TableColumn<Flight, String> departureTimeColumn;
    @FXML
    private TableColumn<Flight, String> durationColumn;
    @FXML
    private TableColumn<Flight, String> departureDayColumn;
    @FXML
    private TableColumn<Flight, String> arrivalDayColumn;
    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;
    @FXML
    private TableColumn<Flight, Double> emissionColumn;
    @FXML
    private TableColumn<Flight, Integer> numberOfStopoversColumn;
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
    private Button clearFlightsBtn;
    @FXML
    private Button bookBtn;
    @FXML
    private Button clearSelectedFlightsBtn;
   
    
    
    
    
    //Getters  
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
        model = DomainController.getInstance();
        addDataToChoiceBox();
        
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("airline"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("origin"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("duration"));
        departureDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        arrivalDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalTime"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
        emissionColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("emission"));
        numberOfStopoversColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("numberOfStops"));
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        mb.deleteSelectedFlights();
        filteredFlights.clear();
       
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
            System.out.println(filteredFlights.toString());
            tableView.setItems(getFlights());
            System.out.println(filteredFlights.get(0).getNumberOfStops());
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
      
      for(int i=1; i<6; i++){
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
        if(event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItems() != null)){
           mb.flightInfo(tableView); 
        }
        
    }

    @FXML
    private void clearFlights(ActionEvent event) {
        filteredFlights.clear();
        tableView.getItems().clear();
    }

    @FXML
    private void makeBooking(ActionEvent event) {
        
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("dataCustomer.fxml"));
            panelToUpdate.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
   
   }

    @FXML
    private void clearSelectedFlights(ActionEvent event) {
        mb.deleteSelectedFlights();
    }
}
