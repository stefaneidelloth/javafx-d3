package com.github.javafxd3.d3.interpolators;

import com.github.javafxd3.d3.color.Color;
import com.github.javafxd3.d3.color.HSLColor;
import com.github.javafxd3.d3.color.RGBColor;
import com.github.javafxd3.d3.core.Transform;
import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;


/**
 * Provide access to the d3.interpolat* methods.
 * <p> 
 */
public class Interpolators {
	
	//#region METHODS


	/**
	 * Returns a string interpolator between the two strings a and b.
	 * <p>
	 * The string interpolator finds numbers embedded in a and b, where each
	 * number is of the form:
	 * 
	 * <pre>
	 * {@code
	 * 	+]?\d+)?/g
	 * }
	 * </pre>
	 * <p>
	 * For each number embedded in b, the interpolator will attempt to find a
	 * corresponding number in a. If a corresponding number is found, a numeric
	 * interpolator is created using interpolateNumber. The remaining parts of
	 * the string b are used as a template: the static parts of the string b
	 * remain constant for the interpolation, with the interpolated numeric
	 * values embedded in the template.
	 * <p>
	 * For example, if a is "300 12px sans-serif", and b is
	 * "500 36px Comic-Sans", two embedded numbers are found. The remaining
	 * static parts of the string are a space between the two numbers (" "), and
	 * the suffix ("px Comic-Sans").
	 * 
	 * The result of the interpolator at t = .5 is "400 24px Comic-Sans".
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<String> interpolateString(final String a,
			final String b) {
		
	
		return new JavascriptFunctionInterpolatorDecorator<String>(interpolate0(a, b));
	}

	/**
	 * Returns an RGB color space interpolator between the two colors a and b.
	 * <p>
	 * The colors a and b need not be in RGB, but they will be converted to RGB
	 * using  D3#rgb(String).
	 * <p>
	 * The red, green and blue channels are interpolated linearly in a manner
	 * equivalent to interpolateRound, as fractional channel values are not
	 * allowed.
	 * <p>
	 * Also note that the interpolator modifies the returned color instance, and
	 * thus a copy must be made if you wish to avoid modification.
	 * 
	 * @param a
	 *            the start color
	 * @param b
	 *            the end color
	 * @return the interpolator
	 */
	public static final Interpolator<RGBColor> interpolateRgb(final String a,
			final String b) {
		
	 throw new IllegalStateException("not yet implemented");
	 /*
	
		return new JavascriptFunctionInterpolatorDecorator<RGBColor>(
				
				interpolateRgb0(Colors.rgb(a), Colors.rgb(b))
				
			) {
			@Override
			public RGBColor cast(final Value v) {
				return Colors.rgb(v.asString());
			}
		};
		
		*/
	}

	/**
	 * Returns an RGB color space interpolator between the two colors a and b.
	 * <p>
	 * The colors a and b need not be in RGB, but they will be converted to RGB
	 * using D3#rgb(String).
	 * <p>
	 * The red, green and blue channels are interpolated linearly in a manner
	 * equivalent to interpolateRound, as fractional channel values are not
	 * allowed.
	 * <p>
	 * Also note that the interpolator modifies the returned color instance, and
	 * thus a copy must be made if you wish to avoid modification.
	 * 
	 * @param a
	 *            the start color
	 * @param b
	 *            the end color
	 * @return the interpolator
	 */
	public static final Interpolator<RGBColor> interpolateRgb(final Color a,
			final Color b) {
		
		 throw new IllegalStateException("not yet implemented");
		 /*
	
		return new JavascriptFunctionInterpolatorDecorator<RGBColor>(
				interpolateRgb0(a, b)) {
			@Override
			public RGBColor cast(final Value v) {
				return Colors.rgb(v.asString());
			}
		};
		
		*/
	}

