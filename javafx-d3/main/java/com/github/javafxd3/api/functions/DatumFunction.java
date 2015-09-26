package com.github.javafxd3.api.functions;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.wrapper.Element;


/**
 * A function taking an element and the index of the element in the selection,
 * returning a value of type T.
 * <p>
 * This function must be passed to mutator functions of {@link Selection} when you knows that nodes are not bound to any data.
 * 
 * 
 * @param <T> 
 * 
 */
public interface DatumFunction<T> {

	/**
	 * Apply the function for the given {@link Element} at the specified index
	 * of the {@link Selection} mapped to the given  Datum.
	 * <p>
	 * Note that if no datum is mapped to the element,  Datum#isUndefined() will return true.
	 * 
	 * @param context
	 *            the current element, may be irrelevant or null in some context.
	 * @param d
	 *            the datum
	 * @param index
	 *            the index of the element in the selection
	 * @return a result to be applied
	 */
	public T apply(Element context, Value d, int index);
}
