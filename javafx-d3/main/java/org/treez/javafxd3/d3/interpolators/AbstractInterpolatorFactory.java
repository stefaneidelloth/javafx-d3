package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Use this class as a base class to create {@link InterpolatorFactory} implementations that can be passed to  D3#interpolators()
 * D3.interpolators().push().
 * <p>
 * 
 * 
 * 
 * 
 */
public abstract class AbstractInterpolatorFactory<O> extends JavaScriptObject implements InterpolatorFactory<O> {
	
	public AbstractInterpolatorFactory(WebEngine webEngine){
		super(webEngine);
	}
	

	@Override
	public abstract <I> Interpolator<O> create(final Object a, final Object b);

	@Override
	public  JSObject asJSOFunction(){	
		
		String interpolatorName = createNewTemporaryInstanceName();
		String jsInterpolatorName = createNewTemporaryInstanceName();
		
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(interpolatorName, this);

		String command = "this."+jsInterpolatorName+" = function(a, b) { return d3." + interpolatorName + ".create(a,b);};";
		d3JsObject.eval(command);		
		
		JSObject result = (JSObject) d3JsObject.eval("this." +jsInterpolatorName);
		
		
		
		return result;			
			
		
	}
}
