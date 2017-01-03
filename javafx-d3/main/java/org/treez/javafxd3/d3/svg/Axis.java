package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.core.Formatter;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.LogScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.time.Interval;
import org.treez.javafxd3.d3.time.TimeScale;
import org.treez.javafxd3.d3.wrapper.Inspector;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * D3 axis component displays reference lines for {@link Scale}s automatically.
 * <p>
 * This lets you focus on displaying the data, while the axis component takes
 * care of the tedious task of drawing axes and labeled ticks.
 * <p>
 * The axis component is designed to work with D3â€™s {@link QuantitativeScale},
 * {@link TimeScale} and {@link OrdinalScale} scales.
 * <p>
 */
public class Axis extends JavaScriptObject implements JsFunction {

	//#region ATTRIBUTES

	Scale<?> associatedScale;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Axis(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Return the associated scale, which defaults to a linear scale.
	 * 
	 * @return the scale.
	 */
	public <S extends Scale<S>> S scale() {
		JSObject result = call("scale");
		if (associatedScale != null) {
			S scale = (S) associatedScale.createScale(webEngine, result);
			return scale;
		} else {
			LinearScale linearScale = new LinearScale(webEngine, result);
			return (S) linearScale;
		}
	}

	/**
	 * Set the associated scale.
	 * 
	 * @param scale
	 * @return the current axis
	 */
	public <S extends Scale<?>> Axis scale(S scale) {
		
		this.associatedScale = scale;
		JSObject jsScale = scale.getJsObject();			
		JSObject result = call("scale", jsScale);
		return new Axis(webEngine, result);
	}

	/**
	 * Returns the current orientation, which defaults to
	 * {@link Orientation#BOTTOM}.
	 * <p>
	 * 
	 * @see #orient(Orientation)
	 * 
	 * @return the current orientation
	 */
	public Orientation orient() {

		String command = "this.orient().toUpperCase()";
		String enumString = evalForString(command);
		Orientation orientation = Orientation.valueOf(enumString);
		return orientation;

	}

	/**
	 * Sets the axis orientation and returns the axis.
	 * <p>
	 * The orientation of an axis is the position of the ticks and their labels
	 * in relation to the axis path.
	 * <p>
	 * For a vertical axis, specify {@link Orientation#LEFT} or
	 * {@link Orientation#RIGHT}; for a horizontal axis, specify
	 * {@link Orientation#TOP} or {@link Orientation#BOTTOM}.
	 * <p>
	 * If instead you want to determine the position of the axis with respect to
	 * the plot, use the transform attribute.
	 * <p>
	 * 
	 * @param o
	 *            the orientation
	 * @return the current axis
	 */
	public Axis orient(Orientation o) {
		String orientation = o.toString().toLowerCase();
		JSObject result = call("orient", orientation);
		return new Axis(webEngine, result);
	}

	// ========== ticks methods =========
	/**
	 * Get the arguments that will be passed to the associated scale
	 * Scale#ticks() method to compute the tick values.
	 * 
	 * @return the arguments
	 */
	public Array<Value> ticks() {
		JSObject result = call("ticks");
		return new Array<Value>(webEngine, result);
	}

	/**
	 * Specify the argument that will be passed to the associated scale
	 * {@link Scale#ticks()} method to compute the tick values. This version
	 * specify the desired tick count.
	 * <p>
	 * The count parameter is also passed to the
	 * {@link Scale#tickFormat(int, String)} method to generate the default tick
	 * format.
	 * <p>
	 * This version suits for {@link LinearScale} and {@link LogScale}.
	 * <p>
	 * 
	 * @param count
	 *            the argument to be passed to the underlying scale.
	 * @return the current axis
	 */
	public Axis ticks(int count) {
		JSObject result = call("ticks", count);
		return new Axis(webEngine, result);
	}

	/**
	 * Same as {@link #ticks(int)} but suitable for
	 * {@link LogScale#tickFormat(int, String)}.
	 * 
	 * 
	 * @param count
	 *            the count to be passed to the underlying scale.
	 * @param formatSpecifier
	 *            the format argument to be passed to the underlying scale.
	 * @return the current axis
	 */
	public Axis ticks(int count, String formatSpecifier) {
		JSObject result = call("ticks", count, formatSpecifier);
		return new Axis(webEngine, result);
	}
	
	/**
	 * Same as {@link #ticks(int)} but suitable for
	 * {@link LogScale#tickFormat(int, String)}.
	 * 
	 * 
	 * @param count
	 *            the count to be passed to the underlying scale.
	 * @param formatSpecifier
	 *            the format argument to be passed to the underlying scale.
	 * @return the current axis
	 */
	public Axis ticksExpression(int count, String formatFunctionExpression) {		
		String command = "this.ticks(" + count + ", " + formatFunctionExpression + ");";
		JSObject result = evalForJsObject(command);
		return new Axis(webEngine, result);		
	}

	/**
	 * Same as {@link #ticks(int)} but suitable for
	 * {@link LogScale#tickFormat(int, DatumFunction)}.
	 * 
	 * 
	 * @param count
	 *            the count to be passed to the underlying scale.
	 * @param formatSpecifier
	 *            the format argument to be passed to the underlying scale.
	 * @return the current axis
	 */
	public Axis ticks(int count, DatumFunction<String> formatSpecifier) {

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, formatSpecifier);

		String command = "this.ticks(" + count + ", d3." + memberName + ");";

		JSObject result = evalForJsObject(command);

		return new Axis(webEngine, result);
	}

