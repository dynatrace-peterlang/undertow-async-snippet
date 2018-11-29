package com.dynatrace.undertow.reproducer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static SimpleDateFormat DF = new SimpleDateFormat("HH:ss:SSS");
	
	public static void trace(String msg) {
		System.out.println(DF.format(new Date()) + " " + msg);
	}

	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
