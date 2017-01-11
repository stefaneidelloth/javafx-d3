package org.treez.javafxd3.d3.selection;

import java.util.Arrays;
import java.util.List;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.selection.comparator.ValueComparator;
import org.treez.javafxd3.d3.selection.datafunction.AssertCounterDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.AssertNullStringDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.AssertOddEvenDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.AssertStringDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.OddEvenDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.OddEvenElementDataFunction;
import org.treez.javafxd3.d3.selection.datafunction.StringDataFunction;
import org.treez.javafxd3.d3.selection.keyfunction.SelectionData2KeyFunction;
import org.treez.javafxd3.d3.wrapper.Element;

public class SelectionData2Test extends AbstractSelectionTest {

	static final double DELTA = 1e-4;

	@Override
	public void doTest() {

		testSelectionExit();
		testSelectionDatumGetter();
		testSelectionDatumSetterConstant();
		testSelectionDatumSetterFunction();
		testSelectionFilterString();
		testSelectionFilterFunction();
		testSelectionSort();
		testSelectionOrder();
	}

	private void testSelectionExit() {
		clearRoot();
		// GIVEN 6 divs elements with some numbers
		int[] data = new int[] { 1, 2, 3, 4, 8, 13 };
		Selection sandBoxSelection = d3.select("#root");
		Selection dataJoinedSelection = sandBoxSelection.selectAll("div").data(data).enter().append("div").attr("blah",
				new StringDataFunction(webEngine));

		assertEquals(6, dataJoinedSelection.size());

		// WHEN I put new data in the current selection
		List<Integer> newDataList = Arrays.asList(1, 2, 3, 4, 5, 6);

		// AND I join the new data on the elements with their old data		
		UpdateSelection updateSelection = dataJoinedSelection.data(newDataList,
				new SelectionData2KeyFunction(webEngine));
		Selection newElements = updateSelection.enter().append("div");
		assertEquals(2, newElements.size());

		Selection joined = d3.select("#root").selectAll("div");
		assertEquals(8, joined.size());

		// AND I remove the exit selection (=the elements that corresponds to old data that can not be found  
		// in the new data: 8, 13)		
		updateSelection.exit().remove();

		// AND I remove the newly added elements (5, 6)
		newElements.remove();

		// THEN The dom contains only the elements corresponding to the
		// intersection of old and new data (1, 2, 3, 4)
		Selection remaining = d3.select("#root").selectAll("div");
		assertEquals(4, remaining.size());

		remaining.each(new AssertCounterDataFunction(webEngine));

	}

	private void testSelectionDatumGetter() {
		// GIVEN a multiple selection with data join
		Selection selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(),
				new InputElementFactory());
		selection.data(new String[] { "54", "2", "10" });
		// WHEN I call selection.datum()
		Value v = selection.datum();
		// THEN I get the datum of the first non null elemenet
		assertEquals("54", v.asString());
	}

	private void testSelectionDatumSetterConstant() {
		// GIVEN a multiple selection
		Selection selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(),
				new InputElementFactory());
		// WHEN I call selection.datum() with a constant "blah"
		selection.datum("blah");
		// THEN all data has a datum of "blah"
		selection.each(new AssertStringDataFunction(webEngine, "blah"));
		// WHEN I call selection.datum() with a constant NULL
		String datum = null;
		selection.datum(datum);
		// THEN all elements has a null data
		selection.each(new AssertNullStringDataFunction(webEngine));
	}

	private void testSelectionDatumSetterFunction() {
		// GIVEN a multiple selection
		Selection selection = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(),
				new InputElementFactory());
		// WHEN I call selection.datum() with a function depending on the index
		selection.datum(new OddEvenDataFunction(5.0, 2.0));
		// THEN each element has a the corresponding data
		selection.each(new AssertOddEvenDataFunction(webEngine, 5.0, 2.0));
	}

	private void testSelectionFilterFunction() {
		// GIVEN a selection with n elements
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("0"), new InputElementFactory("1"),
				new InputElementFactory("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(new OddEvenElementDataFunction(webEngine));
		// THEN the returned selection contains 2 elements (css is 1-based
		// index)
		assertEquals(2, filtered.size());
		assertEquals("0", filtered.node().getText());
		assertEquals(3, selection.size());
	}

	private void testSelectionFilterString() {
		// GIVEN a selection with n elements
		Selection selection = givenMultipleNodeFactories(new InputElementFactory("0"), new InputElementFactory("1"),
				new InputElementFactory("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(":nth-child(odd)");
		// THEN the returned selection contains 2 elements (css is 1-based
		// index)
		assertEquals(2, filtered.size());

		Element firstElement = filtered.node();
		assertEquals("0", firstElement.getText());
		assertEquals(3, selection.size());
	}

	private void testSelectionOrder() {
		// GIVEN a selection with elements ordered differently than in the DOM
		InputElementFactory l1 = new InputElementFactory("blah1");
		InputElementFactory l2 = new InputElementFactory("blah2");
		Selection createdElements = givenMultipleNodeFactories(l1, l2);
		assertEquals(2, createdElements.size());

		//switch order of nodes in DOM (order in selection is kept)
		getRoot().node().remove(l1);
		getRoot().node().add(l1);

		//check order in selection
		assertEquals("blah1", createdElements.get(0).get(0).node().getText());
		assertEquals("blah2", createdElements.get(0).get(1).node().getText());

		//check order in DOM
		assertEquals("blah2", getElementProperty(0, "textContent").asString());
		assertEquals("blah1", getElementProperty(1, "textContent").asString());

		// WHEN calling selection.order
		createdElements.order();

		// THEN the elements are reordered in the DOM in the order of the
		// selection		
		assertEquals("blah1", getElementProperty(0, "textContent").asString());
		assertEquals("blah2", getElementProperty(1, "textContent").asString());
	}

	private void testSelectionSort() {
		// GIVEN a selection with elements ordered differently than in the DOM
		InputElementFactory l1 = new InputElementFactory("blah2");
		InputElementFactory l2 = new InputElementFactory("blah1");
		Selection selection = givenMultipleNodeFactories(l1, l2);
		Element firstNode = selection.node();
		assertEquals("blah2", firstNode.getText());

		assertEquals("blah1", getElementProperty(1, "textContent").asString());

		//assertEquals("blah2", ((Element) getSvg().node().getChild(0)).getInnerText());
		//assertEquals("blah1", ((Element) getSvg().node().getChild(1)).getInnerText());

		// bind integers 1 and 2 on the elements
		selection.data(new Integer[] { 2, 1 });
		// WHEN calling selection.order
		selection = selection.sort(new ValueComparator(webEngine));
		// THEN the elements are reordered in the DOM in the order of the
		// selection
		assertEquals("blah1", getElementProperty(0, "textContent").asString());
		assertEquals("blah2", getElementProperty(1, "textContent").asString());

		//assertEquals("blah1", ((Element) getSvg().node().getChild(0)).getInnerText());
		//assertEquals("blah2", ((Element) getSvg().node().getChild(1)).getInnerText());
	}
}
