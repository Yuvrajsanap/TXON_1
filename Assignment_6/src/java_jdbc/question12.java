package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question12 {
	public static void main(String[] args) {

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

			// Create a statement object with ResultSet.TYPE_SCROLL_INSENSITIVE and
			// ResultSet.CONCUR_UPDATABLE
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// Execute a SELECT query to retrieve records
			String selectQuery = "SELECT id, name, age FROM ass12jdbc";
			resultSet = statement.executeQuery(selectQuery);

			// Display records before update
			System.out.println("Records before update:");
			while (resultSet.next()) {
				System.out.println("ID = " + resultSet.getInt("id") + ", Name = " + resultSet.getString("name")
						+ ", Age = " + resultSet.getInt("age"));
			}

			// Update a record
			resultSet.beforeFirst();
			while (resultSet.next()) {
				if (resultSet.getInt("id") == 1) {
					resultSet.updateString("name", "paravin");
					resultSet.updateInt("age", 40);
					resultSet.updateRow();
					System.out.println("Record with ID 1 updated.");
				}
			}

			// Display records after update
			resultSet.beforeFirst();
			System.out.println("Records after update:");
			while (resultSet.next()) {
				System.out.println("ID = " + resultSet.getInt("id") + ", Name = " + resultSet.getString("name")
						+ ", Age = " + resultSet.getInt("age"));
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
