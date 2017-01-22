package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;



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
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);
		String stringValue = value.asString();
		assertEquals(expectedValue, stringValue);
		return null;
	}
	
	//#end region

}
