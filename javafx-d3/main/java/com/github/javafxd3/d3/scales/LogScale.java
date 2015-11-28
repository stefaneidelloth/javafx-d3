package com.github.javafxd3.d3.scales;

import com.github.javafxd3.d3.arrays.Array;
import com.github.javafxd3.d3.core.Formatter;
import com.github.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Log scales are similar to linear scales, except there's a logarithmic
 * transform that is applied to the input domain value before the output range
 * value is computed.
 * <p>
 * The mapping to the output range value y can be expressed as a function of the
 * input domain value x: y = m log(*x*) + b.
 * <p>
 * Log scales also support negative values, in which case the input value is
 * multiplied by -1, and the resulting output value is also multiplied by -1.
 * <p>
 * However, note that the domain of a log scale should never contain zero, as
 * log(0) is negative infinity.
 * <p>
 * As with {@link LinearScale}s, log scales can also accept more than two values
 * for the domain and range, thus resulting in polylog scale.
 * <p>
 */
public class LogScale extends ContinuousQuantitativeScale<LogScale> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public LogScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// =========== ticks ==========

	/**
	 * Returns representative values from the scale's input domain.
	 * <p>
	 * The returned tick values are uniformly spaced within each power of ten,
	 * and are guaranteed to be within the extent of the input domain.
	 * <p>
	 * Ticks are often used to display reference lines, or tick marks, in
	 * conjunction with the visualized data.
	 * <p>
	 * Note that the number of ticks cannot be customized (due to the nature of
	 * log scales); however, you can filter the returned array of values if you
	 * want to reduce the number of ticks.
	 * 
	 * @return the current scale
	 */
	public <T> Array<T> ticks() {
		JSObject result = call("ticks");
		return new Array<T>(webEngine, result);
	}

	// =========== tickFormat ==========

	/**
	 * Returns a number format function suitable for displaying a tick value.
	 * <p>
	 * The returned tick format is implemented as d.toPrecision(1).
	 * <p>
	 * 
	 * @return the number format
	 */
	public Formatter tickFormat() {
		JSObject result = call("tickFormat");
		return new Formatter(webEngine, result);
	}

	/**
	 * Returns a number format function suitable for displaying a tick value.
	 * <p>
	 * The returned tick format is implemented as d.toPrecision(1).
	 * <p>
	 * Some of the tick labels may not be displayed; this is useful if there is
	 * not enough room to fit all of the tick labels. However, note that the
	 * tick marks will still be displayed (so that the log scale distortion
	 * remains visible).
	 * <p>
	 * 
	 * 
	 * @param the
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
	 * The returned tick format is implemented as d.toPrecision(1).
	 * <p>
	 * Some of the tick labels may not be displayed; this is useful if there is
	 * not enough room to fit all of the tick labels. However, note that the
	 * tick marks will still be displayed (so that the log scale distortion
	 * remains visible).
	 * <p>
	 * The format argument allow to specify a format as a string. If the format
	 * specifier doesn’t have a defined precision, the precision will be set
	 * automatically by the scale, returning the appropriate format.
	 * <p>
	 * This provides a convenient, declarative way of specifying a format whose
	 * precision will be automatically set by the scale.
	 * <p>
	 * 
	 * @param the
	 *            number of ticks to take into account to create the
	 *            {@link Formatter}.
	 * @param the
	 *            format specified, as documented in {@link Formatter}, to be
	 *            used as a basis of the Formatter.
	 * @return a number format
	 */
	public Formatter tickFormat(int count, String formatSpecifier) {
		JSObject result = call("tickFormat", count, formatSpecifier);
		return new Formatter(webEngine, result);
	}

	/**
	 * Returns a number format function suitable for displaying a tick value.
	 * <p>
	 * The returned tick format is implemented as d.toPrecision(1).
	 * <p>
	 * Some of the tick labels may not be displayed; this is useful if there is
	 * not enough room to fit all of the tick labels. However, note that the
	 * tick marks will still be displayed (so that the log scale distortion
	 * remains visible).
	 * <p>
	 * The format argument allow to specify a format as a string. If the format
	 * specifier doesn’t have a defined precision, the precision will be set
	 * automatically by the scale, returning the appropriate format.
	 * <p>
	 * This provides a convenient, declarative way of specifying a format whose
	 * precision will be automatically set by the scale.
	 * <p>
	 * 
	 * @param count
	 *            the number of ticks to take into account to create the
	 *            {@link Formatter}.
	 * @param formatFunction
	 *            the function used to format the tick label
	 * @return a number format
	 */
	public synchronized Formatter tickFormat(int count, DatumFunction<String> function) {

		String functionName = "temp__tickformat__function";
		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		
		d3.setMember(functionName, function);

		String command = "this.tickFormat( " + count + ", function(d) { " //				
				+ "return d3." + functionName + ".apply(null,{datum:d},0); " //
				+ "});";

		Object result = eval(command);
		
		JSObject formatteJsResult = (JSObject) result;
		
		
		return new Formatter(webEngine, formatteJsResult);
	}

	// =========== nice ==========

	/**
	 * Extends the domain so that it starts and ends on nice round values.
	 * <p>
	 * This method typically modifies the scale's domain, and may only extend
	 * the bounds to the nearest round value.
	 * <p>
	 * The nearest round value is based on the nearest power of ten.
	 * <p>
	 * Nicing is useful if the domain is computed from data and may be
	 * irregular.
	 * <p>
	 * For example, for a domain of [0.20147987687960267, 0.996679553296417],
	 * the nice domain is [0.1, 1]. If the domain has more than two values,
	 * nicing the domain only affects the first and last value.
	 * <p>
	 * 
	 * @return the current scale
	 */
	public LogScale nice() {
		JSObject result = call("nice");
		return new LogScale(webEngine, result);		
	}

	// =========== base ==========
	/**
	 * Returns the current base, which defaults to 10.
	 * 
	 * @return the current base
	 */
	public int base() {
		int result = callForInteger("base");
		return result;
	}

	/**
	 * Sets the base for this logarithmic scale.
	 * 
	 * @return the current scale
	 */
	public LogScale base(int b) {
		JSObject result = call("base", b);
		return new LogScale(webEngine, result);	
	}

	@Override
	public LogScale createScale(WebEngine webEngine, JSObject result) {
		return new LogScale(webEngine, result);
	}

	//#end region

}
