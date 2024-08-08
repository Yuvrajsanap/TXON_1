package com.example.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR = "uploads"; // Directory to save uploaded files

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create upload directory if it does not exist
		java.nio.file.Path uploadPath = Paths.get(getServletContext().getRealPath("") + UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// Get file part from the request
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		java.nio.file.Path filePath = uploadPath.resolve(fileName);

		// Write file to the specified location
		try (InputStream inputStream = filePart.getInputStream();
				OutputStream outputStream = Files.newOutputStream(filePath)) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}

		response.setContentType("text/html");
		response.getWriter().println("<html><body>");
		response.getWriter().println("<h1>File uploaded successfully!</h1>");
		response.getWriter().println("<p>File: " + fileName + "</p>");
		response.getWriter().println("</body></html>");
	}
}
