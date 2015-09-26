package com.github.javafxd3.api.interpolators;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


/**
 * An interpolator used when the interpolation function is provided by JSNI.
 * <p>
 * This class is used by {@link D3} to allow java code to invoke built-in
 * interpolators. You should not instanciate this object unless you know what
 * you are doing.
 * <p>
 * 
 * 
 * 
 */
public class JavascriptFunctionInterpolator extends JavaScriptObject implements Interpolator<Value> {

	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param webEngine
	 */
	public JavascriptFunctionInterpolator(WebEngine webEngine) {
		super(webEngine);
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public  Value interpolate(final double t){
		throw new IllegalStateException("not yet implemented");
		//return {
		//	datum : this(t)
		//};
	}

	@Override
	public final JSObject asJSOFunction() {
		return this.getJsObject();
	}
	
	//#end region

}
