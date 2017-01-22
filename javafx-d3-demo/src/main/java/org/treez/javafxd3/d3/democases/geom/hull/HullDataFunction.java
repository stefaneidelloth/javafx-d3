package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;
import org.treez.javafxd3.d3.functions.DataFunction;

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
	public String apply(final Object context, final Object datum, final int i) {
		
		@SuppressWarnings("unchecked")
		Array<HullCoords> coordsList = (Array<HullCoords>) ConversionUtil.convertObjectTo(datum, Array.class, engine);
				
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
