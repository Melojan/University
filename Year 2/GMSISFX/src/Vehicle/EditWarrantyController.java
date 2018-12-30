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

public class EditWarrantyController implements Initializable {

    @FXML
    private Button updateButton;
    @FXML
    private TextField warrantyIDTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField expiryDateTextField;
    
         public void getWInfo(int id, String name, String address, String dateOfExpiry){
        
        warrantyIDTextField.setText(String.valueOf(id));
        nameTextField.setText(name);
        addressTextField.setText(address);
        expiryDateTextField.setText(dateOfExpiry);

     }
         @FXML
          private void updateEdit(ActionEvent event){
              
        if(validateFieldsEdit() && validateAddress()){
                 try {
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            String sql = "update Warranty set id=?, name=?, address=?, dateOfExpiry=? where ID='"+warrantyIDTextField.getText()+"' ";

                    PreparedStatement stmt = c.prepareStatement(sql);
                    
                    stmt.setString(1, warrantyIDTextField.getText()); 
                    stmt.setString(2, nameTextField.getText());
                    stmt.setString(3, addressTextField.getText());
                    stmt.setString(4, expiryDateTextField.getText());
              

                    stmt.execute();     
                   // stmt.close();
                    c.close();
        } catch (Exception e) {
            System.out.println("error 11");
            e.printStackTrace(System.out);
            System.exit(0);
        }
                                 Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
        }
        
           }
         
          private boolean validateFieldsEdit(){
           if(warrantyIDTextField.getText().isEmpty() | nameTextField.getText().isEmpty() | addressTextField.getText().isEmpty() | expiryDateTextField.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Enter values for the Warranty detail you wish to edit");
           alert.showAndWait();
           return false;
       }
       return true;
   }
          private boolean validateAddress()
        {
            String address = addressTextField.getText();
           
             Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(address);
            if(m.find() && m.group().equals(address))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Warranty Address");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Address");
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
