/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Authentication.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MenuController implements Initializable {

    
 
    
    @FXML
    private AnchorPane ap;
    @FXML
    private Button spc;
    @FXML
    private Button dandr;
    @FXML
    private Button customer;
    @FXML
    private Button vehicle;
    @FXML
    private Button parts;
    @FXML
    private Button admin;
    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // this.stage = (Stage) ap.getScene().getWindow();
      
     
    }   
    
    @FXML
    void GoTo1(ActionEvent event) throws Exception{
        Stage stage = (Stage)ap.getScene().getWindow();
        Object source = event.getSource();
        Parent root;
        if (source instanceof Button) { //should always be true in your example
             Button clickedBtn = (Button) source; // that's the button that was clicked
         System.out.println(clickedBtn.getId()); // prints the id of the button
            switch (clickedBtn.getId()) {
                case "customer":
                    root = FXMLLoader.load(getClass().getResource("customers/gui/FXMLDocument.fxml"));
                    break;
                case "spc":
                    root = FXMLLoader.load(getClass().getResource("SPC/SPC.fxml"));
                    break;
                case "admin":
                    root = FXMLLoader.load(getClass().getResource("Authentication/Admins.fxml"));
                    break;
                case "vehicle":
                    root = FXMLLoader.load(getClass().getResource("Vehicle/FXMLDocument.fxml"));
                    break;
                case "parts":
                    root = FXMLLoader.load(getClass().getResource("Parts/menu.fxml"));
                    break;
                case "dandr":
                    root = FXMLLoader.load(getClass().getResource("diagRep/GUI/BookingTable.fxml"));
                    break;
                default:
                    root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    break;
            }
         
        
    
            Scene scene = new Scene(root);
 
            stage.setScene(scene);
           
            stage.show();
    
        }
        
        
     
    
    
  
            
    }

    @FXML
    private void logout(ActionEvent event) throws Exception{
        Session.Logout();
                            FXMLLoader loader = new FXMLLoader((getClass().getResource("Authentication/Login.fxml")));
                   
                    Stage stage = (Stage) logoutButton.getScene().getWindow();
                  
                    
              Parent root = (Parent)loader.load();
         
             Scene scene = new Scene(root);
        
              stage.setScene(scene);
                 stage.show();
    }

    
}
