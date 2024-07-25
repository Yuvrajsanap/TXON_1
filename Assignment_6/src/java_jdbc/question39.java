package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question39 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Define the SQL DELETE statement with a placeholder for the age condition
			String sqlDelete = "DELETE FROM ass39jdbc WHERE age < ?";

			// Create the PreparedStatement object
			preparedStatement = connection.prepareStatement(sqlDelete);

			// Set the age parameter
			preparedStatement.setInt(1, 24); // Delete records where age is less than 20

			// Execute the DELETE statement
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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
