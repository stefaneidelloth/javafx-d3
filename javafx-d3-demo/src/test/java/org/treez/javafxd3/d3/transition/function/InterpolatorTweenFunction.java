package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.tweens.TweenFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class InterpolatorTweenFunction extends JavaScriptObject implements TweenFunction<String>{

	public InterpolatorTweenFunction(WebEngine webEngine){
		super(webEngine);
	}
	
	
	@Override
	public JSObject apply(final Object context,
			final Object datum, final int index, final Object value) {
		
		Interpolator<String> interpolator = new TestCallableInterpolator(webEngine);
		return interpolator.asJSOFunction();
	}
	
}
