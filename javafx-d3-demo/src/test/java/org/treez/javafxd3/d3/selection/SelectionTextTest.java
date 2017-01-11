package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.selection.datafunction.PrefixPlusIndexDataFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

@SuppressWarnings("javadoc")
public class SelectionTextTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();

	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		final String value = "foo bar";
		selection.text(new PrefixPlusIndexDataFunction(value));
		assertEquals(value + "0", getElementInnerText(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection.text(new PrefixPlusIndexDataFunction(value));
		assertEquals(value + "0", getElementInnerText(0));
		assertEquals(value + "1", getElementInnerText(1));
		assertEquals(value + "2", getElementInnerText(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		String value = "foo bar";
		selection.text(value);
		assertEquals(value, getElementInnerText(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection.text(value);
		assertEquals(value, getElementInnerText(0));
		assertEquals(value, getElementInnerText(1));
		assertEquals(value, getElementInnerText(2));

	}

	protected void testGetter() {
		// with single selection
		InputElementFactory inputElementFactory = new InputElementFactory();
		String text = "foo";
		inputElementFactory.setText(text);
		Selection selection = givenASimpleNodeFactory(inputElementFactory);
		assertEquals(text, selection.text());

		// with multiple selection, should return the first element
		selection = givenMultipleNodeFactories(createInputElement(text), createInputElement(text), createInputElement(text));
		assertEquals(text, selection.text());
	}

	private D3NodeFactory createInputElement(final String textValue) {
		InputElementFactory l = new InputElementFactory();
		l.setText(textValue);
		return l;
	}
}
