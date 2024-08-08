package com.example.filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = "sample.txt"; // The file name to be downloaded
		String filePath = getServletContext().getRealPath("/files/") + fileName;

		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);

		// Setting the response headers
		response.setContentType("application/octet-stream");
		response.setContentLength((int) downloadFile.length());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

		// Get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		// Write bytes read from the input stream into the output stream
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
	}
}
