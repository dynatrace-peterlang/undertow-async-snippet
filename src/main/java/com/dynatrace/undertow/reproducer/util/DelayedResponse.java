package com.dynatrace.undertow.reproducer.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class DelayedResponse implements Runnable {
	
	private final AsyncContext context;
	private final long delay;

	public DelayedResponse(AsyncContext context, long delay) {
		this.context = context;
		this.delay = delay;
	}
	
	public void run() {
		Util.trace("ENTER DelayedResponse.run");
		try {
			Util.sleep(delay);
			ServletResponse response = null;
			try {
				response = context.getResponse();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			if (response != null) {
				PrintWriter writer = response.getWriter();
				writer.write("Hello World");
				Util.trace("Calling context.complete()");
				context.complete();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}