	/**
	 * Same as {@link #ticks(int)} but suitable for
	 * {@link TimeScale#ticks(Interval, int)}.
	 * 
	 * 
	 * @param interval
	 *            the time interval to be passed to the underlying scale.
	 * @param steps
	 *            the steps argument to be passed to the underlying scale.
	 * @return the current axis
	 */
	public Axis ticks(Interval interval, int steps) {
		JSObject result = call("ticks", interval, steps);
		return new Axis(webEngine, result);
	}

	// ========== tick size =========

	/**
	 * @return the current tick size, which defaults to 6
	 */
	public double tickSize() {
		Double result = callForDouble("tickSize");
		return result;
	}

	/**
	 * Sets the outer and inner ticks to the specified value.
	 * 
	 * @param outerInnerTickSizeInPixels
	 *            the tick size in pixels
	 * @return the current axis
	 */
	public Axis tickSize(double outerInnerTickSizeInPixels) {
		JSObject result = call("tickSize", outerInnerTickSizeInPixels);
		return new Axis(webEngine, result);
	}

	/**
	 * @param first
	 * @param second
	 * @return
	 */
	public Axis tickSize(double first, double second) {
		JSObject result = call("tickSize", first, second);
		return new Axis(webEngine, result);
	}

	/**
	 * @param first
	 * @param second
	 * @param third
	 * @return
	 */
	public Axis tickSize(double first, double second, double third) {
		JSObject result = call("tickSize", first, second, third);
		return new Axis(webEngine, result);
	}

	/**
	 * Sets the inner tick size to the specified value and returns the axis.
	 * <p>
	 * The inner tick size controls the length of the tick lines, offset from
	 * the native position of the axis.
	 * <p>
	 * 
	 * @param innerTickSizeInPixels
	 *            the new value
	 * @return the current axis
	 */
	public Axis innerTickSize(double innerTickSizeInPixels) {
		JSObject result = call("innerTickSize", innerTickSizeInPixels);
		return new Axis(webEngine, result);
	}

	/**
	 * @return the current inner ticks size, which defaults to 6
	 */
	public double innerTickSize() {
		Double result = callForDouble("innerTickSize");
		return result;
	}

