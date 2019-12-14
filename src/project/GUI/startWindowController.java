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
    
    private AnchorPane paneSearchFlight;
    private AnchorPane paneLoadOverviewCustomers;
     private AnchorPane paneLoadCustomerReport;
     private AnchorPane paneSalesReport;
     private AnchorPane paneBookingSummary;
     
    // dit initaliseert de controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance();    
        try{
            paneSearchFlight = (AnchorPane) FXMLLoader.load(getClass().getResource("searchFlight.fxml"));
            paneLoadOverviewCustomers = (AnchorPane) FXMLLoader.load(getClass().getResource("overviewCustomers.fxml"));
            paneLoadCustomerReport = (AnchorPane) FXMLLoader.load(getClass().getResource("customerReport.fxml"));
        paneSalesReport = (AnchorPane) FXMLLoader.load(getClass().getResource("salesReport.fxml"));
        paneBookingSummary = (AnchorPane) FXMLLoader.load(getClass().getResource("bookingSummary.fxml"));
        }catch (IOException ex) {
        Logger.getLogger(startWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    // de volgende methodes updaten telkens een nieuwe panel zodat we naar een volgend "blad" gaan. 
    @FXML
    private void loadSearchFlight(ActionEvent event) {
            panelToUpdate.getChildren().setAll(paneSearchFlight);       
    }
    
    @FXML
    private void loadOverviewCustomers(ActionEvent event) {
        panelToUpdate.getChildren().setAll(paneLoadOverviewCustomers);
    }

    @FXML
    private void loadCustomerReport(ActionEvent event) {
            panelToUpdate.getChildren().setAll(paneLoadCustomerReport);
    }

    
    @FXML
    private void loadSalesReport(ActionEvent event) { 
            panelToUpdate.getChildren().setAll(paneSalesReport);
    }

    @FXML
    private void loadBookingSummary(ActionEvent event) {
            
            panelToUpdate.getChildren().setAll(paneBookingSummary);
    }
  
    
}
