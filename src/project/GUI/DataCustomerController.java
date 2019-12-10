
package project.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import project.DB.DBBooking;
import project.DB.DBException;
import project.LOGIC.Booking;
import static project.LOGIC.Booking.saveBooking;
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
     private ArrayList<Customer> customersLinkedToBooking = new ArrayList<>();
     private SearchFlightController controller;
     private Booking booking;
   
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
    @FXML
    private Label firstNameLbl;
    @FXML
    private Label lastNameLbl;
    @FXML
    private Label passportIDLbl;
    @FXML
    private Label birthDateLbl;
    @FXML
    private TextField birthDateC1TxtField;
    @FXML
    private TextField birthDateC2TxtField;
    @FXML
    private TextField birthDateC3TxtField;
    @FXML
    private TextField birthDateC4TxtField;
    @FXML
    private TextField birthDateC5TxtField;
    @FXML
    private Button confirmBookingBtn;


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
        Customer klant = new Customer(passportIDC1TxtField.getText(), firstNameC1TxtField.getText(), lastNameC1TxtField.getText(), birthDateC1TxtField.getText());

        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
    }

    @FXML
    private void saveCustomer2(ActionEvent event) {
        Customer klant = new Customer(passportIDC2TxtField.getText(), firstNameC2TxtField.getText(), lastNameC2TxtField.getText(), birthDateC2TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
        }
    

    @FXML
    private void saveCustomer3(ActionEvent event) {
        Customer klant = new Customer(passportIDC3TxtField.getText(), firstNameC3TxtField.getText(), lastNameC3TxtField.getText(),birthDateC3TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
    }

    @FXML
    private void saveCustomer4(ActionEvent event) {
        Customer klant = new Customer(passportIDC4TxtField.getText(), firstNameC4TxtField.getText(), lastNameC4TxtField.getText(), birthDateC4TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
    }

    @FXML
    private void saveCustomer5(ActionEvent event) {
        Customer klant = new Customer(passportIDC5TxtField.getText(), firstNameC5TxtField.getText(), lastNameC5TxtField.getText(), birthDateC5TxtField.getText());
        try {
            saveCustomer(klant);
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customersLinkedToBooking.add(klant);
    }

    @FXML
    private void confirmBooking(ActionEvent event) {
        controller = new SearchFlightController();
        
        try {
            System.out.println(controller.getSelectedFlights());
            System.out.println(customersLinkedToBooking);
            booking = new Booking(controller.getSelectedFlights(), customersLinkedToBooking);
            System.out.println(booking.getFlight());
            try {
                saveBooking(booking);
            } catch (SQLException ex) {
                Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DBException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) {
        
    }

    


     
}
         
