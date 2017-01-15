package org.treez.javafxd3.d3.democases.focus;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.Arrays;
import org.treez.javafxd3.d3.arrays.foreach.ForEachCallback;
import org.treez.javafxd3.d3.arrays.foreach.ForEachCallbackWrapper;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.demo.Margin;
import org.treez.javafxd3.d3.dsv.Dsv;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.accessor.DsvObjectAccessorWrapper;
import org.treez.javafxd3.d3.functions.callback.DsvCallbackWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.svg.Area;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.svg.InterpolationMode;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeFormat;
import org.treez.javafxd3.d3.time.TimeScale;

import javafx.scene.layout.VBox;

public class FocusAndContext extends AbstractDemoCase {

	//#region CONSTRUCTORS

	public FocusAndContext(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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

		final Margin margin = new Margin(10, 10, 100, 40);
		final Margin margin2 = new Margin(430, 10, 20, 40);

		final int width = 960 - margin.left - margin.right;
		final int height = 500 - margin.top - margin.bottom;
		final int height2 = 500 - margin2.top - margin2.bottom;

		final TimeFormat dateFormat = d3.time() //
				.format("%b %Y");

		final TimeScale x = d3.time() //
				.scale() //
				.range(0, width);

		final TimeScale x2 = d3.time() //
				.scale() //
				.range(0, width);

		final LinearScale y = d3.scale() //
				.linear() //
				.range(height, 0);

		final LinearScale y2 = d3.scale() //
				.linear() //
				.range(height2, 0);

		final Axis xAxis = d3.svg() //
				.axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM);

		final Axis xAxis2 = d3.svg() //
				.axis() //
				.scale(x2) //
				.orient(Orientation.BOTTOM);

		final Axis yAxis = d3.svg() //
				.axis() //
				.scale(y) //
				.orient(Orientation.LEFT);

		DataFunction<Double> xDataFunction = new DataFunctionWrapper<>(FocusAndContextData.class, webEngine, (data) -> {
			if (data == null) {
				return 0d;
			}

			JsDate date = data.getDate();
			Value scaledValue = x.apply(date);
			if (scaledValue == null) {
				return 0d;
			}
			return scaledValue.asDouble();
		});

		DataFunction<Double> y1DataFunction = new DataFunctionWrapper<>(FocusAndContextData.class, webEngine,
				(data) -> {
					if (data == null) {
						return 0d;
					}
					Double price = data.getPrice();
					Value scaledValue = y.apply(price);
					if (scaledValue == null) {
						return 0d;
					}
					Double y1Val = scaledValue.asDouble();
					return y1Val;
				});

		final Area area = d3.svg() //
				.area() //
				.interpolate(InterpolationMode.MONOTONE) //
				.x(xDataFunction) //
				.y0(height) //
				.y1(y1DataFunction);

		DataFunction<Double> x2DataFunction = new DataFunctionWrapper<Double, FocusAndContextData>(
				FocusAndContextData.class, webEngine, (data) -> {
					JsDate date = data.getDate();
					Value scaledValue = x2.apply(date);
					if (scaledValue == null) {
						return 0d;
					}
					return scaledValue.asDouble();
				});

		DataFunction<Double> y2DataFunction = new DataFunctionWrapper<>(FocusAndContextData.class, webEngine,
				(data) -> {
					Double price = data.getPrice();
					Value scaledValue = y2.apply(price);
					if (scaledValue == null) {
						return 0d;
					}
					Double y2Val = scaledValue.asDouble();
					return y2Val;
				});

		final Area area2 = d3.svg() //
				.area() //
				.interpolate(InterpolationMode.MONOTONE) //
				.x(x2DataFunction) //
				.y0(height2) //
				.y1(y2DataFunction);

		Selection svg = d3.select("svg") //				
				.attr("width", width + margin.left + margin.right) //
				.attr("height", height + margin.top + margin.bottom);

		svg.append("defs") //
				.append("clipPath") //
				.attr("id", "clip") //
				.append("rect") //
				.attr("width", width) //
				.attr("height", height);

		Selection focus = svg.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		Selection context = svg.append("g") //
				.attr("transform", "translate(" + margin2.left + "," + margin2.top + ")");

		Brush brush = d3.svg() //
				.brush() //
				.x(x2);

