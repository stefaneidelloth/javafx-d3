package org.treez.javafxd3.d3.ease;

/**
 * An easing function takes the current parameterized time value t in the domain [0,1],
 * and maps it to another value in a similar range; it is typically used to set  Transition#ease().
 * <p>
 * Built-in {@link EasingFunction}s may be created using {@link Easing} factory.
 * <p>
 * 
 *
 */
public interface EasingFunction {
	/**
	 * Given a parametric time t in the range [0,1], returns the eased time.
	 * <p>
	 * The returned value is typically in the range [0,1] as well, but may
	 * extend slightly beyond this range for certain easing functions, such as
	 * "elastic".
	 * <p>
	 * 
	 * @param t
	 *            the parametric time
	 * @return the eased time
	 */
	double ease(double t);
}
