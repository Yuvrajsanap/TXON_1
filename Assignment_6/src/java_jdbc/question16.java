package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class question16 {
	private static final int INITIAL_POOL_SIZE = 5;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	private Queue<Connection> connectionPool;

	public question16() {
		connectionPool = new LinkedList<>();
		try {
			for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
				connectionPool.add(createNewConnection());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public synchronized Connection getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
			return createNewConnection();
		}
		return connectionPool.poll();
	}

	public synchronized void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connectionPool.offer(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		question16 pool = new question16();
		Connection connection = null;

		try {
			// Obtain a connection from the pool
			connection = pool.getConnection();
			System.out.println("Connection obtained from the pool!");

			// Use the connection (example: execute a query)
			// Example of using the connection:
			// Statement statement = connection.createStatement();
			// ResultSet resultSet = statement.executeQuery("SELECT * FROM ass16jdbc");
			// while (resultSet.next()) {
			// System.out.println(resultSet.getString("name") + " - " +
			// resultSet.getInt("age"));
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Release the connection back to the pool
			pool.releaseConnection(connection);
			System.out.println("Connection returned to the pool!");
		}
	}
}
