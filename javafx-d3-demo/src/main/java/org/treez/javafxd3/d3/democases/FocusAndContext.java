package org.treez.javafxd3.d3.democases;

import java.util.Date;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class FocusAndContext extends AbstractDemoCase {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public FocusAndContext(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("FocusAndContextStyles.css")
		// fac brush axis
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
				return new FocusAndContext(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		
		/*

		final Margin margin = new Margin(10, 10, 100, 40);
		final Margin margin2 = new Margin(430, 10, 20, 40);
		final int width = 960 - margin.left - margin.right;
		final int height = 500 - margin.top - margin.bottom;
		final int height2 = 500 - margin2.top - margin2.bottom;

		final TimeFormat dateFormat = d3.time().format("%b %Y");

		final TimeScale x = d3.time().scale().range(Array.fromInts(0, width));
		final TimeScale x2 = d3.time().scale().range(Array.fromInts(0, width));
		final LinearScale y = d3.scale.linear().range(Array.fromInts(height, 0));
		final LinearScale y2 = d3.scale.linear().range(Array.fromInts(height2, 0));

		final Axis xAxis = d3.svg().axis().scale(x).orient(Orientation.BOTTOM);
		final Axis xAxis2 = d3.svg().axis().scale(x2).orient(Orientation.BOTTOM);
		final Axis yAxis = d3.svg().axis().scale(y).orient(Orientation.LEFT);

		final Area area = d3.svg().area().interpolate(InterpolationMode.MONOTONE).x(new DatumFunction<Double>() {
			@Override
			public Double apply(final Element context, final Value d, final int index) {
				return x.apply(d.<Data> as().getDate()).asDouble();
			}
		}).y0(height).y1(new DatumFunction<Double>() {
			@Override
			public Double apply(final Element context, final Value d, final int index) {
				return y.apply(d.<Data> as().getPrice()).asDouble();
			}
		});

		final Area area2 = d3.svg().area().interpolate(InterpolationMode.MONOTONE).x(new DatumFunction<Double>() {
			@Override
			public Double apply(final Element context, final Value d, final int index) {
				return x2.apply(d.<Data> as().getDate()).asDouble();
			}
		}).y0(height2).y1(new DatumFunction<Double>() {
			@Override
			public Double apply(final Element context, final Value d, final int index) {
				return y2.apply(d.<Data> as().getPrice()).asDouble();
			}
		});

		Selection svg = d3.select("root").append("svg").classed("fac", true)
				.attr("width", width + margin.left + margin.right).attr("height", height + margin.top + margin.bottom);

		svg.append("defs").append("clipPath").attr("id", "clip").append("rect").attr("width", width).attr("height",
				height);

		final Selection focus = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		final Selection context = svg.append("g").attr("transform",
				"translate(" + margin2.left + "," + margin2.top + ")");

		final Brush brush = d3.svg().brush().x(x2);
		brush.on(BrushEvent.BRUSH, new DatumFunction<Void>() {
			@Override
			public Void apply(final Element context, final Value d, final int index) {
				x.domain(brush.empty() ? x2.domain() : brush.extent());
				focus.select("path").attr("d", area);
				focus.select(".x." + "axis").call(xAxis);
				return null;
			};
		});

		d3.csv("demo-data/sp500.csv", new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final DsvRow row, final int index) {
				return new Data(dateFormat.parse(row.get("date").asString()), row.get("price").asDouble());
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final JavaScriptObject error, final Data[] data) {
				x.domain(Arrays.extent(data.map(new ForEachCallback<Date>() {
					@Override
					public Date forEach(final Object thisArg, final Value d, final int index, final Object[] array) {
						return d.as(Data.class).getDate();
					}
				})));
				y.domain(Array.fromDoubles(0, Arrays.max(data, new NumericForEachCallback() {
					@Override
					public double forEach(final Object thisArg, final Value d, final int index, final Object[] array) {
						return d.as(Data.class).getPrice();
					}
				}).asInt()));
				x2.domain(x.domain());
				y2.domain(y.domain());

				focus.append("path").datum(data).attr("clip-path", "url(#clip)").attr("d", area);

				focus.append("g").attr("class", "x " + "axis").attr("transform", "translate(0," + height + ")")
						.call(xAxis);

				focus.append("g").attr("class", "y " + "axis").call(yAxis);

				context.append("path").datum(data).attr("d", area2);

				context.append("g").attr("class", "x " + "axis").attr("transform", "translate(0," + height2 + ")")
						.call(xAxis2);

				context.append("g").attr("class", "x " + "brush").call(brush).selectAll("rect").attr("y", -6)
						.attr("height", height2 + 7);

			}
		});
		
		*/

	}

	@Override
	public void stop() {
	}

	public static  String dump(JavaScriptObject javaScriptObject){
		String command = "var dump = ''; for ( var p in obj) { dump += p + ' : ' + obj[p] + '\n';}";
		javaScriptObject.eval(command);
		String result = (String) javaScriptObject.evalForString("dump");
		return result;		
	}

	//#end region

	//#region CLASSES

	private static class Data {

		//#region ATTRIOBUTES

		private final Date date;

		private final double price;

		//#end region

		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param date
		 * @param price
		 */
		public Data(final Date date, final double price) {
			super();
			this.date = date;
			this.price = price;
		}

		//#end region

		//#region ACCESSORS

		public Date getDate() {
			return date;
		}

		public double getPrice() {
			return price;
		}

		//#end region
	}

	//#end region

}
