package java_jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class question32 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		Connection connection = null;
		ResultSet tables = null;
		ResultSet columns = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection established!");

			// Get DatabaseMetaData object
			DatabaseMetaData metaData = connection.getMetaData();

			// Get table names
			String catalog = connection.getCatalog();
			String schemaPattern = null;
			String tableNamePattern = "ass32jdbc";
			String[] types = { "TABLE" };
			tables = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);

			System.out.println("Table Names:");
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				System.out.println("Table: " + tableName);

				// Get column names and data types for each table
				columns = metaData.getColumns(catalog, schemaPattern, tableName, null);
				System.out.println("Columns:");
				while (columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");
					int columnSize = columns.getInt("COLUMN_SIZE");
					System.out.printf("Column: %s, Type: %s, Size: %d%n", columnName, columnType, columnSize);
				}
				System.out.println();
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
				if (columns != null) {
					columns.close();
				}
				if (tables != null) {
					tables.close();
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
