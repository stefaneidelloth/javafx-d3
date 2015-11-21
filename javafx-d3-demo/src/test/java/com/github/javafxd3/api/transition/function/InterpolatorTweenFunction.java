package com.github.javafxd3.api.transition.function;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.interpolators.Interpolator;
import com.github.javafxd3.api.tweens.TweenFunction;
import com.github.javafxd3.api.wrapper.Element;

public class InterpolatorTweenFunction implements TweenFunction<String>{

	
	@Override
	public Interpolator<String> apply(final Element context,
			final Value datum, final int index, final Value value) {
		
		Interpolator<String> interpolator = new TestCallableInterpolator();
		return interpolator;
	}
	
}
