package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.PathDataGenerator;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class SelectionAttrTest extends AbstractSelectionTest {

	private static final String ATTRIBUTE = "myattr";

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantBoolean();
		testSetterConstantDouble();
		testSetterConstantString();
		testSetterPathDataGenerator();
		testSetterFunction();

	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		final String value = "1";
		
		selection.attr(ATTRIBUTE, new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Value datum, final int index) {
				return value;
			}
		});
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection2.attr(ATTRIBUTE, new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Value datum, final int index) {
				return value;
			}
		});
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		String value = "1";
		selection.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, SelectionAttrTest.ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals(value, getElementAttribute(0, ATTRIBUTE));
		assertEquals(value, getElementAttribute(1, ATTRIBUTE));
		assertEquals(value, getElementAttribute(2, ATTRIBUTE));

	}

	protected void testSetterConstantDouble() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		double value = 3.56;
		selection.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals("3.56", getElementAttribute(0, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(1, ATTRIBUTE));
		assertEquals("3.56", getElementAttribute(2, ATTRIBUTE));
	}

	protected void testSetterConstantBoolean() {
		boolean value = true;
		String expectedValue = "true";
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		selection.attr(ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, value);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(1, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(2, ATTRIBUTE));
	}

	protected void testSetterPathDataGenerator() {
		PathDataGenerator generator = d3.svg().arc().innerRadius(1).outerRadius(2).startAngle(0).endAngle(2);
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		selection.attr(SelectionAttrTest.ATTRIBUTE, generator);
		String expectedValue = generator.generate(new Object[]{});
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection2.attr(SelectionAttrTest.ATTRIBUTE, generator);
		assertEquals(expectedValue, getElementAttribute(0, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(1, ATTRIBUTE));
		assertEquals(expectedValue, getElementAttribute(2, ATTRIBUTE));
	}

	protected void testGetter() {
		// with single selection
		Label label = new Label();
		label.setAttribute(ATTRIBUTE, "foo");
		Selection selection = givenASimpleSelection(label);
		assertEquals("foo", selection.attr(ATTRIBUTE));

		// with multiple selection, should return the first element
		Selection selection2 = givenAMultipleSelection(createLabelFactory(ATTRIBUTE, "1"), createLabelFactory(ATTRIBUTE, "2"),
				createLabelFactory(ATTRIBUTE, "3"));
		assertEquals("1", selection2.attr(ATTRIBUTE));

	}

	private D3NodeFactory createLabelFactory(final String attr, final String value) {
		Label labelFactory = new Label();
		labelFactory.setAttribute(attr, value);
		return labelFactory;
	}
}