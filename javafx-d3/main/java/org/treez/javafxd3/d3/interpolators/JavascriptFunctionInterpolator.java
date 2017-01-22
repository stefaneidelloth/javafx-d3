package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


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
	
	public JavascriptFunctionInterpolator(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public  Value interpolate(Object t){			
		Object interpolationResult = callThis(t);		
		return Value.create(engine, interpolationResult);
	}

	@Override
	public final JsObject asJsFunction() {
		return this.getJsObject();
	}
	
	//#end region

}
