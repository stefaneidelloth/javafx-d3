package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.democases.geom.hull.HullDemo.MyCoords;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullMouseMoveDatumFunction implements DatumFunction<Void> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	private List<MyCoords> vertices;

	private D3 d3;
	
	private Runnable redrawRunnable;

	//#end region

	//#region CONSTRUCTORS

	public HullMouseMoveDatumFunction(
				WebEngine webEngine, 
				List<MyCoords> vertices, 
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

		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);
		MyCoords coords = new MyCoords(webEngine, d3.mouseX(element), d3.mouseY(element));
		vertices.set(0, coords);
		redrawRunnable.run();
		return null;
	}

	//#end region

}
