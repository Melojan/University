/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.GUI;

import Authentication.Session;
import common.Database;
import diagRep.Logic.Booking;
import diagRep.Logic.DiagAndRepBooking;
import diagRep.Logic.Mechanic;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.time.DayOfWeek;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;



public class BookingTableController implements Initializable {
    
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button homeButton;
   
    @FXML private Button removeButton;
    
    @FXML private Label bookingList;
    @FXML private Label pastAndFuture;
    
    @FXML private CheckBox byHour;
    @FXML private CheckBox byToday;
    @FXML private CheckBox byMonth;
    @FXML private CheckBox showAll;
    @FXML private CheckBox pBooking;
    @FXML private CheckBox fBooking;
    @FXML private CheckBox showAll2;
    
    @FXML private ComboBox<String> droplist;
    @FXML private TextField searchtxt;
    
            
   @FXML private TableView<DiagAndRepBooking> searchTable;   
   @FXML private TableColumn<DiagAndRepBooking, Integer> idColumn;
   @FXML private TableColumn<DiagAndRepBooking, String> bookingDateColumn;
   @FXML private TableColumn<DiagAndRepBooking, Integer> timeColumn;
   @FXML private TableColumn<DiagAndRepBooking, Integer> durationColumn;
   @FXML private TableColumn<DiagAndRepBooking, Integer> mechanicColumn;
   @FXML private TableColumn<DiagAndRepBooking, String> firstNameColumn;
   @FXML private TableColumn<DiagAndRepBooking, String> lastNameColumn;
   @FXML private TableColumn<DiagAndRepBooking, String> vehicleRegistrationColumn;  
   @FXML private TableColumn<DiagAndRepBooking, Integer> currentMColumn; 
   @FXML private TableColumn<DiagAndRepBooking, String> typeColumn;
   @FXML private TableColumn<DiagAndRepBooking, String> makeColumn;
   
   private ObservableList<DiagAndRepBooking> data;
   
   @FXML private TableView<Booking> viewTable;   
   @FXML private TableColumn<Booking, String> vehicleRegistrationCol;  
   @FXML private TableColumn<Booking, String> fnameCol;
   @FXML private TableColumn<Booking, String> lnameCol;
   @FXML private TableColumn<Booking, Integer> mechIdCol;
   @FXML private TableColumn<Booking, String> bookingDateCol;
   @FXML private TableColumn<Booking, Integer> timeCol;
   @FXML private TableColumn<Booking, Integer> durationCol;
   
    private ObservableList<Booking> data2;
    
    
    
    
    @FXML private TableView<Mechanic> table; 
    @FXML private TableColumn<Mechanic, Integer> mechanicIDcol;  
    @FXML private TableColumn<Mechanic, Integer> duration1Col;
    @FXML private TableColumn<Mechanic, Integer> costCol;
    
     private ObservableList<Mechanic> data3;
    
    private ObservableList<DiagAndRepBooking> tempData = FXCollections.observableArrayList();
     private ObservableList<DiagAndRepBooking> tempData2 = FXCollections.observableArrayList();
    
    private ObservableList<String> custNames = FXCollections.observableArrayList();
    private ObservableList<String> vehicleReg = FXCollections.observableArrayList();
    private ObservableList<String> mechNames = FXCollections.observableArrayList();
  
   
   public ObservableList<DiagAndRepBooking> list = FXCollections.observableArrayList();      
   private DBConnection dbCon = new DBConnection();
   
   private String filter = "";
   
   

    /**
     * Initializes the controller class.
     */
   
    
     public ObservableList<DiagAndRepBooking> loadAllData(){
        data = FXCollections.observableArrayList(); 
          try {
        
              
            Database db = Database.getInstance();
           Connection c = db.getConnection();

            String sql = "SELECT Booking.id,Booking.date,Booking.Time,Booking.Duration,Mechanic.MechanicID,customer.firstName,customer.lastName,Vehicle.regNumber,Vehicle.currentMileage,Booking.bookingType,Vehicle.make from Booking "
                    + "INNER JOIN customerVehicle ON Booking.customerVehicle = customerVehicle.id "
                    + "INNER JOIN Vehicle ON Mechanic.VehicleID = Vehicle.id "
                    + "INNER JOIN customer ON customer.id = customerVehicle.customerID "
                    + "INNER JOIN Mechanic ON Mechanic.MechanicID = Booking.MechanicID";
            
            PreparedStatement stmt = c.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(new DiagAndRepBooking(
                         rs.getInt("id"),
                         rs.getString("date"),
                         rs.getInt("Time"),
                         rs.getInt("Duration"),
                         rs.getInt("bookingType"),
                         rs.getInt("MechanicID"),
                         rs.getString("firstName"),
                         rs.getString("lastName"),
                         rs.getString("regNumber"),
                         rs.getInt("currentMileage"),
                         rs.getString("bookingType"),
                         rs.getString("make")));
         
            }
            searchTable.setItems(data);
            //stmt.close();
            rs.close();
            //c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
          
          return data;    
    }
    
