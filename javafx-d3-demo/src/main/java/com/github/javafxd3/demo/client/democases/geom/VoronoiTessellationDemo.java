package com.github.javafxd3.demo.client.democases.geom;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.UpdateSelection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.KeyFunction;
import com.github.javafxd3.api.geom.Voronoi;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 *
 * 
 *
 */
public class VoronoiTessellationDemo extends AbstractDemoCase {

	// #region ATTRIBUTES

	private Selection path;
	private Voronoi voronoi;
	private Double[][] vertices;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public VoronoiTessellationDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("VoronoiTessellationDemo.css")
		// vt
	}

	// #end region

	// #region METHODS

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

		voronoi = d3.geom().voronoi().clipExtent(0, 0, width, height);
		Selection svg = d3.select("root").append("svg").classed("vt", true).attr("width", width).attr("height", height)
				.on("MOUSEMOVE", new DatumFunction<Void>() {

					@Override
					public Void apply(final Element context, final Value d, final int index) {
						vertices[0] = d3.mouse(context);
						redraw();
						return null;
					}
				});

		path = svg.append("g").selectAll("path");

		svg.selectAll("circle").data(vertices[1]).enter().append("circle")
				.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(final Element context, final Value d, final int index) {
						return "translate(" + d.asString() + ")";
					}
				}).attr("r", 1.5);

		redraw();

	}

	private void redraw() {
		UpdateSelection upd = this.path.data(voronoi.apply(vertices), new KeyFunction<String>() {
			@Override
			public String map(final Element context, final Object[] newDataArray, final Value datum, final int index) {
				String polygon = polygon(datum);
				return polygon;
			}
		});

		upd.exit().remove();

		upd.enter().append("path").attr("class", new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Value d, final int index) {
				return "q" + index % 9 + "-9";
			}
		}).attr("d", new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Value d, final int index) {
				return polygon(d);
			}
		});

		upd.order();
		this.path = upd;
	}

	private String polygon(final Value datum) {
		
		String[] strings = datum.<String[]> as();
		List<String> stringList = new ArrayList<>();
		for(String string: strings){
			stringList.add(string);
		}
		
		String dataString = String.join("L", stringList);
		String result = "M" + dataString + "Z";
		
		return result;
	}

	@Override
	public void stop() {
	}

	// #end region
}
