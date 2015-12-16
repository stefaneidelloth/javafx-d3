package org.treez.javafxd3.d3.democases.geom.voronoi;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.geom.Voronoi;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.core.Value;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 */
public class VoronoiTessellationDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection path;
	private Voronoi voronoi;
	private Double[][] vertices;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public VoronoiTessellationDemo(D3 d3, VBox demoPreferenceBox) {
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
				return new VoronoiTessellationDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		final int width = 960, height = 500;

		vertices = new Double[100][2];
		for (int index = 0; index < vertices.length; index++) {
			Double[] vertice = new Double[] { Math.random() * width, Math.random() * height };
			vertices[index] = vertice;
		}

		voronoi = d3.geom() //
				.voronoi() //
				.clipExtent(0, 0, width, height);

		Selection svg = d3.select("svg") //
				.classed("vt", true) //
				.attr("width", width) //
				.attr("height", height) //
				.on("mousemove", new MouseMoveDatumFunction(webEngine, d3, vertices, () -> redraw()));

		path = svg.append("g") //
				.selectAll("path");

		svg.selectAll("circle") //
				.data(vertices) //
				.enter() //
				.append("circle") //
				.attr("transform", new TransformDatumFunction(webEngine)) //
				.attr("r", 1.5);

		redraw();

	}

	private void redraw() {
		Array<Double> polygons = voronoi.apply(vertices);
		UpdateSelection upd = this.path.data(polygons, new PolygonKeyFunction(webEngine));

		upd.exit() //
				.remove();

		upd.enter() //
				.append("path") //
				.attr("class", new ClassDatumFunction()) //
				.attr("d", new PolygonDatumFunction(webEngine));

		upd.order();
		this.path = upd;
	}

	public static String polygon(WebEngine webEngine, Value datum) {

		JSObject jsDatum = datum.as();
		Array<String> strings = new Array<String>(webEngine, jsDatum);

		List<String> stringList = new ArrayList<>();

		int size = strings.sizes().get(0);

		for (int index = 0; index < size; index++) {
			String string = strings.get(index, String.class);
			stringList.add(string);
		}

		String dataString = String.join("L", stringList);
		String result = "M" + dataString + "Z";

		return result;
	}

	@Override
	public void stop() {

	}

	//#end region
}
