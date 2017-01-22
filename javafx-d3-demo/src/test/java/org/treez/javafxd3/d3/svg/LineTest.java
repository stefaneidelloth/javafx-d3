package org.treez.javafxd3.d3.svg;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.svg.datafunction.IndexFactorDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.IndexSwitchDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.XCaptureDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.YCaptureDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.YCoordsCounterDataFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

@SuppressWarnings("javadoc")
public class LineTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override	
    public void doTest() {
    	
    	
    	
        Line line = d3.svg().line();

        // interpolation
        assertEquals(InterpolationMode.LINEAR, line.interpolate());
        line.interpolate(InterpolationMode.CARDINAL);
        assertEquals(InterpolationMode.CARDINAL, line.interpolate());

        final List<Double> xCapture = new ArrayList<>();
        final List<Double> yCapture = new ArrayList<>();

        // default x and y function (data must be a               
        List<JavaScriptObject> data = new ArrayList<>();
        data.add(Array.fromDoubles(engine, new Double[]{0.0, 0.9}));
        data.add(Array.fromDoubles(engine, new Double[]{1.0, 1.0}));
        data.add(Array.fromDoubles(engine, new Double[]{2.0, 2.0}));
        
        String svgPath = line.generate(data);
        assertEquals("svg path","M0,0.9Q0.7999999999999999,0.89,1,1Q1.2,1.11,2,2", svgPath);

        // x and y
        line.x(new XCaptureDataFunction(engine, xCapture) );

        line.y(new YCaptureDataFunction(engine, yCapture));
        
        List<JavaScriptObject> coordsData = new ArrayList<>();
        coordsData.add(new Coords(engine,1, 1));
        coordsData.add(new Coords(engine,2, 2));
        coordsData.add(new Coords(engine,3, 3));

        svgPath = line.generate(coordsData);

        assertEquals(1.0, xCapture.get(0),DELTA);
        assertEquals(2.0, xCapture.get(1),DELTA);
        assertEquals(3.0, xCapture.get(2),DELTA);

        assertEquals(1.0, yCapture.get(0),DELTA);
        assertEquals(2.0, yCapture.get(1),DELTA);
        assertEquals(3.0, yCapture.get(2),DELTA);

        svgPath = line.generate(coordsData);
        // System.out.println("defined : " + d);

        // x and y constants
        svgPath = line.x(50)
                .y(30)
                .generate(coordsData);
        // System.out.println("defined : " + d);

        line.defined(new IndexSwitchDataFunction());
        
        final Coords counter = new Coords(engine, 0, 0);
        // not called
        line.y(new YCoordsCounterDataFunction(engine, yCapture, counter));

        // does not assertEquals(2, counter.y);
        // Smoke test radial line

        RadialLine radial = d3.svg().radialLine();
        radial.angle(3.5);
        radial.radius(56);
        radial.radius(new IndexFactorDataFunction(25.6));

    }

   
}
