package org.treez.javafxd3.d3.selection;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



public class AssertOneChildDatumFunction implements DatumFunction<Void> {
	
	private WebEngine webEngine;
	
	public AssertOneChildDatumFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
		@Override
		public Void apply(final Object context, final Object d, final int index) {

			Element element = new Element(webEngine, (JSObject) context);

			assertEquals(1, element.getChildCount());
			return null;
		}
	

}
