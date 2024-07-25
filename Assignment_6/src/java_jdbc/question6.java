package java_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;

public class question6 {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/Assignment6db";
		String username = "root";
		String password = "Yuvraj@12345";

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		CallableStatement callableStatement = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection established successfully!");

			// Use Statement to create a table
			statement = connection.createStatement();
			String createTableSQL = "CREATE TABLE IF NOT EXISTS ass6jdbc (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "name VARCHAR(100), " + "age INT)";
			statement.executeUpdate(createTableSQL);
			System.out.println("Table 'ass6jdbc' created successfully!");

			// Use PreparedStatement to insert data
			String insertDataSQL = "INSERT INTO ass6jdbc (name, age) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(insertDataSQL);
			preparedStatement.setString(1, "yuvraj");
			preparedStatement.setInt(2, 20);
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "harsh");
			preparedStatement.setInt(2, 25);
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "ravi");
			preparedStatement.setInt(2, 35);
			preparedStatement.executeUpdate();
			System.out.println("Data inserted into 'ass6jdbc' successfully!");

			// Use CallableStatement to call a stored procedure
			String createProcedureSQL = "CREATE PROCEDURE getPersonById(IN personId INT, OUT personName VARCHAR(100), OUT personAge INT) "
					+ "BEGIN " + "SELECT name, age INTO personName, personAge FROM ass6jdbc WHERE id = personId; "
					+ "END";
			statement.executeUpdate(createProcedureSQL);
			System.out.println("Stored procedure 'getPersonById' created successfully!");

			callableStatement = connection.prepareCall("{CALL getPersonById(?, ?, ?)}");
			callableStatement.setInt(1, 1);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.execute();

			String name = callableStatement.getString(2);
			int age = callableStatement.getInt(3);
			System.out.println("Person Details - Name: " + name + ", Age: " + age);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (statement != null) {
					statement.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
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
