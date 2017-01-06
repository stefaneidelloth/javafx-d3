package org.treez.javafxd3.d3.svg;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.svg.datafunction.IndexSwitchDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.XCaptureDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.XDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.YCaptureDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.YCoordsCounterDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.YDataFunction;

public class AreaTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {

		Area area = d3.svg().area();

		// interpolator
		assertEquals(InterpolationMode.LINEAR, area.interpolate());
		area.interpolate(InterpolationMode.CARDINAL);
		assertEquals(InterpolationMode.CARDINAL, area.interpolate());

		final List<Double> xCapture = new ArrayList<>();
		final List<Double> yCapture = new ArrayList<>();

		// default x and y function (data must be a
		area.apply(new Object[] { new Integer[] { 0, 0 }, new Integer[] { 1, 1 }, new Integer[] { 2, 2 } });

		// x and y
		area.x(new XDataFunction(webEngine));

		area.x0(new XCaptureDataFunction(webEngine, xCapture));

		area.x1(new XDataFunction(webEngine));

		area.y(new YDataFunction(webEngine));

		area.y0(new YCaptureDataFunction(webEngine, yCapture));

		area.y1(new YDataFunction(webEngine));

		area.apply(
				new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });

		assertEquals(3, xCapture.size());
		assertEquals(1.0, xCapture.get(0), DELTA);
		assertEquals(2.0, xCapture.get(1), DELTA);
		assertEquals(3.0, xCapture.get(2), DELTA);

		assertEquals(3, yCapture.size());
		assertEquals(1.0, yCapture.get(0), DELTA);
		assertEquals(2.0, yCapture.get(1), DELTA);
		assertEquals(3.0, yCapture.get(2), DELTA);

		area.apply(
				new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });
		// System.out.println("defined : " + d);

		// x and y constants
		area.x(50).y(30).x0(20).x1(22).y0(27).y1(35).apply(
				new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });
		// System.out.println("defined : " + d);

		// TODO: tension

		// defined : it does not seem to work
		area.defined(new IndexSwitchDataFunction());

		final Coords counter = new Coords(webEngine, 0, 0);

		// not called
		area.y(new YCoordsCounterDataFunction(webEngine, yCapture, counter));

		// does not assertEquals(2, counter.y);

	}

}
