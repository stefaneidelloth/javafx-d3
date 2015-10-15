package com.github.javafxd3.demo.client.democases.behaviors;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.ForEachCallback;
import com.github.javafxd3.api.behaviour.Drag;
import com.github.javafxd3.api.behaviour.Drag.DragEventType;
import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;

/**
 * 
 * 
 */
public class DragMultiples extends AbstractDemoCase {

	// #region ATTRIBUTES

	/**
	 * 
	 */
	public static final int SQUARE_WIDTH = 238;
	
	/**
	 * 
	 */
	public static final int SQUARE_HEIGHT = 123;
	
	/**
	 * 
	 */
	public static final int CIRCLE_RADIUS = 20;

	// #end region

	// #region CONSTRUCTORS

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

	// #end region

	// #region METHODS

	/**
	 * Factory provider
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
				.origin(d3.identity()).on(Drag.DragEventType.DRAG, new OnDragMove())
				.on(DragEventType.DRAGSTART, new OnDragStart()).on(DragEventType.DRAGEND, new OnDragEnd());
		
		
		
		ForEachCallback<Coords> callback = new ForEachCallback<Coords>() {
			@Override
			public Coords forEach(final Object thisArg, final Value element, final int index,
					final Object[] array) {
				return new Coords(webEngine, SQUARE_WIDTH / 2, SQUARE_HEIGHT / 2);
			}
		};
		
		//Double[] data = Arrays.range(16).map(callback);
		
		Double[] data = new Double[]{1.0, 2.0, 3.0};
		
		Selection svg = d3.select("root").selectAll("svg")
				// set the data as the center of the squares
				.data(data).enter().append("svg").attr("width", SQUARE_WIDTH).attr("height", SQUARE_HEIGHT)
				.style("float", "left").style("border", "solid 1px #aaa");

		svg.append("circle").attr("r", CIRCLE_RADIUS).attr("cx", new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.as(Coords.class).x();
			}
		}).attr("cy", new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.as(Coords.class).y();
			}
		}).style("cursor", "pointer")
				// listeners are registered
				.call(drag);

	}

	private class OnDragMove implements DatumFunction<Void> {
		@Override
		public Void apply(final Object context, final Object d, final int index) {
			
			Value dat = (Value) d;						
			Element element =(Element) context;
			
			// change color of the element being dragged
			d3.select(element).attr("fill", "green");
			Coords datum = dat.as();
			// compute the new x and y using the mouse position
			// note: the mouse position has been adjusted to the drag 'origin'
			double newX = Math.max(CIRCLE_RADIUS, Math.min(SQUARE_WIDTH - CIRCLE_RADIUS, d3.eventAsCoords().x()));
			double newY = Math.max(CIRCLE_RADIUS, Math.min(SQUARE_HEIGHT - CIRCLE_RADIUS, d3.eventAsCoords().y()));
			// update the datum itself, to adjust the origin
			datum.x(newX).y(newY);
			// update the position of the circle
			d3.select(element).attr("cx", datum.x()).attr("cy", datum.y());
			return null;
		}
	}

	public class OnDragEnd implements DatumFunction<Void> {

		@Override
		public Void apply(final Object context, final Object d, final int index) {
			
			Value datum = (Value) d;						
			Element element =(Element) context;
			
			// remove fill attributes
			d3.select(element).attr("fill", "");
			return null;
		}

	}

	public class OnDragStart implements DatumFunction<Void> {

		@Override
		public Void apply(final Object context, final Object d, final int index) {
			
			Value datum = (Value) d;						
			Element element =(Element) context;
			
			d3.select(element).attr("fill", "red");
			return null;
		}

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

	// #end region

}
