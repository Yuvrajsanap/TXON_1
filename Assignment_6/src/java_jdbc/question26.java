package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class question26 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Create a Statement object
			statement = connection.createStatement();

			// Insert a new record
			String sql = "INSERT INTO ass26jdbc (name, age) VALUES ('surykumar yadav', 32)";
			int rowsAffected = statement.executeUpdate(sql);

			// Check the result
			if (rowsAffected > 0) {
				System.out.println("Record inserted successfully!");
			} else {
				System.out.println("Failed to insert the record.");
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
