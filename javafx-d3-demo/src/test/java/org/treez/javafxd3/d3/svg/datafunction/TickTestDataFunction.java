package org.treez.javafxd3.d3.svg.datafunction;

import static org.junit.Assert.assertTrue;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;

/**
 * A datum function that returns the datum as string
 * 
 */
public class TickTestDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private final StringBuffer stringBuffer;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public TickTestDataFunction(WebEngine webEngine, final StringBuffer stringBuffer){
		this.webEngine=webEngine;
		this.stringBuffer = stringBuffer;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		
		Value value = Value.create(webEngine,  datum);
        System.out.println("INDEX " + index + " " + value.as());
        assertTrue(index >= 0 && index < 4);
        stringBuffer.append("x");
        if (index % 2 == 0) {
            return "";
        }
        return "" + index;	
	}
	
	//#end region

}
