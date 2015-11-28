package com.github.javafxd3.d3.functions;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.core.Value;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A convenient {@link DatumFunction} which returns the value of a specified
 * property for each datum.
 * <p>
 *
 * @param <T>
 */
public class PropertyValueFunction<T> implements DatumFunction<T> {
	
	//#region ATTRIBUTES

	private final String propertyName;
	
	private WebEngine webEngine;
	
	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * @param propertyName
	 */
	public PropertyValueFunction(WebEngine webEngine, String propertyName) {
		this.webEngine = webEngine;
		this.propertyName=propertyName;

	}

	//#end region

	//#region METHODS
	
	/**
	 * Create a {@link PropertyValueFunction} which returns the value of the
	 * property with the given name
	 * 
	 * @param propertyName
	 *            the name of the property the value should be returned of
	 * @return the new function
	 */
	public static <X> PropertyValueFunction<X> forProperty(WebEngine webEngine, final String propertyName) {
		return new PropertyValueFunction<X>(webEngine, propertyName);
	}

	@Override
	public T apply(final Object context, final Object d, final int index) {
		Value value = getProperty(propertyName, d);		
		return value.as();
	}

	private Value getProperty(String propName, Object valueObj){
		
		String varName = "temp_object_var";
		D3 d3 = new D3(webEngine);
		JSObject d3JsObject = d3.getJsObject();
		d3JsObject.setMember(varName, valueObj);
		
		String command = "{datum:d3." +varName +".datum['"+ propName+ "']};";
		JSObject result = d3.evalForJsObject(command);
		return new Value(webEngine, result);
	}

	//#end region
}
