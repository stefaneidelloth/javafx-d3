package com.github.javafxd3.d3.ease;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Creates an <a href="http://easings.net/">easing function</a>.
 * <p>
 * The following easing types are supported:
 * <ul>
 * <li>linear - the identity function, t.
 * <li>poly(k) - raises t to the specified power k (e.g., 3).
 * <li>quad - equivalent to poly(2).
 * <li>cubic - equivalent to poly(3).
 * <li>sin - applies the trigonometric function sin.
 * <li>exp - raises 2 to a power based on t.
 * <li>circle - the quarter circle.
 * <li>elastic(a, p) - simulates an elastic band; may extend slightly beyond 0
 * and 1.
 * <li>back(s) - simulates backing into a parking space.
 * <li>bounce - simulates a bouncy collision.
 * </ul>
 * <p>
 * These built-in types may be extended using a variety of {@link Mode}.
 * <p>
 * The default easing function is "cubic-in-out" which provides suitable <a href="http:in
 * slow-out</a> animation.
 * <p>
 * 
 */
public class Easing {

	/**
	 * The identity function
	 * @param webEngine 
	 * @return the function
	 */
	public static final EasingFunction linear(WebEngine webEngine) {
		return linear(webEngine,Mode.IN);
	}

	/**
	 * The identity function
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction linear(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"linear-" + mode.getValue());
	}

	/**
	 * Raises t to the specified power k (e.g., 3).
	 * @param webEngine 
	 * 
	 * @param k
	 *            the power for raising t
	 * @return the function
	 */
	public static final EasingFunction poly(WebEngine webEngine,final int k) {
		return poly(webEngine,Mode.IN, k);
	}

	/**
	 * Raises t to the specified power k (e.g., 3).
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @param k
	 *            the power
	 * @return the function
	 */
	public static final EasingFunction poly(WebEngine webEngine,final Mode mode, final int k) {
		return ease(webEngine,"poly-" + mode.getValue(), k);
	}

	/**
	 * Equivalent to poly(2).
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction quad(WebEngine webEngine) {
		return quad(webEngine,Mode.IN);
	}

	/**
	 * Equivalent to poly(2).
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction quad(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"quad-" + mode.getValue());
	}

	/**
	 * Equivalent to poly(3).
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction cubic(WebEngine webEngine) {
		return cubic(webEngine,Mode.IN);
	}

	/**
	 * Equivalent to poly(3).
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction cubic(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"cubic-" + mode.getValue());
	}

	/**
	 * Applies the trigonometric function sin.
	 * <p>
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction sin(WebEngine webEngine) {
		return sin(webEngine,Mode.IN);
	}

	/**
	 * Applies the trigonometric function sin.
	 * <p>
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction sin(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"sin-" + mode.getValue());
	}

	/**
	 * Raises 2 to a power based on t.
	 * <p>
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction exp(WebEngine webEngine) {
		return exp(webEngine,Mode.IN);
	}

	/**
	 * Raises 2 to a power based on t.
	 * <p>
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction exp(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"exp-" + mode.getValue());
	}

	/**
	 * The quarter circle.
	 * <p>
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction circle(WebEngine webEngine) {
		return circle(webEngine,Mode.IN);
	}

	/**
	 * The quarter circle
	 * <p>
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction circle(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"circle-" + mode.getValue());
	}

	/**
	 * Simulates an elastic band; may extend slightly beyond 0 and 1.
	 * <p>
	 * @param webEngine 
	 * @param a the amplitude
	 * @param p the period
	 * @return the function
	 */
	public static final EasingFunction elastic(WebEngine webEngine,final double a, final double p) {
		return elastic(webEngine,Mode.IN, a, p);
	}

	/**
	 * Simulates an elastic band; may extend slightly beyond 0 and 1.
	 * <p>
	 * @param webEngine 
	 * @param mode
	 *            the mode
	 * @param a the amplitude
	 * @param p the period
	 * @return the function
	 */
	public static final EasingFunction elastic(WebEngine webEngine,final Mode mode, final double a, final double p) {
		return ease(webEngine,"elastic-" + mode.getValue(), a, p);
	}

	/**
	 * Simulates backing into a parking space.
	 * @param webEngine 
	 * 
	 * @param s the overshoot
	 * @return the function
	 */
	public static final EasingFunction back(WebEngine webEngine,final double s) {
		return back(webEngine,Mode.IN, s);
	}

	/**
	 * Simulates backing into a parking space.
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @param s the overshoot
	 * @return the function
	 */
	public static final EasingFunction back(WebEngine webEngine,final Mode mode, final double s) {
		return ease(webEngine,"back-" + mode.getValue(), s);
	}

	/**
	 * Simulates a bouncy collision.
	 * @param webEngine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction bounce(WebEngine webEngine) {
		return bounce(webEngine,Mode.IN);
	}

	/**
	 * Simulates a bouncy collision.
	 * @param webEngine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction bounce(WebEngine webEngine,final Mode mode) {
		return ease(webEngine,"bounce-" + mode.getValue());
	}

	private static  JavascriptEasingFunction ease(WebEngine webEngine, String type){
		String command = "d3.ease('" + type +"');";
		JSObject result = (JSObject) webEngine.executeScript(command);
		return new JavascriptEasingFunction(webEngine, result);
		
	}

	private static  JavascriptEasingFunction ease(WebEngine webEngine, String type, double a){
		String command = "d3.ease('" + type +"',"+a+");";
		JSObject result = (JSObject) webEngine.executeScript(command);
		return new JavascriptEasingFunction(webEngine, result);		
	}

	private static  JavascriptEasingFunction ease(WebEngine webEngine, String type, double a, double b){
		String command = "d3.ease('" + type +"',"+a+","+b+");";
		JSObject result = (JSObject) webEngine.executeScript(command);
		return new JavascriptEasingFunction(webEngine, result);	
	}
}
