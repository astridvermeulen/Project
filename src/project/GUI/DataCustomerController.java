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
    private TextField firstNameTxtfield;
@FXML
    private TextField lastNameTxtfield;
@FXML
    private TextField passportIDTxtfield;
    
    String firstName = firstNameTxtfield.getText();
    String lastName = lastNameTxtfield.getText();
    String passportID = passportIDTxtfield.getText();
    
    @FXML
    private Label enterPersonalDataLbl;
    @FXML
    private Label firstNameLbl;
    @FXML
    private Label lastNameLbl;
    @FXML
    private Label passportIDLbl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
