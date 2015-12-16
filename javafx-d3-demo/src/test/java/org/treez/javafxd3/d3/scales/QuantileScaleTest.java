package org.treez.javafxd3.d3.scales;


import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.scales.QuantileScale;

import org.treez.javafxd3.d3.AbstractTestCase;

@SuppressWarnings("javadoc")
public class QuantileScaleTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override	
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		QuantileScale quantile = d3.scale().quantile();
		// domain and range are null
		assertEquals(0, quantile.domain().length());
		assertEquals(0, quantile.range().length());

		// domain
		quantile.domain(1, 1, 1, 1, 2, 5, 6, 100);
		quantile.range("one", "two", "three", "last one");
		Array<Double> quantiles = quantile.quantiles();

		assertEquals(1.0, quantiles.get(0, Double.class),DELTA);
		assertEquals(1.5, quantiles.get(1, Double.class),DELTA);
		assertEquals(5.25, quantiles.get(2, Double.class),DELTA);

		assertEquals("one", quantile.apply(0.5).asString());
		assertEquals("two", quantile.apply(1.0).asString());
		assertEquals("three", quantile.apply(3.0).asString());
		assertEquals("three", quantile.apply(5.01).asString());
		assertEquals("last one", quantile.apply(6.0).asString());
		assertEquals("last one", quantile.apply(60.0).asString());

		//invertextent
		assertEquals(1.5, quantile.invertExtent("three").get(0, Double.class),DELTA);
		assertEquals(5.25, quantile.invertExtent("three").get(1, Double.class),DELTA);

		//copy
		QuantileScale copy = quantile.copy();
		copy.domain(1, 2, 3);
		assertEquals(1.0, quantiles.get(0, Double.class),DELTA);
		assertEquals(1.5, quantiles.get(1, Double.class),DELTA);
		assertEquals(5.25, quantiles.get(2, Double.class),DELTA);

	}
}
