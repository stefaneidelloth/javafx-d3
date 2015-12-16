package org.treez.javafxd3.d3.democases.geom.shape;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.geo.ConicProjection;
import org.treez.javafxd3.d3.geo.Projection;
import org.treez.javafxd3.d3.geom.Polygon;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition.EventType;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * 
 * 
 */
public class ShapeTweeningDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Projection<?> projection;
	private Selection path;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ShapeTweeningDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("ShapeTweeningDemo.css")
		// @Source("california.json")
		// demo, intersection
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
				return new ShapeTweeningDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		int width = 960;
		int height = 500;

		ConicProjection albers = d3.geo().albers();
		Projection<?> rotated = albers.rotate(120, 0);
		Projection<?> centered = rotated.center(0, 37.7);
		projection = centered.scale(2700);
		 
		svg = d3.select("svg").attr("width", width).attr("height", height).attr("class", "demo");

		Double[][] coordinates0 = parseJSONToArray();
		Double[][] coordinates1 = circle(coordinates0);

		path = svg.append("path");
		String d0 = "M" + join(coordinates0, "L") + "Z";
		String d1 = "M" + join(coordinates1, "L") + "Z";

		// FIXME: polygon.clip exemple
		// Polygon intersection = d3.geom().polygon(coordinates0)
		// .clip(coordinates1);
		// System.out.println(intersection.length());
		// String d2 = "M" + intersection.join("L") + "Z";
		// System.out.println(d2);
		// svg.append("path").attr("d", d2)
		// .attr("class", Bundle.INSTANCE.css().intersection());

		loop(d0, d1);
	}

	@Override
	public void stop() {
	}

	private String join(final Double[][] coords, final String delimiter) {
		String s = "";
		for (int i = 0; i < (coords.length - 1); i++) {
			s += coords[i][0] + "," + coords[i][1] + "L";
		}
		s += coords[coords.length - 1][0] + "," + coords[coords.length - 1][1];
		return s;
	}

	private Double[][] parseJSONToArray() {

		String jsonText = ""; // TODO

		// JSONValue value = JSONParser.parseLenient(jsonText);
		// JSONArray array = value.isObject().get("coordinates").isArray();

		// return array.get(0).isArray().getJavaScriptObject().<Object[]> cast()
		// .map(projection);
		return new Double[][] {};

	}

	private void loop(final String d0, final String d1) {

		path.attr("d", d0).transition().duration(5000).attr("d", d1).transition().delay(5000).attr("d", d0)
				.each(EventType.END, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						loop(d0, d1);
						return null;
					}
				});

	}

	/**
	 * Transform the provided array of coords to an array of coords that reflect
	 * a circle
	 * 
	 * @param coordinates
	 * @return the circle coordinates
	 */
	private Double[][] circle(final Double[][] coordinates) {
		List<Double[]> circle = new ArrayList<>();
		int length = 0;
		List<Integer> lengths = new ArrayList<>();
		Polygon polygon = d3.geom().polygon(coordinates);
		Double[] p0 = coordinates[0];
		int i = 0;
		int n = coordinates.length;

		// Compute the distances of each coordinate.
		while (++i < n) {
			Double[] p1 = coordinates[i];
			double x = p1[0] - p0[0];
			double y = p1[1] - p0[1];
			lengths.add(length += Math.sqrt((x * x) + (y * y)));
			p0 = p1;
		}

		double area = polygon.area();
		double radius = Math.sqrt(Math.abs(area) / Math.PI);
		Array<Double> centroid = polygon.centroid(-1 / (6 * area));
		double angleOffset = -Math.PI / 2;
		double k = (2 * Math.PI) / lengths.get(lengths.size() - 1);

		// Compute points along the circles circumference at equivalent
		// distances.
		i = -1;
		while (++i < n) {
			double angle = angleOffset + (lengths.get(i) * k);
			double first = (double) centroid.get(0, Double.class) + (radius * Math.cos(angle));
			double second = (double) centroid.get(1, Double.class) + (radius * Math.sin(angle));

			circle.add(new Double[] { first, second });
		}

		Double[][] circleArray = (Double[][]) circle.toArray();

		return circleArray;
	}

	//#end region
}
