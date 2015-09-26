package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class LogScaleTest extends AbstractTestCase {

	@Override
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		LogScale scale = d3.scale().log();
		// get default domain
		assertEquals(2, scale.domain().length);
		assertEquals(1, scale.domain()[0].asInt());
		assertEquals(10, scale.domain()[1].asInt());

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
		scale = d3.scale().log();
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
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals(10, scale.ticks().length);
		assertEquals(10.0, scale.ticks()[0]);
		assertEquals(20.0, scale.ticks()[1]);
		assertEquals(100.0, scale.ticks()[9]);

		scale.domain(10, 1000);
		// assertEquals(17, scale.ticks().length());

		// tickFormat
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals("1e+1", scale.tickFormat().format(10));
		assertEquals("1e+1", scale.tickFormat(2).format(10));
		assertEquals("1e+2", scale.tickFormat(2).format(100));
		assertEquals("$50.00", scale.tickFormat(20, "$,.2f").format(50));
		String format = scale.tickFormat(20, new DatumFunction<String>() {
			@Override
			public String apply(Element context, Value d, int index) {
				System.out.println("FORMATTER " + d.asDouble());
				return "blah";
			}
		}).format(50);
		System.out.println("FORMATTER " + format);

		// nice
		scale = d3.scale().log();
		scale.domain(1.02, 4.98);
		assertEquals(1.02, scale.domain()[0]);
		assertEquals(4.98, scale.domain()[1]);
		scale.nice();
		assertEquals(1.0, scale.domain()[0]);
		assertEquals(10.0, scale.domain()[1]);

		// apply the function
		scale = d3.scale().log();
		scale.domain(1, 10).range(0, 10);
		assertEquals(0, scale.apply(0).asInt());
		assertEquals(10, scale.apply(10).asInt());
		assertEquals(20, scale.apply(100).asInt());
		assertEquals(0, scale.apply(-10).asInt());

		// invert
		assertEquals(1410065408, scale.invert(100).asInt());
		assertEquals(0, scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		LogScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain()[0]);
		assertEquals(2.0, scale.domain()[1]);

		// base
		assertEquals(10, scale.base());
		scale.base(5);
		assertEquals(5, scale.base());

	}
}
