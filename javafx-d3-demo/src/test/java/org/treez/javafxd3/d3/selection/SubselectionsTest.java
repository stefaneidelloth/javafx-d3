package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.data.CountDataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import netscape.javascript.JSObject;

/**
 * Testing the internal structure of the selections and sub selections.
 */
public class SubSelectionsTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testD3Select();
		testD3SelectAll();
		testD3SelectThenSelect();
		testD3SelectThenSelectAll();
		testD3SelectThenSelectAllByFunction();
		testSelectAllSelectByString();
	}

	protected void assertParentNodeIsRootHtml(final Selection selection) {
		Element parentNode = selection.parentNode(0);
		assertNotNull(parentNode);
		assertEquals("html", parentNode.getTagName().toLowerCase());

	}

	private void testD3Select() {
		clearSvg();
		Selection selection = d3.select("svg");		
		assertEquals(getSvg().node(), selection.node());

		// internal structure
		int length = selection.node().getChildCount();
		assertEquals(0, length);

		Integer groupCount = selection.groupCount();
		assertEquals(1, (int) groupCount);
		assertParentNodeIsRootHtml(selection);

		// appending returns a new selection with the same parent node (html)
		Selection child = selection.append("blah");
		assertParentNodeIsRootHtml(child);
		assertEquals(1, child.size());
		assertEquals(1, d3.selectAll("blah").size());

	}

	private void testD3SelectAll() {
		clearRoot();

		getRoot().html("<div><blah>foo</blah></div>" + "<div><blah>bar</blah></div>" + "<div><blah>zing</blah></div>");

		Selection blahs = d3.selectAll("blah");

		// internal structure
		assertEquals(3, blahs.size());
		assertEquals(1, blahs.groupCount());
		assertEquals(1, (int) blahs.asElementArray().length());
		JSObject firstElement = blahs.asElementArray().get(0, JSObject.class);

		assertEquals(3, new Array<>(webEngine, firstElement).length());

		// parentNode
		assertParentNodeIsRootHtml(blahs);

		// appending an element to the blah tags are easy
		Selection barfoos = blahs.append("barfoo");
		assertEquals(3, barfoos.size());
		assertEquals(1, barfoos.groupCount());
		assertEquals(1, (int) barfoos.asElementArray().length());

		firstElement = blahs.asElementArray().get(0, JSObject.class);
		assertEquals(3, new Array<>(webEngine, firstElement).length());
		assertParentNodeIsRootHtml(barfoos);

		//Element[][] matrix1 = getRoot().node().getChild(0).<Element[][]> cast();
		//Element[][] matrix2 = getRoot().node().getChild(1).<Element[][]> cast();
		//Element[][] matrix3 = getRoot().node().getChild(2).<Element[][]> cast();
		//Selection selection = d3.selectAll(matrix1, matrix2, matrix3);
		//assertEquals(3, selection.size());

	}

	private void testD3SelectThenSelect() {
		clearRoot();
		// given 3 DIV>SPAN elements in the sandbox

		getRoot().node().setInnerHtml("<div><zorg>foo</zorg><zorg>foo2</zorg></div>"
				+ "<div><zorg>bar</zorg><zorg>bar2</zorg></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		Selection sandboxSelection = d3.select("#root");

		// for each element in the current selection, select the first
		// descendant matching the selector
		Selection divs = sandboxSelection.selectAll("div");
		Selection firstZorgs = sandboxSelection.select("zorg");
		assertEquals(1, firstZorgs.size());
		assertEquals(1, firstZorgs.groupCount());
		assertParentNodeIsRootHtml(firstZorgs);
		Array<Element> array = firstZorgs.asElementArray();
		assertEquals(1, (int) array.sizes().get(1));

		JSObject firstElement = array.get(0, JSObject.class);

		assertEquals(1, new Array<>(webEngine, firstElement).length());
		// if multiple elements match the selector, only the first matching
		// element in document traversal order will be
		// selected.

		// if no element match for a element, the element at the current index
		// will be null in the returned selection
		// a) operators skip null element , thereby preserving the index of the
		// existing selection
		clearRoot();
		getRoot().node().setInnerHtml("<div></div>" + "<div></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		sandboxSelection = d3.select("#root");
		divs = sandboxSelection.selectAll("div");
		firstZorgs = sandboxSelection.select("zorg");
		assertEquals(1, firstZorgs.size());
		assertEquals(1, firstZorgs.groupCount());
		assertParentNodeIsRootHtml(firstZorgs);

		CountDataFunction countFunction = new CountDataFunction();
		firstZorgs.each(countFunction.reset());
		assertEquals(1, countFunction.getCount());

		// if the current element has associated data, this data is inherited by
		// the returned subselection and
		// automatically bound to the newly selected elements
		clearRoot();
		getRoot().node().setInnerHtml("<div><zorg>foo</zorg><zorg>foo2</zorg></div>"
				+ "<div><zorg>bar</zorg><zorg>bar2</zorg></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		divs = d3.select("#root").selectAll("div");

		Array<JSObject> elemArray = new Array<>(webEngine, divs.asElementArray().get(0, JSObject.class));

		elemArray.get(0, Element.class).setPropertyInt(Selection.DATA_PROPERTY, 6);
		elemArray.get(1, Element.class).setPropertyInt(Selection.DATA_PROPERTY, 2);
		elemArray.get(2, Element.class).setPropertyInt(Selection.DATA_PROPERTY, 4);

		Selection select = divs.select("zorg");
		Array<Object> data = select.data();
		assertEquals(6.0, data.get(0, Double.class), TOLERANCE);
		assertEquals(2.0, data.get(1, Double.class), TOLERANCE);
		assertEquals(4.0, data.get(2, Double.class), TOLERANCE);

		// select as a function returning element or null

		// select by function
		// Selection selection = sandboxSelection.select(new
		// DataFunction<Element>() {
		// @Override
		// public Element apply(final Element context, final Value d, final int
		// index) {
		// // maybe it can be any node ??? even a non child node
		// return Document.get().getBody();
		// }
		// });
		// assertEquals(1, selection.size());
		// assertEquals(1, selection.groupCount());
		// assertEquals(Document.get().getBody(), selection.node());
	}

	private void testD3SelectThenSelectAll() {
		clearRoot();
		// given 3 DIV elements in the sandbox
		getRoot().node()
				.setInnerHtml("<div><span></span></div>" + "<div><span></span></div>" + "<div><span></span></div>");
		//
		assertEquals(3, getRoot().node().getChildCount());
		Selection spans = d3.select("#root").selectAll("span");
		assertEquals(3, spans.size());
		assertEquals(1, spans.groupCount());
		assertEquals(getRoot().node(), spans.parentNode(0));
		// appending a node append it to each span nodes, and the parent node of
		// the new selection is the same as before
		Selection appended = spans.append("foobar");
		assertEquals(3, appended.size());
		assertEquals(1, appended.groupCount());
		assertEquals(getRoot().node(), appended.parentNode(0));

		// where does the new nodes have been appended ? in the span ? or in the
		// sanbox ?
		assertEquals(3, getRoot().node().getChildCount());
		// it seems in the span elements
		d3.select("#root").selectAll("span").each(new AssertOneChildDataFunction(webEngine));

	}

	private void testD3SelectThenSelectAllByFunction() {
		clearRoot();
		// given 3 DIV elements in the sandbox
		getRoot().node()
				.setInnerHtml("<div><span></span></div>" + "<div><span></span></div>" + "<div><span></span></div>");
		//
		assertEquals(3, getRoot().node().getChildCount());
		Selection spans = d3.select("#root").selectAll("span");
		assertEquals(3, spans.size());
		assertEquals(1, spans.groupCount());
		assertEquals(getRoot().node(), spans.parentNode(0));
		// appending a node append it to each span nodes, and the parent node of
		// the new selection is the same as before
		Selection appended = spans.append("foobar");
		assertEquals(3, appended.size());
		assertEquals(1, appended.groupCount());
		assertEquals(getRoot().node(), appended.parentNode(0));

		// where does the new nodes have been appended ? in the span ? or in the
		// sanbox ?
		assertEquals(3, getRoot().node().getChildCount());
		// it seems in the span elements
		d3.select("#root").selectAll("span").each(new AssertOneChildDataFunction(webEngine));

	}

	private void testSelectAllSelectByString() {
		clearRoot();
		// given 3 DIV>SPAN elements in the sandbox
		getRoot().node().setInnerHtml(
				"<div><span>foo</span></div>" + "<div><span>bar</span></div>" + "<div><span>zing</span></div>");
		// when selecting the sandbox then selecting the div
		// I got only 1 node in the selection
		// Selection sandboxSelection = d3.select(sandbox);
		// assertEquals(1, sandboxSelection.count());
		Selection divs = d3.select("#root").selectAll("div");
		assertEquals(3, divs.size());
		Selection spans = divs.select("span");
		assertEquals(3, spans.size());

	}

}
