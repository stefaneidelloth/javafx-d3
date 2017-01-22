package org.treez.javafxd3.d3.selection.datafunction;

import static org.junit.Assert.assertNull;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A datum function that asserts that the datum equals an increasing counting
 * index, starting with 1.
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
	public AssertNullStringDataFunction(JsEngine engine) {
		this.engine = engine;
	}

	//#end region

	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		Integer value = ConversionUtil.convertObjectTo(datum, Integer.class, engine);
		assertNull(value);
		return null;

	}

	//#end region

}
