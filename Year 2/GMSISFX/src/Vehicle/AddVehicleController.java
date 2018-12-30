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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVehicleController implements Initializable {

    @FXML
    private TextField regTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField makeTextField;
    @FXML
    private TextField engineSizeTextField;
    @FXML
    private TextField fuelTypeTextField;
    @FXML
    private TextField colourTextField;
    @FXML
    private TextField MotDateTextField;
    @FXML
    private TextField lastServiceTextField;
    @FXML
    private TextField milageTextField;
    @FXML
    private TextField vehicleTypeField;
    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;
    @FXML

    private TextField warrantyIDTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField expiryDateTextField;

   
    @FXML
    private ComboBox<?> vehicleComboBox;

       @FXML
          public void selectVehicle(ActionEvent event) {
                  try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "select * from VehicleTemplate where Model = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, (String)vehicleComboBox.getSelectionModel().getSelectedItem());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelTextField.setText(rs.getString("Model"));
                makeTextField.setText(rs.getString("Make"));
                engineSizeTextField.setText(rs.getString("EngineSize"));
                fuelTypeTextField.setText(rs.getString("FuelType"));
            }
 

           // stmt.close();
          //  c.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(0);
        }
                  
    }
@FXML
        private Button addWarrantyButton;

    @FXML
    void addWarrantyAction(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddWarranty.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
  }
     @FXML
    private void cancelAction(ActionEvent event) {               

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                
    }

    /**
     * Initializes the controller class.
     */
        private final ObservableList comboOption = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addButton.setOnAction((ActionEvent e) -> {
   
 
                    
     if( validateCurrentMileage() && validateVehicleType()){
   
                  try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = ("INSERT INTO Vehicle (regNumber, model, make, engineSize, fuelType, colour, MotRenewalDate, lastServiceDate, currentMileage, vehicleType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                   PreparedStatement stmt = c.prepareStatement(sql);
                   
                   stmt.setString(1, regTextField.getText());
                   stmt.setString(2, modelTextField.getText());
                   stmt.setString(3, makeTextField.getText());
                   stmt.setString(4, engineSizeTextField.getText());
                   stmt.setString(5, fuelTypeTextField.getText());
                   stmt.setString(6, colourTextField.getText());
                   stmt.setString(7, MotDateTextField.getText());
                   stmt.setString(8, lastServiceTextField.getText());
                   stmt.setString(9, milageTextField.getText());
                   stmt.setString(10, vehicleTypeField.getText());

                    stmt.execute();     
                    stmt.close();
                    c.close();
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                   
        } catch (Exception z) {
          
            z.printStackTrace(System.out);
            
            }
                fillComboBox();
     }
});

                fillComboBox();
                vehicleComboBox.setItems(comboOption);

    }
    
        public void fillComboBox(){
        comboOption.clear();
        
          try {
             Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
 
            String sql = "select * from VehicleTemplate;";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            comboOption.add(rs.getString("Model"));
               
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }
                               private boolean validateCurrentMileage()
        
                    {
            String mileage = milageTextField.getText();
           
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(mileage);
            if(m.find() && m.group().equals(mileage))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Vehicle ID");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Vehicle Mileage");
                    alert.showAndWait();
                  
                return false;
            }
    
                    }
                                         private boolean validateVehicleType()
        {
            String vehicleType = vehicleTypeField.getText();
           
             Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(vehicleType);
            if(m.find() && m.group().equals(vehicleType))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Vehicle Type ");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Vehicle Type");
                    alert.showAndWait();
                  
                return false;
            }
        }
}
    

