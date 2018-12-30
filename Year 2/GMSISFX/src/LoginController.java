

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Authentication.AuthDbMethods;
import Authentication.Session;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController implements Initializable {
    
    public Stage stage;
    AuthDbMethods AuthenticationMethods = new AuthDbMethods();

    @FXML
    private TextField usernametxt;
    @FXML
    private PasswordField passtxt;
    @FXML
    private Button loginbtn;
    @FXML
    private Label loginattempt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }    
    
    public void setStage(Stage stage){
        this.stage = stage;
        System.out.println("Stage: "+ stage);
    }

    @FXML
    private void login(ActionEvent event) {
     initLogin();
    }
    
    private void initLogin(){
        
        try {
            boolean result =  AuthenticationMethods.logUserIn(usernametxt.getText(),passtxt.getText());
        if(result)
        {
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("menu.fxml")));
                   
                    Stage stageNow = (Stage) passtxt.getScene().getWindow();
                  
                    
              Parent root = (Parent)loader.load();
         
             Scene scene = new Scene(root);
             
              stageNow.setScene(scene);
                 stageNow.show();
                 
                String timeStamp = returnCurrentDate();
                AuthenticationMethods.setTimeStamp(timeStamp, Session.getId());
         } else {
            loginattempt.setText("Wrong Username or Password, please try again");
        }
        }
        catch(IOException io) {
           io.printStackTrace();
        }
         
        
    }
    
    private String addSuffixToDay(){
        
        // http://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-in-java
         String[] suffixes =
  //    0     1     2     3     4     5     6     7     8     9
     { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
  //    10    11    12    13    14    15    16    17    18    19
       "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
  //    20    21    22    23    24    25    26    27    28    29
       "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
  //    30    31
       "th", "st" };
         Date date = new Date();
          SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
        int day = Integer.parseInt(formatDayOfMonth.format(date));
           String dayAndsuffix = day + suffixes[day];
          
          return dayAndsuffix;
    }
    
    private String returnCurrentDate() {
        String date = "";
         String dayAsWord = new SimpleDateFormat("EEEE").format(new Date());
         
         String dayAsNumberWithSuffix = addSuffixToDay();
         
         String monthAndYear = new SimpleDateFormat("MMMM yyyy  h:mm:ss a").format(new Date());
          
         date = dayAsWord + " "+ dayAsNumberWithSuffix + " " + monthAndYear;
        
        
        return date;
        
    }

    @FXML
    private void onEnter(ActionEvent event) {
        
        initLogin();
    }

    @FXML
    private void onEnterUserName(ActionEvent event) {
        passtxt.requestFocus();
    }


    
}
