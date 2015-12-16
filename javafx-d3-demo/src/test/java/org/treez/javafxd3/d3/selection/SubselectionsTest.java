package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.CountFunction;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import netscape.javascript.JSObject;

/**
 * Testing the internal structure of the selections and sub selections.
 */
public class SubselectionsTest extends AbstractSelectionTest {

	@Override
	public void doTest() {
		testD3Select();
		testD3SelectAll();
		testD3SelectThenSelect();
		testD3SelectThenSelectAll();
		testD3SelectThenSelectAllByFunction();
		// testSelectAllSelectByString();
		// testSelectAllSelectAllByString();

		// testSelectSelectByFunction();
		// testSelectAllSelectByFunction();
	}

	protected void assertParentNodeIsRootHtml(final Selection s) {
		Element parentNode = s.parentNode(0);
		assertNotNull(parentNode);
		assertEquals("html", parentNode.getTagName().toLowerCase());

	}

	/**
	 * @throws Exception
	 *
	 */
	private void testD3Select() {
		clearSvg();
		Selection selection = d3.select("svg");
		assertEquals(getSvg().node(), selection.node());

		// internal structure

		int length = selection.node().getChildCount();
		assertEquals(1, length);
		int childLength = selection.get(0).node().getChildCount();
		assertEquals(1, childLength);

		Integer groupCount = null;
		try {
			groupCount = selection.groupCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, (int) groupCount);
		assertParentNodeIsRootHtml(selection);

		// appending returns a new selection with the same parent node (html)
		Selection child = selection.append("blah");
		assertParentNodeIsRootHtml(child);
		assertEquals(1, child.size());
		assertEquals(1, d3.selectAll("blah").size());

	}

	/**
	 *
	 */
	private void testD3SelectAll() {
		clearSvg();

		getSvg().node().setInnerHtml(
				"<div><blah>foo</blah></div>" + "<div><blah>bar</blah></div>" + "<div><blah>zing</blah></div>");

		Selection blahs = d3.selectAll("blah");

		// internal structure
		assertEquals(3, blahs.size());
		assertEquals(1, blahs.groupCount());
		assertEquals(1, (int) blahs.asElementArray().sizes().get(1));
		assertEquals(3, blahs.asElementArray().get(0, Object[].class).length);

		// parentNode
		assertParentNodeIsRootHtml(blahs);

		// appending an element to the blah tags are easy
		Selection barfoos = blahs.append("barfoo");
		assertEquals(3, barfoos.size());
		assertEquals(1, barfoos.groupCount());
		assertEquals(1, (int) barfoos.asElementArray().sizes().get(1));
		assertEquals(3, blahs.asElementArray().get(0, Object[].class).length);
		assertParentNodeIsRootHtml(barfoos);

		// also test variant of selectAll
		Selection selection = d3.selectAll(getSvg().node().getChildNodes());
		assertEquals(3, selection.size());

		Element[][] matrix1 = getSvg().node().getChild(0).<Element[][]> cast();
		Element[][] matrix2 = getSvg().node().getChild(1).<Element[][]> cast();
		Element[][] matrix3 = getSvg().node().getChild(2).<Element[][]> cast();
		selection = d3.selectAll(matrix1, matrix2, matrix3);
		assertEquals(3, selection.size());

	}

