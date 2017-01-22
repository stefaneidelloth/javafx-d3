package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;




public class AssertOddEvenDataFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
		
	private static final double DELTA = 1e-6;
	
	private JsEngine engine;
	private double oddValue;
	private double evenValue;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public AssertOddEvenDataFunction(JsEngine engine, double oddValue, double evenValue){
		this.engine = engine;
		this.oddValue = oddValue;
		this.evenValue = evenValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);
		
		Double expected = ((index % 2) == 0) ? oddValue : evenValue;
		Double actual = value.asDouble();
		assertEquals(expected, actual, DELTA);
		return null;
	}
	
	//#end region

}
