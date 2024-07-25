package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class question10 {
	public static void main(String[] args) {
		// Database credentials
		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully!");

			// Create a statement object
			statement = connection.createStatement();

			// Execute a SELECT query to retrieve all records
			String selectQuery = "SELECT id, name, age FROM ass10jdbc";
			resultSet = statement.executeQuery(selectQuery);

			// Process the result set
			System.out.println("ID\tName\tAge");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println(id + "\t" + name + "\t" + age);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed successfully!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
