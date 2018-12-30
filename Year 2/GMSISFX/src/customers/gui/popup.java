/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;

import Authentication.AuthDbMethods;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;


public class popup {
    
    AuthDbMethods adb = new AuthDbMethods();
    boolean success = false;
    
    public popup(String message){
        popupBox(message);
    }
    public popup(int id, String name){ // to change user password
        changePass(id,name);
        // give popup userId, get text from textField and send to database
    }
    
    
    private void popupBox(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    private void messageBox(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
   
    private void changePass(int id, String name) {
        
        // Add a confirm password box, if time permits
        TextInputDialog txtIn = new TextInputDialog(); 
        txtIn.setTitle("New Password");
        txtIn.setContentText("Please enter the new password for " + name);
        Optional<String> result = txtIn.showAndWait();
        result.ifPresent(pass -> {
                 success = adb.update_password(pass,id);
                 
        });
        
        if(success) {
            messageBox("Password successfully changed!");
        } else {
            popupBox("Password not changed, please try again.");
        }
        
    }
    
}
