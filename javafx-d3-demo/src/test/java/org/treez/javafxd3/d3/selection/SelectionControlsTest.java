package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;

import org.treez.javafxd3.d3.selection.datumfunction.StringBuilderFunction;

@SuppressWarnings("javadoc")
public class SelectionControlsTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testEmpty();// 1
		testNode();// 2
		testEach();
		testCount();
		testCall();
		testInterrupt();
	}

	private void testInterrupt() {
		Selection selection = givenAMultipleSelection(new LabelFactory("1"), new LabelFactory("2"));
		selection.interrupt();
	}

	/**
	 * 
	 */
	private void testCall() {
		// nothing to test before any other use cases of using selection.call-
		// maybe by providing a SelectionCallback interface with one method
		// call(Selection) ?

	}

	/**
	 * 
	 */
	private void testEach() {
		Selection selection = givenAMultipleSelection(new LabelFactory("1"), new LabelFactory("2"));
		final StringBuilder stringBuilder = new StringBuilder();
		
		selection.each(new StringBuilderFunction(webEngine, stringBuilder));
		assertEquals("12", stringBuilder.toString());
	}

	/**
	 * 
	 */
	private void testCount() {
		Selection selection = givenAMultipleSelection(new LabelFactory("1"), new LabelFactory("2"));
		assertEquals(2, selection.size());
	}

	/**
	 * 
	 */
	private void testNode() {
		Selection selection = givenAMultipleSelection(new LabelFactory("1"), new LabelFactory("2"));
		assertEquals("1", selection.node().getInnerText());
		selection = selection.selectAll("unknown");
		assertNull(selection.node());
	}

	protected void testEmpty() {
		Selection selection = d3.select("svg");

		selection.append("myelement");

		assertEquals(false, selection.select("myelement").empty());
		assertEquals(true, selection.select("unknown").empty());

	}

}
