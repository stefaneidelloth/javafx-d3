package com.github.javafxd3.api.scales;


import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * {@link QuantitativeScale} with a discrete output range.
 * <p>
 * 
 * 
 * 
 * @param <S>
 */
public abstract class DiscreteQuantitativeScale<S extends DiscreteQuantitativeScale<S>>
		extends QuantitativeScale<S> {

	// #region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param webEngine
		 * @param wrappedJsObject
		 */
		public DiscreteQuantitativeScale(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine, wrappedJsObject);

		}

		// #end region

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
	public  Double[] invertExtent(double y){
		throw new IllegalStateException("not yet implemented");
		//return this.invertExtent(y);
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
	public  Double[] invertExtent(String y){
		throw new IllegalStateException("not yet implemented");
		//return this.invertExtent(y);
	}

}
