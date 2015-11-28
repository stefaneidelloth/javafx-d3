package com.github.javafxd3.d3.functions;

/**
 * A function to be used with D3 timer().
 * <p> 
 */
public interface TimerFunction {

	/**
	 * Return true to stop the timer, false to continue.
	 * 
	 * @return true to stop the timer, false to continue.
	 */
	boolean execute();
}
