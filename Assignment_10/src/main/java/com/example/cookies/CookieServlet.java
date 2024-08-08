package com.example.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookieManagement")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Set a cookie
		Cookie cookie = new Cookie("user", "KaranAujla");
		cookie.setMaxAge(60 * 60); // Cookie will expire in 1 hour
		response.addCookie(cookie);

		// Retrieve cookies
		Cookie[] cookies = request.getCookies();
		String userCookieValue = "Cookie not found";

		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("user".equals(c.getName())) {
					userCookieValue = c.getValue();
					break;
				}
			}
		}

		// Delete a cookie
		Cookie deleteCookie = new Cookie("user", "");
		deleteCookie.setMaxAge(0); // Set cookie age to 0 to delete it
		response.addCookie(deleteCookie);

		out.println("<html><body>");
		out.println("<h1>Cookie Management</h1>");
		out.println("<p>Cookie set: user=KaranAujla</p>");
		out.println("<p>Retrieved cookie value: " + userCookieValue + "</p>");
		out.println("<p>Cookie 'user' has been deleted.</p>");
		out.println("</body></html>");
	}
}
