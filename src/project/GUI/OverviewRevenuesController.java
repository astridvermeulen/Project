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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import static project.LOGIC.Booking.calculateRevenuePerMonth;
import project.LOGIC.DomainController;
import project.LOGIC.Revenue;

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
    private Button showBtn;
    @FXML
    private TableView<Revenue> revenueTableView;
    @FXML
    private TableColumn<Revenue, String> monthColumn;
    @FXML
    private TableColumn<Revenue, Double> revenueColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        addDataToChoiceBox();
        
        monthColumn.setCellValueFactory(new PropertyValueFactory<Revenue,String>("month"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<Revenue,Double>("revenuePerMonth"));
        
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
    
    public ObservableList<Revenue> getRevenuesPerMonth(){
        ObservableList<Revenue> revenues = FXCollections.observableArrayList();
        Revenue revenue=null;
        try {
            revenue = new Revenue(getYear());
        } catch (DBException ex) {
            Logger.getLogger(OverviewRevenuesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        revenues.addAll(revenue);
      
        return revenues;
    }

    @FXML
    private void showRevenues(ActionEvent event) {
        revenueTableView.setItems(getRevenuesPerMonth());
    }
    
    
    
    
    
}
 
