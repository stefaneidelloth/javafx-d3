package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.api.wrapper.Element;


/**
 * Testing
 * 
 * 
 * 
 */
public class SelectionClassedTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();

	}

	protected void testSetterFunction() {
		// 1. add
		// works with single selection
		Selection selection = givenASimpleSelection(createLabelFactory(""));
		selection.classed("foo bar", new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Element context, final Value datum, final int index) {
				return true;
			}
		});
		assertEquals("foo bar", getElementClassAttribute(0));

		// works with multiple selection
		selection = givenAMultipleSelection(createLabelFactory(""), createLabelFactory(""), createLabelFactory(""));
		selection.classed("foo bar", new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Element context, final Value datum, final int index) {
				return (index % 2) == 0;
			}
		});
		assertEquals("foo bar", getElementClassAttribute(0));
		assertEquals("", getElementClassAttribute(1));
		assertEquals("foo bar", getElementClassAttribute(2));

	}

	protected void testSetterConstantString() {
		// 1 test add
		// works with single selection
		Selection selection = givenASimpleSelection(createLabelFactory(""));
		selection.classed("foo bar", true);
		assertEquals("foo bar", getElementClassAttribute(0));

		// works with multiple selection
		selection = givenAMultipleSelection(createLabelFactory(""), createLabelFactory(""));
		selection.classed("barry lindon", true);
		assertEquals("barry lindon", getElementClassAttribute(0));
		assertEquals("barry lindon", getElementClassAttribute(1));

		// 2 test remove
		selection = givenASimpleSelection(createLabelFactory("foo bar"));
		selection.classed("foo", false);
		assertEquals("bar", getElementClassAttribute(0));

		selection = givenASimpleSelection(createLabelFactory("foo bar"));
		selection.classed("foo bar", false);
		assertEquals("", getElementClassAttribute(0));

		selection = givenASimpleSelection(createLabelFactory("foo bar"));
		selection.classed("bar foo", false);
		assertEquals("", getElementClassAttribute(0));

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(createLabelFactory("foo bar"), createLabelFactory("bar lindon"));
		selection2.classed("bar", false);
		assertEquals("foo", getElementClassAttribute(0));
		assertEquals("lindon", getElementClassAttribute(1));

	}

	protected void testGetter() {
		// with single selection
		Selection selection = givenASimpleSelection(createLabelFactory("foo bar"));
		assertEquals(true, selection.classed("bar"));
		assertEquals(true, selection.classed("foo"));
		assertEquals(true, selection.classed("foo bar"));
		assertEquals(true, selection.classed("bar foo"));
		assertEquals(false, selection.classed("bary"));

		Selection multipleSelection = givenAMultipleSelection(createLabelFactory("bary doe"), createLabelFactory("foo bar"));
		assertEquals(true, multipleSelection.classed("doe"));
		assertEquals(true, multipleSelection.classed("bary"));
		assertEquals(true, multipleSelection.classed("doe bary"));
		assertEquals(true, multipleSelection.classed("bary doe"));
		assertEquals(false, multipleSelection.classed("foo"));

	}

	private D3NodeFactory createLabelFactory(final String className) {
		Label labelFactory = new Label();
		labelFactory.setStyleClass(className);
		return labelFactory;
	}
}
