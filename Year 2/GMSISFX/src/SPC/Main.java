/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
       @Override
    public void start(Stage stage) throws Exception
    {
    Parent root = FXMLLoader.load(getClass().getResource("SPC.fxml"));
    stage.setTitle("GMSIS");
    stage.setScene(new Scene(root));
    stage.setResizable(false);
    stage.show();
    }
    
    public static void main (String[] args)
    {
        launch(args);    
    }
    
}
