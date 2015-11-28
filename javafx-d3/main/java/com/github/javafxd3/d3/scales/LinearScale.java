package com.github.javafxd3.d3.scales;


import com.github.javafxd3.d3.arrays.Array;
import com.github.javafxd3.d3.core.Formatter;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Linear scales are the most common scale, and a good default choice to map a
 * continuous input domain to a continuous output range.
 * <p>
 * The mapping is linear in that the output range value y can be expressed as a
 * linear function of the input domain value x: y = mx + b.
 * <p>
 * The input domain is typically a dimension of the data that you want to
 * visualize, such as the height of students (measured in meters) in a sample
 * population.
 * <p>
 * The output range is typically a dimension of the desired output
 * visualization, such as the height of bars (measured in pixels) in a
 * histogram.
 * <p>
 * The default linear scale has a default domain of [0,1] and the default range
 * [0,1]. Thus, the default linear scale is equivalent to the identity function
 * for numbers; for example linear(0.5) returns 0.5.
 * <p>
 * Although linear scales typically have just two numeric values in their
 * domain, you can specify more than two values for a polylinear scale.
 * <p>
 * In this case, there must be an equivalent number of values in the output
 * range. A polylinear scale represents multiple piecewise linear scales that
 * divide a continuous domain and range. This is particularly useful for
 * defining diverging quantitative scales. For example, to interpolate between
 * white and red for negative values, and white and green for positive values,
 * say:
 * 
 * <pre>
 * {@code 
 * Scale color = d3.scale.linear().domain([-1, 0, 1]).range(["red","white", "green"]); 
 * }
 * </pre>
 * 
 * The resulting value of color(-.5) is rgb(255, 128, 128), and the value of
 * color(.5) is rgb(128, 192, 128).
 * <p>
 * Internally, polylinear scales perform a binary search for the output
 * interpolator corresponding to the given domain value. By repeating values in
 * both the domain and range, you can also force a chunk of the input domain to
 * map to a constant in the output range.
 * <p>
 * The output range must contain two or more values, to match the cardinality of
 * the input domain, otherwise the longer of the two is truncated to match the
 * other.
 * <p>
 * The elements in the output range array need not be numbers; any value that is
 * supported by the underlying interpolator will work.
 * <p>
 * However, numeric ranges are required for the {@link #invert(double)}
 * operator.
 * <p> 
 */
public class LinearScale extends ContinuousQuantitativeScale<LinearScale> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public LinearScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);

	}

	//#end region

	//#region METHODS

	// =========== ticks ==========

	/**
	 * Returns approximately count representative values from the scale's input
	 * domain.
	 * <p>
	 * The returned tick values are uniformly spaced, have human-readable values
	 * (such as multiples of powers of 10), and are guaranteed to be within the
	 * extent of the input domain.
	 * <p>
	 * Ticks are often used to display reference lines, or tick marks, in
	 * conjunction with the visualized data. The specified count is only a hint;
	 * the scale may return more or fewer values depending on the input domain.
	 * <p>
	 * @param count 
	 * 
	 * @return
	 */
	public <T> Array<T> ticks(int count) {
		JSObject result = call("ticks", count);
		return new Array<T>(webEngine, result);		
	}

	/**
	 * Alias for {@link #ticks(int) ticks(10)}.
	 * 
	 * @return the array of reference ticks
	 */
	public <T> Array<T> ticks() {
		JSObject result = call("ticks");
		return new Array<T>(webEngine, result);	
	}

	// =========== tickFormat ==========

	/**
	 * Returns a number format function suitable for displaying a tick value.
	 * <p>
	 * The specified count should have the same value as the count that is used
	 * to generate the tick values you want to display.
	 * <p>
	 * You don't have to use the scale's built-in tick format, but it
	 * automatically computes the appropriate precision based on the fixed
	 * interval between tick values.
	 * <p>
	 * @param count 
	 * 
	 *            number of ticks to take into account to create the
	 *            {@link Formatter}.
	 * @return a number format
	 */
	public Formatter tickFormat(int count) {
		JSObject result = call("tickFormat", count);
		return new Formatter(webEngine, result);		
	}

	/**
	 * Returns a number format function suitable for displaying a tick value.
	 * <p>
	 * This is the same as {@link #tickFormat(int)}, except that the format
	 * argument allows a format specifier to be specified.
	 * <p>
	 * If the format specifier doesn’t have a defined precision, the precision
	 * will be set automatically by the scale, returning the appropriate format.
	 * <p>
	 * This provides a convenient, declarative way of specifying a format whose
	 * precision will be automatically set by the scale.
	 * <p>
	 * @param count the
	 *            number of ticks to take into account to create the
	 *            {@link Formatter}.
	 * @param formatSpecifier the
	 *            format specified, as documented in {@link Formatter}, to be
	 *            used as a basis of the Formatter.
	 * 
	 * @return a number format
	 */
	public Formatter tickFormat(int count, String formatSpecifier) {
		JSObject result = call("tickFormat", count, formatSpecifier);
		return new Formatter(webEngine, result);		
	}

	// =========== nice ==========

	/**
	 * Extends the domain so that it starts and ends on nice round values.
	 * <p>
	 * This method typically modifies the scale's domain, and may only extend
	 * the bounds to the nearest round value.
	 * <p>
	 * The precision of the round value is dependent on the extent of the domain
	 * dx according to the following formula: exp(round(log(*dx*)) - 1).
	 * <p>
	 * Nicing is useful if the domain is computed from data and may be
	 * irregular.
	 * <p>
	 * For example, for a domain of [0.20147987687960267, 0.996679553296417],
	 * the nice domain is [0.2, 1]. If the domain has more than two values,
	 * nicing the domain only affects the first and last value.
	 * <p>
	 * 
	 * @return the current scale
	 */
	public LinearScale nice() {
		JSObject result = call("nice");
		return new LinearScale(webEngine, result);		
	}

	/**
	 * Same as {@link #nice()} but with more control.
	 * <p>
	 * The tick count argument allows greater control over the step size used to
	 * extend the bounds, guaranteeing that the ticks returned by
	 * {@link #ticks(int)} will exactly cover the domain.
	 * <p>
	 * @param count 
	 * 
	 * @return the current scale
	 */
	public LinearScale nice(int count) {
		JSObject result = call("nice", count);
		return new LinearScale(webEngine, result);			
	}

	@Override
	public LinearScale createScale(WebEngine webEngine, JSObject result) {
		return new LinearScale(webEngine, result);		
	}

	//#end region

}
