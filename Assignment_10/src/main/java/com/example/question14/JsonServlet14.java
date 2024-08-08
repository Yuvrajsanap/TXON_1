package com.example.question14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/question144")
public class JsonServlet14 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read the JSON data from the request body
		StringBuilder jsonBuffer = new StringBuilder();
		String line;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				jsonBuffer.append(line);
			}
		}

		// Parse the JSON data
		JSONObject requestJson = new JSONObject(jsonBuffer.toString());
		String name = requestJson.optString("name", "undefined");
		int age = requestJson.optInt("age", -1);

		// Create a JSON response
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "Received data successfully");
		responseJson.put("receivedName", name);
		responseJson.put("receivedAge", age);

		// Set the response content type to JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Write the JSON response
		try (PrintWriter out = response.getWriter()) {
			out.print(responseJson.toString());
			out.flush();
		}
	}
}
