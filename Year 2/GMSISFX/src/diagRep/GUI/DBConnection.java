/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diagRep.GUI;

import common.Database;
import diagRep.Logic.DiagAndRepBooking;

import java.sql.*;
import javafx.collections.ObservableList;

public class DBConnection {
    
    
    
    Connection connection = null;
    PreparedStatement ps ;
    ResultSet rs;
    Database db = Database.getInstance();
    
    
    
    public DBConnection(){
        try{
            connection = db.getConnection();
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to connect database");
        }
    }
    /*
    public void addBooking(Booking booking){
        
        
        try{
            
            Statement stmn = connection.createStatement();
            
            String sql = "INSERT INTO Booking (id, bookingType, paid, datetime, balance, customerVehicule, partsID, spcID, sentOut,returnstatus ) " +
                    "VALUES ('" + booking.getId() + "', " + booking.getBookingType() + ", '" + booking.getPaid() + "', '" + booking.getDatetime() + "', " + booking.Balance() + ", '" + booking.CustomerVehicule() + ", '" + booking.getPartsID() + ", '" + booking.getSpcID() + ", '" + booking.getSentOut() + ", '" + booking.getReturnstatus()+ ", '";

                stmn.executeUpdate(sql);            
            
            connection.commit();
            
            System.out.print("Added Successfully\n");
            
        }
        catch (Exception e) {
    
            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
            
        }
        
        
    }
    
    
    public void deleteBooking(Booking booking){
        
        try {
            Statement stmn = connection.createStatement();
            
            String sql = "DELETE FROM Booking Where bookingID = " + booking.getId() + ";";
            
            stmn.executeUpdate(sql);
            
            connection.commit();
            
            System.out.println("Deleted Successfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
        }
        
    }
    
    
    public void editBooking(Booking booking){
        
        try {
            Statement stmn = connection.createStatement();
            
            //merVehicule() + "  " + booking.getDatetime() + "  " + booking. + "  " + booking.getEmpID() + "  " + booking .getBookingID());
            
            String sql = "Update Booking " + 
                            "SET ID = " + booking.getId() +
                             ", bookingtype = '" + booking.getBookingType() +
                             "', paid = '" + booking.getPaid() + 
                             "', datetime = " + booking.getDatetime() + 
                             ", balance = " + booking.Balance() + 
                             ", customerVehicule = " + booking.CustomerVehicule() +
                              ", partsID = " + booking.getPartsID() +
                            ", spcID= " + booking.getSpcID()+
                             ", sentOut= " + booking.getSentOut() +
                             " WHERE returnstatus = " + booking.getReturnstatus() + ";";
            
            stmn.executeUpdate(sql);
            
            connection.commit();
            
            System.out.println("Editted Correctly");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
        }
        
    }*/

   
}
