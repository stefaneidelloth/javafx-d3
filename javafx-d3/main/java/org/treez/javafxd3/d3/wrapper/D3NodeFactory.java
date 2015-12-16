package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.core.Selection;

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
