package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question11 {
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

			// Create a statement object with ResultSet.TYPE_SCROLL_INSENSITIVE
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Execute a SELECT query
			String selectQuery = "SELECT id, name, age FROM ass11jdbc";
			resultSet = statement.executeQuery(selectQuery);

			// Navigate through the ResultSet
			System.out.println("Navigating through ResultSet:");

			// Move to the first row
			if (resultSet.first()) {
				System.out.println("First Row: ID = " + resultSet.getInt("id") + ", Name = "
						+ resultSet.getString("name") + ", Age = " + resultSet.getInt("age"));
			}

			// Move to the last row
			if (resultSet.last()) {
				System.out.println("Last Row: ID = " + resultSet.getInt("id") + ", Name = "
						+ resultSet.getString("name") + ", Age = " + resultSet.getInt("age"));
			}

			// Move to a specific row (e.g., row 2)
			if (resultSet.absolute(2)) {
				System.out.println("Row 2: ID = " + resultSet.getInt("id") + ", Name = " + resultSet.getString("name")
						+ ", Age = " + resultSet.getInt("age"));
			}

			// Move one row back from the current position
			if (resultSet.previous()) {
				System.out.println("Previous Row: ID = " + resultSet.getInt("id") + ", Name = "
						+ resultSet.getString("name") + ", Age = " + resultSet.getInt("age"));
			}

			// Move to a specific row from the end (e.g., 1 row from the end)
			if (resultSet.relative(-1)) {
				System.out.println("1 Row from the End: ID = " + resultSet.getInt("id") + ", Name = "
						+ resultSet.getString("name") + ", Age = " + resultSet.getInt("age"));
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
