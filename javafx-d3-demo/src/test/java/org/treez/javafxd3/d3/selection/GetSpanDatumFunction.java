package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class GetSpanDatumFunction implements DatumFunction<Element[]> {

	private WebEngine webEngine;

	public GetSpanDatumFunction(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public Element[] apply(final Object context, final Object d, final int index) {

		Element element = new Element(webEngine, (JSObject) context);
		Element[] spans =  element.getElementsByTagName("span");
		return spans;
	}

}
