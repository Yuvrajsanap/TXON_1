package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;

public class question19 {
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
			String sql = "INSERT INTO ass19jdbc (name, age) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(sql);

			// Set parameters (assuming valid data)
			preparedStatement.setString(1, "yuvraj sanap");
			preparedStatement.setInt(2, 30);

			// Execute the insert
			preparedStatement.executeUpdate();
			System.out.println("Record inserted successfully!");

		} catch (SQLSyntaxErrorException e) {
			System.err.println("SQL Syntax Error: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Integrity Constraint Violation: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLDataException e) {
			System.err.println("Data Error: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLTimeoutException e) {
			System.err.println("SQL Timeout: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLNonTransientConnectionException e) {
			System.err.println("Connection Error: " + e.getMessage());
			e.printStackTrace();
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
