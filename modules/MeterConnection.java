package modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import implementation.User;
import implementation.Connect;
import implementation.Global;

public class MeterConnection {
	    
	    public static void addConnection() {
	    	Scanner scanner = new Scanner(System.in);
	        System.out.println("Add a new connection");
	        
	        System.out.print("Enter the Name: ");
	        String connectionName = scanner.next();
	        
	        System.out.print("Enter Connection Number: ");
	        long connectionNumber = scanner.nextLong();
	        
	        System.out.println("Enter Connection Type");
	        String connectionType = scanner.next();
	        
	        System.out.println("Enter Connection Address");
	        String connectionAddress = scanner.next();
	        
	        int userId = Global.userId;
	        Connect connect = new Connect(userId, connectionName, connectionNumber, connectionType, connectionAddress);
	        boolean connectionResult = connect.addConnection();
	        if(connectionResult) {
	        	System.out.println("Connection added successfully!");
	        }
	        else {
	        	System.out.println("Failed to add connection!");
	        }
	    }
	    
	    public static void viewConnection() {
	        System.out.println("View all connections");
	            
	            Connect connect = new Connect(0, null, 0, null, null);
		        ResultSet connectionResult = connect.viewConnection();
		        try {
		  
					while(connectionResult.next()) {
						
						System.out.println("Connection Name:"+connectionResult.getString("connection_name"));
						System.out.println("Connection Number:"+connectionResult.getLong("connection_no"));
						System.out.println("Connection Type:"+connectionResult.getString("connection_type"));
						System.out.println("Connection Address:"+connectionResult.getString("connection_address"));
					}
					Options.connectionboard();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		      
		    }
        
	    
	    
	    public static void removeConnection() {
        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Remove connection");
        System.out.print("Enter Connection Number: ");
        long connection_no = scanner.nextLong();
	       
	    Connect connect=new Connect(0, null, connection_no,null, null);
	    boolean result = connect.removeConnection();
        if(result) {
	    	Options.connectionboard();	
	    }
	    else {
	    	System.out.println("Failed! Try again");
	    }
	   
        Options.connectionboard();
        }
	    catch(Exception exception) {
	    	System.out.println(exception);
	    }
	    finally {
	    	scanner.close();
	    }
	    }
}



	    


