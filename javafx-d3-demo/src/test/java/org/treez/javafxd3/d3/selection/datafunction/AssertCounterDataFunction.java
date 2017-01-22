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
public class AssertCounterDataFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private int counter = 1;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public AssertCounterDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JsObject jsDatum = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine,  jsDatum);
		assertEquals(counter, (int) value.asInt());
		counter++;
		return null;
	}
	
	//#end region

}
