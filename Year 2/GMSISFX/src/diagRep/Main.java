package diagRep;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



 
public class Main extends Application{

    public static void main(String[] args)
    {
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    try{
        Parent root = FXMLLoader.load(getClass().getResource("/diagRep/GUI/BookingTable.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("GMSIS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    catch(IOException e ){
        e.printStackTrace();
        }
    }
    
}
