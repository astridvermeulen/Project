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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
    private Button overviewCustomersBtn;
    @FXML
    private Button customerReportBtn;
    @FXML
    private Button salesReportBtn;
    @FXML
    private Button bookingSummaryBtn;
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance();    
    }
    

    // de volgende methodes updaten telkens een nieuwe panel zodat we naar een volgend "blad" gaan. 
    @FXML
    private void loadSearchFlight(ActionEvent event) {
        try {
            AnchorPane paneSearchFlight = (AnchorPane) FXMLLoader.load(getClass().getResource("searchFlight.fxml"));
            panelToUpdate.getChildren().setAll(paneSearchFlight);       
        } catch (IOException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
    }
    
    @FXML
    private void loadOverviewCustomers(ActionEvent event) {
        try {
            AnchorPane paneLoadOverviewCustomers = (AnchorPane) FXMLLoader.load(getClass().getResource("overviewCustomers.fxml"));
            panelToUpdate.getChildren().setAll(paneLoadOverviewCustomers);
        } catch (IOException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
    }

    @FXML
    private void loadCustomerReport(ActionEvent event) {
        try {
            AnchorPane paneLoadCustomerReport = (AnchorPane) FXMLLoader.load(getClass().getResource("customerReport.fxml"));
            panelToUpdate.getChildren().setAll(paneLoadCustomerReport);
        } catch (IOException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
    }

    
    @FXML
    private void loadSalesReport(ActionEvent event) { 
        try {
            AnchorPane paneSalesReport = (AnchorPane) FXMLLoader.load(getClass().getResource("salesReport.fxml"));
            panelToUpdate.getChildren().setAll(paneSalesReport);
        } catch (IOException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", ex.getMessage());
        }
    }

    @FXML
    private void loadBookingSummary(ActionEvent event) {  
        try {
            AnchorPane paneBookingSummary = (AnchorPane) FXMLLoader.load(getClass().getResource("bookingSummary.fxml"));
            panelToUpdate.getChildren().setAll(paneBookingSummary);
        } catch (IOException ex) {
            Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!", "No recent booking has been made!");
        }
    }
  
    
}
