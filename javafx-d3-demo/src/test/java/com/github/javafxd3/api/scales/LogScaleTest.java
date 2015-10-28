package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class LogScaleTest extends AbstractTestCase {

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		LogScale scale = d3.scale().log();
		// get default domain
		assertEquals(2, scale.domain().length());
		assertEquals(1, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(10, (int) scale.domain().get(1, Value.class).asInt());

		// set the domain, keep the default range .get(0,1]
		scale.domain(10, 100);
		assertEquals(2, scale.domain().length());
		assertEquals(10, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(100, (int) scale.domain().get(1, Value.class).asInt());

		scale.domain("5", "6");
		assertEquals(2, scale.domain().length());
		assertEquals("5", scale.domain().get(0, Value.class).asString());
		assertEquals("6", scale.domain().get(1, Value.class).asString());

		scale.domain(-1, 0, 1).range(
				new String[] { "red", "white", "blue" });
		assertEquals(3, scale.domain().length());
		assertEquals(-1, (int) scale.domain().get(0, Value.class).asInt());
		assertEquals(0, (int) scale.domain().get(1, Value.class).asInt());
		assertEquals(1, (int) scale.domain().get(2, Value.class).asInt());

		// default range
		scale = d3.scale().log();
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(1.0, scale.range().get(1, Double.class));

		// set the range
		scale.range(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));

		scale.range(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));
		assertEquals(200.0, scale.range().get(2, Double.class));

		scale.range("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, Double.class));
		assertEquals("bloh", scale.range().get(1, Double.class));
		assertEquals("bluh", scale.range().get(2, Double.class));

		// range round
		scale.rangeRound(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));

		scale.rangeRound(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));
		assertEquals(200.0, scale.range().get(2, Double.class));

		scale.rangeRound("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, Double.class));
		assertEquals("bloh", scale.range().get(1, Double.class));
		assertEquals("bluh", scale.range().get(2, Double.class));

		// clamp
		assertEquals(false, scale.clamp());
		scale.clamp(true);
		assertEquals(true, scale.clamp());

		// ticks
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals(10, scale.ticks().length());
		assertEquals(10.0, scale.ticks().get(0, Double.class));
		assertEquals(20.0, scale.ticks().get(1, Double.class));
		assertEquals(100.0, scale.ticks().get(9, Double.class));

		scale.domain(10, 1000);
		// assertEquals(17, scale.ticks().length()());

		// tickFormat
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals("1e+1", scale.tickFormat().format(10));
		assertEquals("1e+1", scale.tickFormat(2).format(10));
		assertEquals("1e+2", scale.tickFormat(2).format(100));
		assertEquals("$50.00", scale.tickFormat(20, "$,.2f").format(50));
		String format = scale.tickFormat(20, new DatumFunction<String>() {
			@Override
			public String apply(Object context, Object d, int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				System.out.println("FORMATTER " + datum.asDouble());
				return "blah";
			}
		}).format(50);
		System.out.println("FORMATTER " + format);

		// nice
		scale = d3.scale().log();
		scale.domain(1.02, 4.98);
		assertEquals(1.02, scale.domain().get(0, Double.class));
		assertEquals(4.98, scale.domain().get(1, Double.class));
		scale.nice();
		assertEquals(1.0, scale.domain().get(0, Double.class));
		assertEquals(10.0, scale.domain().get(1, Double.class));

		// apply the function
		scale = d3.scale().log();
		scale.domain(1, 10).range(0, 10);
		assertEquals(0, (int) scale.apply(0).asInt());
		assertEquals(10, (int) scale.apply(10).asInt());
		assertEquals(20, (int) scale.apply(100).asInt());
		assertEquals(0, (int) scale.apply(-10).asInt());

		// invert
		assertEquals(1410065408, (int) scale.invert(100).asInt());
		assertEquals(0, (int) scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		LogScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain().get(0, Double.class));
		assertEquals(2.0, scale.domain().get(1, Double.class));

		// base
		assertEquals(10, scale.base());
		scale.base(5);
		assertEquals(5, scale.base());

	}
}
