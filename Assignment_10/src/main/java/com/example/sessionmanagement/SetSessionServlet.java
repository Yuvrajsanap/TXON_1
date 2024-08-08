package com.example.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/setSession")
public class SetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		session.setAttribute("username", "shubhamchavan");
		session.setAttribute("email", "shubham@example.com");

		out.println("<html><body>");
		out.println("<h1>Session Attributes Set</h1>");
		out.println("<p>Username: yuvrajsanap</p>");
		out.println("<p>Email: yuvraj@example.com</p>");
		out.println("</body></html>");
	}
}
