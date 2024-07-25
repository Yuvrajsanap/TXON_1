package java_jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class question23 {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db"; 
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "Yuvraj@12345"; 

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Load the JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connection established!");

            // Prepare the SQL statement for deletion
            String sql = "DELETE FROM ass23jdbc WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Get user input
            System.out.println("Enter the name of the record to delete:");
            String name = scanner.nextLine();

            // Set the parameter
            preparedStatement.setString(1, name);

            // Execute the delete operation
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record(s) deleted successfully!");
            } else {
                System.out.println("No records found with the specified name.");
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                scanner.close(); // Close the scanner
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
