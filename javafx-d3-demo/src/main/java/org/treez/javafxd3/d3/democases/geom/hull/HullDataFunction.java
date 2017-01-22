package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class HullDataFunction implements DataFunction<String> {

	//#region ATTRIBUTES

	private JsEngine engine;

	//#end region

	//#region CONSTRUCTORS

	public HullDataFunction(JsEngine engine) {
		this.engine = engine;
	}

	//#end region

	//#region METHODS

	@Override
	public String apply(final Object context, final Object d, final int i) {

		
		JsObject datum = (JsObject) engine.toJsObjectIfNotSimpleType(d);
		Value value = new Value(engine, datum);

		
		JsObject jsCoordsList = value.as();
		
		//Array<MyCoords> coordsList = value.<Array<MyCoords>> as();
		Array<HullCoords> coordsList = new Array<>(engine, jsCoordsList);
				
		int size = coordsList.sizes().get(1);
				
		List<String> coordsStringList = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			JsObject jsCoords = coordsList.get(index, JsObject.class);
			HullCoords coords = new HullCoords(engine, jsCoords);
			String coordsString = coords.toString();
			coordsStringList.add(coordsString);
		}
		String coordsString = String.join("L", coordsStringList);
		String result = "M" + coordsString + "Z";
		return result;

	}

	//#end region

}
