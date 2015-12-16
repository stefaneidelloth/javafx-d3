package org.treez.javafxd3.d3.svg.datumfunction;

import java.util.List;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the x coordinate as double
 *  
 */
public class XCaptureDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private List<Double> xList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public XCaptureDatumFunction(WebEngine webEngine,  List<Double> xList){
		this.webEngine=webEngine;
		this.xList = xList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);					
	
		Double x = value.asCoords().x();
		xList.add(x);
		return x;
	}
	
	//#end region

}
