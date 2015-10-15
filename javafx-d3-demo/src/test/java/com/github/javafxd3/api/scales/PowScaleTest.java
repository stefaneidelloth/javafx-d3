package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;

@SuppressWarnings("javadoc")
public class PowScaleTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);

		// sqrt
		PowScale sqrt = d3.scale().sqrt();
		assertEquals(0.5, sqrt.exponent(),DELTA);

		// exponent
		PowScale scale = d3.scale().pow();
		assertEquals(1.0, scale.exponent(),DELTA);
		scale.exponent(5);
		assertEquals(5.0, scale.exponent(),DELTA);

		// get default domain
		scale = d3.scale().pow();
		assertEquals(2, scale.domain().length);
		assertEquals(0, scale.domain()[0].asInt());
		assertEquals(1, scale.domain()[1].asInt());

		// set the domain, keep the default range [0,1]
		scale.domain(10, 100);
		assertEquals(2, scale.domain().length);
		assertEquals(10, scale.domain()[0].asInt());
		assertEquals(100, scale.domain()[1].asInt());

		scale.domain("5", "6");
		assertEquals(2, scale.domain().length);
		assertEquals("5", scale.domain()[0].asString());
		assertEquals("6", scale.domain()[1].asString());

		scale.domain(-1, 0, 1).range(
				new String[] { "red", "white", "blue" });
		assertEquals(3, scale.domain().length);
		assertEquals(-1, scale.domain()[0].asInt());
		assertEquals(0, scale.domain()[1].asInt());
		assertEquals(1, scale.domain()[2].asInt());

		// default range
		scale = d3.scale().pow();
		assertEquals(0.0, scale.range()[0]);
		assertEquals(1.0, scale.range()[1]);

		// set the range
		scale.range(0, 100);
		assertEquals(0.0, scale.range()[0]);
		assertEquals(100.0, scale.range()[1]);

		scale.range(0, 100, 200);
		assertEquals(0.0, scale.range()[0]);
		assertEquals(100.0, scale.range()[1]);
		assertEquals(200.0, scale.range()[2]);

		scale.range("blah", "bloh", "bluh");
		assertEquals("blah", scale.range()[0]);
		assertEquals("bloh", scale.range()[1]);
		assertEquals("bluh", scale.range()[2]);

		// range round
		scale.rangeRound(0, 100);
		assertEquals(0.0, scale.range()[0]);
		assertEquals(100.0, scale.range()[1]);

		scale.rangeRound(0, 100, 200);
		assertEquals(0.0, scale.range()[0]);
		assertEquals(100.0, scale.range()[1]);
		assertEquals(200.0, scale.range()[2]);

		scale.rangeRound("blah", "bloh", "bluh");
		assertEquals("blah", scale.range()[0]);
		assertEquals("bloh", scale.range()[1]);
		assertEquals("bluh", scale.range()[2]);

		// clamp
		assertEquals(false, scale.clamp());
		scale.clamp(true);
		assertEquals(true, scale.clamp());

		// ticks
		scale = d3.scale().pow();
		scale.domain(10, 20);
		assertEquals(3, scale.ticks(2).length);
		assertEquals(10.0, scale.ticks(2)[0]);
		assertEquals(15.0, scale.ticks(2)[1]);
		assertEquals(20.0, scale.ticks(2)[2]);

		assertEquals(11, scale.ticks(11).length);
		assertEquals(10.0, scale.ticks(11)[0]);
		assertEquals(11.0, scale.ticks(11)[1]);
		assertEquals(15.0, scale.ticks(11)[5]);
		assertEquals(20.0, scale.ticks(11)[10]);

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
		assertEquals(1.02, scale.domain()[0]);
		assertEquals(4.98, scale.domain()[1]);
		scale.nice();
		assertEquals(1.0, scale.domain()[0]);
		assertEquals(5.0, scale.domain()[1]);

		// test nice(count) =>
		scale = d3.scale().pow();
		scale.domain(2.10007, 2.9);
		scale.nice(6);
		assertEquals(2.1, scale.domain()[0]);
		assertEquals(2.9, scale.domain()[1]);

		scale = d3.scale().pow();
		scale.domain(2.1005, 2.9);
		scale.nice(11);
		assertEquals(2.1d, scale.domain()[0]);
		assertEquals(2.9d, scale.domain()[1]);

		// apply the function
		scale = d3.scale().pow();
		scale.domain(1, 10).range(0, 10);
		assertEquals(-1, scale.apply(0).asInt());
		assertEquals(10, scale.apply(10).asInt());
		assertEquals(110, scale.apply(100).asInt());
		assertEquals(-12, scale.apply(-10).asInt());

		// invert
		assertEquals(91, scale.invert(100).asInt());
		assertEquals(-89, scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		PowScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain()[0]);
		assertEquals(2.0, scale.domain()[1]);

	}
}
