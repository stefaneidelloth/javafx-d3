package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.core.Value;

import netscape.javascript.JSObject;

/**
 * 
 * @param <T>
 */
public class JavascriptFunctionInterpolatorDecorator<T> implements Interpolator<T> {
	
	//#region ATTRIBUTES

    protected JavascriptFunctionInterpolator delegate;
    
    //#end region
    
    //#region CONSTRUCTORS

    /**
     * Constructor
     * @param delegate
     */
    public JavascriptFunctionInterpolatorDecorator(final JavascriptFunctionInterpolator delegate) {      
        this.delegate = delegate;
    }
    
    //#end region
    
    //#region METHODS

    @Override
    public JSObject asJSOFunction() {
        return delegate.asJSOFunction();
    }

    @Override
    public T interpolate(final double t) {
        return cast(delegate.interpolate(t));
    }

    /**
     * Cast the given value to the correct type.
     * @param v
     * @return
     */
    public T cast(final Value v) {
        return v.as();
    }
    
    //#end region
}
