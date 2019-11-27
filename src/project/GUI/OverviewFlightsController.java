/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import project.LOGIC.DomainController;
import static project.LOGIC.DomainController.domainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewFlightsController implements Initializable {
    
    private DomainController model;

    @FXML
    private TableColumn<?, ?> airlineColumn;
    @FXML
    private TableColumn<?, ?> flightNumberColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> emissionColumn;
    @FXML
    private TableColumn<?, ?> flightLegColumn;
    @FXML
    private TableColumn<?, ?> originAirportColumn;
    @FXML
    private TableColumn<?, ?> destinationAirportColumn;
    @FXML
    private TableColumn<?, ?> departureTimeColumn;
    @FXML
    private TableColumn<?, ?> destinationTimeColumn;
    @FXML
    private TableColumn<?, ?> durationColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=domainController.getInstance(); 
    }    
    
}
