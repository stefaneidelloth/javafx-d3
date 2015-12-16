package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.geom.Hull;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * 
 * 
 */
public class HullDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Selection hull;
	private Selection circle;
	private List<MyCoords> vertices;
	private Hull hullModel;

	private static int width = 700;
	private static int height = 500;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public HullDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
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
			public DemoCase newInstance() {
				return new HullDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		List<MyCoords> coordsList = new ArrayList<>();
		for (int index = 0; index < 100; index++) {
			MyCoords coords = new MyCoords(webEngine, randomX(), randomY());
			coordsList.add(coords);
		}

		vertices = coordsList; //coordsList.toArray(new MyCoords[coordsList.size()]);

		svg = d3.select("svg") //
				.attr("width", width) //
				.attr("height", height) //
				.on("mousemove", new HullMouseMoveDatumFunction(webEngine, vertices, d3, () -> redraw())) //
				.on("click", new HullMouseClickDatumFunction(webEngine, vertices, d3, () -> redraw()));

		svg.append("rect") //
				.attr("width", width) //
				.attr("height", height) //
				.attr("class", "hulldemo");

		hull = svg.append("path") //
				.attr("class", "hulldemo");

		circle = svg.selectAll("circle");

		hullModel = d3.geom().hull().x(MyCoords.getXAccessor(webEngine)).y(MyCoords.getYAccessor(webEngine));

		redraw();
	}

	private void redraw() {
		try {

			Array<MyCoords> coordinates = hullModel.apply(vertices);

			hull.datum(coordinates).attr("d", new HullDatumFunction(webEngine));
			UpdateSelection circles = circle.data(vertices);
			circles.enter().append("circle").attr("r", 3);
			circle = circles.attr("transform", new HullTransformDatumFunction(webEngine)) //
					.attr("class", "hulldemo");
			circle = circles;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
	}

	private double randomX() {
		double xmin = width / 2;
		double xmax = 60d;
		double range = xmax - xmin;
		Random random = new Random();
		double randomX = xmin + random.nextDouble() * range;
		return randomX;

	}

	private double randomY() {
		double ymin = height / 2;
		double ymax = 60d;
		double range = ymax - ymin;
		Random random = new Random();
		double randomY = ymin + random.nextDouble() * range;
		return randomY;

	}

	//#end region

	//#regon CLASSES

	public static class MyCoords extends Coords {
		
		//#region CONSTRUCTORS
		
		public MyCoords(WebEngine webEngine, JSObject wrappedJsObject){
			super(webEngine, wrappedJsObject);
		}

		public MyCoords(WebEngine webEngine, double x, double y) {
			super(webEngine, x, y);
		}
		
		//#end region
		
		//#region METHODS

		

		@Override
		public String toString() {
			return x() + "," + y();
		}
		
		//#end region
		
	}

	//#end region

}
