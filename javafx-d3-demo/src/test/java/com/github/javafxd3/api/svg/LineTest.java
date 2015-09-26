package com.github.javafxd3.api.svg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.Line.InterpolationMode;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class LineTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

    @Override
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
        @SuppressWarnings("unused")
        String d = line.generate(new Object[]{new Integer[]{0, 0},
        		new Integer[]{1, 1}, new Integer[]{2, 2}});

        // x and y
        line.x(new DatumFunction<Double>() {
            @Override
            public Double apply(final Element context, final Value d,
                    final int index) {
                xCapture.add(d.<Coords> as().x);
                return d.<Coords> as().x;
            }
        });

        line.y(new DatumFunction<Double>() {
            @Override
            public Double apply(final Element context, final Value d,
                    final int index) {
                yCapture.add(d.<Coords> as().y);
                return d.<Coords> as().y;
            }
        });

        d = line.generate(new Object[]{new Coords(1, 1), new Coords(2, 2),
                new Coords(3, 3)});

        assertEquals(1.0, xCapture.get(0),DELTA);
        assertEquals(2.0, xCapture.get(1),DELTA);
        assertEquals(3.0, xCapture.get(2),DELTA);

        assertEquals(1.0, yCapture.get(0),DELTA);
        assertEquals(2.0, yCapture.get(1),DELTA);
        assertEquals(3.0, yCapture.get(2),DELTA);

        d = line.generate(new Object[]{new Coords(1, 1), new Coords(2, 2),
                new Coords(3, 3)});
        // System.out.println("defined : " + d);

        // x and y constants
        d = line.x(50)
                .y(30)
                .generate(
                		new Object[]{new Coords(1, 1), new Coords(2, 2),
                                new Coords(3, 3)});
        // System.out.println("defined : " + d);

        line.defined(new DatumFunction<Boolean>() {
            @Override
            public Boolean apply(final Element context, final Value d,
                    final int index) {
                // System.out.println(context);
                // System.out.println(d);
                // System.out.println(index);
                return index == 1 ? false : true;
            }
        });
        final Coords counter = new Coords(0, 0);
        // not called
        line.y(new DatumFunction<Double>() {
            @Override
            public Double apply(final Element context, final Value d,
                    final int index) {
                counter.y = (counter.y + 1);
                yCapture.add(d.<Coords> as().y);
                return d.<Coords> as().y;
            }
        });

        // does not assertEquals(2, counter.y);
        // Smoke test radial line

        RadialLine radial = d3.svg().radialLine();
        radial.angle(3.5);
        radial.radius(56);
        radial.radius(new DatumFunction<Double>() {
            @Override
            public Double apply(final Element context, final Value d, final int index) {
                return index * 25.6;
            }
        });

    }

    static class Coords {
        public double x;
        public double y;

        public Coords(final double x, final double y) {
            super();
            this.x = x;
            this.y = y;
        }

        public static Coords get(final double x, final double y) {
            return new Coords(x, y);
        }
    }
}
