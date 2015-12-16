package org.treez.javafxd3.d3.scales;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Quantitative scales have a continuous domain:
 * <ul>
 * <li>{@link ContinuousQuantitativeScale} have a continuous output range
 * <li>{@link DiscreteQuantitativeScale} have a discrete output range
 * </ul> 
 */
public abstract class QuantitativeScale<S extends QuantitativeScale<S>> extends Scale<S> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public QuantitativeScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

}
