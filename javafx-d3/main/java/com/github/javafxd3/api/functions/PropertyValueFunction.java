package com.github.javafxd3.api.functions;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.wrapper.Element;

/**
 * A convenient {@link DatumFunction} which returns the value of a specified
 * property for each datum.
 * <p>
 * 
 * 
 *
 * @param <T>
 */
public class PropertyValueFunction<T> implements DatumFunction<T> {
	
	//#region ATTRIBUTES

	private final String propertyName;
	
	//#end region

	

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * @param propertyName
	 */
	public PropertyValueFunction(String propertyName) {
		this.propertyName=propertyName;

	}

	// #end region

	// #region METHODS
	
	/**
	 * Create a {@link PropertyValueFunction} which returns the value of the
	 * property with the given name
	 * 
	 * @param propertyName
	 *            the name of the property the value should be returned of
	 * @return the new function
	 */
	public static <X> PropertyValueFunction<X> forProperty(final String propertyName) {
		return new PropertyValueFunction<X>(propertyName);
	}

	@Override
	public T apply(final Element context, final Value d, final int index) {
		Value value = getProperty(propertyName, d);		
		return value.as();
	}

	private static  Value getProperty(String propName, Value v){
		
		throw new IllegalStateException("not yet implemented");
		//JsObject result = evalForJsObject(command);
		//return {datum:v.datum[propName]};
	}

	// #end region
}
