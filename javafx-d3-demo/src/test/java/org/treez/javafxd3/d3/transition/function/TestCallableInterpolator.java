package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.interpolators.CallableInterpolator;

import org.treez.javafxd3.d3.core.JsEngine;

public class TestCallableInterpolator extends CallableInterpolator<String>{

	public TestCallableInterpolator(JsEngine engine){
		super(engine);
	}
	
	@Override
	public String interpolate(Object t) {
		return (int) ((Double)t * 10) + "px";
	}
	

}
