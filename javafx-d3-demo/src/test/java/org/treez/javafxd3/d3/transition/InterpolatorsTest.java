package org.treez.javafxd3.d3.transition;

import java.util.List;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.color.HSLColor;
import org.treez.javafxd3.d3.color.RGBColor;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Transform;
import org.treez.javafxd3.d3.interpolators.AbstractInterpolatorFactory;
import org.treez.javafxd3.d3.interpolators.CallableInterpolator;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.interpolators.InterpolatorFactory;
import org.treez.javafxd3.d3.interpolators.Interpolators;


public class InterpolatorsTest extends AbstractTestCase {

	@Override	
	public void doTest() {
		testD3InterpolateNumber();
		testD3InterpolateRound();
		testD3InterpolateString();
		testD3InterpolateRgb();
		testD3InterpolateHsl();
		// testD3InterpolateHcl();
		// testD3InterpolateLab();
		testD3InterpolateObject();
		testD3InterpolateArray();
		testD3InterpolateTransform();
		testD3InterpolateZoom();
		testD3Interpolators();
	}

	private void testD3InterpolateZoom() {
		Interpolator<Double[]> interpolator = Interpolators
				.interpolateZoom(webEngine,new Double[]{5.0, 6.0, 30.0},
						new Double[]{1.0, 2.0, 20.0});

		Double[] array = interpolator.interpolate(0d);
		assertEquals(5.0, array[0], TOLERANCE);
		assertEquals(6.0, array[1], TOLERANCE);
		assertEquals(30.0, array[2], TOLERANCE);

		array = interpolator.interpolate(1d);
		assertEquals(1.0, array[0], TOLERANCE);
		assertEquals(2.0, array[1], TOLERANCE);
		assertEquals(20.0, array[2], TOLERANCE);

		array = interpolator.interpolate(0.5);
		assertEquals(2.6, array[0], TOLERANCE);
		assertEquals(3.6, array[1], TOLERANCE);
		assertEquals(25.114, array[2], 1e-3);

	}

	private void testD3InterpolateTransform() {
		
		Transform transform = new Transform(webEngine);
		
		Interpolator<Transform> interpolator = Interpolators
				.interpolateTransform(webEngine, transform.parse("rotate(40)"),
						transform.parse("rotate(80)"));
		assertEquals(40d, interpolator.interpolate(0d).rotate(), TOLERANCE);
		assertEquals(60d, interpolator.interpolate(0.5d).rotate(), TOLERANCE);
		assertEquals(80d, interpolator.interpolate(1d).rotate(), TOLERANCE);
	}

	private void testD3InterpolateArray() {
		Interpolator<Object[]> interpolator = Interpolators.interpolateArray(webEngine,
				new Object[]{10, 100}, new Object[]{20, 200, 2000});
		
		assertEquals(15d, interpolator.interpolate(0.5)[0]);
		assertEquals(150d, interpolator.interpolate(0.5)[1]);
		assertEquals(2000, interpolator.interpolate(0.5)[2]);
	}

	private void testD3InterpolateObject() {
		// use coords
		Coords a = new Coords(webEngine, 10, 100);
		Coords b = new Coords(webEngine, 20, 200);
		
				
		Interpolator<Coords> interpolator = Interpolators.interpolateObject(webEngine,a,
				b);

		assertEquals(15.0, interpolator.interpolate(0.5).x(),TOLERANCE);
		assertEquals(150.0, interpolator.interpolate(0.5).y(),TOLERANCE);
		
	}

	private void testD3InterpolateRgb() {
		String a = "#ff0000";
		String b = "#0000ff";
		// string variant
		Interpolator<RGBColor> rgb = Interpolators.interpolateRgb(webEngine,a, b);
		assertEquals(255, rgb.interpolate(0d).r());
		assertEquals(0, rgb.interpolate(0d).g());
		assertEquals(0, rgb.interpolate(0d).b());

		//
		assertEquals(0, rgb.interpolate(1d).r());
		assertEquals(0, rgb.interpolate(1d).g());
		assertEquals(255, rgb.interpolate(1d).b());

		// color variant
		Colors colors = new Colors(webEngine);
		rgb = Interpolators.interpolateRgb(webEngine, colors.rgb(255, 0, 0),
				colors.rgb(0, 0, 255));
		assertEquals(255, rgb.interpolate(0d).r());
		assertEquals(0, rgb.interpolate(0d).g());
		assertEquals(0, rgb.interpolate(0d).b());

		assertEquals(0, rgb.interpolate(1d).r());
		assertEquals(0, rgb.interpolate(1d).g());
		assertEquals(255, rgb.interpolate(1d).b());

		// interpolator factory string variant
		// rgb = Interpolators.interpolateRgb.create(a, b);
		// assertEquals(255, rgb.interpolate(0).r());
		// assertEquals(0, rgb.interpolate(0).g());
		// assertEquals(0, rgb.interpolate(0).b());
		//
		// assertEquals(0, rgb.interpolate(1).r());
		// assertEquals(0, rgb.interpolate(1).g());
		// assertEquals(255, rgb.interpolate(1).b());
		//
		// // interpolator factory color variant
		// rgb = Interpolators.interpolateRgb.create(d3.rgb(255, 0, 0),
		// d3.rgb(0, 0, 255));
		// assertEquals(255, rgb.interpolate(0).r());
		// assertEquals(0, rgb.interpolate(0).g());
		// assertEquals(0, rgb.interpolate(0).b());
		//
		// assertEquals(0, rgb.interpolate(1).r());
		// assertEquals(0, rgb.interpolate(1).g());
		// assertEquals(255, rgb.interpolate(1).b());

	}

