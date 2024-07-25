package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class question5 {
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

			// Create a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully!");

			// Create a statement object
			statement = connection.createStatement();

			// Create the table
			String createTableSQL = "CREATE TABLE IF NOT EXISTS ass5jdbc (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "name VARCHAR(100), " + "age INT)";
			statement.executeUpdate(createTableSQL);
			System.out.println("Table 'ass5jdbc' created successfully!");

			// Insert data into the table
			String insertDataSQL = "INSERT INTO ass5jdbc (name, age) VALUES " + "('yuvraj', 21), " + "('yash', 25), "
					+ "('shubham', 24)," + "('aniket',22)";
			statement.executeUpdate(insertDataSQL);
			System.out.println("Data inserted into 'ass5jdbc' successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the statement and connection objects
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed successfully!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
