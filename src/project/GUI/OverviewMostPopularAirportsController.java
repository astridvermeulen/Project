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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.Airport;
import static project.LOGIC.Airport.tenMostPopularAirports;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewMostPopularAirportsController implements Initializable {
    private DomainController model;
    @FXML
    private TableView<Airport> tableViewMostPopularAirports;
    @FXML
    private TableColumn<Airport, String> airportColumn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        airportColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("airportName"));
    
        tableViewMostPopularAirports.setItems(getMostPopularAirports());
        
    } 
    
    public ObservableList<Airport> getMostPopularAirports(){
        ObservableList<Airport> airports = FXCollections.observableArrayList();
      
        try {
            for(Airport a: tenMostPopularAirports()){
                airports.add(a);
            }   
        } catch (DBException ex) {
            Logger.getLogger(OverviewMostPopularAirportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("airports toegevoegd aan observableList");
        
        return airports;
    }
    
        
    
}
