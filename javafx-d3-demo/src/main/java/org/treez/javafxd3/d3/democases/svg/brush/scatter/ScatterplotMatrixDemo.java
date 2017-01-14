package org.treez.javafxd3.d3.democases.svg.brush.scatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.Arrays;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.dsv.Dsv;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.callback.DsvCallbackWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.CompleteDataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.IndexDataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.layout.VBox;

public class ScatterplotMatrixDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Brush brush;

	private LinearScale x;

	private LinearScale y;

	private Map<String, Array<Double>> domainByTrait;

	private Selection svg;

	private double padding;

	private int size;

	private OrdinalScale color;

	private Element brushCell;

	//#end region

	//#region CONSTRUCTORS

	public ScatterplotMatrixDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new ScatterplotMatrixDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		size = 150;
		padding = 19.5;

		x = d3.scale() //
				.linear() //
				.range(padding / 2, size - padding / 2);

		y = d3.scale() //
				.linear() //
				.range(size - padding / 2, padding / 2);

		final Axis xAxis = d3.svg().axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM) //
				.ticks(5);

		final Axis yAxis = d3.svg().axis() //
				.scale(y) //
				.orient(Orientation.LEFT) //
				.ticks(5);

		color = d3.scale() //
				.category10();

		DsvCallback<DsvRow> csvCallback = new DsvCallbackWrapper<>(DsvRow.class, webEngine, (array) -> {

			domainByTrait = new HashMap<>();

			DsvRow firstRow = array.get(0);
			Array<String> traits = d3.keys(firstRow);

			Array<String> filteredTraits = traits.filter((element) -> {
				Boolean includeElement = !"species".equals(element);
				return includeElement;
			});

			final int n = filteredTraits.length();

			filteredTraits.forEach((traitObj) -> {

				String trait = (String) traitObj;
				// for the current trait, get the extent=domain=(min and
				// max), and save it in the map					

				Array<Double> value = Arrays.extent(array, (dsvRow) -> {
					if(dsvRow==null){
						return null;
					}
					return dsvRow.get(trait).asDouble();
				}, webEngine);

				domainByTrait.put(trait, value);

			});

			xAxis.tickSize(size * n);
			yAxis.tickSize(-size * n);

			DataFunction<Void> brushStartFunction = new CompleteDataFunctionWrapper<>(new DataFunction<Void>() {
				@Override
				public Void apply(final Object context, final Object d, final int index) {

					if (context == null) {
						return null;
					}

					if (d == null) {
						return null;
					}

					Element element = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
					Point point = ConversionUtil.convertObjectTo(d, Point.class, webEngine);

					brushstart(element, point);
					return null;
				}
			});

			DataFunction<Void> brushFunction = new DataFunctionWrapper<>(Point.class, webEngine, (point) -> {
				if (point == null) {
					return null;
				}
				try {
					brushmove(point);
				} catch (Exception exception) {
					System.out.println("Could not execute point move");
					return null;
				}
				return null;
			});

			DataFunction<Void> brushEndFunction = new DataFunctionWrapper<>(() -> {
				brushend();
			});

			brush = d3.svg() //
					.brush() //
					.x(x) //
					.y(y) //
					.on(BrushEvent.BRUSH_START, brushStartFunction) //
					.on(BrushEvent.BRUSH, brushFunction) //
					.on(BrushEvent.BRUSH_END, brushEndFunction);

			//create sub plots
			svg = d3.select("svg") //
					.attr("width", size * n + padding) //
					.attr("height", size * n + padding) //
					.append("g") //
					.attr("transform", "translate(" + padding + "," + padding / 2 + ")");

			//draw x axis
			DataFunction<String> xTransformFunction = new IndexDataFunctionWrapper<>((index) -> {
				return "translate(" + (n - index - 1) * size + ",0)";
			});

			DataFunction<Void> xAxisDataFunction = new CompleteDataFunctionWrapper<>(new DataFunction<Void>() {
				@Override
				public Void apply(final Object context, final Object d, final int index) {

					Element element = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
					String trait = ConversionUtil.convertObjectTo(d, String.class, webEngine);

					Array<Double> domain = domainByTrait.get(trait);
					if (domain != null) {
						x.domain(domain);
						d3.select(element).call(xAxis);
					}
					return null;
				}
			});

			svg.selectAll(".x.axis") //
					.data(traits) //
					.enter() //
					.append("g") //
					.attr("class", "x axis") //
					.attr("transform", xTransformFunction) //
					.each(xAxisDataFunction);

			//draw y axis
			DataFunction<String> yTransformFunction = new IndexDataFunctionWrapper<>((index) -> {
				return "translate(0," + index * size + ")";
			});

			DataFunction<Void> yAxisDataFunction = new CompleteDataFunctionWrapper<>(new DataFunction<Void>() {
				@Override
				public Void apply(final Object context, final Object d, final int index) {

					Element element = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
					String trait = ConversionUtil.convertObjectTo(d, String.class, webEngine);

					Array<Double> domain = domainByTrait.get(trait);
					if (domain != null) {
						y.domain(domain);
						d3.select(element).call(yAxis);
					}

					return null;
				}
			});

			svg.selectAll(".y.axis") //
					.data(traits) //
					.enter() //
					.append("g") //
					.attr("class", "y axis") //
					.attr("transform", yTransformFunction) //
					.each(yAxisDataFunction);

			//plot points
			Array<Point> crossArray = cross(traits, traits);

			DataFunction<String> transformFunction = new DataFunctionWrapper<>(Point.class, webEngine, (point) -> {
				return "translate(" + (n - point.i - 1) * size + "," + point.j * size + ")";
			});

			DataFunction<Void> plotFunction = new CompleteDataFunctionWrapper<>(new DataFunction<Void>() {
				@Override
				public Void apply(final Object context, final Object d, final int index) {

					Element element = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
					Point point = ConversionUtil.convertObjectTo(d, Point.class, webEngine);
					if (point == null) {
						return null;
					}

					plot(element, point, array);
					return null;
				}
			});

			Selection cell = svg.selectAll(".cell") //
					.data(crossArray) //
					.enter() //
					.append("g") //
					.attr("class", "cell") //
					.attr("transform", transformFunction) //
					.each(plotFunction);

			//titles for the diagonal sub plots			
			DataFunction<Element> diagonalFilterFunction = new CompleteDataFunctionWrapper<>(
					new DataFunction<Element>() {
						@Override
						public Element apply(final Object context, final Object d, final int index) {

							Element element = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
							Point point = ConversionUtil.convertObjectTo(d, Point.class, webEngine);
							if (point == null) {
								return null;
							}

							return point.i == point.j ? element : null;
						}
					});

			DataFunction<String> textFunction = new DataFunctionWrapper<>(Point.class, webEngine, (point) -> {
				return point.xTrait;
			});

			cell.filter(diagonalFilterFunction) //
					.append("text") //
					.attr("x", padding) //
					.attr("y", padding) //
					.attr("dy", ".71em") //
					.text(textFunction);

			cell.call(brush);

			d3.select("svg") //
					.style("height", size * n + padding + 20 + "px");
		}

		);

		String csvData = getCsvData();

		Dsv<DsvRow> csv = d3.<DsvRow> csv();
		Array<DsvRow> data = csv.parse(csvData);
		csvCallback.get(null, data.getJsObject());

		//d3.csv("demo-data/flowers.csv", csvCallback);

	}

	@Override
	public void stop() {

	}

	private void plot(final Element context, final Point p, final Array<DsvRow> data) {
		Selection cell = d3.select(context);

		if (p == null) {
			return;
		}

		Array<Double> xDomain = domainByTrait.get(p.xTrait);
		if (xDomain != null) {
			x.domain(xDomain);
		}

		Array<Double> yDomain = domainByTrait.get(p.yTrait);
		if (yDomain != null) {
			y.domain(yDomain);
		}

		cell.append("rect") //
				.attr("class", "frame") //
				.attr("x", padding / 2) //
				.attr("y", padding / 2) //
				.attr("width", size - padding) //
				.attr("height", size - padding);

		DataFunction<String> cxDataFunction = new DataFunctionWrapper<>(DsvRow.class, webEngine, (row) -> {
			if (row == null) {
				return null;
			}
			Value v = row.get(p.xTrait);
			String asString = x.apply(v.asDouble()).asString();
			return asString;
		});

		DataFunction<String> cyDataFunction = new DataFunctionWrapper<>(DsvRow.class, webEngine, (row) -> {
			if (row == null) {
				return null;
			}
			Value v = row.get(p.yTrait);
			String asString = y.apply(v.asDouble()).asString();
			return asString;
		});

		DataFunction<String> fillDataFunction = new DataFunctionWrapper<>(DsvRow.class, webEngine, (row) -> {
			if (row == null) {
				return null;
			}
			String species = row.get("species").asString();
			String fill = color.apply(species).asString();
			return fill;
		});

		cell.selectAll("circle") //
				.data(data) //
				.enter() //
				.append("circle") //
				.attr("cx", cxDataFunction) //
				.attr("cy", cyDataFunction) //
				.attr("r", 3) //
				.style("fill", fillDataFunction);
	}

	// Clear the previously-active brush, if any.
	private void brushstart(final Element context, final Point p) {
		// Clear the previously-active brush, if any.
		if (brushCell != context) {
			if (brushCell != null) {
				d3.select(brushCell) //
						.call(brush.clear());

				Array<Double> xDomain = domainByTrait.get(p.xTrait);
				x.domain(xDomain);

				Array<Double> yDomain = domainByTrait.get(p.yTrait);
				y.domain(yDomain);
			}

			brushCell = context;
		}
	}

	// Highlight the selected circles.
	private void brushmove(final Point p) {

		if (p == null) {
			return;
		}

		final Array<Double> e = brush.extent();

		DataFunction<Boolean> hideFunction = new DataFunctionWrapper<>(DsvRow.class, webEngine, (row) -> {

			try {
				if (row == null) {
					return false;
				}

				// the plot coords			
				Value x = row.get(p.xTrait);
				double px = x.asDouble();

				Value y = row.get(p.yTrait);
				double py = y.asDouble();

				// the extent of the brush
				double ex0 = e.get(0, 0);
				double ey0 = e.get(0, 1);
				double ex1 = e.get(1, 0);
				double ey1 = e.get(1, 1);

				// hide it (returns true) if the plot is outside the brush
				// extent
				boolean b = ex0 > px || px > ex1 || ey0 > py || py > ey1;
				return b;
			} catch (Exception exception) {
				return true;
			}
		});

		svg.selectAll("circle") //				
				.classed("hidden", hideFunction);
	}

	// If the brush is empty, select all circles.
	private void brushend() {
		if (brush.empty()) {
			svg.selectAll(".hidden") //
					.classed("hidden", false);
		}
	}

	private Array<Point> cross(final Array<String> a, final Array<String> b) {
		List<Point> crossArray = new ArrayList<>(); //Point[a.length];
		int n = a.length();
		int m = b.length();
		for (int i = -1; ++i < n;) {
			for (int j = -1; ++j < m;) {

				String x = a.get(i, String.class);
				String y = b.get(j, String.class);

				Point point = new Point(x, y, i, j);
				crossArray.add(point);

			}
		}
		return Array.fromList(webEngine, crossArray);
	}

	//#end region

	private String getCsvData() {
		return "sepal length,sepal width,petal length,petal width,species\n" + //
				"5.1,3.5,1.4,0.2,setosa\n" + //
				"4.9,3.0,1.4,0.2,setosa\n" + //
				"4.7,3.2,1.3,0.2,setosa\n" + //
				"4.6,3.1,1.5,0.2,setosa\n" + //
				"5.0,3.6,1.4,0.2,setosa\n" + //
				"5.4,3.9,1.7,0.4,setosa\n" + //
				"4.6,3.4,1.4,0.3,setosa\n" + //
				"5.0,3.4,1.5,0.2,setosa\n" + //
				"4.4,2.9,1.4,0.2,setosa\n" + //
				"4.9,3.1,1.5,0.1,setosa\n" + //
				"5.4,3.7,1.5,0.2,setosa\n" + //
				"4.8,3.4,1.6,0.2,setosa\n" + //
				"4.8,3.0,1.4,0.1,setosa\n" + //
				"4.3,3.0,1.1,0.1,setosa\n" + //
				"5.8,4.0,1.2,0.2,setosa\n" + //
				"5.7,4.4,1.5,0.4,setosa\n" + //
				"5.4,3.9,1.3,0.4,setosa\n" + //
				"5.1,3.5,1.4,0.3,setosa\n" + //
				"5.7,3.8,1.7,0.3,setosa\n" + //
				"5.1,3.8,1.5,0.3,setosa\n" + //
				"5.4,3.4,1.7,0.2,setosa\n" + //
				"5.1,3.7,1.5,0.4,setosa\n" + //
				"4.6,3.6,1.0,0.2,setosa\n" + //
				"5.1,3.3,1.7,0.5,setosa\n" + //
				"4.8,3.4,1.9,0.2,setosa\n" + //
				"5.0,3.0,1.6,0.2,setosa\n" + //
				"5.0,3.4,1.6,0.4,setosa\n" + //
				"5.2,3.5,1.5,0.2,setosa\n" + //
				"5.2,3.4,1.4,0.2,setosa\n" + //
				"4.7,3.2,1.6,0.2,setosa\n" + //
				"4.8,3.1,1.6,0.2,setosa\n" + //
				"5.4,3.4,1.5,0.4,setosa\n" + //
				"5.2,4.1,1.5,0.1,setosa\n" + //
				"5.5,4.2,1.4,0.2,setosa\n" + //
				"4.9,3.1,1.5,0.2,setosa\n" + //
				"5.0,3.2,1.2,0.2,setosa\n" + //
				"5.5,3.5,1.3,0.2,setosa\n" + //
				"4.9,3.6,1.4,0.1,setosa\n" + //
				"4.4,3.0,1.3,0.2,setosa\n" + //
				"5.1,3.4,1.5,0.2,setosa\n" + //
				"5.0,3.5,1.3,0.3,setosa\n" + //
				"4.5,2.3,1.3,0.3,setosa\n" + //
				"4.4,3.2,1.3,0.2,setosa\n" + //
				"5.0,3.5,1.6,0.6,setosa\n" + //
				"5.1,3.8,1.9,0.4,setosa\n" + //
				"4.8,3.0,1.4,0.3,setosa\n" + //
				"5.1,3.8,1.6,0.2,setosa\n" + //
				"4.6,3.2,1.4,0.2,setosa\n" + //
				"5.3,3.7,1.5,0.2,setosa\n" + //
				"5.0,3.3,1.4,0.2,setosa\n" + //
				"7.0,3.2,4.7,1.4,versicolor\n" + //
				"6.4,3.2,4.5,1.5,versicolor\n" + //
				"6.9,3.1,4.9,1.5,versicolor\n" + //
				"5.5,2.3,4.0,1.3,versicolor\n" + //
				"6.5,2.8,4.6,1.5,versicolor\n" + //
				"5.7,2.8,4.5,1.3,versicolor\n" + //
				"6.3,3.3,4.7,1.6,versicolor\n" + //
				"4.9,2.4,3.3,1.0,versicolor\n" + //
				"6.6,2.9,4.6,1.3,versicolor\n" + //
				"5.2,2.7,3.9,1.4,versicolor\n" + //
				"5.0,2.0,3.5,1.0,versicolor\n" + //
				"5.9,3.0,4.2,1.5,versicolor\n" + //
				"6.0,2.2,4.0,1.0,versicolor\n" + //
				"6.1,2.9,4.7,1.4,versicolor\n" + //
				"5.6,2.9,3.6,1.3,versicolor\n" + //
				"6.7,3.1,4.4,1.4,versicolor\n" + //
				"5.6,3.0,4.5,1.5,versicolor\n" + //
				"5.8,2.7,4.1,1.0,versicolor\n" + //
				"6.2,2.2,4.5,1.5,versicolor\n" + //
				"5.6,2.5,3.9,1.1,versicolor\n" + //
				"5.9,3.2,4.8,1.8,versicolor\n" + //
				"6.1,2.8,4.0,1.3,versicolor\n" + //
				"6.3,2.5,4.9,1.5,versicolor\n" + //
				"6.1,2.8,4.7,1.2,versicolor\n" + //
				"6.4,2.9,4.3,1.3,versicolor\n" + //
				"6.6,3.0,4.4,1.4,versicolor\n" + //
				"6.8,2.8,4.8,1.4,versicolor\n" + //
				"6.7,3.0,5.0,1.7,versicolor\n" + //
				"6.0,2.9,4.5,1.5,versicolor\n" + //
				"5.7,2.6,3.5,1.0,versicolor\n" + //
				"5.5,2.4,3.8,1.1,versicolor\n" + //
				"5.5,2.4,3.7,1.0,versicolor\n" + //
				"5.8,2.7,3.9,1.2,versicolor\n" + //
				"6.0,2.7,5.1,1.6,versicolor\n" + //
				"5.4,3.0,4.5,1.5,versicolor\n" + //
				"6.0,3.4,4.5,1.6,versicolor\n" + //
				"6.7,3.1,4.7,1.5,versicolor\n" + //
				"6.3,2.3,4.4,1.3,versicolor\n" + //
				"5.6,3.0,4.1,1.3,versicolor\n" + //
				"5.5,2.5,4.0,1.3,versicolor\n" + //
				"5.5,2.6,4.4,1.2,versicolor\n" + //
				"6.1,3.0,4.6,1.4,versicolor\n" + //
				"5.8,2.6,4.0,1.2,versicolor\n" + //
				"5.0,2.3,3.3,1.0,versicolor\n" + //
				"5.6,2.7,4.2,1.3,versicolor\n" + //
				"5.7,3.0,4.2,1.2,versicolor\n" + //
				"5.7,2.9,4.2,1.3,versicolor\n" + //
				"6.2,2.9,4.3,1.3,versicolor\n" + //
				"5.1,2.5,3.0,1.1,versicolor\n" + //
				"5.7,2.8,4.1,1.3,versicolor\n" + //
				"6.3,3.3,6.0,2.5,virginica\n" + //
				"5.8,2.7,5.1,1.9,virginica\n" + //
				"7.1,3.0,5.9,2.1,virginica\n" + //
				"6.3,2.9,5.6,1.8,virginica\n" + //
				"6.5,3.0,5.8,2.2,virginica\n" + //
				"7.6,3.0,6.6,2.1,virginica\n" + //
				"4.9,2.5,4.5,1.7,virginica\n" + //
				"7.3,2.9,6.3,1.8,virginica\n" + //
				"6.7,2.5,5.8,1.8,virginica\n" + //
				"7.2,3.6,6.1,2.5,virginica\n" + //
				"6.5,3.2,5.1,2.0,virginica\n" + //
				"6.4,2.7,5.3,1.9,virginica\n" + //
				"6.8,3.0,5.5,2.1,virginica\n" + //
				"5.7,2.5,5.0,2.0,virginica\n" + //
				"5.8,2.8,5.1,2.4,virginica\n" + //
				"6.4,3.2,5.3,2.3,virginica\n" + //
				"6.5,3.0,5.5,1.8,virginica\n" + //
				"7.7,3.8,6.7,2.2,virginica\n" + //
				"7.7,2.6,6.9,2.3,virginica\n" + //
				"6.0,2.2,5.0,1.5,virginica\n" + //
				"6.9,3.2,5.7,2.3,virginica\n" + //
				"5.6,2.8,4.9,2.0,virginica\n" + //
				"7.7,2.8,6.7,2.0,virginica\n" + //
				"6.3,2.7,4.9,1.8,virginica\n" + //
				"6.7,3.3,5.7,2.1,virginica\n" + //
				"7.2,3.2,6.0,1.8,virginica\n" + //
				"6.2,2.8,4.8,1.8,virginica\n" + //
				"6.1,3.0,4.9,1.8,virginica\n" + //
				"6.4,2.8,5.6,2.1,virginica\n" + //
				"7.2,3.0,5.8,1.6,virginica\n" + //
				"7.4,2.8,6.1,1.9,virginica\n" + //
				"7.9,3.8,6.4,2.0,virginica\n" + //
				"6.4,2.8,5.6,2.2,virginica\n" + //
				"6.3,2.8,5.1,1.5,virginica\n" + //
				"6.1,2.6,5.6,1.4,virginica\n" + //
				"7.7,3.0,6.1,2.3,virginica\n" + //
				"6.3,3.4,5.6,2.4,virginica\n" + //
				"6.4,3.1,5.5,1.8,virginica\n" + //
				"6.0,3.0,4.8,1.8,virginica\n" + //
				"6.9,3.1,5.4,2.1,virginica\n" + //
				"6.7,3.1,5.6,2.4,virginica\n" + //
				"6.9,3.1,5.1,2.3,virginica\n" + //
				"5.8,2.7,5.1,1.9,virginica\n" + //
				"6.8,3.2,5.9,2.3,virginica\n" + //
				"6.7,3.3,5.7,2.5,virginica\n" + //
				"6.7,3.0,5.2,2.3,virginica\n" + //
				"6.3,2.5,5.0,1.9,virginica\n" + //
				"6.5,3.0,5.2,2.0,virginica\n" + //
				"6.2,3.4,5.4,2.3,virginica\n" + //
				"5.9,3.0,5.1,1.8,virginica\n";
	}

}
