package com.github.javafxd3.api.selection;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.selection.datumfunction.PrefixPlusIndexDatumFunction;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.demo.client.democases.svg.LabelFactory;

@SuppressWarnings("javadoc")
public class SelectionHtmlTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();
	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(new PrefixPlusIndexDatumFunction(webEngine, value));
		assertEquals(value + "0", getElementInnerHtml(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection.html(new PrefixPlusIndexDatumFunction(webEngine, value));
		assertEquals(value + "0", getElementInnerHtml(0));
		assertEquals(value + "1", getElementInnerHtml(1));
		assertEquals(value + "2", getElementInnerHtml(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(value);
		assertEquals(value, getElementInnerHtml(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection.html(value);
		assertEquals(value, getElementInnerHtml(0));
		assertEquals(value, getElementInnerHtml(1));
		assertEquals(value, getElementInnerHtml(2));

	}

	protected void testGetter() {
		// with single selection
		LabelFactory label = new LabelFactory();
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		label.setInnerHTML(value);
		Selection selection = givenASimpleSelection(label);
		assertEquals(value, selection.html());

		// with multiple selection, should return the first element
		selection = givenAMultipleSelection(createLabel(value), createLabel(value), createLabel(value));
		assertEquals(value, selection.html());
	}

	private D3NodeFactory createLabel(final String textValue) {
		LabelFactory label = new LabelFactory();
		label.setInnerHTML(textValue);
		return label;
	}
}
