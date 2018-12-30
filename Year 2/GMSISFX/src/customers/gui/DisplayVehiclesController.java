/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;

import customers.logic.Vehicle;
import customers.logic.dbmethods;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class DisplayVehiclesController implements Initializable {
    
    
   dbmethods dbm = new dbmethods();

    @FXML
    private TableColumn<Vehicle, String> name;
    @FXML
    private TableColumn<Vehicle, String> regNumber;
    @FXML
    private TableColumn<Vehicle, String> model;
    @FXML
    private TableColumn<Vehicle, String> make;
    @FXML
    private TableColumn<Vehicle, String> engineSize;
    @FXML
    private TableColumn<Vehicle, String> fuelType;
    @FXML
    private TableColumn<Vehicle, String> colour;
    @FXML
    private TableView<Vehicle> vehicletbv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        
         

        
    }    
    
   public void setupTables(int id) {
       name.setCellValueFactory(new PropertyValueFactory<>("name"));
         regNumber.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
         model.setCellValueFactory(new PropertyValueFactory<>("model"));
         make.setCellValueFactory(new PropertyValueFactory<>("make"));
         engineSize.setCellValueFactory(new PropertyValueFactory<>("engineSize"));
         fuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
         colour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        ObservableList<Vehicle> vehicles = dbm.findVehiclesByCustomer(id);
         vehicletbv.setItems(vehicles);
    }
    
}
