package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class AssertOddEvenDataFunction implements DataFunction<Void> {

	//#region ATTRIBUTES

	private static final double DELTA = 1e-6;

	private JsEngine engine;
	private double oddValue;
	private double evenValue;

	//#end region

	//#region CONSTRUCTORS

	public AssertOddEvenDataFunction(JsEngine engine, double oddValue, double evenValue) {
		this.engine = engine;
		this.oddValue = oddValue;
		this.evenValue = evenValue;
	}

	//#end region

	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		double value = ConversionUtil.convertObjectTo(datum, Double.class, engine);
		Double expected = ((index % 2) == 0) ? oddValue : evenValue;
		assertEquals(expected, value, DELTA);
		return null;
	}

	//#end region

}
