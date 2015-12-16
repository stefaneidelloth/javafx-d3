package org.treez.javafxd3.d3.transition;

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

import org.treez.javafxd3.d3.AbstractTestCase;

@SuppressWarnings("javadoc")
public class InterpolatorsTest extends AbstractTestCase {

	private static final double DELTA = 0.001;

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
				.interpolateZoom(new Double[]{5.0, 6.0, 30.0},
						new Double[]{1.0, 2.0, 20.0});

		Double[] array = interpolator.interpolate(0);
		assertEquals(5.0, array[0], DELTA);
		assertEquals(6.0, array[1], DELTA);
		assertEquals(30.0, array[2], DELTA);

		array = interpolator.interpolate(1);
		assertEquals(1.0, array[0], DELTA);
		assertEquals(2.0, array[1], DELTA);
		assertEquals(20.0, array[2], DELTA);

		array = interpolator.interpolate(0.5);
		assertEquals(2.6, array[0], DELTA);
		assertEquals(3.6, array[1], DELTA);
		assertEquals(25.114, array[2], DELTA);

	}

	private void testD3InterpolateTransform() {
		
		Transform transform = new Transform(webEngine);
		
		Interpolator<Transform> interpolator = Interpolators
				.interpolateTransform(webEngine, transform.parse("rotate(40)"),
						transform.parse("rotate(80)"));
		assertEquals(40d, interpolator.interpolate(0).rotate(), DELTA);
		assertEquals(60d, interpolator.interpolate(0.5).rotate(), DELTA);
		assertEquals(80d, interpolator.interpolate(1).rotate(), DELTA);
	}

	private void testD3InterpolateArray() {
		Interpolator<Object[]> interpolator = Interpolators.interpolateArray(
				new Object[]{10, 100}, new Object[]{20, 200, 2000});
		assertEquals(15d, interpolator.interpolate(0.5)[0]);
		assertEquals(150d, interpolator.interpolate(0.5)[1]);
		assertEquals(2000d, interpolator.interpolate(0.5)[2]);
	}

	private void testD3InterpolateObject() {
		// use coords
		Coords a = new Coords(webEngine, 10, 100);
		Coords b = new Coords(webEngine, 20, 200);
		
				
		Interpolator<Coords> interpolator = Interpolators.interpolateObject(a,
				b);

		assertEquals(15.0, interpolator.interpolate(0.5).x(),DELTA);
		assertEquals(150.0, interpolator.interpolate(0.5).y(),DELTA);
		
	}

	private void testD3InterpolateRgb() {
		String a = "#ff0000";
		String b = "#0000ff";
		// string variant
		Interpolator<RGBColor> rgb = Interpolators.interpolateRgb(a, b);
		assertEquals(255, rgb.interpolate(0).r());
		assertEquals(0, rgb.interpolate(0).g());
		assertEquals(0, rgb.interpolate(0).b());

		//
		assertEquals(0, rgb.interpolate(1).r());
		assertEquals(0, rgb.interpolate(1).g());
		assertEquals(255, rgb.interpolate(1).b());

		// color variant
		Colors colors = new Colors(webEngine);
		rgb = Interpolators.interpolateRgb(colors.rgb(255, 0, 0),
				colors.rgb(0, 0, 255));
		assertEquals(255, rgb.interpolate(0).r());
		assertEquals(0, rgb.interpolate(0).g());
		assertEquals(0, rgb.interpolate(0).b());

		assertEquals(0, rgb.interpolate(1).r());
		assertEquals(0, rgb.interpolate(1).g());
		assertEquals(255, rgb.interpolate(1).b());

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
				.interpolateHsl(a, b);
		assertEquals(0, interpolator.interpolate(0).h());
		assertEquals(1.0, interpolator.interpolate(0).s(),DELTA);
		assertEquals(0.5, interpolator.interpolate(0).l(),DELTA);

		//
		assertEquals(240, interpolator.interpolate(1).h(),DELTA);
		assertEquals(1.0, interpolator.interpolate(1).s(),DELTA);
		assertEquals(0.5, interpolator.interpolate(1).l(),DELTA);

		// color variant
		Colors colors = new Colors(webEngine);
		
		
		interpolator = Interpolators.interpolateHsl(colors.rgb(255, 0, 0),
				colors.rgb(0, 0, 255));
		assertEquals(0, interpolator.interpolate(0).h());
		assertEquals(1.0, interpolator.interpolate(0).s(),DELTA);
		assertEquals(0.5, interpolator.interpolate(0).l(),DELTA);

		assertEquals(240, interpolator.interpolate(1).h(),DELTA);
		assertEquals(1.0, interpolator.interpolate(1).s(),DELTA);
		assertEquals(0.5, interpolator.interpolate(1).l(),DELTA);

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
		InterpolatorFactory<?>[] interpolators = Interpolators
				.interpolators();
		assertEquals(1, interpolators.length);
		InterpolatorFactory<?> factory = interpolators[0];
		Interpolator<?> interpolator = factory.create("10px", "20px");
		String interpolated = interpolator.interpolate(0.5).toString();
		assertEquals("15px", interpolated);

		InterpolatorFactory<Coords> newInterpolator = new AbstractInterpolatorFactory<Coords>() {
			@Override
			public <I> Interpolator<Coords> create(final I a, final I b) {
				if (b instanceof Coords) {
					return new CallableInterpolator<Coords>() {
						@Override
						public Coords interpolate(final double t) {
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
		// Interpolators.interpolators().push(newInterpolator);
		// assertEquals(2, Interpolators.interpolators().length());
	}

	private void testD3InterpolateString() {
		// string
		Interpolator<String> strInterpolator = Interpolators.interpolateString(
				"Saw 10 (movie)", "Saw 20 (movie)");
		assertEquals("Saw 15 (movie)", strInterpolator.interpolate(0.5));

	}

	private void testD3InterpolateRound() {
		// char
		Interpolator<Character> charInterpolator = Interpolators
				.interpolateRound('a', 'j');
		assertEquals('a', (char) charInterpolator.interpolate(0));
		assertEquals('c', (char) charInterpolator.interpolate(0.25));
		assertEquals('j', (char) charInterpolator.interpolate(1));

		// int
		Interpolator<Integer> intInterpolator = Interpolators.interpolateRound(
				10, 20);
		assertEquals(10, (int) intInterpolator.interpolate(0));
		assertEquals(13, (int) intInterpolator.interpolate(0.25));
		assertEquals(20, (int) intInterpolator.interpolate(1));

		// short
		Interpolator<Short> shortInterpolator = Interpolators.interpolateRound(
				(short) 10, (short) 20);
		assertEquals(10, (short) shortInterpolator.interpolate(0));
		assertEquals(13, (short) shortInterpolator.interpolate(0.25));
		assertEquals(20, (short) shortInterpolator.interpolate(1));

		// byte
		Interpolator<Byte> byteInterpolator = Interpolators.interpolateRound(
				(byte) 10, (byte) 20);
		assertEquals(10, (byte) byteInterpolator.interpolate(0));
		assertEquals(13, (byte) byteInterpolator.interpolate(0.25));
		assertEquals(20, (byte) byteInterpolator.interpolate(1));

		// long
		Interpolator<Long> longInterpolator = Interpolators.interpolateRound(
				(long) 10, (long) 20);
		assertEquals(10l, (long) longInterpolator.interpolate(0));
		assertEquals(13l, (long) longInterpolator.interpolate(0.25));
		assertEquals(20l, (long) longInterpolator.interpolate(1));

		// double
		Interpolator<Long> doubleInterpolator = Interpolators.interpolateRound(
				(double) 10, (double) 20);
		assertEquals(10l, (long) doubleInterpolator.interpolate(0));
		assertEquals(13l, (long) doubleInterpolator.interpolate(0.25));
		assertEquals(20l, (long) doubleInterpolator.interpolate(1));

	}

	private void testD3InterpolateNumber() {

		// byte
		Interpolator<Double> byteInterpolator = Interpolators
				.interpolateNumber((byte) 10, (byte) 20);
		assertEquals(10.0, byteInterpolator.interpolate(0),DELTA);
		assertEquals(12.5, byteInterpolator.interpolate(0.25),DELTA);
		assertEquals(20.0, byteInterpolator.interpolate(1),DELTA);

		// double
		Interpolator<Double> doubleInterpolator = Interpolators
				.interpolateNumber(0.1, 0.2);
		assertEquals(0.1, doubleInterpolator.interpolate(0), 0.000001);
		assertEquals(0.125, doubleInterpolator.interpolate(0.25), 0.000001);
		assertEquals(0.2, doubleInterpolator.interpolate(1), 0.000001);
		// float
		Interpolator<Double> floatInterpolator = Interpolators
				.interpolateNumber(0.1f, 0.2f);
		assertEquals(0.1, floatInterpolator.interpolate(0), 0.000001);
		assertEquals(0.125, floatInterpolator.interpolate(0.25), 0.000001);
		assertEquals(0.2, floatInterpolator.interpolate(1), 0.000001);
		// int
		Interpolator<Double> intInterpolator = Interpolators.interpolateNumber(
				10, 20);
		assertEquals(10.0, intInterpolator.interpolate(0),DELTA);
		assertEquals(12.5, intInterpolator.interpolate(0.25),DELTA);
		assertEquals(20.0, intInterpolator.interpolate(1),DELTA);
		//
		// long
		Interpolator<Double> longInterpolator = Interpolators
				.interpolateNumber(10L, 20L);
		assertEquals(10.0, longInterpolator.interpolate(0),DELTA);
		assertEquals(12.5, longInterpolator.interpolate(0.25),DELTA);
		assertEquals(20.0, longInterpolator.interpolate(1),DELTA);

		// short
		Interpolator<Double> shortInterpolator = Interpolators
				.interpolateNumber((short) 10, (short) 20);
		assertEquals(10.0, shortInterpolator.interpolate(0),DELTA);
		assertEquals(12.5, shortInterpolator.interpolate(0.25),DELTA);
		assertEquals(20.0, shortInterpolator.interpolate(1),DELTA);

		// factory
		// LinearScale scale = d3.scale.linear().interpolate(
		// Interpolators.interpolateNumber);
		// scale.apply(7);
		//
		// Interpolator<Double> interpolator = Interpolators.interpolateNumber
		// .create(5, 6);
		// assertEquals(5.5, interpolator.interpolate(0.5).doubleValue(),
		// DELTA);
	}
}
