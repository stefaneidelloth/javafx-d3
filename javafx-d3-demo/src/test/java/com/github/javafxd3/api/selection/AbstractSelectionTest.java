
package com.github.javafxd3.api.selection;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.wrapper.D3NodeFactory;

import netscape.javascript.JSObject;

/**
 * Abstract parent class for all selection tests
 * 
 */
public abstract class AbstractSelectionTest extends AbstractTestCase {

	/**
	 * Clear the root node, add a node with the given factory and return the D3
	 * selection corresponding to the added node.
	 * 
	 * @param w
	 * @return the selection containing only the given widget
	 */
	protected Selection givenASimpleSelection(D3NodeFactory nodeFactory) {
		Selection root = clearRoot();				
		Selection newNode = nodeFactory.create(root);		
		return newNode;
	}

	/**
	 * Clear the root node, add nodes using all the given factories and return a selection containing
	 * all the created nodes.
	 * 
	 * @param widgets
	 * @return
	 */
	protected Selection givenAMultipleSelection(final D3NodeFactory... nodeFactories) {
		Selection root = clearRoot();	
		for (D3NodeFactory nodeFactory : nodeFactories) {
			nodeFactory.create(root);
		}
		return root.selectAll("*");
	}
	
		
	/**
	 * Returns the selection child element with the given index as Selection
	 * @param index
	 * @return
	 */
	public Selection getElement(final int index) {
		return getRoot().get(index);
	}

	/**
	 * @param index
	 * @param attribute
	 * @return
	 */
	public String getElementAttribute(final int index, final String attribute) {
		Object member = getElement(index).getMember(attribute);
		String result = (String) member;
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementInnerText(final int index) {
		Object member = getElement(index).getMember("text");
		String result = (String) member;
		return result;
		
	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementInnerHtml(final int index) {
		Object member = getElement(index).getMember("html");
		String result = (String) member;
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementClassAttribute(final int index) {
		Object member = getElement(index).getMember("class");
		String result = (String) member;
		return result;
	}

	/**
	 * @param index
	 * @param style
	 * @return
	 */
	public String getElementStyle(final int index, final String style) {
		Object member = getElement(index).getMember("style");
		String result = (String) member;
		return result;
	}

	protected Value getElementProperty(final int index, final String property) {
		JSObject propertyObj = (JSObject) getElement(index).getMember(property);
		return new Value(webEngine, propertyObj);
	}

}
