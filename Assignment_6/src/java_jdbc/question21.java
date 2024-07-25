package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class question21 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Insert data using PreparedStatement to prevent SQL injection
			System.out.println("Enter name:");
			String name = scanner.nextLine();
			System.out.println("Enter age:");
			int age = scanner.nextInt();

			String insertSQL = "INSERT INTO ass21jdbc (name, age) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.executeUpdate();
			System.out.println("Record inserted successfully!");

			// Retrieve and display data
			String selectSQL = "SELECT * FROM ass21jdbc WHERE name = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();

			System.out.println("Retrieved records:");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String retrievedName = resultSet.getString("name");
				int retrievedAge = resultSet.getInt("age");
				System.out.printf("ID: %d, Name: %s, Age: %d%n", id, retrievedName, retrievedAge);
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
				if (resultSet != null) {
					resultSet.close();
				}
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
