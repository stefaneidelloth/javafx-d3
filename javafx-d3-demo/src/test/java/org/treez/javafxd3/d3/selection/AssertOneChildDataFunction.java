package org.treez.javafxd3.d3.selection;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;



public class AssertOneChildDataFunction implements DataFunction<Void> {
	
	private JsEngine engine;
	
	public AssertOneChildDataFunction(JsEngine engine){
		this.engine = engine;
	}
	
		@Override
		public Void apply(final Object context, final Object d, final int index) {

			Element element = ConversionUtil.convertObjectTo(context,  Element.class, engine);

			assertEquals(1, element.getChildCount());
			return null;
		}
	

}