	/**
	 *
	 */
	private void testD3SelectThenSelect() {
		clearSvg();
		// given 3 DIV>SPAN elements in the sandbox
		clearSvg();
		getSvg().node().setInnerHtml("<div><zorg>foo</zorg><zorg>foo2</zorg></div>"
				+ "<div><zorg>bar</zorg><zorg>bar2</zorg></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		Selection sandboxSelection = d3.select("root");

		// for each element in the current selection, select the first
		// descendant matching the selector
		Selection divs = sandboxSelection.selectAll("div");
		Selection firstZorgs = sandboxSelection.select("zorg");
		assertEquals(1, firstZorgs.size());
		assertEquals(1, firstZorgs.groupCount());
		assertParentNodeIsRootHtml(firstZorgs);
		Array<JSObject> array = firstZorgs.asElementArray();
		assertEquals(1, (int) array.sizes().get(1));
		assertEquals(1, array.get(0, Object[].class).length);
		// if multiple elements match the selector, only the first matching
		// element in document traversal order will be
		// selected.

		// if no element match for a element, the element at the current index
		// will be null in the returned selection
		// a) operators skip null element , thereby preserving the index of the
		// existing selection
		clearSvg();
		getSvg().node().setInnerHtml("<div></div>" + "<div></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		sandboxSelection = d3.select("root");
		divs = sandboxSelection.selectAll("div");
		firstZorgs = sandboxSelection.select("zorg");
		assertEquals(1, firstZorgs.size());
		assertEquals(1, firstZorgs.groupCount());
		assertParentNodeIsRootHtml(firstZorgs);

		CountFunction countFunction = new CountFunction();
		firstZorgs.each(countFunction.reset());
		assertEquals(1, countFunction.getCount());

		// if the current element has associated data, this data is inherited by
		// the returned subselection and
		// automatically bound to the newly selected elements
		clearSvg();
		getSvg().node().setInnerHtml("<div><zorg>foo</zorg><zorg>foo2</zorg></div>"
				+ "<div><zorg>bar</zorg><zorg>bar2</zorg></div>" + "<div><zorg>zing</zorg><zorg>zing2</zorg></div>");
		divs = d3.select("root").selectAll("div");
		
		/*
		divs.asElementArray().get(0, Node.class)[0].setPropertyInt(Selection.DATA_PROPERTY, 6);
		divs.asElementArray()[0][1].setPropertyInt(Selection.DATA_PROPERTY, 2);
		divs.asElementArray()[0][2].setPropertyInt(Selection.DATA_PROPERTY, 4);
		*/
		
		Selection select = divs.select("zorg");
		Array<Object> data = select.data();
		assertEquals(6.0, data.get(0, Double.class), TOLERANCE);
		assertEquals(2.0, data.get(1, Double.class), TOLERANCE);
		assertEquals(4.0, data.get(2, Double.class), TOLERANCE);

		// select as a function returning element or null

		// select by function
		// Selection selection = sandboxSelection.select(new
		// DatumFunction<Element>() {
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

	/**
	 *
	 */
	private void testD3SelectThenSelectAll() {
		clearSvg();
		// given 3 DIV elements in the sandbox
		getSvg().node()
				.setInnerHtml("<div><span></span></div>" + "<div><span></span></div>" + "<div><span></span></div>");
		//
		assertEquals(3, getSvg().node().getChildCount());
		Selection spans = d3.select("root").selectAll("span");
		assertEquals(3, spans.size());
		assertEquals(1, spans.groupCount());
		assertEquals(getSvg().node(), spans.parentNode(0));
		// appending a node append it to each span nodes, and the parent node of
		// the new selection is the same as before
		Selection appended = spans.append("foobar");
		assertEquals(3, appended.size());
		assertEquals(1, appended.groupCount());
		assertEquals(getSvg().node(), appended.parentNode(0));

		// where does the new nodes have been appended ? in the span ? or in the
		// sanbox ?
		assertEquals(3, getSvg().node().getChildCount());
		// it seems in the span elements
		d3.select("root").selectAll("span").each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {

				Value datum = (Value) d;
				Element element = (Element) context;

				assertEquals(1, element.getChildCount());
				return null;
			}
		});

	}

	/**
	 *
	 */
	private void testD3SelectThenSelectAllByFunction() {
		clearSvg();
		// given 3 DIV elements in the sandbox
		getSvg().node()
				.setInnerHtml("<div><span></span></div>" + "<div><span></span></div>" + "<div><span></span></div>");
		//
		assertEquals(3, getSvg().node().getChildCount());
		Selection spans = d3.select("root").selectAll(new DatumFunction<Element[]>() {
			@Override
			public Element[] apply(final Object context, final Object d, final int index) {

				Value datum = (Value) d;
				Element element = (Element) context;

				return element.getElementsByTagName("span");
			}
		});
		assertEquals(3, spans.size());
		assertEquals(1, spans.groupCount());
		assertEquals(getSvg().node(), spans.parentNode(0));
		// appending a node append it to each span nodes, and the parent node of
		// the new selection is the same as before
		Selection appended = spans.append("foobar");
		assertEquals(3, appended.size());
		assertEquals(1, appended.groupCount());
		assertEquals(getSvg().node(), appended.parentNode(0));

		// where does the new nodes have been appended ? in the span ? or in the
		// sanbox ?
		assertEquals(3, getSvg().node().getChildCount());
		// it seems in the span elements
		d3.select("root").selectAll("span").each(new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int index) {

				Value datum = (Value) d;
				Element element = (Element) context;

				assertEquals(1, element.getChildCount());
				return null;
			}
		});

	}

	private void testSelectAllSelectByString() {
		clearSvg();
		// given 3 DIV>SPAN elements in the sandbox
		getSvg().node().setInnerHtml(
				"<div><span>foo</span></div>" + "<div><span>bar</span></div>" + "<div><span>zing</span></div>");
		// when selecting the sandbox then selecting the div
		// I got only 1 node in the selection
		// Selection sandboxSelection = d3.select(sandbox);
		// assertEquals(1, sandboxSelection.count());
		Selection divs = d3.select("root").selectAll("div");
		assertEquals(3, divs.size());
		Selection spans = divs.select("span");
		assertEquals(3, spans.size());

	}

}
