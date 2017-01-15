package org.treez.javafxd3.d3.democases.svg.text;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.demo.SvgTextElementFactory;

import javafx.scene.layout.VBox;
import netscape.javascript.JSObject;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 */
public class TextDemo extends AbstractDemoCase {

	//#region CONSTRUCTORS

	public TextDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);

	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new TextDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		Selection svg = getSvg();

		SvgTextElementFactory labelFactory = new SvgTextElementFactory("Svg text label");

		Selection text = labelFactory.createInParentSelection(svg);

		Selection movedText = text //
				.attr("y", "100") //
				.attr("x", "50");

		final String ATTRIBUTE = "data-myAttr";
		Boolean value = true;
		movedText.attr(ATTRIBUTE, value);

		Object result = getElementAttribute(0, ATTRIBUTE);
		assertEquals(result, "true");

	}

	@Override
	public void stop() {

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
		return getSvg() //
				.selectAll("*") //
				.get(index);
	}

	public String getElementAttribute(final int index, final String attribute) {
		Selection element = getElement(index).get(0);

		if (element == null) {
			String message = "Could not retrieve element with index " + index;
			throw new IllegalArgumentException(message);
		}

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
			String attributeValue = attributeObj.getMember("value").toString();
			attributeMap.put(attributeName, attributeValue);
		}
		return attributeMap;
	}

	//#end region

}
