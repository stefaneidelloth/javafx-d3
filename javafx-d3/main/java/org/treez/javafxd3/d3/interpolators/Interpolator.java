package org.treez.javafxd3.d3.interpolators;



import netscape.javascript.JSObject;

/**
 * An interpolator is a function that maps a parametric value t in the domain [0,1] to a color, number or arbitrary value.
 * <p>
 * D3 has many built-in interpolators to simplify the transitioning of arbitrary values; see the  D3#interpolate*() methods.
 * <p>
 * You should not directly implements this interface, but rather extend the class {@link CallableInterpolator}, if you need to pass your own
 * interpolator to D3.
 * <p>
 * 
 * 
 * @param <T> 
 * 
 */
public interface Interpolator<T> {

	/**
	 * Return the domain value corresponding to the parametric value t.
	 * 
	 * @param t
	 *            the parameter
	 * @return the corresponding value
	 */
	public T interpolate(double t);

	/**
	 * Return a one-arg JS function wrapping the interpolation.
	 * <p>
	 * Consider using {@link CallableInterpolator} for your own interpolation.
	 * <p>
	 * 
	 * @return the JS function
	 */
	public JSObject asJSOFunction();
}
