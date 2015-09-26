package com.github.javafxd3.api.tweens;

import com.github.javafxd3.api.core.Transition;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.interpolators.Interpolator;
import com.github.javafxd3.api.wrapper.Element;

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
