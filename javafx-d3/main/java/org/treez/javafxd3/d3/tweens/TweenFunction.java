package org.treez.javafxd3.d3.tweens;

import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.interpolators.Interpolator;

import org.treez.javafxd3.d3.core.JsObject;

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
	 * Returns a java script interpolation method interpolate(doubleValue) as JsObject
	 */
	JsObject apply(Object context, Object datum, int index, Object value);
}
