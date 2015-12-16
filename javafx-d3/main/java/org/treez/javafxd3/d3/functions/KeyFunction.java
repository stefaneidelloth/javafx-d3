package org.treez.javafxd3.d3.functions;


import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;


/**
 * A function used to control how data is joined to elements.
 * <p>
 * Single argument {@link Selection#data} methods use a default by-index behavior to map data with elements. This interface can be passed to
 *  Selection#data(com.google.gwt.core.client.JavaScriptObject, KeyFunction) and variants to map data to elements using a different criteria.
 * <p>
 * The  #apply(Element, Datum, int) method will be called once for each element in the new data array, and once again for each existing element in the selection. The result
 * will be used to map the passed data to the passed element.
 * <p>
 *  
 * @param <T> 
 * 
 */
public interface KeyFunction<T> {

    /**
     * Invoked once for each datum in the new data array (A),
     * then once for each element in the current selection (B).
     * <p>
	 * The returned value is used as a key to map the new data array elements to existing (or future) elements of the selection:
     * <ul>
	 * <li>when the value returned by A matches a return value of B, the new datum is associated to the corresponding element
	 * <li>when the value returned by A does not match any return value of B, the new datum is associated to a placeholder for a new element ( the entering selection) and can be
	 * retrieved via {@link UpdateSelection#enter()}
	 * <li>when the value returned by B does not match any return value of A, the element is put in the exiting selection and can be retrieved using {@link UpdateSelection#exit()}.
     * </ul>
     * <p>
     * 
	 * 
	 * @param context
	 *            the current element when this method is invoked for the existing elements of the selection,
	 *            or null when this method is invoked for the new data array
	 * @param newDataArray
	 *            the new data array when this method is invoked for the elements in the new data array,
	 *            or null when this method is invoked for the existing elements of the selection.
	 * @param datum
	 *            the datum element of the new data array, or the datum of the current element context
	 * @param index
	 *            the index of the datum in the new data array, or the index of the element in the selection
	 * 
     * @return a key to be used to map new datum to existing elements.
     */
	public T call(Object context, Object newDataArray, Object datum, int index);
}
