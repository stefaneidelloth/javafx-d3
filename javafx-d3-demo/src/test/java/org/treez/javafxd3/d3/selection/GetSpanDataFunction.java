package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

public class GetSpanDataFunction implements DataFunction<Element[]> {

	private JsEngine engine;

	public GetSpanDataFunction(JsEngine engine) {
		this.engine = engine;
	}

	@Override
	public Element[] apply(final Object context, final Object d, final int index) {

		Element element = ConversionUtil.convertObjectTo(context,  Element.class, engine);
		Element[] spans =  element.getElementsByTagName("span");
		return spans;
	}

}
