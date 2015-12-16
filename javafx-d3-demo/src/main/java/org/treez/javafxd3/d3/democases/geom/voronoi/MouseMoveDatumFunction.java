package org.treez.javafxd3.d3.democases.geom.voronoi;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class MouseMoveDatumFunction implements DatumFunction<Void> {

	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	private D3 d3;
	private Double[][] vertices;
	private Runnable redrawRunnable;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public MouseMoveDatumFunction(WebEngine webEngine, D3 d3, Double[][] vertices, Runnable redrawRunnable){
		this.webEngine = webEngine;
		this.d3 = d3;
		this.vertices = vertices;
		this.redrawRunnable=redrawRunnable;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public Void apply(final Object context, final Object d, final int index) {
		
		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);
					
		Array<Double> jsArray = d3.mouse(element);
		List<? extends Double> list = jsArray.asList(Double.class);
		Double[] array = list.toArray(new Double[list.size()]);
		
		vertices[0] = array;
		redrawRunnable.run();
		return null;
	}
	
	//#end region
	
	
}
