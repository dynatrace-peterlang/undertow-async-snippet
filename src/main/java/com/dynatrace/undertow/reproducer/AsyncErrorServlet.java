package com.dynatrace.undertow.reproducer;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynatrace.undertow.reproducer.util.ErrorHandlingAsyncListener;
import com.dynatrace.undertow.reproducer.util.LoggingAsyncListener;

@WebServlet(urlPatterns="/error", asyncSupported = true)
public class AsyncErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AsyncContext context = request.startAsync();
		context.addListener(new LoggingAsyncListener());
		context.addListener(new ErrorHandlingAsyncListener());
		context.setTimeout(3000);
		throw new NullPointerException();
	}
	
}
