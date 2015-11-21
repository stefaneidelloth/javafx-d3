package com.github.javafxd3.api.wrapper;

import com.github.javafxd3.api.core.Selection;

/**
 * Factory for d3 nodes
 */
public interface D3NodeFactory {
	
	/**
	 * Creates a d3 node, using the given selection as a parent
	 * @param selection
	 * @return 
	 */
	Selection createInParentSelection(Selection selection);
	
	/**
	 * Removes a d3 node, using the given selection as a parent
	 * @param selection
	 * @return
	 */
	Selection remove(Selection selection);

}
