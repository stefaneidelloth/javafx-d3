package org.treez.javafxd3.d3.svg;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.svg.Area;
import org.treez.javafxd3.d3.svg.Area.InterpolationMode;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.svg.datumfunction.IndexSwitchDatumFunction;
import org.treez.javafxd3.d3.svg.datumfunction.XCaptureDatumFunction;
import org.treez.javafxd3.d3.svg.datumfunction.XDatumFunction;
import org.treez.javafxd3.d3.svg.datumfunction.YCaptureDatumFunction;
import org.treez.javafxd3.d3.svg.datumfunction.YCoordsCounterDatumFunction;
import org.treez.javafxd3.d3.svg.datumfunction.YDatumFunction;

@SuppressWarnings("javadoc")
public class AreaTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {

		D3 d3 = new D3(webEngine);

		Area area = d3.svg().area();

		// interpolator
		assertEquals(InterpolationMode.LINEAR, area.interpolate());
		area.interpolate(InterpolationMode.CARDINAL);
		assertEquals(InterpolationMode.CARDINAL, area.interpolate());

		final List<Double> xCapture = new ArrayList<>();
		final List<Double> yCapture = new ArrayList<>();

		// default x and y function (data must be a
		String d = area.apply(new Object[] { new Integer[] { 0, 0 }, new Integer[] { 1, 1 }, new Integer[] { 2, 2 } });

		// x and y
		area.x(new XDatumFunction(webEngine));

		area.x0(new XCaptureDatumFunction(webEngine, xCapture));

		area.x1(new XDatumFunction(webEngine));

		area.y(new YDatumFunction(webEngine));

		area.y0(new YCaptureDatumFunction(webEngine, yCapture));

		area.y1(new YDatumFunction(webEngine));

		d = area.apply(new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });

		assertEquals(3, xCapture.size());
		assertEquals(1.0, xCapture.get(0), DELTA);
		assertEquals(2.0, xCapture.get(1), DELTA);
		assertEquals(3.0, xCapture.get(2), DELTA);

		assertEquals(3, yCapture.size());
		assertEquals(1.0, yCapture.get(0), DELTA);
		assertEquals(2.0, yCapture.get(1), DELTA);
		assertEquals(3.0, yCapture.get(2), DELTA);

		d = area.apply(new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });
		// System.out.println("defined : " + d);

		// x and y constants
		d = area.x(50).y(30).x0(20).x1(22).y0(27).y1(35)
				.apply(new Object[] { new Coords(webEngine, 1, 1), new Coords(webEngine, 2, 2), new Coords(webEngine, 3, 3) });
				// System.out.println("defined : " + d);

		// TODO: tension

		// defined : it does not seem to work
		area.defined(new IndexSwitchDatumFunction());
		
		final Coords counter = new Coords(webEngine, 0, 0);
		
		// not called
		area.y(new YCoordsCounterDatumFunction(webEngine, yCapture, counter) );

		// does not assertEquals(2, counter.y);

	}

}