	/**
	 * Returns an HSL color space interpolator between the two colors a and b.
	 * <p>
	 * The colors a and b need not be in HSL, but they will be converted to HSL
	 * using  D3#hsl(String).
	 * <p>
	 * The hue, saturation and lightness are interpolated linearly in a manner
	 * equivalent to interpolateNumber. (The shortest path between the start and
	 * end hue is used.)
	 * <p>
	 * Also note that the interpolator modifies the returned color instance, and
	 * thus a copy must be made if you wish to avoid modification.
	 * 
	 * @param a
	 *            the start color
	 * @param b
	 *            the end color
	 * @return the interpolator
	 */
	public static final Interpolator<HSLColor> interpolateHsl(final String a,
			final String b) {
		
		 throw new IllegalStateException("not yet implemented");
		 /*
	
		return new JavascriptFunctionInterpolatorDecorator<HSLColor>(
				interpolateHsl0(Colors.hsl(a), Colors.hsl(b))) {
			@Override
			public HSLColor cast(final Value v) {
				return Colors.hsl(v.asString());
			}
		};
		
		*/
	}

	/**
	 * Returns an RGB color space interpolator between the two colors a and b.
	 * <p>
	 * The colors a and b need not be in RGB, but they will be converted to RGB
	 * using  D3#rgb(String).
	 * <p>
	 * The red, green and blue channels are interpolated linearly in a manner
	 * equivalent to interpolateRound, as fractional channel values are not
	 * allowed.
	 * <p>
	 * Also note that the interpolator modifies the returned color instance, and
	 * thus a copy must be made if you wish to avoid modification.
	 * 
	 * @param a
	 *            the start color
	 * @param b
	 *            the end color
	 * @return the interpolator
	 */
	public static final Interpolator<HSLColor> interpolateHsl(final Color a,
			final Color b) {
		
		 throw new IllegalStateException("not yet implemented");
		 /*
	
		return new JavascriptFunctionInterpolatorDecorator<HSLColor>(
				interpolateHsl0(a, b)) {
			@Override
			public HSLColor cast(final Value v) {
				return Colors.hsl(v.asString());
			}
		};
		
		*/
	}

	/**
	 * Returns a numeric interpolator between the two numbers a and b. The
	 * returned interpolator is equivalent to:
	 * 
	 * <pre>
	 * @code
	 * 	function interpolate(t) {
	 *   		return a * (1 - t) + b * t;
	 * 	}
	 * }
	 * </pre>
	 * <p>
	 * Caution: avoid interpolating to or from the number zero when the
	 * interpolator is used to generate a string (such as with attr). Very small
	 * values, when stringified, may be converted to scientific notation and
	 * cause a temporarily invalid attribute or style property value. For
	 * example, the number 0.0000001 is converted to the string "1e-7". This is
	 * particularly noticeable when interpolating opacity values. To avoid
	 * scientific notation, start or end the transition at 1e-6, which is the
	 * smallest value that is not stringified in exponential notation.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final double a,
			final double b) {
		return new JavascriptFunctionInterpolatorDecorator<Double>(
				interpolateNumber0(a, b)) {
			@Override
			public Double cast(final Value v) {
				return new Double(v.asDouble());
			}
		};
	}

	/**
	 * Returns an array interpolator between the two arrays a and b.
	 * <p>
	 * Internally, an array template is created that is the same length in b.
	 * 
	 * For each element in b, if there exists a corresponding element in a, a
	 * generic interpolator is created for the two elements using interpolate.
	 * 
	 * If there is no such element, the static value from b is used in the
	 * template.
	 * 
	 * Then, for the given parameter t, the template's embedded interpolators
	 * are evaluated. The updated array template is then returned.
	 * <p>
	 * For example, if a is the array [0, 1] and b is the array [1, 10, 100],
	 * 
	 * then the result of the interpolator for t = .5 is the array [.5, 5.5,
	 * 100].
	 * 
	 * <p>
	 * Note: no defensive copy of the template array is created; modifications
	 * of the returned array may adversely affect subsequent evaluation of the
	 * interpolator. No copy is made because interpolators should be fast, as
	 * they are part of the inner loop of animation.
	 * 
	 * @param a
	 *            the array a
	 * @param b
	 *            the array b
	 * @return the interpolator
	 */
	public static final Interpolator<Object[]> interpolateArray(
			final Object[] a, final Object[] b) {
		return new JavascriptFunctionInterpolatorDecorator<Object[]>(
				interpolateArray0(a, b)) {
			@Override
			public Object[] cast(final Value v) {
				return v.as();
			}
		};
	}

