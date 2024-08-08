package com.example.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/getSession")
public class GetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		out.println("<html><body>");
		out.println("<h1>Session Attributes</h1>");
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String email = (String) session.getAttribute("email");

			if (username != null && email != null) {
				out.println("<p>Username: " + username + "</p>");
				out.println("<p>Email: " + email + "</p>");
			} else {
				out.println("<p>No session attributes found.</p>");
			}
		} else {
			out.println("<p>No active session found.</p>");
		}
		out.println("</body></html>");
	}
}
