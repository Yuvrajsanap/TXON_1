package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question28 {
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

			// Prepare the SQL statement
			String sql = "INSERT INTO ass28jdbc (name, age) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(sql);

			// Add multiple records to the batch
			preparedStatement.setString(1, "Yuvraj");
			preparedStatement.setInt(2, 28);
			preparedStatement.addBatch();

			preparedStatement.setString(1, "aniket");
			preparedStatement.setInt(2, 34);
			preparedStatement.addBatch();

			preparedStatement.setString(1, "shubham");
			preparedStatement.setInt(2, 22);
			preparedStatement.addBatch();

			// Execute the batch
			int[] batchResults = preparedStatement.executeBatch();

			// Check the results
			for (int result : batchResults) {
				if (result == PreparedStatement.EXECUTE_FAILED) {
					System.err.println("A record failed to insert.");
				} else {
					System.out.println("Record inserted successfully!");
				}
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
