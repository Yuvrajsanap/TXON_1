package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class question41 {
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
			System.out.println("1. Create Record");
			System.out.println("2. Read Records");
			System.out.println("3. Update Record");
			System.out.println("4. Delete Record");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				createRecord(scanner);
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

	private static void createRecord(Scanner scanner) {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter age: ");
		int age = scanner.nextInt();

		String sql = "INSERT INTO ass41jdbc (name, age) VALUES (?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Record inserted successfully! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void readRecords() {
		String sql = "SELECT * FROM ass41jdbc";

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void updateRecord(Scanner scanner) {
		System.out.print("Enter ID of the record to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new age: ");
		int age = scanner.nextInt();

		String sql = "UPDATE ass41jdbc SET name = ?, age = ? WHERE id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setInt(3, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Record updated successfully! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void deleteRecord(Scanner scanner) {
		System.out.print("Enter ID of the record to delete: ");
		int id = scanner.nextInt();

		String sql = "DELETE FROM ass41jdbc WHERE id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Record deleted successfully! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
