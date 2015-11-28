package com.github.javafxd3.d3.transition.function;

import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.interpolators.Interpolator;
import com.github.javafxd3.d3.tweens.TweenFunction;
import com.github.javafxd3.d3.wrapper.Element;

public class InterpolatorTweenFunction implements TweenFunction<String>{

	
	@Override
	public Interpolator<String> apply(final Element context,
			final Value datum, final int index, final Value value) {
		
		Interpolator<String> interpolator = new TestCallableInterpolator();
		return interpolator;
	}
	
}
