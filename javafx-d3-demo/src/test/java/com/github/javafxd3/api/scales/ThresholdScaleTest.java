package com.github.javafxd3.api.scales;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.core.Value;

@SuppressWarnings("javadoc")
public class ThresholdScaleTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// default domain and range, and apply functions
		ThresholdScale threshold = d3.scale().threshold();
		Array<Value> domain = threshold.domain();
		assertEquals(1, domain.length());
		assertEquals(0.5, domain.get(0, Double.class));

		Array<Double> range = threshold.range();
		assertEquals(2, range.length());
		assertEquals(0d, range.get(0, Double.class));
		assertEquals(1d, range.get(1, Double.class));

		assertEquals(0d, threshold.apply(0.49d).asDouble(),DELTA);
		assertEquals(1d, threshold.apply(0.51d).asDouble(),DELTA);

		// change domain and range
		threshold.domain(0.5, 0.8);
		assertEquals(0d, threshold.apply(0.48d).asDouble(),DELTA);
		assertEquals(1d, threshold.apply(0.51d).asDouble(),DELTA);
		// now undefined values are returned for input > 0.8
		assertTrue(threshold.apply(0.81).isUndefined());

		// setting range with 3 values
		threshold.range("a", "b", "c");
		assertEquals("a", threshold.apply(0.48d).asString());
		assertEquals("b", threshold.apply(0.51d).asString());
		assertEquals("c", threshold.apply(0.82d).asString());

		// invert extent
		assertEquals(0.5d, threshold.invertExtent("b").get(0, Double.class),DELTA);
		assertEquals(0.8d, threshold.invertExtent("b").get(1, Double.class),DELTA);

	}
}
