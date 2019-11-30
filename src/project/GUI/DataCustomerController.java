/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Lars
 */
public class DataCustomerController implements Initializable {

    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    private void submitC1(ActionEvent event) {
        submitC1Btn.setTextFill(Color.GREEN);
    }

    
    
     
}
