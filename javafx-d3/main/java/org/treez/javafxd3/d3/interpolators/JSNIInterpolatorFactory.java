package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * An interpolator factory returned by JSNI.
 * <p>
 * This class is used by {@link D3} to allow java code to invoke built-in interpolator factories. You should not instanciate this object
 * unless you know what you are doing.
 * <p>
 * 
 * 
 * @param <O> 
 * 
 */
public class JSNIInterpolatorFactory<O> extends JavaScriptObject implements InterpolatorFactory<O> {

	//#region CONSTRUCTORS	

	public JSNIInterpolatorFactory(JsEngine engine) {
		super(engine);
	}
	
	public JSNIInterpolatorFactory(JsEngine engine, JsObject jsObject) {
		super(engine, jsObject);
	}
	
	//#end region

	//#region METHODS
	@Override
	public final JsObject asJSOFunction() {
		JsObject result = this.getJsObject();
		return result;
	}

	@Override
	public final <I> Interpolator<O> create(Object a, Object b) {
		return new JavascriptFunctionInterpolatorDecorator<O>(createInterpolator(a, b)) {
			@Override
			public O cast(final Value v) {
				return v.as();
			}
		};
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public  <I> JavascriptFunctionInterpolator createInterpolator(Object a, Object b){
		JsObject result = callThisForJsObject(a, b);
		return new JavascriptFunctionInterpolator(engine, result);		
	}
	
	//#end region

}
