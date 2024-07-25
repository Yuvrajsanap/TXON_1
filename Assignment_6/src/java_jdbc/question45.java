package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class question45 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";
	private static final int POOL_SIZE = 5;

	private static final Queue<Connection> connectionPool = new LinkedList<>();

	static {
		try {
			for (int i = 0; i < POOL_SIZE; i++) {
				connectionPool.add(DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() {
		Connection connection = null;
		synchronized (connectionPool) {
			if (!connectionPool.isEmpty()) {
				connection = connectionPool.poll();
			}
		}
		return connection;
	}

	private static void releaseConnection(Connection connection) {
		synchronized (connectionPool) {
			connectionPool.offer(connection);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("CRUD Operations Menu:");
			System.out.println("1. Insert Record");
			System.out.println("2. Read Records");
			System.out.println("3. Update Record");
			System.out.println("4. Delete Record");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				insertRecord(scanner);
				break;
			case 2:
				readRecords();
				break;
			case 3:
				updateRecord(scanner);
				break;
			case 4:
				deleteRecord(scanner);
				break;
			case 5:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 5);

		scanner.close();
	}

	private static void insertRecord(Scanner scanner) {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter age: ");
		int age = scanner.nextInt();

		String insertSQL = "INSERT INTO ass45jdbc (name, age) VALUES (?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Record inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void readRecords() {
		String selectSQL = "SELECT * FROM ass45jdbc";

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectSQL)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateRecord(Scanner scanner) {
		System.out.print("Enter record ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new age: ");
		int age = scanner.nextInt();

		String updateSQL = "UPDATE ass45jdbc SET name = ?, age = ? WHERE id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setInt(3, id);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Record updated successfully!");
			} else {
				System.out.println("Record not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteRecord(Scanner scanner) {
		System.out.print("Enter record ID to delete: ");
		int id = scanner.nextInt();

		String deleteSQL = "DELETE FROM ass45jdbc WHERE id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

			preparedStatement.setInt(1, id);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Record deleted successfully!");
			} else {
				System.out.println("Record not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
