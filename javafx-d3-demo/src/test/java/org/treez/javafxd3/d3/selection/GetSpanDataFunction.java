package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class GetSpanDataFunction implements DataFunction<Element[]> {

	private WebEngine webEngine;

	public GetSpanDataFunction(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public Element[] apply(final Object context, final Object d, final int index) {

		Element element = new Element(webEngine, (JSObject) context);
		Element[] spans =  element.getElementsByTagName("span");
		return spans;
	}

}
