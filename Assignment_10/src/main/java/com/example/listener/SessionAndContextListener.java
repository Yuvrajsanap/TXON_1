package com.example.listener;

import java.util.logging.Logger;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionAndContextListener implements HttpSessionListener, ServletContextListener {
	private static final Logger logger = Logger.getLogger(SessionAndContextListener.class.getName());

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("Session created: " + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Session destroyed: " + se.getSession().getId());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Servlet context initialized: " + sce.getServletContext().getContextPath());
		// Set a context attribute
		sce.getServletContext().setAttribute("appName", "MyApp");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Servlet context destroyed: " + sce.getServletContext().getContextPath());
		// Remove a context attribute
		sce.getServletContext().removeAttribute("appName");
	}
}
