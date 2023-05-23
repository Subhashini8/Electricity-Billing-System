package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import application.DatabaseConnection;
import modules.Options;

public class Billing {
int billingId;
int userId;
long connectionNumber;
long unitsConsumed;
long billAmount;
String dueDate;
String paidDate;
String paymentStatus; 


public Billing(int billingId, int userId, long connectionNumber, long unitsConsumed, long billAmount, String dueDate,
		String paidDate, String paymentStatus) {
	super();
	this.billingId = billingId;
	this.userId = userId;
	this.connectionNumber = connectionNumber;
	this.unitsConsumed = unitsConsumed;
	this.billAmount = billAmount;
	this.dueDate = dueDate;
	this.paidDate = paidDate;
	this.paymentStatus = paymentStatus;
}
public boolean addBilling() {
	Connection database = DatabaseConnection.establishConnection();
	
	try {
		PreparedStatement query = database.prepareStatement("INSERT INTO BILLING (USER_ID, CONNECTION_NO,UNITS_CONSUMED, BILL_AMOUNT,DUE_DATE) VALUES (?,?,?,?,?)");
			query.setInt(1, userId);
			query.setLong(2, connectionNumber);
			query.setLong(3, unitsConsumed);
			query.setLong(4, billAmount);	
			query.setString(5, dueDate);
			int addBilingResult = query.executeUpdate();
			if(addBilingResult==1) {
				System.out.println("Billing Added Successfully");
					return true;
				}
				else {
					System.out.println("Failed! Try again");
					return false;
				}
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return false;
	}
}


public ResultSet fetchIndividualBill(String action) {
	Connection database=DatabaseConnection.establishConnection();
	ResultSet fetchIndividualBill=null;
	try {
		if(action.equals("all")) {
			PreparedStatement query=database.prepareStatement("SELECT * FROM BILLING WHERE CONNECTION_NO=?");
			query.setLong(1, connectionNumber);
			fetchIndividualBill=query.executeQuery();
		}
		else {
			PreparedStatement query=database.prepareStatement("SELECT * FROM BILLING WHERE CONNECTION_NO=? AND PAYMENT_STATUS = ?");
			query.setLong(1, connectionNumber);
			query.setString(2, "Pending");
			fetchIndividualBill=query.executeQuery();
		}
		if(fetchIndividualBill.isBeforeFirst()) {
		System.out.println("Billing data exists");
		return fetchIndividualBill;
		}
		else {
			System.out.println("Billing data does not exists");
			Options.dashboard();
			return fetchIndividualBill;
			
		}
			
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return fetchIndividualBill;
	}		
}


public ResultSet fetchAllBill() {
	Connection database=DatabaseConnection.establishConnection();
	try {
		PreparedStatement query=database.prepareStatement("SELECT * FROM BILLING WHERE USER_ID=?");
		int userId=Global.userId;
		query.setInt(1, userId);
		ResultSet fetchAllBillResult= query.executeQuery();
		if(fetchAllBillResult.isBeforeFirst()) {
			System.out.println("Billing data exists");
			return fetchAllBillResult;
		}
		else {
			System.out.println("Billing data does not exists");
			return fetchAllBillResult;
		}
	}
	catch(SQLException exception) {
		exception.printStackTrace();
		return null;
	}
	
}
public boolean payBill() {
	int walletBalance=walletBalance();
	if(walletBalance>=billAmount) {
		 boolean transactionResult=transaction();
		 if(transactionResult) {
			 boolean updateResult=updateStatus();
			 if(updateResult) {
				 System.out.println("Payment done");
				 return true;
			 }
			 else {
				 System.out.println("Payment failed");
				 return false;
			 }
			 
		 }
		 else {
			 System.out.println("Payment process failed");
			 return false;
		 }
	}
		
	else {
		System.out.println("Insufficient Balance");
		return false;
	}
		
	}

public int walletBalance() {
	Connection database=DatabaseConnection.establishConnection();
	try {
		PreparedStatement query=database.prepareStatement("SELECT * FROM BILLING WHERE USER_ID=?");
		int userId=Global.userId;
		query.setInt(1, userId);
		ResultSet userDetail=query.executeQuery();
		if(userDetail.isBeforeFirst()) {
			int walletBalance=userDetail.getInt("wallet");
			return walletBalance;
			
		}
		else {
			return 0;
		}
	}
	catch(SQLException exception) {
		exception.printStackTrace();
		return 0;
	}
}

public boolean transaction() {
	Connection database=DatabaseConnection.establishConnection();
	try {
		PreparedStatement query=database.prepareStatement("UPDATE USER_CREDENTIALS SET WALLET=WALLET-? WHERE USER_ID=?");
		int userId=Global.userId;
		query.setInt(2, userId);
		query.setLong(1, billAmount);
		int transactionResult=query.executeUpdate();
		if(transactionResult==1) {
			System.out.println("Payment Success");
			return true;
		}
		else {
			System.out.println("Payment Failed");
			return false;
		}
	}
	catch(SQLException exception) {
		exception.printStackTrace();
		return false;
	}
}
public boolean updateStatus() {
	Connection database=DatabaseConnection.establishConnection();
	try {
		PreparedStatement query=database.prepareStatement("UPDATE BILLING SET PAYMENT_STATUS=?, PAID_DATE=? WHERE CONNECTION_NO=?");
		String paidDate=getCurrentTimeStamp();
		query.setString(1, paymentStatus);
		query.setString(2, paidDate);
		query.setLong(3,connectionNumber);
		
		int updateStatusResult=query.executeUpdate();
		if(updateStatusResult==1) {
			System.out.println("Paid");
			return true;
		}
		else {
			System.out.println("Failed");
			return false;
		}
	}
	catch(SQLException exception) {
		exception.printStackTrace();
		return false;
	}
}
public static String getCurrentTimeStamp() {
	
	LocalDateTime localDateTime=LocalDateTime.now();
	ZoneId zoneId=ZoneId.systemDefault();
	ZonedDateTime zonedDateTime=ZonedDateTime.of(localDateTime, zoneId);
	long currentTimestamp=zonedDateTime.toInstant().toEpochMilli();
	DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String formattedDateTime=localDateTime.format(formatter);
	return formattedDateTime;	
}
public static String addDaysToCurrentDateTime(String currentDateTime, int daysToAdd) {
    LocalDateTime localDateTime = LocalDateTime.parse(currentDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime dueDateTime = localDateTime.plusDays(daysToAdd);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = dueDateTime.format(formatter);

    return formattedDateTime;

}
}


		





