package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertStringDataFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;	
	
	private String expectedValue;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public AssertStringDataFunction(JsEngine engine, String expectedValue){
		this.engine=engine;
		this.expectedValue=expectedValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		String value = ConversionUtil.convertObjectTo(datum,  String.class, engine);
		assertEquals(expectedValue, value);
		return null;
	}
	
	//#end region

}
