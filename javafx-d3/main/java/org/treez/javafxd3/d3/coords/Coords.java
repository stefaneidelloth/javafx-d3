package org.treez.javafxd3.d3.coords;

import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A Javascript object containing x and y coordinates.
 */
public class Coords extends JavaScriptObject {

	//#region ATTRIBUTES

	private Double x;

	private Double y;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * 
	 * @param x
	 * @param y
	 */
	public Coords(WebEngine webEngine, Double x, Double y) {
		super(webEngine);
		this.x = x;
		this.y = y;

		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();
		
		String command = "var " + varName +" = {x:" + x + ",y:" + y + "};";
		d3.eval(command);
		Object resultObj = d3.eval(varName);
		JSObject result = (JSObject) resultObj;
		
		d3.eval(varName + " = null;");
		
		setJsObject(result);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * 
	 * @param x
	 * @param y
	 */
	public Coords(WebEngine webEngine, int x, int y) {
		this(webEngine, (double) x, (double) y);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 * 
	 */
	public Coords(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
				
		setJsObject(wrappedJsObject);
		this.x = getMemberForDouble("x");
		this.y = getMemberForDouble("y");
	}

	//#end region

	//#region METHODS

	/**
	 * Convenient {@link DatumFunction} that return the x component of a
	 * {@link Coords} datum.
	 */
	public static final DatumFunction<Double> getXAccessor(WebEngine webEngine){
		DatumFunction<Double> accessor = new XAccessorDatumFunction(webEngine); 
		return accessor;
	}

	/**
	 * Convenient {@link DatumFunction} that return the y component of a
	 * {@link Coords} datum.
	 */
	public static final DatumFunction<Double> getYAccessor(WebEngine webEngine){
		DatumFunction<Double> accessor = new YAccessorDatumFunction(webEngine); 
		return accessor;
	}

	/**
	 * @return the x coordinates
	 */
	public double x() {
		return x;
	}

	/**
	 * @return the y coordinates
	 */
	public double y() {
		return y;
	}

	/**
	 * set the x coords
	 * 
	 * @param x
	 * 
	 * @return the x coordinates
	 */
	public Coords x(double x) {
		this.x = x;
		return this;
	}

	/**
	 * set the y coords
	 * 
	 * @param y
	 * @return
	 */

	public Coords y(double y) {
		this.y = y;
		return this;
	}

	/**
	 * @return x and y comma-separated string
	 */

	public String toCommaSeparatedString() {
		return this.x + "," + this.y;
	}

	//#end region
}
