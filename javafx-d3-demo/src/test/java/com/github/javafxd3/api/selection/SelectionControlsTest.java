package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

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
		Selection selection = givenAMultipleSelection(new Label("1"),
				new Label("2"));
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
		Selection selection = givenAMultipleSelection(new Label("1"),
				new Label("2"));
		final StringBuilder sb = new StringBuilder();
		selection.each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Element context, final Value d,
					final int index) {
				sb.append(context.getInnerText());
				return null;
			}
		});
		assertEquals("12", sb.toString());
	}

	/**
	 * 
	 */
	private void testCount() {
		Selection selection = givenAMultipleSelection(new Label("1"),
				new Label("2"));
		assertEquals(2, selection.size());
	}

	/**
	 * 
	 */
	private void testNode() {
		Selection selection = givenAMultipleSelection(new Label("1"),
				new Label("2"));
		assertEquals("1", selection.node().getInnerText());
		selection = selection.selectAll("unknown");
		assertNull(selection.node());
	}

	protected void testEmpty() {
		Selection selection = d3.select("root");

		selection.append("myelement");

		assertEquals(false, selection.select("myelement").empty());
		assertEquals(true, selection.select("unknown").empty());

	}

}
