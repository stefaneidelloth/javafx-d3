package org.treez.javafxd3.d3.interpolators;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.color.Color;
import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.color.HSLColor;
import org.treez.javafxd3.d3.color.RGBColor;
import org.treez.javafxd3.d3.core.Transform;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


/**
 * Provide access to the d3.interpolate* methods.
 * <p> 
 */
public class Interpolators {
	
	//#region METHODS
	
	private static D3 getD3(WebEngine webEngine){		
		return new D3(webEngine);			
	}

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
	public static final Interpolator<String> interpolateString(WebEngine webEngine,final String a,
			final String b) {	
		return new JavascriptFunctionInterpolatorDecorator<String>(interpolate0(webEngine, a, b));
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
	
	
	public static final Interpolator<RGBColor> interpolateRgb(WebEngine webEngine, final String a,
			final String b) {	
		
		Colors colors = new Colors(webEngine);	
		Color startColor = colors.rgb(a);
		Color endColor = colors.rgb(b);		
		
		JavascriptFunctionInterpolator interpolator = interpolateRgb0(webEngine, startColor, endColor);
		
		return new JavascriptFunctionInterpolatorDecorator<RGBColor>(interpolator) {
			
			@Override
			public RGBColor cast(final Value v) {
				String colorString = v.asString();
				return colors.rgb(colorString);
			}
		};		
		
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
	public static final Interpolator<RGBColor> interpolateRgb(WebEngine webEngine, final Color a,
			final Color b) {		
		Colors colors = new Colors(webEngine);	
		return new JavascriptFunctionInterpolatorDecorator<RGBColor>(interpolateRgb0(webEngine, a, b)) {
			@Override
			public RGBColor cast(final Value v) {
				return colors.rgb(v.asString());
			}
		};		
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
	public static final Interpolator<HSLColor> interpolateHsl(WebEngine webEngine, final String a,
			final String b) {		
		Colors colors = new Colors(webEngine);	
		return new JavascriptFunctionInterpolatorDecorator<HSLColor>(interpolateHsl0(webEngine, colors.hsl(a), colors.hsl(b))) {
			@Override
			public HSLColor cast(final Value v) {
				return colors.hsl(v.asString());
			}
		};	
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
	public static final Interpolator<HSLColor> interpolateHsl(WebEngine webEngine, final Color a,
			final Color b) {		
		Colors colors = new Colors(webEngine);		
		return new JavascriptFunctionInterpolatorDecorator<HSLColor>(interpolateHsl0(webEngine, a, b)) {
			@Override
			public HSLColor cast(final Value v) {
				return colors.hsl(v.asString());
			}
		};	
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine, final double a,
			final double b) {
		return new JavascriptFunctionInterpolatorDecorator<Double>(
				interpolateNumber0(webEngine, a, b)) {
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
	public static final Interpolator<Object[]> interpolateArray(WebEngine webEngine,
			final Object[] a, final Object[] b) {
		return new JavascriptFunctionInterpolatorDecorator<Object[]>(
				interpolateArray0(webEngine, a, b)) {
			@Override
			public Object[] cast(final Value v) {
				JSObject jsValue = v.as();				
				Array<Object> array = new Array<Object>(webEngine, jsValue);
				return array.asArray(Object.class);				
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
	public static final <T extends JavaScriptObject> Interpolator<T> interpolateObject(WebEngine webEngine,
			final T a, final T b) {
		return new JavascriptFunctionInterpolatorDecorator<T>(
				interpolateObject0(webEngine, a, b)) {
			@SuppressWarnings("unchecked")
			@Override
			public T cast(final Value v) {
				
				JSObject jsObject = v.as();
				Class<?> clazz = a.getClass();
				
				Object instance = createNewJavaScriptObjectInstance(webEngine, clazz, jsObject); 				
				return (T) instance;
			}

			private Object createNewJavaScriptObjectInstance(WebEngine webEngine,
					final Class<?> clazz, JSObject jsObject) {
				
				Constructor<?> constructor;
				try {
					constructor = clazz.getConstructor(new Class<?>[]{WebEngine.class, JSObject.class});
				} catch (NoSuchMethodException exception) {
					throw new IllegalStateException("Could not get constructor for JavaScriptObject", exception);
				} catch (SecurityException securityException) {
					throw new IllegalStateException("Could not get constructor for JavaScriptObject", securityException);
				}
				
				if(constructor==null){
					throw new IllegalStateException("Could not get constructor for JavaScriptObject");
				}
				
				Object instance;
				try {
					instance = constructor.newInstance(webEngine, jsObject);
				} catch (Exception exception) {
					throw new IllegalStateException("Could not create instance of JavaScriptObject", exception);
				}
				return instance;
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
				interpolateTransform0(webEngine, a, b)) {
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine, final int a,
			final int b) {
		return interpolateNumber(webEngine, (double) a, (double) b);
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine, final byte a,
			final byte b) {
		return interpolateNumber(webEngine, (double) a, (double) b);
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine,final float a,
			final float b) {
		return interpolateNumber(webEngine, (double) a, (double) b);
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine,final long a,
			final long b) {
		return interpolateNumber(webEngine, (double) a, (double) b);
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
	public static final Interpolator<Double> interpolateNumber(WebEngine webEngine,final short a,
			final short b) {
		return interpolateNumber(webEngine, (double) a, (double) b);
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
	public static final Interpolator<Long> interpolateRound(WebEngine webEngine, final double a,
			final double b) {
		return new JavascriptFunctionInterpolatorDecorator<Long>(
				interpolateRound0(webEngine, a, b)) {
			@Override
			public Long cast(final Value v) {
				return new Long((long) (double) v.asDouble());
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
	public static final Interpolator<Byte> interpolateRound(WebEngine webEngine,final byte a,
			final byte b) {
		return new JavascriptFunctionInterpolatorDecorator<Byte>(
				interpolateRound0(webEngine, a, b)) {
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
	public static final Interpolator<Character> interpolateRound(WebEngine webEngine,final char a,
			final char b) {
		return new JavascriptFunctionInterpolatorDecorator<Character>(
				interpolateRound0(webEngine, a, b)) {
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
	public static final Interpolator<Integer> interpolateRound(WebEngine webEngine,final int a,
			final int b) {
		return new JavascriptFunctionInterpolatorDecorator<Integer>(
				interpolateRound0(webEngine, a, b)) {
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
	public static final Interpolator<Long> interpolateRound(WebEngine webEngine,final long a,
			final long b) {
		return new JavascriptFunctionInterpolatorDecorator<Long>(
				interpolateRound0(webEngine, a, b)) {
			@Override
			public Long cast(final Value v) {
				// this will not work !!!
				// see
				//toolkit/doc/latest/DevGuideCodingBasicsJSNI#important
				// v.asLong()
				return new Long((long) (double) v.asDouble());
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
	public static final Interpolator<Short> interpolateRound(WebEngine webEngine,final short a,
			final short b) {
		return new JavascriptFunctionInterpolatorDecorator<Short>(
				interpolateRound0(webEngine,a, b)) {
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
	public static final Interpolator<Double[]> interpolateZoom(WebEngine webEngine,
			final Double[] a, final Double[] b) {
		return new JavascriptFunctionInterpolatorDecorator<Double[]>(
				interpolateZoom0(webEngine, a, b)) {
			@Override
			public Double[] cast(final Value v) {
				JSObject jsArray = v.as();
				Array<Double> doubleArray = new Array<Double>(webEngine, jsArray);
				return doubleArray.asArray(Double.class);
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
	private static  <T> JavascriptFunctionInterpolator interpolate0(WebEngine webEngine,
			T a, T b) {
		
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolate",  a, b);
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
	}

	
	
	private static  JavascriptFunctionInterpolator interpolateNumber0(WebEngine webEngine,
			double a, double b) {
		
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateNumber",  a, b);
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
		
	}

	
	private static  JavascriptFunctionInterpolator interpolateRound0(WebEngine webEngine,
			double a, double b) {
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateRound",  a, b);
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
		
	}

	
	private static  JavascriptFunctionInterpolator interpolateZoom0(WebEngine webEngine,
			Double[] a, Double[] b) {
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateZoom",  a, b);
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
	}

	
	private static  JavascriptFunctionInterpolator interpolateRgb0(WebEngine webEngine,
			Color a, Color b) {
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateRgb",  a.getJsObject(), b.getJsObject());
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
	}

	private static  JavascriptFunctionInterpolator interpolateHsl0(WebEngine webEngine,
			Color a, Color b) {
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateHsl",  a.getJsObject(), b.getJsObject());
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
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

	private static  JavascriptFunctionInterpolator interpolateObject0(WebEngine webEngine,
			JavaScriptObject a, JavaScriptObject b) {		
	
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateObject",  a.getJsObject(), b.getJsObject());
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
	}

	private static  JavascriptFunctionInterpolator interpolateArray0(WebEngine webEngine,
			Object[] a, Object[] b) {		
	
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateArray",  a, b);
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
	}

	private static  JavascriptFunctionInterpolator interpolateTransform0(WebEngine webEngine,
			Transform a, Transform b) {
		D3 d3 = getD3(webEngine);
		JSObject result = d3.call("interpolateTransform",  a.getJsObject(), b.getJsObject());
		if(result==null){
			return null;
		}
		return new JavascriptFunctionInterpolator(webEngine, result);
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

	/*
	private static  JSNIInterpolatorFactory<Double> interpolateNumberFactory(WebEngine webEngine){
				
		D3 d3 = getD3(webEngine);
		JSObject result = d3.getMember("interpolateNumber");
		if(result==null){
			return null;
		}
		return new JSNIInterpolatorFactory<Double>(webEngine, result);
	}
	*/

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
	public static  List<InterpolatorFactory<?>> interpolators(WebEngine webEngine){		
		D3 d3 = getD3(webEngine);
		JSObject result = d3.getMember("interpolators");
		if(result==null){
			return null;
		}
		
		List<InterpolatorFactory<?>> interpolatorList = new ArrayList<>();
		Array<JSObject> interpolators = new Array<JSObject>(webEngine, result);
		interpolators.forEach((element)->{interpolatorList.add(new JSNIInterpolatorFactory<>(webEngine, (JSObject)element));});		
		
		return interpolatorList;		
	}
	
	//#end region
}
