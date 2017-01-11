package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.functions.data.ConstantDataFunction;
import org.treez.javafxd3.d3.functions.data.EvenIndexDataFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;

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
		Selection selection = givenASimpleNodeFactory(createLabelFactory(""));
		
		
		selection.classed("foo bar", new ConstantDataFunction<Boolean>(true));
		
		assertEquals("foo bar", getElementClassAttribute(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(createLabelFactory(""), createLabelFactory(""), createLabelFactory(""));
		
		
		selection.classed("foo bar", new EvenIndexDataFunction());
		
		assertEquals("foo bar", getElementClassAttribute(0));
		assertEquals("", getElementClassAttribute(1));
		assertEquals("foo bar", getElementClassAttribute(2));

	}

	protected void testSetterConstantString() {
		// 1 test add
		// works with single selection
		Selection selection = givenASimpleNodeFactory(createLabelFactory(""));
		selection.classed("foo bar", true);
		assertEquals("foo bar", getElementClassAttribute(0));

		// works with multiple selection
		selection = givenMultipleNodeFactories(createLabelFactory(""), createLabelFactory(""));
		selection.classed("barry lindon", true);
		assertEquals("barry lindon", getElementClassAttribute(0));
		assertEquals("barry lindon", getElementClassAttribute(1));

		// 2 test remove
		selection = givenASimpleNodeFactory(createLabelFactory("foo bar"));
		selection.classed("foo", false);
		assertEquals("bar", getElementClassAttribute(0));

		selection = givenASimpleNodeFactory(createLabelFactory("foo bar"));
		selection.classed("foo bar", false);
		assertEquals("", getElementClassAttribute(0));

		selection = givenASimpleNodeFactory(createLabelFactory("foo bar"));
		selection.classed("bar foo", false);
		assertEquals("", getElementClassAttribute(0));

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(createLabelFactory("foo bar"), createLabelFactory("bar lindon"));
		selection2.classed("bar", false);
		assertEquals("foo", getElementClassAttribute(0));
		assertEquals("lindon", getElementClassAttribute(1));

	}

	protected void testGetter() {
		// with single selection
		Selection selection = givenASimpleNodeFactory(createLabelFactory("foo bar"));
		assertEquals(true, selection.classed("bar"));
		assertEquals(true, selection.classed("foo"));
		assertEquals(true, selection.classed("foo bar"));
		assertEquals(true, selection.classed("bar foo"));
		assertEquals(false, selection.classed("bary"));

		Selection multipleSelection = givenMultipleNodeFactories(createLabelFactory("bary doe"),
				createLabelFactory("foo bar"));
		assertEquals(true, multipleSelection.classed("doe"));
		assertEquals(true, multipleSelection.classed("bary"));
		assertEquals(true, multipleSelection.classed("doe bary"));
		assertEquals(true, multipleSelection.classed("bary doe"));
		assertEquals(false, multipleSelection.classed("foo"));

	}

	private D3NodeFactory createLabelFactory(final String className) {
		InputElementFactory labelFactory = new InputElementFactory();
		labelFactory.setStyleClass(className);
		return labelFactory;
	}
}
