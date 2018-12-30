/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import common.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class databaseQueries{
    
      int lastid;
      Database db;
      Database db1;
      Database db2;
      Database db3;
      Database db4;
      
      Connection connection;
      Connection connection1;
      Connection connection2;
      Connection connection3;
      Connection connection4;
      
      public databaseQueries(){
          
           db = Database.getInstance();
           connection = db.getConnection();
           
           db1 = Database.getInstance();
           connection1 = db1.getConnection();
           
           db2 = Database.getInstance();
           connection2 = db2.getConnection();
           
           db3 = Database.getInstance();
           connection3 = db3.getConnection();
      
           db4 = Database.getInstance();
           connection4 = db4.getConnection();
      }
      
      public ObservableList<Vehicle> buildData(){
           ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
           
             try {
          
           PreparedStatement ps;
            
            ps = connection.prepareStatement("SELECT * FROM Vehicle");

            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {
                 
                 vehicleData.add(new Vehicle(
                 rs.getInt("ID"),
                 rs.getInt("warrantyID"),
                 rs.getString("RegNumber"),
                 rs.getString("Model"),
                 rs.getString("make"),
                 rs.getDouble("engineSize"),
                 rs.getString("FuelType"),
                 rs.getString("Colour"),
                 rs.getString("MOTrenewalDate"),
                 rs.getString("lastServiceDate"),
                 rs.getInt("currentMileage"),
                 rs.getString("vehicleType")));
             }
        }catch(Exception e){
             e.printStackTrace(System.out);
            
            System.out.println("error");
            
        }
             return vehicleData;
      }
          public ObservableList<Warranty> WarrantyData(){
           ObservableList<Warranty> warrantyData = FXCollections.observableArrayList();
           
             try {
          
           PreparedStatement ps1;
            
            ps1 = connection1.prepareStatement("SELECT * FROM Warranty");
         
            ResultSet rs1 = ps1.executeQuery();
            
             while (rs1.next()) {
             
                 warrantyData.add(new Warranty(
                 rs1.getInt("ID"),
                 rs1.getString("Name"),
                 rs1.getString("Address"),
                 rs1.getString("dateOfExpiry")));
             }
            
        }catch(Exception y){
             y.printStackTrace(System.out);
            
            System.out.println("error1");
            
        }
             return warrantyData;
          
      }
          
         public ObservableList<PartsBooking> PartsBookingData(){
           ObservableList<PartsBooking> PartsBookingData = FXCollections.observableArrayList();
           
             try {
          
           PreparedStatement ps2;
            
            ps2 = connection2.prepareStatement("SELECT  Booking.partsID, Parts.name, Booking.date, Booking.balance FROM Booking INNER JOIN Parts ON Booking.partsID = Parts.id WHERE Booking.date < DATETIME('now')");
          
            ResultSet rs2 = ps2.executeQuery();
            
             while (rs2.next()) {
                 
                 PartsBookingData.add(new PartsBooking(
                 rs2.getInt("partsID"),
                 rs2.getString("name"),
                 rs2.getString("date"),
          
                 rs2.getInt("balance")));
             }

        }catch(Exception z){
             z.printStackTrace(System.out);
            
            System.out.println("error2");
            
        }
             return PartsBookingData;
      }
         public ObservableList<PartsBooking1> PartsBookingData1(){
           ObservableList<PartsBooking1> PartsBookingData1 = FXCollections.observableArrayList();
           
             try {
          

           PreparedStatement ps3;
            
            ps3 = connection3.prepareStatement("SELECT  Booking.partsID, Parts.name, Booking.date, Booking.balance FROM Booking INNER JOIN Parts ON Booking.partsID = Parts.id WHERE Booking.date > DATETIME('now')");
            
            ResultSet rs3 = ps3.executeQuery();
            
             while (rs3.next()) {
                 
                 PartsBookingData1.add(new PartsBooking1(
                 rs3.getInt("partsID"),
                 rs3.getString("name"),
                 rs3.getString("date"),
                 rs3.getInt("balance")));
             }
            
                /*  
             ps2.close();
            connection2.close();*/
        }catch(Exception z){
             z.printStackTrace(System.out);
            
            System.out.println("error3");
            
        }
             return PartsBookingData1;
      }
          public ObservableList<Customer> CustomerData(){
           ObservableList<Customer> customerData = FXCollections.observableArrayList();
           
             try {
          

           PreparedStatement ps4;
            
            ps4 = connection4.prepareStatement("SELECT * FROM customer");
            
            
            
            ResultSet rs4 = ps4.executeQuery();
            
             while (rs4.next()) {
             
                 customerData.add(new Customer(
                 rs4.getInt("id"),
                 rs4.getString("firstName"),
                 rs4.getString("lastName"),
                 rs4.getString("address"),
                 rs4.getString("phoneNumber"),
                 rs4.getString("email")));
             }
            
        }catch(Exception a){
             a.printStackTrace(System.out);
            
            System.out.println("error4");
            
        }
             return customerData;
          
      }
}
