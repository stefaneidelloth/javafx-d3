package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.LogScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.selection.datafunction.PrefixPlusIndexDataFunction;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.datafunction.TickTestDataFunction;

@SuppressWarnings("javadoc")
public class AxisTest extends AbstractTestCase {	

	@Override	
    public void doTest() {
    	
    	
    	
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
        LogScale logScale = d3.scale().log();
        axis.scale(logScale);

        // ticks
        assertEquals(1, axis.ticks().length());
        assertEquals(10, (int) axis.ticks().get(0,  Value.class).asInt());

        axis.ticks(12);
        assertEquals(12, (int) axis.ticks().get(0,  Value.class).asInt());

        axis.ticks(15, "blah");
        assertEquals(15, (int) axis.ticks().get(0,  Value.class).asInt());
        assertEquals("blah", axis.ticks().get(1, Value.class).asString());

      //  Interval interval = d3.time().day();
      //  axis.ticks(interval, 10);
      //  assertEquals(interval, axis.ticks()[0].as());
      //  assertEquals(10, axis.ticks()[1].asInt());

        // FIXME: smoke test to be cross checked
        DataFunction<String> f = new PrefixPlusIndexDataFunction("index");
        
        axis.ticks(8, f);
        assertEquals(8, (int) axis.ticks().get(0,  Value.class).asInt());
        assertEquals(f, axis.ticks().get(1,  Value.class).as());

        // tick values
        assertNull(axis.tickValues());
        axis.tickValues(1, 2, 3);
        assertEquals(1, (int) axis.tickValues().get(0,  Value.class).asInt());
        assertEquals(2, (int) axis.tickValues().get(1,  Value.class).asInt());
        assertEquals(3, (int) axis.tickValues().get(2,  Value.class).asInt());

        // tick subdivide : replaced
        // assertEquals(0, axis.tickSubdivide());
        // axis.tickSubdivide(9);
        // assertEquals(9, axis.tickSubdivide());

        // tick size
        assertEquals(6, axis.tickSize(),TOLERANCE);
        assertEquals(6, axis.innerTickSize(),TOLERANCE);
        assertEquals(6, axis.outerTickSize(),TOLERANCE);
        axis.tickSize(6);
        axis.innerTickSize(5);
        axis.outerTickSize(5);
        assertEquals(5, axis.innerTickSize(),TOLERANCE);
        assertEquals(5, axis.outerTickSize(),TOLERANCE);

        axis.tickSize(6, 0);
        axis.tickSize(6, 3, 2);

        // tick padding
        assertEquals(3, axis.tickPadding(),TOLERANCE);
        axis.tickPadding(5);
        assertEquals(5, axis.tickPadding(),TOLERANCE);

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
        axis.tickFormat(new TickTestDataFunction(webEngine, counter));
        
        // apply
        Selection svg = d3.select("svg").attr("width", 100)
                .attr("height", 100).append("g")
                .attr("transform", "translate(32,50)");
        
       

        axis.apply(svg);
        int counterLength = counter.length();
        assertEquals(4, counterLength);

        Axis axis2 = d3.svg().axis();
        svg.call(axis2);
        
    }
}
