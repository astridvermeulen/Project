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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import project.DB.DBException;
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
    private ListView<String> listViewTopAirports;
    @FXML
    private Label thisIsLbl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
       
        listViewTopAirports.setItems(getMostPopularAirports());
        thisIsLbl.setText("This is an overview of the top " + getMostPopularAirports().size() + " most popular airports");
    } 
    
    public ObservableList<String> getMostPopularAirports(){
        ObservableList<String> airports = FXCollections.observableArrayList();
      
        try {
            for(String p: tenMostPopularAirports()){
                airports.add(p);
            }   
        } catch (DBException ex) {
            Logger.getLogger(OverviewMostPopularAirportsController.class.getName()).log(Level.SEVERE, null, ex);
            alertBox.display("Warning!","A Database exception has been thrown");
        }
        return airports;
    }
    
    
    
        
    
}
