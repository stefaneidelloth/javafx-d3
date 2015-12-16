package org.treez.javafxd3.d3.svg.datumfunction;

import java.util.List;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the x coordinate as double
 *  
 */
public class YCoordsCounterDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private List<Double> yList;
	
	private Coords counter;
	
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public YCoordsCounterDatumFunction(WebEngine webEngine,  List<Double> yList, Coords counter){
		this.webEngine=webEngine;
		this.yList = yList;
		this.counter = counter;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);					
	
		counter.y(counter.y() + 1);
		
		Double y = value.asCoords().y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
