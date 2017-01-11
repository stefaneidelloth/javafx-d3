package org.treez.javafxd3.d3.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class JsDate extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public JsDate(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	public static JsDate create(WebEngine webEngine, long time) {
		D3 d3 = new D3(webEngine);
		Date date = new Date(time);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		String dateString = dateFormat.format(date);

		JsDate dateResult = d3.time().format("%Y-%m-%d %H:%M:%S:%L").parse(dateString);
		return dateResult;
	}

	public static JsDate create(WebEngine webEngine, double date) {
		JsDate result = create(webEngine, (long) date);
		return result;
	}

	/**
	 * Parses a string representation of a date and time and returns the
	 * internal millisecond representation, e.g. "January 11, 1979 09:05:18" =>
	 * 283939518000
	 * 
	 * @param webEngine
	 * @param dateString
	 * @return
	 */
	public static double parse(WebEngine webEngine, String dateString) {

		D3 d3 = new D3(webEngine);
		
		JsDate dateResult;
		try{
		 dateResult = d3.time().format("%B %d, %Y %H:%M:%S").parse(dateString);
		} catch (Exception exception){
			
			String message = "Could not parse date " + dateString;
			throw new IllegalStateException(message, exception);
				
		}
		double millis = dateResult.getTime();
		return millis;		

	}

	public long getTime() {
		double result = callForDouble("getTime");
		return (long) result;
	}

	public int getFullYear() {
		int result = callForInteger("getFullYear");
		return result;
	}

	public int getMonth() {
		int result = callForInteger("getMonth");
		return result;
	}
	
	public Double doubleValue(){
		Double result = callForDouble("doubleValue");
		return result;
	}

	/**
	 * Returns the day of the week as number (e.g. 1 for monday)
	 * 
	 * @return
	 */
	public int getDay() {
		int result = callForInteger("getDay");
		return result;
	}

	/**
	 * Returns the day of the month as number (e.g. 31)
	 * 
	 * @return
	 */
	public int getDate() {
		int result = callForInteger("getDate");
		return result;
	}

	public int getHours() {
		int result = callForInteger("getHours");
		return result;
	}

	public int getMinutes() {
		int result = callForInteger("getMinutes");
		return result;
	}

	public int getSeconds() {
		int result = callForInteger("getSeconds");
		return result;
	}

	public int getMilliseconds() {
		int result = callForInteger("getMilliseconds");
		return result;
	}

	@Override
	public String toString() {
		String valueString = getJsObject().toString();
		return valueString;
	}

	//#end region

}