	private void testD3InterpolateHsl() {
		String a = "#ff0000";
		String b = "#0000ff";
		// string variant
		Interpolator<HSLColor> interpolator = Interpolators
				.interpolateHsl(webEngine,a, b);
		assertEquals(0, interpolator.interpolate(0d).h(),TOLERANCE);
		assertEquals(1.0, interpolator.interpolate(0d).s(),TOLERANCE);
		assertEquals(0.5, interpolator.interpolate(0d).l(),TOLERANCE);

		//
		assertEquals(240, interpolator.interpolate(1d).h(),TOLERANCE);
		assertEquals(1.0, interpolator.interpolate(1d).s(),TOLERANCE);
		assertEquals(0.5, interpolator.interpolate(1d).l(),TOLERANCE);

		// color variant
		Colors colors = new Colors(webEngine);
		
		
		interpolator = Interpolators.interpolateHsl(webEngine,colors.rgb(255, 0, 0),
				colors.rgb(0, 0, 255));
		assertEquals(0, interpolator.interpolate(0d).h(),TOLERANCE);
		assertEquals(1.0, interpolator.interpolate(0d).s(),TOLERANCE);
		assertEquals(0.5, interpolator.interpolate(0d).l(),TOLERANCE);

		assertEquals(240, interpolator.interpolate(1d).h(),TOLERANCE);
		assertEquals(1.0, interpolator.interpolate(1d).s(),TOLERANCE);
		assertEquals(0.5, interpolator.interpolate(1d).l(),TOLERANCE);

		// interpolator factory string variant
		// rgb = Interpolators.interpolateRgb.create(a, b);
		// assertEquals(255, rgb.interpolate(0).r());
		// assertEquals(0, rgb.interpolate(0).g());
		// assertEquals(0, rgb.interpolate(0).b());
		//
		// assertEquals(0, rgb.interpolate(1).r());
		// assertEquals(0, rgb.interpolate(1).g());
		// assertEquals(255, rgb.interpolate(1).b());
		//
		// // interpolator factory color variant
		// rgb = Interpolators.interpolateRgb.create(d3.rgb(255, 0, 0),
		// d3.rgb(0, 0, 255));
		// assertEquals(255, rgb.interpolate(0).r());
		// assertEquals(0, rgb.interpolate(0).g());
		// assertEquals(0, rgb.interpolate(0).b());
		//
		// assertEquals(0, rgb.interpolate(1).r());
		// assertEquals(0, rgb.interpolate(1).g());
		// assertEquals(255, rgb.interpolate(1).b());

	}

	private void testD3Interpolators() {
		List<InterpolatorFactory<?>> interpolators = Interpolators
				.interpolators(webEngine);
		assertEquals(1, interpolators.size());
		InterpolatorFactory<?> factory = interpolators.get(0);
		Interpolator<?> interpolator = factory.create("10px", "20px");
		String interpolated = interpolator.interpolate(0.5).toString();
		assertEquals("15px", interpolated);

		//InterpolatorFactory<Coords> newInterpolator = 
				new AbstractInterpolatorFactory<Coords>(webEngine) {
			@Override
			public <I> Interpolator<Coords> create(Object a, Object b) {
				if (b instanceof Coords) {
					return new CallableInterpolator<Coords>(webEngine) {
						@Override
						public Coords interpolate(Object t) {
							return new Coords(webEngine, 15, 20);
						}
					};
				} else {
					return null;
				}
			}
		};
		// // push one
		// FIXME : adding a new interpolator does not work:
		// cause d3.js cannot call the Java InterpolatorFactory-
		// we should find a way to add the JSO function instead
		// see
		// https://github.com/mbostock/d3/blob/master/src/interpolate/interpolate.js
		
		//Interpolators.interpolators(webEngine).add(newInterpolator);
		//assertEquals(2, Interpolators.interpolators(webEngine).size());
	}

	private void testD3InterpolateString() {
		// string
		Interpolator<String> strInterpolator = Interpolators.interpolateString(webEngine,
				"Saw 10 (movie)", "Saw 20 (movie)");
		assertEquals("Saw 15 (movie)", strInterpolator.interpolate(0.5));

	}