		DataFunction<Void> brushFunction = new DataFunctionWrapper<>(() -> {

			Array<JsDate> limits = brush.empty() ? x2.domain() : brush.extent();

			x.domain(limits);

			focus.select("path") //
					.attr("d", area);

			focus.select(".x.axis") //
					.call(xAxis);

		});

		brush.on(BrushEvent.BRUSH, brushFunction);

		DsvObjectAccessor<FocusAndContextData> objectAccessor = new DsvObjectAccessorWrapper<FocusAndContextData, DsvRow>(
				DsvRow.class, webEngine, (row) -> {
					String date = row.get("date").asString();
					Double price = row.get("price").asDouble();
					return new FocusAndContextData(dateFormat.parse(date), price);
				});

		DsvCallback<FocusAndContextData> callback = new DsvCallbackWrapper<>(webEngine, (array) -> {

			Array<JsDate> jsDateArray = array.map((focusAndContextData) -> {
				JsDate date = focusAndContextData.getDate();
				return date;
			});

			Array<JsDate> limits = Arrays.extent(jsDateArray, webEngine);
			x.domain(limits);

			ForEachCallback<Double> priceCallback = new ForEachCallbackWrapper<>(FocusAndContextData.class, webEngine,
					(focusAndContextData) -> {
						Double price = focusAndContextData.getPrice();
						return price;
					});

			Value maxPrice = Arrays.max(array, priceCallback, webEngine);

			y.domain(0, maxPrice.asInt());

			x2.domain(x.domain());

			y2.domain(y.domain());

			focus.append("path") //
					.datum(array) //
					.attr("clip-path", "url(#clip)") //
					.attr("d", area);

			focus.append("g") //
					.attr("class", "x axis") //
					.attr("transform", "translate(0," + height + ")") //
					.call(xAxis);

			focus.append("g") //
					.attr("class", "y axis") //
					.call(yAxis);

			context.append("path") //
					.datum(array) //
					.attr("d", area2);

			context.append("g") //
					.attr("class", "x axis") //
					.attr("transform", "translate(0," + height2 + ")") //
					.call(xAxis2);

			context.append("g") //
					.attr("class", "x brush") //
					.call(brush) //
					.selectAll("rect") //
					.attr("y", -6) //
					.attr("height", height2 + 7);

		});

		String csvData = csvData();

		Dsv<FocusAndContextData> csv = d3.<FocusAndContextData> csv();
		Array<FocusAndContextData> data = csv.parse(csvData, objectAccessor);
		callback.get(null, data.getJsObject());

