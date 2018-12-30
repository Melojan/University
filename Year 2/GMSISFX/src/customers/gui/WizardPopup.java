/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;




import customers.logic.Customer;
import customers.logic.dbmethods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
//import org.controlsfx.validation.Validator;

// This is used to store locally the files from the survey
class DummyCustomer {
  IntegerProperty custType = new SimpleIntegerProperty();
    // this is a little hack to get the vaue of the radio button
  // then run an if statement and convert it to 1 or 2 for custType
  StringProperty RadioButtonForCustType = new SimpleStringProperty(); 
  StringProperty firstName = new SimpleStringProperty();
  StringProperty lastName = new SimpleStringProperty();
  StringProperty email = new SimpleStringProperty();
  StringProperty phone = new SimpleStringProperty();
  StringProperty address = new SimpleStringProperty();
  static DummyCustomer instance = new DummyCustomer();
}


public class WizardPopup {
    boolean disable = false;
private int newlyInsertedCustomer = 0;

private final ComboBox<StageStyle> styleCombobox = new ComboBox<>();
private final ComboBox<Modality> modalityCombobox = new ComboBox<>();
private final CheckBox cbUseBlocking = new CheckBox();
private final CheckBox cbCloseDialogAutomatically = new CheckBox();
private final CheckBox cbShowMasthead = new CheckBox();
private final CheckBox cbSetOwner = new CheckBox();
private final CheckBox cbCustomGraphic = new CheckBox();
        private RadioButton Individual;
        private RadioButton Business;
        private ToggleGroup options = new ToggleGroup();
         

private Stage stage;



public WizardPopup(){
    super();
    
}






public int showLinearWizard() {
   
    WizardPane page1 = new WizardPane();
    WizardPane page2 = new WizardPane();
    WizardPane page3 = new WizardPane();
     disableButtonBar(page1, true);
    
    
    // define pages to show

    Wizard wizard = new Wizard();
    
    wizard.setTitle("New Customer");

    // --- page 1
    int row = 0;
    
    TextField fNameTx = new TextField();
    TextField lNameTx = new TextField();
    TextField[] txtArr = {fNameTx,lNameTx};
    GridPane page1Grid = new GridPane();
    page1Grid.setVgap(10);
    page1Grid.setHgap(1);
    
    Addlistener(page1, txtArr);
    Label fNamelbl = new Label("First Name");
    DummyCustomer.instance.firstName.bind(fNameTx.textProperty());

     //fNameTx.setPromptText("Please enter the First Name");
    ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(fNameTx, Validator.createEmptyValidator("Text is required"));
    
    page1Grid.add(fNamelbl, 1, row);
    page1Grid.add(fNameTx, 1, row+1);
     Label lNamelbl = new Label("Last Name");
     
     Addlistener(page1, txtArr);
     DummyCustomer.instance.lastName.bind(lNameTx.textProperty());
 
      page1Grid.add(lNamelbl, 1, row+2);
      page1Grid.add(lNameTx, 1, row+3);

    


   
    
    page1.setContent(page1Grid);
    
  
    page1.setHeaderText("Please Enter Your Details");
    
 
   
   

    
    // --- page 2
   
    
    GridPane page2Grid = new GridPane();
    page2Grid.setVgap(10);
    page2Grid.setHgap(1);
    
            Individual = new RadioButton("Individual");
      Business = new RadioButton("Business");
      Individual.setToggleGroup(options);
      Business.setToggleGroup(options);
    options.selectToggle(Business);
     DummyCustomer.instance.custType.setValue(2);
      page2Grid.add(Individual, 1, row);
      page2Grid.add(Business, 1, row+1);
  options.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> observableValue,
          Toggle oldToggle, Toggle newToggle) {
            if (newToggle == Business){
                // setCustType to 2
                DummyCustomer.instance.custType.setValue(2);
            } else{
                DummyCustomer.instance.custType.setValue(1);
            }
      }
    });
    
    
    
    
    
    
    
    
     page2.setHeaderText("Type Of Business");
    page2.setContent(page2Grid);
     
     TextField emailTx = new TextField("Example@gmail.com");
      TextField phoneTx = new TextField("07982759148");
    TextField[] txtArr2 = {emailTx,phoneTx};
   TextArea addressTx = new TextArea("1 Downing Streeet \n"+"London \n"+"E1 7HW");
     EmailPhoneVerify(page3,txtArr2,addressTx);
    // --- page 3
   GridPane page3Grid = new GridPane();
    page3Grid.setVgap(10);
    page3Grid.setHgap(10);
    
    Label emailLbl = new Label("Email");
   
     
     page3Grid.add(emailLbl, 1, row);
    page3Grid.add(emailTx, 1, row+1);
    DummyCustomer.instance.email.bind(emailTx.textProperty());
    
    Label phoneLbl = new Label("Phone Number");
     
    
     page3Grid.add(phoneLbl, 1, row+2);
      page3Grid.add(phoneTx, 1, row+3);
      DummyCustomer.instance.phone.bind(phoneTx.textProperty());
      
      Label addressLbl = new Label("Full Address");
     
     
     DummyCustomer.instance.address.bind(addressTx.textProperty());
     System.out.println(DummyCustomer.instance.address.getValue());
     page3Grid.add(addressLbl, 1, row+4);
     page3Grid.add(addressTx, 1, row+5);
     
    
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
            System.out.println(DummyCustomer.instance.custType.getValue());
            System.out.println(DummyCustomer.instance.phone.getValue());
            System.out.println(DummyCustomer.instance.email.getValue());
            System.out.println(DummyCustomer.instance.address.getValue());
            Customer customer = new Customer();
            dbmethods dbm = new dbmethods();
            boolean isEmpty = checkIfAnyFieldIsEmpty();
            
            if (isEmpty){
                System.out.println("Found an empty field");
                popup emptyFieldPopup = new popup("Please fill in all of the fields");
            } else{
                 newlyInsertedCustomer = dbm.add_customer(DummyCustomer.instance.firstName.getValue(),DummyCustomer.instance.lastName.getValue(),DummyCustomer.instance.custType.getValue() ,DummyCustomer.instance.address.getValue(),DummyCustomer.instance.phone.getValue(), DummyCustomer.instance.email.getValue());
            }
           
            
        }
    });
    return newlyInsertedCustomer;
}


