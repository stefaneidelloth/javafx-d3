
package org.treez.javafxd3.d3.selection;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.Inspector;

import org.treez.javafxd3.d3.AbstractTestCase;

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
		Selection svg = clearSvg();
		Selection newNode = nodeFactory.createInParentSelection(svg);
		return newNode;
	}

	/**
	 * Clear the root node, add nodes using all the given factories and return a
	 * selection containing all the created nodes.
	 * 
	 * @param widgets
	 * @return
	 */
	protected Selection givenAMultipleSelection(final D3NodeFactory... nodeFactories) {
		Selection svg = clearSvg();
		for (D3NodeFactory nodeFactory : nodeFactories) {
			nodeFactory.createInParentSelection(svg);
		}
		return svg.selectAll("*");
	}

	/**
	 * Returns the selection child element with the given index as Selection.
	 * (Apply get(0) on the result to select the first element of the
	 * selection.)
	 * 
	 * @param index
	 * @return
	 */
	public Selection getElement(final int index) {
		Selection children = getSvg().selectAll("*").get(0);
		//Inspector.inspect(children);
		Selection child = children.get(index);

		return child;
	}

	/**
	 * @param index
	 * @param attribute
	 * @return
	 */
	public String getElementAttribute(final int index, final String attribute) {
		Selection element = getElement(index);

		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		//Inspector.inspect(element);

		String attributeValue = getAttributeFromElement(element, attribute);
		return attributeValue;

	}

	protected String getAttributeFromElement(Selection element, final String attribute) {
		Map<String, String> attributeMap = getDomAttributes(element);
		if (attributeMap.containsKey(attribute)) {
			String result = attributeMap.get(attribute);
			return result;
		}
		return null;
	}

	private Map<String, String> getDomAttributes(Selection element) {
		Map<String, String> attributeMap = new HashMap<>();
		JSObject attributes = element.getMember("attributes");
		int length = (int) attributes.getMember("length");
		for (int attributeIndex = 0; attributeIndex < length; attributeIndex++) {
			JSObject attributeObj = (JSObject) attributes.getMember("" + attributeIndex);
			String attributeName = attributeObj.getMember("name").toString();
			Object attributeValue = attributeObj.getMember("value");

			String valueString = attributeValue.toString();
			boolean isJsObject = attributeValue instanceof JSObject;
			if (isJsObject) {
				JSObject attributeJsValue = (JSObject) attributeValue;
				valueString = attributeJsValue.call("toString").toString();
			}

			attributeMap.put(attributeName, valueString);
		}
		return attributeMap;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementInnerText(final int index) {
		Selection element = getElement(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = getAttributeFromElement(element, "text");
		return result;

	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementInnerHtml(final int index) {
		Selection element = getElement(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = getAttributeFromElement(element, "innerHtml");
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getElementClassAttribute(final int index) {
		Selection element = getElement(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = getAttributeFromElement(element, "class");
		return result;
	}

	/**
	 * @param index
	 * @param style
	 * @return
	 */
	public String getElementStyle(final int index, final String style) {
		Selection element = getElement(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = getAttributeFromElement(element, "style");
		return result;
	}

	protected Value getElementProperty(final int index, final String property) {
		JSObject propertyObj = (JSObject) getElement(index).getMember(property);
		return new Value(webEngine, propertyObj);
	}

}
