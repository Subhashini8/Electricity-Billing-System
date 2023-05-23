package application;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;


public class DatabaseConnection {
private static Connection connectivity=null;

public static Connection establishConnection() {
	Map<Object, Object> databaseCredentials = new HashMap<Object, Object>();
	databaseCredentials = databaseCredentialsReader();
	if(connectivity!=null) {
		return connectivity;
	}
	else {
		try {
			final String url = (String) databaseCredentials.get("url");
			final String user = (String) databaseCredentials.get("user");
			final String password = (String) databaseCredentials.get("password");
			connectivity = DriverManager.getConnection(url, user, password);
			System.out.println("Database connection established successfully.");
			return connectivity;
		}
		catch (SQLException exception){
			System.out.println("Failed to establish database connection.");
			exception.printStackTrace();
			return null;
		}
	}
}
public static Connection closeConnection() {
	if(connectivity==null) {
		return connectivity;
	}
	else
		try {
			connectivity.close();
			System.out.println("Database connection closed successfully");
			return null;
		}
	catch(SQLException exception) {
		System.out.println("Failed to close database connection");
		exception.printStackTrace();
		return connectivity;
	}
	
	
	
}

	public static Map<Object, Object> databaseCredentialsReader() {
		String fileName = "C:\\Users\\subhashini.surya\\Documents\\credentials.txt";
		Map<Object, Object> databaseCredentials = new HashMap<Object, Object>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    databaseCredentials.put(key, value);
                }
            }
            return databaseCredentials;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return databaseCredentials;
	}
}



