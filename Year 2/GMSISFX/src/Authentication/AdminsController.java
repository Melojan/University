/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import customers.gui.popup;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
public class AdminsController implements Initializable {

    AuthDbMethods adb = new AuthDbMethods();
    ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> admintbv;
    @FXML
    private TableColumn<Admin, Integer> id;
    @FXML
    private TableColumn<Admin, String> fName;
    @FXML
    private TableColumn<Admin, String> lName;
    @FXML
    private TableColumn<Admin, StringProperty> Type;
    @FXML
    private TableColumn<Admin, String> lastLogin;
    @FXML
    private Label welcome;
    


    /// USE SESSION ID and SESSION TYPE to identify the current USER
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
          fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
          lName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
          lastLogin.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));
          
         
          if (Session.getType()==1) {
               fName.setCellFactory(TextFieldTableCell.forTableColumn());
          fName.setOnEditCommit(new AdminsController.EventHandlerImpl());
          lName.setCellFactory(TextFieldTableCell.forTableColumn());
          lName.setOnEditCommit(new AdminsController.EventHandlerImpl());
            setupComboBoxForAdminType();
          setupContextMenu();
          admintbv.setEditable(true);
          } else {
              Type.setCellValueFactory(new PropertyValueFactory<>("type"));
          }
         
          
          
        
         
        
          String type = "";
          if(Session.getType() == 1) {
              type = "Administrator";
          } else {
              type = "System User";
          }
          
          
          
          welcome.setText("   Welcome "+ Session.getFirstName() + ". You are an " + type);
          
        adminList = adb.getAllAdmins();

        admintbv.setItems(adminList);
        
        
    }    

    @FXML
    private void HomeBtn() throws Exception{
              Stage stage = (Stage) this.welcome.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void getActive(ActionEvent event) {
        admintbv.setItems(adb.getActiveAdmins());
    }

    
    
    /// event handlers
    
        private static class EventHandlerImpl implements EventHandler<TableColumn.CellEditEvent<Admin, String>> {

        public EventHandlerImpl() {
        }

        @Override
        public void handle(TableColumn.CellEditEvent<Admin, String> t) {
          
           String columnName = t.getTableColumn().textProperty().getValue();
           if (columnName.equals("First Name")) {
               
               t.getRowValue().setFirstName(t.getNewValue());
           } else { // trying to edit lastName
               t.getRowValue().setLastName(t.getNewValue());
           }
        }
    }
        
        private void setupComboBoxForAdminType(){
            
            try{
            
                   String Admina = "Admin";
            String SysUser = "System User";
             ObservableList<String> cbValues = FXCollections.observableArrayList(SysUser,Admina );

                            Type.setCellValueFactory(i -> {
        final StringProperty value = i.getValue().optionProperty();
        // binding to constant value
        return Bindings.createObjectBinding(() -> value);
    });
                                            Type.setCellFactory(col -> {
        TableCell<Admin, StringProperty> c = new TableCell<>();
        final ComboBox<String> comboBox = new ComboBox<>(cbValues);
       
         
       
        c.itemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                comboBox.valueProperty().unbindBidirectional(oldValue);
                    System.out.println("changed");
            }
            if (newValue != null) {
                comboBox.valueProperty().bindBidirectional(newValue);
                      Object AdminRow =  c.getTableRow().getItem();
                      
                       Admin AdRow = (Admin) AdminRow;
                       // Check for error if AdminRow is null
                       if (AdRow.getId()==12345){
                           comboBox.getSelectionModel().selectLast();
                           comboBox.setDisable(true);
                       } else{
                             if (AdRow.getType().equals(Admina) ){
                            comboBox.getSelectionModel().selectLast();
                                                System.out.println("changed");

                       } else{
                                comboBox.getSelectionModel().selectFirst();
                       }
                       comboBox.setOnAction(e -> AdRow.setType(comboBox.getValue())); 
                       }
                     
                      
                
                
                          
                           

            }
        });
        c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
        return c;
    });
        } catch(Exception ex) {  
            System.out.println("Row deleted, do not worry");
            
        }
    }
        private void setupContextMenu(){
            
            MenuItem mi1 = new MenuItem("Remove User");
            MenuItem mi2 = new MenuItem("Add User");
            MenuItem mi3 = new MenuItem("Change Password");
            
             mi1.setOnAction((ActionEvent event) -> {
  
          Admin adminSelected = (Admin) admintbv.getSelectionModel().getSelectedItem();
          if (adminSelected.getId() == Session.getId()){
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
            alert.setHeaderText(null);
                alert.setContentText("Please contact a system admin to remove your own account.");

            alert.showAndWait();
              
          } else if(adminSelected.getId()==12345){
                         Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
            alert.setHeaderText(null);
                alert.setContentText("Sorry,Cannot delete the Elevated Admin");

            alert.showAndWait();
          }
           else {
                        adb.removeUser(adminSelected.getId());
                        adminList.remove(adminSelected);
          }

    
  
});
             mi2.setOnAction((ActionEvent event) -> {
             // add new user
             WizardPopupAdmin newUser = new WizardPopupAdmin();
           int newlyInsertedId =  newUser.showLinearWizard();
           if (newlyInsertedId == 0) {
               // missing fields or for some reason, did not insert into db
           } else {
               adminList.add(adb.findAdmin(newlyInsertedId));
           }
           
    
  
});
             mi3.setOnAction((ActionEvent event) -> {
             // chnage password
             Admin adminSelected = admintbv.getSelectionModel().getSelectedItem();
             int id = adminSelected.getId();
             String name = adminSelected.getFirstName();
             popup popUp = new popup(id,name);
           
    
  
});
            
            ContextMenu menu = new ContextMenu();
            menu.getItems().addAll(mi1,mi2,mi3);
            admintbv.setContextMenu(menu);
        }
    
}


