package implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;

public class Connect{
	int userId;
	String connection_name;
	long connection_no;
	String connection_type;
	String connection_address;
	
	public Connect(int userId, String connection_name, long connection_no, String connection_type,
			String connection_address) {
		super();
		this.userId = userId;
		this.connection_name = connection_name;
		this.connection_no = connection_no;
		this.connection_type = connection_type;
		this.connection_address = connection_address;
	}
	
	public boolean addConnection() {
		Connection database = DatabaseConnection.establishConnection();
		try {
			PreparedStatement query = database.prepareStatement("INSERT INTO CONNECTIONS (USER_ID, CONNECTION_NAME,CONNECTION_NO, CONNECTION_TYPE, CONNECTION_ADDRESS) VALUES (?,?,?,?,?)");
			this.userId = Global.userId;
			query.setInt(1, userId);
			query.setString(2, connection_name);
			query.setLong(3, connection_no);
			query.setString(4, connection_type);
			query.setString(5, connection_address);
			int addResult = query.executeUpdate();
			if(addResult == 1) {
				System.out.println("Connection added to DB.");
				return true;
			}
			else {
				System.out.println("Failed to add connection to DB");
				return false;
			}
		}
		catch(SQLException exception){
			exception.printStackTrace();
			return false;
		}
	}
public ResultSet viewConnection() {
	Connection database = DatabaseConnection.establishConnection();
	ResultSet viewResult=null;
	try {
		PreparedStatement query = database.prepareStatement("SELECT * FROM CONNECTIONS WHERE USER_ID=?");
		this.userId = Global.userId;
		query.setInt(1, userId);
		viewResult = query.executeQuery();
		if(viewResult.isBeforeFirst()) {
			System.out.println("Connection details");
			return viewResult;
		}
		else {
			System.out.println("Try again");
			return viewResult;
		}
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return viewResult;
	}
}

public boolean removeConnection() {
	Connection database = DatabaseConnection.establishConnection();
	try {
		PreparedStatement query = database.prepareStatement("DELETE FROM CONNECTIONS WHERE CONNECTION_NO=?");
		query.setLong(1, connection_no);
		int removeResult = query.executeUpdate();
		if(removeResult==1) {
			System.out.println("Connection Removed");
			return true;
		}
		else {
			System.out.println("Try again");
			return false;
		}
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return false;
	}
}

public boolean validateConnection() {
	Connection database=DatabaseConnection.establishConnection();
	ResultSet connectionExistence = null;
	try {
		PreparedStatement query=database.prepareStatement("SELECT * FROM CONNECTIONS WHERE CONNECTION_NO=?");
		query.setLong(1, connection_no);
		connectionExistence = query.executeQuery();
		if(connectionExistence.next()) {
			long fetchedConnectionNumber= connectionExistence.getLong("CONNECTION_NO");
			if(fetchedConnectionNumber==connection_no) {
				System.out.println("Connection Exists");
				return true;
			}
			else {
				System.out.println("Connection does not exists");
				return false;
			}
		}
		else {
			System.out.println("Connection does not exists");
			return false;
		}
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return false;
	}
	
	
}
}


