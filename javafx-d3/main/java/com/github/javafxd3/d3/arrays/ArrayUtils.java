package com.github.javafxd3.d3.arrays;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import netscape.javascript.JSObject;

/**
 * Helper class that provides methods to create object arrays which can be
 * passed to JavaScript
 *
 */
public class ArrayUtils {
	
	
	/**
	 * Creates an object array from a double array
	 * @param numbers
	 * @return
	 */
	public static Object[] createArrayFromDoubles(double[] numbers) {
		Object[] values = new Object[numbers.length];
		for(int i = 0; i< numbers.length; i++){
			values[i]= numbers[i];
		}		
		return values;
	}
	
	/**
	 * Creates an object array from a String array
	 * @param numbers
	 * @return
	 */
	public static Object[] createArrayFromStrings(String[] numbers) {
		Object[] values = new Object[numbers.length];
		for(int i = 0; i< numbers.length; i++){
			values[i]= numbers[i];
		}		
		return values;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(Integer... numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(long[] numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param chars
	 * @return
	 */
	public static String createArrayString(char[] chars) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object character: chars){
    		list.add("'"+character+"'");
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(int[] numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(short[] numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(int[][] numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (int[] numberRow: numbers){
    		List<String> rowList = new java.util.ArrayList<>();    		
    		for (Integer number: numberRow){    			
        		rowList.add(""+number);
        	}
    		String rowString = "[" + String.join(",", rowList) + "]";    		
    		list.add(rowString);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(byte... numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(Double... numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(Double[][] numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Double[] numberRow: numbers){
    		List<String> rowList = new java.util.ArrayList<>();    		
    		for (Double number: numberRow){    			
        		rowList.add(""+number);
        	}
    		String rowString = "[" + String.join(",", rowList) + "]";    		
    		list.add(rowString);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(double... numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(float... numbers) {
		List<String> list = new java.util.ArrayList<>();
    	for (Object number: numbers){
    		list.add(""+number);
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Creates a string that contains the given values as array in square brackets
	 * @param numbers
	 * @return
	 */
	public static String createArrayString(final String... strings) {
		List<String> list = new java.util.ArrayList<>();
    	for (String string: strings){
    		list.add("'"+string + "'");
    	}
    	String arrayString = "[" + String.join(",", list) + "]";
		return arrayString;
	}
	
	/**
	 * Extracts the JSObjects from the given JavaScriptObjects and returns them
	 * as new array
	 * @param data
	 * @return
	 */
	public static JSObject[] JavaScriptObjectToJSObject(JavaScriptObject[] data) {
			    		
		List<JSObject> jsObjectList = new ArrayList<>();
		for(JavaScriptObject javaScriptObject: data){
			jsObjectList.add(javaScriptObject.getJsObject());
		}
		JSObject[] jsObjects = jsObjectList.toArray(new JSObject[jsObjectList.size()]);
		
		return jsObjects;
	}

}
