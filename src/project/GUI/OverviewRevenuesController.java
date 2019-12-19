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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewRevenuesController implements Initializable {
    private DomainController model;
    private MakeBooking mb = MakeBooking.getInstance();
    
    @FXML
    private ChoiceBox<String> yearChoiceBox;
    @FXML
    private Label chooseYearLbl;
    @FXML
    private Button showBtn;
    @FXML
    private ListView<Double> listViewRevenvues;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        addDataToChoiceBox();
        
    }   
    
    private void addDataToChoiceBox(){
        
        ObservableList<String> list = FXCollections.observableArrayList();
    
        for(Integer i=2019; i<2025; i++){
          list.add(i.toString());
        }
        
        yearChoiceBox.getItems().addAll(list);
    }
    
    public String getYear(){
        return yearChoiceBox.getValue();
    }

    @FXML
    private void showRevenues(ActionEvent event) {
        listViewRevenvues.setItems(returnRevenuesPerMonth());
    }
    
    public ObservableList<Double> returnRevenuesPerMonth(){
        ObservableList<Double> revenues = FXCollections.observableArrayList();
        for(Double d: mb.getRevenuesPerMonth(getYear())){
                revenues.add(d);
        }
        return revenues;
    }
    
}
