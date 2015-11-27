package com.github.javafxd3.demo.client.democases.behaviors;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class OnDragMove implements DatumFunction<Void> {
		
	private WebEngine webEngine;
	private D3 d3;
	
	public OnDragMove(WebEngine webEngine, D3 d3){
		this.webEngine = webEngine;
		this.d3=d3;
	}
	
	
	@Override
	public Void apply(final Object context, final Object d, final int index) {

		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);
		
		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);

		// change color of the element being dragged
		d3.select(element) //
		.attr("fill", "green");
		
		Coords coords = value.<Coords>as(Coords.class);
		
		// compute the new x and y using the mouse position
		// note: the mouse position has been adjusted to the drag 'origin'
		double newX = Math.max(DragMultiples.CIRCLE_RADIUS, Math.min(DragMultiples.SQUARE_WIDTH - DragMultiples.CIRCLE_RADIUS, d3.eventAsCoords().x()));
		double newY = Math.max(DragMultiples.CIRCLE_RADIUS, Math.min(DragMultiples.SQUARE_HEIGHT - DragMultiples.CIRCLE_RADIUS, d3.eventAsCoords().y()));
		
		// update the datum itself, to adjust the origin
		coords.x(newX).y(newY);
		
		// update the position of the circle
		d3.select(element) //
		.attr("cx", coords.x()) //
		.attr("cy", coords.y());
		return null;
	}
}