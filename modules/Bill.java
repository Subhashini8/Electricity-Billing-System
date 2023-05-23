package modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import implementation.Billing;

public class Bill {
public static void fetchIndividualBill() {
	Scanner scanner=new Scanner(System.in);
	
	try {
		System.out.println("");
		System.out.println("Enter the Connection Number: ");
		long connectionNumber=scanner.nextLong();
		
		Billing billing=new Billing(0, 0, connectionNumber, 0, 0, null, null, null);
		ResultSet billingResultSet=billing.fetchIndividualBill("all");
		while(billingResultSet.next()) {
			System.out.println("");
			System.out.println("Bill");
			System.out.println("Billing ID: "+billingResultSet.getInt("billing_id"));
			System.out.println("Units Consumed: "+billingResultSet.getLong("units_consumed") );
			System.out.println("Bill Amount: "+billingResultSet.getLong("bill_amount"));
			System.out.println("Due Date: "+billingResultSet.getDate("due_date"));
			System.out.println("Paid Date: "+billingResultSet.getDate("paid_date"));
			System.out.println("Payment Status: "+billingResultSet.getString("payment_status"));
		}
		Options.billingDashboard();
		
		
	} 
	catch (SQLException exception) {
		exception.printStackTrace();
		Options.billingDashboard();
	}	
}

public static void fetchAllBill(){
	Billing billing=new Billing(0, 0, 0, 0, 0, null, null, null);
	ResultSet fetchAllBillResult=billing.fetchAllBill();
	try {
		while(fetchAllBillResult.next()) {
			System.out.println("");
			System.out.println("Bill");
			System.out.println("Billing ID: "+fetchAllBillResult.getInt("billing_id"));
			System.out.println("Units Consumed: "+fetchAllBillResult.getLong("units_consumed") );
			System.out.println("Bill Amount: "+fetchAllBillResult.getLong("bill_amount"));
			System.out.println("Due Date: "+fetchAllBillResult.getDate("due_date"));
			System.out.println("Paid Date: "+fetchAllBillResult.getDate("paid_date"));
			System.out.println("Payment Status: "+fetchAllBillResult.getString("payment_status"));
		}
		Options.billingDashboard();
	} catch (SQLException e) {
		e.printStackTrace();
		Options.billingDashboard();
	}
	
}
}
