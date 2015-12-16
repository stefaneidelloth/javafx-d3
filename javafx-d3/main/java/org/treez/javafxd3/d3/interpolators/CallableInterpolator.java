package org.treez.javafxd3.d3.interpolators;

import netscape.javascript.JSObject;

/**
 * An interpolator that can be passed to JSNI.
 * <p>
 * This is useful when you want to create an implementation of {@link Interpolator} in Java that must be used in JSNI side.
 * <p>
 * 
 * 
 * 
 * @param <T>
 *            the type to be interpolated
 */
public abstract class CallableInterpolator<T> implements Interpolator<T> {

	@Override
	public abstract T interpolate(double t);

	@Override
	public  JSObject asJSOFunction() {
		throw new IllegalStateException("not yet implemented");
    	/*
		var self = this;
		return function(t) {
			return self.@com.github.gwtd3.api.interpolators.CallableInterpolator::interpolate(D)(t);
		}
		*/
	}

}
