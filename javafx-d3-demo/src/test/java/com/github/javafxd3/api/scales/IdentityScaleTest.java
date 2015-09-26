package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;

@SuppressWarnings("javadoc")
public class IdentityScaleTest extends AbstractTestCase {

	@Override
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		IdentityScale scale = d3.scale().identity();
		// get default domain
		assertEquals(2, scale.domain().length);
		assertEquals(0, scale.domain()[0].asInt());
		assertEquals(1, scale.domain()[1].asInt());

		// set the domain, keep the default range [0,1]
		scale.domain(0, 10);
		assertEquals(2, scale.domain().length);
		assertEquals(0, scale.domain()[0].asInt());
		assertEquals(10, scale.domain()[1].asInt());

		scale.domain("5", "6");
		assertEquals(2, scale.domain().length);
		assertEquals("5", scale.domain()[0].asString());
		assertEquals("6", scale.domain()[1].asString());

		scale.domain(-1, 0, 1).range(new String[] { "red", "white", "blue" });
		assertEquals(3, scale.domain().length);
		assertEquals(0, scale.domain()[0].asInt());
		assertEquals(0, scale.domain()[1].asInt());
		assertEquals(0, scale.domain()[2].asInt());

		// default range
		scale = d3.scale().identity();
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

		// ticks
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

		assertEquals(6, scale.ticks(4).length);
		assertEquals(10.0, scale.ticks(4)[0]);
		assertEquals(12.0, scale.ticks(4)[1]);
		assertEquals(14.0, scale.ticks(4)[2]);
		assertEquals(16.0, scale.ticks(4)[3]);
		assertEquals(18.0, scale.ticks(4)[4]);
		assertEquals(20.0, scale.ticks(4)[5]);

		// tickFormat
		scale.domain(10, 20);
		assertEquals("10", scale.tickFormat(2).format(10));
		assertEquals("20", scale.tickFormat(2).format(20));

		assertEquals("10.00", scale.tickFormat(200).format(10));
		assertEquals("20.00", scale.tickFormat(200).format(20));

		assertEquals("015.00", scale.tickFormat(200, "06f").format(15));

		// apply the function
		scale.domain(0, 1).range(0, 10);
		assertEquals(0, scale.apply(0).asInt());
		assertEquals(15, scale.apply(15).asInt());
		assertEquals(100, scale.apply(100).asInt());
		assertEquals(-10, scale.apply(-10).asInt());

		// invert
		assertEquals(100, scale.invert(100).asInt());
		assertEquals(-100, scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		IdentityScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain()[0]);
		assertEquals(2.0, scale.domain()[1]);

	}
}
