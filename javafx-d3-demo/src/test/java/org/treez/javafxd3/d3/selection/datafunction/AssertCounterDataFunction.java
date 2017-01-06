package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertCounterDataFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private int counter = 1;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertCounterDataFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsDatum = (JSObject) datum;
		Value value = new Value(webEngine,  jsDatum);
		assertEquals(counter, (int) value.asInt());
		counter++;
		return null;
	}
	
	//#end region

}
