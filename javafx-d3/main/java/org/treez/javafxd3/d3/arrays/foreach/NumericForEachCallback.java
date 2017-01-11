package org.treez.javafxd3.d3.arrays.foreach;

/**
 * Same as {@link ForEachCallback} but ensure the result is treated as a numeric
 * double.
 */
public interface NumericForEachCallback extends ForEachCallback<Double> {

	/**
	 * Executed for each element of the array with an assigned value.
	 * <p>
	 * If a thisArg parameter is provided to
	 * Array#forEach(NumericForEachCallback, Object), it will be used as the
	 * thisArg parameter for each callback invocation.
	 * <p>
	 * If thisArg is undefined or null, the this value within the function
	 * depends on whether the function is in strict mode or not (passed value if
	 * in strict mode, global object if in non-strict mode).
	 * 
	 * https:US/docs/JavaScript/Reference/Global_Objects/Array/forEach
	 * 
	 * @param thisArg
	 * @param element
	 * @param index
	 * @param array
	 * @return
	 */
	Double forEach(Object thisArg, Object element, int index, Object array);
}
