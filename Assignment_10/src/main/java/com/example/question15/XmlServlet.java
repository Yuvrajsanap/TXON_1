package com.example.question15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/xml15")
public class XmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read the XML data from the request body
		StringBuilder xmlBuffer = new StringBuilder();
		String line;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				xmlBuffer.append(line);
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid XML input");
			return;
		}

		// Process XML data (Here we are just echoing the received data)
		String receivedXml = xmlBuffer.toString();

		// Create an XML response
		String responseXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<response>"
				+ "<message>Received data successfully</message>" + "<receivedData>" + receivedXml + "</receivedData>"
				+ "</response>";

		// Set the response content type to XML
		response.setContentType("application/xml");
		response.setCharacterEncoding("UTF-8");

		// Write the XML response
		try (PrintWriter out = response.getWriter()) {
			out.print(responseXml);
			out.flush();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error writing XML response");
		}
	}
}
