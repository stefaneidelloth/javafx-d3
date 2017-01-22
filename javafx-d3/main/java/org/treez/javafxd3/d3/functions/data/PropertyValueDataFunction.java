package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A convenient {@link DataFunction} which returns the value of a specified
 * property for each datum.
 * <p>
 *
 * @param <T>
 */
public class PropertyValueDataFunction<T> implements DataFunction<T> {
	
	//#region ATTRIBUTES

	private final String propertyName;
	
	private JsEngine engine;
	
	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * @param propertyName
	 */
	public PropertyValueDataFunction(JsEngine engine, String propertyName) {
		this.engine = engine;
		this.propertyName=propertyName;
	}

	//#end region

	//#region METHODS
	
	/**
	 * Create a {@link PropertyValueDataFunction} which returns the value of the
	 * property with the given name
	 * 
	 * @param propertyName
	 *            the name of the property the value should be returned of
	 * @return the new function
	 */
	public static <X> PropertyValueDataFunction<X> forProperty(JsEngine engine, final String propertyName) {
		return new PropertyValueDataFunction<X>(engine, propertyName);
	}

	@Override
	public T apply(final Object context, final Object d, final int index) {
		Value value = getProperty(propertyName, d);		
		return value.as();
	}

	private Value getProperty(String propName, Object valueObj){
		
		String varName = "temp_object_var";
		D3 d3 = new D3(engine);
		JsObject d3JsObject = d3.getJsObject();
		d3JsObject.setMember(varName, valueObj);
		
		String command = "{datum:d3." +varName +"['"+ propName+ "']};";
		Object result = d3.eval(command);
		return Value.create(engine, result);
	}

	//#end region
}
