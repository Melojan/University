/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    @FXML
            private TableView<Vehicle> searchTable;
    @FXML
    private TableColumn<Vehicle, Integer> vehicleIdColumn;

    @FXML
    private TableColumn<Vehicle, String> registrationNoColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, String> makeColumn;

    @FXML
    private TableColumn<Vehicle, Double> engineSizeColumn;

    @FXML
    private TableColumn<Vehicle, String> fuelTypeColumn;

    @FXML
    private TableColumn<Vehicle, String> colourColumn;

    @FXML
    private TableColumn<Vehicle, String> MotRenewalDateColumn;

    @FXML
    private TableColumn<Vehicle, String> lastServiceColumn;

    @FXML
    private TableColumn<Vehicle, Integer> currentMileageColumn;

    @FXML
    private TableColumn<Vehicle, String> vehicleTypeColumn;
   
    @FXML
        
    private TableColumn<Vehicle,Integer> WIdColumn;
    @FXML

    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer,Integer > customerIDColumn;

    @FXML
    private TableColumn<Customer, String> firstName;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> address1Column;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;
    
    @FXML  
    private TableView<PartsBooking> partsBookingTable;

    @FXML
    private TableColumn<PartsBooking, String> partsUsedColumn;

    @FXML
    private TableColumn<PartsBooking, String> pastBookingDateColumn;

    @FXML
    private TableColumn<PartsBooking, Integer> totalBookingCostColumn;

    @FXML
    private TableColumn<PartsBooking, Integer> partsIDColumn;
    
    @FXML
    private TableView<PartsBooking1> partsBookingTable1;

    @FXML
    private TableColumn<PartsBooking1, Integer> partsIDColumn1;

    @FXML
    private TableColumn<PartsBooking1, String> partsUsedColumn1;

    @FXML
    private TableColumn<PartsBooking1, String> futureBookingDateColumn1;

    @FXML
    private TableColumn<PartsBooking1, Integer> totalBookingCostColumn1;
    
    @FXML
    private Button addButton;
    
     @FXML
    public void addVehicleAction(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddVehicle.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
  }
    
    @FXML
    private Button refreshButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchField;
        
    @FXML
    
    private Button editWarrantyButton;
    @FXML

 public void editWarrantyAction(ActionEvent event) throws Exception {               
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditWarranty.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Warranty w = warrantyTable.getSelectionModel().getSelectedItem();
                EditWarrantyController contro = fxmlLoader.getController();
                contro.getWInfo(w.getID(), w.getName(), w.getAddress(), w.getDateOfExpiry());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
  }        
    private Button editButton;
    
    @FXML
  public void editVehicleAction(ActionEvent event) throws Exception {               
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditVehicle.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Vehicle v = searchTable.getSelectionModel().getSelectedItem();
                EditVehicleController contro = fxmlLoader.getController();
                contro.getInfo(v.getID(), v.getReg(), v.getModel(), v.getMake(), v.getEngSize(), v.getFuel(), v.getColourCol(), v.getMOTDate(), v.getLastServiceDate(), v.getCurrentMileage(), v.getVehicleType());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
  }
    @FXML
    private Button logoutButton;

    @FXML
    private TableView<Warranty> warrantyTable;

    @FXML
    private TableColumn<Warranty , Integer> warrantyIdColumn;

    @FXML
    private TableColumn<Warranty , String> nameColumn;

    @FXML
    private TableColumn<Warranty , String> addressColumn;

    @FXML
    private TableColumn<Warranty , Integer> dateOfExpiryColumn;

    @FXML
    private Button homeButton;


    @FXML
    
    private void GoHome(ActionEvent event) throws Exception{
        Stage stage = (Stage) this.homeButton.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void logout(ActionEvent event) throws Exception{
      
             Stage stage = (Stage) logoutButton.getScene().getWindow();
             Parent root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("Authentication/Login.fxml"));
             Scene scene = new Scene(root);
        
              stage.setScene(scene);
                 stage.show();
    }
 private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList(); 

    @FXML
    public void searchFunction(KeyEvent event) {

        FilteredList<Vehicle> filteredData = new FilteredList<>(vehicleData, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Vehicle>) vehicle -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (vehicle.getReg().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(vehicle.getMake().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(vehicle.getVehicleType().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else
                        return false;
                });
            });
        });

        SortedList<Vehicle> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(searchTable.comparatorProperty());
        searchTable.setItems(sortedData);
    }

 private ObservableList<Warranty> warrantyData = FXCollections.observableArrayList();
     private ObservableList<PartsBooking> partsBookingData = FXCollections.observableArrayList();
          private ObservableList<PartsBooking1> partsBookingData1 = FXCollections.observableArrayList();
          private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       loadData();
       databaseQueries dbq = new databaseQueries();
       vehicleData = dbq.buildData();
       searchTable.setItems(vehicleData);
       
       databaseQueries dbq1 = new databaseQueries();
       warrantyData = dbq1.WarrantyData();
       warrantyTable.setItems(warrantyData);

       databaseQueries dbq2 = new databaseQueries();
       partsBookingData = dbq2.PartsBookingData();
       partsBookingTable.setItems(partsBookingData);
       
       databaseQueries dbq3 = new databaseQueries();
       partsBookingData1 = dbq3.PartsBookingData1();
       partsBookingTable1.setItems(partsBookingData1);
 
       databaseQueries dbq4 = new databaseQueries();
       customerData = dbq4.CustomerData();
       customerTable.setItems(customerData);
       
       removeButton.setOnAction((ActionEvent e) -> {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("Are you sure you want to delete?");
          Optional <ButtonType> action = alert1.showAndWait();
          
          if(action.get() == ButtonType.OK){
    Vehicle selectedItem = searchTable.getSelectionModel().getSelectedItem();
    searchTable.getItems().remove(selectedItem);
    
                  try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                      
                    String sql = ("DELETE FROM Vehicle WHERE regNumber = '"+selectedItem.getReg()+"'");
                   PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.execute();     
                    stmt.close();
                    c.close();
                   
        } catch (Exception z) {
          
            z.printStackTrace(System.out);
            
            }
                
       }});
       }
      
               
         public void loadData(){
             
    vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("ID"));
    WIdColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("warrantyID"));
    registrationNoColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("Reg"));
    modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("Model"));    
    makeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("Make"));   
    engineSizeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Double>("EngSize"));       
    fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("Fuel"));   
    colourColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("ColourCol"));   
    MotRenewalDateColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("MOTDate"));
    lastServiceColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("lastServiceDate"));
    currentMileageColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("currentMileage"));
    vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("vehicleType"));
    
    warrantyIdColumn.setCellValueFactory(new PropertyValueFactory<Warranty,Integer>("ID"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<Warranty,String>("Name"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<Warranty,String>("Address"));
    dateOfExpiryColumn.setCellValueFactory(new PropertyValueFactory<Warranty,Integer>("DateOfExpiry"));
    
    partsIDColumn.setCellValueFactory(new PropertyValueFactory<PartsBooking, Integer>("PartsID"));
    partsUsedColumn.setCellValueFactory(new PropertyValueFactory<PartsBooking, String>("PartUsed"));
    pastBookingDateColumn.setCellValueFactory(new PropertyValueFactory<PartsBooking, String>("PastBookingDate"));
    totalBookingCostColumn.setCellValueFactory(new PropertyValueFactory<PartsBooking, Integer>("BookingCost"));

    partsIDColumn1.setCellValueFactory(new PropertyValueFactory<PartsBooking1, Integer>("PartsID1"));
    partsUsedColumn1.setCellValueFactory(new PropertyValueFactory<PartsBooking1, String>("PartUsed1"));
    futureBookingDateColumn1.setCellValueFactory(new PropertyValueFactory<PartsBooking1, String>("FutureBookingDate1"));
    totalBookingCostColumn1.setCellValueFactory(new PropertyValueFactory<PartsBooking1, Integer>("BookingCost1"));
    
    customerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
    firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
    address1Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("address1"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
    
     }
         
    @FXML
    private void refresh(ActionEvent event) {
       databaseQueries dbq = new databaseQueries();
       vehicleData = dbq.buildData();
       searchTable.setItems(vehicleData);
        
       databaseQueries dbq1 = new databaseQueries();
       warrantyData = dbq1.WarrantyData();
       warrantyTable.setItems(warrantyData);
       
       databaseQueries dbq2 = new databaseQueries();
       partsBookingData = dbq2.PartsBookingData();
       partsBookingTable.setItems(partsBookingData);
       
       databaseQueries dbq3 = new databaseQueries();
       partsBookingData1 = dbq3.PartsBookingData1();
       partsBookingTable1.setItems(partsBookingData1);
 
       databaseQueries dbq4 = new databaseQueries();
       customerData = dbq4.CustomerData();
       customerTable.setItems(customerData);
    }
}