	/**
	 * Returns an object interpolator between the two objects a and b.
	 * <p>
	 * Internally, an object template is created that has the same properties as
	 * b.
	 * <p>
	 * For each property in b, if there exists a corresponding property in a, a
	 * generic interpolator is created for the two elements using interpolate.
	 * <p>
	 * If there is no such property, the static value from b is used in the
	 * template.
	 * <p>
	 * Then, for the given parameter t, the template's embedded interpolators
	 * are evaluated and the updated object template is then returned.
	 * <p>
	 * For example, if a is the object {x: 0, y: 1} and b is the object {x: 1,
	 * y: 10, z: 100}, the result of the interpolator for t = .5 is the object
	 * {x: .5, y: 5.5, z: 100}.
	 * <p>
	 * Object interpolation is particularly useful for dataspace interpolation,
	 * where data is interpolated rather than attribute values. For example, you
	 * can interpolate an object which describes an arc in a pie chart, and then
	 * use d3.svg.arc to compute the new SVG path data.
	 * <p>
	 * Note: no defensive copy of the template object is created; modifications
	 * of the returned object may adversely affect subsequent evaluation of the
	 * interpolator. No copy is made because interpolators should be fast, as
	 * they are part of the inner loop of animation.
	 * 
	 * @param a
	 *            the object a
	 * @param b
	 *            the object b
	 * @return the interpolator
	 */
	public static final <T extends JavaScriptObject> Interpolator<T> interpolateObject(
			final T a, final T b) {
		return new JavascriptFunctionInterpolatorDecorator<T>(
				interpolateObject0(a, b)) {
			@Override
			public T cast(final Value v) {
				return v.<T> as();
			}
		};
	}

	/**
	 * Returns an interpolator between the two 2D affine transforms represented
	 * by a and b. Each transform is decomposed to a standard representation of
	 * translate, rotate, x-skew and scale; these component transformations are
	 * then interpolated. This behavior is standardized by CSS: see matrix
	 * decomposition for animation.
	 * @param webEngine 
	 * 
	 * @param a
	 *            the object a
	 * @param b
	 *            the object b
	 * @return the interpolator
	 */
	public static final Interpolator<Transform> interpolateTransform(WebEngine webEngine,
			final Transform a, final Transform b) {
		
		Transform transform = new Transform(webEngine);
	
		return new JavascriptFunctionInterpolatorDecorator<Transform>(
				interpolateTransform0(a, b)) {
			@Override
			public Transform cast(final Value v) {
				return transform.parse(v.asString());
			}
		};
	}

