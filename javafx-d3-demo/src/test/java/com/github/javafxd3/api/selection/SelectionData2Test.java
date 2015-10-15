package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.UpdateSelection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.KeyFunction;
import com.github.javafxd3.api.wrapper.Element;


@SuppressWarnings("javadoc")
public class SelectionData2Test extends AbstractSelectionTest {
	
	static final double DELTA = 1e-4;

	@Override
	@Test
	public void doTest() {

		// testSelectionExit();
		testSelectionDatumGetter();
		testSelectionDatumSetterConstant();
		testSelectionDatumSetterFunction();
		testSelectionFilterString();
		testSelectionFilterFunction();
		testSelectionSort();
		testSelectionOrder();
	}

	/**
	 * 
	 */
	private void testSelectionExit() {
		clearSvg();
		// GIVEN 6 divs elements with their textContents filled with the fibonacci numbers
		// AND joined to the same data
		int[] data = new int[] { 1, 2, 3, 5, 8, 13 };
		Selection sandBoxSelection = d3.select("root");
		Selection dataJoinedSelection = sandBoxSelection.selectAll("div").data(data).enter().append("div")
				.attr("blah", new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return datum.asString();
					}
				});
		assertEquals(6, dataJoinedSelection.size());
		// WHEN I put new data in the current selection
		int[] newData = new int[] { 1, 2, 3, 4, 5, 6 };
		// AND I join the new data on the elements with their old data
		UpdateSelection updateSelection = dataJoinedSelection.data(Arrays.asList(newData), new KeyFunction<Integer>() {
			@Override
			public Integer map(final Element context, final Object[] newDataArray, final Value d, final int index) {
				System.out.println("yo");
				if (context != null) {
					System.out.println("appending div " + d.asInt() + " blah: " + context.getAttribute("blah") + " index: " + index);
				}
				else {
					System.out.println("appending div " + d.asInt() + " blah: " + d + " index: " + index);
				}

				return d.asInt();
			}
		});
		Selection newElements = updateSelection.enter().append("div");
		assertEquals(2, newElements.size());
		// AND I remove the exit selection
		Selection removedSelection = updateSelection.exit().remove();
		// THEN The dom contains only the elements corresponding to the intersection of old and new data
		Selection remaining = d3.select("root").selectAll("div");
		assertEquals(4, remaining.size());
		remaining.each(new DatumFunction<Void>() {
			private int i = 1;

			@Override
			public Void apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				assertEquals(i, datum.asInt());
				i++;
				return null;
			}
		});

	}

	/**
	 * 
	 */
	private void testSelectionDatumSetterFunction() {
		// GIVEN a multiple selection
		Selection selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		// WHEN I call selection.datum() with a function depending on the index
		selection.datum(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d, final int index) {
				return (index % 2) == 0 ? 5.0 : 2.0;
			}
		});
		// THEN each element has a the corresponding data
		selection.each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				assertEquals(((index % 2) == 0) ? 5.0 : 2.0, datum.asDouble(),DELTA);
				return null;
			}
		});
	}

	/**
	 * 
	 */
	private void testSelectionDatumSetterConstant() {
		// GIVEN a multiple selection
		Selection selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		// WHEN I call selection.datum() with a constant "blah"
		selection.datum("blah");
		// THEN all data has a datum of "blah"
		selection.each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				assertEquals("blah", datum.asString());
				return null;
			}
		});
		// WHEN I call selection.datum() with a constant NULL
		selection.datum(null);
		// THEN all elements has a null data
		selection.each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				assertNull(datum.asString());
				return null;
			}
		});
	}

	/**
	 * 
	 */
	private void testSelectionDatumGetter() {
		// GIVEN a multiple selection with data join
		Selection selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection.data(new String[]{"54", "2", "10"});
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
		Selection selection = givenAMultipleSelection(new Label("0"), new Label("1"), new Label("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(new DatumFunction<Element>() {
			@Override
			public Element apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return (index % 2) == 0 ? element : null;
			}
		});
		// THEN the returned selection contains 2 elements (css is 1-based index)
		assertEquals(2, filtered.size());
		assertEquals("0", filtered.node().getInnerText());
		assertEquals(3, selection.size());
	}

	/**
	 * 
	 */
	private void testSelectionFilterString() {
		// GIVEN a selection with n elements
		Selection selection = givenAMultipleSelection(new Label("0"), new Label("1"), new Label("2"));
		assertEquals(3, selection.size());
		// WHEN i call filter(":nth-child(odd)")
		Selection filtered = selection.filter(":nth-child(odd)");
		// THEN the returned selection contains 2 elements (css is 1-based index)
		assertEquals(2, filtered.size());
		assertEquals("0", filtered.node().getInnerText());
		assertEquals(3, selection.size());
	}

	/**
	 * 
	 */
	private void testSelectionSort() {
		// GIVEN a selection with elements ordered differently than in the DOM
		Label l1 = new Label("blah2"), l2 = new Label("blah1");
		Selection selection = givenAMultipleSelection(l1, l2);
		assertEquals("blah2", getSvg().get(0).get(0).node().getInnerText());
		assertEquals("blah1", getSvg().get(0).get(1).node().getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(1)).getInnerText());
		// bind integers 1 and 2 on the elements
		selection.data(new Integer[]{2, 1});
		// WHEN calling selection.order
		selection = selection.sort(new Comparator<Value>() {
			@Override
			public int compare(final Value o1, final Value o2) {
				Integer d1 = o1.<Integer> as();
				Integer d2 = o2.<Integer> as();
				System.out.println("sorting: " + d1 + " " + d2);
				return d1.compareTo(d2);
			}
		});
		// THEN the elements are reordered in the DOM in the order of the selection
		assertEquals("blah1", getSvg().get(0).get(0).node().getInnerText());
		assertEquals("blah2", getSvg().get(0).get(1).node().getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(1)).getInnerText());
	}

	/**
	 * 
	 */
	private void testSelectionOrder() {
		// GIVEN a selection with elements ordered differently than in the DOM
		Label l1 = new Label("blah1"), l2 = new Label("blah2");
		Selection selection = givenAMultipleSelection(l1, l2);
		getSvg().node().remove(l1);
		getSvg().node().add(l1);
		assertEquals("blah1", selection.get(0).get(0).node().getInnerText());
		assertEquals("blah2", selection.get(0).get(1).node().getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(1)).getInnerText());
		// WHEN calling selection.order
		selection.order();
		// THEN the elements are reordered in the DOM in the order of the selection
		assertEquals("blah1", getSvg().get(0).get(0).node().getInnerText());
		assertEquals("blah2", getSvg().get(0).get(1).node().getInnerText());
		assertEquals("blah1", ((Element) getSvg().node().getChild(0)).getInnerText());
		assertEquals("blah2", ((Element) getSvg().node().getChild(1)).getInnerText());

	}

}
