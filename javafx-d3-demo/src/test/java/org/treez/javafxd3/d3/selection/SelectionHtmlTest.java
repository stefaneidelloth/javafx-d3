package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.DivElementFactory;
import org.treez.javafxd3.d3.selection.datafunction.PrefixPlusIndexDataFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

public class SelectionHtmlTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();
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
		selection = givenMultipleNodeFactories(createDivFactory(html), createDivFactory(html), createDivFactory(html));
		assertEquals(html, selection.html());
	}

	

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new DivElementFactory());
		final String html = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(html);
		assertEquals(html, getElementInnerHtml(0));

		// works with multiple selection
		clearRoot();
		selection = givenMultipleNodeFactories(new DivElementFactory(), new DivElementFactory(), new DivElementFactory());
		selection.html(html);		
		
		assertEquals(html, getElementInnerHtml(0));
		assertEquals(html, getElementInnerHtml(1));
		assertEquals(html, getElementInnerHtml(2));
	}
	
	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new DivElementFactory());
		final String html = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(new PrefixPlusIndexDataFunction(html));
		assertEquals(html + "0", getElementInnerHtml(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(new DivElementFactory(), new DivElementFactory(), new DivElementFactory());
		selection.html(new PrefixPlusIndexDataFunction(html));
		assertEquals(html + "0", getElementInnerHtml(0));
		assertEquals(html + "1", getElementInnerHtml(1));
		assertEquals(html + "2", getElementInnerHtml(2));

	}

	

	private D3NodeFactory createDivFactory(final String html) {
		DivElementFactory label = new DivElementFactory();
		label.setInnerHTML(html);
		return label;
	}
}
