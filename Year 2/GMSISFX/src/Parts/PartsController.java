package Parts;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static javafx.scene.control.Alert.AlertType.WARNING;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PartsController implements Initializable {

    //////////REPAIR TABLE//////////////////////
    @FXML
    private TableView<Repairs> RepairTable;
    
    @FXML
    private TableView<PartsBooking> BookTable;
    
    @FXML
    private TableView<Parts> PartsTable;
   
    @FXML
    private TableColumn<Repairs,String> repairidcol;
    
    @FXML
    private TableColumn<Repairs, String> partnamecol;

    @FXML
    private TableColumn<Repairs, String> installdatecol;
    
    @FXML
    private TableColumn<Repairs, String> warrantydatecol;
    
    @FXML
    private TableColumn<Repairs, String> registrationcol;
    
    @FXML 
    private TableColumn<Repairs, String> fnamecol2;
    
    @FXML
    private TableColumn<Repairs, String> lnamecol2;
    
    @FXML
    private TableColumn<Repairs, String> bookingidcol;
    
    @FXML
    private TableColumn<PartsBooking, String> customeridcol;
    
    @FXML
    private TableColumn<PartsBooking, String> fnamecol;
    
    @FXML
    private TableColumn<PartsBooking, String> lnamecol;
    
    @FXML
    private TableColumn<PartsBooking, String> bookingcol2;
    
    @FXML
    private TableColumn<PartsBooking, String> datecol;
    
    @FXML
    private TableColumn<Parts, String> Partsidcol;
    
    @FXML
    private TableColumn<Parts, String> partsnamecol2;
    
    @FXML
    private TableColumn<Parts, String> descol;
    
    @FXML
    private TableColumn<Parts, String> partcostcol;
    
    @FXML
    private TableColumn<Parts, String> stocklevelcol;

    //////////////////////TEXT/////////////////////
    
    @FXML
    private DatePicker installdatetext;
    
    @FXML
    private DatePicker warrantydatetext;
    
    @FXML
    private TextField partnametext;
    
    @FXML
    private TextField registrationtext;
    
    @FXML
    private TextField customeridtext;
    
    @FXML
    private TextField bookingidtext;
    
    @FXML
    private TextField fnametext;
    
    @FXML
    private TextField lnametext;
    
    @FXML
    private TextField stockleveltext;
    
    @FXML
    private TextField partidtextfield;
    
    @FXML
    private TextField stockleveltextfield;
    
    @FXML
    private TextField partnametextfield;
    
    @FXML
    private TextField partcosttextfield;
    
    @FXML
    private TextField destextfield;
    
    //////////////////LABEL//////////////////////
    
    @FXML
    private Label partidlabel;
   
    @FXML
    private Label partnamelabel;
    
    @FXML
    private Label deslabel;
    
    @FXML
    private Label stocklevellabel;
    
    ////////////BUTTONS///////////////
    @FXML
    private Button addbtn;
    
    @FXML
    private Button editbtn;
    
    @FXML
    private Button deletebtn;
    
    @FXML
    private Button billbtn;
    
    @FXML 
    private Button addpartsbutton;
    
    @FXML
    private Button addstock;
    
    @FXML
    private Button editstock;
    
    @FXML
    private Button removebtn;
    
    @FXML
    private Button homebtn;
    
    @FXML
    private Button logoutbtn;
    
    //////////////////COMBOBOX AND CHOICEBOX////////////////////
    
    @FXML
    private ComboBox<String> selectiondroplist;

    
    @FXML
    private TextField searchtext;

   //////////////////////////////OBSERVABLE LISTS/////////////////
    
    private ObservableList<Repairs> data;
    
    private ObservableList<PartsBooking> data2;
    
    private ObservableList<Parts> data3;
    
    //private ObservableList comboPartsOption;
    
    ///////////////////DATABASE/////////////////////////////////
    private DBConnection database;
    
    private String carpart = "";
    
    private String filter = "";
    
    //private int pID;
    
   // private int stockChange = 0;
    
    ObservableList<Repairs> item = FXCollections.observableArrayList();
    
    public void initialize(URL url, ResourceBundle rb) {
        
        selectiondroplist.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) ->
                        filter = newValue
        );
        
        selectiondroplist.setItems(fillUpComboBox());
        
        setCellValueFromRepairTableToTextField();
        setCellValueFromPartsTableToTextField();
        
        repairidcol.setCellValueFactory(new PropertyValueFactory<>("RepairID"));
        partnamecol.setCellValueFactory(new PropertyValueFactory<>("PartsName"));
        installdatecol.setCellValueFactory(new PropertyValueFactory<>("InstallDate"));
        warrantydatecol.setCellValueFactory(new PropertyValueFactory<>("WarrantyDate"));
        registrationcol.setCellValueFactory(new PropertyValueFactory<>("RegistrationNumber"));
        fnamecol2.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lnamecol2.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        
        loadAllData();
        RepairTable.setItems(data);
        
        fnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        bookingcol2.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        bookingidcol.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
        
        loadPartsBookingData();
        BookTable.setItems(data2);
                
        Partsidcol.setCellValueFactory(new PropertyValueFactory<>("PartsID"));
        partsnamecol2.setCellValueFactory(new PropertyValueFactory<>("PartsName"));
        descol.setCellValueFactory(new PropertyValueFactory("Description"));
        partcostcol.setCellValueFactory(new PropertyValueFactory("PartCost"));
        stocklevelcol.setCellValueFactory(new PropertyValueFactory("StockLevel"));
        
        loadPartsData();
        PartsTable.setItems(data3);
        
       
    
    }    
    
    @FXML
    private void GoHome(ActionEvent event) throws Exception{
        Stage stage = (Stage) this.homebtn.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void logout(ActionEvent event) throws Exception{
      
             Stage stage = (Stage) logoutbtn.getScene().getWindow();
             Parent root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("Authentication/Login.fxml"));
             Scene scene = new Scene(root);
        
              stage.setScene(scene);
                 stage.show();
    }
    
    public ObservableList<Repairs> loadAllData(){
        data = FXCollections.observableArrayList(); 
          try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "SELECT Inventory.RepairID,Parts.name,Repair.InstallDate,Repair.WarrantyDate,Vehicle.regNumber,Booking.bookingType,customer.firstName,customer.lastName,customerVehicle.id FROM Inventory "
                    + "INNER JOIN Repair ON Inventory.RepairID = Repair.RepairID "
                    + "INNER JOIN Parts ON Inventory.PartsID = Parts.id "
                    + "INNER JOIN customer ON customer.id = Repair.CustomerID "
                    + "INNER JOIN Booking ON Booking.id = Repair.BookingID "
                    + "INNER JOIN Vehicle ON Vehicle.id = customerVehicle.vehicleID "
                    + "INNER JOIN customerVehicle ON customerVehicle.id = Booking.customerVehicle";
            
            PreparedStatement stmt = c.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                 data.add(new Repairs(
                         rs.getInt("RepairID"),
                         rs.getString("name"),
                         rs.getString("InstallDate"),
                         rs.getString("WarrantyDate"),
                         rs.getString("regNumber"),
                         rs.getString("FirstName"),
                         rs.getString("LastName")));
         
            }
            RepairTable.setItems(data);
            BookTable.setItems(data2);
            stmt.close();
            rs.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
          
          return data;    
    }
    
    public void refresh()
    {
        Partsidcol.setCellValueFactory(new PropertyValueFactory<>("PartsID"));
        partsnamecol2.setCellValueFactory(new PropertyValueFactory<>("PartsName"));
        descol.setCellValueFactory(new PropertyValueFactory("Description"));
        partcostcol.setCellValueFactory(new PropertyValueFactory("PartCost"));
        stocklevelcol.setCellValueFactory(new PropertyValueFactory("StockLevel"));

        loadPartsData();
        PartsTable.setItems(data3);
    }
    
    private ObservableList<PartsBooking> loadPartsBookingData() {
        data2 = FXCollections.observableArrayList();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            String sql2 = "SELECT Booking.id,customer.FirstName,customer.LastName,Booking.bookingType,"
                    + "Booking.Date From Booking\n"
                          + "INNER JOIN customerVehicle ON Booking.customerVehicle = customerVehicle.id \n" 
                          + "INNER JOIN customer ON customer.id = customerVehicle.customerID";
        
        
            PreparedStatement stmt2 = c.prepareStatement(sql2);
            ResultSet rsBook = stmt2.executeQuery();
            
            while(rsBook.next())
            {
                data2.add(new PartsBooking(
                                   rsBook.getInt("id"),
                                   rsBook.getString("FirstName"),
                                   rsBook.getString("LastName"),
                                   rsBook.getString("bookingType"),
                                   rsBook.getString("Date")));
            }
            BookTable.setItems(data2);
            stmt2.close();
            rsBook.close();
            c.close();

            }catch(Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                e.printStackTrace(System.out);
                System.exit(0);
            }
        return data2;
    }
    
    private ObservableList<Parts> loadPartsData() {
        data3 = FXCollections.observableArrayList();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            String sql = "SELECT * FROM PARTS";
            
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rsParts = stmt.executeQuery();
            
            while(rsParts.next())
            {
                data3.add(new Parts(
                        rsParts.getInt("id"),
                        rsParts.getString("name"),
                        rsParts.getString("Description"),
                        rsParts.getInt("PartCost"),
                        rsParts.getInt("StockLevel")));
            }
            
            stmt.close();
            rsParts.close();
            c.close();
        }catch(Exception e)
        {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                e.printStackTrace(System.out);
                System.exit(0);
        }
        
        return data3;
    }

    @FXML
    public void clickAddtoVehicle(ActionEvent event) throws Exception{
        
            String instdate = installdatetext.getEditor().getText();
            String warranty = warrantydatetext.getEditor().getText();
            String fname=fnametext.getText();
            String lname=lnametext.getText();
            String pname = partnametext.getText();
            String rnumber = registrationtext.getText();
            int bookingID=Integer.parseInt(bookingidtext.getText());
            int customerID = Integer.parseInt(customeridtext.getText());

            if(validateFirstName() && validateLastName() && validatePartsName()){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
    
            if(bookingID > 10 && customerID > 10)
            {
                JOptionPane.showMessageDialog(null,"There can be 10 distinct part,please uninstall one of the part");
                return;
            }
 
            //PreparedStatement preparestatement6 = c.prepareStatement("INSERT INTO REPAIR(RepairID,InstallDate,WarrantyDate,FirstName,LastName,RegNumber,BookingID) VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement preparestatement = c.prepareStatement("INSERT INTO REPAIR(REPAIRID,INSTALLDATE,WARRANTYDATE,BOOKINGID,CUSTOMERID) VALUES (?,?,?,?,?)");
            PreparedStatement preparestatement2 = c.prepareStatement("INSERT INTO PARTS(name) VALUES (?)");
            PreparedStatement preparestatement3 = c.prepareStatement("INSERT INTO customer(FIRSTNAME,LASTNAME) VALUES (?,?)");
            PreparedStatement preparestatement4 = c.prepareStatement("INSERT INTO Vehicle(regNumber) VALUES (?)");
            preparestatement.setString(1,null);
            preparestatement.setString(2,instdate);
            preparestatement.setString(3,warranty);
            preparestatement.setInt(4,bookingID);
            preparestatement.setInt(5,customerID);
            preparestatement2.setString(1,pname);
            preparestatement3.setString(1,fname);
            preparestatement3.setString(2,lname);
            preparestatement4.setString(1,rnumber);

            preparestatement.execute();
            preparestatement2.execute();
            preparestatement3.execute();
            preparestatement4.execute();
            preparestatement.close();
            preparestatement2.close();
            preparestatement3.close();
            preparestatement4.close();
           
            
            RepairTable.setItems(data);
            loadAllData();
            
            JOptionPane.showMessageDialog(null,"Part added to vehicle successfully");
            removeStock();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
    }
    
    @FXML
    public void updateStock(ActionEvent event) throws Exception
    {   
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to add Part?");
            Optional<ButtonType> action = alert.showAndWait();
            
            String pname = partnametextfield.getText();
            String pcost = partcosttextfield.getText();
            String des = destextfield.getText();
            String stock = stockleveltextfield.getText();

            if(action.get() == ButtonType.OK) {
                
                if(validatePartCost() && validateDescription() && validatePartsNameFromPartsTable() && validateStockLevel() ){
                
                try{

                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

                    String sql = "INSERT INTO PARTS(id,name,DESCRIPTION,PARTCOST,STOCKLEVEL) VALUES (?,?,?,?,?)";
                    PreparedStatement stmt = c.prepareStatement(sql);
                    
                     if(Integer.parseInt(pcost) < 0)
                    {
                        JOptionPane.showMessageDialog(null,"Negative Cost cannot be entered!");
                        return;
                    }

                    stmt.setString(1,null);
                    stmt.setString(2,pname);
                    stmt.setString(3,des);
                    stmt.setString(4,pcost);
                    stmt.setString(5,stock); 

                    stmt.execute();
                    stmt.close();

                    PartsTable.setItems(data3);
                    loadPartsData();
                    JOptionPane.showMessageDialog(null,"Part has been added sucessfully");
                } catch (Exception e) {
                    System.err.println(e.getClass().getName()+ ": " + e.getMessage());
                    e.printStackTrace();
                    System.exit(0);
                }
                }
            }
        }
    
    @FXML
    public void editStock(ActionEvent event) throws Exception 
    {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to edit the Part");
            Optional< ButtonType> action = alert.showAndWait();
            
            String pname = partnametextfield.getText();
            String pcost = partcosttextfield.getText();
            String des = destextfield.getText();
            String stock = stockleveltextfield.getText();

            if(validatePartCost() && validateDescription() && validatePartsNameFromPartsTable() && validateStockLevel()){
       try{
           Class.forName("org.sqlite.JDBC");
           Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
           int pID = PartsTable.getSelectionModel().getSelectedItem().getPartsID();
           String sql = "UPDATE PARTS SET name = ?,Description = ?,PartCost = ?,StockLevel = ? WHERE id = " + pID; 
           
           PreparedStatement stmt = c.prepareStatement(sql);
           
            if(Integer.parseInt(pcost) < 0)
                    {
                        JOptionPane.showMessageDialog(null,"Negative Cost cannot be entered!");
                        return;
                    }
           
            //stmt.setString(1,null);
            stmt.setString(1,pname);
            stmt.setString(2,des);
            stmt.setString(3,pcost);
            stmt.setString(4,stock); 
            
            JOptionPane.showMessageDialog(null,"Part has been edited successfully");
            stmt.executeUpdate();
            stmt.close();

            }catch(Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
           e.printStackTrace();
           System.exit(0);
            }
       }
    }
    
    private void removeStock() {
    {
        String carpart = partnametext.getText();
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");

            String sql = "UPDATE PARTS SET STOCKLEVEL = STOCKLEVEL - 1 WHERE name LIKE '%" + carpart + "%'";
            PreparedStatement stmt = c.prepareStatement(sql);
            
            stmt.executeUpdate();
            stmt.close();
            PartsTable.setItems(data3);
            loadAllData();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
    
    @FXML
    public void removeParts(ActionEvent event) throws Exception 
    {
        int pID = PartsTable.getSelectionModel().getSelectedItem().getPartsID();
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            
            String sql = "DELETE FROM PARTS WHERE id = " + pID;
            
            PreparedStatement stmt = c.prepareStatement(sql);
 
            JOptionPane.showMessageDialog(null,"Part has been deleted successfully");
            stmt.executeUpdate();
            stmt.close();
            PartsTable.setItems(data3);
            loadAllData();
        }catch(Exception e)
        {
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
        
    

    private void partsUpdate(String carpart) 
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
            
            String sql = "SELECT * FROM Parts WHERE name LIKE '%" + carpart + "%'";
            PreparedStatement preparestatement = c.prepareStatement(sql);
            ResultSet rs = preparestatement.executeQuery();
            
            partidlabel.setText(rs.getString("id"));
            partnamelabel.setText(rs.getString("name"));
            deslabel.setText(rs.getString("Description"));
            stocklevellabel.setText(rs.getString("StockLevel"));

        }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
    } 
        @FXML
        public void deleteFromVehicle(){
        
        int rID = RepairTable.getSelectionModel().getSelectedItem().getRepairID();
           
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
           
            PreparedStatement preparestatement = c.prepareStatement("DELETE FROM REPAIR where RepairID=" + rID);
            preparestatement.execute();
            preparestatement.close();

            RepairTable.setItems(data);
            loadAllData();
            
            JOptionPane.showMessageDialog(null,"Part deleted from vehicle successfully");
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    
    }
        @FXML
        private void deleteBooking(ActionEvent event) throws Exception
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the Booking!");
            Optional<ButtonType> action = alert.showAndWait();
            
            if(action.get() == ButtonType.OK) {
                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                    int bID = BookTable.getSelectionModel().getSelectedItem().getBookingID();
                    String sql = "DELETE FROM BOOKING WHERE id = ?;";
                    
                    PreparedStatement stmt = c.prepareStatement(sql);
                    stmt.setInt(1,bID);
                    
                    stmt.executeUpdate();
                    stmt.close();
                    
                    BookTable.setItems(data2);
                    loadPartsBookingData();
                    c.close();
                    JOptionPane.showMessageDialog(null,"Booking has been deleted successfully");
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error");
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    e.printStackTrace(System.out);
                    System.exit(0);
                }
            } 
        }

        
        private ObservableList<String> fillUpComboBox() {
            ArrayList<String> fillup = new ArrayList<>();
            fillup.add("FirstName");
            fillup.add("LastName");
            fillup.add("RegNumber");
            return FXCollections.observableArrayList(fillup);
        }
        
        private void setCellValueFromRepairTableToTextField() {
            RepairTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Repairs r = RepairTable.getItems().get(RepairTable.getSelectionModel().getSelectedIndex());
                    installdatetext.getEditor().setText(r.getInstallDate());
                    warrantydatetext.getEditor().setText(r.getWarrantyDate());
                    fnametext.setText(r.getFirstName());
                    lnametext.setText(r.getLastName());
                    partnametext.setText(r.getPartsName());
                    registrationtext.setText(r.getRegistrationNumber());
                }
            });
        }
        
        
        private void setCellValueFromPartsTableToTextField() {
            PartsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Parts p = PartsTable.getItems().get(PartsTable.getSelectionModel().getSelectedIndex());
                    partnametextfield.setText(p.getPartsName());
                    destextfield.setText(p.getDescription());
                    partcosttextfield.setText("" +p.getPartCost());
                    stockleveltextfield.setText("" + p.getStockLevel());
  
                }
            });
        }

        @FXML
        public void searchFunction(ActionEvent event) throws Exception
        {
            FilteredList<Repairs> filteredData = new FilteredList<>(loadAllData(),e -> true);
            searchtext.setOnKeyReleased(e ->{
                searchtext.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate(Repairs -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        
                        String lowerCaseFilter = newValue.toLowerCase();
                        
                        if(filter.equals("FirstName")){
                            if(Repairs.getFirstName().contains(newValue)){
                                return true;
                            }
                        }
                        if(filter.equals("LastName")) {
                            if(Repairs.getLastName().contains(newValue)) {
                                return true;
                            }
                        }
                        if(filter.equals("RegNumber")){
                            if(Repairs.getRegistrationNumber().contains(newValue)) {
                                return true;
                            }
                        }
                        return false;
                    });
                });
                SortedList<Repairs> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(RepairTable.comparatorProperty());
                RepairTable.setItems(sortedData);
            });
        }
        
        @FXML
        private void calculatebillForPartsCost(ActionEvent event) throws Exception
        {
            int ID = 1;
            double cost = 0.0;
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to check your Bill?");
            Optional<ButtonType> action = alert.showAndWait();
            
          if(action.get() == ButtonType.OK){
            try{
                Class.forName("org.sqlite.JDBC");
                Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                String sql = "SELECT Repair.RepairID,Parts.PartCost FROM Repair INNER JOIN Inventory ON Repair.RepairID = Inventory.RepairID "
                              + "INNER JOIN Parts ON Inventory.PartsID = Parts.id WHERE Repair.BookingID = " +ID;
                
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rsBill = stmt.executeQuery();
                
                while(rsBill.next())
                {
                    cost = cost + rsBill.getInt("PartCost");
                
                }
            }catch(Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                e.printStackTrace(System.out);
                System.exit(0);
            }
            
            JOptionPane.showMessageDialog(null,"Your Bill adds up to " + cost +".");
            }
        }
        
        private boolean validateFirstName()
        {
            String fname=fnametext.getText();
           
            Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(fname);
            if(m.find() && m.group().equals(fname))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate FirstName");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid FirstName");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validateLastName()
        {
            String lname=lnametext.getText();
            Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(lname);
            if(m.find() && m.group().equals(lname))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate LastName");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid LastName");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validatePartsName()
        {
            String pname = partnametext.getText();
           
             Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(pname);
            if(m.find() && m.group().equals(pname))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate PartsName");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid PartsName");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validatePartsNameFromPartsTable()
        {
             String pnametext = partnametextfield.getText();
           
             Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(pnametext);
            if(m.find() && m.group().equals(pnametext))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate PartsName");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid PartsName");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validateDescription()
        {
            String des = destextfield.getText();
           
             Pattern p = Pattern.compile("^[ A-z]+$");
            Matcher m = p.matcher(des);
            if(m.find() && m.group().equals(des))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate Part Description");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Part Description");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validatePartCost()
        {
            String pcost = partcosttextfield.getText();
           
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(pcost);
            if(m.find() && m.group().equals(pcost))
            {
                return true;
            }
            else
            {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Validate PartCost");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid PartCost");
                    alert.showAndWait();
                  
                return false;
            }
        }
       
        private boolean validateStockLevel()
        {
            String stock = stockleveltextfield.getText();
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(stock);
           
            if(m.find() && m.group().equals(stock))
            {
                return true;
            }
            else
            {
                 Alert alert = new Alert(AlertType.WARNING);
                 alert.setTitle("Validate StockLevel");
                 alert.setHeaderText(null);
                 alert.setContentText("Please Enter Valid Stock Level");
                 alert.showAndWait();
                
                 return false;
            }
        }
}
        
 
      





















