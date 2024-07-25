package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class question13 {
	public static void main(String[] args) {
		// Database credentials
		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		Savepoint savepoint = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			connection.setAutoCommit(false); // Disable auto-commit mode
			System.out.println("Connection established and auto-commit disabled!");

			// Create a statement object
			statement = connection.createStatement();

			// Create a savepoint
			savepoint = connection.setSavepoint("BeforeInsert");

			// Insert data into the table
			String insertSQL1 = "INSERT INTO ass13jdbc (name, age) VALUES ('yuvraj sanap', 20)";
			String insertSQL2 = "INSERT INTO ass13jdbc (name, age) VALUES ('om deshmukh', 32)";

			// Execute the first insert statement
			statement.executeUpdate(insertSQL1);

			// Execute the second insert statement
			statement.executeUpdate(insertSQL2);

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed successfully!");

		} catch (Exception e) {
			try {
				// Rollback to the savepoint or to the start of the transaction
				if (connection != null) {
					if (savepoint != null) {
						connection.rollback(savepoint);
						System.out.println("Rolled back to the savepoint!");
					} else {
						connection.rollback();
						System.out.println("Transaction rolled back!");
					}
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
