package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.selection.datafunction.StringBuilderFunction;

public class SelectionControlsTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testEmpty();
		testCount();
		testNode();
		testEach();		
		testCall();
		testInterrupt();
	}

	protected void testEmpty() {
		Selection selection = d3.select("svg");
		selection.append("myelement");

		assertEquals(false, selection.select("myelement").empty());
		assertEquals(true, selection.select("unknown").empty());
	}
	
	private void testCount() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("1"), new InputElementFactory("2"));
		assertEquals(2, selection.size());
	}

	private void testNode() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("1"), new InputElementFactory("2"));	
				
		assertEquals("1", selection.get(0).get(0).node().getText());
		
		selection = selection.selectAll("unknown");
		assertNull(selection.node());
	}

	private void testEach() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("1"), new InputElementFactory("2"));
		final StringBuilder stringBuilder = new StringBuilder();

		selection.each(new StringBuilderFunction(webEngine, stringBuilder));
		assertEquals("12", stringBuilder.toString());
	}	

	private void testCall() {
		// nothing to test before any other use cases of using selection.call-
		// maybe by providing a SelectionCallback interface with one method
		// call(Selection) ?
	}

	private void testInterrupt() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("1"), new InputElementFactory("2"));
		selection.interrupt();
	}
}
