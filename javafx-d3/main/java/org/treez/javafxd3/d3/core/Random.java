package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A pseudorandom number generation function.
 * <p>
 * Random instances are created using static factory methods of this class, such
 * as {@link #normal()}.
 * <p>
 * The {@link #generate()} method is then used to generate numbers.
 * <p>
 */
public class Random extends JavaScriptObject {

	//#region CONSTRUCTORS
	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public Random(WebEngine webEngine) {
		super(webEngine);
		JSObject random = (JSObject) webEngine.executeScript("d3.random");
		setJsObject(random);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Random(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Returns a function for generating random number with a
	 * <a href="http://en.wikipedia.org/wiki/Irwin%E2%80%93Hall_distribution" >
	 * Irwin-Hall</a> distribution.
	 * <p>
	 * 
	 * @param count
	 *            the number of independant variables
	 * @return the generator
	 */
	public Random irwinHall(int count) {
		JSObject result = call("irwinHall", count);
		return new Random(webEngine, result);
	}

	/**
	 * Returns a function for generating random number with a
	 * <a href="http://en.wikipedia.org/wiki/Normal_distribution">normal
	 * (Gaussian) distribution</a>, with a mean of 0 and a deviation of 1.
	 * <p>
	 * 
	 * @return the generator
	 */
	public Random normal() {
		JSObject result = call("normal");
		return new Random(webEngine, result);
	}

	/**
	 * Returns a function for generating random number with a
	 * <a href="http://en.wikipedia.org/wiki/Normal_distribution">normal
	 * (Gaussian) distribution</a>, with the given mean, and a deviation of 1.
	 * <p>
	 * The expected value of the generated pseudorandom numbers is mean, with
	 * the standard deviation of 1.
	 * 
	 * @param mean
	 *            the mean
	 * @return the generator
	 */
	public Random normal(double mean) {
		JSObject result = call("normal", mean);
		return new Random(webEngine, result);
	}

	/**
	 * Returns a function for generating random number with a
	 * <a href="http://en.wikipedia.org/wiki/Normal_distribution">normal
	 * (Gaussian) distribution</a>, with the given mean, and the given
	 * deviation.
	 * <p>
	 * 
	 * @param mean
	 *            the mean
	 * @param deviation
	 *            the deviation
	 * @return the generator
	 */
	public Random normal(double mean, double deviation) {
		JSObject result = call("normal", mean, deviation);
		if (result == null){
			return null;
		}
		return new Random(webEngine, result);
	}

	/**
	 * Returns a function for generating random number with a
	 * <a href="http:normal
	 * distribution</a>, with a mean of 0 and a deviation of 1.
	 * <p>
	 * 
	 * @return the generator
	 */
	public Random logNormal() {
		JSObject result = call("logNormal");
		return new Random(webEngine, result);
	}

	/**
	 * Returns a function for generating random number with a <a
	 * href="http:normal
	 * distribution</a>, with the given mean, and a deviation of 1.
	 * <p>
	 * The expected value of the generated pseudorandom numbers is mean, with
	 * the standard deviation of 1.
	 * 
	 * @param mean
	 *            the mean
	 * @return the generator
	 */
	public   Random logNormal(double mean){
		JSObject result = call("logNormal", mean);
		return new Random(webEngine, result);		
	}

	/**
	 * Returns a function for generating random number with a <a
	 * href="http:normal
	 * distribution</a>, with the given mean, and the given deviation.
	 * <p>
	 * 
	 * @param mean
	 *            the mean
	 * @param deviation
	 *            the deviation
	 * @return the generator
	 */
	public   Random logNormal(double mean, double deviation){
		JSObject result = call("logNormal", mean, deviation);
		return new Random(webEngine, result);		
	}

	/**
	 * Generate a new number with this distribution.
	 * 
	 * @return the new generated number.
	 */
	public double generate(){
		Double result = evalForDouble("this()");
		return result;		
	}
	
	//#end region

}
