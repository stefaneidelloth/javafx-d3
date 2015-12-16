package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

import org.treez.javafxd3.d3.selection.datumfunction.PrefixPlusIndexDatumFunction;

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
		Selection selection = givenASimpleSelection(new LabelFactory());
		final String value = "foo bar";
		selection.text(new PrefixPlusIndexDatumFunction(webEngine, value));
		assertEquals(value + "0", getElementInnerText(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection.text(new PrefixPlusIndexDatumFunction(webEngine, value));
		assertEquals(value + "0", getElementInnerText(0));
		assertEquals(value + "1", getElementInnerText(1));
		assertEquals(value + "2", getElementInnerText(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		String value = "foo bar";
		selection.text(value);
		assertEquals(value, getElementInnerText(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection.text(value);
		assertEquals(value, getElementInnerText(0));
		assertEquals(value, getElementInnerText(1));
		assertEquals(value, getElementInnerText(2));

	}

	protected void testGetter() {
		// with single selection
		LabelFactory label = new LabelFactory();
		String value = "foo";
		label.setInnerText(value);
		Selection selection = givenASimpleSelection(label);
		assertEquals(value, selection.text());

		// with multiple selection, should return the first element
		selection = givenAMultipleSelection(createLabel(value), createLabel(value), createLabel(value));
		assertEquals(value, selection.text());
	}

	private D3NodeFactory createLabel(final String textValue) {
		LabelFactory l = new LabelFactory();
		l.setInnerText(textValue);
		return l;
	}
}
