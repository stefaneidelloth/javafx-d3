package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Quantitative scales have a continuous domain:
 * <ul>
 * <li>{@link ContinuousQuantitativeScale} have a continuous output range
 * <li>{@link DiscreteQuantitativeScale} have a discrete output range
 * </ul> 
 */
public abstract class QuantitativeScale<S extends QuantitativeScale<?>> extends Scale<S> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public QuantitativeScale(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

}
