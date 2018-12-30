/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;




import customers.gui.*;
import customers.logic.Customer;
import customers.logic.dbmethods;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;
//import org.controlsfx.validation.Validator;

// This is used to store locally the files from the survey
class DummyCustomer {
  IntegerProperty userType = new SimpleIntegerProperty();
    // this is a little hack to get the vaue of the radio button
  // then run an if statement and convert it to 1 or 2 for custType
  StringProperty RadioButtonForCustType = new SimpleStringProperty(); 
  StringProperty firstName = new SimpleStringProperty();
  StringProperty password = new SimpleStringProperty();
  StringProperty lastName = new SimpleStringProperty();
  
  static DummyCustomer instance = new DummyCustomer();
}


public class WizardPopupAdmin {
    
private int newlyInsertedUser = 0;

private final ComboBox<StageStyle> styleCombobox = new ComboBox<>();
private final ComboBox<Modality> modalityCombobox = new ComboBox<>();
private final CheckBox cbUseBlocking = new CheckBox();
private final CheckBox cbCloseDialogAutomatically = new CheckBox();
private final CheckBox cbShowMasthead = new CheckBox();
private final CheckBox cbSetOwner = new CheckBox();
private final CheckBox cbCustomGraphic = new CheckBox();
        private RadioButton administrator;
        private RadioButton systemUser;
        private ToggleGroup options = new ToggleGroup();

private Stage stage;



public WizardPopupAdmin(){
    super();
    
}







public int showLinearWizard() {
    
    // define pages to show

    Wizard wizard = new Wizard();
    
    wizard.setTitle("New User");

    // --- page 1
    int row = 0;

    GridPane page1Grid = new GridPane();
    page1Grid.setVgap(10);
    page1Grid.setHgap(1);
    TextField fNameTx = new TextField();
    Label fNamelbl = new Label("First Name");
    DummyCustomer.instance.firstName.bind(fNameTx.textProperty());

     //fNameTx.setPromptText("Please enter the First Name");
    
    
    page1Grid.add(fNamelbl, 1, row);
    page1Grid.add(fNameTx, 1, row+1);
     Label lNamelbl = new Label("Last Name");
     TextField lNameTx = new TextField();
     DummyCustomer.instance.lastName.bind(lNameTx.textProperty());
  //   lNameTx.setPromptText("Please enter the Last Name");
      page1Grid.add(lNamelbl, 1, row+2);
      page1Grid.add(lNameTx, 1, row+3);

    


    WizardPane page1 = new WizardPane();
    
   
    page1.setHeaderText("Please Enter Your Details");
    
    
    page1.setContent(page1Grid);

    // --- page 2
   
    
    GridPane page2Grid = new GridPane();
    page2Grid.setVgap(10);
    page2Grid.setHgap(1);
    
            administrator = new RadioButton("Administrator");
      systemUser = new RadioButton("System User");
      administrator.setToggleGroup(options);
      systemUser.setToggleGroup(options);
    
      page2Grid.add(administrator, 1, row);
      page2Grid.add(systemUser, 1, row+1);
  options.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> observableValue,
          Toggle oldToggle, Toggle newToggle) {
            if (newToggle == systemUser){
                // setCustType to 2
                DummyCustomer.instance.userType.setValue(2);
            } else{
                DummyCustomer.instance.userType.setValue(1);
            }
      }
    });
    
    
    
    
    
    
    
    WizardPane page2 = new WizardPane();
     page2.setHeaderText("Choose a password");
    page2.setContent(page2Grid);
     
    

    // --- page 3
   GridPane page3Grid = new GridPane();
    page3Grid.setVgap(10);
    page3Grid.setHgap(10);
    
    String fName = DummyCustomer.instance.firstName.getValue();
    System.out.println(fName);
    
    Label passLbl = new Label("Please choose a password for your new user");
    TextField passTx = new TextField();
     page3Grid.add(passLbl, 1, row);
    page3Grid.add(passTx, 1, row+1);
    DummyCustomer.instance.password.bind(passTx.textProperty());
    

    WizardPane page3 = new WizardPane();
    page3.setHeaderText("Final Step");
    page3.setContent(page3Grid);
    
    
  
    // create wizard
    wizard.setFlow(new LinearFlow(page1, page2, page3));


    // show wizard and wait for response
    wizard.showAndWait().ifPresent(result -> {
        if (result == ButtonType.FINISH) {
            System.out.println(result.getButtonData());
            System.out.println("Wizard finished, settings: " + wizard.getSettings());
            System.out.println(DummyCustomer.instance.firstName.getValue());
            System.out.println(DummyCustomer.instance.lastName.getValue());
            System.out.println(DummyCustomer.instance.userType.getValue());
            
            AuthDbMethods adb = new AuthDbMethods();
            boolean isEmpty = checkIfAnyFieldIsEmpty();
            
            if (isEmpty){
               
                popup emptyFieldPopup = new popup("Please fill in all of the fields");
            } else{
                 //newlyInsertedCustomer = dbm.add_customer(DummyCustomer.instance.firstName.getValue(),DummyCustomer.instance.lastName.getValue(),DummyCustomer.instance.custType.getValue() ,DummyCustomer.instance.address.getValue(),DummyCustomer.instance.phone.getValue(), DummyCustomer.instance.email.getValue());
                newlyInsertedUser = adb.add_user(DummyCustomer.instance.firstName.getValue(), DummyCustomer.instance.lastName.getValue(), DummyCustomer.instance.password.getValue(), DummyCustomer.instance.userType.getValue());
                System.out.println(newlyInsertedUser);
            }
           
            
        }
    });
    return newlyInsertedUser;
}



private boolean checkIfAnyFieldIsEmpty(){
    if (DummyCustomer.instance.firstName.getValue().isEmpty()){
        System.out.println(1);
        return true;
    } else if(DummyCustomer.instance.lastName.getValue().isEmpty()){
        System.out.println(2);
        return true;
    }else if((DummyCustomer.instance.userType.getValue()!=1) & (DummyCustomer.instance.userType.getValue()!=2)){
        System.out.println(3);
        return true;
    }else if(DummyCustomer.instance.password.getValue().isEmpty()){
        System.out.println(4);
        return true;
    } else{
        return false;
    }
    
    
    
}
private TextField createTextField(String id) {
    TextField textField = new TextField();
    textField.setId(id);
    GridPane.setHgrow(textField, Priority.ALWAYS);
    return textField;
}

}