private boolean checkIfAnyFieldIsEmpty(){
    if (DummyCustomer.instance.firstName.getValue().isEmpty()){
        System.out.println(1);
        return true;
    } else if(DummyCustomer.instance.lastName.getValue().isEmpty()){
        System.out.println(2);
        return true;
    }else if((DummyCustomer.instance.custType.getValue()!=1) & (DummyCustomer.instance.custType.getValue()!=2)){
        System.out.println(3);
        return true;
    }else if(DummyCustomer.instance.phone.getValue().isEmpty()){
        System.out.println(4);
        return true;
    }else if(DummyCustomer.instance.email.getValue().isEmpty()){
        return true;
    }else if(DummyCustomer.instance.address.getValue().isEmpty()){
        System.out.println(5);
        return true;
    } else{
        return false;
    }
    
    
    
}


private void disableButtonBar(WizardPane page,boolean bool){
        for (Node nd : page.getChildren()) {
      if (nd.getClass() == ButtonBar.class) {
          nd.setDisable(bool);
      }
    }
}

public static boolean containsWhiteSpace(String line){
    boolean space= false; 
    if(line != null){


        for(int i = 0; i < line.length(); i++){

            if(line.charAt(i) == ' '){
            space= true;
            }

        }
    }
    return space;
}
private void Addlistener(WizardPane page, TextField[] txtArr) {
    
    TextField fn = txtArr[0];
    TextField ln = txtArr[1];
    
    
  
         fn.textProperty().addListener((obv,oldv,newv) -> {
        boolean allLetters = fn.getText().chars().allMatch(Character::isLetter);
         boolean allLetters2 = ln.getText().chars().allMatch(Character::isLetter);
            
            if ((allLetters)&& (!containsWhiteSpace(fn.getText())) && (!fn.getText().equals(""))&&(allLetters2)&& (!containsWhiteSpace(ln.getText())) && (!ln.getText().equals(""))) {
               disableButtonBar(page, false);
                
            }
            else {
               disableButtonBar(page, true);
            }
    });
               ln.textProperty().addListener((obv,oldv,newv) -> {
        boolean allLetters = fn.getText().chars().allMatch(Character::isLetter);
         boolean allLetters2 = ln.getText().chars().allMatch(Character::isLetter);
            
            if ((allLetters)&& (!containsWhiteSpace(fn.getText())) && (!fn.getText().equals(""))&&(allLetters2)&& (!containsWhiteSpace(ln.getText())) && (!ln.getText().equals(""))) {
               disableButtonBar(page, false);
                
            }
            else {
               disableButtonBar(page, true);
            }
    });
    
    
   
}
private void EmailPhoneVerify(WizardPane page, TextField[] txtArr,TextArea addressP) {
    
    TextField email = txtArr[0];
    TextField phone = txtArr[1];
    TextArea address = addressP;
    
    
    
    
     address.textProperty().addListener((obv,oldv,newv) -> {
       
         String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email.getText());
            String regexStr = "^[0-9]*$";
            
            if ( !((address.getText().equals("") ) && (address.getText().isEmpty())) && (m.matches()) && (phone.getText().matches(regexStr))) {
               disableButtonBar(page, false);
            }
            else {
                disableButtonBar(page, true);
            }
    });
    email.textProperty().addListener((obv,oldv,newv) -> {
       
         String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email.getText());
            String regexStr = "^[0-9]*$";
            
            if  ( !((address.getText().equals("") ) && (address.getText().isEmpty())) && (m.matches()) && (phone.getText().matches(regexStr))) {
               disableButtonBar(page, false);
            }
            else {
                disableButtonBar(page, true);
            }
    });
    
       phone.textProperty().addListener((obv,oldv,newv) -> {
       
          String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email.getText());
            String regexStr = "^[0-9]*$";
            
            if  ( !((address.getText().equals("") ) && (address.getText().isEmpty())) && (m.matches()) && (phone.getText().matches(regexStr))) {
               disableButtonBar(page, false);
            }
            else {
                disableButtonBar(page, true);
            }
           
            
          
    });
}


}
