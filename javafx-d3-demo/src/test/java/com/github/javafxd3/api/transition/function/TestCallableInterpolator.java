package com.github.javafxd3.api.transition.function;

import com.github.javafxd3.api.interpolators.CallableInterpolator;

public class TestCallableInterpolator extends CallableInterpolator<String>{

	@Override
	public String interpolate(final double t) {
		return (int) (t * 10) + "px";
	}
	

}
