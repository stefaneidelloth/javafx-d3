package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.core.Selection;


/**
 * A function taking an element and the index of the element in the selection,
 * returning a value of type T.
 * <p>
 * This function must be passed to mutator functions of {@link Selection} when you 
 * know that nodes are not bound to any data.
 * 
 * 
 * @param <T> 
 * @param <D>
 * 
 */
public interface DataFunction<R> {

	/**
	 * Apply the function for the given context at the specified index
	 * of the {@link Selection} mapped to the given datum.
	 * <p>
	 * Note that if no datum is mapped to the element,  Datum#isUndefined() will return true.
	 * 
	 * @param context
	 *            the current element, may be irrelevant or null in some context.
	 * @param datum
	 *            the datum
	 * @param index
	 *            the index of the context element in the selection
	 * @return a result to be applied
	 */
	public R apply(Object context, Object datum, int index);
}
