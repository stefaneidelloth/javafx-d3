package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.core.Value;

@SuppressWarnings("javadoc")
public class LogScaleTest extends AbstractTestCase {

	@Override
	public void doTest() {		
		
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
		assertEquals(0.0, scale.range().get(0, Double.class),TOLERANCE);
		assertEquals(1.0, scale.range().get(1, Double.class),TOLERANCE);

		// set the range
		scale.range(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class),TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class),TOLERANCE);

		scale.range(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class),TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class),TOLERANCE);
		assertEquals(200.0, scale.range().get(2, Double.class),TOLERANCE);

		scale.range("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, String.class));
		assertEquals("bloh", scale.range().get(1, String.class));
		assertEquals("bluh", scale.range().get(2, String.class));

		// range round
		scale.rangeRound( 0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class),TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class),TOLERANCE);

		scale.rangeRound( 0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class),TOLERANCE);
		assertEquals(100.0, scale.range().get(1, Double.class),TOLERANCE);
		assertEquals(200.0, scale.range().get(2, Double.class),TOLERANCE);

		scale.rangeRound( "blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, String.class));
		assertEquals("bloh", scale.range().get(1, String.class));
		assertEquals("bluh", scale.range().get(2, String.class));

		// clamp
		assertEquals(false, scale.clamp());
		scale.clamp( true);
		assertEquals(true, scale.clamp());

		// ticks
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals(10, scale.ticks().length());
		assertEquals(10.0, scale.ticks().get(0, Double.class),TOLERANCE);
		assertEquals(20.0, scale.ticks().get(1, Double.class),TOLERANCE);
		assertEquals(100.0, scale.ticks().get(9, Double.class),TOLERANCE);

		scale.domain(10, 1000);
		// assertEquals(17, scale.ticks().length()());

		// tickFormat
		scale = d3.scale().log();
		scale.domain(10, 100);
		assertEquals("1e+1", scale.tickFormat().format(10));
		assertEquals("1e+1", scale.tickFormat(2).format(10));
		assertEquals("1e+2", scale.tickFormat(2).format(100));
		assertEquals("$50.00", scale.tickFormat(20, "$,.2f").format(50));
		String format = scale.tickFormat(20, new LogScaleTestDataFunction(webEngine)).format(50);
		System.out.println("FORMATTER " + format);

		// nice
		scale = d3.scale().log();
		scale.domain(1.02, 4.98);
		assertEquals(1.02, scale.domain().get(0, Double.class),TOLERANCE);
		assertEquals(4.98, scale.domain().get(1, Double.class),TOLERANCE);
		scale.nice();
		assertEquals(1.0, scale.domain().get(0, Double.class),TOLERANCE);
		assertEquals(10.0, scale.domain().get(1, Double.class),TOLERANCE);

		// apply the function
		scale = d3.scale().log();
		scale.domain(1, 10).range(0, 10);
		assertEquals(Double.NaN, scale.apply(0).asDouble(), TOLERANCE);
		assertEquals(10, (int) scale.apply(10).asInt());
		assertEquals(20, (int) scale.apply(100).asInt());
		assertEquals(Double.NaN, scale.apply(-10).asDouble(), TOLERANCE);

		// invert
		assertEquals(1000, (int) scale.invert(30).asInt());
		assertEquals(0, (int) scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		LogScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain().get(0, Double.class),TOLERANCE);
		assertEquals(2.0, scale.domain().get(1, Double.class),TOLERANCE);

		// base
		assertEquals(10, scale.base());
		scale.base(5);
		assertEquals(5, scale.base());

	}
}
