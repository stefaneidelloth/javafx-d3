package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.interpolators.CallableInterpolator;

import javafx.scene.web.WebEngine;

public class TestCallableInterpolator extends CallableInterpolator<String>{

	public TestCallableInterpolator(WebEngine webEngine){
		super(webEngine);
	}
	
	@Override
	public String interpolate(Object t) {
		return (int) ((Double)t * 10) + "px";
	}
	

}
