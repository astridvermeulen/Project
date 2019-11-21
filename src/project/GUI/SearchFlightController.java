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

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class SearchFlightController implements Initializable {

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
    private ChoiceBox<?> choiceSortBy;
    @FXML
    private CheckBox checkYes;
    @FXML
    private CheckBox checkNo;
    @FXML
    private ChoiceBox choiceAmountOfPassengers;
    
    ObservableList sortByList = FXCollections.observableArrayList("Duration", "Price");
    
    @FXML
    private void initialize(){
        choiceSortBy.setItems(sortByList);
    }
    
    @FXML
    private Label lblSlash;
    @FXML
    private Button btnSearchFlight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchFlight(ActionEvent event) {
    }
    
}
