package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class question34 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Get user input for the update
			System.out.print("Enter the ID of the record to update: ");
			int id = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			System.out.print("Enter the new age: ");
			int age = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			// Define the SQL update statement
			String sqlUpdate = "UPDATE ass34jdbc SET age = ? WHERE id = ?";

			// Create the PreparedStatement object
			preparedStatement = connection.prepareStatement(sqlUpdate);

			// Set the parameters
			preparedStatement.setInt(1, age);
			preparedStatement.setInt(2, id);

			// Execute the update statement
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
				scanner.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
