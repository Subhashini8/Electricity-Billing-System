package modules;

import java.util.*;
public class Options {
	public static void homepage() {
	Scanner scanner=new Scanner(System.in);
	System.out.println("Login or Register");
	System.out.println("1.Login");
	System.out.println("2.Register");
	System.out.println("3.Exit");
	System.out.println("Enter your option:");
	int option=scanner.nextInt();
	switch(option) {
	case 1:
		Login.login();
		break;
	case 2:
		Registration.register();
		break;
	case 3:
		System.out.println("Application is closed");
		System.exit(0);
		break;
		
	}
	}
	public static void dashboard() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("1.User Profile");
		System.out.println("2.Connections");
		System.out.println("3.Bill");
		System.out.println("4.Payment");
		System.out.println("5.Logout");
		System.out.println("Enter your option:");
		int option=scanner.nextInt();
		switch(option) {
		case 1:
		     UserProfile.viewProfile();
			break;
		case 2:
			connectionboard();
			break;
		case 3:
		    billingDashboard();
			break;
		case 4:
			Payment.payBill();
			break;
		case 5:
			homepage();
			break;	
		default:
			System.out.println("Invalid Option");
			dashboard();
		}
}
public static void connectionboard() {
	Scanner scanner=new Scanner(System.in);
	System.out.println("Select an option:");
    System.out.println("1. Add a new connection");
    System.out.println("2. View all connections");
    System.out.println("3. Remove a connection");
    System.out.println("4. Exit");
	System.out.println("Enter your option:");
	
	int option=scanner.nextInt();
	switch(option) {
	case 1:
		MeterConnection.addConnection();
		break;
	case 2:
		MeterConnection.viewConnection();
		break;
	case 3:
		MeterConnection.removeConnection();
		break;
	case 4:
		dashboard();
		break;		
	default:
		System.out.println("Invalid Option");
		connectionboard();
	}
	connectionboard();
}

public static void admindashboard(){
	System.out.println("1.Unit Management");
	System.out.println("2.Logout");
	Scanner scanner=new Scanner(System.in);
	int option=scanner.nextInt();
	switch(option) {
	case 1:
		Administration.unitManagement();
		break;
	case 2:
		homepage();
		break;
	default:
		System.out.println("Invalid option");
		admindashboard();
		break;		
	}
}

public static void billingDashboard() {
	Scanner scanner=new Scanner(System.in);
	System.out.println("1.Individual Bill");
	System.out.println("2.All Bills");
	System.out.println("3.Back");
	int option=scanner.nextInt();
	switch(option) {
	case 1:
		Bill.fetchIndividualBill();
		break;
	case 2:
		Bill.fetchAllBill();
		break;
	case 3:
		dashboard();
		break;
}
}





}




	