	/**
	 * Sets the outer tick size to the specified value and returns the axis.
	 * <p>
	 * The outer tick size controls the length of the square ends of the domain
	 * path, offset from the native position of the axis.
	 * <p>
	 * Thus, the outer ticks are not actually ticks but part of the domain path,
	 * and their position is determined by the associated scale's domain extent.
	 * <p>
	 * Thus, outer ticks may overlap with the first or last inner tick. An outer
	 * tick size of 0 suppresses the square ends of the domain path, instead
	 * producing a straight line.
	 * <p>
	 * 
	 * @param outerTickSizeInPixels
	 *            the new value
	 * @return the current axis
	 */
	public Axis outerTickSize(double outerTickSizeInPixels) {
		JSObject result = call("outerTickSize", outerTickSizeInPixels);
		return new Axis(webEngine, result);
	}

	/**
	 * @return the current outer ticks size, which defaults to 6
	 */
	public double outerTickSize() {
		Double result = callForDouble("outerTickSize");
		return result;
	}

	// ========== tick subdivide =========

	// ========== tick padding =========

	/**
	 * Returns the current padding which defaults to 3 pixels
	 * 
	 * @return the current padding
	 */
	public double tickPadding() {
		Double result = callForDouble("tickPadding");
		return result;
	}

	/**
	 * Sets the padding to the specified value in pixels and returns the axis.
	 * <p>
	 * 
	 * @param padding
	 *            the padding in pixels
	 * @return the current axis
	 */
	public Axis tickPadding(double padding) {
		JSObject result = call("tickPadding", padding);
		return new Axis(webEngine, result);
	}

	// ========== apply =========

	/**
	 * Apply the axis to a selection.
	 * <p>
	 * The selection must contain an SVG or G element.
	 * <p>
	 * 
	 * @param selection
	 *            the selection to apply the axis to
	 * @return the current axis.
	 */
	public Axis apply(Selection selection) {
		
		//Inspector.inspect(this);
		
		selection.call(this);
		return this;		
	}

	/**
	 * Apply the axis to a transition.
	 * <p>
	 * The transition must contain an SVG or G element.
	 * <p>
	 * 
	 * @param transition
	 *            the transition to apply the axis to
	 * @return the current axis.
	 */
	public Axis apply(Transition transition) {
		JSObject result = call("this", transition.getJsObject());
		return new Axis(webEngine, result);
	}

	// ========== tickFormat =========

	/**
	 * Override the tick formatting for labels.
	 * 
	 * @param format
	 *            the tick value formatter for labels.
	 * @return the current axis.
	 */
	public Axis tickFormat(Formatter format) {
		JSObject result = call("tickFormat", format.getJsObject());
		return new Axis(webEngine, result);
	}

	/**
	 * Set the function to be used to format tick values.
	 * <p>
	 * This method can be used for example to add prefix or suffix to the result
	 * of a {@link Formatter#format(double)} method.
	 * <p>
	 * Note: the given function of context argument will be null instance, and
	 * the index will be -1.
	 * <p>
	 * 
	 * @param formatFunction
	 *            the function converting each tick value to a String.
	 * @return the current {@link Axis}
	 */
	public Axis tickFormat(DatumFunction<String> formatFunction) {

		assertObjectIsNotAnonymous(formatFunction);
		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObj = getD3();
		d3JsObj.setMember(memberName, formatFunction);

		String command = "this.tickFormat(function(d,i) {"//
				+ "return d3." + memberName + ".apply(null,{datum:d},i);"//
				+ "});";

		JSObject result = evalForJsObject(command);

		return new Axis(webEngine, result);

	}
	
	public Axis tickFormatExpression(String formatFunctionExpression) {		
		String command = "this.tickFormat("+formatFunctionExpression+");";
		JSObject result = evalForJsObject(command);
		return new Axis(webEngine, result);
	}

