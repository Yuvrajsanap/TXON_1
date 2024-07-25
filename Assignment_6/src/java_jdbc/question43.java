package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class question43 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("Transaction Management Menu:");
			System.out.println("1. Perform Transactions");
			System.out.println("2. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				performTransactions();
				break;
			case 2:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 2);

		scanner.close();
	}

	private static void performTransactions() {
		String insertSQL1 = "INSERT INTO ass43jdbc (name, age) VALUES (?, ?)";
		String insertSQL2 = "INSERT INTO ass43jdbc (name, age) VALUES (?, ?)";
		String updateSQL = "UPDATE ass43jdbc SET age = ? WHERE name = ?";

		try (Connection connection = getConnection()) {
			// Disable auto-commit mode
			connection.setAutoCommit(false);

			try (PreparedStatement insertStmt1 = connection.prepareStatement(insertSQL1);
					PreparedStatement insertStmt2 = connection.prepareStatement(insertSQL2);
					PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {

				// Insert operations
				insertStmt1.setString(1, "John Doe");
				insertStmt1.setInt(2, 30);
				insertStmt1.executeUpdate();

				insertStmt2.setString(1, "Jane Smith");
				insertStmt2.setInt(2, 25);
				insertStmt2.executeUpdate();

				// Update operation
				updateStmt.setInt(1, 31); // New age
				updateStmt.setString(2, "John Doe");
				updateStmt.executeUpdate();

				// Commit transaction
				connection.commit();
				System.out.println("Transaction committed successfully!");

			} catch (SQLException e) {
				// Rollback transaction in case of an error
				try {
					connection.rollback();
					System.out.println("Transaction rolled back due to an error.");
				} catch (SQLException rollbackEx) {
					System.err.println("Failed to roll back transaction: " + rollbackEx.getMessage());
				}
				System.err.println("SQL Exception: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Connection Exception: " + e.getMessage());
		}
	}
}