     public ObservableList<Booking> loadAllPastandFutureData(){
        data2 = FXCollections.observableArrayList(); 
          try {
        
              
            Database db = Database.getInstance();
           Connection c = db.getConnection();

            String sql = "SELECT Vehicle.regNumber,customer.firstName,customer.lastName,Mechanic.MechanicID,Booking.date,Booking.Time,Booking.Duration,customerVehicle.id FROM Booking "
                    + "INNER JOIN Vehicle ON Vehicle.id = Mechanic.VehicleID "
                    + "INNER JOIN customer ON customer.id = customerVehicle.customerID "
                    + "INNER JOIN customerVehicle ON customerVehicle.id = Booking.customerVehicle "
                    + "INNER JOIN Mechanic ON Mechanic.MechanicID = Booking.MechanicID";
            
            PreparedStatement stmt = c.prepareStatement(sql); 
            ResultSet rsBook = stmt.executeQuery();

            while (rsBook.next()) {
                data2.add(new Booking(
                         rsBook.getString("regNumber"),
                         rsBook.getString("firstName"),
                         rsBook.getString("lastName"),
                         rsBook.getInt("MechanicID"),
                         rsBook.getString("date"),
                         rsBook.getInt("time"),
                         rsBook.getInt("Duration")));
         
            };
            viewTable.setItems(data2);
            //stmt.close();
            rsBook.close();
            //c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
          
          return data2;    
    }
     public ObservableList<Mechanic> loadAllMechanicData(){
        data3 = FXCollections.observableArrayList(); 
          try {
        
              
            Database db = Database.getInstance();
           Connection c = db.getConnection();

            String sql = "SELECT MechanicID,Booking.Duration,Cost FROM Mechanic INNER JOIN Booking using (MechanicID)";
            
            PreparedStatement stmt = c.prepareStatement(sql); 
            ResultSet rsCost = stmt.executeQuery();

            while (rsCost.next()) {
                data3.add(new Mechanic(
                         rsCost.getInt("MechanicID"),
                         rsCost.getInt("Duration"),
                         rsCost.getInt("Cost")));
         
            };
            table.setItems(data3);
            //stmt.close();
            rsCost.close();
            //c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(0);
        }
          
          return data3;    
    }
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("here");
        idColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, Integer>("bookingId"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("BookingDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, Integer>("time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, Integer>("Duration"));
        mechanicColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, Integer>("MechanicID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("lastName"));
        vehicleRegistrationColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("vehicleRegistration"));
        currentMColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, Integer>("currentMileage"));
       typeColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("type"));
       makeColumn.setCellValueFactory(new PropertyValueFactory<DiagAndRepBooking, String>("Make"));

        loadAllData();
       
       vehicleRegistrationCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("vehicleRegistration"));
       fnameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
       lnameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
       mechIdCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("MechanicID"));
       bookingDateCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingDate"));
       timeCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("Time"));
       durationCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("Duration"));           
     
       loadAllPastandFutureData();
       //addButton.setOnAction(e -> add());
        
       mechanicIDcol.setCellValueFactory(new PropertyValueFactory<Mechanic, Integer>("mID"));
       duration1Col.setCellValueFactory(new PropertyValueFactory<Mechanic, Integer>("Duration"));           
       costCol.setCellValueFactory(new PropertyValueFactory<Mechanic, Integer>("Cost"));

       loadAllMechanicData();
       
       
       
       droplist.getSelectionModel().selectedItemProperty().addListener(
                   (observable,oldValue,newValue) ->
                           filter =  newValue
        );
        
        droplist.setItems(fillUpComboBox());
         
        searchTable.setItems(data);
        viewTable.setItems(data2);
        
        
       
    removeButton.setOnAction((ActionEvent e) -> {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
           alert1.setTitle("Information Dialog");
           alert1.setHeaderText(null);
           alert1.setContentText("Are you sure you want to delete?");
          Optional <ButtonType> action = alert1.showAndWait();
          
          if(action.get() == ButtonType.OK){
    DiagAndRepBooking selectedItem = searchTable.getSelectionModel().getSelectedItem();
    searchTable.getItems().remove(selectedItem);
    
                  try {
                        
                    Class.forName("org.sqlite.JDBC");
                    Connection c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
                      
                    String sql = ("DELETE FROM Booking WHERE id = '"+selectedItem.getBookingId()+"'");
                   PreparedStatement stmt = c.prepareStatement(sql);

                    stmt.execute();     
                    stmt.close();
                    c.close();
                   
        } catch (Exception z) {
          
            z.printStackTrace(System.out);
            
            }
                
       }});
       }
   
        
        
        
    
    
    
    @FXML
    private void add(ActionEvent event) throws IOException
    { 
        Stage stage;
        Parent parent;
 
            if(event.getSource() == addButton)
            {
                
                try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            parent = (Parent) loader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Add Booking");
            stage.setScene(new Scene(parent));
            stage.show();
    
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    }
    /*
     @FXML
     private void edit(ActionEvent event) throws IOException
    { 
        Stage stage;
        Parent parent;
 
            if(event.getSource() == editButton)
            {
                
                try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            parent = (Parent) loader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Edit Booking");
            stage.setScene(new Scene(parent));
            stage.show();
    
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    } 
     */
    
     @FXML
  private void editBookingAction(ActionEvent event) throws Exception {               
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
               DiagAndRepBooking book = searchTable.getSelectionModel().getSelectedItem();
                EditController contro = fxmlLoader.getController();
              contro.getInfo(book.getBookingId(), book.getType(),  book.getBookingDate(), book.getTime(), book.getDuration(), book.getVehicleRegistration(), book.getFirstName(), book.getLastName(), book.getCurrentMileage(), book.getMechanicID(), book.getMake());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
  }
  
      
         @FXML
    private void filterByHour() throws ClassNotFoundException
    {
        //datetime
    }
    
    @FXML
    private void filterByToday() throws ClassNotFoundException
    {
        if(!byToday.isSelected())
        {
            return;
        }
        
        byMonth.setSelected(false);
        pBooking.setSelected(false);
        fBooking.setSelected(false);
        showAll.setSelected(false);
        
        
        data = FXCollections.observableArrayList(tempData);
     
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(formatter);
        
        for(int i=0; i<data.size(); i++)
        {
            if(!today.equals(data.get(i).getBookingDate()))
            {
                data.remove(i);
                i--;  
            }
           
        }
       searchTable.setItems(data);
    } 
/*
    @FXML
    private void filterByMonth() throws ClassNotFoundException
    {
        if(!byMonth.isSelected())
        {
            return;
        }
        
        byToday.setSelected(false);
        pBooking.setSelected(false);
        fBooking.setSelected(false);
        showAll.setSelected(false);
          
        data = FXCollections.observableArrayList(tempData);
        
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = now.format(formatter);
        String[] dateNow = date.split("/");
        
        for(int i=0; i<data.size(); i++)
        {
            String[] tempDate = data.get(i).getBookingDate().split("/");
            if(!dateNow[1].equals(tempDate[1]) || !dateNow[2].equals(tempDate[2])) //different month or different year
            {
                data.remove(i);
                i--;
            }
        }
        searchTable.setItems(data); 
    }*/
    /*
    @FXML
    private void filterByPast() throws ClassNotFoundException
    {
       if(!pBooking.isSelected())
        {
            return;
        }
        
        fBooking.setSelected(false);
        showAll2.setSelected(false);
        
        data2 = FXCollections.observableArrayList(tempData2);
     
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i=0; i<data2.size(); i++)
        {
            LocalDate tempDate = LocalDate.parse(data2.get(i).getBookingDate(),formatter);
            if(now.isBefore(tempDate)) //past dates
            {
                data2.remove(i);
                i--;
            }
        }
        viewTable.setItems(data2);
    }*/
    /*
    @FXML
    private void filterByFuture() throws ClassNotFoundException
    {
        if(!fBooking.isSelected())
        {
            return;
        }
        byToday.setSelected(false);
        pBooking.setSelected(false);
        showAll2.setSelected(false);
        
        data2 = FXCollections.observableArrayList(tempData2);
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i=0; i<data2.size(); i++)
        {
           LocalDate tempDate = LocalDate.parse(data2.get(i).getBookingDate(),formatter);
            if(now.isAfter(tempDate)) //past dates
            {
                data2.remove(i);
                i--;
            }
        }
        viewTable.setItems(data2);    
    }*/
    
    @FXML
    private void showAllBooking() throws ClassNotFoundException
    {
        if(!showAll.isSelected())
        {
            return;
        }
        
        byToday.setSelected(false); 
        byMonth.setSelected(false);
        pBooking.setSelected(false);
        fBooking.setSelected(false);
        
        //buildBooking();
    }
    
    @FXML
    private void showAllBooking2() throws ClassNotFoundException
    {
        if(!showAll2.isSelected())
        {
            return;
        }
        
        pBooking.setSelected(false);
        fBooking.setSelected(false);
        
        //buildBookingForTable2();
    }
    
    @FXML
    private void home(ActionEvent event) throws Exception{
        Stage stage = (Stage) this.homeButton.getScene().getWindow();
       Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }
    
    private ObservableList<String> fillUpComboBox() {
        ArrayList<String> fillup = new ArrayList<>();
        fillup.add("firstName");
        fillup.add("lastName");
        fillup.add("regNumber");
        fillup.add("make");
        
        return FXCollections.observableArrayList(fillup);
    };
    
    
    
   @FXML
        public void searchFunction(ActionEvent event) throws Exception
        {
            FilteredList<DiagAndRepBooking> filteredData = new FilteredList<>(loadAllData(),e -> true);
            searchtxt.setOnKeyReleased(e ->{
                searchtxt.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate(DiagAndRepBooking -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        
                        String lowerCaseFilter = newValue.toLowerCase();
                        
                        if(filter.equals("firstName")){
                            if(DiagAndRepBooking.getFirstName().contains(newValue)){
                                return true;
                            }
                        }
                        if(filter.equals("lastName")) {
                            if(DiagAndRepBooking.getLastName().contains(newValue)) {
                                return true;
                            }
                        }
                        if(filter.equals("regNumber"))
                        {
                            if(DiagAndRepBooking.getVehicleRegistration().contains(newValue)) 
                            {
                                return true;
                            }
                        }
                        
                        if(filter.equals("make")){
                            if(DiagAndRepBooking.getMake().contains(newValue)) {
                                return true;
                            }
                        }

                        return false;
                    });
                });
                SortedList<DiagAndRepBooking> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(searchTable.comparatorProperty());
                searchTable.setItems(sortedData);
            });
        }

    
 /*   
    @FXML
        public void searchFunction(ActionEvent event) throws Exception
        {
            FilteredList<DiagAndRepBooking> filteredData = new FilteredList<>(loadAllData(),e -> true);
            searchtxt.setOnKeyReleased(e ->{
                searchtxt.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate(DiagAndRepBooking -> {
                        if(newValue == null || newValue.isEmpty()){
                           return true;
                        }
                       
                        String lowerCaseFilter = newValue.toLowerCase();
                        
                        if(filter.equals("firstName")){
                            if(DiagAndRepBooking.getFirstName().contains(newValue)){
                                return true;
                            }
                        }
                        if(filter.equals("lastName")) {
                            if(DiagAndRepBooking.getLastName().contains(newValue)) {
                                return true;
                            }
                        }
                        if(filter.equals("regNumber")){
                            if(DiagAndRepBooking.getVehicleRegistration().contains(newValue)) {
                                return true;
                            }
                        }
                        return false;
                    });
                });
                SortedList<DiagAndRepBooking> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(searchTable.comparatorProperty());
                searchTable.setItems(sortedData);
            });
        }
*/
        
        @FXML
    private void refresh(ActionEvent event) throws Exception {
       DBConnection dbq = new DBConnection();
     // data = dbq.loadAllData();
       searchTable.setItems(data);
    }

    

    
    
    
    
    
        
    }
