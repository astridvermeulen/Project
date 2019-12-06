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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewTripsController implements Initializable {
    private DomainController model;
    
    @FXML
    private TableView<?> tableViewTrips;
    @FXML
    private ChoiceBox<?> yearChoiceBox;
    @FXML
    private Label chooseLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
    }    
    
}
