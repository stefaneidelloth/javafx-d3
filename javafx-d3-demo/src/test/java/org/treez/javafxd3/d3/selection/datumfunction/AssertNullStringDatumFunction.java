package org.treez.javafxd3.d3.selection.datumfunction;

import static org.junit.Assert.assertNull;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

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
