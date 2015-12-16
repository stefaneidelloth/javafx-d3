package org.treez.javafxd3.d3.scales;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Threshold scales are similar to quantize scales, except they allow you to map
 * arbitrary subsets of the domain to discrete values in the range.
 * <p>
 * The input domain is still continuous, and divided into slices based on a set
 * of threshold values.
 * <p>
 * The input domain is typically a dimension of the data that you want to
 * visualize, such as the height of students (measured in meters) in a sample
 * population.
 * <p>
 * The output range is typically a dimension of the desired output
 * visualization, such as a set of colors (represented as strings).
 * <p>
 * A new threshold scale has the default domain [.5] and the default range
 * [0,1]. Thus, the default thresold scale is equivalent to the round function
 * for numbers; for example threshold(0.49) returns 0, and threshold(0.51)
 * returns 1.
 * <p>
 * The values in the domain array must be in sorted ascending order, or the
 * behavior of the scale is undefined. The values are typically numbers, but any
 * naturally ordered values (such as strings) will work. Thus, a threshold scale
 * can be used to encode any type that is ordered. If the number of values in
 * the scale's range is N + 1, the number of values in the scale's domain must
 * be N. If there are fewer than N elements in the domain, the additional values
 * in the range are ignored. If there are more than N elements in the domain,
 * the scale may return undefined for some inputs.
 * <p>
 * The elements in the output range array need not be numbers; any value or type
 * will work.
 * <p> 
 */
public class ThresholdScale extends DiscreteQuantitativeScale<ThresholdScale> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public ThresholdScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}	

	//#end region
	
	//#region METHODS
	
	@Override
	public ThresholdScale createScale(WebEngine webEngine, JSObject result) {
		return new ThresholdScale(webEngine, result);
	}
	
	//#end region

}
