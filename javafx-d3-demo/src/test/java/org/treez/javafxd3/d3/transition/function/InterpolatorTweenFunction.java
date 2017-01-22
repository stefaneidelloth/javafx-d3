package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.tweens.TweenFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class InterpolatorTweenFunction extends JavaScriptObject implements TweenFunction<String>{

	public InterpolatorTweenFunction(JsEngine engine){
		super(engine);
	}
	
	
	@Override
	public JsObject apply(final Object context,
			final Object datum, final int index, final Object value) {
		
		Interpolator<String> interpolator = new TestCallableInterpolator(engine);
		return interpolator.asJsFunction();
	}
	
}
