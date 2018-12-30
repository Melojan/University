/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import common.Database;
import customers.logic.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AuthDbMethods {
          Database db;
         Connection connection;
      
      
      
      public AuthDbMethods(){
           db = Database.getInstance();
           connection = db.getConnection();
      }
      
      
      public boolean logUserIn(String username, String password) {
          
          boolean result = false;
                   
             try {
			 
			
			PreparedStatement finduser;
			
                     
			finduser = connection.prepareStatement("Select * from Admin Where id= ? and pass =?");
                        finduser.setString(1,username);
                        finduser.setString(2,password);
                       
                        ResultSet rs = finduser.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
			
                            Session.setId(rs.getInt("id"));
                            
                            Session.setType(rs.getInt("Type"));
                            
                        
                            Session.setLastName(rs.getString("lastName"));
                        
                            Session.setFirstName(rs.getString("firstName"));
                            result = true;
                                
			}
                        
                      
		}
		catch (SQLException ex) {
                   
                    
			//System.err.println(ex.getMessage());
		}
             
        return result;
          
          
         
      }
      
      
      public ObservableList<Admin> getAllAdmins(){
          ObservableList<Admin> admins = FXCollections.observableArrayList();
              try {
			 
			
			PreparedStatement getusers;
			
                     
			getusers = connection.prepareStatement("Select * from Admin");
                        
                       
                        ResultSet rs = getusers.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				String userType = "";
                            if (rs.getInt("type")==1){
                                userType = "Admin";
                            } else {
                                userType = "System User";
                            }
                           String firstName = rs.getString("firstName");
                           String lastName = rs.getString("lastName");
                            
                        admins.add(new Admin(rs.getInt("id"),firstName,lastName, userType,rs.getString("lastLoginDate")));
                                
			}
                        
                      
		}
		catch (SQLException ex) {
                   
                    
			//System.err.println(ex.getMessage());
		}
          
          
          
          return admins;
      }
      
      public Admin findAdmin(int id){
          Admin admin = new Admin();
              try {
			 
			
			PreparedStatement getusers;
			
                     
			getusers = connection.prepareStatement("Select * from Admin WHERE id=?");
                        
                       getusers.setInt(1, id);
                        ResultSet rs = getusers.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				String userType = "";
                            if (rs.getInt("type")==1){
                                userType = "Admin";
                            } else {
                                userType = "System User";
                            }
                           String firstName = rs.getString("firstName");
                           String lastName = rs.getString("lastName");
                            
                        admin = new Admin(rs.getInt("id"),firstName,lastName, userType,rs.getString("lastLoginDate"));
                                
			}
                        
                      
		}
		catch (SQLException ex) {
                   
                    
			//System.err.println(ex.getMessage());
		}
          
          
          
          return admin;
      }
      public ObservableList<Admin> getActiveAdmins(){
          // admins that have logged in with the last day
          ObservableList<Admin> admins = FXCollections.observableArrayList();
              try {
			 
			
			PreparedStatement getusers;
			
                     
			getusers = connection.prepareStatement("SELECT  * FROM   Admin\n" +
                                        "WHERE   lastLoginDate >= datetime('now','-1 day')");
                        
                       
                        ResultSet rs = getusers.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				String userType = "";
                            if (rs.getInt("type")==1){
                                userType = "Admin";
                            } else {
                                userType = "System User";
                            }
                           String firstName = rs.getString("firstName");
                           String lastName = rs.getString("lastName");
                            
                        admins.add(new Admin(rs.getInt("id"),firstName,lastName, userType,rs.getString("lastLoginDate")));
                                
			}
                        
                      
		}
		catch (SQLException ex) {
                   
                    
			//System.err.println(ex.getMessage());
		}
          
          
          
          return admins;
      }
      
       public void update_adminType(int type, int id)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Admin SET Type =? WHERE id = ?");
			
                        updatedb.setInt((1), type);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
       public boolean update_password(String pass, int id)
         {
             boolean success = false;
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Admin SET pass =? WHERE id = ?");
			
                        updatedb.setString((1), pass);

                        updatedb.setInt(2, id);
                       int action = updatedb.executeUpdate();
                        if (action >0){
                            success = true;
                        } else {
                            success = false;
                        }
                       
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
              
              return success;
         }
       public void update_adminFirstName(String fName, int id)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Admin SET firstName =? WHERE id = ?");
			
                        updatedb.setString((1), fName);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
       public void update_adminLastName(String lName, int id)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Admin SET lastName =? WHERE id = ?");
			
                        updatedb.setString((1), lName);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
       public void setTimeStamp(String timeStamp, int id)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Admin SET lastLoginDate =? WHERE id = ?");
			
                        updatedb.setString((1), timeStamp);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
       public void removeUser(int id)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("DELETE from Admin WHERE id = ?");
			
                        

                        updatedb.setInt(1, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
       
                       public int add_user(String fName, String lName,  String pass,int cType)
                
        {
      
            int lastid =0;
            
            //take the addressID and put it into customer field and create customer
            
             try {
			
			
			PreparedStatement insertdb;
			
                        insertdb = connection.prepareStatement("insert into 'Admin' (firstName,lastName,pass,Type) values(?,?,?,?)");
			
                        insertdb.setString((1), fName);
                        insertdb.setString(2, lName);
                        insertdb.setString(3, pass);
                        insertdb.setInt(4, cType);
                        insertdb.executeUpdate();
                        ResultSet rs = insertdb.getGeneratedKeys();
                        
                        if (rs.next()) {
                             lastid = rs.getInt(1);
                                }
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
             return lastid;
		
           
        }
}
