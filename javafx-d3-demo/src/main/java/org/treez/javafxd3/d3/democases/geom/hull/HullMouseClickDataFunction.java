package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.core.JsEngine;

public class HullMouseClickDataFunction implements DataFunction<Void> {

	//#region ATTRIBUTES

	private JsEngine engine;

	private List<HullCoords> vertices;

	private D3 d3;
	
	private Runnable redrawRunnable;

	//#end region

	//#region CONSTRUCTORS

	public HullMouseClickDataFunction(
				JsEngine engine, 
				List<HullCoords> vertices, 
				D3 d3,
				Runnable redrawRunnable
		){
			this.engine=engine;
			this.vertices = vertices;
			this.d3 = d3;
			this.redrawRunnable=redrawRunnable;
		}

	//#end region

	//#region METHODS

	@Override
	public Void apply(final Object context, final Object d, final int index) {
		
		Element element = ConversionUtil.convertObjectTo(context,  Element.class, engine);
		HullCoords coords = new HullCoords(engine, d3.mouseX(element), d3.mouseY(element));
		vertices.add(coords);
		redrawRunnable.run();
		return null;	
		
	}

	//#end region

}
