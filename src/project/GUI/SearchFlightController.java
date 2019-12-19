/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.DB.DBAirport;
import project.DB.DBException;
import static project.LOGIC.Airport.airportsAlphabetic;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    private DomainController dc;
    private MakeBooking mb = MakeBooking.getInstance();
    private ArrayList<Flight> filteredFlights;  
   
    
    
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
    private ChoiceBox<?> numberOfPassengersChoice;
    @FXML
    private ChoiceBox<?> sortByChoice;
    @FXML
    private CheckBox intermediateStopsAllowedCheck;
    @FXML
    private CheckBox intermediateStopsNotAllowedCheck;
    @FXML
    private AnchorPane panelToUpdate;
    @FXML
    private Button bookBtn;
    @FXML
    private Button clearSelectedFlightsBtn;
    @FXML
    private Label fromLbl1;
    @FXML
    private Button searchFlightBtn;
    @FXML
    private Button clearSearchResultsBtn;
  
    
    public String getDatePicker() {
        return datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String getOriginAirport(){
        return originCityChoice.getValue().toString();
    }
    public String getDestinationAirport(){
        return destinationCityChoice.getValue().toString();
    }
    public String getSortBy(){
        return sortByChoice.getValue().toString();
    }
    public boolean getIntermediateStopsAllowed(){
        return intermediateStopsAllowedCheck.isSelected();
    }
    
    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = DomainController.getInstance();
        filteredFlights = new ArrayList();
        
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("airline"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("origin"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("durationGui"));
        departureDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        arrivalDayColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalTime"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("price"));
        emissionColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("emission"));
        numberOfStopoversColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("numberOfStops"));
        
        
        addDataToChoiceBox();
        
        mb.deleteSelectedFlights();
        
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
        try {
        if(dc.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker()).isEmpty()){
            alertBox.display("Oops!", "No matching flight(s) found.");
        }
        else{
            filteredFlights.addAll(dc.searchFlight(getIntermediateStopsAllowed(), getSortBy(), getOriginAirport(), getDestinationAirport(), getDatePicker()));
            tableView.setItems(getFlights());
            } 
        }catch (DBException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", "A database exception has been thrown.");
         }
        
    }
    
    // ObservableList om te kunnen weergeven in TableView.    
    public ObservableList<Flight> getFlights(){
        
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        for(Flight f: filteredFlights){
            flights.add(f);
        }
        return flights;
    }

    //Fills the choiceboxes with data 
    private void addDataToChoiceBox(){
        
        ObservableList list1 = FXCollections.observableArrayList();
        ObservableList list2 = FXCollections.observableArrayList();
        ObservableList list3 = FXCollections.observableArrayList();
        //Airports
        try {
            list1.addAll(airportsAlphabetic());
        
        } catch (DBException ex) {
            Logger.getLogger(DBAirport.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
        //Number of passengers 
        for(int i=1; i<6; i++){
        list2.add(i);
        }
        //Filter choices
        list3.addAll("Duration", "Price", "Emission");
      
        originCityChoice.getItems().addAll(list1);
        destinationCityChoice.getItems().addAll(list1);
        numberOfPassengersChoice.getItems().addAll(list2);
        sortByChoice.getItems().addAll(list3);
        
    }
    
    @FXML
    private void getFlightInfo(MouseEvent event) {
        if(event.getClickCount() == 2 && ( !tableView.getSelectionModel().getSelectedItems().isEmpty())){
            alertBox.display("Info", "Flight selected.");
            mb.flightInfo(tableView); 
        }
        tableView.getSelectionModel().clearSelection();
        
    }

    

    @FXML
    private void makeBooking(ActionEvent event) {
        if(!mb.getSelectedFlights().isEmpty()){
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("dataCustomer.fxml"));
            panelToUpdate.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(SearchFlightController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", "An IOException has been thrown");
        }
        }
        else{
            alertBox.display("Warning", "No flights selected");
        }
          
   
   }

    @FXML
    private void clearSelectedFlights(ActionEvent event) {
        mb.deleteSelectedFlights();
    }

    @FXML
    private void clearSearchResults(ActionEvent event) {
        filteredFlights.clear();
        tableView.getItems().clear();
    }
}
