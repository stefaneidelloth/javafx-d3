package org.treez.javafxd3.d3.democases.behaviors;

import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class OnDragStart implements DatumFunction<Void> {
	
	private WebEngine webEngine;
	private D3 d3;
	
	public OnDragStart(WebEngine webEngine, D3 d3){
		this.webEngine = webEngine;
		this.d3=d3;
	}

	@Override
	public Void apply(final Object context, final Object d, final int index) {
				
		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);	

		d3.select(element) //
		.attr("fill", "red");
		
		return null;
	}

}
