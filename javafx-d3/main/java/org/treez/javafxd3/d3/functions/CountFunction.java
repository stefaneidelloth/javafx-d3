package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.core.Selection;

/**
 * A {@link DatumFunction} that counts something, mainly used as a debugging purpose
 * in {@link Selection#each(DatumFunction)} method.
 * <p>
 * You may override the  #takeIntoAccount(Element, Datum, int) to change when the count is incremented.
 * 
 * 
 * 
 */
public class CountFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private int count = 0;

	//#end region	
	
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 */
	public CountFunction(){}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public Void apply(final Object context, final Object d, final int index) {
		if (takeIntoAccount(context, d, index)) {
			count++;
		}
		return null;
	}

	/**
	 * Return true by default.
	 * 
	 * @param context
	 * @param d
	 * @param index
	 * @return true to increment the count, false otherwise.
	 */
	private boolean takeIntoAccount(final Object context, final Object d, final int index) {
		return true;
	}

	/**
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return
	 */
	public CountFunction reset() {
		count = 0;
		return this;
	}
	
	//#end region
}