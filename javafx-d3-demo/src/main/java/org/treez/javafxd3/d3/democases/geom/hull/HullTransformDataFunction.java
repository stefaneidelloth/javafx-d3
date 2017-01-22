package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class HullTransformDataFunction implements DataFunction<String> {

	//#region ATTRIBUTES

	private JsEngine engine;

	//#end region

	//#region CONSTRUCTORS

	public HullTransformDataFunction(JsEngine engine) {
		this.engine = engine;
	}

	//#end region

	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {

		try {
			HullCoords coords = ConversionUtil.convertObjectTo(datum, HullCoords.class, engine);
			String result = "translate(" + coords.toString() + ")";
			return result;
		} catch (Exception exception) {
			return "translate(0,0)";
		}

	}

	//#end region

}
