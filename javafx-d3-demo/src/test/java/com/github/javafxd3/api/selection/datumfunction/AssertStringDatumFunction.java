package com.github.javafxd3.api.selection.datumfunction;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertStringDatumFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;	
	
	private String expectedValue;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertStringDatumFunction(WebEngine webEngine, String expectedValue){
		this.webEngine=webEngine;
		this.expectedValue=expectedValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		String stringValue = value.asString();
		assertEquals(expectedValue, stringValue);
		return null;
	}
	
	//#end region

}
