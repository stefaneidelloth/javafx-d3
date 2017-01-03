package org.treez.javafxd3.d3.svg;

/**
 * Interpolation mode to be used with Line and Area.
 * 
 * The behavior of some of these interpolation modes may be further
 * customized by specifying a tension.
 */
public enum InterpolationMode {

	//#region VALUES

	/**
	 * piecewise linear segments, as in a polyline.
	 */
	LINEAR("linear"),

	/**
	 * close the linear segments to form a polygon.
	 */
	LINEAR_CLOSED("linear-closed"),

	/**
	 * alternate between horizontal and vertical segments, as in a step
	 * function
	 */
	STEP("step"),
	/**
	 * alternate between vertical and horizontal segments, as in a step
	 * function.
	 */
	STEP_BEFORE("step-before"),

	/**
	 * alternate between horizontal and vertical segments, as in a step
	 * function
	 */
	STEP_AFTER("step-after"),

	/**
	 * a B-spline, with control point duplication on the ends.
	 */
	BASIS("basis"),
	/**
	 * an open B-spline; may not intersect the start or end.
	 */
	BASIS_OPEN("basis-open"),
	/**
	 * a closed B-spline, as in a loop.
	 */
	BASIS_CLOSED("basis-closed"),
	/**
	 * equivalent to basis, except the tension parameter is used to
	 * straighten the spline.
	 */
	BUNDLE("bundle"),
	/**
	 * a Cardinal spline, with control point duplication on the ends.
	 */
	CARDINAL("cardinal"),
	/**
	 * an open Cardinal spline; may not intersect the start or end, but will
	 * intersect other control points.
	 */
	CARDINAL_OPEN("cardinal-open"),
	/**
	 * a closed Cardinal spline, as in a loop.
	 */
	CARDINAL_CLOSED("cardinal-closed"),
	/**
	 * cubic interpolation that preserves monotonicity in y.
	 */
	MONOTONE("monotone");

	//#end region

	//#region ATTRIBUTES

	private final String value;

	//#end region

	//#region CONSTRUCTORS

	private InterpolationMode(final String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	/**
	 * @param value
	 * @return
	 */
	public static InterpolationMode fromValue(final String value) {
		return valueOf(value.toUpperCase().replace('-', '_'));
	}

	//#end region

	//#region ACCESSORS

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	//#end region

}
