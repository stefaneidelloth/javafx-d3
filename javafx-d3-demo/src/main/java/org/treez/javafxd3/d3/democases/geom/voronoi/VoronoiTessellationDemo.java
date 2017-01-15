package org.treez.javafxd3.d3.democases.geom.voronoi;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.ContextDataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.IndexDataFunctionWrapper;
import org.treez.javafxd3.d3.functions.key.KeyFunctionWrapper;
import org.treez.javafxd3.d3.geom.Voronoi;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

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

	public VoronoiTessellationDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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
		
		
		DataFunction<Void> mouseMoveFunction = new ContextDataFunctionWrapper<>(webEngine, (element)->{
			Array<Double> jsArray = d3.mouse(element);
			List<? extends Double> list = jsArray.asList(Double.class);
			Double[] array = list.toArray(new Double[list.size()]);
			
			vertices[0] = array;
			redraw();
			return null;
		});

		Selection svg = d3.select("svg") //
				.classed("vt", true) //
				.attr("width", width) //
				.attr("height", height) //
				.on("mousemove", mouseMoveFunction);

		path = svg.append("g") //
				.selectAll("path");
		
		DataFunction<String> transformDataFunction = new DataFunctionWrapper<>(String.class, webEngine, (value)->{
			String transform =  "translate(" + value + ")";
			return transform;
		});

		svg.selectAll("circle") //
				.data(vertices) //
				.enter() //
				.append("circle") //
				.attr("transform", transformDataFunction) //
				.attr("r", 1.5);

		redraw();

	}

	private void redraw() {
		
		
		
		Array<Double> polygons = voronoi.apply(vertices);
		
		KeyFunction<String> polygonKeyFunction = new KeyFunctionWrapper<>(Array.class, webEngine, (value)->{
			
			@SuppressWarnings("unchecked")
			Array<String> stringArray = (Array<String>) value;
			String polygon = VoronoiTessellationDemo.polygon(webEngine, stringArray);
			return polygon;
		});		
		
		UpdateSelection upd = this.path.data(polygons, polygonKeyFunction);

		upd.exit() //
				.remove();
		
		DataFunction<String> classDataFunction = new IndexDataFunctionWrapper<>((index)->{
			String className =  "q" + index % 9 + "-9";
			return className;
		});
		
		
		DataFunction<String> polygonDataFunction = new DataFunctionWrapper<>(Array.class, webEngine, (value)->{
			
			@SuppressWarnings("unchecked")
			Array<String> stringArray = (Array<String>) value;
			String polygon = VoronoiTessellationDemo.polygon(webEngine, stringArray);
			return polygon;
		});

		upd.enter() //
				.append("path") //
				.attr("class", classDataFunction) //
				.attr("d", polygonDataFunction);

		upd.order();
		this.path = upd;
	}

	public static String polygon(WebEngine webEngine, Array<String> strings) {
		
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
