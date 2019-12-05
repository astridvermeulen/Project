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
public class startWindowController implements Initializable {
    
    private DomainController model; // elke controller moet een link hebben naar de businesslaag via de instantievariabele model (domeinController is een singleton klasse). 
    @FXML
    private AnchorPane panelToUpdate;
    @FXML
    private Button searchFlightBtn;
    @FXML
    private Button overviewFlightsBtn;
    @FXML
    private Button DataCustomerBtn;
    @FXML
    private Button overviewCustomersBtn;
    @FXML
    private Button customerReportBtn;
    @FXML
    private Button salesReportBtn;
    
    
    // dit initaliseerd de controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance();    
    }
    
//
    // de volgende methodes updaten telkens een nieuwe panel zodat we naar een volgend "blad" gaan. 

    @FXML
    private void loadSearchFlight(ActionEvent event) {
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
      
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("project/GUI/overviewFlights.fxml"));
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
  
    
}
