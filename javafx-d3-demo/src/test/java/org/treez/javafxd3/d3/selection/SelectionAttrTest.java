package org.treez.javafxd3.d3.selection;

import java.util.ArrayList;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.ConstantDataFunction;
import org.treez.javafxd3.d3.svg.PathDataGenerator;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

@SuppressWarnings("javadoc")
public class SelectionAttrTest extends AbstractSelectionTest {

	private static final String ATTRIBUTE = "data-myattr";

	@Override	
	public void doTest() {
		testGetter();
		testSetterConstantBoolean();
		testSetterConstantDouble();
		testSetterConstantString();
		testSetterPathDataGenerator();
		testSetterFunction();

	}
	
	protected void testGetter() {
		// with single selection
		InputElementFactory label = new InputElementFactory();
		label.setAttribute(ATTRIBUTE, "foo");
		Selection selection = givenASimpleNodeFactory(label);
		assertEquals("foo", selection.attr(ATTRIBUTE));

		// with multiple selection, should return the first element
		Selection selection2 = givenMultipleNodeFactories(createLabelFactory(ATTRIBUTE, "1"), createLabelFactory(ATTRIBUTE, "2"),
				createLabelFactory(ATTRIBUTE, "3"));
		assertEquals("1", selection2.attr(ATTRIBUTE));

	}
	
	protected void testSetterConstantBoolean() {
		boolean value = true;
		String expectedValue = "true";
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		selection.attr(ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.attr(ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(1, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(2, ATTRIBUTE));
	}

	

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		String value = "1";
		selection.attr(ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, SelectionAttrTest.ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.attr(ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	protected void testSetterConstantDouble() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		double value = 3.56;
		selection.attr(ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(1, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(2, ATTRIBUTE));
	}

	

	protected void testSetterPathDataGenerator() {
		PathDataGenerator generator = d3.svg().arc().innerRadius(1).outerRadius(2).startAngle(0).endAngle(2);
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		selection.attr(SelectionAttrTest.ATTRIBUTE, generator);
		String generationResult  = generator.generate(new ArrayList<JavaScriptObject>());		
		String firstAttributeValue = getElementAttribute(0, ATTRIBUTE);
		assertEquals(generationResult, firstAttributeValue);

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, generator);
		assertEquals(generationResult, getElementAttribute(0, ATTRIBUTE));
		assertEquals(generationResult, getElementAttribute(1, ATTRIBUTE));
		assertEquals(generationResult, getElementAttribute(2, ATTRIBUTE));
	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		
		final String value = "1";
		DataFunction<String> datumFunction = new ConstantDataFunction<String>(value);
			
		selection.attr(ATTRIBUTE, datumFunction);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.attr(ATTRIBUTE, datumFunction);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	private D3NodeFactory createLabelFactory(final String attr, final String value) {
		InputElementFactory labelFactory = new InputElementFactory();
		labelFactory.setAttribute(attr, value);
		return labelFactory;
	}
}
