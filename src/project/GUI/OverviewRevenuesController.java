/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class OverviewRevenuesController implements Initializable {
    private DomainController model;
    
    @FXML
    private ChoiceBox<?> yearChoiceBox;
    @FXML
    private TableView<?> tableViewRevenues;
    @FXML
    private Label chooseYearLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        addDataToChoiceBox();
        
    }   
    
    private void addDataToChoiceBox(){
        
        ObservableList list = FXCollections.observableArrayList();
    
        for(int i=2019; i<2025; i++){
          list.add(i);
        }
    
        yearChoiceBox.getItems().addAll(list);
    }
    
    
    
}
