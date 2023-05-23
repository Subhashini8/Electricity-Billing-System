package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;
import modules.Options;

public class User{
	int userId;
	String role;
	String name;
	String username;
	String password;
	String address;
	String city;
	int pincode;
	long mobileNumber;
	long wallet;
	
	public User(int userId, String role, String name, String username, String password, String address, String city,
			int pincode, long mobileNumber, long wallet) {
		super();
		this.userId = userId;
		this.role = role;
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}
	
	
	
	public boolean validateLogin() {
		Connection database = DatabaseConnection.establishConnection();
		try {
			PreparedStatement query = database.prepareStatement("SELECT * FROM USER_CREDENTIALS WHERE USERNAME = ? AND PASSWORD = ?");
			query.setString(1, username);
			query.setString(2, password);
			ResultSet result = query.executeQuery();
			if(result.next()) {
				userId = result.getInt("user_id");
				role = result.getString("role");
				Global.userId = userId;
				Global.username = username;
				Global.role = role;
				System.out.println("Login successful.");
				return true;
			}
			else {
				System.out.println("Login failed.");
				Options.homepage();
				return false;
			}
		}
			catch(SQLException exception) {
				exception.printStackTrace();
				return false;

			}
		}
		public boolean registerUser() {
			Connection database = DatabaseConnection.establishConnection();
			try {
				PreparedStatement query = database.prepareStatement("INSERT INTO USER_CREDENTIALS (NAME,USERNAME,PASSWORD,ADDRESS,CITY,PINCODE,MOBILE_NUMBER) VALUES(?,?,?,?,?,?,?)");
				query.setString(1, name);
				query.setString(2, username);
				query.setString(3, password);
				query.setString(4, address);
				query.setString(5, city);
				query.setInt(6, pincode);
				query.setLong(7, mobileNumber);
				int result = query.executeUpdate();
				if(result==1) {
					System.out.println("Registered successfully");
					return true;
				}
				else {
					System.out.println("Registration failed!");
					return false;
				}
			}
			catch(SQLException exception) {
				exception.printStackTrace();
				return false;

			}
		
		}
		public ResultSet viewProfile() {
			Connection database=DatabaseConnection.establishConnection();
			ResultSet viewProfileResult=null;
			
			try {
				PreparedStatement query=database.prepareStatement("SELECT * FROM USER_CREDENTIALS WHERE USER_ID=?");
				int userId=Global.userId;
				query.setInt(1, userId);
			    viewProfileResult=query.executeQuery();
			    if(viewProfileResult.isBeforeFirst()) {
			    	System.out.println("User details");
			    	return viewProfileResult;
			    }
			    else {
			    	System.out.println("Profile not found");
			    	return null;
			    }
			    
				
			}
			catch(SQLException exception) {
				exception.printStackTrace();
				return viewProfileResult;
			}
		}
		
	}

	



