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
    private Button nextPassenger1Btn;
    @FXML
    private HBox C1Pane;
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
    private Button nextPassenger5Btn;
    @FXML
    private HBox C6Pane;
    @FXML
    private Button nextPassenger6Btn;
    @FXML
    private HBox C7Pane;
    @FXML
    private Button nextPassenger7Btn;
    @FXML
    private HBox C8Pane;
    @FXML
    private Button nextPassenger8Btn;
    @FXML
    private HBox C9Pane;
    @FXML
    private Button submitC1Btn;
    @FXML
    private Button submitC2Btn;
    @FXML
    private Button submitC3Btn;
    @FXML
    private Button submitC4Btn;
    @FXML
    private Button submitC5Btn;
    @FXML
    private Button submitC6Btn;
    @FXML
    private Button submitC7Btn;
    @FXML
    private Button submitC8Btn;
    @FXML
    private Button submitC9Btn;
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
    private TextField firstNameC3TxtField;
    @FXML
    private TextField lastNameC3TxtField;
    @FXML
    private TextField passportIDC3TxtField;
    @FXML
    private TextField firstNameC4TxtField;
    @FXML
    private TextField lastNameC4TxtField;
    @FXML
    private TextField passportIDC4TxtField;
    @FXML
    private TextField firstNameC5TxtField;
    @FXML
    private TextField lastNameC5TxtField;
    @FXML
    private TextField passportIDC5TxtField;
    @FXML
    private TextField firstNameC6TxtField;
    @FXML
    private TextField lastNameC6TxtField;
    @FXML
    private TextField passportIDC6TxtField;
    @FXML
    private TextField firstNameC7TxtField;
    @FXML
    private TextField lastNameC7TxtField;
    @FXML
    private TextField passportIDC7TxtField;
    @FXML
    private TextField firstNameC8TxtField;
    @FXML
    private TextField lastNameC8TxtField;
    @FXML
    private TextField passportIDC8TxtField;
    @FXML
    private TextField firstNameC9TxtField;
    @FXML
    private TextField lastNameC9TxtField;
    @FXML
    private TextField passportIDC9TxtField;

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
        C6Pane.setVisible(false);
        C7Pane.setVisible(false);
        C8Pane.setVisible(false);
        C9Pane.setVisible(false);
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
    private void showC6Pane(ActionEvent event) {
        C6Pane.setVisible(true);
    }

    @FXML
    private void showC7Pane(ActionEvent event) {
        C7Pane.setVisible(true);
    }

    @FXML
    private void showC8Pane(ActionEvent event) {
        C8Pane.setVisible(true);
    
    }

    @FXML
    private void showC9Pane(ActionEvent event) {
        C9Pane.setVisible(true);
    }

    private void submitC1(ActionEvent event) {
        submitC1Btn.setTextFill(Color.GREEN);
    }

    @FXML
    private void saveCustomer1(ActionEvent event) {
        System.out.println("customer aant saven");
        Customer klant = new Customer(passportIDC1TxtField.getText(), firstNameC1TxtField.getText(), lastNameC1TxtField.getText());

        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("customer opgeslagen");
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

    @FXML
    private void saveCustomer6(ActionEvent event) {
        Customer klant = new Customer(passportIDC6TxtField.getText(), firstNameC6TxtField.getText(), lastNameC6TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
           }

    @FXML
    private void saveCustomer7(ActionEvent event) {
        Customer klant = new Customer(passportIDC7TxtField.getText(), firstNameC7TxtField.getText(), lastNameC7TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    @FXML
    private void saveCustomer8(ActionEvent event) {
        Customer klant = new Customer(passportIDC8TxtField.getText(), firstNameC8TxtField.getText(), lastNameC8TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

    @FXML
    private void saveCustomer9(ActionEvent event) {
        Customer klant = new Customer(passportIDC9TxtField.getText(), firstNameC9TxtField.getText(), lastNameC9TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    
    
     
}
         