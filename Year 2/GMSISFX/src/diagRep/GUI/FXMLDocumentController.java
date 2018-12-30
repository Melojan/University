/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.GUI;

import Vehicle.databaseQueries;
import diagRep.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {
    
    private DBConnection dbCon = new DBConnection();

    @FXML private Label repairBooking;
    @FXML private Text bookType;
    @FXML private Text bookDate;
    @FXML private Text time;
    @FXML private Text dt;
    @FXML private Text fName;
    @FXML private Text lName;
    @FXML private Text curMileage;
    @FXML private Text mecId;
    @FXML private Text vehReg;
    @FXML private Text makefield;
    @FXML private DatePicker bookingdatetext;
    @FXML private ComboBox timetxt;
    @FXML private TextField mechctxt;
    @FXML private TextField dtxt;
    @FXML private TextField ttxt;
    @FXML private TextField maketxt;
    
    @FXML private ComboBox<String> cb1;

    @FXML private TextField custxt;
    @FXML private TextField miltxt;

    @FXML private TextField vehtxt;
    @FXML private TextField fnametxt;
    @FXML private TextField lnametxt;
 
    @FXML private Button gbButton;
    @FXML private Button submitBookButton;
    private String filter = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
  

            submitBookButton.setOnAction((ActionEvent e) -> {
                    
                  try {                        
                   Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = ("INSERT INTO Vehicle (regNumber, make,currentMileage) VALUES (?, ?, ?)");
                    String sql2 = ("INSERT INTO customer (firstName, lastName) VALUES (?, ?)");
                    String sql3 = ("INSERT INTO Booking ( Time, Duration, date, MechanicID ) VALUES (?, ?,?,?)");
                    PreparedStatement stmt = c.prepareStatement(sql);
                    PreparedStatement stmt2 = c.prepareStatement(sql2);
                    PreparedStatement stmt3 = c.prepareStatement(sql3);

                   stmt.setString(1, vehtxt.getText());
                   stmt.setString(2, miltxt.getText());
                   stmt.setString(3, maketxt.getText());

                    stmt2.setString(1, fnametxt.getText());
                   stmt2.setString(2, lnametxt.getText());
                    
                   stmt3.setString(1, bookDate.getText());
                   stmt3.setString(2, ttxt.getText());
                   stmt3.setString(3, dtxt.getText());
                   stmt3.setString(4, mechctxt.getText());


                   stmt.execute(); 
                   stmt2.execute();
                   stmt3.execute();
                    stmt.close();
                   stmt2.close();
                    stmt3.close();
                    c.close();
                    Stage stage = (Stage) submitBookButton.getScene().getWindow();
                    stage.close();
                               
                   
        } catch (Exception z) {
          
            z.printStackTrace(System.out);
            
            }
          });
                        
                    cb1.getSelectionModel().selectedItemProperty().addListener(
                   (observable,oldValue,newValue) ->
                           filter =  newValue );
       
        cb1.setItems(fillUpComboBox1());
    }
//     submitBookButton.setOnAction(e -> {
//                try {
//                    
//                    Class.forName("org.sqlite.JDBC");
//                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
//                    String sql = ("INSERT INTO Booking (ID, BoookingDate, Time , Duration, Firstname,Lastname, VehiculeRegistration, Type, CurrentMileage )");
//                    PreparedStatement stmt = c.prepareStatement(sql);
//                    
//                    stmt.setString(1, dtxt.getText());
//                    stmt.setString(2, custxt.getText());
//                    stmt.setString(3, milTxt.getText());
//                    stmt.setString(4, vehtxt.getText());
//                    stmt.setString(5, pUsedtxt.getText());
//                    
//                  
//                    stmt.execute();
//                    stmt.close();
//                    c.close();
//                    
//                
//                } catch (SQLException ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
//            
/*
            goBackButton.setOnAction(e -> {
                try {
                    gobackButton();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });*/
            
