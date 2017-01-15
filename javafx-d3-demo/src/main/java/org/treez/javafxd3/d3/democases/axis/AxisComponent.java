package org.treez.javafxd3.d3.democases.axis;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.dsv.Dsv;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.svg.Area;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.InterpolationMode;
import org.treez.javafxd3.d3.svg.Line;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeFormat;
import org.treez.javafxd3.d3.time.TimeScale;

import javafx.scene.layout.VBox;

public class AxisComponent extends AbstractDemoCase {

	//#region CONSTRUCTORS

	public AxisComponent(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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

		final TimeFormat format = d3.time() //
				.format("%b %Y");

		// Scales and axes. Note the inverted domain for the y-scale: bigger is
		// up!

		final TimeScale x = d3.time() //
				.scale() //
				.range(0, w);

		final LinearScale y = d3.scale() //
				.linear() //
				.range(h, 0);

		final Axis xAxis = d3.svg() //
				.axis() //
				.scale(x) //
				.tickSize(-h);		

		final Axis yAxis = d3.svg() //
				.axis() //
				.scale(y) //
				.orient(Orientation.RIGHT) //
				.ticks(4);

		// An area generator, for the light fill.

		DataFunction<Double> xFunction = new DataFunctionWrapper<>(DsvData.class, webEngine, (dsvData) -> {
			JsDate date = dsvData.getDate();
			Value scaledValue = x.apply(date);
			Double xResult = scaledValue.asDouble();
			return xResult;
		});

		DataFunction<Double> yFunction = new DataFunctionWrapper<>(DsvData.class, webEngine, (dsvData) -> {
			Double price = dsvData.getPrice();
			Value scaledValue = y.apply(price);
			Double yResult = scaledValue.asDouble();
			return yResult;
		});

		final Area area = d3.svg() //
				.area() //
				.interpolate(InterpolationMode.MONOTONE) //
				.x(xFunction) //
				.y0(h) //
				.y1(yFunction);

		// A line generator, for the dark stroke.
		final Line line = d3.svg() //
				.line() //
				.interpolate(InterpolationMode.MONOTONE) //
				.x(xFunction) //
				.y(yFunction);

		Selection svg = d3.select("svg");

		DsvObjectAccessor<DsvData> accessor = new DataDsvObjectAccessor(webEngine, format);
		DsvCallback<DsvData> callback = new DataDsvCallback(webEngine, svg, x, y, xAxis, yAxis, line, area, m, w, h);

		String csvData = getCsvData();
		Dsv<DsvData> csv = d3.<DsvData> csv();
		Array<DsvData> data = csv.parse(csvData, accessor);
		callback.get(null, data.getJsObject());

		//String relativeUrl = "readme.csv";		
		//URL url = getClass().getClassLoader().getResource(relativeUrl);
		//String fileUrl = url.toString();
		//d3.csv(fileUrl,accessor ,callback);

	}

	@Override
	public void stop() {
	}

