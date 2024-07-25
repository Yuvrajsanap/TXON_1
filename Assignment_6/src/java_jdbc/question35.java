package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question35 {
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

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Define the SQL update statement
			String sqlUpdate = "UPDATE ass35jdbc SET age = ? WHERE id = ?";

			// Create the PreparedStatement object
			preparedStatement = connection.prepareStatement(sqlUpdate);

			// Add multiple updates to the batch
			preparedStatement.setInt(1, 25);
			preparedStatement.setInt(2, 1);
			preparedStatement.addBatch();

			preparedStatement.setInt(1, 30);
			preparedStatement.setInt(2, 2);
			preparedStatement.addBatch();

			preparedStatement.setInt(1, 35);
			preparedStatement.setInt(2, 3);
			preparedStatement.addBatch();

			// Execute the batch update
			int[] updateCounts = preparedStatement.executeBatch();
			System.out.println("Batch update completed!");

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed!");

			// Print the number of rows affected for each batch
			for (int count : updateCounts) {
				System.out.println("Rows affected: " + count);
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();

			try {
				if (connection != null) {
					System.out.println("Rolling back the transaction...");
					connection.rollback();
				}
			} catch (SQLException rollbackException) {
				System.err.println("Rollback Exception: " + rollbackException.getMessage());
				rollbackException.printStackTrace();
			}

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
