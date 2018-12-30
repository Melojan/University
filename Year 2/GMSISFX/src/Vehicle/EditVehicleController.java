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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditVehicleController implements Initializable {

    @FXML
    private TextField modelTextField;
    @FXML
    private TextField regTextField;
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
    private TextField vehicleTypeTextField;
    @FXML
    
    private TextField idTextField;
    @FXML
     
    private Button updateButton;
    
         public void getInfo(int ID, String regNumber, String model, String make, double engineSize, String fuelType, String colour, String mot, String lastService, int mileage, String type){
        
        idTextField.setText(String.valueOf(ID));
        regTextField.setText(regNumber);
        modelTextField.setText(model);
        makeTextField.setText(make);
        engineSizeTextField.setText(String.valueOf(engineSize));
        fuelTypeTextField.setText(fuelType);
        colourTextField.setText(colour);
        MotDateTextField.setText(mot);
        lastServiceTextField.setText(lastService);
        milageTextField.setText(String.valueOf(mileage));
        vehicleTypeTextField.setText(type);

     }
@FXML
    private void updateEdit(ActionEvent event){
              
        if(validateFieldsEdit() && validateVehicleID() && validateCurrentMileage() && validateVehicleType()){
                 try {
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            String sql = "update Vehicle set id=?, regNumber=?, model=?, make=?, engineSize=?, fuelType=?, colour=?,MotRenewalDate=?, lastServiceDate=?, currentMileage=?, vehicleType=? where ID='"+idTextField.getText()+"' ";

                    PreparedStatement stmt = c.prepareStatement(sql);
                    
                    stmt.setString(1, idTextField.getText()); 
                    stmt.setString(2, regTextField.getText());
                    stmt.setString(3, modelTextField.getText());
                    stmt.setString(4, makeTextField.getText());
                    stmt.setString(5, engineSizeTextField.getText());
                    stmt.setString(6, fuelTypeTextField.getText()); 
                    stmt.setString(7, colourTextField.getText()); 
                    stmt.setString(8, MotDateTextField.getText()); 
                    stmt.setString(9, lastServiceTextField.getText()); 
                    stmt.setString(10, milageTextField.getText()); 
                    stmt.setString(11, vehicleTypeTextField.getText()); 

                    stmt.execute();     
                   // stmt.close();
                    c.close();
        } catch (Exception e) {
            System.out.println("error 101");
            e.printStackTrace(System.out);
            System.exit(0);
        }
                Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
        }

    }
          private boolean validateFieldsEdit(){
           if(idTextField.getText().isEmpty() | regTextField.getText().isEmpty() | modelTextField.getText().isEmpty() | makeTextField.getText().isEmpty() | engineSizeTextField.getText().isEmpty()| fuelTypeTextField.getText().isEmpty()| colourTextField.getText().isEmpty()| MotDateTextField.getText().isEmpty()| lastServiceTextField.getText().isEmpty()| milageTextField.getText().isEmpty()| vehicleTypeTextField.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Enter values for the Vehicle detail you wish to edit");
           alert.showAndWait();
           return false;
       }
       return true;
   }
          
           private boolean validateVehicleID()
        {
                    {
            String VID = idTextField.getText();
           
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(VID);
            if(m.find() && m.group().equals(VID))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Vehicle ID");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Vehicle ID");
                    alert.showAndWait();
                  
                return false;
            }
                    
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
                    alert.setTitle("Validate Vehicle Mileage");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Vehicle Mileage");
                    alert.showAndWait();
                  
                return false;
            }
        }
                                                                private boolean validateVehicleType()
        {
            String vehicleType = vehicleTypeTextField.getText();
           
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
       
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
