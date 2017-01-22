package org.treez.javafxd3.d3.democases.axis;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.svg.Area;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Line;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeScale;

public class DataDsvCallback implements DsvCallback<DsvData> {

	//#region ATTRIBUTES

	private JsEngine engine;
	private Selection svg;
	private TimeScale xScale;
	private LinearScale yScale;
	private Axis xAxis;
	private Axis yAxis;
	private Line line;
	private Area area;
	private int m[];
	private int w;
	private int h;

	//#end region

	//#region CONSTRUCTORS

	public DataDsvCallback(JsEngine engine, Selection svg, TimeScale xScale, LinearScale yScale, Axis xAxis,
			Axis yAxis, Line line, Area area, int m[], int w, int h) {
		this.engine = engine;
		this.svg = svg;
		this.xScale = xScale;
		this.yScale = yScale;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.line = line;
		this.area = area;
		this.m = m;
		this.w = w;
		this.h = h;
	}

	//#end region

	//#region METHODS

	@Override
	public void get(Object error, Object dsvDataArray) {

		if (error != null) {
			//XmlHttpRequest xhrError = error.convertToJavaScriptObject(error, XmlHttpRequest.class);
			//String message = xhrError.status() + " (" + xhrError.statusText() + ")";
			//System.err.println(message);
			String message = "Could not get data. " + error.toString();
			throw new RuntimeException(message);
		}

	
		@SuppressWarnings("unchecked")
		Array<DsvData> values = (Array<DsvData>) ConversionUtil.convertObjectTo(dsvDataArray,  Array.class, engine);
		List<? extends DsvData> valueList = values.asList(DsvData.class);

		// // Compute the minimum and maximum date, and the maximum
		// price.
		List<JsDate> domainValues = new ArrayList<>();

		int size = valueList.size();

		DsvData firstData = valueList.get(0);

		domainValues.add(firstData.getDate());
		domainValues.add(valueList.get(size - 1).getDate());

		xScale.domain(Array.fromList(engine, domainValues));

		double maxY = values.get(0, DsvData.class).getPrice();
		for (DsvData entry : valueList) {
			double price = entry.getPrice();
			if (price > maxY) {
				maxY = price;
			}
		}

		System.out.println("the max Y is " + maxY + " among " + values);
		yScale.domain(new double[] { 0.0, maxY }).nice();

		// Add an SVG element with the desired dimensions and margin.
		svg = svg.attr("class", "svg") //
				.attr("width", w + m[1] + m[3]) //
				.attr("height", h + m[0] + m[2]) //
				.append("svg:g") //
				.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

		// Add the clip path.
		svg.append("svg:clipPath") //
				.attr("id", "clip") //				
				.append("svg:rect") //
				.attr("width", w) //
				.attr("height", h);

		// Add the area path.
		svg.append("svg:path") //
				.classed("area", true) //
				.attr("clip-path", "url(#clip)") //
				.attr("d", area.apply(values));

		// Add the x-axis.
		svg.append("svg:g") //
				.attr("class", "x" + " " + "axis") //
				.attr("transform", "translate(0," + h + ")") //
				.call(xAxis);

		// Add the y-axis.
		svg.append("svg:g") //
				.attr("class", "y" + " " + "axis") //
				.attr("transform", "translate(" + w + ",0)") //
				.call(yAxis);

		// Add the line path.
		svg.append("svg:path") //
				.attr("class", "line") //
				.attr("clip-path", "url(#clip)") //
				.attr("d", line.generate(valueList));

		// Add a small label for the symbol name.
		svg.append("svg:text") //
				.attr("x", w - 6) //
				.attr("y", h - 6) //
				.attr("text-anchor", "end") //
				.text(firstData.getSymbol());

		// On click, update the x-axis.
		
		DataFunction<Void> clickFunction = new DataFunctionWrapper<>(()->{
			int n = valueList.size() - 1;
			int i = (int) Math.floor((Math.random() * n) / 2);
			int j = i + (int) Math.floor((Math.random() * n) / 2) + 1;
			JsObject firstObj = valueList.get(i).getDate().getJsObject();
			JsObject secondObj = valueList.get(j).getDate().getJsObject();
			
			xScale.domain(Array.fromJavaScriptObjects(engine, firstObj, secondObj));
			
			Transition transition = svg.transition() //
					.duration(750);
			
			transition.select("." + "x" + "." + "axis") //
			.call(xAxis);
			
			transition.select("." + "area") //
			.attr("d", area.apply(values));
			
			transition.select("." + "line") //
			.attr("d", line.generate(valueList));
			
		});
		
		svg.on("click", clickFunction);

	}

	//#end region

}
