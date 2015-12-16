package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.democases.geom.hull.HullDemo.MyCoords;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullDatumFunction implements DatumFunction<String> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	//#end region

	//#region CONSTRUCTORS

	public HullDatumFunction(WebEngine webEngine) {
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
		Array<MyCoords> coordsList = new Array<MyCoords>(webEngine, jsCoordsList);
				
		int size = coordsList.sizes().get(1);
				
		List<String> coordsStringList = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			JSObject jsCoords = coordsList.get(index, JSObject.class);
			MyCoords coords = new MyCoords(webEngine, jsCoords);
			String coordsString = coords.toString();
			coordsStringList.add(coordsString);
		}
		String coordsString = String.join("L", coordsStringList);
		String result = "M" + coordsString + "Z";
		return result;

	}

	//#end region

}
