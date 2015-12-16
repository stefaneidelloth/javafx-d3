package org.treez.javafxd3.d3.transition.function;

import org.treez.javafxd3.d3.ease.EasingFunction;

public class CustomEasingFunction implements EasingFunction {

	@Override
	public double ease(final double t) {
		return t;
	}

}
