package com.dynatrace.undertow.reproducer;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynatrace.undertow.reproducer.util.DelayedResponse;
import com.dynatrace.undertow.reproducer.util.LoggingAsyncListener;
import com.dynatrace.undertow.reproducer.util.Util;

@WebServlet(urlPatterns="/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.trace("ENTER AsyncServlet.doGet()");
		try {
			final AsyncContext context = request.startAsync(request, response); 
			context.addListener(new LoggingAsyncListener());
			Thread thread = new Thread(new DelayedResponse(context, 1000));
			thread.setDaemon(false);
			thread.start();
			Util.sleep(500);
		} finally {
			Util.trace("EXIT AsyncServlet.doGet()");
		}
	}
	
}
