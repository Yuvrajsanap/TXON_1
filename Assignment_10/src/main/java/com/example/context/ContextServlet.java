package com.example.context;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contextExample")
public class ContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// Setting an attribute in the servlet context during initialization
		ServletContext context = getServletContext();
		context.setAttribute("appName", "MyWebApp");
		context.setAttribute("version", "1.0");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Retrieving attributes from the servlet context
		ServletContext context = getServletContext();
		String appName = (String) context.getAttribute("appName");
		String version = (String) context.getAttribute("version");

		out.println("<html><body>");
		out.println("<h1>Servlet Context Example</h1>");
		out.println("<p>Application Name: " + appName + "</p>");
		out.println("<p>Version: " + version + "</p>");
		out.println("</body></html>");
	}
}
