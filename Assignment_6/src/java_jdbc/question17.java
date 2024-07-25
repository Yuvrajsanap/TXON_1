package java_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class question17 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db"; // Change URL to your database
	private static final String USERNAME = "root"; // Change to your database username
	private static final String PASSWORD = "Yuvraj@12345"; // Change to your database password

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement callableStatement = null;

		try {
			// Load the JDBC driver (optional for modern JDBC drivers)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Prepare the callable statement for the stored procedure
			String sql = "{call InsertPerson(?, ?)}";
			callableStatement = connection.prepareCall(sql);

			// Set input parameters for the stored procedure
			callableStatement.setString(1, "yuvraj sanap");
			callableStatement.setInt(2, 30);

			// Execute the stored procedure
			callableStatement.execute();
			System.out.println("Stored procedure executed successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
