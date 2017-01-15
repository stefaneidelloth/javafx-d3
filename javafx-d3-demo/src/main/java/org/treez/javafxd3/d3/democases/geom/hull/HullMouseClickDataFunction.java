package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;

public class HullMouseClickDataFunction implements DataFunction<Void> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	private List<HullCoords> vertices;

	private D3 d3;
	
	private Runnable redrawRunnable;

	//#end region

	//#region CONSTRUCTORS

	public HullMouseClickDataFunction(
				WebEngine webEngine, 
				List<HullCoords> vertices, 
				D3 d3,
				Runnable redrawRunnable
		){
			this.webEngine=webEngine;
			this.vertices = vertices;
			this.d3 = d3;
			this.redrawRunnable=redrawRunnable;
		}

	//#end region

	//#region METHODS

	@Override
	public Void apply(final Object context, final Object d, final int index) {
		
		Element element = ConversionUtil.convertObjectTo(context,  Element.class, webEngine);
		HullCoords coords = new HullCoords(webEngine, d3.mouseX(element), d3.mouseY(element));
		vertices.add(coords);
		redrawRunnable.run();
		return null;	
		
	}

	//#end region

}
