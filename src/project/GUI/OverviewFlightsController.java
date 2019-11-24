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

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewFlightsController implements Initializable {

    @FXML
    private TableColumn<?, ?> airlinecolumn;
    @FXML
    private TableColumn<?, ?> originAirportcolumn;
    @FXML
    private TableColumn<?, ?> destinationAirportcolumn;
    @FXML
    private TableColumn<?, ?> departureTimecolumn;
    @FXML
    private TableColumn<?, ?> destinationTimecolumn;
    @FXML
    private TableColumn<?, ?> durationcolumn;
    @FXML
    private TableColumn<?, ?> flightNumbercolumn;
    @FXML
    private TableColumn<?, ?> pricecolumn;
    @FXML
    private TableColumn<?, ?> co2Emissioncolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
