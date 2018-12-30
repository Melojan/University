/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;

import Authentication.Session;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Waleed
 */
public class SPCController extends DBConnection implements Initializable {

    @FXML
    private TableView<SPC> allTable;
    
    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn addressCol;

    @FXML
    private TableColumn phoneCol;

    @FXML
    private TableColumn emailCol;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private TextField search;

    @FXML
    private ComboBox comboBox1;

    @FXML
    private TextField ID;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;
    
    @FXML
    private TableView<VehicleNotReturned> returnedTable;

    @FXML
    private TableColumn idCol2;

    @FXML
    private TableColumn nameCol2;

    @FXML
    private TableColumn vehicleCol2;

    @FXML
    private TableColumn partCol2;
    
    @FXML
    private ComboBox comboBox2;

    @FXML
    private Button priceReturn;
    
    @FXML
    private ComboBox comboBox3;
    
    @FXML
    private Button booking;

    @FXML
    private TableColumn  vehicleNameCol2;

    @FXML
    private TableColumn vehicleRegCol2;
    
    @FXML
    private TableColumn partNameCol2;
    
    @FXML
    private TableView<SpcBooking> bookingsTable;

    @FXML
    private TableColumn idCol3;

    @FXML
    private TableColumn nameCol3;

    @FXML
    private TableColumn bookingCol3;

    @FXML
    private TableColumn customerIDCol3;

    @FXML
    private TableColumn customerNameCol3;

    @FXML
    private TableColumn phoneCol3;

    @FXML
    private TableColumn emailCol3;

    @FXML
    private TableColumn vehicleIDCol3;

    @FXML
    private TableColumn vehicleNameCol3;

    @FXML
    private TableColumn regNumberCol3;

    @FXML
    private TableColumn partIDCol3;

    @FXML
    private TableColumn partNameCol3;
    
    @FXML
    private TableColumn returnCol3;
    
        @FXML
    private TableView<PartNotReturned> partReturned;

    @FXML
    private TableColumn  idCol4;

    @FXML
    private TableColumn nameCol4;

    @FXML
    private TableColumn  partIDCol4;

    @FXML
    private TableColumn partNameCol4;

    @FXML
    private TableColumn descriptionCol4;

    @FXML
    private ComboBox comboBox4;

    @FXML
    private Button partPriceReturn;
        @FXML
    private TableColumn bookingIDCol2;
            @FXML
    private TableColumn bookingIDCol4;
            
    @FXML
    private Button bookingDeleteVehicle;

    @FXML
    private TextField searchVehicle;
    
    @FXML
    private Button deleteBookingPart;

    @FXML
    private TextField searchPart;
    
    @FXML
    private TextField vehiclePriceField;
    
        @FXML
    private TextField partPriceField;
        
    @FXML
    private TableColumn lastNameCol3;
    
    @FXML
    private Button displayCost;
    
        @FXML
    private Button homeButton;
    @FXML
    private TableColumn returnDateCol2;
    
      @FXML
    private TableColumn returnDateCol4;


