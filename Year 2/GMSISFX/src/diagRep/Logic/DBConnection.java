/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.Logic;

import common.Database;

import java.sql.*;

public class DBConnection {
    
   
    
    public static void connect(){
        

        Connection conn = null;
        try {
          
            String url = "jdbc:sqlite::resource:data.db";
            
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }

    
    }
    
//    public void addBooking(Booking booking){
//        
//        
//        try{
//            
//            Statement stmn = connection.createStatement();
//            
//            String sql = "INSERT INTO Booking (id, bookingType, paid, datetime, balance, customerVehicule, partsID, spcID, sentOut,returnstatus ) " +
//                    "VALUES ('" + booking.getId() + "', " + booking.getBookingType() + ", '" + booking.getPaid() + "', '" + booking.getDatetime() + "', " + booking.Balance() + ", '" + booking.CustomerVehicule() + ", '" + booking.getPartsID() + ", '" + booking.getSpcID() + ", '" + booking.getSentOut() + ", '" + booking.getReturnstatus()+ ", '";
//
//                stmn.executeUpdate(sql);            
//            
//            connection.commit();
//            
//            System.out.print("Added Successfully\n");
//            
//        }
//        catch (Exception e) {
//    
//            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
//            
//        }
//        
//        
//        
//    }
//    
//    
//    public void deleteBooking(Booking booking){
//        
//        try {
//            Statement stmn = connection.createStatement();
//            
//            String sql = "DELETE FROM Booking Where bookingID = " + booking.getId() + ";";
//            
//            stmn.executeUpdate(sql);
//            
//            connection.commit();
//            
//            System.out.println("Deleted Successfully");
//        } catch (Exception e) {
//            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
//        }
//        
//    }
//    
//    
//    public void editBooking(Booking booking){
//        
//        try {
//            Statement stmn = connection.createStatement();
//            
//            //merVehicule() + "  " + booking.getDatetime() + "  " + booking. + "  " + booking.getEmpID() + "  " + booking .getBookingID());
//            
//            String sql = "Update Booking " + 
//                            "SET ID = " + booking.getId() +
//                             ", bookingDate = '" + booking.getBookingType() +
//                             "', paid = '" + booking.getPaid() + 
//                             "', datetime = " + booking.getDatetime() + 
//                             ", balance = " + booking.Balance() + 
//                             ", customerVehicule = " + booking.CustomerVehicule() +
//                              ", partsID = " + booking.getPartsID() +
//                            ", spcID= " + booking.getSpcID()+
//                             ", sentOut= " + booking.getSentOut() +
//                             " WHERE returnstatus = " + booking.getReturnstatus() + ";";
//            
//            stmn.executeUpdate(sql);
//            
//            connection.commit();
//            
//            System.out.println("Editted Correctly");
//        } catch (Exception e) {
//            System.out.println(e.getClass().getName() + ": " + e.getMessage() );
//        }
//        
//    }

//}
