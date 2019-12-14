
package project.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import project.DB.DBBooking;
import project.DB.DBException;
import project.LOGIC.Booking;
import static project.LOGIC.Booking.saveBooking;
import project.LOGIC.Customer;
import static project.LOGIC.Customer.saveCustomer;
import project.LOGIC.DomainController;
import project.LOGIC.Flight;

/**
 * FXML Controller class
 *
 * @author Lars
 */
public class DataCustomerController implements Initializable {
     private DomainController model;
     private MakeBooking mb = MakeBooking.getInstance();
     private ArrayList<Customer> customersLinkedToBooking = new ArrayList<>();
     private SearchFlightController controller;
     private Booking booking;
     private ArrayList<Flight> selectedFlights = new ArrayList<Flight>();
   
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
    @FXML
    private Label firstNameLbl1;
    @FXML
    private Label lastNameLbl1;
    @FXML
    private Label passportIDLbl1;
    @FXML
    private Label birthDateLbl1;
    @FXML
    private Label firstNameLbl11;
    @FXML
    private Label lastNameLbl11;
    @FXML
    private Label passportIDLbl11;
    @FXML
    private Label birthDateLbl11;
    @FXML
    private Label firstNameLbl111;
    @FXML
    private Label lastNameLbl111;
    @FXML
    private Label passportIDLbl111;
    @FXML
    private Label birthDateLbl111;
    @FXML
    private Label firstNameLbl1111;
    @FXML
    private Label lastNameLbl1111;
    @FXML
    private Label passportIDLbl1111;
    @FXML
    private Label birthDateLbl1111;
    private AnchorPane panelToUpdate;


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
        mb.customerInfo(passportIDC1TxtField, firstNameC1TxtField, lastNameC1TxtField, birthDateC1TxtField);
        submitC1Btn.setText("Submitted");
    }
    @FXML
    private void saveCustomer2(ActionEvent event) {
        mb.customerInfo(passportIDC2TxtField, firstNameC2TxtField, lastNameC2TxtField, birthDateC2TxtField);
        submitC2Btn.setText("Submitted");
    }
    @FXML
    private void saveCustomer3(ActionEvent event) {
        mb.customerInfo(passportIDC3TxtField, firstNameC3TxtField, lastNameC3TxtField, birthDateC3TxtField);
        submitC3Btn.setText("Submitted");
    }
    @FXML
    private void saveCustomer4(ActionEvent event) {
       mb.customerInfo(passportIDC4TxtField, firstNameC4TxtField, lastNameC4TxtField, birthDateC4TxtField);
       submitC4Btn.setText("Submitted");
    }
    @FXML
    private void saveCustomer5(ActionEvent event) {
        mb.customerInfo(passportIDC5TxtField, firstNameC5TxtField, lastNameC5TxtField, birthDateC5TxtField);
        submitC5Btn.setText("Submitted");
    }
    @FXML
    private void confirmBooking(ActionEvent event) {
        mb.makeBooking();
        try {
             AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("bookingSummary.fxml"));
             panelToUpdate.getChildren().setAll(pane);
         } catch (IOException ex) {
             Logger.getLogger(DataCustomerController.class.getName()).log(Level.SEVERE, null, ex);
         }

        
    }
}
         
