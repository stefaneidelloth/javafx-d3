package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;

@SuppressWarnings("javadoc")
public class ThresholdScaleTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// default domain and range, and apply functions
		ThresholdScale threshold = d3.scale().threshold();
		Object[] domain = threshold.domain();
		assertEquals(1, domain.length);
		assertEquals(0.5, domain[0]);

		Object[] range = threshold.range();
		assertEquals(2, range.length);
		assertEquals(0d, range[0]);
		assertEquals(1d, range[1]);

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
		assertEquals(0.5d, threshold.invertExtent("b")[0],DELTA);
		assertEquals(0.8d, threshold.invertExtent("b")[1],DELTA);

	}
}
