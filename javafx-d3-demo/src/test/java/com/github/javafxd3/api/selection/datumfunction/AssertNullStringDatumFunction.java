package com.github.javafxd3.api.selection.datumfunction;

import static org.junit.Assert.assertNull;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertNullStringDatumFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertNullStringDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		assertNull(value.asString());
		return null;
	}
	
	//#end region

}
