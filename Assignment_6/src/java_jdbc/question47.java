package java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class question47 {

	private static final String URL = "jdbc:mysql://localhost:3306/Assignment6db";
	private static final String USER = "root";
	private static final String PASSWORD = "Yuvraj@12345";

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			// Insert operations
			insertCourse(connection, 44, "ravi");
			insertCourse(connection, 55, "Physics");
			insertStudent(connection, 66, "roshan deshmukh", 1);
			insertStudent(connection, 77, "roshan deshmukh", 2);

			// Update operations
			updateStudentName(connection, 12, "rohit patil");
			updateStudentCourse(connection, 2, 1);

			// Delete operations
			deleteStudent(connection, 2);
			deleteCourse(connection, 2);

			// Read operations
			readStudents(connection);
			readCourses(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertCourse(Connection connection, int courseId, String courseName) throws SQLException {
		String query = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setString(2, courseName);
			preparedStatement.executeUpdate();
		}
	}

	private static void insertStudent(Connection connection, int studentId, String studentName, int courseId)
			throws SQLException {
		String query = "INSERT INTO students (student_id, student_name, course_id) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setString(2, studentName);
			preparedStatement.setInt(3, courseId);
			preparedStatement.executeUpdate();
		}
	}

	private static void updateStudentName(Connection connection, int studentId, String newName) throws SQLException {
		String query = "UPDATE students SET student_name = ? WHERE student_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, studentId);
			preparedStatement.executeUpdate();
		}
	}

	private static void updateStudentCourse(Connection connection, int studentId, int newCourseId) throws SQLException {
		String query = "UPDATE students SET course_id = ? WHERE student_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, newCourseId);
			preparedStatement.setInt(2, studentId);
			preparedStatement.executeUpdate();
		}
	}

	private static void deleteStudent(Connection connection, int studentId) throws SQLException {
		String query = "DELETE FROM students WHERE student_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
		}
	}

	private static void deleteCourse(Connection connection, int courseId) throws SQLException {
		String query = "DELETE FROM courses WHERE course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.executeUpdate();
		}
	}

	private static void readStudents(Connection connection) throws SQLException {
		String query = "SELECT * FROM students";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				int studentId = resultSet.getInt("student_id");
				String studentName = resultSet.getString("student_name");
				int courseId = resultSet.getInt("course_id");
				System.out.println("Student ID: " + studentId + ", Name: " + studentName + ", Course ID: " + courseId);
			}
		}
	}

	private static void readCourses(Connection connection) throws SQLException {
		String query = "SELECT * FROM courses";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				int courseId = resultSet.getInt("course_id");
				String courseName = resultSet.getString("course_name");
				System.out.println("Course ID: " + courseId + ", Name: " + courseName);
			}
		}
	}
}
