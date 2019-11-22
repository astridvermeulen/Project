/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import project.LOGIC.DomeinController;
import static project.LOGIC.DomeinController.domeinController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {
    
    private DomeinController model; // elke controller moet een link hebben naar de businesslaag via de instantievariabele model (domeinController is een singleton klasse). 
    
    // dit initaliseerd de controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domeinController.getInstance();
    }    


    // dit wordt automatisch door scene builder aangemaakt => niet in aanpassen!
    @FXML
    private Label lblNameAirline;
    @FXML
    private Label lblFrom;
    @FXML
    private Label lblTo;
    @FXML
    private Label lblChooseDepartureDate;
    @FXML
    private Label lblAmountOfPassengers;
    @FXML
    private Label lblIntermediateAllowed;
    @FXML
    private Label lblSortBy;
    @FXML
    private Label lblSlash;
    @FXML
    private ChoiceBox<?> choiceOriginCity;
    @FXML
    private ChoiceBox<?> choiceDestinationCity;
    @FXML
    private ChoiceBox<?> choiceDayDeparture;
    @FXML
    private ChoiceBox<?> choiceMonthDeparture;
    @FXML
    private ChoiceBox<?> choiceYearDeparture;
    @FXML
    private ChoiceBox choiceAmountOfPassengers;
    @FXML
    private ChoiceBox<?> choiceSortBy;
    @FXML
    private Button btnSearchFlight;
    @FXML
    private CheckBox checkIntermediateStopsAllowed;
    @FXML
    private CheckBox checkIntermediateStopsNotAllowed;
    
    
    
    ObservableList sortByList = FXCollections.observableArrayList("Duration", "Price", "Eco friendly");
    
    private void initialize(){
        // choiceSortBy.setValue("Duration"); Er wordt een error gegeven, je moet dit doen volgens dit filmpje: https://www.youtube.com/watch?v=MUw7MHIibBc 
        choiceSortBy.setItems(sortByList);
    }
    

    @FXML
    private void searchFlight(ActionEvent event) {
        // deze methode voert een actie uit wanneer de gebruiker op de searchFlight knop drukt. 
        // deze methode moet uitgeschreven worden in de domeinController. 
        // beetje hetzelfde als addStudent lesopname algo&data 1 uur 15 sec. 
    }
    
}
