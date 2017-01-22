package org.treez.javafxd3.d3.democases.behaviors;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.core.JsEngine;

public class DragFunction implements DataFunction<Void> {

	//#region ATTRIBUTES

	private JsEngine engine;
	private D3 d3;

	//#end region

	//#region CONSTRUCTORS

	public DragFunction(JsEngine engine, D3 d3) {
		this.engine = engine;
		this.d3 = d3;
	}

	//#end region

	//#region METHODS

	@Override
	public Void apply(final Object context, final Object d, final int index) {

		Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
		Coords coords = ConversionUtil.convertObjectTo(d, Coords.class, engine);

		// change color of the element being dragged
		d3.select(element) //
				.attr("fill", "green");

		// compute the new x and y using the mouse position
		// note: the mouse position has been adjusted to the drag 'origin'
		double newX = Math.max(DragMultiples.CIRCLE_RADIUS,
				Math.min(DragMultiples.SQUARE_WIDTH - DragMultiples.CIRCLE_RADIUS, d3.eventAsCoords().x()));
		double newY = Math.max(DragMultiples.CIRCLE_RADIUS,
				Math.min(DragMultiples.SQUARE_HEIGHT - DragMultiples.CIRCLE_RADIUS, d3.eventAsCoords().y()));

		// update the datum itself, to adjust the origin
		coords.x(newX).y(newY);

		// update the position of the circle
		d3.select(element) //
				.attr("cx", coords.x()) //
				.attr("cy", coords.y());
		return null;
	}

	//#end region
}