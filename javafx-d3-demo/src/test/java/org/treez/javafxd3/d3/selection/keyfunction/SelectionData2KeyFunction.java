package org.treez.javafxd3.d3.selection.keyfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class SelectionData2KeyFunction implements KeyFunction<Integer> {
	
	private WebEngine webEngine;
	
	public SelectionData2KeyFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
		@Override
		public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
			
			JSObject elementJsObject = (JSObject) context;
			Element element = new Element(webEngine, elementJsObject);				
						
			JSObject jsDatum = (JSObject) datum;
			
			
			Value value = new Value(webEngine, jsDatum);			
			
			if (context != null) {				
				
				System.out.println("looping through existing element div with value " + value.asInt() + " blah: " + element.getAttribute("blah")
						+ " index: " + index);
				return value.asInt();
			} else {
				System.out.println("looping through new given data value " + value.asInt() + " blah: " + value.asInt() + " index: " + index);
				return value.asInt();
			}

			
		}
	
}
