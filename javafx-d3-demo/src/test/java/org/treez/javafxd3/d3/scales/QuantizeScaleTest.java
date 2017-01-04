package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.AbstractTestCase;

public class QuantizeScaleTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {

		QuantizeScale quantize = d3.scale().quantize();

		// default
		assertEquals(0.0, quantize.apply(0.49).asDouble(), DELTA);
		assertEquals(1.0, quantize.apply(0.51).asDouble(), DELTA);
		assertEquals(1.0, quantize.apply(2.5).asDouble(), DELTA);
		assertEquals(0.0, quantize.apply(-100).asDouble(), DELTA);

		// range
		quantize.range(0.0, 1.0, 100.0);
		assertEquals(0.0, quantize.apply(-10.0).asDouble(), DELTA);
		assertEquals(0.0, quantize.apply(0.0).asDouble(), DELTA);
		// assertEquals(1.0, quantize.apply(0.26).asDouble());
		// assertEquals(1.0, quantize.apply(0.5).asDouble());
		assertEquals(100.0, quantize.apply(1.0).asDouble(), DELTA);
		assertEquals(100.0, quantize.apply(261).asDouble(), DELTA);

		// domain: only 1st and last number are taken
		quantize.domain(0.0, 1.0, 30.0);
		// it takes only the first and last
		assertEquals(0.0, quantize.domain().get(0, Double.class), TOLERANCE);
		assertEquals(30.0, quantize.domain().get(1, Double.class), TOLERANCE);

		assertEquals(0.0, quantize.apply(-10.0).asDouble(), DELTA);
		assertEquals(0.0, quantize.apply(0.0).asDouble(), DELTA);
		assertEquals(0.0, quantize.apply(4.9).asDouble(), DELTA);
		assertEquals(0.0, quantize.apply(5.9).asDouble(), DELTA);
		assertEquals(1.0, quantize.apply(10.1).asDouble(), DELTA);
		assertEquals(1.0, quantize.apply(18.0).asDouble(), DELTA);
		assertEquals(100.0, quantize.apply(20.1).asDouble(), DELTA);
		assertEquals(100.0, quantize.apply(25.0).asDouble(), DELTA);
		assertEquals(100.0, quantize.apply(261).asDouble(), DELTA);

		//invertextent
		assertEquals(0.0, quantize.invertExtent(0.0).get(0, Double.class), DELTA);
		assertEquals(10.0, quantize.invertExtent(0.0).get(1, Double.class), DELTA);

		// getters
		assertEquals(0.0, quantize.range().get(0, Double.class), TOLERANCE);
		assertEquals(1.0, quantize.range().get(1, Double.class), TOLERANCE);
		assertEquals(100.0, quantize.range().get(2, Double.class), TOLERANCE);

		// copy
		quantize.copy().range(5, 6, 7);
		assertEquals(0.0, quantize.range().get(0, Double.class), TOLERANCE);
		assertEquals(1.0, quantize.range().get(1, Double.class), TOLERANCE);
		assertEquals(100.0, quantize.range().get(2, Double.class), TOLERANCE);

	}
}