	/**
	 * See {@link #interpolateNumber(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final int a,
			final int b) {
		return interpolateNumber((double) a, (double) b);
	}

	/**
	 * See #interpolate(double, double).
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final byte a,
			final byte b) {
		return interpolateNumber((double) a, (double) b);
	}

	/**
	 * See #interpolate(double, double).
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final float a,
			final float b) {
		return interpolateNumber((double) a, (double) b);
	}

	/**
	 * See #interpolate(double, double).
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final long a,
			final long b) {
		return interpolateNumber((double) a, (double) b);
	}

	/**
	 * See  #interpolate(double, double).
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double> interpolateNumber(final short a,
			final short b) {
		return interpolateNumber((double) a, (double) b);
	}

	/**
	 * Returns a numeric interpolator between the two numbers a and b; the
	 * interpolator is similar to  #interpolate(double, double), except
	 * it will round the resulting value to the nearest integer.
	 * <p>
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Long> interpolateRound(final double a,
			final double b) {
		return new JavascriptFunctionInterpolatorDecorator<Long>(
				interpolateRound0(a, b)) {
			@Override
			public Long cast(final Value v) {
				return new Long((long) v.asDouble());
			}
		};
	}

	/**
	 * See {@link #interpolateRound(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Byte> interpolateRound(final byte a,
			final byte b) {
		return new JavascriptFunctionInterpolatorDecorator<Byte>(
				interpolateRound0(a, b)) {
			@Override
			public Byte cast(final Value v) {
				return new Byte(v.asByte());
			}
		};
	}

	/**
	 * See {@link #interpolateRound(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Character> interpolateRound(final char a,
			final char b) {
		return new JavascriptFunctionInterpolatorDecorator<Character>(
				interpolateRound0(a, b)) {
			@Override
			public Character cast(final Value v) {
				return new Character(v.asChar());
			}
		};
	}

	/**
	 * See {@link #interpolateRound(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Integer> interpolateRound(final int a,
			final int b) {
		return new JavascriptFunctionInterpolatorDecorator<Integer>(
				interpolateRound0(a, b)) {
			@Override
			public Integer cast(final Value v) {
				return new Integer(v.asInt());
			}
		};
	}

	/**
	 * See {@link #interpolateRound(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Long> interpolateRound(final long a,
			final long b) {
		return new JavascriptFunctionInterpolatorDecorator<Long>(
				interpolateRound0(a, b)) {
			@Override
			public Long cast(final Value v) {
				// this will not work !!!
				// see
				//toolkit/doc/latest/DevGuideCodingBasicsJSNI#important
				// v.asLong()
				return new Long((long) v.asDouble());
			}
		};
	}

	/**
	 * See {@link #interpolateRound(double, double)}.
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Short> interpolateRound(final short a,
			final short b) {
		return new JavascriptFunctionInterpolatorDecorator<Short>(
				interpolateRound0(a, b)) {
			@Override
			public Short cast(final Value v) {
				return new Short(v.asShort());
			}
		};
	}

	/**
	 * Returns a smooth interpolator between the two views a and b of a
	 * two-dimensional plane, based on â€œSmooth and efficient zooming and
	 * panningâ€� by Jarke J. van Wijk and Wim A.A. Nuij.
	 * <p>
	 * Each view is defined as an array of three numbers: cx, cy and width. The
	 * first two coordinates cx, cy represent the center of the viewport; the
	 * last coordinate width represents the size of the viewport.
	 * <p>
	 * The returned interpolator also has a duration property which encodes the
	 * recommended transition duration in milliseconds. This duration is based
	 * on the path length of the curved trajectory through x,y space. If you
	 * want to a slower or faster transition, multiply this by an arbitrary
	 * scale factor (V as described in the original paper).
	 * <p>
	 * 
	 * 
	 * @param a
	 *            the start
	 * @param b
	 *            the end
	 * @return the interpolator
	 */
	public static final Interpolator<Double[]> interpolateZoom(
			final Double[] a, final Double[] b) {
		return new JavascriptFunctionInterpolatorDecorator<Double[]>(
				interpolateZoom0(a, b)) {
			@Override
			public Double[] cast(final Value v) {
				return v.as();
			}
		};
	}

	// public static final <T> Interpolator<T> interpolate(final T a, final T b)
	// {
	// return new JavascriptFunctionInterpolatorDecorator<T>(interpolate0(a, b))
	// {
	// @Override
	// public T interpolate(final double t) {
	// return delegate.interpolate(t).as();
	// }
	// };
	// }

	/**
	 * Actual JSNI implementation; the result is auto-casted to a
	 * {@link JavascriptFunctionInterpolator} and can be used by more specific
	 * versions of the
	 * 
	 * @param a
	 * @param b
	 * @return
	 */	
	private static  <T> JavascriptFunctionInterpolator interpolate0(
			T a, T b) {
		throw new IllegalStateException("not yet implemented");
		//var result = $wnd.d3.interpolate(a, b);
		//return result;
	}

	
	private static  JavascriptFunctionInterpolator interpolateNumber0(
			double a, double b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateNumber(a, b);
	}

	
	private static  JavascriptFunctionInterpolator interpolateRound0(
			double a, double b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateRound(a, b);
	}

	
	private static  JavascriptFunctionInterpolator interpolateZoom0(
			Double[] a, Double[] b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateZoom(a, b);
	}

	
	private static  JavascriptFunctionInterpolator interpolateRgb0(
			Color a, Color b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateRgb(a, b);
	}

	private static  JavascriptFunctionInterpolator interpolateHsl0(
			Color a, Color b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateHsl(a, b);
	}

	// private static  JavascriptFunctionInterpolator
	//{
	// return $wnd.d3.interpolateHcl(a, b);
	// }
	//
	// private static  JavascriptFunctionInterpolator
	//{
	// return $wnd.d3.interpolateLab(a, b);
	// }

	private static  JavascriptFunctionInterpolator interpolateObject0(
			JavaScriptObject a, JavaScriptObject b) {
		
	
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateObject(a, b);
	}

