package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class question8 {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Scanner scanner = new Scanner(System.in);

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully!");

			// Get user input
			System.out.print("Enter the ID to fetch details: ");
			int idInput = scanner.nextInt();

			// Create a PreparedStatement to fetch data based on user input
			String selectQuery = "SELECT id, name, age FROM ass6jdbc WHERE id = ?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, idInput);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

			// Process the result set
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			} else {
				System.out.println("No record found with ID: " + idInput);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed successfully!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			scanner.close();
		}
	}
}
