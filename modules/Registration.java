package modules;

import java.util.Scanner;
import implementation.User;

public class Registration {
	public static void register() {

 Scanner scanner = new Scanner(System.in); 
 try {
    System.out.print(" Enter the Name: ");
    String name = scanner.next();

System.out.print("Enter the Username:");
String username = scanner.next();

System.out.print("Enter the Password:");
String password=scanner.next();

System.out.print("Enter the Address:");
String address=scanner.next();

System.out.print("Enter the City");
String city=scanner.next();

System.out.print("Enter the Pincode");
Integer pincode=scanner.nextInt();

System.out.print("Enter the Mobileno");
long mobileno=scanner.nextLong();


 User user=new User(0,"", name,username,password,address,city,pincode,mobileno,0);
 boolean result = user.registerUser();
 if(result) {
 	System.out.println("Success");
 	Options.dashboard();	
 }
 else {
 	System.out.println("Failed! Try again");
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