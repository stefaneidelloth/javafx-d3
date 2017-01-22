
package org.treez.javafxd3.d3.selection;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.Node;

import org.treez.javafxd3.d3.core.JsObject;

/**
 * Abstract parent class for all selection tests
 * 
 */
public abstract class AbstractSelectionTest extends AbstractTestCase {
	
	//#region CONSTRUCTORS
	
	public AbstractSelectionTest(){
		super();
	}
	
	//#end region
	
	//#region METHODS

	/**
	 * Clear the root node, add a node with the given factory and return the D3
	 * selection corresponding to the added node.
	 * 
	 * @param w
	 * @return the selection containing only the given widget
	 */
	protected Selection givenASimpleNodeFactory(D3NodeFactory nodeFactory) {
		Selection root = clearRoot();
		Selection newNode = nodeFactory.createInParentSelection(root);
		return newNode;
	}

	/**
	 * Clear the root node, add nodes using all the given factories and return a
	 * selection containing all the created nodes.
	 * 
	 * @param widgets
	 * @return
	 */
	protected Selection givenMultipleNodeFactories(final D3NodeFactory... nodeFactories) {
		Selection root = clearRoot();
		for (D3NodeFactory nodeFactory : nodeFactories) {
			nodeFactory.createInParentSelection(root);
		}
		return root.selectAll("*");
	}

	/**
	 * Returns the selection child element with the given index as Element.
	 * (Apply get(0) on the result to select the first element of the
	 * selection.)
	 * 
	 * @param index
	 * @return
	 */
	public Element getElementFromRoot(final int index) {
		Array<Node> children = getRoot().node().children();
				
		if(children==null){
			return null;
		}
		
		Element child = children.get(index, Element.class);
		return child;
		
	}
	
	public Selection getElementAsSelection(final int index) {
		Selection children = getSvg().selectAll("*").get(0);
		if(children==null){
			return null;
		}
		
		Selection child = children.get(index);
		return child;
		
	}

	public String getElementAttribute(final int index, final String attribute) {
		Element element = getElementFromRoot(index);

		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String attributeValue = element.getAttribute(attribute);
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
		JsObject attributes = element.getMember("attributes");
		int length = (int) attributes.getMember("length");
		for (int attributeIndex = 0; attributeIndex < length; attributeIndex++) {
			JsObject attributeObj = (JsObject) attributes.getMember("" + attributeIndex);
			String attributeName = attributeObj.getMember("name").toString();
			Object attributeValue = attributeObj.getMember("value");

			String valueString = attributeValue.toString();
			boolean isJsObject = attributeValue instanceof JsObject;
			if (isJsObject) {
				JsObject attributeJsValue = (JsObject) attributeValue;
				valueString = attributeJsValue.call("toString").toString();
			}

			attributeMap.put(attributeName, valueString);
		}
		return attributeMap;
	}


	public String getElementInnerText(final int index) {
		Element element = getElementFromRoot(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = element.getText();
		return result;

	}


	public String getElementInnerHtml(final int index) {
		Element element = getElementFromRoot(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = element.getInnerHtml();
		return result;
	}


	public String getElementClassAttribute(final int index) {
		Element element = getElementFromRoot(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = element.getAttribute("class");
		return result;
	}

	public String getElementStyle(final int index, final String styleProperty) {
		Element element = getElementFromRoot(index);
		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

		String result = element.getStyle(styleProperty);		
		
		return result;
	}

	protected Value getElementProperty(final int index, final String property) {
		Element element =  getElementFromRoot(index);
		
		if(element==null){
			return null;
		}
		Object valueObj =  element.getProperty(property);		
		return Value.create(engine,  valueObj);
	}
	
	//#end region

}
