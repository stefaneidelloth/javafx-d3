package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.arrays.Array;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * {@link QuantitativeScale} with a discrete output range.
 * <p> 
 * @param <S>
 */
public abstract class DiscreteQuantitativeScale<S extends DiscreteQuantitativeScale<S>> extends QuantitativeScale<S> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public DiscreteQuantitativeScale(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	/**
	 * Returns the extent of values in the input domain [x0, x1] for the
	 * corresponding value in the output range y, representing the inverse
	 * mapping from range to domain.
	 * <p>
	 * This method is useful for interaction, say to determine the value in the
	 * input domain that corresponds to the pixel location under the mouse.
	 * <p>
	 * 
	 * @param y
	 *            the output value to be converted to a domain range
	 * @return the array of doubles
	 */
	public Array<Double> invertExtent(double y) {
		JsObject result = call("invertExtent", y);
		return new Array<Double>(engine, result);		
	}

	/**
	 * Returns the extent of values in the input domain [x0, x1] for the
	 * corresponding value in the output range y, representing the inverse
	 * mapping from range to domain.
	 * <p>
	 * This method is useful for interaction, say to determine the value in the
	 * input domain that corresponds to the pixel location under the mouse.
	 * <p>
	 * 
	 * @param y
	 *            the output value to be converted to a domain range
	 * @return the array of doubles
	 */
	public Array<Double> invertExtent(String y) {
		JsObject result = call("invertExtent", y);
		return new Array<Double>(engine, result);
	}

}
