/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import project.DB.DBException;
import project.LOGIC.Customer;
import static project.LOGIC.Customer.saveCustomer;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author Lars
 */
public class DataCustomerController implements Initializable {
    private DomainController model;

    
   
    @FXML
    private HBox C1Pane; 
    @FXML
    private Button nextPassenger1Btn;
    @FXML
    private HBox C2Pane;
    @FXML
    private Button nextPassenger2Btn;
    @FXML
    private HBox C3Pane;
    @FXML
    private Button nextPassenger3Btn;
    @FXML
    private HBox C4Pane;
    @FXML
    private Button nextPassenger4Btn;
    @FXML
    private HBox C5Pane;
    @FXML
    private Button submitC1Btn;
    @FXML
    private TextField firstNameC1TxtField;
    @FXML
    private TextField lastNameC1TxtField;
    @FXML
    private TextField passportIDC1TxtField;
    @FXML
    private TextField firstNameC2TxtField;
    @FXML
    private TextField lastNameC2TxtField;
    @FXML
    private TextField passportIDC2TxtField;
    @FXML
    private Button submitC2Btn;
    @FXML
    private TextField firstNameC3TxtField;
    @FXML
    private TextField lastNameC3TxtField;
    @FXML
    private TextField passportIDC3TxtField;
    @FXML
    private Button submitC3Btn;
    @FXML
    private TextField firstNameC4TxtField;
    @FXML
    private TextField lastNameC4TxtField;
    @FXML
    private TextField passportIDC4TxtField;
    @FXML
    private Button submitC4Btn;
    @FXML
    private TextField firstNameC5TxtField;
    @FXML
    private TextField lastNameC5TxtField;
    @FXML
    private TextField passportIDC5TxtField;
    @FXML
    private Button submitC5Btn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = DomainController.getInstance();
        C2Pane.setVisible(false);
        C3Pane.setVisible(false);
        C4Pane.setVisible(false);
        C5Pane.setVisible(false);
    }   

    @FXML
    private void showC2Pane(ActionEvent event) {
        C2Pane.setVisible(true);
    }

    @FXML
    private void showC3Pane(ActionEvent event) {
        C3Pane.setVisible(true);
    }

    @FXML
    private void showC4Pane(ActionEvent event) {
        C4Pane.setVisible(true);
    }

    @FXML
    private void showC5Pane(ActionEvent event) {
        C5Pane.setVisible(true);
    }

    @FXML
    private void saveCustomer1(ActionEvent event) {
        System.out.println("test");
        Customer klant = new Customer(passportIDC1TxtField.getText(), firstNameC1TxtField.getText(), lastNameC1TxtField.getText());

        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveCustomer2(ActionEvent event) {
        Customer klant = new Customer(passportIDC2TxtField.getText(), firstNameC2TxtField.getText(), lastNameC2TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void saveCustomer3(ActionEvent event) {
        Customer klant = new Customer(passportIDC3TxtField.getText(), firstNameC3TxtField.getText(), lastNameC3TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void saveCustomer4(ActionEvent event) {
        Customer klant = new Customer(passportIDC4TxtField.getText(), firstNameC4TxtField.getText(), lastNameC4TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }

    @FXML
    private void saveCustomer5(ActionEvent event) {
        Customer klant = new Customer(passportIDC5TxtField.getText(), firstNameC5TxtField.getText(), lastNameC5TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
          }


     
}
         