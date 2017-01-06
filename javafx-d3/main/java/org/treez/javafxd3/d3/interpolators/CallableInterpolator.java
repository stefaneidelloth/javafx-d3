package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * An interpolator that can be passed to JSNI.
 * <p>
 * This is useful when you want to create an implementation of
 * {@link Interpolator} in Java that must be used in JSNI side.
 * <p>
 * 
 * 
 * 
 * @param <T>
 *            the type to be interpolated
 */
public abstract class CallableInterpolator<T> extends JavaScriptObject implements Interpolator<T> {

	public CallableInterpolator(WebEngine webEngine) {
		super(webEngine);
	}

	@Override
	public abstract T interpolate(Object t);

	@Override
	public JSObject asJSOFunction() {
		
		assertObjectIsNotAnonymous(this);

		String interpolatorName = createNewTemporaryInstanceName();
		String jsInterpolatorName = createNewTemporaryInstanceName();

		JSObject d3JsObject = getD3();
		d3JsObject.setMember(interpolatorName, this);

		String command = "this." + jsInterpolatorName + " = function(t) { " + //
				//"   alert('callableInterpolator with t:' + t);" +//				
				"   var result =  d3." + interpolatorName + ".interpolate(t);" + //
				//"   alert('callableInterpolator result: ' + result);" + //
				"   return result;" + //
				"};";
		d3JsObject.eval(command);

		JSObject result = (JSObject) d3JsObject.eval("this." + jsInterpolatorName);
		return result;

	}

}
