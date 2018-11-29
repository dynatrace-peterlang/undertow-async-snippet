package com.dynatrace.undertow.reproducer.util;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandlingAsyncListener implements AsyncListener {
	
	public void onStartAsync(AsyncEvent event) throws IOException {
	}
	
	public void onTimeout(AsyncEvent event) throws IOException {
		HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
		response.setStatus(500);
		response.getWriter().write("Timeout");
		AsyncContext context = event.getAsyncContext();
		context.complete();
	}
	
	public void onError(AsyncEvent event) throws IOException {
//		HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
//		response.setStatus(500);
//		response.getWriter().write("Error from AsyncListener");
		AsyncContext context = event.getAsyncContext();
		context.dispatch("/do.simple");
//		context.complete();
	}

	public void onComplete(AsyncEvent event) throws IOException {
	}
	
}