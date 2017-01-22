package org.treez.javafxd3.d3.coords;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Coords extends JavaScriptObject {

	//#region CONSTRUCTORS
	
	public Coords(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	public Coords(JsEngine engine, Double x, Double y) {
		super(engine);
	
		JsObject d3 = (JsObject) engine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();
		
		String command = "var " + varName +" = {x:" + x + ",y:" + y + "};";
		d3.eval(command);
		Object resultObj = d3.eval(varName);
		JsObject result = (JsObject) resultObj;
		
		d3.eval(varName + " = undefined;");
		
		setJsObject(result);
	}

	public Coords(JsEngine engine, int x, int y) {
		this(engine, (double) x, (double) y);
	}	

	//#end region

	//#region METHODS

	/**
	 * Convenient {@link DataFunction} that return the x component of a
	 * {@link Coords} datum.
	 */
	public static final DataFunction<Double> getXAccessor(JsEngine engine){
		DataFunction<Double> accessor = new DataFunctionWrapper<>(Coords.class, engine, (coords)->{
			return coords.x();
		}); 
		return accessor;
	}

	/**
	 * Convenient {@link DataFunction} that return the y component of a
	 * {@link Coords} datum.
	 */
	public static final DataFunction<Double> getYAccessor(JsEngine engine){
		DataFunction<Double> accessor = new DataFunctionWrapper<>(Coords.class, engine, (coords)->{
			return coords.y();
		}); 
		return accessor;
	}
	
	public String toCommaSeparatedString() {
		return x() + "," + y();
	}
	
	//#end region
	
	//#region ACCESSORS

	public double x() {
		return getMemberForDouble("x");
	}

	public double y() {
		return getMemberForDouble("y");
	}

	public Coords x(double x) {
		setMember("x", x);
		return this;
	}

	public Coords y(double y) {
		setMember("y", y);
		return this;
	}

	//#end region
}
