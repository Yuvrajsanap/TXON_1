package com.example.filter;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/how") // Apply this filter to all requests
public class RequestLoggingFilter implements Filter {
	private static final Logger logger = Logger.getLogger(RequestLoggingFilter.class.getName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code if needed
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Log request details
		logger.info("Request received: ");
		logger.info("Remote Host: " + request.getRemoteHost());
		logger.info("Remote Addr: " + request.getRemoteAddr());
		logger.info("Request URI: " + ((HttpServletRequest) request).getRequestURI());
		logger.info("Request Method: " + ((HttpServletRequest) request).getMethod());

		// Continue the request-response chain
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Cleanup code if needed
	}
}