	private void testD3InterpolateRound() {
		// char
		Interpolator<Character> charInterpolator = Interpolators
				.interpolateRound(webEngine,'a', 'j');
		assertEquals('a', (char) charInterpolator.interpolate(0d));
		assertEquals('c', (char) charInterpolator.interpolate(0.25));
		assertEquals('j', (char) charInterpolator.interpolate(1d));

		// int
		Interpolator<Integer> intInterpolator = Interpolators.interpolateRound(webEngine,
				10, 20);
		assertEquals(10, (int) intInterpolator.interpolate(0d));
		assertEquals(13, (int) intInterpolator.interpolate(0.25));
		assertEquals(20, (int) intInterpolator.interpolate(1d));

		// short
		Interpolator<Short> shortInterpolator = Interpolators.interpolateRound(webEngine,
				(short) 10, (short) 20);
		assertEquals(10, (short) shortInterpolator.interpolate(0d));
		assertEquals(13, (short) shortInterpolator.interpolate(0.25));
		assertEquals(20, (short) shortInterpolator.interpolate(1d));

		// byte
		Interpolator<Byte> byteInterpolator = Interpolators.interpolateRound(webEngine,
				(byte) 10, (byte) 20);
		assertEquals(10, (byte) byteInterpolator.interpolate(0d));
		assertEquals(13, (byte) byteInterpolator.interpolate(0.25));
		assertEquals(20, (byte) byteInterpolator.interpolate(1d));

		// long
		Interpolator<Long> longInterpolator = Interpolators.interpolateRound(webEngine,
				(long) 10, (long) 20);
		assertEquals(10l, (long) longInterpolator.interpolate(0d));
		assertEquals(13l, (long) longInterpolator.interpolate(0.25));
		assertEquals(20l, (long) longInterpolator.interpolate(1d));

		// double
		Interpolator<Long> doubleInterpolator = Interpolators.interpolateRound(webEngine,
				(double) 10, (double) 20);
		assertEquals(10l, (long) doubleInterpolator.interpolate(0d));
		assertEquals(13l, (long) doubleInterpolator.interpolate(0.25));
		assertEquals(20l, (long) doubleInterpolator.interpolate(1d));

	}

	private void testD3InterpolateNumber() {

		// byte
		Interpolator<Double> byteInterpolator = Interpolators
				.interpolateNumber(webEngine, (byte) 10, (byte) 20);
		assertEquals(10.0, byteInterpolator.interpolate(0d),TOLERANCE);
		assertEquals(12.5, byteInterpolator.interpolate(0.25),TOLERANCE);
		assertEquals(20.0, byteInterpolator.interpolate(1d),TOLERANCE);

		// double
		Interpolator<Double> doubleInterpolator = Interpolators
				.interpolateNumber(webEngine,0.1, 0.2);
		assertEquals(0.1, doubleInterpolator.interpolate(0d), TOLERANCE);
		assertEquals(0.125, doubleInterpolator.interpolate(0.25), TOLERANCE);
		assertEquals(0.2, doubleInterpolator.interpolate(1d), TOLERANCE);
		// float
		Interpolator<Double> floatInterpolator = Interpolators
				.interpolateNumber(webEngine,0.1f, 0.2f);
		assertEquals(0.1, floatInterpolator.interpolate(0d), TOLERANCE);
		assertEquals(0.125, floatInterpolator.interpolate(0.25), TOLERANCE);
		assertEquals(0.2, floatInterpolator.interpolate(1d), TOLERANCE);
		// int
		Interpolator<Double> intInterpolator = Interpolators.interpolateNumber(webEngine,
				10, 20);
		assertEquals(10.0, intInterpolator.interpolate(0d),TOLERANCE);
		assertEquals(12.5, intInterpolator.interpolate(0.25),TOLERANCE);
		assertEquals(20.0, intInterpolator.interpolate(1d),TOLERANCE);
		//
		// long
		Interpolator<Double> longInterpolator = Interpolators
				.interpolateNumber(webEngine,10L, 20L);
		assertEquals(10.0, longInterpolator.interpolate(0d),TOLERANCE);
		assertEquals(12.5, longInterpolator.interpolate(0.25),TOLERANCE);
		assertEquals(20.0, longInterpolator.interpolate(1d),TOLERANCE);

		// short
		Interpolator<Double> shortInterpolator = Interpolators
				.interpolateNumber(webEngine,(short) 10, (short) 20);
		assertEquals(10.0, shortInterpolator.interpolate(0d),TOLERANCE);
		assertEquals(12.5, shortInterpolator.interpolate(0.25),TOLERANCE);
		assertEquals(20.0, shortInterpolator.interpolate(1d),TOLERANCE);

		// factory
		// LinearScale scale = d3.scale.linear().interpolate(
		// Interpolators.interpolateNumber);
		// scale.apply(7);
		//
		// Interpolator<Double> interpolator = Interpolators.interpolateNumber
		// .create(5, 6);
		// assertEquals(5.5, interpolator.interpolate(0.5).doubleValue(),
		// TOLERANCE);
	}
}
