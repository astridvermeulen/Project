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
import javafx.scene.control.TableColumn;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewCustomersController implements Initializable {
private DomainController model;
    @FXML
    private TableColumn<?, ?> firstNamecolumn;
    @FXML
    private TableColumn<?, ?> lastNamecolumn;
    @FXML
    private TableColumn<?, ?> passportNumbercolumn;
    @FXML
    private TableColumn<?, ?> homeCountrycolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
