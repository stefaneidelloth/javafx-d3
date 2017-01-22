package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

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
	 * @param engine
	 */
	public Random(JsEngine engine) {
		super(engine);
		JsObject random = (JsObject) engine.executeScript("d3.random");
		setJsObject(random);
	}

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Random(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
		JsObject result = call("irwinHall", count);
		return new Random(engine, result);
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
		JsObject result = call("normal");
		return new Random(engine, result);
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
		JsObject result = call("normal", mean);
		return new Random(engine, result);
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
		JsObject result = call("normal", mean, deviation);
		if (result == null){
			return null;
		}
		return new Random(engine, result);
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
		JsObject result = call("logNormal");
		return new Random(engine, result);
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
		JsObject result = call("logNormal", mean);
		return new Random(engine, result);		
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
		JsObject result = call("logNormal", mean, deviation);
		return new Random(engine, result);		
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
