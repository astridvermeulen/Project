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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import project.DB.DBException;
import static project.LOGIC.Booking.calculateRevenuePerMonth;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewRevenuesController implements Initializable {
    private DomainController model;
    
    @FXML
    private ChoiceBox<String> yearChoiceBox;
    @FXML
    private Label chooseYearLbl;
    @FXML
    private Label januaryLbl;
    @FXML
    private Label februaryLbl;
    @FXML
    private Label marchLbl;
    @FXML
    private Label aprilLbl;
    @FXML
    private Label mayLbl;
    @FXML
    private Label juneLbl;
    @FXML
    private Label julyLbl;
    @FXML
    private Label augustLbl;
    @FXML
    private Label septemberLbl;
    @FXML
    private Label octoberLbl;
    @FXML
    private Label novemberLbl;
    @FXML
    private Label decemberLbl;
    @FXML
    private ListView<Double> listViewRevenues;
    @FXML
    private Button showBtn;

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
    
    public ObservableList<Double> getRevenues(){
        ObservableList<Double> revenues = FXCollections.observableArrayList();
        
        try {
            for(Double d: calculateRevenuePerMonth(getYear())){
                revenues.add(d);
            }
        } catch (DBException ex) {
            Logger.getLogger(OverviewRevenuesController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return revenues;
    }

    @FXML
    private void showRevenues(ActionEvent event) {
        listViewRevenues.setItems(getRevenues());
    }
    
    
    
}
