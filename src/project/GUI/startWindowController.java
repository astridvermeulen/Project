/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.DB.DBAirport;
import project.DB.DBException;
import project.LOGIC.DomeinController;
import static project.LOGIC.DomeinController.domeinController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class startWindowController implements Initializable {
    
    private DomeinController model; // elke controller moet een link hebben naar de businesslaag via de instantievariabele model (domeinController is een singleton klasse). 
    
    
    
    @FXML
    private Button startWindowBtn;
    @FXML
    private Button overviewFlightsbtn;
    @FXML
    private AnchorPane panelToUpdate;
    @FXML
    private Button DataCustomerbtn;
    @FXML
    private Button overviewCustomersbtn;
    @FXML
    private Button customerReportbtn;
    @FXML
    private Button salesReportbtn;
    @FXML
    private Label fromlbl;
    @FXML
    private Label tolbl;
    @FXML
    private Label chooseDepartureDatelbl;
    @FXML
    private Label intermediateStopsAllowedlbl;
    @FXML
    private Label sortBylbl;
    @FXML
    private ChoiceBox originCitychoice;
    @FXML
    private ChoiceBox destinationCitychoice;
    @FXML
    private ChoiceBox departureDaychoice;
    @FXML
    private ChoiceBox departureMonthchoice;
    @FXML
    private ChoiceBox departureYearchoice;
    @FXML
    private ChoiceBox amountOfPassengerschoice;
    @FXML
    private ChoiceBox sortBychoice;
    @FXML
    private Button searchFlightsbtn;
    @FXML
    private CheckBox intermediateStopsAllowedcheck;
    @FXML
    private CheckBox intermediateStopsNotAllowedcheck;
    @FXML
    private Label numberOfPassengerslbl;
    
    // dit initaliseerd de controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domeinController.getInstance();
        
        try {
            originCitychoice.getItems().setAll(DBAirport.getAirports());
            originCitychoice.getItems().addAll("Zaventem");
            originCitychoice.setValue("Zaventem");
        } catch (DBException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }     

    // de volgende methodes updaten telkens een nieuwe panel zodat we naar een volgend "blad" gaan. 
    @FXML
    private void loadStartWindow(ActionEvent event) {
        try {
      
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("searchFlight.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void loadOverviewFlights(ActionEvent event) {
        try {
      
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("overviewFlights.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void loadDataCustomer(ActionEvent event) {
        try {
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("dataCustomer.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void loadOverviewCustomers(ActionEvent event) {
        try {
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("overviewCustomers.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void loadCustomerReport(ActionEvent event) {
        try {
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("customerReport.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    @FXML
    private void loadSalesReport(ActionEvent event) {
        try {
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("salesReport.fxml"));
            panelToUpdate.getChildren().setAll(pane);
      
      
        } catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void searchFlight(ActionEvent event) {
        loadOverviewFlights(event);
    }

    @FXML
    private void showValues(MouseEvent event) {
       
    }
    
  
    
}
