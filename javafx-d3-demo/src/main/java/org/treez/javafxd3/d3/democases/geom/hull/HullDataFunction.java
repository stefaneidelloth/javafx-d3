package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullDataFunction implements DataFunction<String> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	//#end region

	//#region CONSTRUCTORS

	public HullDataFunction(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	//#end region

	//#region METHODS

	@Override
	public String apply(final Object context, final Object d, final int i) {

		
		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);

		
		JSObject jsCoordsList = value.as();
		
		//Array<MyCoords> coordsList = value.<Array<MyCoords>> as();
		Array<HullCoords> coordsList = new Array<>(webEngine, jsCoordsList);
				
		int size = coordsList.sizes().get(1);
				
		List<String> coordsStringList = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			JSObject jsCoords = coordsList.get(index, JSObject.class);
			HullCoords coords = new HullCoords(webEngine, jsCoords);
			String coordsString = coords.toString();
			coordsStringList.add(coordsString);
		}
		String coordsString = String.join("L", coordsStringList);
		String result = "M" + coordsString + "Z";
		return result;

	}

	//#end region

}
