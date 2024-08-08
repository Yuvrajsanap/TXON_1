package com.example.formvalidation;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/validateForm")
public class FormValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String errorMsg = null;
		if (name == null || name.isEmpty()) {
			errorMsg = "Name cannot be empty.";
		} else if (email == null || email.isEmpty()
				|| !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
			errorMsg = "Invalid email address.";
		} else if (password == null || password.isEmpty() || password.length() < 6) {
			errorMsg = "Password must be at least 6 characters long.";
		}

		if (errorMsg != null) {
			request.setAttribute("errorMessage", errorMsg);
			request.getRequestDispatcher("error17.jsp").forward(request, response);
		} else {
			request.setAttribute("name", name);
			request.getRequestDispatcher("success17.jsp").forward(request, response);
		}
	}
}
