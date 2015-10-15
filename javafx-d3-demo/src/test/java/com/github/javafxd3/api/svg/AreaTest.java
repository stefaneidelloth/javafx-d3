package com.github.javafxd3.api.svg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.Area.InterpolationMode;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class AreaTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		Area area = d3.svg().area();

		// interpolator
		assertEquals(InterpolationMode.LINEAR, area.interpolate());
		area.interpolate(InterpolationMode.CARDINAL);
		assertEquals(InterpolationMode.CARDINAL, area.interpolate());

		final List<Double> xCapture = new ArrayList<Double>();
		final List<Double> yCapture = new ArrayList<Double>();

		// default x and y function (data must be a
		String d = area.apply(new Object[]{new Integer[]{0, 0}, new Integer[]{1, 1}, new Integer[]{2, 2}});

		// x and y
		area.x(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				xCapture.add(datum.<Coords> as().x);
				return datum.<Coords> as().x;
			}
		});

		area.x0(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				xCapture.add(datum.<Coords> as().x);
				return datum.<Coords> as().x;
			}
		});

		area.x1(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.<Coords> as().x;
			}
		});

		area.y(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.<Coords> as().y;
			}
		});

		area.y0(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				yCapture.add(datum.<Coords> as().y);
				return datum.<Coords> as().y;
			}
		});

		area.y1(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.<Coords> as().y;
			}
		});

		d = area.apply(new Object[]{new Coords(1, 1), new Coords(2, 2),
				new Coords(3, 3)});
		assertEquals(3, xCapture.size());
		assertEquals(1.0, xCapture.get(0),DELTA);
		assertEquals(2.0, xCapture.get(1),DELTA);
		assertEquals(3.0, xCapture.get(2),DELTA);

		assertEquals(3, yCapture.size());
		assertEquals(1.0, yCapture.get(0),DELTA);
		assertEquals(2.0, yCapture.get(1),DELTA);
		assertEquals(3.0, yCapture.get(2),DELTA);

		d = area.apply(new Object[]{new Coords(1, 1), new Coords(2, 2),
				new Coords(3, 3)});
		// System.out.println("defined : " + d);

		// x and y constants
		d = area.x(50)
				.y(30)
				.x0(20)
				.x1(22)
				.y0(27)
				.y1(35)
				.apply(new Object[]{new Coords(1, 1), new Coords(2, 2),
						new Coords(3, 3)});
		// System.out.println("defined : " + d);

		// TODO: tension

		// defined : it does not seem to work
		area.defined(new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Object context, final Object d,
					final int index) {
				// System.out.println(context);
				// System.out.println(d);
				// System.out.println(index);
				return index == 1 ? false : true;
			}
		});
		final Coords counter = new Coords(0, 0);
		// not called
		area.y(new DatumFunction<Double>() {
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
