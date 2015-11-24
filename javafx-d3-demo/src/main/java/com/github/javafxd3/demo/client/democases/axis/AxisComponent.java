package com.github.javafxd3.demo.client.democases.axis;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Transition;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.dsv.DsvObjectAccessor;
import com.github.javafxd3.api.dsv.DsvRow;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.LinearScale;
import com.github.javafxd3.api.svg.Area;
import com.github.javafxd3.api.svg.Axis;
import com.github.javafxd3.api.svg.Axis.Orientation;
import com.github.javafxd3.api.svg.Line;
import com.github.javafxd3.api.time.JsDate;
import com.github.javafxd3.api.time.TimeFormat;
import com.github.javafxd3.api.time.TimeScale;
import com.github.javafxd3.api.wrapper.JavaScriptObject;
import com.github.javafxd3.api.xhr.XmlHttpRequest;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

@SuppressWarnings("javadoc")
public class AxisComponent extends AbstractDemoCase {
	
	//#region CONSTRUCTORS

	/**
	 * 
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public AxisComponent(D3 d3, VBox demoPreferenceBox) {
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
				return new AxisComponent(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		final int[] m = new int[] { 80, 80, 80, 80 };
		final int w = 960 - m[1] - m[3];
		final int h = 500 - m[0] - m[2];

		final TimeFormat format = d3.time().format("%b %Y");

		// Scales and axes. Note the inverted domain for the y-scale: bigger is
		// up!

		final TimeScale x = d3.time().scale().range(0, w);

		final LinearScale y = d3.scale().linear().range(h, 0);
		final Axis xAxis = d3.svg().axis().scale(x).tickSize(-h);
		// removed .tickSubdivide(1);
		final Axis yAxis = d3.svg().axis().scale(y).orient(Orientation.RIGHT).ticks(4);

		// An area generator, for the light fill.
		final Area area = d3.svg().area().interpolate(Area.InterpolationMode.MONOTONE)
				// .x(function(d) { return x(d.date); })
				.x(new XAxisDatumFunction(webEngine, x)).y0(h)
				// .y1(function(d) { return y(d.price); });
				.y1(new YAxisDatumFunction(webEngine, y));

		// A line generator, for the dark stroke.
		final Line line = d3.svg().line().interpolate(Line.InterpolationMode.MONOTONE)
				// .x(function(d) { return x(d.date); })
				.x(new XAxisDatumFunction(webEngine, x))
				// // .y(function(d) { return y(d.price); });
				.y(new YAxisDatumFunction(webEngine, y));
		
		String relativeUrl = "readme.csv";
		
		URL url = getClass().getClassLoader().getResource(relativeUrl);

		d3.csv(url.toString(), new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final DsvRow d, final int index) {
				Value value = d.get("symbol");
				if ("S&P 500".equals(value.asString())) {
					String symbol = d.get("symbol").asString();
					JsDate date = format.parse(d.get("date").asString());
					double price = d.get("price").asDouble();
					return new Data(webEngine, symbol, date, price);
				} else {
					return null;
				}
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final JavaScriptObject error, final Data[] values) {

				if (error != null) {
					XmlHttpRequest xhrError = error.convertToJavaScriptObject(error, XmlHttpRequest.class);
					String message = xhrError.status() + " (" + xhrError.statusText() + ")";
					System.err.println(message);
					throw new RuntimeException(message);
				}

				// // Compute the minimum and maximum date, and the maximum
				// price.
				List<JsDate> domainValues = new ArrayList<>();
				domainValues.add(values[0].getDate());
				domainValues.add(values[values.length - 1].getDate());
				
				
				x.domain(Array.fromList(webEngine, domainValues));
				
				double maxY = values[0].getPrice();
				for(Data entry: values){
					double price = entry.getPrice();
					if (price > maxY){
						maxY=price;
					}
				}
							
				System.out.println("the max Y is " + maxY + " among " + values);
				y.domain(new double[] { 0.0, maxY }).nice();

				// Add an SVG element with the desired dimensions and margin.
				final Selection svg = d3.select("svg").attr("class", "svg")
						.attr("width", w + m[1] + m[3]).attr("height", h + m[0] + m[2]).append("svg:g")
						.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

				// Add the clip path.
				svg.append("svg:clipPath").attr("id", "clip").append("svg:rect").attr("width", w).attr("height", h);

				// Add the area path.
				svg.append("svg:path").attr("class", "area").attr("clip-path", "url(#clip)").attr("d",
						area.apply(values));

				// Add the x-axis.
				svg.append("svg:g").attr("class", "x" + " " + "axis").attr("transform", "translate(0," + h + ")")
						.call(xAxis);

				// Add the y-axis.
				svg.append("svg:g").attr("class", "y" + " " + "axis").attr("transform", "translate(" + w + ",0)")
						.call(yAxis);
				
				List<Data> valueList = Arrays.asList(values);

				// Add the line path.
				svg.append("svg:path").attr("class", "line").attr("clip-path", "url(#clip)").attr("d",
						line.generate(valueList));

				// Add a small label for the symbol name.
				svg.append("svg:text").attr("x", w - 6).attr("y", h - 6).attr("text-anchor", "end")
						.text(values[0].getSymbol());

				// On click, update the x-axis.
				svg.on("CLICK", new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						int n = values.length - 1;
						int i = (int) Math.floor((Math.random() * n) / 2);
						int j = i + (int) Math.floor((Math.random() * n) / 2) + 1;
						JSObject firstObj = values[i].getDate().getJsObject();
						JSObject secondObj = values[j].getDate().getJsObject();
						x.domain(Array.fromJavaScriptObjects(webEngine, firstObj, secondObj));
						Transition transition = svg.transition().duration(750);
						transition.select("." + "x" + "." + "axis").call(xAxis);
						transition.select("." + "area").attr("d", area.apply(values));
						transition.select("." + "line").attr("d", line.generate(valueList));
						return null;
					};
				});
			}
		});

	}

	@Override
	public void stop() {
	}

	//#end region

	//#region CLASSES

	private static class Data extends JavaScriptObject {

		//#region ATTRIBUTES

		private final String symbol;

		private final JsDate date;

		private final double price;

		//#end region

		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param symbol
		 * @param date
		 * @param price
		 */
		public Data(WebEngine webEngine, final String symbol, final JsDate date, final double price) {
			super(webEngine);
			this.symbol = symbol;
			this.date = date;
			this.price = price;
		}

		//#end region

		//#region METHODS

		@Override
		public String toString() {
			return "Data [date=" + date.getTime() + ", price=" + price + "]";
		}
		
		//#end region

		//#region ACCESSORS

		/**
		 * @return
		 */
		public String getSymbol() {
			return symbol;
		}

		/**
		 * @return
		 */
		public JsDate getDate() {
			return date;
		}

		/**
		 * @return
		 */
		public double getPrice() {
			return price;
		}

		//#end region
	}

	//#end region

}
