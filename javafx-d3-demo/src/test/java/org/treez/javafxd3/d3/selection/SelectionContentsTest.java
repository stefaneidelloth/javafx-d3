package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;

@SuppressWarnings("javadoc")
public class SelectionContentsTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testRemove();
		testInsert();
		testAppend();
	}

	private void testRemove() {
		clearRoot();
		Selection selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());

		// we gonna insert sub elements into the divs
		Selection insertedElements = selection.insert("blah", "*");
		assertEquals(3, insertedElements.size());
		assertEquals(1, selection.node().getChildCount());
		insertedElements.remove();
		// there is still off screen elements in the selection
		assertEquals(3, insertedElements.size());
		assertEquals(0, selection.node().getChildCount());

		// GIVEN 3 divs, the last div is a blahblah class
		clearRoot();
		for (int i = 0; i < 3; i++) {
			selection = d3.select("#root").append("div");
		}
		selection.classed("blahblah", true);
		assertEquals(1, selection.size());		
		
		assertEquals(3, getRoot().node().getChildCount());
		// WHEN I remove the blahblah div from the selection
		selection = d3.select("#root").selectAll(".blahblah");
		selection.remove();
		// THEN it remove from the DOM
		assertEquals(2, getRoot().node().getChildCount());
		// BUT remains in the selection
		assertEquals(1, selection.size());
	}

	private void testInsert() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory());
		assertEquals(1, selection.size());
		assertEquals(0, selection.node().getChildCount());
		assertEquals("input", selection.node().getTagName().toLowerCase());
		Selection insertedElements = selection.insert("blah", "*");
		assertEquals(1, insertedElements.size());
		// the inserted blah nodes has been inserted into the labels div
		// insert before unexisting element => idem to append
		assertEquals(selection.node(), insertedElements.node().getParentElement());

		// insert a span before the existing divs
		Selection spans = selection.insert("span", "blah");
		assertEquals("span", spans.node().getTagName().toLowerCase());
		assertEquals(2, selection.node().getChildCount());
	}

	private void testAppend() {
		Selection selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory());
		assertEquals(2, selection.size());
		assertEquals(getRoot().node(), selection.node().getParentElement());
		Selection selection2 = selection.append("div");
		assertEquals(2, selection.size());
		assertNotNull(selection.node());
		assertNotNull(selection2.node());
		assertEquals(selection.node(), selection2.node().getParentElement());
		assertEquals(2, selection2.size());// returned selection contains only
											// the appended nodes
		//
		assertEquals("div", selection2.node().getTagName().toLowerCase());

		// insert before the existing divs
		Selection selection3 = selection2.append("span");
		assertEquals(2, selection.size());
		assertEquals(2, selection2.size());
		assertEquals(2, selection3.size());
		assertNotNull(selection.node());
		assertEquals("span", selection3.node().getTagName().toLowerCase());
		assertEquals(selection2.node(), selection3.node().getParentElement());
	}

}
