package modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import implementation.Billing;

public class Payment {
public static void payBill() {
Scanner scanner=new Scanner(System.in);

try {
	System.out.println("");
	System.out.println("Enter the Connection Number: ");
	long connectionNumber=scanner.nextLong();
	
	
	Billing billing=new Billing(0, 0, connectionNumber, 0, 0, null, null, "Paid");
	ResultSet billingDataResult= billing.fetchIndividualBill("pending");
	if(billingDataResult.equals(null)) {
		Options.billingDashboard();
	}
	else {
	while(billingDataResult.next()) {
		
        	System.out.println("Bill Amount: "+billingDataResult.getLong("bill_amount"));
        	
	}
	System.out.println("Do you want to pay? (Yes or No)");
	String paymentOption=scanner.next();
	if(paymentOption.equals("Yes")) {
		boolean result = billing.payBill();
		 if(result) {
		 	System.out.println("Paid Successfully");
		 	Options.billingDashboard();
		 }
		 else {
		 	System.out.println("Failed! Try again");
		 }
	}
	else {
		Options.billingDashboard();
	}
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







