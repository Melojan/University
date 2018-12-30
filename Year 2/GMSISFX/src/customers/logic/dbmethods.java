/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import common.Database;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class dbmethods {
    
      int lastid;
      Database db;
      Connection connection;
      
      
      
      public dbmethods(){
           db = Database.getInstance();
           connection = db.getConnection();
      }
     
         
            
        public void remove_customer(int id)
                
        {
             try {
			
			
			PreparedStatement deleteuser;
			
                        deleteuser = connection.prepareStatement("DELETE FROM customer WHERE id =?");
			
                        deleteuser.setInt(1, id);
                        deleteuser.executeUpdate();  
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
        }
        

        
                public int add_customer(String fName, String lName, int cType, String address,String pNumber, String eml)
                
        {
      
            
            
            //take the addressID and put it into customer field and create customer
            
             try {
			
			
			PreparedStatement insertdb;
			
                        insertdb = connection.prepareStatement("insert into 'Customer' (firstName, lastName,customerType,address,phoneNumber,email) values(?,?,?,?,?,?)");
			
                        insertdb.setString((1), fName);
                        insertdb.setString(2, lName);
                        insertdb.setInt(3, cType);
                        insertdb.setString(4, address);
                        insertdb.setString(5, pNumber);
                        insertdb.setString(6, eml);
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
                
                
                
                
        public void listAll_customer()
        {
               try {
			
			
			PreparedStatement listall;
			
                        listall = connection.prepareStatement("SELECT * FROM customer");
                        
			
                        //listall.setInt(1, id);
                        ResultSet rs = listall.executeQuery();  
                        while(rs.next()){
				System.out.println(rs.getInt("id")+"	"+rs.getString("firstName"));
			}
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		
        }
           
         public void update_customerFname(int id, String fName)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET firstName =? WHERE id = ?");
			
                        updatedb.setString((1), fName);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        System.out.println("Did it");
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
        
          public void update_bookingPaid(int id, int paid)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE Booking SET paid =? WHERE id = ?");
			
                        updatedb.setInt((1), paid);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
         
         
         
                  public void update_customerLname(int id, String lName)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET lastname =? WHERE id = ?");
			
                        updatedb.setString((1), lName);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }
        public void update_customerType(int id, int Type)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET customerType =? WHERE id = ?");
			
                        updatedb.setInt((1), Type);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }         
        
         public void update_customerEmail(int id, String email)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET email =? WHERE id = ?");
			
                        updatedb.setString((1), email);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }  
         
        public void update_customerPhone(int id, String phone)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET phoneNumber =? WHERE id = ?");
			
                        updatedb.setString((1), phone);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }  
        
         public void update_customerAddress(int id, String address)
         {
              try {
			
			
			PreparedStatement updatedb;
			
                        updatedb = connection.prepareStatement("UPDATE customer SET address =? WHERE id = ?");
			
                        updatedb.setString((1), address);

                        updatedb.setInt(2, id);
                        updatedb.executeUpdate();
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
         }  
        
          public ObservableList<Bookings> getbillsfor_customer(int id)
                
        {
            // This gets all of the bills for a customer
          
           ObservableList<Bookings> bookingsList = FXCollections.observableArrayList();
            try {
			
			
			PreparedStatement listbills;
			
                        listbills = connection.prepareStatement("SELECT b.date as dt, b.paid,b.balance,b1.id AS bookID,c.customerID, b1.name, v.make as vehName,v.regNumber,w.name as warrantyName, p.name as partsName,b.returnStatus\n" +
"                                    FROM Booking b \n" +
"                                	INNER JOIN customerVehicle c ON ( b.customerVehicle = c.id  )  \n" +
"									left join Parts p on (b.partsiD = p.id)\n" +
"									INNER JOIN vehicle v on (v.id = c.vehicleID)\n" +
"									left Join Warranty w on (w.id =  v.warrantyId)\n" +
"                                		INNER JOIN customer c1 ON ( c.customerID = c1.id  )  \n" +
"                                	INNER JOIN BookingType b1 ON ( b.bookingType = b1.id  )  \n" +
"                                	where c1.id =? AND w.name IS NULL");
                        
			
                        listbills.setInt(1, id);
                        ResultSet rs = listbills.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
                            
                            String warrantyName = rs.getString("warrantyName");
                              if (rs.wasNull())
                                 warrantyName = "Not Available";
                              
                              String partsName = rs.getString("partsName");
                              if (rs.wasNull())
                                 partsName = "No parts were repaired";
                              
                              
                                Bookings booking = new Bookings(rs.getInt("bookID"),rs.getString("balance"), rs.getString("name"), rs.getInt("paid"),rs.getString("dt"),rs.getInt("returnStatus"),rs.getString("vehName"),partsName,warrantyName,rs.getString("regNumber"));
                                 bookingsList.add(booking);
                            
                               //     System.out.println(rs.getInt("bookID"));
                                 //   System.out.println(rs.getString("name"));
                                   //  System.out.println(rs.getString("balance"));
			}
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
            
            return bookingsList;
        }
                          
        public Double getTotalBillCost_customer(int id)
                
        {
                       Double balance = 0.0;
                         try {
			
			
			PreparedStatement sumbills;
			
                        sumbills = connection.prepareStatement("SELECT b.id, sum(b.balance) As balance, c.customerID, c1.id\n" +
                                                    "FROM Booking b \n" +
                                                         " INNER JOIN customerVehicle c ON ( b.customerVehicle = c.id  )  \n" +
                                                         " INNER JOIN Vehicle v ON (v.id = c.vehicleID  )  \n" +
                                                    "   INNER JOIN customer c1 ON ( c.customerID = c1.id  )  \n" +
                                                    " WHERE c.customerID = ? AND b.paid = 0 AND v.warrantyId IS NULL");
                        
			
                        sumbills.setInt(1, id);
                        ResultSet rs = sumbills.executeQuery();  
                        while(rs.next()){
				// System.out.println(rs.getInt("id")+"	"+rs.getString("firstName"));
                                balance = rs.getDouble("balance");
			}
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
                         
                    return balance;
         
        }
                    
        public ObservableList<Customer> getActiveListOf_customer( )
                
        {
            ObservableList<Customer> cust = FXCollections.observableArrayList();
           
            // Active customers are people with return status = 0, so items have not been returned.
            // active customers have an active booking
            
             try {
			 
			
			PreparedStatement listactive;
			
                        listactive = connection.prepareStatement("select booking.customerVehicle,customerVehicle.customerID,customer.firstName,customer.lastName,customer.customerType,customer.email,customer.phoneNumber,customer.address from booking \n" +
                        "inner join customerVehicle on booking.customerVehicle = customerVehicle.id\n" +
                        "inner join customer on customer.id = customerVehicle.customerID\n" +
                        "where booking.returnstatus = 0 \n"
                                + "GROUP BY (customerVehicle.customerID)");
                        
			
                       
                        ResultSet rs = listactive.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				cust.add(new Customer(rs.getInt("customerID"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address")));
                                System.out.println(rs.getInt("customerId"));
                               
                                 System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                
			}
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
             return  cust;
        }
        public ObservableList<Customer> getFirst20Customers()
                
        {
            ObservableList<Customer> cust = FXCollections.observableArrayList();
           
            // Active customers are people with return status = 0, so items have not been returned.
            // active customers have an active booking
            
             try {
			 
			
			PreparedStatement listactive;
			
                        listactive = connection.prepareStatement("SELECT * from customer \n"
                                                                     + "LIMIT 20");
                        
			
                       
                        ResultSet rs = listactive.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				cust.add(new Customer(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address")));
                                System.out.println(rs.getInt("id"));
                               
                                 System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                
			}
                        
                      
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
             return  cust;
        }
        
       public ObservableList<Customer> searchWithFirstOrLastName_customer(String Name, int type)
       {
            ObservableList<Customer> cust = FXCollections.observableArrayList();
           
           // if user enters just one word, we will search it in the first and last name
            try {
			
			
			PreparedStatement searchn;
			
                        searchn = connection.prepareStatement("select * from customer\n" +
                                                    "where (customer.firstName Like ? \n" +
                                                    "or\n" +
                                                    "customer.lastName LIKE ?)"
                                                            + "And customer.customerType = ?");
                        
			searchn.setString(1, "%"+Name+"%");
                        searchn.setString(2, "%"+Name+"%");
                        searchn.setInt(3,type);
                        ResultSet rs = searchn.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				//System.out.println(rs.getInt(1));
                                //System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                
                                cust.add(new Customer(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address")));
			}
                        
                      
		}
                catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
            return cust;
       }
       
         public ObservableList<Vehicle> findVehiclesByCustomer(int customerID)
       {
           
            ObservableList<Vehicle> veh = FXCollections.observableArrayList();
            try {
			
			
			PreparedStatement searchveh;
			
                        searchveh = connection.prepareStatement("select * from Vehicle\n" +
                        "Inner join customerVehicle on (customerVehicle.vehicleID=Vehicle.id)\n" +
                        "Where customerVehicle.customerID = ?");
                        
			searchveh.setInt(1,customerID); // the percetnage is a wildcard meaning zero or more
                        
                            
                        ResultSet rs = searchveh.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				//System.out.println(rs.getInt(1));
                               //  System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                veh.add(new Vehicle(rs.getString("make") + " "+rs.getString("model"), rs.getString("regNumber"), rs.getString("model"),rs.getString("make"), rs.getString("engineSize"),rs.getString("fuelType"),rs.getString("colour")));
			}
                        
                      
		}
                catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
            
          
           return veh;
           
           
       }
              
       public ObservableList<Customer> searchWithFullName_customer(String fName, String lName, int type)
       {
           
            ObservableList<Customer> cust = FXCollections.observableArrayList();
            try {
			
			
			PreparedStatement searchlnfn;
			
                        searchlnfn = connection.prepareStatement("select * from Customer\n" +
                        "where customer.lastName LIKE ?  and customer.firstName LIKE ? \n"
                                + "AND customer.customerType =?" +
                        "COLLATE NOCASE");
                        
			searchlnfn.setString(1,"%"+lName+"%"); // the percetnage is a wildcard meaning zero or more
                        searchlnfn.setString(2, "%"+fName+"%");
                        searchlnfn.setInt(3, type);    
                        ResultSet rs = searchlnfn.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				//System.out.println(rs.getInt(1));
                               //  System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                cust.add(new Customer(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address")));
			}
                        
                      
		}
                catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
            
          
           return cust;
           
           
       }
       
          public ObservableList<Customer> searchWithRegNumber(String regNumber)
       {
           
            ObservableList<Customer> cust = FXCollections.observableArrayList();
            try {
			
			
			PreparedStatement searchreg;
			
                        searchreg = connection.prepareStatement("Select * from customer\n" +
                        "inner join customerVehicle on customer.id = customerVehicle.customerID\n" +
                        "inner join vehicle on customerVehicle.vehicleID = vehicle.id\n"
                                + " Where Vehicle.regNumber LIKE ?"+
                                "GROUP BY customer.id");
                        
			searchreg.setString(1,"%"+regNumber+"%"); // the percetnage is a wildcard meaning zero or more
                        
                            
                        ResultSet rs = searchreg.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				//System.out.println(rs.getInt(1));
                               //  System.out.println(rs.getString("firstname")+"	"+rs.getString("lastName"));
                                cust.add(new Customer(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address")));
			}
                        
                      
		}
                catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
            
          
           return cust;
           
           
       }
       
       public Customer findCus(int id){
            Customer customer = new Customer();
           try {
			
			
			PreparedStatement searchln;
			
                        searchln = connection.prepareStatement("select * from Customer\n" +
                        "where customer.id = ?");
                        
			searchln.setInt(1, id);
                       
                        ResultSet rs = searchln.executeQuery();  
                        //System.out.println(rs);
                        while(rs.next()){
				 customer = new Customer(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastName"),rs.getInt("customerType"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("address"));
                              
			}
                        
                      
		}
                catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
           
          // update
          return customer;
       }
       

           
                
    
}
