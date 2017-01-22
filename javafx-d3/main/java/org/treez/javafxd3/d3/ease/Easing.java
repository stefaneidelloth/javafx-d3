package org.treez.javafxd3.d3.ease;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

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
	 * @param engine 
	 * @return the function
	 */
	public static final EasingFunction linear(JsEngine engine) {
		return linear(engine,Mode.IN);
	}

	/**
	 * The identity function
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction linear(JsEngine engine,final Mode mode) {
		return ease(engine,"linear-" + mode.getValue());
	}

	/**
	 * Raises t to the specified power k (e.g., 3).
	 * @param engine 
	 * 
	 * @param k
	 *            the power for raising t
	 * @return the function
	 */
	public static final EasingFunction poly(JsEngine engine,final int k) {
		return poly(engine,Mode.IN, k);
	}

	/**
	 * Raises t to the specified power k (e.g., 3).
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @param k
	 *            the power
	 * @return the function
	 */
	public static final EasingFunction poly(JsEngine engine,final Mode mode, final int k) {
		return ease(engine,"poly-" + mode.getValue(), k);
	}

	/**
	 * Equivalent to poly(2).
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction quad(JsEngine engine) {
		return quad(engine,Mode.IN);
	}

	/**
	 * Equivalent to poly(2).
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction quad(JsEngine engine,final Mode mode) {
		return ease(engine,"quad-" + mode.getValue());
	}

	/**
	 * Equivalent to poly(3).
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction cubic(JsEngine engine) {
		return cubic(engine,Mode.IN);
	}

	/**
	 * Equivalent to poly(3).
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction cubic(JsEngine engine,final Mode mode) {
		return ease(engine,"cubic-" + mode.getValue());
	}

	/**
	 * Applies the trigonometric function sin.
	 * <p>
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction sin(JsEngine engine) {
		return sin(engine,Mode.IN);
	}

	/**
	 * Applies the trigonometric function sin.
	 * <p>
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction sin(JsEngine engine,final Mode mode) {
		return ease(engine,"sin-" + mode.getValue());
	}

	/**
	 * Raises 2 to a power based on t.
	 * <p>
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction exp(JsEngine engine) {
		return exp(engine,Mode.IN);
	}

	/**
	 * Raises 2 to a power based on t.
	 * <p>
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction exp(JsEngine engine,final Mode mode) {
		return ease(engine,"exp-" + mode.getValue());
	}

	/**
	 * The quarter circle.
	 * <p>
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction circle(JsEngine engine) {
		return circle(engine,Mode.IN);
	}

	/**
	 * The quarter circle
	 * <p>
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction circle(JsEngine engine,final Mode mode) {
		return ease(engine,"circle-" + mode.getValue());
	}

	/**
	 * Simulates an elastic band; may extend slightly beyond 0 and 1.
	 * <p>
	 * @param engine 
	 * @param a the amplitude
	 * @param p the period
	 * @return the function
	 */
	public static final EasingFunction elastic(JsEngine engine,final double a, final double p) {
		return elastic(engine,Mode.IN, a, p);
	}

	/**
	 * Simulates an elastic band; may extend slightly beyond 0 and 1.
	 * <p>
	 * @param engine 
	 * @param mode
	 *            the mode
	 * @param a the amplitude
	 * @param p the period
	 * @return the function
	 */
	public static final EasingFunction elastic(JsEngine engine,final Mode mode, final double a, final double p) {
		return ease(engine,"elastic-" + mode.getValue(), a, p);
	}

	/**
	 * Simulates backing into a parking space.
	 * @param engine 
	 * 
	 * @param s the overshoot
	 * @return the function
	 */
	public static final EasingFunction back(JsEngine engine,final double s) {
		return back(engine,Mode.IN, s);
	}

	/**
	 * Simulates backing into a parking space.
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @param s the overshoot
	 * @return the function
	 */
	public static final EasingFunction back(JsEngine engine,final Mode mode, final double s) {
		return ease(engine,"back-" + mode.getValue(), s);
	}

	/**
	 * Simulates a bouncy collision.
	 * @param engine 
	 * 
	 * @return the function
	 */
	public static final EasingFunction bounce(JsEngine engine) {
		return bounce(engine,Mode.IN);
	}

	/**
	 * Simulates a bouncy collision.
	 * @param engine 
	 * 
	 * @param mode
	 *            the mode
	 * @return the function
	 */
	public static final EasingFunction bounce(JsEngine engine,final Mode mode) {
		return ease(engine,"bounce-" + mode.getValue());
	}

	private static  JavascriptEasingFunction ease(JsEngine engine, String type){
		String command = "d3.ease('" + type +"');";
		JsObject result = (JsObject) engine.executeScript(command);
		return new JavascriptEasingFunction(engine, result);
		
	}

	private static  JavascriptEasingFunction ease(JsEngine engine, String type, double a){
		String command = "d3.ease('" + type +"',"+a+");";
		JsObject result = (JsObject) engine.executeScript(command);
		return new JavascriptEasingFunction(engine, result);		
	}

	private static  JavascriptEasingFunction ease(JsEngine engine, String type, double a, double b){
		String command = "d3.ease('" + type +"',"+a+","+b+");";
		JsObject result = (JsObject) engine.executeScript(command);
		return new JavascriptEasingFunction(engine, result);	
	}
}
