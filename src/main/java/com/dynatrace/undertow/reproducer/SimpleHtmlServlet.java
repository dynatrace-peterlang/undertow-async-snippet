package com.dynatrace.undertow.reproducer;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true)
public class SimpleHtmlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpServletResponse response = resp;
		if (req.isAsyncStarted()) {
			AsyncContext asyncContext = req.getAsyncContext();
			response = (HttpServletResponse) asyncContext.getResponse();
		}
		response.getWriter().write("Hello World");
	}
	
}
