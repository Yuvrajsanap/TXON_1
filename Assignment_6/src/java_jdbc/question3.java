package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class question3 {
	public static void main(String[] args) {
		// Database credentials
		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully using Type 4 driver!");

			// Create a statement object
			statement = connection.createStatement();

			// Create the table
			String createTableSQL = "CREATE TABLE IF NOT EXISTS ass3jdbc (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "name VARCHAR(100), " + "age INT)";
			statement.executeUpdate(createTableSQL);
			System.out.println("Table 'ass3jdbc' created successfully!");

			// Insert data into the table
			String insertDataSQL = "INSERT INTO ass3jdbc (name, age) VALUES " + "('yuvraj', 20), " + "('aniket', 25), "
					+ "('om', 24)";
			statement.executeUpdate(insertDataSQL);
			System.out.println("Data inserted into 'ass3jdbc' successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
