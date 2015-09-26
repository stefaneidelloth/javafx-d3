package com.github.javafxd3.api.dsv;

import com.github.javafxd3.api.wrapper.JavaScriptObject;

/**
 * When the CSV data is available, the specified callback will be invoked with
 * the parsed rows as the argument. If an error occurs, the callback function
 * will instead be invoked with null.
 * 
 * 
 * 
 *            the type of the parsed DSV line (if no accessor is provided, the
 *            type will be {@link DsvRow})
 * @param <T> 
 */
public interface DsvCallback<T> {
	
	/**
	 * When the CSV data is available, the specified callback will be invoked
	 * with the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null.
	 * 
	 * @param error the error, if any occurs.
	 * @param data 
	 * @param rows the rows values, or null if an error occurs.
	 */
	void get(JavaScriptObject error, T[] data);
}
