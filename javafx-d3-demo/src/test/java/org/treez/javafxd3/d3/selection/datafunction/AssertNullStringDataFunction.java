package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertNull;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertNullStringDataFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public AssertNullStringDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);
		assertNull(value.asString());
		return null;
	}
	
	//#end region

}
