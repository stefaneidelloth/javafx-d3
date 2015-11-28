package com.github.javafxd3.d3.tweens;

import com.github.javafxd3.d3.core.Transition;
import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.interpolators.Interpolator;
import com.github.javafxd3.d3.wrapper.Element;

/**
 * A function returning an {@link Interpolator} used to tween elements attribute or styles within {@link Transition}s.
 * <p>
 * @see <a href="https:attrTween">Official attrTween function</a>
 * 
 * @param <T> 
 * 
 */
public interface TweenFunction<T> {

	/**
	 * @param context
	 * @param datum
	 * @param index
	 * @param value 
	 * @param attributeValue
	 * @return
	 */
	Interpolator<T> apply(Element context, Value datum, int index, Value value);
}
