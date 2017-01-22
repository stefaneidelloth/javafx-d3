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

import javafx.application.Platform;
import javafx.scene.layout.VBox;

public class ScatterPlotMatrixDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Brush brush;

	private LinearScale x;

	private LinearScale y;

	private Map<String, Array<Double>> domainByTrait;

	private Selection svg;

	private double padding;

	private int size;

	private OrdinalScale color;

	private Element oldBrushCell = null;

	//#end region

	//#region CONSTRUCTORS

	public ScatterPlotMatrixDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new ScatterPlotMatrixDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		d3.logNumberOfTempVars();

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

		DsvCallback<DsvRow> csvCallback = new DsvCallbackWrapper<>(engine, (array) -> {

			Array<String> traits = getTraits(array);
			storeDomainsByTraits(array, traits);

			final int n = traits.length();
			xAxis.tickSize(size * n);
			yAxis.tickSize(-size * n);

			createBrush(); //the brush listens to mouse selections

			createSubPlots(xAxis, yAxis, traits, n);

			Selection subPlots = plotCsvData(array, traits, n);

			setTitleForDiagonalSubPlots(subPlots);

			subPlots.call(brush);

			d3.select("svg") //
					.style("height", size * n + padding + 20 + "px");
		}

		);

		String csvData = getCsvData();

		Dsv<DsvRow> csv = d3.<DsvRow> csv();
		Array<DsvRow> data = csv.parse(csvData);
		csvCallback.get(null, data.getJsObject());

		d3.logNumberOfTempVars();

		//d3.csv("demo-data/flowers.csv", csvCallback);

	}

	private void createBrush() {

		brush = d3.svg() //
				.brush() //
				.x(x) //
				.y(y) //
				.on(BrushEvent.BRUSH_START, new BrushStartFunction(this,engine)) //
				.on(BrushEvent.BRUSH, new BrushMoveFunction(this)) //
				.on(BrushEvent.BRUSH_END, new BrushEndFunction(this));
	}

	private void createSubPlots(final Axis xAxis, final Axis yAxis, Array<String> traits, final int n) {
		svg = d3.select("svg") //
				.attr("width", size * n + padding) //
				.attr("height", size * n + padding) //
				.append("g") //
				.attr("transform", "translate(" + padding + "," + padding / 2 + ")");

		//draw x axis
		DataFunction<String> xTransformFunction = new IndexDataFunctionWrapper<>((index) -> {
			return "translate(" + (n - index - 1) * size + ",0)";
		});

		DataFunction<Void> xAxisDataFunction = new CompleteDataFunctionWrapper<>(engine, new DataFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {

				Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
				String trait = ConversionUtil.convertObjectTo(d, String.class, engine);

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

		DataFunction<Void> yAxisDataFunction = new CompleteDataFunctionWrapper<>(engine,new DataFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {

				Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
				String trait = ConversionUtil.convertObjectTo(d, String.class, engine);

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
	}

	private Selection plotCsvData(Array<DsvRow> array, Array<String> traits, final int n) {
		Array<Point> points = points(traits, traits);

		DataFunction<String> transformFunction = new DataFunctionWrapper<>(Point.class, engine, (point) -> {
			return "translate(" + (n - point.i - 1) * size + "," + point.j * size + ")";
		});

		DataFunction<Void> plotFunction = new CompleteDataFunctionWrapper<>(engine, new DataFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {

				Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
				Point point = ConversionUtil.convertObjectTo(d, Point.class, engine);
				if (point == null) {
					return null;
				}

				plot(element, point, array);
				return null;
			}
		});

		Selection subPlots = svg.selectAll(".cell") //
				.data(points) //
				.enter() //
				.append("g") //
				.attr("class", "cell") //
				.attr("transform", transformFunction) //
				.each(plotFunction);
		return subPlots;
	}

	private void setTitleForDiagonalSubPlots(Selection subPlots) {
		DataFunction<Element> diagonalFilterFunction = new CompleteDataFunctionWrapper<>(engine, new DataFunction<Element>() {
			@Override
			public Element apply(final Object context, final Object d, final int index) {

				Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
				Point point = ConversionUtil.convertObjectTo(d, Point.class, engine);
				if (point == null) {
					return null;
				}

				return point.i == point.j ? element : null;
			}
		});

		DataFunction<String> textFunction = new DataFunctionWrapper<>(Point.class, engine, (point) -> {
			return point.xTrait;
		});

		subPlots.filter(diagonalFilterFunction) //
				.append("text") //
				.attr("x", padding) //
				.attr("y", padding) //
				.attr("dy", ".71em") //
				.text(textFunction);
	}

	private void storeDomainsByTraits(Array<DsvRow> array, Array<String> traits) {
		domainByTrait = new HashMap<>();
		traits.forEach((traitObj) -> {

			String trait = (String) traitObj;
			// for the current trait, get the extent=domain=(min and
			// max), and save it in the map					

			Array<Double> domain = Arrays.extent(array, DsvRow.class, (dsvRow) -> {
				return dsvRow.get(trait).asDouble();
			}, engine);

			domainByTrait.put(trait, domain);
		});
	}

	private Array<String> getTraits(Array<DsvRow> array) {
		DsvRow firstRow = array.get(0, DsvRow.class);
		Array<String> traits = d3.keys(firstRow);

		Array<String> filteredTraits = traits.filter((element) -> {
			Boolean includeElement = !"species".equals(element);
			return includeElement;
		});
		return filteredTraits;
	}

	@Override
	public void stop() {

	}

	private void plot(Element context, Point p, Array<DsvRow> dsvRows) {
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

		DataFunction<String> cxDataFunction = new DataFunctionWrapper<>(DsvRow.class, engine, (row) -> {
			if (row == null) {
				return null;
			}
			Value v = row.get(p.xTrait);
			String asString = x.apply(v.asDouble()).asString();
			return asString;
		});

		DataFunction<String> cyDataFunction = new DataFunctionWrapper<>(DsvRow.class, engine, (row) -> {
			if (row == null) {
				return null;
			}
			Value v = row.get(p.yTrait);
			String asString = y.apply(v.asDouble()).asString();
			return asString;
		});

		DataFunction<String> fillDataFunction = new DataFunctionWrapper<>(DsvRow.class, engine, (row) -> {
			if (row == null) {
				return null;
			}
			String species = row.get("species").asString();
			String fill = color.apply(species).asString();
			return fill;
		});

		cell.selectAll("circle") //
				.data(dsvRows) //
				.enter() //
				.append("circle") //
				.attr("cx", cxDataFunction) //
				.attr("cy", cyDataFunction) //
				.attr("r", 3) //
				.style("fill", fillDataFunction);
	}

	// Clear the previously-active brush, if any.
	public void brushStart(final Element context, final Point p) {
		// Clear the previously-active brush, if any.
		if (context != oldBrushCell) {
			
			d3.select(oldBrushCell) //
						.call(brush.clear());			

			Array<Double> xDomain = domainByTrait.get(p.xTrait);
			x.domain(xDomain);

			Array<Double> yDomain = domainByTrait.get(p.yTrait);
			y.domain(yDomain);

			oldBrushCell = context;
		}

		d3.logNumberOfTempVars("after brush start");
	}

	// Highlight the selected circles.
	public void  brushMove(final Point p) {

		if (p == null) {
			return;
		}

		final Array<Double> e = brush.extent();

		DataFunction<Boolean> hideFunction = new DataFunctionWrapper<>(DsvRow.class, engine, (row) -> {

			try {

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
				throw new IllegalStateException("Could not determine hidden value", exception);

			}
		});

		Selection circles = svg.selectAll("circle");
		circles.classed("hidden", hideFunction);

		d3.logNumberOfTempVars("after brush move");
	}

	// If the brush is empty, select all circles.
	public void brushEnd() {
		if (brush.empty()) {
			Platform.runLater(() -> {
				svg.selectAll(".hidden") //
						.classed("hidden", false);
			});

		}

		d3.logNumberOfTempVars("after brush end");
	}

	private Array<Point> points(final Array<String> a, final Array<String> b) {
		List<Point> points = new ArrayList<>();
		int n = a.length();
		int m = b.length();
		for (int i = -1; ++i < n;) {
			for (int j = -1; ++j < m;) {

				String x = a.get(i, String.class);
				String y = b.get(j, String.class);

				Point point = new Point(x, y, i, j);
				points.add(point);

			}
		}
		return Array.fromList(engine, points);
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
