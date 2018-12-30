/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Dimension;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
     // AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Home.fxml"));


   
         FXMLLoader loader = new FXMLLoader((getClass().getResource("/Authentication/Login.fxml")));
       Parent root = (Parent)loader.load();
       LoginController LoginCont = (LoginController) loader.getController();
       
      LoginCont.stage = stage;
        Scene scene = new Scene(root);
         final Dimension d = new Dimension(1000, 1000);
           stage.setResizable(false);
           stage.setWidth(d.width);
           stage.setHeight(d.height);
           stage.setScene(scene);
           stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
