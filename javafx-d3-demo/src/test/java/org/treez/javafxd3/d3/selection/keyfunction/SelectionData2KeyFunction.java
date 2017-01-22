package org.treez.javafxd3.d3.selection.keyfunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.wrapper.Element;

public class SelectionData2KeyFunction implements KeyFunction<Integer> {
	
	private JsEngine engine;
	
	public SelectionData2KeyFunction(JsEngine engine){
		this.engine = engine;
	}
	
		@Override
		public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {			
			
			Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);				
			Value value = ConversionUtil.convertObjectTo(datum, Value.class, engine);			
			
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
