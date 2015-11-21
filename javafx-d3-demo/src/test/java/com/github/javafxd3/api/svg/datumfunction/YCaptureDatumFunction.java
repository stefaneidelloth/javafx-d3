package com.github.javafxd3.api.svg.datumfunction;

import java.util.List;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

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
