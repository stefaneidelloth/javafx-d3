package org.treez.javafxd3.d3.interpolators;

import netscape.javascript.JSObject;

/**
 * Use this class as a base class to create {@link InterpolatorFactory} implementations that can be passed to  D3#interpolators()
 * D3.interpolators().push().
 * <p>
 * 
 * 
 * 
 * 
 */
public abstract class AbstractInterpolatorFactory<O> implements InterpolatorFactory<O> {

	@Override
	public abstract <I> Interpolator<O> create(final I a, final I b);

	@Override
	public  JSObject asJSOFunction(){
		throw new IllegalStateException("not yet implemented");
		/*
		return function(a, b) {
			return this.@com.github.gwtd3.api.interpolators.AbstractInterpolatorFactory::create(Ljava/lang/Object;Ljava/lang/Object;)();
		}
		*/
		
	}
}
