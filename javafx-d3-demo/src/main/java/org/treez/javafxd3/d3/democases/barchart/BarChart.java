package org.treez.javafxd3.d3.democases.barchart;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Formatter;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.democases.Margin;

import javafx.scene.layout.VBox;
import netscape.javascript.JSObject;

@SuppressWarnings("javadoc")
public class BarChart extends AbstractDemoCase {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	public BarChart(D3 d3, VBox demoPreferenceBox) {
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

		final Selection svg = d3.select("svg") //
				.attr("width", width + margin.left + margin.right) //
				.attr("height", height + margin.top + margin.bottom) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		d3.tsv("demo-data/data.tsv", new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final Object row, final int index) {

				JSObject jsRow = (JSObject) row;
				DsvRow dsvRow = new DsvRow(webEngine, jsRow);

				return new Data(dsvRow.get("letter").asString(), dsvRow.get("frequency").asDouble());
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final Object error, final Object dataArray) {

				JSObject jsDsvDataArray = (JSObject) dataArray;
				Array<Data> values = new Array<Data>(webEngine, jsDsvDataArray);
				List<? extends Data> valueList = values.asList(Data.class);

				String[] stringArray = new String[valueList.size()];

				double maxFrequency = valueList.get(0).getFrequency();
				for (int index = 0; index < valueList.size(); index++) {
					Data dataEntry = valueList.get(index);
					stringArray[index] = dataEntry.getLetter();
					Double frequency = dataEntry.getFrequency();
					if (frequency > maxFrequency) {
						maxFrequency = frequency;
					}
				}

				x.domain(stringArray);

				int maxFreq = (int) maxFrequency;
				double[] frequencies = new double[maxFreq];
				for (int index = 0; index < maxFreq; index++) {
					frequencies[index] = (double) index;
				}

				y.domain(frequencies);

				svg.append("g").attr("class", "x" + " " + "axis").attr("transform", "translate(0," + height + ")")
						.call(xAxis);

				svg.append("g").attr("class", "y" + " " + "axis").call(yAxis).append("text")
						.attr("transform", "rotate(-90)").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end")
						.text("Frequency");

				/*
				 * 
				 * svg.selectAll("." +
				 * "bar").data(valueList).enter().append("rect").attr("class",
				 * "bar") .attr("x", new DatumFunction<Double>() {
				 * 
				 * @Override public Double apply(final Object context, final
				 * Object d, final int index) {
				 * 
				 * Value datum = (Value) d; Element element =(Element) context;
				 * 
				 * return x.apply(datum.<Data> as().getLetter()).asDouble(); }
				 * }).attr("width", x.rangeBand()).attr("y", new
				 * DatumFunction<Double>() {
				 * 
				 * @Override public Double apply(final Object context, final
				 * Object d, final int index) {
				 * 
				 * Value datum = (Value) d; Element element =(Element) context;
				 * 
				 * return y.apply(datum.<Data> as().getFrequency()).asDouble();
				 * } }).attr("height", new DatumFunction<Double>() {
				 * 
				 * @Override public Double apply(final Object context, final
				 * Object d, final int index) {
				 * 
				 * Value datum = (Value) d; Element element =(Element) context;
				 * 
				 * return height - y.apply(datum.<Data>
				 * as().getFrequency()).asDouble(); } });
				 * 
				 */

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