//     submitBookButton.setOnAction(e -> {
//                try {
//                    
//                    Class.forName("org.sqlite.JDBC");
//                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
//                    String sql = ("INSERT INTO Booking (ID, BoookingDate, Time , Duration, Firstname,Lastname, VehiculeRegistration, Type, CurrentMileage )");
//                    PreparedStatement stmt = c.prepareStatement(sql);
//                    
//                    stmt.setString(1, dtxt.getText());
//                    stmt.setString(2, custxt.getText());
//                    stmt.setString(3, milTxt.getText());
//                    stmt.setString(4, vehtxt.getText());
//                    stmt.setString(5, pUsedtxt.getText());
//                    
//                  
//                    stmt.execute();
//                    stmt.close();
//                    c.close();
//                    
//                
//                } catch (SQLException ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
            
    
    
     private ObservableList<String> fillUpComboBox1() {
        ArrayList<String> fillup = new ArrayList<>();
        fillup.add("Diagnosis and Repair");
        fillup.add("Scheduled Maintenance");
     
        
        return FXCollections.observableArrayList(fillup);
    };
   /* 
    @FXML
    public void addBooking(ActionEvent event) throws Exception
    {   
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to add Booking?");
            Optional<ButtonType> action = alert.showAndWait();
            
           
           String btype = ;
            String bdate = bookingdatetext.getEditor().getText();
          int btime = Integer.parseInt(ttxt.getText());
            int dur = Integer.parseInt(dtxt.getText());
            String regnumber = vehtxt.getText();
            String fname = fnametxt.getText();
            String lname = lnametxt.getText();
            int mileage = Integer.parseInt(milTxt.getText());
            String make = maketxt.getText();
          int mechanicID = Integer.parseInt();


            if(action.get() == ButtonType.OK) {
                
                try{

                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

                    String sql = "INSERT INTO BOOKING(ID, BoookingDate, Time , Duration, Firstname,Lastname, VehicleRegistration, Type, CurrentMileage) VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt = c.prepareStatement(sql);
                    

                    stmt.setString(1,null);
                    stmt.setString(2,"string");
                    stmt.setString(3,"string");
                    stmt.setInt(4,4);
                    stmt.setInt(5,5); 
                    stmt.setString(6,"string");
                    stmt.setString(7,"string");
                    stmt.setString(8,"string");
                    stmt.setInt(9,5);
                    stmt.setInt(10,8);
                    
                     stmt.execute();
                    stmt.close();

                    searchTable.setItems(data);
                    loadAllData();
                    JOptionPane.showMessageDialog(null,"Booking has been added sucessfully");
                } catch (Exception e) {
                    System.err.println(e.getClass().getName()+ ": " + e.getMessage());
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
           */     

   
//@FXML
//    public void clickAddtoVehicle(ActionEvent event) throws Exception{
//        
//            String instdate = installdatetext.getEditor().getText();
//            String warranty = warrantydatetext.getEditor().getText();
//            String fname=fnametext.getText();
//            String lname=lnametext.getText();
//            String pname = partnametext.getText();
//            String rnumber = registrationtext.getText();
//            int bookingID=Integer.parseInt(bookingidtext.getText());
//            int customerID = Integer.parseInt(customeridtext.getText());
//
//        try{
//            Class.forName("org.sqlite.JDBC");
//            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
// 
//            //PreparedStatement preparestatement6 = c.prepareStatement("INSERT INTO REPAIR(RepairID,InstallDate,WarrantyDate,FirstName,LastName,RegNumber,BookingID) VALUES (?,?,?,?,?,?,?,?)");
//            PreparedStatement preparestatement = c.prepareStatement("INSERT INTO REPAIR(REPAIRID,INSTALLDATE,WARRANTYDATE,BOOKINGID,CUSTOMERID) VALUES (?,?,?,?,?)");
//            PreparedStatement preparestatement2 = c.prepareStatement("INSERT INTO PARTS(name) VALUES (?)");
//            PreparedStatement preparestatement3 = c.prepareStatement("INSERT INTO customer(FIRSTNAME,LASTNAME) VALUES (?,?)");
//            PreparedStatement preparestatement4 = c.prepareStatement("INSERT INTO Vehicle(regNumber) VALUES (?)");
//            preparestatement.setString(1,null);
//            preparestatement.setString(2,instdate);
//            preparestatement.setString(3,warranty);
//            preparestatement.setInt(4,bookingID);
//            preparestatement.setInt(5,customerID);
//            preparestatement2.setString(1,pname);
//            preparestatement3.setString(1,fname);
//            preparestatement3.setString(2,lname);
//            preparestatement4.setString(1,rnumber);
//
//            preparestatement.execute();
//            preparestatement2.execute();
//            preparestatement3.execute();
//            preparestatement4.execute();
//            preparestatement.close();
//            preparestatement2.close();
//            preparestatement3.close();
//            preparestatement4.close();
//           
//            
//            RepairTable.setItems(data);
//            loadAllData();
//            
//            JOptionPane.showMessageDialog(null,"Part added to vehicle successfully");
//            removeStock();
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//    
//    
    
   public boolean checkSaturday(LocalDate item)
    {
            if(item.getDayOfWeek() == DayOfWeek.SATURDAY)
            {
                return true;
            }
            return false;
    }
    
    private LocalDate getBankHoliday(String item)
    {    
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(item.equals("gFriday"))
        {
            return LocalDate.parse("14/04/2017",formatter);
        }
        else if(item.equals("easterMonday"))
        {
            return LocalDate.parse("17/04/2017",formatter);
        }
        else if(item.equals("earlyMay"))
        {
            return LocalDate.parse("01/05/2017",formatter);
        }
        else if(item.equals("spring"))
        {
            return LocalDate.parse("29/05/2017",formatter);
        }
        else if(item.equals("summer"))
        {
            return LocalDate.parse("28/08/2017",formatter);
        }
        else if(item.equals("christmas"))
        {
            return LocalDate.parse("25/12/2017",formatter);
        }
        else if(item.equals("boxing"))
        {
            return LocalDate.parse("26/12/2017",formatter);
        }
       return null;    
    }
    
    
   


    
    @FXML
    private void goBackButton(ActionEvent event) throws Exception {               

                Stage stage = (Stage) gbButton.getScene().getWindow();
                stage.close();
                
    }
   
    }
    


    
  
   
    
