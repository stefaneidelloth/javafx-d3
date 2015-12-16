package org.treez.javafxd3.d3.selection.datumfunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertCounterDatumFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private int counter = 1;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertCounterDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		Value value = Value.create(webEngine,  datum);
		assertEquals(counter, (int) value.asInt());
		counter++;
		return null;
	}
	
	//#end region

}
