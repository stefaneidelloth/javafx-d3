package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A datum function that returns the datum as object array
 * 
 */
public class ObjectArrayDataFunction implements DataFunction<Object[]> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public ObjectArrayDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Object[] apply(Object context, Object datum, int index) {		
		
		Value value = ConversionUtil.convertObjectTo(datum,  Value.class, engine);
		
		System.out.println(context + " " + value.asString() + " " + index);
		Object[] as = value.as();
		System.out.println(as);
		return as;
		
	}
	
	//#end region

}
