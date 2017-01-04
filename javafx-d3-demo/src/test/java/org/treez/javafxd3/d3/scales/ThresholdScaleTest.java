package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Value;

public class ThresholdScaleTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {

		// default domain and range, and apply functions
		ThresholdScale threshold = d3.scale().threshold();
		Array<Value> domain = threshold.domain();
		assertEquals(1, domain.length());
		assertEquals(0.5, domain.get(0, Double.class), TOLERANCE);

		Array<Double> range = threshold.range();
		assertEquals(2, range.length());
		assertEquals(0d, range.get(0, Double.class), TOLERANCE);
		assertEquals(1d, range.get(1, Double.class), TOLERANCE);

		assertEquals(0d, threshold.apply(0.49d).asDouble(), DELTA);
		assertEquals(1d, threshold.apply(0.51d).asDouble(), DELTA);

		// change domain and range
		threshold.domain(0.5, 0.8);
		assertEquals(0d, threshold.apply(0.48d).asDouble(), DELTA);
		assertEquals(1d, threshold.apply(0.51d).asDouble(), DELTA);
		// now undefined values are returned for input > 0.8
		assertTrue(threshold.apply(0.81).isUndefined());

		// setting range with 3 values
		threshold.range("a", "b", "c");
		assertEquals("a", threshold.apply(0.48d).asString());
		assertEquals("b", threshold.apply(0.51d).asString());
		assertEquals("c", threshold.apply(0.82d).asString());

		// invert extent
		assertEquals(0.5d, threshold.invertExtent("b").get(0, Double.class), DELTA);
		assertEquals(0.8d, threshold.invertExtent("b").get(1, Double.class), DELTA);

	}
}
