package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.Inspector;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public  class CustomCoords extends Coords {

	//#region ATTRIBUTES

	boolean defined;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * 
	 * @param x
	 * @param y
	 * @param defined
	 */
	public CustomCoords(WebEngine webEngine, double x, double y, boolean defined) {
		super(webEngine, x, y);
		this.defined = defined;
		
		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();
		
		String command = "var " + varName +" = {x:" + x + ",y:" + y + ", defined:" + defined + "};";
		d3.eval(command);
		Object resultObj = d3.eval(varName);
		JSObject result = (JSObject) resultObj;
		
		d3.eval(varName + " = null;");
		
		//Inspector.inspect(result);
		
		setJsObject(result);
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 * 
	 */
	public CustomCoords(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);		
		this.defined = getMemberForBoolean("defined");
		
	}

	//#end region

	//#region METHODS

	public static DatumFunction<Double> xAccessor(WebEngine webEngine) {
		return new XAccessorDatumFunction(webEngine);
	}

	public static DatumFunction<Double> yAccessor(WebEngine webEngine) {
		return new YAccessorDatumFunction(webEngine);
	}

	public static DatumFunction<Boolean> definedAccessor(WebEngine webEngine) {
		return new DefinedDatumFunction(webEngine);
	}
	
	/**
	 * @return the y coordinates
	 */
	public Boolean defined() {
		return defined;
	}

	//#end region

}
