package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.interpolators.CallableInterpolator;

public class TestCallableInterpolator extends CallableInterpolator<String>{

	@Override
	public String interpolate(final double t) {
		return (int) (t * 10) + "px";
	}
	

}
