/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class DataCustomerController implements Initializable {
private DomainController model;

    @FXML
    private Label enterPersonalDatalbl;
    @FXML
    private Label firstNamelbl;
    @FXML
    private Label lastNamelbl;
    @FXML
    private Label passportIDlbl;
    @FXML
    private TextField firstNametxtfield;
    @FXML
    private TextField lastNametxtfield;
    @FXML
    private TextField passportIDtxtfield;
    
    String firstName = firstNametxtfield.getText();
    String lastName = lastNametxtfield.getText();
    String passportID = passportIDtxtfield.getText();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
