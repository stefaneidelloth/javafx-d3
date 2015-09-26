package com.github.javafxd3.demo.client.democases;

import java.util.Date;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class AxisComponent extends AbstractDemoCase {

	// #region ATTRIBUTES

	// #end region

	// #region CONSTRUCTORS

	/**
	 * 
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public AxisComponent(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		//@Source("AxisComponentStyles.css")
		// area axis x y line svg
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
		
		/*
		
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
				.x(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return x.apply(((Data) d.as()).getDate()).asDouble();
					}
				}).y0(h)
				// .y1(function(d) { return y(d.price); });
				.y1(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return y.apply(((Data) d.as()).getPrice()).asDouble();
					}
				});

		// A line generator, for the dark stroke.
		final Line line = d3.svg().line().interpolate(Line.InterpolationMode.MONOTONE)
				// .x(function(d) { return x(d.date); })
				.x(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return x.apply(((Data) d.as()).getDate()).asDouble();
					}
				})
				// // .y(function(d) { return y(d.price); });
				.y(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return y.apply(d.<Data> as().getPrice()).asDouble();
					}
				});

		d3.csv("demo-data/readme.csv", new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final DsvRow d, final int index) {
				Value value = d.get("symbol");
				if ("S&P 500".equals(value.asString())) {
					String symbol = d.get("symbol").asString();
					Date date = format.parse(d.get("date").asString());
					double price = d.get("price").asDouble();
					return new Data(symbol, date, price);
				} else {
					return null;
				}
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final JavaScriptObject error, final Data[] values) {

				if (error != null) {
					XmlHttpRequest xhrError = error.cast();
					String message = xhrError.status() + " (" + xhrError.statusText() + ")";
					System.err.println(message);
					throw new RuntimeException(message);
				}

				// // Compute the minimum and maximum date, and the maximum
				// price.
				x.domain(new Object[]{values[0].getDate(), values[values.length - 1].getDate()});

				int maxY = Arrays.max(values, new NumericForEachCallback() {
					@Override
					public double forEach(final Object thisArg, final Value element, final int index,
							final Object[] array) {
						return element.<Data> as().getPrice();
					}
				}).asInt();
				System.out.println("the max Y is " + maxY + " among " + values);
				y.domain(new double[]{0.0, (double )maxY}).nice();
				
				// Add an SVG element with the desired dimensions and margin.
				final Selection svg = d3.select("root").append("svg:svg").attr("class", "svg")
						.attr("width", w + m[1] + m[3]).attr("height", h + m[0] + m[2]).append("svg:g")
						.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

				// Add the clip path.
				svg.append("svg:clipPath").attr("id", "clip").append("svg:rect").attr("width", w).attr("height", h);

				// Add the area path.
				svg.append("svg:path").attr("class", "area").attr("clip-path", "url(#clip)").attr("d",
						area.apply(values));

				// Add the x-axis.
				svg.append("svg:g").attr("class", "x" + " " + "axis")
						.attr("transform", "translate(0," + h + ")").call(xAxis);

				// Add the y-axis.
				svg.append("svg:g").attr("class", "y" + " " + "axis")
						.attr("transform", "translate(" + w + ",0)").call(yAxis);

				// Add the line path.
				svg.append("svg:path").attr("class", "line").attr("clip-path", "url(#clip)").attr("d",
						line.generate(values));

				// Add a small label for the symbol name.
				svg.append("svg:text").attr("x", w - 6).attr("y", h - 6).attr("text-anchor", "end")
						.text(values.getObject(0).getSymbol());

				// On click, update the x-axis.
				svg.on("CLICK", new DatumFunction<Void>() {
					@Override
					public Void apply(final Element context, final Value d, final int index) {
						int n = values.length() - 1;
						int i = (int) Math.floor((Math.random() * n) / 2);
						int j = i + (int) Math.floor((Math.random() * n) / 2) + 1;
						x.domain(Array.fromObjects(values.getObject(i).getDate(), values.getObject(j).getDate()));
						Transition transition = svg.transition().duration(750);
						transition.select("." + "x" + "." + "axis").call(xAxis);
						transition.select("." + "area").attr("d", area.apply(values));
						transition.select("." + "line").attr("d", line.generate(values));
						return null;
					};
				});
			}
		});
		
		*/

	}

	@Override
	public void stop() {
	}
	
	//#end region
	
	//#region CLASSES

	private static class Data {
		
		//#region ATTRIBUTES
		
		private final String symbol;

		private final Date date;

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
		public Data(final String symbol, final Date date, final double price) {
			super();
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
		public Date getDate() {
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
