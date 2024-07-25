package java_jdbc;
//crud operation

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class question25 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			while (true) {
				System.out.println("\nCRUD Operations:");
				System.out.println("1. Create Record");
				System.out.println("2. Read Records");
				System.out.println("3. Update Record");
				System.out.println("4. Delete Record");
				System.out.println("5. Exit");
				System.out.print("Choose an option: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					createRecord(connection, scanner);
					break;
				case 2:
					readRecords(connection);
					break;
				case 3:
					updateRecord(connection, scanner);
					break;
				case 4:
					deleteRecord(connection, scanner);
					break;
				case 5:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
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
				if (connection != null) {
					connection.close();
				}
				scanner.close(); // Close the scanner
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static void createRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter age: ");
		int age = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "INSERT INTO ass25jdbc (name, age) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record created successfully!");
			} else {
				System.out.println("Failed to create record.");
			}
		}
	}

	private static void readRecords(Connection connection) throws SQLException {
		String sql = "SELECT * FROM ass25jdbc";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

			System.out.println("Records in the database:");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
			}
		}
	}

	private static void updateRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter ID of the record to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new name: ");
		String newName = scanner.nextLine();
		System.out.print("Enter new age: ");
		int newAge = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "UPDATE ass25jdbc SET name = ?, age = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, newAge);
			preparedStatement.setInt(3, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record updated successfully!");
			} else {
				System.out.println("No record found with the specified ID.");
			}
		}
	}

	private static void deleteRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter ID of the record to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "DELETE FROM ass25jdbc WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record deleted successfully!");
			} else {
				System.out.println("No record found with the specified ID.");
			}
		}
	}
}
