package java_jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question24 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		ResultSet resultSet = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Get the database metadata
			DatabaseMetaData metaData = connection.getMetaData();

			// Fetch and display table names
			System.out.println("Tables in the database:");
			resultSet = metaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				System.out.println("Table: " + tableName);

				// Fetch and display column names and data types for each table
				displayColumnMetadata(metaData, tableName);
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (resultSet != null) {
					resultSet.close();
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

	private static void displayColumnMetadata(DatabaseMetaData metaData, String tableName) throws SQLException {
		ResultSet columns = metaData.getColumns(null, null, tableName, null);
		System.out.println("Columns for table " + tableName + ":");
		while (columns.next()) {
			String columnName = columns.getString("COLUMN_NAME");
			String columnType = columns.getString("TYPE_NAME");
			int columnSize = columns.getInt("COLUMN_SIZE");
			System.out.printf("Column: %s, Type: %s, Size: %d%n", columnName, columnType, columnSize);
		}
	}
}
