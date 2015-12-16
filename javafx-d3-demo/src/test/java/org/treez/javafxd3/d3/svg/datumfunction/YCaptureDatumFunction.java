package org.treez.javafxd3.d3.svg.datumfunction;

import java.util.List;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the y coordinate as double
 *  
 */
public class YCaptureDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private List<Double> yList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public YCaptureDatumFunction(WebEngine webEngine,  List<Double> yList){
		this.webEngine=webEngine;
		this.yList = yList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);					
	
		Double y = value.asCoords().y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
