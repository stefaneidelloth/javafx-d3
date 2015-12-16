package org.treez.javafxd3.d3.democases;

/**
 *
 *
 */
public class Margin {
	
	//#region ATTRIBUTES
	
	/**
	 * 
	 */
	public final int top;
	
	/**
	 * 
	 */
	public final int right;
	
	/**
	 * 
	 */
	public final int bottom;
	
	/**
	 * 
	 */
	public final int left;
	
	//#end region
	
	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public Margin(final int top, final int right, final int bottom,
			final int left) {
		super();
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}
	
	//#end region
}