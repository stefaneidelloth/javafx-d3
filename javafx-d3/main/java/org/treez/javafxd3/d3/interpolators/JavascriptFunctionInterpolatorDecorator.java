package org.treez.javafxd3.d3.interpolators;

import org.treez.javafxd3.d3.core.Value;

import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 * @param <T>
 */
public class JavascriptFunctionInterpolatorDecorator<T> implements Interpolator<T> {
	
	//#region ATTRIBUTES

    protected JavascriptFunctionInterpolator delegate;
    
    //#end region
    
    //#region CONSTRUCTORS


    public JavascriptFunctionInterpolatorDecorator(final JavascriptFunctionInterpolator delegate) {      
        this.delegate = delegate;
    }
    
    //#end region
    
    //#region METHODS

    @Override
    public JsObject asJsFunction() {
        return delegate.asJsFunction();
    }

    @Override
    public T interpolate(Object t) {
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
