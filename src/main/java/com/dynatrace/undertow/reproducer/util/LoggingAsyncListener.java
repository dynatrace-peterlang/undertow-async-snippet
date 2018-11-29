package com.dynatrace.undertow.reproducer.util;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletResponse;

import com.dynatrace.undertow.reproducer.AsyncErrorServlet;

public class LoggingAsyncListener implements AsyncListener {
	
	public void onStartAsync(AsyncEvent event) throws IOException {
		Util.trace("LoggingAsyncListener.onStartAsync()");
	}
	
	public void onComplete(AsyncEvent event) throws IOException {
		HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
		Util.trace("LoggingAsyncListener.onComplete(status = " + response.getStatus() + ")");
	}
	
	public void onTimeout(AsyncEvent event) throws IOException {
		HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
		Util.trace("LoggingAsyncListener.onTimeout(status = " + response.getStatus() + ")");
		Throwable throwable = event.getThrowable();
		if (throwable != null) {
			Util.trace("  throwable = " + throwable.getClass().getName());
		}
	}
	
	public void onError(AsyncEvent event) throws IOException {
		HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
		Util.trace("LoggingAsyncListener.onError(status = " + response.getStatus() + ")");
		Throwable throwable = event.getThrowable();
		if (throwable != null) {
			Util.trace("  throwable = " + throwable.getClass().getName());
		}
	}
	
}