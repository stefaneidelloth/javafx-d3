package org.treez.javafxd3.d3.democases.geom.hull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.geom.Hull;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 */
public class HullDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Selection hull;
	private Selection circle;
	private List<HullCoords> vertices;
	private Hull hullModel;

	private static int width = 700;
	private static int height = 500;

	//#end region

	//#region CONSTRUCTORS

	public HullDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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

		List<HullCoords> coordsList = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			HullCoords coords = new HullCoords(webEngine, randomX(), randomY());
			coordsList.add(coords);
		}

		vertices = coordsList; //coordsList.toArray(new MyCoords[coordsList.size()]);

		svg = d3.select("svg") //
				.attr("width", width) //
				.attr("height", height) //
				.on("mousemove", new HullMouseMoveDataFunction(webEngine, vertices, d3, () -> redraw())) //
				.on("click", new HullMouseClickDataFunction(webEngine, vertices, d3, () -> redraw()));

		svg.append("rect") //
				.attr("width", width) //
				.attr("height", height) //
				.attr("class", "hulldemo");

		hull = svg.append("path") //
				.attr("class", "hulldemo");

		circle = svg.selectAll("circle");

		hullModel = d3.geom() //
				.hull() //
				.x(HullCoords.getXAccessor(webEngine)) //
				.y(HullCoords.getYAccessor(webEngine));

		redraw();
	}

	private void redraw() {
		try {

			Array<HullCoords> coordinates = hullModel.apply(vertices);

			hull.datum(coordinates) //
					.attr("d", new HullDataFunction(webEngine));

			UpdateSelection circles = circle //
					.data(vertices);

			circles.enter() //
					.append("circle") //
					.attr("r", 3);

			circle = circles.attr("transform", new HullTransformDataFunction(webEngine)) //
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



}
