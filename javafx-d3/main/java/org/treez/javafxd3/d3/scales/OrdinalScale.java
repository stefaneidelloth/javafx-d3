package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.arrays.Array;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Ordinal {@link Scales} have a discrete domain, such as a set of names or
 * categories, and a discrete output range.
 * <p>
 * The first element in the domain array will be mapped to the first element in
 * the output range, the second domain value to the second range value, and so
 * on.
 * <p>
 * Domain values are stored internally in an associative array as a mapping from
 * value to index; the resulting index is then used to retrieve a value from the
 * output range.
 * <p>
 * Thus, an ordinal scale's values must be coercible to a string, and the
 * stringified version of the domain value uniquely identifies the corresponding
 * range value.
 * <p>
 * Setting the domain on an ordinal scale is optional: if no domain is set, each
 * unique value that is passed to the scale function will be assigned a new
 * value from the output range; in other words, the domain will be inferred
 * implicitly from usage.
 * <p>
 * However, it is a good idea to assign the ordinal scale's domain to ensure
 * deterministic behavior, as inferring the domain from usage will be dependent
 * on ordering.
 * <p>
 * If the domain is set explicitly, values passed to the scale that were not
 * explicitly part of the domain will be added.
 * <p>
 * If there are fewer elements in the range than in the domain, the scale will
 * recycle values from the start of the range.
 * <p>
 * You may use {@link #range(double...)} methods for when the set of discrete
 * output values is computed explicitly, such as a set of categorical colors.
 * <p>
 * In other cases, such as determining the layout of an ordinal scatterplot or
 * bar chart, you may find the #rangePoints(JsArrayInteger, double) or
 * #rangeBands(JsArrayInteger, double) operators more convenient.
 * <p>
 */
public class OrdinalScale extends Scale<OrdinalScale> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public OrdinalScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// ============ rangePoints ============
	/**
     * Sets the output range from the continuous interval specified by its start
     * and end bounds, representing the minimum and maximum numeric value.
     * <p>
     * This interval is subdivided into n evenly-spaced points, where n is the number of (unique) values in the input
     * domain.
     * <p>
     * The first and last point may be offset from the edge of the interval according to the specified
     * <code>padding</code>.
     * <p>
     * The padding is expressed as a multiple of the spacing between points.
     * <p>
     * A reasonable value is 1.0, such that the first and last point will be offset from the minimum and maximum value
     * by half the distance between points.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding
     * @return this instance
     */
    public  OrdinalScale rangePoints(double start, double end,
            double padding) {
    	String command = "this.rangePoints([ "+start+", "+end+" ], "+padding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * Shortcut to {@link #rangePoints(double, double, double)} with a default
     * padding of 0.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @return this instance
     */
    public  OrdinalScale rangePoints(double start, double end) {		
		String command = "this.rangePoints([ "+start+", "+end+" ]);";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	// ============ rangeRoundPoints ============
	/**
     * Like {@link #rangePoints(double, double, double)}, except guarantees that the range values are integers so as to
     * avoid antialiasing artifacts.
     *
     * <p>
     * Note that rounding necessarily introduces additional outer padding which is, on average, proportional to the
     * length of the domain. For example, for a domain of size 50, an additional 25px of outer padding on either side
     * may be required. Modifying the range extent to be closer to a multiple of the domain length may reduce the
     * additional padding.
     * <p>
     * (Alternatively, you could round the output of the scale manually or apply shape-rendering: crispEdges. However,
     * this will result in irregularly spaced points.)
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding
     * @return this instance
     */
    public  OrdinalScale rangeRoundPoints(double start, double end,
            double padding) {		
		String command = "this.rangeRoundPoints([ "+start+", "+end+" ], "+padding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * Shortcut to {@link #rangeRoundPoints(double, double, double)} with a default
     * padding of 0.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @return this instance
     */
    public  OrdinalScale rangeRoundPoints(double start, double end) {
    	String command = "this.rangeRoundPoints([ "+start+", "+end+" ]);";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	// ============ range bands ===============

	/**
     * Sets the output range from the continuous interval specified by its start
     * and end bounds, representing the minimum and maximum numeric value.
     * <p>
     * This interval is subdivided into n evenly-spaced bands, where n is the number of (unique) values in the input
     * domain.
     * <p>
     * The bands may be offset from the edge of the interval and other bands according to the specified padding, which
     * defaults to zero.
     * <p>
     * The padding is typically in the range [0,1] and corresponds to the amount of space in the range interval to
     * allocate to padding.
     * <p>
     * A value of 0.5 means that the band width will be equal to the padding width.
     * <p>
     * The outerpadding argument is for the entire group of bands; a value of 0 means there will be padding only between
     * rangeBands.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding to be set to between each band, between 0 and 1
     * @param outerPadding
     *            the padding around the whole group of bands.
     * @return this instance
     */
    public  OrdinalScale rangeBands(double start, double end,
            double padding, double outerPadding){		
		String command = "this.rangeBands([ "+start+", "+end+" ], "+padding+","+outerPadding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * See {@link #rangeBands(double, double, double, double)} with a
     * outerPadding of 0.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding to be set to between each band, between 0 and 1
     * @return this instance
     */
    public  OrdinalScale rangeBands(double start, double end,
            double padding){		
		
		String command = "this.rangeBands([ "+start+", "+end+" ], "+padding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * See {@link #rangeBands(double, double, double, double)} with a padding of
     * 0.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @return this instance
     */
    public  OrdinalScale rangeBands(double start, double end){
    	String command = "this.rangeBands([ "+start+", "+end+" ]);";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	// ================= range round bands ==============

	/**
	 * Like {@link #rangeRoundBands(double, double, double, double)}.
	 * <p>
	 *
	 * @param interval
	 *            a two-elements array
	 * @return this instance
	 */
	public OrdinalScale rangeRoundBands(JSObject interval) {
		JSObject result = call("rangeBands", interval);
		return new OrdinalScale(webEngine, result);
	}

	/**
	 * Like {@link #rangeRoundBands(double, double, double, double)}.
	 * <p>
	 *
	 * @param interval
	 *            a two-elements array
	 * @param padding
	 *            the padding
	 * @return this instance
	 */
	public OrdinalScale rangeRoundBands(Double[] interval, double padding) {
		JSObject result = call("rangeBands", interval, padding);
		return new OrdinalScale(webEngine, result);
	}

	/**
	 * Like {@link #rangeRoundBands(double, double, double, double)}.
	 * <p>
	 *
	 * @param interval
	 *            a two-elements array
	 * @param padding
	 *            the padding
	 * @param outerPadding
	 *            the outer padding
	 * @return this instance
	 */
	public OrdinalScale rangeRoundBands(JSObject interval, double padding, double outerPadding) {
		JSObject result = call("rangeBands", interval, padding, outerPadding);
		return new OrdinalScale(webEngine, result);
	}

	/**
     * Like {@link #rangeBands(double, double, double,double)}, except
     * guarantees that the band width and offset are integer values, so as to
     * avoid antialiasing artifacts.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding to be set to between each band, between 0 and 1
     * @param outerPadding
     *            the padding around the whole group of bands.
     * @return this instance
     */
    public  OrdinalScale rangeRoundBands(double start, double end,
            double padding, double outerPadding){
    	String command = "this.rangeRoundBands([ "+start+", "+end+" ], "+padding+", "+outerPadding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * Like {@link #rangeBands(double, double, double)}, except guarantees that
     * the band width and offset are integer values, so as to avoid antialiasing
     * artifacts.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @param padding
     *            the padding to be set to between each band, between 0 and 1
     * @return this instance
     */
    public  OrdinalScale rangeRoundBands(double start, double end,
            double padding){		
		String command = "this.rangeRoundBands([ "+start+", "+end+" ], "+padding+");";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
     * Like {@link #rangeBands(double, double, double, double)}, except
     * guarantees that the band width and offset are integer values, so as to
     * avoid antialiasing artifacts.
     * <p>
     *
     * @param start
     *            the start bound of the interval
     * @param end
     *            the end bound of the interval
     * @return this instance
     */
    public  OrdinalScale rangeRoundBands(double start, double end){
    	String command = "this.rangeRoundBands([ "+start+", "+end+" ]);";
    	JSObject result = evalForJsObject(command);
    	return new OrdinalScale(webEngine, result);
    }

	/**
	 * Returns the band width.
	 * <p>
	 * When the scale’s range is configured with
	 * {@link #rangeBands(double, double, double, double)} or
	 * {@link #rangeRoundBands(double, double, double, double)}, the scale
	 * returns the lower value for the given input.
	 * <p>
	 * The upper value can then be computed by offsetting by the band width.
	 * <p>
	 * If the scale’s range is set using {@link #range()} or
	 * {@link #rangePoints(double, double, double)}, the band width is zero.
	 *
	 * @return the band width
	 */
	public double rangeBand() {
		Double result = callForDouble("rangeBand");
		return result;		
	}

	/**
	 * Returns a two-element array representing the extent of the scale's range,
	 * i.e., the smallest and largest values.
	 * <p>
	 *
	 * @return the extent of the scale's range
	 */
	public Array<Double> rangeExtent() {
		JSObject result = call("rangeExtent");
		return new Array<Double>(webEngine, result);		
	}

	@Override
	public OrdinalScale createScale(WebEngine webEngine, JSObject result) {
		return new OrdinalScale(webEngine, result);		
	}
	
	//#end region

}