    /**
     * Initializes the controller class.
     */
    private final ObservableList<SPC> data = FXCollections.observableArrayList(); 
    private final ObservableList comboOption = FXCollections.observableArrayList();
    private final ObservableList comboOption2 = FXCollections.observableArrayList();
    private final ObservableList comboOption3 = FXCollections.observableArrayList();
    private final ObservableList comboOption4 = FXCollections.observableArrayList();
    private final ObservableList<VehicleNotReturned> data2 = FXCollections.observableArrayList(); 
    private final ObservableList<SpcBooking> data3 = FXCollections.observableArrayList(); 
     private final ObservableList<PartNotReturned> data4 = FXCollections.observableArrayList(); 
    ObservableList<String> item = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<SPC, Integer>("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<SPC, String>("Name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<SPC, String>("Address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<SPC, String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<SPC, String>("Phone"));
        
        idCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, Integer>("ID"));
        nameCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, String>("Name"));
        vehicleCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, Integer>("VehicleID"));
        vehicleNameCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, String>("VehicleName"));
        vehicleRegCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, String>("VehicleReg"));
        bookingIDCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, Integer>("BID"));
        returnDateCol2.setCellValueFactory(new PropertyValueFactory<VehicleNotReturned, String>("ReturnDate"));
        
        idCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("ID"));
        nameCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("Name"));
        vehicleIDCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("VehicleID"));
        vehicleNameCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("VehicleName"));
        regNumberCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("VehicleReg"));
        partIDCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("PartID"));
        partNameCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("PartName")); 
        bookingCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("BookingID"));
        customerIDCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("CustomerID"));
        customerNameCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("CustomerName"));
        lastNameCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("LastName"));
        phoneCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("Phone"));
        emailCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, String>("Email"));
        returnCol3.setCellValueFactory(new PropertyValueFactory<SpcBooking, Integer>("ReturnStatus"));
        
        idCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, Integer>("ID"));
        nameCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, String>("Name"));
        partIDCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, Integer>("PartID"));
        partNameCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, String>("PartName"));
        descriptionCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, String>("Description"));
        bookingIDCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, Integer>("ID"));
        returnDateCol4.setCellValueFactory(new PropertyValueFactory<PartNotReturned, String>("ReturnDate"));
        
        loadAllData();
        loadNotReturnedTable();
        loadBookingTable();
        loadPartNotReturnedTable();
        fillComboBox();
        fillComboBox2();
        fillComboBox3();
        fillComboBox4();
        comboBox1.setItems(comboOption);
        comboBox2.setItems(comboOption2);
        comboBox3.setItems(comboOption3);
        comboBox4.setItems(comboOption4);
  

    }    
    @FXML
    private void addSPC(ActionEvent event) {
            if(validateFieldsAdd()&&checkSpcLimit() && checkAdmin())
            try {
                Class.forName("org.sqlite.JDBC");
                Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                String sql = "INSERT INTO SPC (ID,NAME, ADDRESS, PHONE, EMAIL) VALUES(?,?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(sql);
                        
                stmt.setString(2, name.getText());
                stmt.setString(3, address.getText());
                stmt.setString(4, phone.getText());
                stmt.setString(5, email.getText());

                stmt.execute();
                stmt.close();
                c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);    
        }
        fillComboBox();
        clearFields();
        loadAllData();

    }

    @FXML
    private void editSPC(ActionEvent event) {
        
        if(validateFieldsEdit() && checkAdmin()){
                 try {
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "UPDATE SPC set ID=?,NAME=?,ADDRESS=?,PHONE=?,EMAIL=? where ID='"+ID.getText()+"';";
                    PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.setString(1, ID.getText());
                    stmt.setString(2, name.getText());
                    stmt.setString(3, address.getText());
                    stmt.setString(4, phone.getText());
                    stmt.setString(5, email.getText()); 

                    stmt.execute();     
                    stmt.close();
                    c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
        }
                    fillComboBox();
                    clearFields();
                    loadAllData();
                    loadNotReturnedTable();
                    loadPartNotReturnedTable();
                    loadBookingTable();
    }

    @FXML
    private void deleteSPC(ActionEvent event) {
        if(validateFieldDelete() && checkAdmin())
        comboOption.clear();
        
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("Are you sure you want to delete?");
          Optional <ButtonType> action = alert1.showAndWait();
          
          if(action.get() == ButtonType.OK){
                 try {
                   Class.forName("org.sqlite.JDBC");
                   Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                   String sql = "delete from SPC where NAME =? ;";
                   PreparedStatement stmt = c.prepareStatement(sql);
                   stmt.setString(1, name.getText());

                   stmt.executeUpdate();
                   stmt.close();
                   c.close();
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    e.printStackTrace(System.out);
                    System.exit(0);
            }
        }
                 fillComboBox();
                 clearFields();
                 loadAllData();
                 loadPartNotReturnedTable();
                 loadBookingTable();
    }
    
    public void loadAllData(){
                data.clear();
          try {
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "select * from SPC;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(new SPC(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("ADDRESS"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL")));
            }
            allTable.setItems(data);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }

    }
    
    public void fillComboBox(){
        comboOption.clear();
        
          try {
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "select NAME from SPC;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                comboOption.add(rs.getString("NAME"));
               
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }
    
        @FXML
   public void selectSPC(ActionEvent event) {
                  try {
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "select * from SPC where NAME = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, (String)comboBox1.getSelectionModel().getSelectedItem());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ID.setText(rs.getString("ID"));
                name.setText(rs.getString("NAME"));
                address.setText(rs.getString("ADDRESS"));
                phone.setText(rs.getString("PHONE"));
                email.setText(rs.getString("EMAIL"));
            }
 

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
                  
    }
   
   public void clearFields(){
                ID.clear();
                name.clear();
                address.clear();
                phone.clear();
                email.clear();
   }
   
    @FXML
    void clearFieldsButton(ActionEvent event) {
        clearFields();
    }
   
   private boolean validateFieldsAdd(){
   
       if(name.getText().isEmpty() | address.getText().isEmpty() | phone.getText().isEmpty() | email.getText().isEmpty()){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Enter values for\nNAME/ADDRESS/PHONE/EMAIL to add");
           alert.showAndWait();
           return false;
       }
       return true;
   }
   
   private boolean validateFieldDelete(){
          if(name.getText().isEmpty()){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Please enter NAME of the SPC you wish to delete");
           alert.showAndWait();
           return false;
       }
       return true;
   }
   
      private boolean validateFieldsEdit(){
           if(ID.getText().isEmpty() | name.getText().isEmpty() | address.getText().isEmpty() | phone.getText().isEmpty() | email.getText().isEmpty()){
           Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Enter values for\nNAME/ADDRESS/PHONE/EMAIL and the ID of the SPC you wish to edit");
           alert.showAndWait();
           return false;
       }
       return true;
   }

   public void loadNotReturnedTable(){
       data2.clear();
                 try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "SELECT SPC.ID, SPC.NAME, vehicleID, Vehicle.make AS vehiclename, Vehicle.regNumber,Booking.id AS bid,date FROM SPC INNER JOIN Booking ON SPC.ID = Booking.spcID  INNER JOIN Vehicle ON customerVehicle.vehicleID = Vehicle.id INNER JOIN customerVehicle ON Booking.customerVehicle = customerVehicle.id  where returnStatus =0 AND bookingType = 2;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String bookingDate = rs.getString("date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar c1 = Calendar.getInstance();
c1.setTime(sdf.parse(bookingDate)); //use bookindate
c1.add(Calendar.DATE, 5);
String output = sdf.format(c1.getTime());
                data2.add(new VehicleNotReturned(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("vehicleID"),
                        rs.getString("vehiclename"),
                        rs.getString("regNumber"),
                        rs.getInt("bid"),
                        output));
            }
            returnedTable.setItems(data2);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
   }
   
    @FXML
    public void searchFunction(KeyEvent event) {

        FilteredList<SpcBooking> filteredData = new FilteredList<>(data3, e -> true);

        search.setOnKeyReleased(e -> {

            search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super SpcBooking>) booking -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (booking.getCustomerName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(booking.getLastName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(booking.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(booking.getReturnStatus().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else
                        return false;
                });
            });
        });

        SortedList<SpcBooking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(bookingsTable.comparatorProperty());
        bookingsTable.setItems(sortedData);
    }
    
        public void fillComboBox2(){
        comboOption2.clear();
        
          try { 
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "SELECT Booking.id AS bid FROM Booking WHERE customerVehicle != '' AND returnStatus =0 AND bookingType =2;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                comboOption2.add(rs.getString("bid"));
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }
        
    @FXML
    void setPriceAndReturn(ActionEvent event) {
           Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("You are about to set return the vehicle chosen with booking id '"+comboBox2.getSelectionModel().getSelectedItem()+"' and set the booking price. Please confirm");
          Optional <ButtonType> action = alert1.showAndWait();
          if(action.get() == ButtonType.OK){
                    try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                   
                    String sql = "UPDATE Booking set balance = balance + "+Integer.parseInt(vehiclePriceField.getText())+" where id = "+comboBox2.getSelectionModel().getSelectedItem()+ ";";
                    String sql2 = "UPDATE Booking set returnStatus = 1 where id ="+comboBox2.getSelectionModel().getSelectedItem()+";";
                    PreparedStatement stmt = c.prepareStatement(sql);
                    PreparedStatement stmt2 = c.prepareStatement(sql2);

                    stmt.execute();     
                    stmt2.execute();
                    stmt2.close();
                    stmt.close();
                    c.close();
                    
        } catch (Exception e) {
            System.out.println("FAILED");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
        }
        fillComboBox2();
        fillComboBox4();
        loadNotReturnedTable();
        loadPartNotReturnedTable();
        loadBookingTable();
        comboOption2.clear();
    }
    
    public void fillComboBox3(){
                comboOption3.clear();
          try { 
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "SELECT id FROM Booking WHERE bookingType = 2";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                comboOption3.add(rs.getString("id"));
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }
    
    @FXML
    void deleteBooking(ActionEvent event) {
           Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("You are about to delete a booking. Please confirm");
          Optional <ButtonType> action = alert1.showAndWait();
          if(action.get() == ButtonType.OK){
                    try {
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "DELETE FROM Booking WHERE id = "+comboBox3.getSelectionModel().getSelectedItem()+ ";";
                    PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.execute();     
                    stmt.close();
                    c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
        }
          fillComboBox3();
          fillComboBox2();
          fillComboBox4();
          loadBookingTable();
          loadNotReturnedTable();
          loadPartNotReturnedTable();
    }
       public void loadBookingTable(){
       data3.clear();
                 try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "select SPC.ID, SPC.NAME, Booking.id AS bookingid, vehicleID,Vehicle.make AS vehiclename,regNumber,Booking.partsID,Parts.name AS partname,customer.id AS customerid, firstName,lastName, phoneNumber,customer.email,returnStatus FROM SPC INNER JOIN Booking ON SPC.ID = Booking.spcID INNER JOIN customerVehicle ON Booking.customerVehicle = customerVehicle.id INNER JOIN customer ON customerVehicle.customerID = customer.id INNER JOIN Vehicle ON customerVehicle.vehicleID = Vehicle.id INNER JOIN Parts ON Booking.partsID = Parts.id WHERE bookingType =2;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String status ="";
                if(rs.getInt("returnStatus") == 0){
                    status = "Not Returned";
                }
                else{
                    status ="Returned";
                }
                data3.add(new SpcBooking(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("bookingid"),
                        rs.getInt("vehicleID"),
                        rs.getString("vehiclename"),
                        rs.getString("regNumber"),
                        rs.getInt("partsID"),
                        rs.getString("partname"),
                        rs.getInt("customerid"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        status
                ));
            }
            bookingsTable.setItems(data3);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
   }
       public boolean checkSpcLimit(){
            try {
                    int count =0;
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "SELECT count(ID) AS count FROM SPC";
                    PreparedStatement stmt = c.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        count = rs.getInt("count");
                    }
                    stmt.execute();     
                    stmt.close();
                    c.close();
                    if(count >=10){
                         Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("The garage can only use 10 SPC's exclusively");
           alert.showAndWait();
           return false;
                    }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
            return true;
       }
       
          public void loadPartNotReturnedTable(){
       data4.clear();
                 try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "SELECT SPC.ID, SPC.NAME, PartsID, Parts.name AS partname, Parts.description, Booking.id AS bookingID,date FROM SPC INNER JOIN Booking ON SPC.ID = Booking.spcID  INNER JOIN Parts ON Booking.partsID = Parts.id where returnStatus =0 AND bookingType = 2;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                                String bookingDate = rs.getString("date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar c1 = Calendar.getInstance();
c1.setTime(sdf.parse(bookingDate)); //use bookingdate
c1.add(Calendar.DATE, 5);
String output = sdf.format(c1.getTime());
                data4.add(new PartNotReturned(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("partsID"),
                        rs.getString("partname"),
                        rs.getString("description"),
                        rs.getInt("bookingID"),
                output));
            }
            partReturned.setItems(data4);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
   }
          
            public void fillComboBox4(){
        comboOption4.clear();
        
          try { 
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "SELECT Booking.id AS pid FROM Booking WHERE returnstatus = 0 AND Booking.bookingType =2 AND partsID != '';";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                comboOption4.add(rs.getString("pid"));
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }
    @FXML
    void setPartPriceReturn(ActionEvent event) {
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("You are about to return the part chosen and set the booking price. Please confirm");
          Optional <ButtonType> action = alert1.showAndWait();
          if(action.get() == ButtonType.OK){
                    try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "UPDATE Booking set balance = balance + "+Integer.parseInt(partPriceField.getText())+" where id = "+comboBox4.getSelectionModel().getSelectedItem()+ ";";
                    String sql2 = "UPDATE Booking set returnStatus = 1 where id ="+comboBox4.getSelectionModel().getSelectedItem()+";";
                    PreparedStatement stmt = c.prepareStatement(sql);
                    PreparedStatement stmt2 = c.prepareStatement(sql2);

                    stmt.execute();     
                    stmt2.execute();
                    stmt2.close();
                    stmt.close();
                    c.close();
                    
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
        }
        fillComboBox4();
        fillComboBox2();
        loadPartNotReturnedTable();
        loadNotReturnedTable();
        loadBookingTable();
        comboOption4.clear();
    }
    
    
    @FXML
    void deleteVehicleBooking(ActionEvent event) {
                 Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("You are about to delete the booking with id '"+comboBox2.getSelectionModel().getSelectedItem()+"'. Please confirm");
          Optional <ButtonType> action = alert1.showAndWait();
          if(action.get() == ButtonType.OK){
                    try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "DELETE FROM Booking WHERE id = "+comboBox2.getSelectionModel().getSelectedItem()+ ";";
                    PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.execute();     
                    stmt.close();
                    c.close();
                    comboOption2.clear();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
        }
          fillComboBox3();
          fillComboBox2();
          fillComboBox4();
          loadBookingTable();
          loadNotReturnedTable();
          loadPartNotReturnedTable();
    }
    
    
    @FXML
    void deletePartBooking(ActionEvent event) {
                         Alert alert1 = new Alert(AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("You are about to delete a booking with id '"+comboBox2.getSelectionModel().getSelectedItem()+"'. Please confirm");
          Optional <ButtonType> action = alert1.showAndWait();
          if(action.get() == ButtonType.OK){
                    try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "DELETE FROM Booking WHERE id = "+comboBox4.getSelectionModel().getSelectedItem()+ ";";
                    PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.execute();     
                    stmt.close();
                    c.close();
                    comboOption4.clear();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
            }
        }
          fillComboBox3();
          fillComboBox2();
          fillComboBox4();
          loadBookingTable();
          loadNotReturnedTable();
          loadPartNotReturnedTable();
    }
        @FXML
    void searchFunctionPart(KeyEvent event) {
                FilteredList<PartNotReturned> filteredData = new FilteredList<>(data4, e -> true);

        searchPart.setOnKeyReleased(e -> {

            searchPart.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super PartNotReturned>) booking -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (booking.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(booking.getPartName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else
                        return false;
                });
            });
        });

        SortedList<PartNotReturned> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(partReturned.comparatorProperty());
        partReturned.setItems(sortedData);
    }

    @FXML
    void searchFunctionVehicle(KeyEvent event) {
                        FilteredList<VehicleNotReturned> filteredData = new FilteredList<>(data2, e -> true);

        searchVehicle.setOnKeyReleased(e -> {

            searchVehicle.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super VehicleNotReturned>) booking -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (booking.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(booking.getVehicleReg().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else
                        return false;
                });
            });
        });

        SortedList<VehicleNotReturned> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(returnedTable.comparatorProperty());
        returnedTable.setItems(sortedData);

    }
    
    @FXML
    void displayItemCost(ActionEvent event) {
        
        try{
            int co =0;
            int r =0;
                Class.forName("org.sqlite.JDBC");
                Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                String returnStatus = "SELECT returnStatus FROM Booking where id = "+comboBox3.getSelectionModel().getSelectedItem()+";";
                String cost = "SELECT balance FROM Booking where id = "+comboBox3.getSelectionModel().getSelectedItem()+";";
                                    PreparedStatement stmt = c.prepareStatement(returnStatus);
                                    PreparedStatement stmt2 = c.prepareStatement(cost);
                    ResultSet rs = stmt.executeQuery();
                    r = rs.getInt("returnStatus");
                    ResultSet rs2 = stmt2.executeQuery();
                    co = rs2.getInt("balance");
                    
                    stmt2.execute();
                    stmt.execute(); 
                    stmt2.close();
                    stmt.close();
                    c.close();
                if(r == 1){
                    System.out.println("ERROR");
                               Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("The total cost of repairs for the booking with booking id '"+comboBox3.getSelectionModel().getSelectedItem()+"' is £"+co+"");
           alert.showAndWait();
                } 
                else {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("The item associated with booking id '"+comboBox3.getSelectionModel().getSelectedItem()+"' has not been returned yet");
           alert.showAndWait();
                }
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
                       
    }
    
    
    @FXML
    void GoHome(ActionEvent event ) throws Exception {
                Stage stage = (Stage) this.homeButton.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }
        public boolean checkAdmin(){
        if(Session.getType() == 1){
 
            return true;
        }
        else {
                        Alert alert = new Alert(AlertType.WARNING);
                       alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Only admins can access this feature");
           alert.showAndWait();
            return false;
        }
    }
}