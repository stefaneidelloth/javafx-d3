package org.treez.javafxd3.d3.dsv;

/**
 * When the DSV data is available, the specified callback will be invoked with
 * the parsed row array as the argument. If an error occurs, the callback function
 * will instead be invoked with null.
 * 
 * 
 * 
 *            the type of the parsed DSV line (if no accessor is provided, the
 *            type will be {@link DsvRow})
 * @param <A> 
 */
public interface DsvCallback<A> {
	
	/**
	 * When the CSV data is available, the specified callback will be invoked
	 * with the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null.
	 * 
	 * @param error the error, if any occurs.
	 * @param data 
	 * @param rows the rows values, or null if an error occurs.
	 */
	void get(Object error, Object rowsArray);
}
