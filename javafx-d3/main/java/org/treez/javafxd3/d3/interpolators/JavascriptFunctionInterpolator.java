package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


/**
 * An interpolator used when the interpolation function is provided by JSNI.
 * <p>
 * This class is used by {@link D3} to allow java code to invoke built-in
 * interpolators. You should not instanciate this object unless you know what
 * you are doing.
 * <p>  
 */
public class JavascriptFunctionInterpolator extends JavaScriptObject implements Interpolator<Value> {

	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param webEngine
	 */
	public JavascriptFunctionInterpolator(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public  Value interpolate(final double t){
		String command = "{datum : this("+t+")};";
		JSObject result = evalForJsObject(command);
		return new Value(webEngine, result);
	}

	@Override
	public final JSObject asJSOFunction() {
		return this.getJsObject();
	}
	
	//#end region

}
