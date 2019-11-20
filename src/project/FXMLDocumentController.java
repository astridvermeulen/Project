/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import project.LOGIC.DomeinController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;

    private DomeinController dc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = DomeinController.getInstance();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText(dc.toonVluchtNaam());
    }
    
}
