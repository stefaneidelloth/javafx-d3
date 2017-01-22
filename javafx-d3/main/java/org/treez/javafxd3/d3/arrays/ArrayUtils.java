package org.treez.javafxd3.d3.arrays;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsObject;

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
	
	public static String createArrayString(Short[] numbers) {
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
	 * Extracts the JsObjects from the given JavaScriptObjects and returns them
	 * as new array
	 * @param data
	 * @return
	 */
	public static JsObject[] JavaScriptObjectToJsObject(JavaScriptObject[] data) {
			    		
		List<JsObject> jsObjectList = new ArrayList<>();
		for(JavaScriptObject javaScriptObject: data){
			jsObjectList.add(javaScriptObject.getJsObject());
		}
		JsObject[] jsObjects = jsObjectList.toArray(new JsObject[jsObjectList.size()]);
		
		return jsObjects;
	}
	
	public double[] range(double start, double stop, double step) {

		List<Double> list = new ArrayList<>();
		double value = start;
		while (value <= stop) {
			list.add(value);
			value += step;
		}

		double[] result = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public Double[] range(double start, double stop) {
		Double[] result = new Double[(int) (stop - start)];

		for (int i = 0; i < stop - start; i++) {
			result[i] = start + i;
		}

		return result;
	}

}
