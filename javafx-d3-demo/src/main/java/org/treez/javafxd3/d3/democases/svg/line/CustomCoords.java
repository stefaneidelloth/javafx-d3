package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public  class CustomCoords extends Coords {

	//#region ATTRIBUTES

	boolean defined;

	//#end region

	//#region CONSTRUCTORS

	public CustomCoords(JsEngine engine, double x, double y, boolean defined) {
		super(engine, x, y);
		this.defined = defined;		
		JsObject wrappedJsObject = createJsCoords(x, y, defined);			
		setJsObject(wrappedJsObject);
		
	}
	
	public CustomCoords(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);		
		this.defined = getMemberForBoolean("defined");		
	}

	//#end region

	//#region METHODS
	
	public static DataFunction<Boolean> definedAccessor(JsEngine engine) {
		return new DefinedDataFunction(engine);
	}
	
	private JsObject createJsCoords(double x, double y, boolean defined) {
		JsObject d3 = (JsObject) engine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();
		
		String command = "var " + varName +" = {x:" + x + ",y:" + y + ", defined:" + defined + "};";
		d3.eval(command);
		Object resultObj = d3.eval(varName);
		JsObject result = (JsObject) resultObj;
		
		d3.eval(varName + " = undefined;");
		return result;
	}	
	
	public Boolean defined() {
		return defined;
	}

	//#end region

}