	private static  JavascriptFunctionInterpolator interpolateArray0(
			Object[] a, Object[] b) {
		
	
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateArray(a, b);
	}

	private static  JavascriptFunctionInterpolator interpolateTransform0(
			Transform a, Transform b) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateTransform(a, b);
	}

	// FIXME access to interpolators does not work as expected
	// see issue #42

	// private static  JavascriptFunctionInterpolator
	//{
	// return $wnd.d3.interpolateTransform(a, b);
	// }
	/**
	 * The interpolator factory used by
	 * {@link #interpolateNumber(double, double)}
	 */
	// public static final InterpolatorFactory<Double> interpolateNumber =
	// interpolateNumberFactory();

	/**
	 * The interpolator factory used by
	 * {@link #interpolateRound(double, double)}
	 */
	// public static final InterpolatorFactory<Long> interpolateRound =
	// interpolateRoundFactory();
	/**
	 * The interpolator factory used by
	 * {@link #interpolateString(String, String)}
	 */
	// public static final InterpolatorFactory<String> interpolateString =
	// interpolateStringFactory();
	/**
	 * The interpolator factory used by {@link #interpolateRgb(Color, Color)}
	 */
	// FIXME: providing access to the interpolator factory does not work as is
	// since it
	// public static final InterpolatorFactory<RGBColor> interpolateRgb =
	// interpolateRgbFactory();
	// public static final InterpolatorFactory<HCLColor> interpolateHcl =
	// interpolateHclFactory();
	/**
	 * The interpolator factory used by {@link #interpolateHsl(Color, Color)}
	 */
	// public static final InterpolatorFactory<HSLColor> interpolateHsl =
	// interpolateHslFactory();
	// public static final InterpolatorFactory<LabColor> interpolateLab =
	// interpolateLabFactory();
	/**
	 * The interpolator factory used by {@link #interpolateArray(Array, Array)}
	 */
	// public static final InterpolatorFactory<Array<?>> interpolateArray =
	// interpolateArrayFactory();
	/**
	 * The interpolator factory used by
	 * {@link #interpolateObject(JavaScriptObject, JavaScriptObject)}
	 */
	// public static final InterpolatorFactory<JavaScriptObject>
	// interpolateObject = interpolateObjectFactory();

	// public static final InterpolatorFactory<String> interpolateTransform =
	// interpolateTransformFactory();

	private static  JSNIInterpolatorFactory<Double> interpolateNumberFactory(){
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolateNumber;
	}

	//
	// private static  JSNIInterpolatorFactory<Long>
	//{
	// return $wnd.d3.interpolateRound;
	// }
	//
	// private static  JSNIInterpolatorFactory<String>
	//{
	// return $wnd.d3.interpolateString;
	// }
	//
	// private static  JSNIInterpolatorFactory<RGBColor>
	//{
	// return $wnd.d3.interpolateRgb;
	// }
	//
	// private static  JSNIInterpolatorFactory<HSLColor>
	//{
	// return $wnd.d3.interpolateHsl;
	// }

	// private static  JSNIInterpolatorFactory<HCLColor>
	//{
	// return $wnd.d3.interpolateHcl;
	// }
	//
	// private static  JSNIInterpolatorFactory<LabColor>
	//{
	// return $wnd.d3.interpolateLab;
	// }

	// private static  JSNIInterpolatorFactory<Array<?>>
	//{
	// return $wnd.d3.interpolateArray;
	// }
	//
	// private static  JSNIInterpolatorFactory<JavaScriptObject>
	//{
	// return $wnd.d3.interpolateObject;
	// }

	// private static  JSNIInterpolatorFactory<Transform>
	//{
	// return $wnd.d3.interpolateTransform;
	// }

	/**
	 * The array of built-in interpolator factories, as used by #interpolate().
	 * <p>
	 * Additional interpolator factories may be pushed onto the end of this
	 * array.
	 * <p>
	 * Each factory may return an interpolator, if it supports interpolating the
	 * two specified input values; otherwise, the factory should return a falsey
	 * value and other interpolators will be tried.
	 * 
	 * @return the array of interpolator factories
	 */
	public static  InterpolatorFactory<?>[] interpolators(){
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call($wnd.d3.interpolators;
	}
	
	//#end region
}
