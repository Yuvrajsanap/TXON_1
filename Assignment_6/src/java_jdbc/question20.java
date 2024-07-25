package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question20 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Prepare the SQL statement
			String sql = "SELECT * FROM ass20jdbc";
			preparedStatement = connection.prepareStatement(sql);

			// Execute the query
			resultSet = preparedStatement.executeQuery();
			System.out.println("Query executed successfully!");

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} finally {

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
