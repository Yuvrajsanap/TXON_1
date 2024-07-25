package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class question14 {
	public static void main(String[] args) {
		// Database credentials
		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			connection.setAutoCommit(false); // Disable auto-commit mode
			System.out.println("Connection established and auto-commit disabled!");

			// Create a statement object
			statement = connection.createStatement();

			// Perform multiple updates within a transaction
			String updateSQL1 = "UPDATE ass14jdbc SET age = age + 2 WHERE name = 'yuvraj'";
			String updateSQL2 = "UPDATE ass14jdbc SET age = age - 1 WHERE name = 'ys'";

			// Execute the updates
			int rowsAffected1 = statement.executeUpdate(updateSQL1);
			int rowsAffected2 = statement.executeUpdate(updateSQL2);

			System.out.println("Rows affected by first update: " + rowsAffected1);
			System.out.println("Rows affected by second update: " + rowsAffected2);

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed successfully!");

		} catch (Exception e) {
			try {
				// Rollback the transaction in case of an error
				if (connection != null) {
					connection.rollback();
					System.out.println("Transaction rolled back!");
				}
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed successfully!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
