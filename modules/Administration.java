package modules;

import java.util.Scanner;
import implementation.Connect;
import implementation.Global;
import modules.Options;
import implementation.Billing;

public class Administration {
public static void unitManagement() {
	Scanner scanner=new Scanner(System.in);
	System.out.println("Enter connection number: ");
	long connectionNumber=scanner.nextLong();
	System.out.println("Enter units consumed: ");
	int unitsConsumed=scanner.nextInt();
	
	Connect connect=new Connect(0, null, connectionNumber, null, null);
	boolean validationResult=connect.validateConnection();
	if(validationResult) {
		int slabRate;
		if((unitsConsumed>=0) && (unitsConsumed<=100))  {
			slabRate=3;
		
		}
		else if((unitsConsumed>100) && (unitsConsumed<=300)) {
			slabRate=5;
		}
		else if((unitsConsumed>300)&& (unitsConsumed<=500)) {
			slabRate=7;
		}
		else if((unitsConsumed>500)&&(unitsConsumed<=700)) {
			slabRate=8;
		}
		else if((unitsConsumed>700)&&(unitsConsumed<=1000)) {
			slabRate=10;
		}
		else if(unitsConsumed>1000) {
			slabRate=15;
		}
		else {
			slabRate=-2;
			
		}
		
		int userId = Global.userId; 
		String currentDateTime=Billing.getCurrentTimeStamp();
		String dueDateTime=Billing.addDaysToCurrentDateTime(currentDateTime, 30);
		Billing billing=new Billing(0, userId, connectionNumber, unitsConsumed, slabRate*unitsConsumed, dueDateTime, null, null);
		
		boolean billingResult = billing.addBilling();
		if(billingResult) {
			Options.admindashboard();
		}
		else {
			Options.admindashboard();
		}
	}
	else {
		Options.admindashboard();
	}	
}


}
