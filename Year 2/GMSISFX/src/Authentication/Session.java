/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;


public class Session {
    private static int id  = -1;
    private static int type = -1;
    private static String firstName = "Unknown";
    private static String lastName = "Unknown";

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        if (Session.firstName.equals("Unknown")) {
              Session.firstName = firstName;
        }
      
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        if (Session.lastName.equals("Unknown")){
            Session.lastName = lastName;
        }
        
    }
    

    public static int getId() {
        return id;
    }



    public static void setId(int id) {
        if (Session.id == -1){
            Session.id = id;
        } else {
            System.out.println("ID already Set, please logout");
        }
        
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        if (Session.type == -1){
            Session.type = type;
        } else{
            System.out.println("Type already set");
        }
        
    }
    
    public static void Logout(){
        Session.id = -1;
        Session.type = -1;
        Session.firstName = "Unknown";
        Session.lastName = "Unknown";
    }
    
    

    
        
    }

