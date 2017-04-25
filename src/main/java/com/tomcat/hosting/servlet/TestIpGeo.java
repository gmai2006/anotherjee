package com.tomcat.hosting.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TestIpGeo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestIpGeo geo = new TestIpGeo();
//		JSONObject obj = geo.getGeoLocation();
//		System.out.println(obj.get("loc"));
//		System.out.println(obj.get("city"));
//		System.out.println(obj.get("region"));
		
		JSONObject obj = geo.getGeoLocationFromZipcode("98037");
		JSONArray array = (JSONArray)obj.get("results");
		JSONObject entity = (JSONObject)array.get(0);
		System.out.println(entity);
		System.out.println((String)entity.get("formatted_address"));
		JSONObject geometry = (JSONObject)entity.get("geometry");
		JSONObject bounds = (JSONObject)geometry.get("bounds");
		JSONObject viewport = (JSONObject)geometry.get("viewport");
		JSONObject location = (JSONObject)geometry.get("location");
		System.out.println(geometry);
		System.out.println(bounds);
		System.out.println(viewport);
		System.out.println(location);
		System.out.println((Double)location.get("lat"));
		System.out.println((Double)location.get("lng"));
		System.out.println((int)((Double)location.get("lat")*100000));
		System.out.println((int)((Double)location.get("lng")*100000));
	}
		
	
	private JSONObject getGeoLocation() throws Exception {

		String url = "http://ipinfo.io/geo";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Object output = JSONValue.parse(response.toString());

		return (JSONObject)output;
	}
	
	private JSONObject getGeoLocationFromZipcode(String zipcode) throws Exception {
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + zipcode + "&sensor=false";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Object output = JSONValue.parse(response.toString());

		return (JSONObject)output;
	}

}
