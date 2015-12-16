package org.treez.javafxd3.d3.selection;

import java.util.Arrays;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.selection.comparator.ValueComparator;
import org.treez.javafxd3.d3.selection.datumfunction.AssertCounterDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.AssertNullStringDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.AssertOddEvenDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.AssertStringDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.OddEvenDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.OddEvenElementDatumFunction;
import org.treez.javafxd3.d3.selection.datumfunction.StringDatumFunction;

import netscape.javascript.JSObject;

@SuppressWarnings("javadoc")
public class SelectionData2Test extends AbstractSelectionTest {

	static final double DELTA = 1e-4;

	@Override
	public void doTest() {

		// testSelectionExit();
		testSelectionDatumGetter();
		testSelectionDatumSetterConstant();
		testSelectionDatumSetterFunction();
		testSelectionFilterString();
		testSelectionFilterFunction();
		testSelectionSort();
		//testSelectionOrder();
	}

	/**
	 * 
	 */
	private void testSelectionExit() {
		clearSvg();
		// GIVEN 6 divs elements with their textContents filled with the
		// fibonacci numbers
		// AND joined to the same data
		int[] data = new int[] { 1, 2, 3, 5, 8, 13 };
		Selection sandBoxSelection = d3.select("root");
		Selection dataJoinedSelection = sandBoxSelection.selectAll("div").data(data).enter().append("div").attr("blah",
				new StringDatumFunction(webEngine) );
		assertEquals(6, dataJoinedSelection.size());
		// WHEN I put new data in the current selection
		int[] newData = new int[] { 1, 2, 3, 4, 5, 6 };
		// AND I join the new data on the elements with their old data
		UpdateSelection updateSelection = dataJoinedSelection.data(Arrays.asList(newData), new KeyFunction<Integer>() {
			@Override
			public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
				
				JSObject jsObject = (JSObject) datum;
				Value value = new Value(webEngine, jsObject);
				
				JSObject elementJsObject = (JSObject) context;
				Element element = new Element(webEngine, elementJsObject);
				
				
				System.out.println("yo");
				if (context != null) {
					System.out.println("appending div " + value.asInt() + " blah: " + element.getAttribute("blah")
							+ " index: " + index);
				} else {
					System.out.println("appending div " + value.asInt() + " blah: " + value + " index: " + index);
				}

				return value.asInt();
			}
		});
		Selection newElements = updateSelection.enter().append("div");
		assertEquals(2, newElements.size());
		// AND I remove the exit selection
		Selection removedSelection = updateSelection.exit().remove();
		// THEN The dom contains only the elements corresponding to the
		// intersection of old and new data
		Selection remaining = d3.select("root").selectAll("div");
		assertEquals(4, remaining.size());
		remaining.each(new AssertCounterDatumFunction(webEngine));

	}

	/**
	 * 
	 */
	private void testSelectionDatumSetterFunction() {
		// GIVEN a multiple selection
		Selection selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		// WHEN I call selection.datum() with a function depending on the index
		selection.datum(new OddEvenDatumFunction(5.0, 2.0));
		// THEN each element has a the corresponding data
		selection.each(new AssertOddEvenDatumFunction(webEngine, 5.0, 2.0));
	}

	/**
	 * 
	 */
	private void testSelectionDatumSetterConstant() {
		// GIVEN a multiple selection
		Selection selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		// WHEN I call selection.datum() with a constant "blah"
		selection.datum("blah");
		// THEN all data has a datum of "blah"
		selection.each(new AssertStringDatumFunction(webEngine, "blah"));
		// WHEN I call selection.datum() with a constant NULL
		String datum = null;
		selection.datum(datum);
		// THEN all elements has a null data
		selection.each(new AssertNullStringDatumFunction(webEngine));
	}

	/**
	 * 
	 */
	private void testSelectionDatumGetter() {
		// GIVEN a multiple selection with data join
		Selection selection = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection.data(new String[] { "54", "2", "10" });
		// WHEN I call selection.datum()
		Value v = selection.datum();
		// THEN I get the datum of the first non null elemenet
		assertEquals("54", v.asString());

	}

	/**
	 * 
	 */
	private void testSelectionFilterFunction() {
		// GIVEN a selection with n elements
		Selection selection = givenAMultipleSelection(new LabelFactory("0"), new LabelFactory("1"), new LabelFactory("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(new OddEvenElementDatumFunction(webEngine));
		// THEN the returned selection contains 2 elements (css is 1-based
		// index)
		assertEquals(2, filtered.size());
		assertEquals("0", filtered.node().getInnerText());
		assertEquals(3, selection.size());
	}

	/**
	 * 
	 */
	private void testSelectionFilterString() {
		// GIVEN a selection with n elements
		Selection selection = givenAMultipleSelection(new LabelFactory("0"), new LabelFactory("1"), new LabelFactory("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(":nth-child(odd)");
		// THEN the returned selection contains 2 elements (css is 1-based
		// index)
		assertEquals(2, filtered.size());
		assertEquals("0", filtered.node().getInnerText());
		assertEquals(3, selection.size());
	}

	/**
	 * 
	 */
	private void testSelectionSort() {
		// GIVEN a selection with elements ordered differently than in the DOM
		LabelFactory l1 = new LabelFactory("blah2");
		LabelFactory l2 = new LabelFactory("blah1");
		Selection selection = givenAMultipleSelection(l1, l2);		
		Element firstNode = selection.node();		
		assertEquals("blah2", firstNode.getInnerText());
				
		
		assertEquals("blah1", getElementAttribute(1, "innerText"));
		
		
		//assertEquals("blah2", ((Element) getSvg().node().getChild(0)).getInnerText());
		//assertEquals("blah1", ((Element) getSvg().node().getChild(1)).getInnerText());
		
		// bind integers 1 and 2 on the elements
		selection.data(new Integer[] { 2, 1 });
		// WHEN calling selection.order
		selection = selection.sort(new ValueComparator(webEngine));
		// THEN the elements are reordered in the DOM in the order of the
		// selection
		assertEquals("blah1", getElementAttribute(0, "innerText"));
		assertEquals("blah2", getElementAttribute(1, "innerText"));
		
		//assertEquals("blah1", ((Element) getSvg().node().getChild(0)).getInnerText());
		//assertEquals("blah2", ((Element) getSvg().node().getChild(1)).getInnerText());
	}

	/**
	 * 
	 */
	private void testSelectionOrder() {
		// GIVEN a selection with elements ordered differently than in the DOM
		LabelFactory l1 = new LabelFactory("blah1"), l2 = new LabelFactory("blah2");
		Selection selection = givenAMultipleSelection(l1, l2);
		getSvg().node().remove(l1);
		getSvg().node().add(l1);
		assertEquals("blah1", selection.get(0).get(0).node().getInnerText());
		assertEquals("blah2", selection.get(0).get(1).node().getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(1)).getInnerText());
		// WHEN calling selection.order
		selection.order();
		// THEN the elements are reordered in the DOM in the order of the
		// selection
		assertEquals("blah1", getSvg().get(0).get(0).node().getInnerText());
		assertEquals("blah2", getSvg().get(0).get(1).node().getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(1)).getInnerText());

	}

}
