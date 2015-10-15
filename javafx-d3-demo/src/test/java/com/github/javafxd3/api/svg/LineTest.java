package com.github.javafxd3.api.svg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.Line.InterpolationMode;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;

@SuppressWarnings("javadoc")
public class LineTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override
	@Test
    public void doTest() {
    	
    	D3 d3 = new D3(webEngine);
    	
        Line line = d3.svg().line();

        // interpolation
        assertEquals(InterpolationMode.LINEAR, line.interpolate());
        line.interpolate(InterpolationMode.CARDINAL);
        assertEquals(InterpolationMode.CARDINAL, line.interpolate());

        final List<Double> xCapture = new ArrayList<Double>();
        final List<Double> yCapture = new ArrayList<Double>();

        // default x and y function (data must be a               
        List<JavaScriptObject> data = new ArrayList<>();
        data.add(Array.fromDoubles(webEngine, new Double[]{0.0, 0.9}));
        data.add(Array.fromDoubles(webEngine, new Double[]{1.0, 1.0}));
        data.add(Array.fromDoubles(webEngine, new Double[]{2.0, 2.0}));
        
        String d = line.generate(data);

        // x and y
        line.x(new DatumFunction<Double>() {
            @Override
            public Double apply(final Object context, final Object d,
                    final int index) {
            	
            	Value datum = (Value) d;						
				Element element =(Element) context;
				
                xCapture.add(datum.<Coords> as().x);
                return datum.<Coords> as().x;
            }
        });

        line.y(new DatumFunction<Double>() {
            @Override
            public Double apply(final Object context, final Object d,
                    final int index) {
            	
            	Value datum = (Value) d;						
				Element element =(Element) context;
				
                yCapture.add(datum.<Coords> as().y);
                return datum.<Coords> as().y;
            }
        });
        
        List<JavaScriptObject> coordsData = new ArrayList<>();
        coordsData.add(new Coords(webEngine,1, 1));
        coordsData.add(new Coords(webEngine,2, 2));
        coordsData.add(new Coords(webEngine,3, 3));

        d = line.generate(coordsData);

        assertEquals(1.0, xCapture.get(0),DELTA);
        assertEquals(2.0, xCapture.get(1),DELTA);
        assertEquals(3.0, xCapture.get(2),DELTA);

        assertEquals(1.0, yCapture.get(0),DELTA);
        assertEquals(2.0, yCapture.get(1),DELTA);
        assertEquals(3.0, yCapture.get(2),DELTA);

        d = line.generate(coordsData);
        // System.out.println("defined : " + d);

        // x and y constants
        d = line.x(50)
                .y(30)
                .generate(coordsData);
        // System.out.println("defined : " + d);

        line.defined(new DatumFunction<Boolean>() {
            @Override
            public Boolean apply(final Object context, final Object d,
                    final int index) {
                // System.out.println(context);
                // System.out.println(d);
                // System.out.println(index);
                return index == 1 ? false : true;
            }
        });
        final Coords counter = new Coords(webEngine, 0, 0);
        // not called
        line.y(new DatumFunction<Double>() {
            @Override
            public Double apply(final Object context, final Object d,
                    final int index) {
            	
            	Value datum = (Value) d;						
				Element element =(Element) context;
				
                counter.y = (counter.y + 1);
                yCapture.add(datum.<Coords> as().y);
                return datum.<Coords> as().y;
            }
        });

        // does not assertEquals(2, counter.y);
        // Smoke test radial line

        RadialLine radial = d3.svg().radialLine();
        radial.angle(3.5);
        radial.radius(56);
        radial.radius(new DatumFunction<Double>() {
            @Override
            public Double apply(final Object context, final Object d, final int index) {
                return index * 25.6;
            }
        });

    }

    static class Coords extends JavaScriptObject {
        public double x;
        public double y;

        public Coords(WebEngine webEngine, final double x, final double y) {
            super(webEngine);
            this.x = x;
            this.y = y;
        }

        public static Coords get(WebEngine webEngine, final double x, final double y) {
            return new Coords(webEngine, x, y);
        }
    }
}
