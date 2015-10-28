package com.github.javafxd3.api.svg;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.LinearScale;
import com.github.javafxd3.api.scales.OrdinalScale;
import com.github.javafxd3.api.svg.Axis.Orientation;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class AxisTest extends AbstractTestCase {
	
	
	

	@Override
	@Test
    public void doTest() {
    	
    	D3 d3 = new D3(webEngine);
    	
        Axis axis = d3.svg().axis();

        // default scale
        LinearScale s = axis.scale();
        assertNotNull(s);
        int asInt = s.domain().get(0, Value.class).asInt();
        assertEquals(0, asInt);
        // default orientation
        assertEquals(Orientation.BOTTOM, axis.orient());
        // set orientation
        axis.orient(Orientation.TOP);
        assertEquals(Orientation.TOP, axis.orient());

        // set scale
        axis.scale(d3.scale().log());

        // ticks
        assertEquals(1, axis.ticks().length);
        assertEquals(10, axis.ticks()[0].asInt());

        axis.ticks(12);
        assertEquals(12, axis.ticks()[0].asInt());

        axis.ticks(15, "blah");
        assertEquals(15, axis.ticks()[0].asInt());
        assertEquals("blah", axis.ticks()[1].asString());

      //  Interval interval = d3.time().day();
      //  axis.ticks(interval, 10);
      //  assertEquals(interval, axis.ticks()[0].as());
      //  assertEquals(10, axis.ticks()[1].asInt());

        // FIXME: smoke test to be cross checked
        DatumFunction<String> f = new DatumFunction<String>() {
            @Override
            public String apply(final Object context, final Object d, final int index) {
                return "index" + index;
            }
        };
        axis.ticks(8, f);
        assertEquals(8, axis.ticks()[0].asInt());
        assertEquals(f, axis.ticks()[1].as());

        // tick values
        assertNull(axis.tickValues());
        axis.tickValues(1, 2, 3);
        assertEquals(1, axis.tickValues()[0].asInt());
        assertEquals(2, axis.tickValues()[1].asInt());
        assertEquals(3, axis.tickValues()[2].asInt());

        // tick subdivide : replaced
        // assertEquals(0, axis.tickSubdivide());
        // axis.tickSubdivide(9);
        // assertEquals(9, axis.tickSubdivide());

        // tick size
        assertEquals(6, axis.tickSize());
        assertEquals(6, axis.innerTickSize());
        assertEquals(6, axis.outerTickSize());
        axis.tickSize(6);
        axis.innerTickSize(5);
        axis.outerTickSize(5);
        assertEquals(5, axis.innerTickSize());
        assertEquals(5, axis.outerTickSize());

        axis.tickSize(6, 0);
        axis.tickSize(6, 3, 2);

        // tick padding
        assertEquals(3, axis.tickPadding());
        axis.tickPadding(5);
        assertEquals(5, axis.tickPadding());

        // tick format
        assertNull(axis.tickFormat());
        axis.tickFormat(d3.format("3"));
        assertNotNull(axis.tickFormat());

        // index in tick format
        OrdinalScale s2 = d3.scale().ordinal();
        axis = d3.svg().axis().scale(s2);
        s2.domain(5, 15, 20, 100);
        s2.range(1, 2, 3, 4);
        final StringBuffer counter = new StringBuffer();
        axis.tickFormat(new DatumFunction<String>() {

            @Override
            public String apply(final Object context, final Object d, final int index) {
            	
            	Value datum = (Value) d;						
				Element element =(Element) context;
				
                System.out.println("INDEX " + index + " " + datum.as());
                assertTrue(index >= 0 && index < 4);
                counter.append("x");
                if (index % 2 == 0) {
                    return "";
                }
                return "" + index;
            }
        });
        // apply
        Selection svg = d3.select("root").append("svg").attr("width", 100)
                .attr("height", 100).append("g")
                .attr("transform", "translate(32,50)");

        axis.apply(svg);
        assertEquals(4, counter.length());

        Axis axis2 = d3.svg().axis().apply(svg);
    }
}