	/**
	 * Returns the current format function, which defaults to null. A null
	 * format indicates that the {@link Scale}'s default formatter should be
	 * used, which is generated by calling Scale#tickFormat(int). In this case,
	 * the arguments specified by ticks are likewise passed to scale tickFormat.
	 * 
	 * 
	 * @return the current axis.
	 */
	public Formatter tickFormat() {
		JSObject result = call("tickFormat");
		if (result == null) {
			return null;
		}
		return new Formatter(webEngine, result);
	}

	// ========== tickValues =========

	/**
	 * Return the currently-set tick values, which defaults to null.
	 * <p>
	 * 
	 * @return the currently-set tick values
	 */
	public Array<Value> tickValues() {
		JSObject result = call("tickValues");
		if (result == null) {
			return null;
		}
		return new Array<Value>(webEngine, result);
	}

	/**
	 * Specify the values to be used for ticks, rather than using the scale's
	 * automatic tick generator.
	 * <p>
	 * If values is null, clears any previously-set explicit tick values,
	 * reverting back to the scale's tick generator.
	 * <p>
	 * The explicit tick values take precedent over the tick arguments set by
	 * axis.ticks. However, any tick arguments will still be passed to the
	 * scale's tickFormat function if a tick format is not also set; thus, it
	 * may be valid to set both axis.ticks and axis.tickValues.
	 * <p>
	 * 
	 * @param values
	 *            the values to be used for ticks
	 * @return the current axis
	 */
	public Axis tickValues(JavaScriptObject values) {
		JSObject result = call("tickValues", values);
		return new Axis(webEngine, result);
	}

	/**
	 * Alias for {@link #tickValues(JavaScriptObject)}.
	 * 
	 * @param values
	 *            the values
	 * @return the current axis
	 */
	public final Axis tickValues(double... values) {
		String arrayString = ArrayUtils.createArrayString(values);
		String command = "this.tickValues(" + arrayString + ");";
		JSObject result = evalForJsObject(command);
		return new Axis(webEngine, result);
	}

	/**
	 * * Alias for {@link #tickValues(JavaScriptObject)}.
	 * 
	 * @param values
	 *            the values
	 * @return the current axis
	 */
	public final Axis tickValues(String... values) {
		String arrayString = ArrayUtils.createArrayString(values);
		String command = "this.tickValues(" + arrayString + ");";
		JSObject result = evalForJsObject(command);
		return new Axis(webEngine, result);
	}

	/**
	 * Alias for {@link #tickValues(JavaScriptObject)}.
	 * 
	 * @param values
	 *            the values
	 * @return the current axis
	 */
	public final Axis tickValues(Object[] values) {
		throw new IllegalStateException("not yet implemented");

		/*
		 * String arrayString = ArrayUtils.createArrayString(values); String
		 * command = "this.tickValues(" + arrayString + ");"; JSObject result =
		 * evalForJsObject(command); return new Axis(webEngine, result);
		 */
	}

	//#end region

	//#region ENUM

	/**
	 * Orientation of the ticks in relation to the axis.
	 * <p>
	 * The choice of the tick orientation induce the orientation of the axis.
	 * <p>
	 * 
	 * 
	 * 
	 */
	public static enum Orientation {

		//#region VALUES
		/**
		 * Ticks as above the horizontal axis.
		 */
		TOP,
		/**
		 * Ticks as below the horizontal axis.
		 */
		BOTTOM,
		/**
		 * Ticks as on the left of the vertical axis.
		 */
		LEFT,
		/**
		 * Ticks as on the right of the vertical axis.
		 */
		RIGHT;

		//#end region

		//#region ACCESSORS

		/**
		 * @return true if the Orientation denote a vertical axis
		 */
		public boolean isVerticalAxis() {
			return (this == LEFT) || (this == RIGHT);
		}

		/**
		 * @return true if the Orientation denote a horizontal axis
		 */
		public boolean isHorizontalAxis() {
			return (this == TOP) || (this == BOTTOM);
		}

		//#end region
	}

	//#end region

}
