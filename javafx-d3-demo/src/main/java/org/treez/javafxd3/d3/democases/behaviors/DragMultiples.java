package org.treez.javafxd3.d3.democases.behaviors;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.behaviour.Drag;
import org.treez.javafxd3.d3.behaviour.Drag.DragEventType;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.ContextDataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;

import javafx.scene.layout.VBox;

public class DragMultiples extends AbstractDemoCase {

	//#region ATTRIBUTES

	public static final int SQUARE_WIDTH = 220;

	public static final int SQUARE_HEIGHT = 80;

	public static final int CIRCLE_RADIUS = 20;

	//#end region

	//#region CONSTRUCTORS

	public DragMultiples(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		init();
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DragMultiples newInstance() {
				return new DragMultiples(d3, demoPreferenceBox);
			}
		};
	}

	private void init() {

		DataFunction<Void> dragStartFunction = new ContextDataFunctionWrapper<>(webEngine, (context) -> {
			d3.select(context) //
					.attr("fill", "red");
			return null;
		});

		DataFunction<Void> dragFunction = new DragFunction(webEngine, d3);

		DataFunction<Void> dragEndFunction = new ContextDataFunctionWrapper<>(webEngine, (context) -> {
			d3.select(context) //
					.attr("fill", "");
			return null;
		});

		Drag drag = d3.behavior().drag()
				// the origin will be set with the data of svg on mousedown
				.origin(d3.identity()) //					
				.on(DragEventType.DRAGSTART, dragStartFunction) //
				.on(Drag.DragEventType.DRAG, dragFunction) //
				.on(DragEventType.DRAGEND, dragEndFunction);

		//Double[] data = Arrays.range(16).map(callback);
		List<Coords> coordsList = new ArrayList<>();
		for (int index = 0; index < 16; index++) {
			Coords coords = new Coords(webEngine, SQUARE_WIDTH / 2, SQUARE_HEIGHT / 2);
			coordsList.add(coords);
		}

		Selection svg = d3.selectAll("svg") //
				.attr("width", 1).attr("height", 1)
				// set the data as the center of the squares
				.data(coordsList) //
				.enter() //
				.append("svg") //
				.attr("width", SQUARE_WIDTH) //
				.attr("height", SQUARE_HEIGHT) //
				.style("float", "left") //
				.style("border", "solid 1px #aaa");

		DataFunction<Double> xDataFunction = new DataFunctionWrapper<>(Coords.class, webEngine, (coords) -> {
			return coords.x();
		});

		DataFunction<Double> yDataFunction = new DataFunctionWrapper<>(Coords.class, webEngine, (coords) -> {
			return coords.y();
		});

		svg.append("circle") //
				.attr("r", CIRCLE_RADIUS) //
				.attr("cx", xDataFunction) //
				.attr("cy", yDataFunction) //
				.style("cursor", "pointer")
				// listeners are registered
				.call(drag);

	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	//#end region

}
