package modules;

import java.util.Scanner;

import implementation.Global;
import implementation.User;

public class Login {

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        try {
	        System.out.print("Enter your User Name: ");
	        String username = scanner.next();
	
	        System.out.print("Enter your Password: ");
	        String password = scanner.next();
	        
	        User user = new User(0,"", "", username, password, "", "", 0, 0,0);
	        boolean result = user.validateLogin();
	        
	        if(result) {
	        	String role = Global.role;
	        	if(role.equals("User")) {
	        		Options.dashboard();
	        	}
	        	else {
	        		Options.admindashboard();
	        	}
	        	
	        }
	        else {
	        	System.out.println("Account not found");
	        	System.out.println("Please register");
	        	Options.homepage();
	        }
        }
        catch(Exception exception) {
        	System.out.println(exception);
        }
        finally {
        	scanner.close();
        }
    }
}


