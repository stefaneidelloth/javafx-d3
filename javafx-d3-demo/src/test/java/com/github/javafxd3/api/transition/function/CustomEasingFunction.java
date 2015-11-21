package com.github.javafxd3.api.transition.function;

import com.github.javafxd3.api.ease.EasingFunction;

public class CustomEasingFunction implements EasingFunction {

	@Override
	public double ease(final double t) {
		return t;
	}

}
