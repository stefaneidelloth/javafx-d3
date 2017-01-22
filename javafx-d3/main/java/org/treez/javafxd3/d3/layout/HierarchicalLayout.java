package org.treez.javafxd3.d3.layout;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.svg.Diagonal;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 *
 *
 */
public class HierarchicalLayout extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public HierarchicalLayout(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Runs the tree layout, returning the array of nodes associated with the
	 * specified root node. The tree layout is part of D3's family of
	 * hierarchical layouts. These layouts follow the same basic structure: the
	 * input argument to layout is the root node of the hierarchy, and the
	 * output return value is an array representing the computed positions of
	 * all nodes. Several attributes are populated on each node:
	 * 
	 * <ul>
	 * <li>parent - the parent {@link Node}, or null for the root.
	 * <li>children - the array of child nodes, or null for leaf nodes.
	 * <li>depth - the depth of the node, starting at 0 for the root.
	 * <li>x - the computed x-coordinate of the node position.
	 * <li>y - the computed y-coordinate of the node position.
	 * </ul>
	 * 
	 * Although the layout has a size in x and y, this represents an arbitrary
	 * coordinate system; for example, you can treat x as a radius and y as an
	 * angle to produce a radial rather than Cartesian layout.
	 * 
	 * @param r
	 *            root of the the tre
	 * 
	 * @return array of {@link Node} in the tree stemming from root
	 */
	public Array<Node> nodes(Node root) {
		JsObject rootObj = root.getJsObject();
		JsObject result = call("nodes", rootObj);
		return new Array<Node>(engine, result);
	}

	/**
	 * Given the specified array of nodes, such as those returned by nodes,
	 * returns an array of objects representing the from parent to child for
	 * each node. Leaf nodes will not have any links. Each link is an object
	 * with two attributes:
	 * 
	 * <ul>
	 * <li>source - the parent node (as described above).
	 * <li>target - the child node.
	 * </ul>
	 * 
	 * This method is useful for retrieving a set of link descriptions suitable
	 * for display, often in conjunction with the {@link Diagonal} shape
	 * generator.
	 * 
	 * @param n
	 * 
	 * @return array of {@link Link} connecting nodes
	 */
	public Array<Link> links(Array<Node> nodes) {
		JsObject nodesObj = nodes.getJsObject();
		JsObject result = call("links", nodesObj);
		return new Array<Link>(engine, result);		
	}

	/**
	 * A node in d3j's tree layout, see <a href="https:nodes">d3 docs on
	 * node</a>. The node class is used in {@link Tree}, cluster, and
	 * partitions. The class provides accessors for the nodes key attributes,
	 * its position, children, parent, and depth.  
	 */
	public static class Node extends org.treez.javafxd3.d3.layout.Node {

		//#region CONSTRUCTORS

	
		public Node(JsEngine engine, JsObject wrappedJsObject) {
			super(engine, wrappedJsObject);
		}

		//#end region

		//#region METHODS

		/**
		 * @return array of {@link Node} objects or null
		 */
		public Array<Node> children() {
			JsObject result = getMember("children");
			return new Array<Node>(engine, result);			
		}

		/**
		 * @return parent node
		 */
		public Node parent() {
			JsObject result = getMember("parent");
			return new Node(engine, result);
		}

		/**
		 * @return the node's depth, root depth = 0
		 */
		public double depth() {
			Double result = getMemberForDouble("depth");
			return result;
		}

		//#end region
	}
}
