package org.treez.javafxd3.d3.selection.datumfunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;




public class AssertOddEvenDatumFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
		
	private static final double DELTA = 1e-6;
	
	private WebEngine webEngine;
	private double oddValue;
	private double evenValue;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertOddEvenDatumFunction(WebEngine webEngine, double oddValue, double evenValue){
		this.webEngine = webEngine;
		this.oddValue = oddValue;
		this.evenValue = evenValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		
		Double expected = ((index % 2) == 0) ? oddValue : evenValue;
		Double actual = value.asDouble();
		assertEquals(expected, actual, DELTA);
		return null;
	}
	
	//#end region

}
