package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

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
	/**
	 * Constructor
	 * @param webEngine
	 */
	public JSNIInterpolatorFactory(WebEngine webEngine) {
		super(webEngine);
	}
	
	//#end region

	//#region METHODS
	@Override
	public final JSObject asJSOFunction() {
		JSObject result = this.getJsObject();
		return result;
	}

	@Override
	public final <I> Interpolator<O> create(final I a, final I b) {
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
	public  <I> JavascriptFunctionInterpolator createInterpolator(final I a, final I b){
		JSObject result = callThisForJsObject(a, b);
		return new JavascriptFunctionInterpolator(webEngine, result);		
	}
	
	//#end region

}
