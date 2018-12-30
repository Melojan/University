/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;


public class ConfirmPopup {
    
    //http://code.makery.ch/blog/javafx-dialogs-official/
    public ConfirmPopup(){
        
    }
    
    public boolean showAlert(String firstName, String lastName){
                Alert alert = new Alert(AlertType.CONFIRMATION);
      //  alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Delete");
        alert.setContentText("Please Confirm that you would like to delete "+firstName+" "+lastName);

        



Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    
    return true;
    // ... user chose "One"
} else {
    return false;
    // ... user chose CANCEL or closed the dialog
}

        
    }
    
    
}
