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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private TextField birthDateTxtField;
    @FXML
    private TableColumn<Customer, String> bithDateColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=DomainController.getInstance();
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("passportNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        homeCountryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("homeCountry"));
        bithDateColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("birthDate"));      
    
        tableViewCustomers.setItems(getCustomers());
        tableViewCustomers.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bithDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
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
        
        Customer customer = new Customer(passportIDTxtField.getText(), firstNameTxtField.getText(), lastNameTxtField.getText(), birthDateTxtField.getText());
        
        //customer toevoegen of aanpassen indien ze al bestaat, in datalaag wordt de oude customer dan onmiddellijk verwijderd
        try {
            saveCustomer(customer);
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableViewCustomers.getItems().add(customer);
        
        //tekstvakjes leeg maken
        passportIDTxtField.clear();
        firstNameTxtField.clear();
        lastNameTxtField.clear();
        birthDateTxtField.clear();
        
    }

@FXML
    public void changeFirstNameCellEvent(CellEditEvent editedCell){
        Customer customerSelected = tableViewCustomers.getSelectionModel().getSelectedItem();
        customerSelected.setFirstName(editedCell.getNewValue().toString());
        try {
            saveCustomer(customerSelected);
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
@FXML
    public void changeLastNameCellEvent(CellEditEvent editedCell){
        Customer customerSelected = tableViewCustomers.getSelectionModel().getSelectedItem();
        customerSelected.setLastName(editedCell.getNewValue().toString());
        try {
            saveCustomer(customerSelected);
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
@FXML
    public void changeBirthDateCellEvent(CellEditEvent editedCell){
        Customer customerSelected = tableViewCustomers.getSelectionModel().getSelectedItem();
        customerSelected.setBirthDate(editedCell.getNewValue().toString());
        try {
            saveCustomer(customerSelected);
        } catch (DBException ex) {
            Logger.getLogger(OverviewCustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
