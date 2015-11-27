package com.github.javafxd3.demo.client.democases.behaviors;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.arrays.ForEachCallback;
import com.github.javafxd3.api.behaviour.Drag;
import com.github.javafxd3.api.behaviour.Drag.DragEventType;
import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;

/**
 * 
 * 
 */
public class DragMultiples extends AbstractDemoCase {

	//#region ATTRIBUTES

	/**
	 * 
	 */
	public static final int SQUARE_WIDTH = 220;

	/**
	 * 
	 */
	public static final int SQUARE_HEIGHT = 80;

	/**
	 * 
	 */
	public static final int CIRCLE_RADIUS = 20;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public DragMultiples(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		init();
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DragMultiples newInstance() {
				return new DragMultiples(d3, demoPreferenceBox);
			}
		};
	}

	/**
	 * 
	 */
	private void init() {

		Drag drag = d3.behavior().drag()
				// the origin will be set with the data of svg
				// on mousedown
				.origin(d3.identity()) //			
				.on(Drag.DragEventType.DRAG, new OnDragMove(webEngine, d3)) //
				.on(DragEventType.DRAGSTART, new OnDragStart(webEngine, d3)) //
				.on(DragEventType.DRAGEND, new OnDragEnd(webEngine, d3));

		//Double[] data = Arrays.range(16).map(callback);
		List<Coords> coordsList = new ArrayList<>();
		for(int index=0;index<16;index++){
			Coords coords = new Coords(webEngine, SQUARE_WIDTH / 2, SQUARE_HEIGHT / 2);
			coordsList.add(coords);
		}
		
		Selection svg = d3.selectAll("svg") //
				.attr("width", 1)
				.attr("height", 1)
				// set the data as the center of the squares
				.data(coordsList) //
				.enter() //
				.append("svg") //
				.attr("width", SQUARE_WIDTH) //
				.attr("height", SQUARE_HEIGHT) //
				.style("float", "left") //
				.style("border", "solid 1px #aaa");

		svg.append("circle") //
				.attr("r", CIRCLE_RADIUS) //
				.attr("cx", new XDatumFunction(webEngine)) //
				.attr("cy", new YDatumFunction(webEngine)) //
				.style("cursor", "pointer")
				// listeners are registered
				.call(drag);

	}

		

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.gwtd3.demo.client.DemoCase#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.gwtd3.demo.client.DemoCase#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	//#end region

}
