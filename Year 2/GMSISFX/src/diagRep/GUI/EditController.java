/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class EditController implements Initializable {

    @FXML
    private TextField booktIDField;
    @FXML
    private TextField booktypeField;
    @FXML
    private TextField booktDateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField mileageField;
    @FXML
    private TextField mechnaicField;
    @FXML
    private TextField makeField;
    @FXML
    
    private TextField regField;
    @FXML
     
    private Button updateButton;
   
    
    
   public void getInfo(int bookingId, String Type, String date, int Time, int dur, String vehReg, String firName, String surName, int mileage, int mechID, String ma) 
{
           
        booktIDField.setText(String.valueOf(bookingId));
        booktypeField.setText(Type);
        booktDateField.setText(date);
        timeField.setText(String.valueOf(Time));
        durationField.setText(String.valueOf(dur));
        regField.setText(vehReg);
        firstnameField.setText(firName);
        lastnameField.setText(surName);
        mileageField.setText(String.valueOf(mileage));
        mechnaicField.setText(String.valueOf(mechID));
        makeField.setText(ma);
  
    } 

    
    @FXML
    private void updateBooking(ActionEvent event){
              
       if(validateFieldsEdit()){
                 try {
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            String sql = "update Booking set id=?, bookingType=?, date=?, time=?, Duration=?, regNumber=?, firstName=?, lastName=?, currentMileage=?, MechanicID=?, make=? where ID='"+booktIDField.getText()+"' ";

                    PreparedStatement stmt = c.prepareStatement(sql);
                    
                    stmt.setString(1, booktIDField.getText()); 
                    stmt.setString(2, booktypeField.getText());
                    stmt.setString(3, booktDateField.getText());
                    stmt.setString(4, timeField.getText());
                    stmt.setString(5, durationField.getText());
                    stmt.setString(6, regField.getText()); 
                    stmt.setString(7, firstnameField.getText()); 
                    stmt.setString(8, lastnameField.getText()); 
                    stmt.setString(9, mileageField.getText()); 
                    stmt.setString(10, mechnaicField.getText()); 
                    stmt.setString(11, makeField.getText()); 

                    stmt.execute();     
                    stmt.close();
                    c.close();
        } catch (Exception e) {
            System.out.println("error 101");
           // e.printStackTrace(System.out);
            System.exit(0);
        }
                Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
        }

    }
    
    
   
   private boolean validateFieldsEdit(){
           if(booktIDField.getText().isEmpty() | booktypeField.getText().isEmpty() | booktDateField.getText().isEmpty() | timeField.getText().isEmpty() | durationField.getText().isEmpty()| regField.getText().isEmpty()| firstnameField.getText().isEmpty()| lastnameField.getText().isEmpty()| mileageField.getText().isEmpty()| mechnaicField.getText().isEmpty()| makeField.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Enter values for the Booking detail you wish to edit");
           alert.showAndWait();
           return false;
       }
       return true;
   }
   
   
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    
    }    
    
}
    

