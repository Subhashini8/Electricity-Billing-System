package modules;

import java.sql.ResultSet;
import java.sql.SQLException;

import implementation.User;

public class UserProfile {
	public static void viewProfile() {
		User user=new User(0, null, null, null, null, null, null, 0, 0, 0);
		ResultSet viewProfileResult=user.viewProfile();
		try {
			while(viewProfileResult.next()){
				System.out.println("Name: "+viewProfileResult.getString("name"));
				System.out.println("User Name: "+viewProfileResult.getString("username"));
				System.out.println("Address: "+viewProfileResult.getString("address"));
				System.out.println("City: "+viewProfileResult.getString("city"));
				System.out.println("Pincode: "+viewProfileResult.getInt("pincode"));
				System.out.println("Mobile: "+viewProfileResult.getLong("mobile_number"));	
			}
			Options.dashboard();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
