package com.github.javafxd3.demo.client.democases;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Formatter;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.dsv.DsvObjectAccessor;
import com.github.javafxd3.api.dsv.DsvRow;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.LinearScale;
import com.github.javafxd3.api.scales.OrdinalScale;
import com.github.javafxd3.api.svg.Axis;
import com.github.javafxd3.api.svg.Axis.Orientation;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.api.wrapper.JavaScriptObject;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class BarChart extends AbstractDemoCase {

	// #region ATTRIBUTES

	// #end region

	// #region CONSTRUCTORS

	public BarChart(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// Bundle.INSTANCE.css().ensureInjected();@Source("BarChartStyles.css")
		// String axis();
		// String x();
		// String y();
		// String bar();
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
				return new BarChart(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		final Margin margin = new Margin(20, 20, 30, 40);
		final double width = 960 - margin.left - margin.right;
		final int height = 500 - margin.top - margin.bottom;

		final Formatter formatPercent = d3.format(".0%");

		final OrdinalScale x = d3.scale().ordinal().rangeRoundBands(new Double[] { 0.0, width }, .1);

		final LinearScale y = d3.scale().linear().range(new double[] { height, 0 });

		final Axis xAxis = d3.svg().axis().scale(x).orient(Orientation.BOTTOM);

		final Axis yAxis = d3.svg().axis().scale(y).orient(Orientation.LEFT).tickFormat(formatPercent);

		final Selection svg = d3.select("root").append("svg").attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom).append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		d3.tsv("demo-data/data.tsv", new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final DsvRow row, final int index) {
				return new Data(row.get("letter").asString(), row.get("frequency").asDouble());
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final JavaScriptObject error, final Data[] data) {
				
				String[] stringArray = new String[data.length];	
				
				double maxFrequency=data[0].getFrequency();
				for(int index=0;index<data.length;index++){
					Data dataEntry = data[index];
					stringArray[index]= dataEntry.getLetter();
					Double frequency=dataEntry.getFrequency();
					if (frequency>maxFrequency){
						maxFrequency=frequency;
					}
				}								
				
				x.domain(stringArray);
				
				int maxFreq = (int) maxFrequency;
				double[] frequencies = new double[maxFreq];
				for(int index=0;index<maxFreq;index++){
					frequencies[index] = (double) index;
				}				

				y.domain(frequencies);
				

				svg.append("g").attr("class", "x" + " " + "axis")
						.attr("transform", "translate(0," + height + ")").call(xAxis);

				svg.append("g").attr("class", "y" + " " + "axis").call(yAxis).append("text")
						.attr("transform", "rotate(-90)").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end")
						.text("Frequency");

				svg.selectAll("." + "bar").data(data).enter().append("rect").attr("class", "bar")
						.attr("x", new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return x.apply(d.<Data> as().getLetter()).asDouble();
					}
				}).attr("width", x.rangeBand()).attr("y", new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return y.apply(d.<Data> as().getFrequency()).asDouble();
					}
				}).attr("height", new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Value d, final int index) {
						return height - y.apply(d.<Data> as().getFrequency()).asDouble();
					}
				});
			}

		});
	}

	@Override
	public void stop() {
	}
	
	//#end region
	
	//#region CLASSES

	private static class Data {
		private final String letter;
		private final double frequency;

		public Data(final String letter, final double frequency) {
			super();

			this.letter = letter;
			this.frequency = frequency;
		}

		public String getLetter() {
			return letter;
		}

		public double getFrequency() {
			return frequency;
		}
	}
	
	//#end region

}
