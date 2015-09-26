package com.github.javafxd3.api.coords;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A Javascript object containing x and y properties.
 * 
 * 
 * 
 */
public class Coords extends JavaScriptObject {

	// #region ATTRIBUTES

	private Double x;

	private Double y;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * Constructor
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
		String command = "var temp__result__object = {x:"+x+",y:"+y+"};";
		d3.eval(command);
		Object resultObj = d3.eval("temp__result__object");
		JSObject result = (JSObject) resultObj;		
		setJsObject(result);		
	}
	
	/**
	 * Constructor
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

	// #end region

	// #region METHODS
	
	/**
	 * Convenient {@link DatumFunction} that return the x component of a
	 * {@link Coords} datum.
	 */
	public static final DatumFunction<Double> X_ACCESSOR = new DatumFunction<Double>(){
		
		@Override public Double apply(Element context, Value d, int index){
			return d.<Coords>as().x();
		}
	};

	/**
	 * Convenient {@link DatumFunction} that return the y component of a
	 * {@link Coords} datum.
	 */
	public static final DatumFunction<Double> Y_ACCESSOR=new DatumFunction<Double>(){
		
		@Override public Double apply(Element context,Value d,int index){
			return d.<Coords>as().y();
			}
	};

	/**
	 * @return the x coordinates
	 */
	public  double x(){
		return x;
	}

	/**
	 * @return the y coordinates
	 */
	public  double y(){
		return y;
	}

	/**
	 * set the x coords
	 * @param x 
	 * 
	 * @return the x coordinates
	 */
	public  Coords x(double x){
		this.x = x;	
		return this;
	}

	/**
	 * set the y coords
	 * 
	 * @param y
	 * @return
	 */

	public Coords y(double y){
		this.y = y;
		return this;
	}

	/**
	 * @return x and y comma-separated string
	 */

	public  String toCommaSeparatedString(){
		return this.x + "," + this.y;
	}
	
	
	
	// #end region
}
