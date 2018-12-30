/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;


import customers.logic.Bookings;
import customers.logic.Customer;
import customers.logic.dbmethods;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class FXMLDocumentController implements Initializable {

    
    ObservableList<Customer> customer = FXCollections.observableArrayList();
    ObservableList<Bookings> bookingsList = FXCollections.observableArrayList();
    dbmethods dbm = new dbmethods();
    Customer selectedCustomer;

    
    
    
    @FXML
    private ComboBox<String> typeComboBox;
        // TableView on the left for customer Data
    @FXML
    private TableView<Customer> tbv =  new TableView();
    @FXML
    private TableColumn<Customer, Double> balanceColumn;
    @FXML
    private TableColumn<Customer, String> fnameColumn;
    @FXML
    private TableColumn<Customer, String> lnameColumn;
    @FXML
    private TableColumn<Customer, StringProperty> typecust;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    
    // TableView on the right for booking dtata for a specifc customer
    @FXML
    private TableView<Bookings> bookingtbv;

    @FXML
    private TableColumn<Bookings, Integer> bookid;

    @FXML
    private TableColumn<Bookings, String> name;

    @FXML
    private TableColumn<Bookings, String> bookcost;
    @FXML
    private TableColumn<Bookings, Boolean> paid;
    @FXML
    private TableColumn<Bookings, String> date;
    @FXML
    private TextField searchTxt;
    
        @FXML
    private Button searchbtn;


    
    //// treeview
      @FXML
    private TreeView<String> detailstreevw;
    @FXML
    private Button Homebtn;
    


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        setupCombox();
        MenuItem mi1 = new MenuItem("Remove Customer");
        MenuItem mi2 = new MenuItem("Add Customer");
        MenuItem mi3 = new MenuItem("View Vehicles");
        mi1.setOnAction((ActionEvent event) -> {
  
            ConfirmPopup confirm = new ConfirmPopup();
            Customer item = tbv.getSelectionModel().getSelectedItem();
            
       boolean userChoseYes =   confirm.showAlert(item.getFname(), item.getLname());
            if (userChoseYes){
                  customer.remove(item);
                    dbm.remove_customer(item.getId());
                     bookingsList.removeAll(bookingsList); // reset bookingList
                     tbv.setItems(customer);
                     detailstreevw.setRoot(null);
            }else{
                // Do nothing;
            }
    
  
});
        
         mi2.setOnAction((ActionEvent event) -> {
    
    WizardPopup add = new WizardPopup();
   int newCustomerid = add.showLinearWizard();
   if (newCustomerid!=0){ // zero means that the insert was unsuccessful
          customer.add(dbm.findCus(newCustomerid));
   }

   
  
    
});
                  mi3.setOnAction((ActionEvent event) -> {
                 try{
        
                     Customer item = tbv.getSelectionModel().getSelectedItem();
                     System.out.println("ItemId: "+ item.getId());
                     System.out.println("Item: "+ item);
  
                FXMLLoader loader = new FXMLLoader((getClass().getResource("displayVehicles.fxml")));
                 Parent root = (Parent)loader.load();
                 DisplayVehiclesController vehCont = (DisplayVehiclesController) loader.getController();
                 vehCont.setupTables(item.getId());
                 Stage stage = new Stage();
                Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
        
                 } 
                 catch(NullPointerException exp){
                     popup excp = new popup("Please Select a customer!");
                 }
                 
                 catch(Exception exception){
                    // exception.printStackTrace(System.out);
                    
                 }
   
    
});

ContextMenu menu = new ContextMenu();
menu.getItems().addAll(mi1,mi2,mi3);
tbv.setContextMenu(menu);
        
        ///////////////////////////////
      // Customer Table SEtup, one on the left
        ////////////////////////////////
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tbv.setEditable(true);
        
        // fName setup for table
        fnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("fname"));
        fnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
          fnameColumn.setOnEditCommit(new EventHandlerImpl());
        
        
        // lname setup for table
        lnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lname"));
           lnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
             lnameColumn.setOnEditCommit(new EventHandlerImpl());
             
             // email setup for table
             emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
          emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
          emailColumn.setOnEditCommit(new EventHandlerImpl());
          
           // Address setup for table
             addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
          addressColumn.setCellFactory(new Callback<TableColumn<Customer, String>, TableCell<Customer, String>>() {
            
        @Override
        public TableCell<Customer, String> call(
                TableColumn<Customer, String> param) {
            TableCell<Customer, String> cell = new TableCell<>();
            Text text = new Text();
            text.getStyleClass().add("tableText");
            
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(addressColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        };
            
        });
          addressColumn.setOnEditCommit(new EventHandlerImpl());
                     // phoneNumber setup for table
             phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
          phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
          phoneColumn.setOnEditCommit(new EventHandlerImpl());
          
            String individual = "Individual";
            String Business = "Business";
             ObservableList<String> cbValues = FXCollections.observableArrayList(Business,individual );

               typecust.setCellValueFactory(i -> {
        final StringProperty value = i.getValue().optionProperty();
        // binding to constant value
        return Bindings.createObjectBinding(() -> value);
    });
               
                typecust.setCellFactory(col -> {
        TableCell<Customer, StringProperty> c = new TableCell<>();
        final ComboBox<String> comboBox = new ComboBox<>(cbValues);
       
         
     
        c.itemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                comboBox.valueProperty().unbindBidirectional(oldValue);
                    System.out.println("changed");
            }
            if (newValue != null) {
                comboBox.valueProperty().bindBidirectional(newValue);
                      Object customerRow =  c.getTableRow().getItem();
                       Customer custRow = (Customer) customerRow;
                       if (custRow != null){
                           
                           if (custRow.getcustType().equals(individual)){
                            comboBox.getSelectionModel().selectLast(); // slect the individual one
                                                System.out.println("changed");

                       } else{
                                comboBox.getSelectionModel().selectFirst();
                       }
                       comboBox.setOnAction(e -> custRow.setcustType(comboBox.getValue())); // updates db on customerType 
                           
                           
                       }
                     
                      
                            

            }
        });
        c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
        return c;
    });
                
                customer = dbm.getFirst20Customers();
                tbv.setItems(customer);
        ///////////////////////////////
      // Booking Table Setup, one on the top right
        //////////////////////////////// 
              
          name.setCellValueFactory(new PropertyValueFactory<>("name"));       
           bookcost.setCellValueFactory(new PropertyValueFactory<>("balance")); 
           date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
           date.setCellFactory(new Callback<TableColumn<Bookings, String>, TableCell<Bookings, String>>() {
            
        @Override
        public TableCell<Bookings, String> call(
                TableColumn<Bookings, String> param) {
            TableCell<Bookings, String> cell = new TableCell<>();
            Text text = new Text();
            text.getStyleClass().add("tableText");
            
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(addressColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        };
            
        });
           paid.setCellValueFactory( c-> c.getValue().paidProperty() );
          paid.setCellFactory( CheckBoxTableCell.forTableColumn( paid ) );
          
           
     // listener property which lokks out for when row is selected on table 1        
     tbv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
         
     if(tbv.getSelectionModel().getSelectedItem() != null) {
         Customer cust = tbv.getSelectionModel().getSelectedItem();
         selectedCustomer = cust; // globally make the selceted customer available for bookings to update the balance.
         int custid = cust.getId();
         bookingsList = dbm.getbillsfor_customer(custid);
         
         for (Bookings books : bookingsList){
             books.paidProperty().addListener((observableVal,oldValue,newValue) -> {
                 
                 if (newValue){
                     books.setPaidDb(1);
                    
                 // we have the customer information
                 // we need to search for the customer in list, so we can get the position of him in the list
                 // then we will run a database call to find the upated customer
                 // replace the old customer with it
                 // add it back into the list
                 Double balanceTotal = 0.0;
                for (Bookings booksIte : bookingsList){
                    if (booksIte.paidProperty().getValue()){
                    //Means that they have settled it so do not add that to balance
                            }
                    else{
                        Double balance = Double.parseDouble(booksIte.getBalance());
                    balanceTotal = balanceTotal + balance;
                    }
                    
                }
                
                
                selectedCustomer.setBalance(balanceTotal);
               
                 tbv.refresh();
                      
                 } else{
                     books.setPaidDb(0);
                    //  ObservableList<Customer> customerTemp = FXCollections.observableArrayList();
                    
                 
                        Double balanceTotal = 0.0;
                for (Bookings booksIte : bookingsList){
                    if (booksIte.paidProperty().getValue()){
                    //Means that they have settled it so do not add that to balance
                            }
                    else{
                        Double balance = Double.parseDouble(booksIte.getBalance());
                    balanceTotal = balanceTotal + balance;
                    }
                   
                } 
                
                 selectedCustomer.setBalance(balanceTotal);

                tbv.refresh();
                    
                   
                 
                 }
                // books.setPaid(newValue);
             }
             
             
             
             );
         }

         bookingtbv.setItems(bookingsList);
         bookingtbv.setEditable(true);
     }

});       
     
          bookingtbv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
         
     if(tbv.getSelectionModel().getSelectedItem() != null) {
         
         
         ///Get selected item which is a booking
         Bookings books = bookingtbv.getSelectionModel().getSelectedItem();
                 //Treeview Setup
                 
    TreeItem<String> root = new TreeItem<>("Vehicle Name: "+ books.getVehName());
     TreeItem<String> regNumber = new TreeItem<>("Registration Number: " + books.getRegNumber());
    TreeItem<String> node = new TreeItem<>("Warranty Company: "+ books.getWarrantyName());
    TreeItem<String> node2 = new TreeItem<>("Parts Used: "+ books.getPartName());
    TreeItem<String> node3 = new TreeItem<>("Return status: "+ books.getReturnStatus());
    root.getChildren().addAll(regNumber,node,node2,node3);
    detailstreevw.setRoot(root);   
     }

});  
                   

     

    
    
 
              
             
        

          
       
        
        
       
        // TODO
    } 
    

    
    

   



    @FXML
    private void GoHome(ActionEvent event) throws Exception{
        Stage stage = (Stage) this.searchbtn.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }

    
 

    private static class EventHandlerImpl implements EventHandler<CellEditEvent<Customer, String>> {

        public EventHandlerImpl() {
        }

        @Override
        public void handle(CellEditEvent<Customer, String> t) {
            String columnName = t.getTableColumn().textProperty().getValue();
            switch(columnName){
                case "First Name":
                    ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFname(t.getNewValue());
                    break;
                
                case "Last Name":
                     ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLname(t.getNewValue());
                    break;
                
                case "Address":
                     
                     ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
                     break;
                
                case "Phone":
                      ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNumber(t.getNewValue());
                    break;
                
                case "Email":
                     ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    break;
                
                default:
                    popup pop = new popup("Could not change the selected field. Please try again");
                    break;
            }
                
            
        }
    }
    
   
        
 
 

            @FXML
    void makeBooking(ActionEvent event) throws Exception{

          FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("diagRep/GUI/BookingTable.fxml")));
                   
                    Stage stage = (Stage) tbv.getScene().getWindow();
                  
                    
              Parent root = (Parent)loader.load();
         
             Scene scene = new Scene(root);
        
              stage.setScene(scene);
                 stage.show();
    }
          
    
    
    
    
        @FXML
    void getActiveCust(ActionEvent event) {
         
              customer.removeAll(customer);
                 bookingsList.removeAll(bookingsList); // empty booking table
                 detailstreevw.setRoot(null);
                  ObservableList<Customer> cust = FXCollections.observableArrayList();
               cust= dbm.getActiveListOf_customer();
              
                for (Customer cus : cust){
                    customer.add(cus);
                }
                
                tbv.setItems(customer);
    }
    
        @FXML
    void searchdb(ActionEvent event) {
                // get text and check whether it is a user search or Reg number search
                
                
                checkIfUserOrVehicleSearch(searchTxt.getText());
               
    }

    public void checkIfUserOrVehicleSearch(String searchQuery){
         boolean founddigit = false;
         for(char c : searchQuery.toCharArray()){
           
            if(Character.isDigit(c)){
           
             founddigit = true;
             } else{
          
                      
            }
        }
         
         if (founddigit){
             // search the vehicle table, because search query is for REG Number
             // Maybe have a method that deletes all white spaces
             
             customer.removeAll(customer);
                 bookingsList.removeAll(bookingsList); // empty booking table
                  ObservableList<Customer> cust = FXCollections.observableArrayList();
               cust= dbm.searchWithRegNumber(searchQuery);
               tbv.setItems(customer);
                for (Customer cus : cust){
                    customer.add(cus);
                    
                }
               
                        
                  
         } else{
             int type = 1;
             String cbvalue = typeComboBox.getValue();
             if (cbvalue.equals("Individual")) {
                 type = 1;
             } else {
                 type = 2;
             }
             // search customer table;
             // now need to check for any spaces in the searchQuery
             // if any then, they have entered a first name and lastName.
             // if not then they have entered just a first ior last searchWithFirstOrLastName_customer(String Name)
             if (containsWhiteSpace(searchQuery)){
                 // two seperate words, so it is firstName and LastName
                 String[] FnameAndLName = searchQuery.split(" ");
                 customer.removeAll(customer);
                 bookingsList.removeAll(bookingsList); // empty booking table
                  ObservableList<Customer> cust = FXCollections.observableArrayList();
                 cust = dbm.searchWithFullName_customer(FnameAndLName[0], FnameAndLName[1],type);
                    for (Customer cus : cust){
                    customer.add(cus);
                   
                }
                 
             } else{
                  customer.removeAll(customer);
                  bookingsList.removeAll(bookingsList);
                  ObservableList<Customer> cust = FXCollections.observableArrayList();
                  
               cust= dbm.searchWithFirstOrLastName_customer(searchQuery,type);
               tbv.setItems(customer);
                for (Customer cus : cust){
                    customer.add(cus);
                    
                }
                
                 
             }
             
         }
    }
    
    public static boolean containsWhiteSpace(final String searchQuery){
        // http://stackoverflow.com/questions/4067809/how-can-i-find-whitespace-space-in-a-string
    if(searchQuery != null){
        for(int i = 0; i < searchQuery.length(); i++){
            if(Character.isWhitespace(searchQuery.charAt(i))){
                return true;
            }
        }
    }
    return false;
}
    private void setupCombox(){
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Individual");
        options.add("Business");
        
       typeComboBox.setItems(options);
    }
   
    
}


