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


public class AddWarrantyController implements Initializable {

    //@FXML
   // private TextField warrantyIDTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField expiryDateTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                addButton.setOnAction((ActionEvent e) -> {
            if(validateAddress()){

                  try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = ("INSERT INTO Warranty (name, address, dateOfExpiry) VALUES ( ?, ?, ?)");
                   PreparedStatement stmt = c.prepareStatement(sql);
                   
                  // stmt.setString(1, warrantyIDTextField.getText());
                   stmt.setString(1, nameTextField.getText());
                   stmt.setString(2, addressTextField.getText());
                   stmt.setString(3, expiryDateTextField.getText());
                   

                    stmt.execute();     
                    stmt.close();
                    c.close();
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                   
        } catch (Exception z) {
          
            z.printStackTrace(System.out);
            
            }
            }
});
    }    
    @FXML
            private void cancelAction(ActionEvent event) {               

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                
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
    }
    
