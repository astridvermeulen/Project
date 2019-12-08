/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import project.DB.DBException;
import project.LOGIC.Customer;
import static project.LOGIC.Customer.customersOverview;
import static project.LOGIC.Customer.deleteCustomer;
import static project.LOGIC.Customer.saveCustomer;
import project.LOGIC.DomainController;

/**
 * FXML Controller class
 *
 * @author eliseverschelde
 */
public class OverviewCustomersController implements Initializable {
    
    // bekijk: https://www.youtube.com/watch?v=uz2sWCnTq6E
private DomainController model;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> passportNumberColumn;
    @FXML
    private TableColumn<Customer, String> homeCountryColumn;
    @FXML
    private TableView<Customer> tableViewCustomers;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField lastNameTxtField;
    @FXML
    private TextField passportIDTxtField;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Customer, String> bithDayColumn;
    @FXML
    private TextField birthDateTxtField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("passportNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        bithDayColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("birthDate"));
        homeCountryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("homeCountry"));
        
    
        tableViewCustomers.setItems(getCustomers());
    } 
    
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            for(Customer c: customersOverview()){
            customers.add(c);
        }
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
        
    }

    @FXML
    private void addBtnClicked(ActionEvent event) {
        //Indien de customer met hetzelfde paspoort al bestaat, zullen we dit eerst verwijderen uit onze table, ze staat wel nog in de database. 
        ObservableList<Customer> allCustomers = tableViewCustomers.getItems();
        ObservableList<Customer> listCustomersToDelete=null;

        for(Customer c: allCustomers){
            if (c.getPassportNumber().equals(passportIDTxtField.getText())){
                listCustomersToDelete.add(c);
            }
        }
        
        listCustomersToDelete.forEach(allCustomers::remove);
        System.out.println("customer uit tabel verwijderd");
        
        
        Customer customer = new Customer(passportIDTxtField.getText(), firstNameTxtField.getText(), lastNameTxtField.getText(), birthDateTxtField.getText());
        System.out.println("customer object aangemaakt");

        
        //customer toevoegen of aanpassen indien ze al bestaat, in datalaag wordt de oude customer dan onmiddellijk verwijderd
        try {
            saveCustomer(customer);
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("aangepast customer opgeslaan");
        tableViewCustomers.getItems().add(customer);
        System.out.println("aangepaste customer toegevoegd");
        
        //tekstvakjes leeg maken
        passportIDTxtField.clear();
        firstNameTxtField.clear();
        lastNameTxtField.clear();
    }

    
    @FXML
    private void deleteBtnClicked(ActionEvent event) {
        ObservableList<Customer> customerSelected, allCustomers;
        allCustomers = tableViewCustomers.getItems();
        customerSelected = tableViewCustomers.getSelectionModel().getSelectedItems();
        try {
            deleteCustomer(customerSelected.get(0).getPassportNumber());
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customerSelected.forEach(allCustomers::remove);
    }
    
}
