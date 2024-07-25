package java_jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class question44 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("BLOB CRUD Operations Menu:");
			System.out.println("1. Insert Image");
			System.out.println("2. Read Image");
			System.out.println("3. Update Image");
			System.out.println("4. Delete Record");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				insertImage();
				break;
			case 2:
				readImage();
				break;
			case 3:
				updateImage();
				break;
			case 4:
				deleteRecord();
				break;
			case 5:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 5);

		scanner.close();
	}

	private static void insertImage() {
		String insertSQL = "INSERT INTO ass44jdbc (name, image) VALUES (?, ?)";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
				FileInputStream fis = new FileInputStream(
						"\"C:\\Users\\yuvra\\Pictures\\Saved Pictures\\wallpaperflare.com_wallpaper (7).jpg")) {

			preparedStatement.setString(1, "Example Image");
			preparedStatement.setBlob(2, fis);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Image inserted successfully!");
			}
		} catch (SQLException | IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static void readImage() {
		String selectSQL = "SELECT name, image FROM ass44jdbc WHERE id = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter record ID to read: ");
			int id = scanner.nextInt();
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				Blob blob = resultSet.getBlob("image");

				InputStream inputStream = blob.getBinaryStream();
				FileOutputStream outputStream = new FileOutputStream("output.jpg");

				int bytesRead = -1;
				byte[] buffer = new byte[4096];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				System.out.println("Image retrieved and saved as output.jpg");

				inputStream.close();
				outputStream.close();
			} else {
				System.out.println("Record not found.");
			}
		} catch (SQLException | IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static void updateImage() {
		String updateSQL = "UPDATE ass44jdbc SET image = ? WHERE id = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
				FileInputStream fis = new FileInputStream(
						"\"C:\\Users\\yuvra\\Pictures\\Saved Pictures\\wallpaperflare.com_wallpaper (7).jpg\"")) {

			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter record ID to update: ");
			int id = scanner.nextInt();
			preparedStatement.setBlob(1, fis);
			preparedStatement.setInt(2, id);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Image updated successfully!");
			} else {
				System.out.println("Record not found.");
			}
		} catch (SQLException | IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static void deleteRecord() {
		String deleteSQL = "DELETE FROM ass44jdbc WHERE id = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter record ID to delete: ");
			int id = scanner.nextInt();
			preparedStatement.setInt(1, id);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				System.out.println("Record deleted successfully!");
			} else {
				System.out.println("Record not found.");
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
