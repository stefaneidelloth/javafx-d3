package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class AxisScaleSizeDataFunction implements DataFunction<Double> {

	//#region ATTRIBUTES

	private JsEngine engine;

	private Scale<?> scale;

	//#end region

	//#region CONSTRUCTORS

	public AxisScaleSizeDataFunction(JsEngine engine, Scale<?> scale) {
		this.engine = engine;
		this.scale = scale;
	}

	//#end region

	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {

		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);

		Double value = Double.parseDouble(jsObject.eval("this.value").toString());
		Double size = Double.parseDouble(jsObject.eval("this.size").toString());

		Double scaledRightValueInPx = scale.applyForDouble("" + (value + size));
		Double scaledLeftValueInPx = scale.applyForDouble("" + value);
		Double sizeInPx = scaledRightValueInPx - scaledLeftValueInPx;
		return sizeInPx;
	}

	//#end region

}
