package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;
import org.treez.javafxd3.d3.functions.ConstantDatumFunction;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.svg.PathDataGenerator;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

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
		LabelFactory label = new LabelFactory();
		label.setAttribute(ATTRIBUTE, "foo");
		Selection selection = givenASimpleSelection(label);
		assertEquals("foo", selection.attr(ATTRIBUTE));

		// with multiple selection, should return the first element
		Selection selection2 = givenAMultipleSelection(createLabelFactory(ATTRIBUTE, "1"), createLabelFactory(ATTRIBUTE, "2"),
				createLabelFactory(ATTRIBUTE, "3"));
		assertEquals("1", selection2.attr(ATTRIBUTE));

	}
	
	protected void testSetterConstantBoolean() {
		boolean value = true;
		String expectedValue = "true";
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		selection.attr(ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.attr(ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(1, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(2, ATTRIBUTE));
	}

	

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		String value = "1";
		selection.attr(ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, SelectionAttrTest.ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.attr(ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	protected void testSetterConstantDouble() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		double value = 3.56;
		selection.attr(ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(1, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(2, ATTRIBUTE));
	}

	

	protected void testSetterPathDataGenerator() {
		PathDataGenerator generator = d3.svg().arc().innerRadius(1).outerRadius(2).startAngle(0).endAngle(2);
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		selection.attr(SelectionAttrTest.ATTRIBUTE, generator);
		//String generationResult  = generator.generate(new ArrayList<JavaScriptObject>());
		String expectedValue = generator.toString();
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, generator);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(1, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(2, ATTRIBUTE));
	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		
		final String value = "1";
		DatumFunction<String> datumFunction = new ConstantDatumFunction<String>(value);
			
		selection.attr(ATTRIBUTE, datumFunction);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.attr(ATTRIBUTE, datumFunction);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	private D3NodeFactory createLabelFactory(final String attr, final String value) {
		LabelFactory labelFactory = new LabelFactory();
		labelFactory.setAttribute(attr, value);
		return labelFactory;
	}
}
