package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Formatter;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Brush;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Identity scales are a special case of linear scales where the domain and
 * range are identical; the {@link #apply(double)} and its
 * {@link #invert(double)} method are both the identity function.
 * <p>
 * These scales are occasionally useful when working with pixel coordinates, say
 * in conjunction with the {@link Axis} and {@link Brush} components.
 * <p>
 * The methods {@link #domain(double...)} and {@link #range(double...)} have the
 * same effect of setting both the domain and range in the same time.
 * <p>  
 */
public class IdentityScale extends ContinuousQuantitativeScale<IdentityScale> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public IdentityScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);

	}

	//#end region

	//#region METHODS

	// =========== ticks ==========

	/**
	 * Returns approximately count representative values from the scale's input
	 * domain (or equivalently, output range).
	 * <p>
	 * The returned tick values are uniformly spaced, have human-readable values
	 * (such as multiples of powers of 10), and are guaranteed to be within the
	 * extent of the input domain.
	 * <p>
	 * Ticks are often used to display reference lines, or tick marks, in
	 * conjunction with the visualized data.
	 * <p>
	 * The specified count is only a hint; the scale may return more or fewer
	 * values depending on the input domain.
	 * <p>
	 * 
	 * @param count
	 * 
	 * @return the array of ticks
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
	 * 
	 * @param count
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

	@Override
	public IdentityScale createScale(WebEngine webEngine, JSObject result) {
		return new IdentityScale(webEngine, result);
	}

	//#end region
}
