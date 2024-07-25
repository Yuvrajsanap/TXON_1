package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class question15 {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			connection.setAutoCommit(false);
			System.out.println("Connection established and auto-commit disabled!");

			String sql = "INSERT INTO ass15jdbc (name, age) VALUES (?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			// Add multiple statements to the batch
			preparedStatement.setString(1, "sanap");
			preparedStatement.setInt(2, 25);
			preparedStatement.addBatch();

			preparedStatement.setString(1, "aniket");
			preparedStatement.setInt(2, 30);
			preparedStatement.addBatch();

			preparedStatement.setString(1, "mayank");
			preparedStatement.setInt(2, 35);
			preparedStatement.addBatch();

			// Execute the batch
			int[] updateCounts = preparedStatement.executeBatch();
			System.out.println("Batch update executed!");

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed successfully!");

		} catch (Exception e) {
			try {
				// Rollback in case of an error
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
