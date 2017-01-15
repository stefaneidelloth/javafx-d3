package org.treez.javafxd3.d3.coords;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Coords extends JavaScriptObject {

	//#region CONSTRUCTORS
	
	public Coords(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	public Coords(WebEngine webEngine, Double x, Double y) {
		super(webEngine);
	
		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();
		
		String command = "var " + varName +" = {x:" + x + ",y:" + y + "};";
		d3.eval(command);
		Object resultObj = d3.eval(varName);
		JSObject result = (JSObject) resultObj;
		
		d3.eval(varName + " = undefined;");
		
		setJsObject(result);
	}

	public Coords(WebEngine webEngine, int x, int y) {
		this(webEngine, (double) x, (double) y);
	}	

	//#end region

	//#region METHODS

	/**
	 * Convenient {@link DataFunction} that return the x component of a
	 * {@link Coords} datum.
	 */
	public static final DataFunction<Double> getXAccessor(WebEngine webEngine){
		DataFunction<Double> accessor = new DataFunctionWrapper<>(Coords.class, webEngine, (coords)->{
			return coords.x();
		}); 
		return accessor;
	}

	/**
	 * Convenient {@link DataFunction} that return the y component of a
	 * {@link Coords} datum.
	 */
	public static final DataFunction<Double> getYAccessor(WebEngine webEngine){
		DataFunction<Double> accessor = new DataFunctionWrapper<>(Coords.class, webEngine, (coords)->{
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
