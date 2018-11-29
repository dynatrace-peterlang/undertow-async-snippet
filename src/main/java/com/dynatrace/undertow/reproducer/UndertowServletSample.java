package com.dynatrace.undertow.reproducer;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;


public class UndertowServletSample {
	
	private String host = "127.0.0.1";
	private int port = 8080;
	
	public void run() {
		try {
			DeploymentInfo servletBuilder = Servlets.deployment()
			        .setClassLoader(UndertowServletSample.class.getClassLoader())
			        .setContextPath("/sample")
			        .setDeploymentName("test.war")
			        .addServlets(
		                Servlets.servlet("SimpleHtmlServlet", SimpleHtmlServlet.class).addMapping("/do.simple").setAsyncSupported(true),
		                Servlets.servlet("AsyncErrorServlet", AsyncErrorServlet.class).addMapping("/error").setAsyncSupported(true),
		                Servlets.servlet("AsyncServlet", AsyncServlet.class).addMapping("/async").setAsyncSupported(true)
			        );

			DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
			manager.deploy();
			PathHandler path = Handlers.path().addPrefixPath("/", manager.start());

			Undertow server = Undertow.builder()
			        .addHttpListener(port, host)
			        .setHandler(path)
			        .setIoThreads(5)
			        .setWorkerThreads(5)
			        .build();
			System.out.println("Will start undertow server at http://localhost:" + port);
			server.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UndertowServletSample().run();
	}
	
}

