package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.core.Value;

public class PowScaleTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {

		// sqrt
		PowScale sqrt = d3.scale().sqrt();
		assertEquals(0.5, sqrt.exponent(), DELTA);

		// exponent
		PowScale scale = d3.scale().pow();
		assertEquals(1.0, scale.exponent(), DELTA);
		scale.exponent(5);
		assertEquals(5.0, scale.exponent(), DELTA);

		// get default domain
		scale = d3.scale().pow();
		assertEquals(2, scale.domain().length());
		assertEquals(0, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(1, (int) scale.domain().get(1, Value.class).asInt());

		// set the domain, keep the default range .get(0,1]
		scale.domain(10, 100);
		assertEquals(2, scale.domain().length());
		assertEquals(10, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(100, (int) scale.domain().get(1, Value.class).asInt());

		scale.domain("5", "6");
		assertEquals(2, scale.domain().length());
		assertEquals("5", scale.domain().get(0, Value.class).asString());
		assertEquals("6", scale.domain().get(1, Value.class).asString());

		scale.domain(-1, 0, 1).range(new String[] { "red", "white", "blue" });
		assertEquals(3, scale.domain().length());
		assertEquals(-1, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(0, (int) scale.domain().get(1, Value.class).asInt());
		assertEquals(1, (int) scale.domain().get(2, Value.class).asInt());

		// default range
		scale = d3.scale().pow();
		assertEquals(0.0, scale.range().get(0, Double.class), TOLERANCE);
		assertEquals(1.0, scale.range().get(1, Double.class), TOLERANCE);

		// set the range
		scale.range(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class), TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class), TOLERANCE);

		scale.range(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class), TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class), TOLERANCE);
		assertEquals(200.0, scale.range().get(2, Double.class), TOLERANCE);

		scale.range("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, String.class));
		assertEquals("bloh", scale.range().get(1, String.class));
		assertEquals("bluh", scale.range().get(2, String.class));

		// range round
		scale.rangeRound(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class), TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class), TOLERANCE);

		scale.rangeRound(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class), TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class), TOLERANCE);
		assertEquals(200.0, scale.range().get(2, Double.class), TOLERANCE);

		scale.rangeRound("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, String.class));
		assertEquals("bloh", scale.range().get(1, String.class));
		assertEquals("bluh", scale.range().get(2, String.class));

		// clamp
		assertEquals(false, scale.clamp());
		scale.clamp(true);
		assertEquals(true, scale.clamp());

		// ticks
		scale = d3.scale().pow();
		scale.domain(10, 20);
		assertEquals(3, scale.ticks(2).length());
		assertEquals(10.0, scale.ticks(2).get(0, Double.class), TOLERANCE);
		assertEquals(15.0, scale.ticks(2).get(1, Double.class), TOLERANCE);
		assertEquals(20.0, scale.ticks(2).get(2, Double.class), TOLERANCE);

		assertEquals(11, scale.ticks(11).length());
		assertEquals(10.0, scale.ticks(11).get(0, Double.class), TOLERANCE);
		assertEquals(11.0, scale.ticks(11).get(1, Double.class), TOLERANCE);
		assertEquals(15.0, scale.ticks(11).get(5, Double.class), TOLERANCE);
		assertEquals(20.0, scale.ticks(11).get(10, Double.class), TOLERANCE);

		// tickFormat
		scale = d3.scale().pow();
		scale.domain(10, 100);
		assertEquals("10", scale.tickFormat().format(10));
		assertEquals("10", scale.tickFormat(2).format(10));
		assertEquals("100", scale.tickFormat(2).format(100));
		assertEquals("$50.00", scale.tickFormat(20, "$,.2f").format(50));
		// FIXME: and the tickFormat(count,function) version

		// nice
		scale = d3.scale().pow();
		scale.domain(1.02, 4.98);
		assertEquals(1.02, scale.domain().get(0, Double.class), TOLERANCE);
		assertEquals(4.98, scale.domain().get(1, Double.class), TOLERANCE);
		scale.nice();
		assertEquals(1.0, scale.domain().get(0, Double.class), TOLERANCE);
		assertEquals(5.0, scale.domain().get(1, Double.class), TOLERANCE);

		// test nice(count) =>
		scale = d3.scale().pow();
		scale.domain(2.10007, 2.9);
		scale.nice(6);
		assertEquals(2.1, scale.domain().get(0, Double.class), TOLERANCE);
		assertEquals(2.9, scale.domain().get(1, Double.class), TOLERANCE);

		scale = d3.scale().pow();
		scale.domain(2.1005, 2.9);
		scale.nice(11);
		assertEquals(2.1d, scale.domain().get(0, Double.class), TOLERANCE);
		assertEquals(2.9d, scale.domain().get(1, Double.class), TOLERANCE);

		// apply the function
		scale = d3.scale().pow();
		scale.domain(1, 10).range(0, 10);
		assertEquals(-1, (int) scale.apply(0).asInt());
		assertEquals(10, (int) scale.apply(10).asInt());
		assertEquals(110, (int) scale.apply(100).asInt());
		assertEquals(-12, (int) scale.apply(-10).asInt());

		// invert
		assertEquals(91, (int) scale.invert(100).asInt());
		assertEquals(-89, (int) scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		PowScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain().get(0, Double.class), TOLERANCE);
		assertEquals(2.0, scale.domain().get(1, Double.class), TOLERANCE);

	}
}
