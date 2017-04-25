package com.tomcat.hosting.servlet;

import java.text.SimpleDateFormat;

public class SimpleFileWrapper {
	static final SimpleDateFormat FORMATER = new SimpleDateFormat("MM/dd/yyyy");
	static final String IMAGE = "gif,jpg,jpeg,png,ico,bmp";
	String name;
	String date;
	String size;
	
	SimpleFileWrapper(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static String convertFileSize (long size)
	{
		int divisor = 1;
		String unit = "bytes";
		if (size>= 1024*1024){
			divisor = 1024*1024;
			unit = "MB";
		}
		else if (size>= 1024){
			divisor = 1024;
			unit = "KB";
		}
		if (divisor ==1) return size /divisor + " "+unit;
		String aftercomma = ""+100*(size % divisor)/divisor;
		if (aftercomma.length() == 1) aftercomma="0"+aftercomma;
		return size /divisor + "."+aftercomma+" "+unit;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
