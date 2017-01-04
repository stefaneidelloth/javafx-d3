package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.DivElementFactory;
import org.treez.javafxd3.d3.democases.InputElementFactory;
import org.treez.javafxd3.d3.selection.datumfunction.PrefixPlusIndexDatumFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

public class SelectionHtmlTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();
	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(new PrefixPlusIndexDatumFunction(value));
		assertEquals(value + "0", getElementInnerHtml(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection.html(new PrefixPlusIndexDatumFunction(value));
		assertEquals(value + "0", getElementInnerHtml(0));
		assertEquals(value + "1", getElementInnerHtml(1));
		assertEquals(value + "2", getElementInnerHtml(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		final String html = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(html);
		assertEquals(html, getElementInnerHtml(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection.html(html);
		assertEquals(html, getElementInnerHtml(0));
		assertEquals(html, getElementInnerHtml(1));
		assertEquals(html, getElementInnerHtml(2));

	}

	protected void testGetter() {
		// with single selection
		DivElementFactory divElementFactory = new DivElementFactory();
		final String html = "<div name=\"test\" style=\"background-color:red;\"></div>";
		divElementFactory.setInnerHTML(html);
		Selection selection = givenASimpleNodeFactory(divElementFactory);
		
		assertEquals(html, getElementInnerHtml(0));
		
		assertEquals(html, selection.html());

		// with multiple selection, should return the first element
		selection = givenMultipleNodeFactories(createLabel(html), createLabel(html), createLabel(html));
		assertEquals(html, selection.html());
	}

	private D3NodeFactory createLabel(final String textValue) {
		InputElementFactory label = new InputElementFactory();
		label.setInnerHTML(textValue);
		return label;
	}
}