		//d3.csv("demo-data/sp500.csv", objectAccessor, callback);

	}

	@Override
	public void stop() {
	}

	private String csvData() {

		return "date,price\n" + //
				"Jan 2000,1394.46\n" + //
				"Feb 2000,1366.42\n" + //
				"Mar 2000,1498.58\n" + //
				"Apr 2000,1452.43\n" + //
				"May 2000,1420.6\n" + //
				"Jun 2000,1454.6\n" + //
				"Jul 2000,1430.83\n" + //
				"Aug 2000,1517.68\n" + //
				"Sep 2000,1436.51\n" + //
				"Oct 2000,1429.4\n" + //
				"Nov 2000,1314.95\n" + //
				"Dec 2000,1320.28\n" + //
				"Jan 2001,1366.01\n" + //
				"Feb 2001,1239.94\n" + //
				"Mar 2001,1160.33\n" + //
				"Apr 2001,1249.46\n" + //
				"May 2001,1255.82\n" + //
				"Jun 2001,1224.38\n" + //
				"Jul 2001,1211.23\n" + //
				"Aug 2001,1133.58\n" + //
				"Sep 2001,1040.94\n" + //
				"Oct 2001,1059.78\n" + //
				"Nov 2001,1139.45\n" + //
				"Dec 2001,1148.08\n" + //
				"Jan 2002,1130.2\n" + //
				"Feb 2002,1106.73\n" + //
				"Mar 2002,1147.39\n" + //
				"Apr 2002,1076.92\n" + //
				"May 2002,1067.14\n" + //
				"Jun 2002,989.82\n" + //
				"Jul 2002,911.62\n" + //
				"Aug 2002,916.07\n" + //
				"Sep 2002,815.28\n" + //
				"Oct 2002,885.76\n" + //
				"Nov 2002,936.31\n" + //
				"Dec 2002,879.82\n" + //
				"Jan 2003,855.7\n" + //
				"Feb 2003,841.15\n" + //
				"Mar 2003,848.18\n" + //
				"Apr 2003,916.92\n" + //
				"May 2003,963.59\n" + //
				"Jun 2003,974.5\n" + //
				"Jul 2003,990.31\n" + //
				"Aug 2003,1008.01\n" + //
				"Sep 2003,995.97\n" + //
				"Oct 2003,1050.71\n" + //
				"Nov 2003,1058.2\n" + //
				"Dec 2003,1111.92\n" + //
				"Jan 2004,1131.13\n" + //
				"Feb 2004,1144.94\n" + //
				"Mar 2004,1126.21\n" + //
				"Apr 2004,1107.3\n" + //
				"May 2004,1120.68\n" + //
				"Jun 2004,1140.84\n" + //
				"Jul 2004,1101.72\n" + //
				"Aug 2004,1104.24\n" + //
				"Sep 2004,1114.58\n" + //
				"Oct 2004,1130.2\n" + //
				"Nov 2004,1173.82\n" + //
				"Dec 2004,1211.92\n" + //
				"Jan 2005,1181.27\n" + //
				"Feb 2005,1203.6\n" + //
				"Mar 2005,1180.59\n" + //
				"Apr 2005,1156.85\n" + //
				"May 2005,1191.5\n" + //
				"Jun 2005,1191.33\n" + //
				"Jul 2005,1234.18\n" + //
				"Aug 2005,1220.33\n" + //
				"Sep 2005,1228.81\n" + //
				"Oct 2005,1207.01\n" + //
				"Nov 2005,1249.48\n" + //
				"Dec 2005,1248.29\n" + //
				"Jan 2006,1280.08\n" + //
				"Feb 2006,1280.66\n" + //
				"Mar 2006,1294.87\n" + //
				"Apr 2006,1310.61\n" + //
				"May 2006,1270.09\n" + //
				"Jun 2006,1270.2\n" + //
				"Jul 2006,1276.66\n" + //
				"Aug 2006,1303.82\n" + //
				"Sep 2006,1335.85\n" + //
				"Oct 2006,1377.94\n" + //
				"Nov 2006,1400.63\n" + //
				"Dec 2006,1418.3\n" + //
				"Jan 2007,1438.24\n" + //
				"Feb 2007,1406.82\n" + //
				"Mar 2007,1420.86\n" + //
				"Apr 2007,1482.37\n" + //
				"May 2007,1530.62\n" + //
				"Jun 2007,1503.35\n" + //
				"Jul 2007,1455.27\n" + //
				"Aug 2007,1473.99\n" + //
				"Sep 2007,1526.75\n" + //
				"Oct 2007,1549.38\n" + //
				"Nov 2007,1481.14\n" + //
				"Dec 2007,1468.36\n" + //
				"Jan 2008,1378.55\n" + //
				"Feb 2008,1330.63\n" + //
				"Mar 2008,1322.7\n" + //
				"Apr 2008,1385.59\n" + //
				"May 2008,1400.38\n" + //
				"Jun 2008,1280\n" + //
				"Jul 2008,1267.38\n" + //
				"Aug 2008,1282.83\n" + //
				"Sep 2008,1166.36\n" + //
				"Oct 2008,968.75\n" + //
				"Nov 2008,896.24\n" + //
				"Dec 2008,903.25\n" + //
				"Jan 2009,825.88\n" + //
				"Feb 2009,735.09\n" + //
				"Mar 2009,797.87\n" + //
				"Apr 2009,872.81\n" + //
				"May 2009,919.14\n" + //
				"Jun 2009,919.32\n" + //
				"Jul 2009,987.48\n" + //
				"Aug 2009,1020.62\n" + //
				"Sep 2009,1057.08\n" + //
				"Oct 2009,1036.19\n" + //
				"Nov 2009,1095.63\n" + //
				"Dec 2009,1115.1\n" + //
				"Jan 2010,1073.87\n" + //
				"Feb 2010,1104.49\n" + //
				"Mar 2010,1140.45\n";

	}

	//#end region

}
