package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class question1 {
	public static void main(String[] args) {
		// Database credentials
		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a statement object
			statement = connection.createStatement();

			// Create the table
			String createTableSQL = "CREATE TABLE IF NOT EXISTS assjdbc1 (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "name VARCHAR(100), " + "age INT)";
			statement.executeUpdate(createTableSQL);

			// Insert data into the table
			String insertDataSQL = "INSERT INTO assjdbc1 (name, age) VALUES " + "('yash', 30), " + "('aniket', 25), "
					+ "('om', 35)";
			statement.executeUpdate(insertDataSQL);

			// Retrieve data from the table
			String selectSQL = "SELECT * FROM assjdbc1";
			ResultSet resultSet = statement.executeQuery(selectSQL);

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