	private String getCsvData() {
		return "symbol,date,price\n" + //
				"S&P 500,Jan 2000,1394.46\n" + //
				"S&P 500,Feb 2000,1366.42\n" + //
				"S&P 500,Mar 2000,1498.58\n" + //
				"S&P 500,Apr 2000,1452.43\n" + //
				"S&P 500,May 2000,1420.6\n" + //
				"S&P 500,Jun 2000,1454.6\n" + //
				"S&P 500,Jul 2000,1430.83\n" + //
				"S&P 500,Aug 2000,1517.68\n" + //
				"S&P 500,Sep 2000,1436.51\n" + //
				"S&P 500,Oct 2000,1429.4\n" + //
				"S&P 500,Nov 2000,1314.95\n" + //
				"S&P 500,Dec 2000,1320.28\n" + //
				"S&P 500,Jan 2001,1366.01\n" + //
				"S&P 500,Feb 2001,1239.94\n" + //
				"S&P 500,Mar 2001,1160.33\n" + //
				"S&P 500,Apr 2001,1249.46\n" + //
				"S&P 500,May 2001,1255.82\n" + //
				"S&P 500,Jun 2001,1224.38\n" + //
				"S&P 500,Jul 2001,1211.23\n" + //
				"S&P 500,Aug 2001,1133.58\n" + //
				"S&P 500,Sep 2001,1040.94\n" + //
				"S&P 500,Oct 2001,1059.78\n" + //
				"S&P 500,Nov 2001,1139.45\n" + //
				"S&P 500,Dec 2001,1148.08\n" + //
				"S&P 500,Jan 2002,1130.2\n" + //
				"S&P 500,Feb 2002,1106.73\n" + //
				"S&P 500,Mar 2002,1147.39\n" + //
				"S&P 500,Apr 2002,1076.92\n" + //
				"S&P 500,May 2002,1067.14\n" + //
				"S&P 500,Jun 2002,989.82\n" + //
				"S&P 500,Jul 2002,911.62\n" + //
				"S&P 500,Aug 2002,916.07\n" + //
				"S&P 500,Sep 2002,815.28\n" + //
				"S&P 500,Oct 2002,885.76\n" + //
				"S&P 500,Nov 2002,936.31\n" + //
				"S&P 500,Dec 2002,879.82\n" + //
				"S&P 500,Jan 2003,855.7\n" + //
				"S&P 500,Feb 2003,841.15\n" + //
				"S&P 500,Mar 2003,848.18\n" + //
				"S&P 500,Apr 2003,916.92\n" + //
				"S&P 500,May 2003,963.59\n" + //
				"S&P 500,Jun 2003,974.5\n" + //
				"S&P 500,Jul 2003,990.31\n" + //
				"S&P 500,Aug 2003,1008.01\n" + //
				"S&P 500,Sep 2003,995.97\n" + //
				"S&P 500,Oct 2003,1050.71\n" + //
				"S&P 500,Nov 2003,1058.2\n" + //
				"S&P 500,Dec 2003,1111.92\n" + //
				"S&P 500,Jan 2004,1131.13\n" + //
				"S&P 500,Feb 2004,1144.94\n" + //
				"S&P 500,Mar 2004,1126.21\n" + //
				"S&P 500,Apr 2004,1107.3\n" + //
				"S&P 500,May 2004,1120.68\n" + //
				"S&P 500,Jun 2004,1140.84\n" + //
				"S&P 500,Jul 2004,1101.72\n" + //
				"S&P 500,Aug 2004,1104.24\n" + //
				"S&P 500,Sep 2004,1114.58\n" + //
				"S&P 500,Oct 2004,1130.2\n" + //
				"S&P 500,Nov 2004,1173.82\n" + //
				"S&P 500,Dec 2004,1211.92\n" + //
				"S&P 500,Jan 2005,1181.27\n" + //
				"S&P 500,Feb 2005,1203.6\n" + //
				"S&P 500,Mar 2005,1180.59\n" + //
				"S&P 500,Apr 2005,1156.85\n" + //
				"S&P 500,May 2005,1191.5\n" + //
				"S&P 500,Jun 2005,1191.33\n" + //
				"S&P 500,Jul 2005,1234.18\n" + //
				"S&P 500,Aug 2005,1220.33\n" + //
				"S&P 500,Sep 2005,1228.81\n" + //
				"S&P 500,Oct 2005,1207.01\n" + //
				"S&P 500,Nov 2005,1249.48\n" + //
				"S&P 500,Dec 2005,1248.29\n" + //
				"S&P 500,Jan 2006,1280.08\n" + //
				"S&P 500,Feb 2006,1280.66\n" + //
				"S&P 500,Mar 2006,1294.87\n" + //
				"S&P 500,Apr 2006,1310.61\n" + //
				"S&P 500,May 2006,1270.09\n" + //
				"S&P 500,Jun 2006,1270.2\n" + //
				"S&P 500,Jul 2006,1276.66\n" + //
				"S&P 500,Aug 2006,1303.82\n" + //
				"S&P 500,Sep 2006,1335.85\n" + //
				"S&P 500,Oct 2006,1377.94\n" + //
				"S&P 500,Nov 2006,1400.63\n" + //
				"S&P 500,Dec 2006,1418.3\n" + //
				"S&P 500,Jan 2007,1438.24\n" + //
				"S&P 500,Feb 2007,1406.82\n" + //
				"S&P 500,Mar 2007,1420.86\n" + //
				"S&P 500,Apr 2007,1482.37\n" + //
				"S&P 500,May 2007,1530.62\n" + //
				"S&P 500,Jun 2007,1503.35\n" + //
				"S&P 500,Jul 2007,1455.27\n" + //
				"S&P 500,Aug 2007,1473.99\n" + //
				"S&P 500,Sep 2007,1526.75\n" + //
				"S&P 500,Oct 2007,1549.38\n" + //
				"S&P 500,Nov 2007,1481.14\n" + //
				"S&P 500,Dec 2007,1468.36\n" + //
				"S&P 500,Jan 2008,1378.55\n" + //
				"S&P 500,Feb 2008,1330.63\n" + //
				"S&P 500,Mar 2008,1322.7\n" + //
				"S&P 500,Apr 2008,1385.59\n" + //
				"S&P 500,May 2008,1400.38\n" + //
				"S&P 500,Jun 2008,1280\n" + //
				"S&P 500,Jul 2008,1267.38\n" + //
				"S&P 500,Aug 2008,1282.83\n" + //
				"S&P 500,Sep 2008,1166.36\n" + //
				"S&P 500,Oct 2008,968.75\n" + //
				"S&P 500,Nov 2008,896.24\n" + //
				"S&P 500,Dec 2008,903.25\n" + //
				"S&P 500,Jan 2009,825.88\n" + //
				"S&P 500,Feb 2009,735.09\n" + //
				"S&P 500,Mar 2009,797.87\n" + //
				"S&P 500,Apr 2009,872.81\n" + //
				"S&P 500,May 2009,919.14\n" + //
				"S&P 500,Jun 2009,919.32\n" + //
				"S&P 500,Jul 2009,987.48\n" + //
				"S&P 500,Aug 2009,1020.62\n" + //
				"S&P 500,Sep 2009,1057.08\n" + //
				"S&P 500,Oct 2009,1036.19\n" + //
				"S&P 500,Nov 2009,1095.63\n" + //
				"S&P 500,Dec 2009,1115.1\n" + //
				"S&P 500,Jan 2010,1073.87\n" + //
				"S&P 500,Feb 2010,1104.49\n" + //
				"S&P 500,Mar 2010,1140.45\n" + //
				"MSFT,Jan 2000,39.81\n" + //
				"MSFT,Feb 2000,36.35\n" + //
				"MSFT,Mar 2000,43.22\n" + //
				"MSFT,Apr 2000,28.37\n" + //
				"MSFT,May 2000,25.45\n" + //
				"MSFT,Jun 2000,32.54\n" + //
				"MSFT,Jul 2000,28.4\n" + //
				"MSFT,Aug 2000,28.4\n" + //
				"MSFT,Sep 2000,24.53\n" + //
				"MSFT,Oct 2000,28.02\n" + //
				"MSFT,Nov 2000,23.34\n" + //
				"MSFT,Dec 2000,17.65\n" + //
				"MSFT,Jan 2001,24.84\n" + //
				"MSFT,Feb 2001,24\n" + //
				"MSFT,Mar 2001,22.25\n" + //
				"MSFT,Apr 2001,27.56\n" + //
				"MSFT,May 2001,28.14\n" + //
				"MSFT,Jun 2001,29.7\n" + //
				"MSFT,Jul 2001,26.93\n" + //
				"MSFT,Aug 2001,23.21\n" + //
				"MSFT,Sep 2001,20.82\n" + //
				"MSFT,Oct 2001,23.65\n" + //
				"MSFT,Nov 2001,26.12\n" + //
				"MSFT,Dec 2001,26.95\n" + //
				"MSFT,Jan 2002,25.92\n" + //
				"MSFT,Feb 2002,23.73\n" + //
				"MSFT,Mar 2002,24.53\n" + //
				"MSFT,Apr 2002,21.26\n" + //
				"MSFT,May 2002,20.71\n" + //
				"MSFT,Jun 2002,22.25\n" + //
				"MSFT,Jul 2002,19.52\n" + //
				"MSFT,Aug 2002,19.97\n" + //
				"MSFT,Sep 2002,17.79\n" + //
				"MSFT,Oct 2002,21.75\n" + //
				"MSFT,Nov 2002,23.46\n" + //
				"MSFT,Dec 2002,21.03\n" + //
				"MSFT,Jan 2003,19.31\n" + //
				"MSFT,Feb 2003,19.34\n" + //
				"MSFT,Mar 2003,19.76\n" + //
				"MSFT,Apr 2003,20.87\n" + //
				"MSFT,May 2003,20.09\n" + //
				"MSFT,Jun 2003,20.93\n" + //
				"MSFT,Jul 2003,21.56\n" + //
				"MSFT,Aug 2003,21.65\n" + //
				"MSFT,Sep 2003,22.69\n" + //
				"MSFT,Oct 2003,21.45\n" + //
				"MSFT,Nov 2003,21.1\n" + //
				"MSFT,Dec 2003,22.46\n" + //
				"MSFT,Jan 2004,22.69\n" + //
				"MSFT,Feb 2004,21.77\n" + //
				"MSFT,Mar 2004,20.46\n" + //
				"MSFT,Apr 2004,21.45\n" + //
				"MSFT,May 2004,21.53\n" + //
				"MSFT,Jun 2004,23.44\n" + //
				"MSFT,Jul 2004,23.38\n" + //
				"MSFT,Aug 2004,22.47\n" + //
				"MSFT,Sep 2004,22.76\n" + //
				"MSFT,Oct 2004,23.02\n" + //
				"MSFT,Nov 2004,24.6\n" + //
				"MSFT,Dec 2004,24.52\n" + //
				"MSFT,Jan 2005,24.11\n" + //
				"MSFT,Feb 2005,23.15\n" + //
				"MSFT,Mar 2005,22.24\n" + //
				"MSFT,Apr 2005,23.28\n" + //
				"MSFT,May 2005,23.82\n" + //
				"MSFT,Jun 2005,22.93\n" + //
				"MSFT,Jul 2005,23.64\n" + //
				"MSFT,Aug 2005,25.35\n" + //
				"MSFT,Sep 2005,23.83\n" + //
				"MSFT,Oct 2005,23.8\n" + //
				"MSFT,Nov 2005,25.71\n" + //
				"MSFT,Dec 2005,24.29\n" + //
				"MSFT,Jan 2006,26.14\n" + //
				"MSFT,Feb 2006,25.04\n" + //
				"MSFT,Mar 2006,25.36\n" + //
				"MSFT,Apr 2006,22.5\n" + //
				"MSFT,May 2006,21.19\n" + //
				"MSFT,Jun 2006,21.8\n" + //
				"MSFT,Jul 2006,22.51\n" + //
				"MSFT,Aug 2006,24.13\n" + //
				"MSFT,Sep 2006,25.68\n" + //
				"MSFT,Oct 2006,26.96\n" + //
				"MSFT,Nov 2006,27.66\n" + //
				"MSFT,Dec 2006,28.13\n" + //
				"MSFT,Jan 2007,29.07\n" + //
				"MSFT,Feb 2007,26.63\n" + //
				"MSFT,Mar 2007,26.35\n" + //
				"MSFT,Apr 2007,28.3\n" + //
				"MSFT,May 2007,29.11\n" + //
				"MSFT,Jun 2007,27.95\n" + //
				"MSFT,Jul 2007,27.5\n" + //
				"MSFT,Aug 2007,27.34\n" + //
				"MSFT,Sep 2007,28.04\n" + //
				"MSFT,Oct 2007,35.03\n" + //
				"MSFT,Nov 2007,32.09\n" + //
				"MSFT,Dec 2007,34\n" + //
				"MSFT,Jan 2008,31.13\n" + //
				"MSFT,Feb 2008,26.07\n" + //
				"MSFT,Mar 2008,27.21\n" + //
				"MSFT,Apr 2008,27.34\n" + //
				"MSFT,May 2008,27.25\n" + //
				"MSFT,Jun 2008,26.47\n" + //
				"MSFT,Jul 2008,24.75\n" + //
				"MSFT,Aug 2008,26.36\n" + //
				"MSFT,Sep 2008,25.78\n" + //
				"MSFT,Oct 2008,21.57\n" + //
				"MSFT,Nov 2008,19.66\n" + //
				"MSFT,Dec 2008,18.91\n" + //
				"MSFT,Jan 2009,16.63\n" + //
				"MSFT,Feb 2009,15.81\n" + //
				"MSFT,Mar 2009,17.99\n" + //
				"MSFT,Apr 2009,19.84\n" + //
				"MSFT,May 2009,20.59\n" + //
				"MSFT,Jun 2009,23.42\n" + //
				"MSFT,Jul 2009,23.18\n" + //
				"MSFT,Aug 2009,24.43\n" + //
				"MSFT,Sep 2009,25.49\n" + //
				"MSFT,Oct 2009,27.48\n" + //
				"MSFT,Nov 2009,29.27\n" + //
				"MSFT,Dec 2009,30.34\n" + //
				"MSFT,Jan 2010,28.05\n" + //
				"MSFT,Feb 2010,28.67\n" + //
				"MSFT,Mar 2010,28.8\n" + //
				"AMZN,Jan 2000,64.56\n" + //
				"AMZN,Feb 2000,68.87\n" + //
				"AMZN,Mar 2000,67\n" + //
				"AMZN,Apr 2000,55.19\n" + //
				"AMZN,May 2000,48.31\n" + //
				"AMZN,Jun 2000,36.31\n" + //
				"AMZN,Jul 2000,30.12\n" + //
				"AMZN,Aug 2000,41.5\n" + //
				"AMZN,Sep 2000,38.44\n" + //
				"AMZN,Oct 2000,36.62\n" + //
				"AMZN,Nov 2000,24.69\n" + //
				"AMZN,Dec 2000,15.56\n" + //
				"AMZN,Jan 2001,17.31\n" + //
				"AMZN,Feb 2001,10.19\n" + //
				"AMZN,Mar 2001,10.23\n" + //
				"AMZN,Apr 2001,15.78\n" + //
				"AMZN,May 2001,16.69\n" + //
				"AMZN,Jun 2001,14.15\n" + //
				"AMZN,Jul 2001,12.49\n" + //
				"AMZN,Aug 2001,8.94\n" + //
				"AMZN,Sep 2001,5.97\n" + //
				"AMZN,Oct 2001,6.98\n" + //
				"AMZN,Nov 2001,11.32\n" + //
				"AMZN,Dec 2001,10.82\n" + //
				"AMZN,Jan 2002,14.19\n" + //
				"AMZN,Feb 2002,14.1\n" + //
				"AMZN,Mar 2002,14.3\n" + //
				"AMZN,Apr 2002,16.69\n" + //
				"AMZN,May 2002,18.23\n" + //
				"AMZN,Jun 2002,16.25\n" + //
				"AMZN,Jul 2002,14.45\n" + //
				"AMZN,Aug 2002,14.94\n" + //
				"AMZN,Sep 2002,15.93\n" + //
				"AMZN,Oct 2002,19.36\n" + //
				"AMZN,Nov 2002,23.35\n" + //
				"AMZN,Dec 2002,18.89\n" + //
				"AMZN,Jan 2003,21.85\n" + //
				"AMZN,Feb 2003,22.01\n" + //
				"AMZN,Mar 2003,26.03\n" + //
				"AMZN,Apr 2003,28.69\n" + //
				"AMZN,May 2003,35.89\n" + //
				"AMZN,Jun 2003,36.32\n" + //
				"AMZN,Jul 2003,41.64\n" + //
				"AMZN,Aug 2003,46.32\n" + //
				"AMZN,Sep 2003,48.43\n" + //
				"AMZN,Oct 2003,54.43\n" + //
				"AMZN,Nov 2003,53.97\n" + //
				"AMZN,Dec 2003,52.62\n" + //
				"AMZN,Jan 2004,50.4\n" + //
				"AMZN,Feb 2004,43.01\n" + //
				"AMZN,Mar 2004,43.28\n" + //
				"AMZN,Apr 2004,43.6\n" + //
				"AMZN,May 2004,48.5\n" + //
				"AMZN,Jun 2004,54.4\n" + //
				"AMZN,Jul 2004,38.92\n" + //
				"AMZN,Aug 2004,38.14\n" + //
				"AMZN,Sep 2004,40.86\n" + //
				"AMZN,Oct 2004,34.13\n" + //
				"AMZN,Nov 2004,39.68\n" + //
				"AMZN,Dec 2004,44.29\n" + //
				"AMZN,Jan 2005,43.22\n" + //
				"AMZN,Feb 2005,35.18\n" + //
				"AMZN,Mar 2005,34.27\n" + //
				"AMZN,Apr 2005,32.36\n" + //
				"AMZN,May 2005,35.51\n" + //
				"AMZN,Jun 2005,33.09\n" + //
				"AMZN,Jul 2005,45.15\n" + //
				"AMZN,Aug 2005,42.7\n" + //
				"AMZN,Sep 2005,45.3\n" + //
				"AMZN,Oct 2005,39.86\n" + //
				"AMZN,Nov 2005,48.46\n" + //
				"AMZN,Dec 2005,47.15\n" + //
				"AMZN,Jan 2006,44.82\n" + //
				"AMZN,Feb 2006,37.44\n" + //
				"AMZN,Mar 2006,36.53\n" + //
				"AMZN,Apr 2006,35.21\n" + //
				"AMZN,May 2006,34.61\n" + //
				"AMZN,Jun 2006,38.68\n" + //
				"AMZN,Jul 2006,26.89\n" + //
				"AMZN,Aug 2006,30.83\n" + //
				"AMZN,Sep 2006,32.12\n" + //
				"AMZN,Oct 2006,38.09\n" + //
				"AMZN,Nov 2006,40.34\n" + //
				"AMZN,Dec 2006,39.46\n" + //
				"AMZN,Jan 2007,37.67\n" + //
				"AMZN,Feb 2007,39.14\n" + //
				"AMZN,Mar 2007,39.79\n" + //
				"AMZN,Apr 2007,61.33\n" + //
				"AMZN,May 2007,69.14\n" + //
				"AMZN,Jun 2007,68.41\n" + //
				"AMZN,Jul 2007,78.54\n" + //
				"AMZN,Aug 2007,79.91\n" + //
				"AMZN,Sep 2007,93.15\n" + //
				"AMZN,Oct 2007,89.15\n" + //
				"AMZN,Nov 2007,90.56\n" + //
				"AMZN,Dec 2007,92.64\n" + //
				"AMZN,Jan 2008,77.7\n" + //
				"AMZN,Feb 2008,64.47\n" + //
				"AMZN,Mar 2008,71.3\n" + //
				"AMZN,Apr 2008,78.63\n" + //
				"AMZN,May 2008,81.62\n" + //
				"AMZN,Jun 2008,73.33\n" + //
				"AMZN,Jul 2008,76.34\n" + //
				"AMZN,Aug 2008,80.81\n" + //
				"AMZN,Sep 2008,72.76\n" + //
				"AMZN,Oct 2008,57.24\n" + //
				"AMZN,Nov 2008,42.7\n" + //
				"AMZN,Dec 2008,51.28\n" + //
				"AMZN,Jan 2009,58.82\n" + //
				"AMZN,Feb 2009,64.79\n" + //
				"AMZN,Mar 2009,73.44\n" + //
				"AMZN,Apr 2009,80.52\n" + //
				"AMZN,May 2009,77.99\n" + //
				"AMZN,Jun 2009,83.66\n" + //
				"AMZN,Jul 2009,85.76\n" + //
				"AMZN,Aug 2009,81.19\n" + //
				"AMZN,Sep 2009,93.36\n" + //
				"AMZN,Oct 2009,118.81\n" + //
				"AMZN,Nov 2009,135.91\n" + //
				"AMZN,Dec 2009,134.52\n" + //
				"AMZN,Jan 2010,125.41\n" + //
				"AMZN,Feb 2010,118.4\n" + //
				"AMZN,Mar 2010,128.82\n" + //
				"IBM,Jan 2000,100.52\n" + //
				"IBM,Feb 2000,92.11\n" + //
				"IBM,Mar 2000,106.11\n" + //
				"IBM,Apr 2000,99.95\n" + //
				"IBM,May 2000,96.31\n" + //
				"IBM,Jun 2000,98.33\n" + //
				"IBM,Jul 2000,100.74\n" + //
				"IBM,Aug 2000,118.62\n" + //
				"IBM,Sep 2000,101.19\n" + //
				"IBM,Oct 2000,88.5\n" + //
				"IBM,Nov 2000,84.12\n" + //
				"IBM,Dec 2000,76.47\n" + //
				"IBM,Jan 2001,100.76\n" + //
				"IBM,Feb 2001,89.98\n" + //
				"IBM,Mar 2001,86.63\n" + //
				"IBM,Apr 2001,103.7\n" + //
				"IBM,May 2001,100.82\n" + //
				"IBM,Jun 2001,102.35\n" + //
				"IBM,Jul 2001,94.87\n" + //
				"IBM,Aug 2001,90.25\n" + //
				"IBM,Sep 2001,82.82\n" + //
				"IBM,Oct 2001,97.58\n" + //
				"IBM,Nov 2001,104.5\n" + //
				"IBM,Dec 2001,109.36\n" + //
				"IBM,Jan 2002,97.54\n" + //
				"IBM,Feb 2002,88.82\n" + //
				"IBM,Mar 2002,94.15\n" + //
				"IBM,Apr 2002,75.82\n" + //
				"IBM,May 2002,72.97\n" + //
				"IBM,Jun 2002,65.31\n" + //
				"IBM,Jul 2002,63.86\n" + //
				"IBM,Aug 2002,68.52\n" + //
				"IBM,Sep 2002,53.01\n" + //
				"IBM,Oct 2002,71.76\n" + //
				"IBM,Nov 2002,79.16\n" + //
				"IBM,Dec 2002,70.58\n" + //
				"IBM,Jan 2003,71.22\n" + //
				"IBM,Feb 2003,71.13\n" + //
				"IBM,Mar 2003,71.57\n" + //
				"IBM,Apr 2003,77.47\n" + //
				"IBM,May 2003,80.48\n" + //
				"IBM,Jun 2003,75.42\n" + //
				"IBM,Jul 2003,74.28\n" + //
				"IBM,Aug 2003,75.12\n" + //
				"IBM,Sep 2003,80.91\n" + //
				"IBM,Oct 2003,81.96\n" + //
				"IBM,Nov 2003,83.08\n" + //
				"IBM,Dec 2003,85.05\n" + //
				"IBM,Jan 2004,91.06\n" + //
				"IBM,Feb 2004,88.7\n" + //
				"IBM,Mar 2004,84.41\n" + //
				"IBM,Apr 2004,81.04\n" + //
				"IBM,May 2004,81.59\n" + //
				"IBM,Jun 2004,81.19\n" + //
				"IBM,Jul 2004,80.19\n" + //
				"IBM,Aug 2004,78.17\n" + //
				"IBM,Sep 2004,79.13\n" + //
				"IBM,Oct 2004,82.84\n" + //
				"IBM,Nov 2004,87.15\n" + //
				"IBM,Dec 2004,91.16\n" + //
				"IBM,Jan 2005,86.39\n" + //
				"IBM,Feb 2005,85.78\n" + //
				"IBM,Mar 2005,84.66\n" + //
				"IBM,Apr 2005,70.77\n" + //
				"IBM,May 2005,70.18\n" + //
				"IBM,Jun 2005,68.93\n" + //
				"IBM,Jul 2005,77.53\n" + //
				"IBM,Aug 2005,75.07\n" + //
				"IBM,Sep 2005,74.7\n" + //
				"IBM,Oct 2005,76.25\n" + //
				"IBM,Nov 2005,82.98\n" + //
				"IBM,Dec 2005,76.73\n" + //
				"IBM,Jan 2006,75.89\n" + //
				"IBM,Feb 2006,75.09\n" + //
				"IBM,Mar 2006,77.17\n" + //
				"IBM,Apr 2006,77.05\n" + //
				"IBM,May 2006,75.04\n" + //
				"IBM,Jun 2006,72.15\n" + //
				"IBM,Jul 2006,72.7\n" + //
				"IBM,Aug 2006,76.35\n" + //
				"IBM,Sep 2006,77.26\n" + //
				"IBM,Oct 2006,87.06\n" + //
				"IBM,Nov 2006,86.95\n" + //
				"IBM,Dec 2006,91.9\n" + //
				"IBM,Jan 2007,93.79\n" + //
				"IBM,Feb 2007,88.18\n" + //
				"IBM,Mar 2007,89.44\n" + //
				"IBM,Apr 2007,96.98\n" + //
				"IBM,May 2007,101.54\n" + //
				"IBM,Jun 2007,100.25\n" + //
				"IBM,Jul 2007,105.4\n" + //
				"IBM,Aug 2007,111.54\n" + //
				"IBM,Sep 2007,112.6\n" + //
				"IBM,Oct 2007,111\n" + //
				"IBM,Nov 2007,100.9\n" + //
				"IBM,Dec 2007,103.7\n" + //
				"IBM,Jan 2008,102.75\n" + //
				"IBM,Feb 2008,109.64\n" + //
				"IBM,Mar 2008,110.87\n" + //
				"IBM,Apr 2008,116.23\n" + //
				"IBM,May 2008,125.14\n" + //
				"IBM,Jun 2008,114.6\n" + //
				"IBM,Jul 2008,123.74\n" + //
				"IBM,Aug 2008,118.16\n" + //
				"IBM,Sep 2008,113.53\n" + //
				"IBM,Oct 2008,90.24\n" + //
				"IBM,Nov 2008,79.65\n" + //
				"IBM,Dec 2008,82.15\n" + //
				"IBM,Jan 2009,89.46\n" + //
				"IBM,Feb 2009,90.32\n" + //
				"IBM,Mar 2009,95.09\n" + //
				"IBM,Apr 2009,101.29\n" + //
				"IBM,May 2009,104.85\n" + //
				"IBM,Jun 2009,103.01\n" + //
				"IBM,Jul 2009,116.34\n" + //
				"IBM,Aug 2009,117\n" + //
				"IBM,Sep 2009,118.55\n" + //
				"IBM,Oct 2009,119.54\n" + //
				"IBM,Nov 2009,125.79\n" + //
				"IBM,Dec 2009,130.32\n" + //
				"IBM,Jan 2010,121.85\n" + //
				"IBM,Feb 2010,127.16\n" + //
				"IBM,Mar 2010,125.55\n" + //
				"GOOG,Aug 2004,102.37\n" + //
				"GOOG,Sep 2004,129.6\n" + //
				"GOOG,Oct 2004,190.64\n" + //
				"GOOG,Nov 2004,181.98\n" + //
				"GOOG,Dec 2004,192.79\n" + //
				"GOOG,Jan 2005,195.62\n" + //
				"GOOG,Feb 2005,187.99\n" + //
				"GOOG,Mar 2005,180.51\n" + //
				"GOOG,Apr 2005,220\n" + //
				"GOOG,May 2005,277.27\n" + //
				"GOOG,Jun 2005,294.15\n" + //
				"GOOG,Jul 2005,287.76\n" + //
				"GOOG,Aug 2005,286\n" + //
				"GOOG,Sep 2005,316.46\n" + //
				"GOOG,Oct 2005,372.14\n" + //
				"GOOG,Nov 2005,404.91\n" + //
				"GOOG,Dec 2005,414.86\n" + //
				"GOOG,Jan 2006,432.66\n" + //
				"GOOG,Feb 2006,362.62\n" + //
				"GOOG,Mar 2006,390\n" + //
				"GOOG,Apr 2006,417.94\n" + //
				"GOOG,May 2006,371.82\n" + //
				"GOOG,Jun 2006,419.33\n" + //
				"GOOG,Jul 2006,386.6\n" + //
				"GOOG,Aug 2006,378.53\n" + //
				"GOOG,Sep 2006,401.9\n" + //
				"GOOG,Oct 2006,476.39\n" + //
				"GOOG,Nov 2006,484.81\n" + //
				"GOOG,Dec 2006,460.48\n" + //
				"GOOG,Jan 2007,501.5\n" + //
				"GOOG,Feb 2007,449.45\n" + //
				"GOOG,Mar 2007,458.16\n" + //
				"GOOG,Apr 2007,471.38\n" + //
				"GOOG,May 2007,497.91\n" + //
				"GOOG,Jun 2007,522.7\n" + //
				"GOOG,Jul 2007,510\n" + //
				"GOOG,Aug 2007,515.25\n" + //
				"GOOG,Sep 2007,567.27\n" + //
				"GOOG,Oct 2007,707\n" + //
				"GOOG,Nov 2007,693\n" + //
				"GOOG,Dec 2007,691.48\n" + //
				"GOOG,Jan 2008,564.3\n" + //
				"GOOG,Feb 2008,471.18\n" + //
				"GOOG,Mar 2008,440.47\n" + //
				"GOOG,Apr 2008,574.29\n" + //
				"GOOG,May 2008,585.8\n" + //
				"GOOG,Jun 2008,526.42\n" + //
				"GOOG,Jul 2008,473.75\n" + //
				"GOOG,Aug 2008,463.29\n" + //
				"GOOG,Sep 2008,400.52\n" + //
				"GOOG,Oct 2008,359.36\n" + //
				"GOOG,Nov 2008,292.96\n" + //
				"GOOG,Dec 2008,307.65\n" + //
				"GOOG,Jan 2009,338.53\n" + //
				"GOOG,Feb 2009,337.99\n" + //
				"GOOG,Mar 2009,348.06\n" + //
				"GOOG,Apr 2009,395.97\n" + //
				"GOOG,May 2009,417.23\n" + //
				"GOOG,Jun 2009,421.59\n" + //
				"GOOG,Jul 2009,443.05\n" + //
				"GOOG,Aug 2009,461.67\n" + //
				"GOOG,Sep 2009,495.85\n" + //
				"GOOG,Oct 2009,536.12\n" + //
				"GOOG,Nov 2009,583\n" + //
				"GOOG,Dec 2009,619.98\n" + //
				"GOOG,Jan 2010,529.94\n" + //
				"GOOG,Feb 2010,526.8\n" + //
				"GOOG,Mar 2010,560.19\n" + //
				"10 Year T-Note,Jan 2000,6.67\n" + //
				"10 Year T-Note,Feb 2000,6.41\n" + //
				"10 Year T-Note,Mar 2000,6.02\n" + //
				"10 Year T-Note,Apr 2000,6.21\n" + //
				"10 Year T-Note,May 2000,6.28\n" + //
				"10 Year T-Note,Jun 2000,6.02\n" + //
				"10 Year T-Note,Jul 2000,6.03\n" + //
				"10 Year T-Note,Aug 2000,5.73\n" + //
				"10 Year T-Note,Sep 2000,5.78\n" + //
				"10 Year T-Note,Oct 2000,5.76\n" + //
				"10 Year T-Note,Nov 2000,5.44\n" + //
				"10 Year T-Note,Dec 2000,5.11\n" + //
				"10 Year T-Note,Jan 2001,5.18\n" + //
				"10 Year T-Note,Feb 2001,4.91\n" + //
				"10 Year T-Note,Mar 2001,4.91\n" + //
				"10 Year T-Note,Apr 2001,5.34\n" + //
				"10 Year T-Note,May 2001,5.41\n" + //
				"10 Year T-Note,Jun 2001,5.39\n" + //
				"10 Year T-Note,Jul 2001,5.04\n" + //
				"10 Year T-Note,Aug 2001,4.82\n" + //
				"10 Year T-Note,Sep 2001,4.57\n" + //
				"10 Year T-Note,Oct 2001,4.26\n" + //
				"10 Year T-Note,Nov 2001,4.74\n" + //
				"10 Year T-Note,Dec 2001,5.03\n" + //
				"10 Year T-Note,Jan 2002,5.03\n" + //
				"10 Year T-Note,Feb 2002,4.86\n" + //
				"10 Year T-Note,Mar 2002,5.41\n" + //
				"10 Year T-Note,Apr 2002,5.09\n" + //
				"10 Year T-Note,May 2002,5.04\n" + //
				"10 Year T-Note,Jun 2002,4.82\n" + //
				"10 Year T-Note,Jul 2002,4.47\n" + //
				"10 Year T-Note,Aug 2002,4.14\n" + //
				"10 Year T-Note,Sep 2002,3.61\n" + //
				"10 Year T-Note,Oct 2002,3.91\n" + //
				"10 Year T-Note,Nov 2002,4.21\n" + //
				"10 Year T-Note,Dec 2002,3.82\n" + //
				"10 Year T-Note,Jan 2003,3.97\n" + //
				"10 Year T-Note,Feb 2003,3.7\n" + //
				"10 Year T-Note,Mar 2003,3.82\n" + //
				"10 Year T-Note,Apr 2003,3.86\n" + //
				"10 Year T-Note,May 2003,3.35\n" + //
				"10 Year T-Note,Jun 2003,3.53\n" + //
				"10 Year T-Note,Jul 2003,4.47\n" + //
				"10 Year T-Note,Aug 2003,4.45\n" + //
				"10 Year T-Note,Sep 2003,3.94\n" + //
				"10 Year T-Note,Oct 2003,4.3\n" + //
				"10 Year T-Note,Nov 2003,4.32\n" + //
				"10 Year T-Note,Dec 2003,4.26\n" + //
				"10 Year T-Note,Jan 2004,4.14\n" + //
				"10 Year T-Note,Feb 2004,3.98\n" + //
				"10 Year T-Note,Mar 2004,3.84\n" + //
				"10 Year T-Note,Apr 2004,4.5\n" + //
				"10 Year T-Note,May 2004,4.66\n" + //
				"10 Year T-Note,Jun 2004,4.62\n" + //
				"10 Year T-Note,Jul 2004,4.47\n" + //
				"10 Year T-Note,Aug 2004,4.13\n" + //
				"10 Year T-Note,Sep 2004,4.12\n" + //
				"10 Year T-Note,Oct 2004,4.03\n" + //
				"10 Year T-Note,Nov 2004,4.36\n" + //
				"10 Year T-Note,Dec 2004,4.22\n" + //
				"10 Year T-Note,Jan 2005,4.13\n" + //
				"10 Year T-Note,Feb 2005,4.36\n" + //
				"10 Year T-Note,Mar 2005,4.5\n" + //
				"10 Year T-Note,Apr 2005,4.2\n" + //
				"10 Year T-Note,May 2005,4.01\n" + //
				"10 Year T-Note,Jun 2005,3.94\n" + //
				"10 Year T-Note,Jul 2005,4.29\n" + //
				"10 Year T-Note,Aug 2005,4.02\n" + //
				"10 Year T-Note,Sep 2005,4.33\n" + //
				"10 Year T-Note,Oct 2005,4.56\n" + //
				"10 Year T-Note,Nov 2005,4.5\n" + //
				"10 Year T-Note,Dec 2005,4.39\n" + //
				"10 Year T-Note,Jan 2006,4.53\n" + //
				"10 Year T-Note,Feb 2006,4.55\n" + //
				"10 Year T-Note,Mar 2006,4.85\n" + //
				"10 Year T-Note,Apr 2006,5.07\n" + //
				"10 Year T-Note,May 2006,5.11\n" + //
				"10 Year T-Note,Jun 2006,5.14\n" + //
				"10 Year T-Note,Jul 2006,4.99\n" + //
				"10 Year T-Note,Aug 2006,4.73\n" + //
				"10 Year T-Note,Sep 2006,4.63\n" + //
				"10 Year T-Note,Oct 2006,4.61\n" + //
				"10 Year T-Note,Nov 2006,4.46\n" + //
				"10 Year T-Note,Dec 2006,4.71\n" + //
				"10 Year T-Note,Jan 2007,4.83\n" + //
				"10 Year T-Note,Feb 2007,4.55\n" + //
				"10 Year T-Note,Mar 2007,4.65\n" + //
				"10 Year T-Note,Apr 2007,4.63\n" + //
				"10 Year T-Note,May 2007,4.89\n" + //
				"10 Year T-Note,Jun 2007,5.03\n" + //
				"10 Year T-Note,Jul 2007,4.77\n" + //
				"10 Year T-Note,Aug 2007,4.54\n" + //
				"10 Year T-Note,Sep 2007,4.58\n" + //
				"10 Year T-Note,Oct 2007,4.47\n" + //
				"10 Year T-Note,Nov 2007,3.97\n" + //
				"10 Year T-Note,Dec 2007,4.03\n" + //
				"10 Year T-Note,Jan 2008,3.64\n" + //
				"10 Year T-Note,Feb 2008,3.53\n" + //
				"10 Year T-Note,Mar 2008,3.43\n" + //
				"10 Year T-Note,Apr 2008,3.76\n" + //
				"10 Year T-Note,May 2008,4.05\n" + //
				"10 Year T-Note,Jun 2008,3.98\n" + //
				"10 Year T-Note,Jul 2008,3.98\n" + //
				"10 Year T-Note,Aug 2008,3.81\n" + //
				"10 Year T-Note,Sep 2008,3.83\n" + //
				"10 Year T-Note,Oct 2008,3.97\n" + //
				"10 Year T-Note,Nov 2008,2.96\n" + //
				"10 Year T-Note,Dec 2008,2.24\n" + //
				"10 Year T-Note,Jan 2009,2.84\n" + //
				"10 Year T-Note,Feb 2009,3.04\n" + //
				"10 Year T-Note,Mar 2009,2.68\n" + //
				"10 Year T-Note,Apr 2009,3.12\n" + //
				"10 Year T-Note,May 2009,3.46\n" + //
				"10 Year T-Note,Jun 2009,3.52\n" + //
				"10 Year T-Note,Jul 2009,3.5\n" + //
				"10 Year T-Note,Aug 2009,3.4\n" + //
				"10 Year T-Note,Sep 2009,3.31\n" + //
				"10 Year T-Note,Oct 2009,3.39\n" + //
				"10 Year T-Note,Nov 2009,3.2\n" + //
				"10 Year T-Note,Dec 2009,3.84\n" + //
				"10 Year T-Note,Jan 2010,3.61\n" + //
				"10 Year T-Note,Feb 2010,3.6\n" + //
				"10 Year T-Note,Mar 2010,3.7\n" + //
				"AAPL,Jan 2000,25.94\n" + //
				"AAPL,Feb 2000,28.66\n" + //
				"AAPL,Mar 2000,33.95\n" + //
				"AAPL,Apr 2000,31.01\n" + //
				"AAPL,May 2000,21\n" + //
				"AAPL,Jun 2000,26.19\n" + //
				"AAPL,Jul 2000,25.41\n" + //
				"AAPL,Aug 2000,30.47\n" + //
				"AAPL,Sep 2000,12.88\n" + //
				"AAPL,Oct 2000,9.78\n" + //
				"AAPL,Nov 2000,8.25\n" + //
				"AAPL,Dec 2000,7.44\n" + //
				"AAPL,Jan 2001,10.81\n" + //
				"AAPL,Feb 2001,9.12\n" + //
				"AAPL,Mar 2001,11.03\n" + //
				"AAPL,Apr 2001,12.74\n" + //
				"AAPL,May 2001,9.98\n" + //
				"AAPL,Jun 2001,11.62\n" + //
				"AAPL,Jul 2001,9.4\n" + //
				"AAPL,Aug 2001,9.27\n" + //
				"AAPL,Sep 2001,7.76\n" + //
				"AAPL,Oct 2001,8.78\n" + //
				"AAPL,Nov 2001,10.65\n" + //
				"AAPL,Dec 2001,10.95\n" + //
				"AAPL,Jan 2002,12.36\n" + //
				"AAPL,Feb 2002,10.85\n" + //
				"AAPL,Mar 2002,11.84\n" + //
				"AAPL,Apr 2002,12.14\n" + //
				"AAPL,May 2002,11.65\n" + //
				"AAPL,Jun 2002,8.86\n" + //
				"AAPL,Jul 2002,7.63\n" + //
				"AAPL,Aug 2002,7.38\n" + //
				"AAPL,Sep 2002,7.25\n" + //
				"AAPL,Oct 2002,8.03\n" + //
				"AAPL,Nov 2002,7.75\n" + //
				"AAPL,Dec 2002,7.16\n" + //
				"AAPL,Jan 2003,7.18\n" + //
				"AAPL,Feb 2003,7.51\n" + //
				"AAPL,Mar 2003,7.07\n" + //
				"AAPL,Apr 2003,7.11\n" + //
				"AAPL,May 2003,8.98\n" + //
				"AAPL,Jun 2003,9.53\n" + //
				"AAPL,Jul 2003,10.54\n" + //
				"AAPL,Aug 2003,11.31\n" + //
				"AAPL,Sep 2003,10.36\n" + //
				"AAPL,Oct 2003,11.44\n" + //
				"AAPL,Nov 2003,10.45\n" + //
				"AAPL,Dec 2003,10.69\n" + //
				"AAPL,Jan 2004,11.28\n" + //
				"AAPL,Feb 2004,11.96\n" + //
				"AAPL,Mar 2004,13.52\n" + //
				"AAPL,Apr 2004,12.89\n" + //
				"AAPL,May 2004,14.03\n" + //
				"AAPL,Jun 2004,16.27\n" + //
				"AAPL,Jul 2004,16.17\n" + //
				"AAPL,Aug 2004,17.25\n" + //
				"AAPL,Sep 2004,19.38\n" + //
				"AAPL,Oct 2004,26.2\n" + //
				"AAPL,Nov 2004,33.53\n" + //
				"AAPL,Dec 2004,32.2\n" + //
				"AAPL,Jan 2005,38.45\n" + //
				"AAPL,Feb 2005,44.86\n" + //
				"AAPL,Mar 2005,41.67\n" + //
				"AAPL,Apr 2005,36.06\n" + //
				"AAPL,May 2005,39.76\n" + //
				"AAPL,Jun 2005,36.81\n" + //
				"AAPL,Jul 2005,42.65\n" + //
				"AAPL,Aug 2005,46.89\n" + //
				"AAPL,Sep 2005,53.61\n" + //
				"AAPL,Oct 2005,57.59\n" + //
				"AAPL,Nov 2005,67.82\n" + //
				"AAPL,Dec 2005,71.89\n" + //
				"AAPL,Jan 2006,75.51\n" + //
				"AAPL,Feb 2006,68.49\n" + //
				"AAPL,Mar 2006,62.72\n" + //
				"AAPL,Apr 2006,70.39\n" + //
				"AAPL,May 2006,59.77\n" + //
				"AAPL,Jun 2006,57.27\n" + //
				"AAPL,Jul 2006,67.96\n" + //
				"AAPL,Aug 2006,67.85\n" + //
				"AAPL,Sep 2006,76.98\n" + //
				"AAPL,Oct 2006,81.08\n" + //
				"AAPL,Nov 2006,91.66\n" + //
				"AAPL,Dec 2006,84.84\n" + //
				"AAPL,Jan 2007,85.73\n" + //
				"AAPL,Feb 2007,84.61\n" + //
				"AAPL,Mar 2007,92.91\n" + //
				"AAPL,Apr 2007,99.8\n" + //
				"AAPL,May 2007,121.19\n" + //
				"AAPL,Jun 2007,122.04\n" + //
				"AAPL,Jul 2007,131.76\n" + //
				"AAPL,Aug 2007,138.48\n" + //
				"AAPL,Sep 2007,153.47\n" + //
				"AAPL,Oct 2007,189.95\n" + //
				"AAPL,Nov 2007,182.22\n" + //
				"AAPL,Dec 2007,198.08\n" + //
				"AAPL,Jan 2008,135.36\n" + //
				"AAPL,Feb 2008,125.02\n" + //
				"AAPL,Mar 2008,143.5\n" + //
				"AAPL,Apr 2008,173.95\n" + //
				"AAPL,May 2008,188.75\n" + //
				"AAPL,Jun 2008,167.44\n" + //
				"AAPL,Jul 2008,158.95\n" + //
				"AAPL,Aug 2008,169.53\n" + //
				"AAPL,Sep 2008,113.66\n" + //
				"AAPL,Oct 2008,107.59\n" + //
				"AAPL,Nov 2008,92.67\n" + //
				"AAPL,Dec 2008,85.35\n" + //
				"AAPL,Jan 2009,90.13\n" + //
				"AAPL,Feb 2009,89.31\n" + //
				"AAPL,Mar 2009,105.12\n" + //
				"AAPL,Apr 2009,125.83\n" + //
				"AAPL,May 2009,135.81\n" + //
				"AAPL,Jun 2009,142.43\n" + //
				"AAPL,Jul 2009,163.39\n" + //
				"AAPL,Aug 2009,168.21\n" + //
				"AAPL,Sep 2009,185.35\n" + //
				"AAPL,Oct 2009,188.5\n" + //
				"AAPL,Nov 2009,199.91\n" + //
				"AAPL,Dec 2009,210.73\n" + //
				"AAPL,Jan 2010,192.06\n" + //
				"AAPL,Feb 2010,204.62\n" + //
				"AAPL,Mar 2010,223.02\n";
	}

	//#end region	

}
