package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Question130")
public class Question13 extends HttpServlet {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment10_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Yuvraj@12345";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>Database Operations</h1>");

		// Display HTML form for creating a new user
		out.println("<h2>Create User</h2>");
		out.println("<form method='post' action='Question13?action=create'>");
		out.println("Name: <input type='text' name='name'><br>");
		out.println("Email: <input type='text' name='email'><br>");
		out.println("<input type='submit' value='Create User'>");
		out.println("</form>");

		// Display HTML form for updating a user
		out.println("<h2>Update User</h2>");
		out.println("<form method='post' action='Question13?action=update'>");
		out.println("User ID: <input type='text' name='id'><br>");
		out.println("New Name: <input type='text' name='name'><br>");
		out.println("New Email: <input type='text' name='email'><br>");
		out.println("<input type='submit' value='Update User'>");
		out.println("</form>");

		// Display HTML form for deleting a user
		out.println("<h2>Delete User</h2>");
		out.println("<form method='post' action='Question13?action=delete'>");
		out.println("User ID: <input type='text' name='id'><br>");
		out.println("<input type='submit' value='Delete User'>");
		out.println("</form>");

		// Display the list of users
		out.println("<h2>Read Users</h2>");
		out.println("<form method='get' action='Question13'>");
		out.println("<input type='hidden' name='action' value='read'>");
		out.println("<input type='submit' value='Read Users'>");
		out.println("</form>");

		String action = request.getParameter("action");

		if ("read".equalsIgnoreCase(action)) {
			try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				readUsers(conn, out);
			} catch (SQLException e) {
				e.printStackTrace(out);
			}
		}

		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			if ("create".equalsIgnoreCase(action)) {
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				createUser(conn, name, email);
				out.println("User created.");
			} else if ("update".equalsIgnoreCase(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				updateUser(conn, id, name, email);
				out.println("User updated.");
			} else if ("delete".equalsIgnoreCase(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				deleteUser(conn, id);
				out.println("User deleted.");
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void createUser(Connection conn, String name, String email) throws SQLException {
		String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.executeUpdate();
		}
	}

	private void readUsers(Connection conn, PrintWriter out) throws SQLException {
		String sql = "SELECT * FROM users";
		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th></tr>");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>"
						+ rs.getString("email") + "</td></tr>");
			}
			out.println("</table>");
		}
	}

	private void updateUser(Connection conn, int id, String name, String email) throws SQLException {
		String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setInt(3, id);
			stmt.executeUpdate();
		}
	}

	private void deleteUser(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM users WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}