package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Formatter;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.demo.Margin;
import org.treez.javafxd3.d3.dsv.Dsv;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;

import javafx.scene.layout.VBox;

public class BarChart extends AbstractDemoCase {

	//#region ATTRIBUTES

	private OrdinalScale x;

	private LinearScale y;

	private int height;

	private Selection svgGroup;

	private Axis xAxis;

	private Axis yAxis;

	//#end region

	//#region CONSTRUCTORS

	public BarChart(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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
		height = 500 - margin.top - margin.bottom;

		final Formatter formatPercent = d3.format(".0%");

		x = d3.scale() //
				.ordinal() //
				.rangeRoundBands(new Double[] { 0.0, width }, .1);

		y = d3.scale() //
				.linear() //
				.range(new double[] { height, 0 });

		xAxis = d3.svg() //
				.axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM);

		yAxis = d3.svg() //
				.axis() //
				.scale(y) //
				.orient(Orientation.LEFT) //
				.tickFormat(formatPercent);

		svgGroup = d3.select("svg") //
				.attr("width", width + margin.left + margin.right) //
				.attr("height", height + margin.top + margin.bottom) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		DsvObjectAccessor<BarChartData> accessor = new BarChartObjectAccessor(webEngine);
		DsvCallback<BarChartData> callback = new BarChartCallback(webEngine, BarChart.this);

		Dsv<BarChartData> tsv = d3.<BarChartData> tsv();

		String tsvData = getTsvData();

		Array<BarChartData> data = tsv.parse(tsvData, accessor);
		callback.get(null, data.getJsObject());

		//TODO: reading the text file from url does not work yet:
		//String dataUrl = "http://raw.githubusercontent.com/stefaneidelloth/javafx-d3/master/javafx-d3-demo/src/main/resources/demo-data/data.tsv"; //
		//d3.tsv(dataUrl,accessor ,callback );
	}	

	@Override
	public void stop() {

	}
	
	private String getTsvData() {
		String tsvData = "letter	frequency\n" + //
				"A	.08167\n" + //
				"B	.01492\n" + //
				"C	.02780\n" + //
				"D	.04253\n" + //		
				"E	.12702\n" + //
				"F	.02288\n" + //
				"G	.02022\n" + //
				"H	.06094\n" + //
				"I	.06973\n" + //
				"J	.00153\n" + //
				"K	.00747\n" + //
				"L	.04025\n" + //
				"M	.02517\n" + //
				"N	.06749\n" + //
				"O	.07507\n" + //
				"P	.01929\n" + //
				"Q	.00098\n" + //
				"R	.05987\n" + //
				"S	.06333\n" + //
				"T	.09056\n" + //
				"U	.02758\n" + //
				"V	.01037\n" + //
				"W	.02465\n" + //
				"X	.00150\n" + //
				"Y	.01971\n" + //
				"Z	.00074\n";
		return tsvData;
	}

	//#end region

	//#region ACCESSORS

	public OrdinalScale getXScale() {
		return x;
	}

	public LinearScale getYScale() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public Selection getSvgGroup() {
		return svgGroup;
	}

	public Axis getXAxis() {
		return xAxis;
	}

	public Axis getYAxis() {
		return yAxis;
	}

	//#end region

}
