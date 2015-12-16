package org.treez.javafxd3.d3.arrays;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;

import org.treez.javafxd3.d3.AbstractTestCase;

import netscape.javascript.JSObject;

public class ArrayTest extends AbstractTestCase {

	@Override	
	public void doTest() {	

		D3 d3 = browser.getD3();

		testEmptyArray(d3);
		test3x0Array(d3);
		test1x3Array(d3);
		test3x2Array(d3);
		testFromList(d3);
		testFromDoubles(d3);
		testFromJavaScriptObjects(d3);
		
	}

	private void testEmptyArray(D3 d3) {
		JSObject emptyArrayObj = (JSObject) d3.eval("[]");
		Array emptyArray = new Array(webEngine, emptyArrayObj);

		List<Integer> sizes = emptyArray.sizes();
		assertEquals("number of rows", 0, (int) sizes.get(0));
		assertEquals("number of columns", 0, (int) sizes.get(1));
		assertEquals("dimension", 0, emptyArray.dimension());
	}

	private void test3x0Array(D3 d3) {
		JSObject emptyArrayObj = (JSObject) d3.eval("[[],[],[]]");
		Array emptyArray = new Array(webEngine, emptyArrayObj);

		List<Integer> sizes = emptyArray.sizes();
		assertEquals("number of rows", (int) sizes.get(0), 3);
		assertEquals("number of columns", (int) sizes.get(1), 0);
		assertEquals("dimension", 2, emptyArray.dimension());
	}

	private void test3x2Array(D3 d3) {
		// 3 x 2
		JSObject array3x2Object = (JSObject) d3.eval("[[1, 2],[3,4],[5,6]]");
		Array array3x2 = new Array(webEngine, array3x2Object);

		List<Integer> sizes3x2 = array3x2.sizes();

		assertEquals("number of rows", (int) sizes3x2.get(0), 3);
		assertEquals("number of columns", (int) sizes3x2.get(1), 2);
		assertEquals("dimension", array3x2.dimension(), 2);
	}

	private void test1x3Array(D3 d3) {
		// 1 x 3
		JSObject array1x3Object = (JSObject) d3.eval("[1, 2, 3]");
		Array array1x3 = new Array(webEngine, array1x3Object);

		List<Integer> sizes1x3 = array1x3.sizes();
		assertEquals("number of rows", 1, (int) sizes1x3.get(0));
		assertEquals("number of columns", 3, (int) sizes1x3.get(1));
		assertEquals("dimensions", 1, array1x3.dimension());
	}

	private void testFromList(D3 d3) {
		List<Double> data = new ArrayList<>();
		data.add(1.0d);
		data.add(2.0d);
		Array<Double> doubleArray = Array.fromList(webEngine, data);

		List<Integer> sizes = doubleArray.sizes();
		assertEquals("number of rows", 1, (int) sizes.get(0));
		assertEquals("number of columns", 2, (int) sizes.get(1));
		assertEquals("dimensions", 1, doubleArray.dimension());
		
		double firstValue = doubleArray.get(0, Double.class);
		assertEquals("first value", 1.0, firstValue, 1e-6);
		
		assertEquals("second value", 2.0, doubleArray.get(1, Double.class), 1e-6);
	}

	private void testFromDoubles(D3 d3) {
		
		Array<Double> doubleArray = Array.fromDoubles(webEngine, new Double[]{3.0,4.0});
		
		List<Integer> sizes = doubleArray.sizes();
		assertEquals("number of rows", 1, (int) sizes.get(0));
		assertEquals("number of columns", 2, (int) sizes.get(1));
		assertEquals("dimensions", 1, doubleArray.dimension());
		
		assertEquals("first value", 3.0, doubleArray.get(0, Double.class),TOLERANCE);
		assertEquals("second value", 4.0, doubleArray.get(1, Double.class),TOLERANCE);

	}

	private void testFromJavaScriptObjects(D3 d3) {
		
		JSObject firstObject = d3.evalForJsObject("[2]");
		JSObject secondObject = d3.evalForJsObject("['foo']");		
		
		Array<JSObject> array = Array.fromJavaScriptObjects(webEngine, firstObject, secondObject);		
		List<Integer> sizes = array.sizes();
		assertEquals("number of rows", 2, (int) sizes.get(0));
		assertEquals("number of columns", 1, (int) sizes.get(1));
		assertEquals("dimensions", 1, array.dimension());
				
		assertEquals("first value", firstObject, array.get(0, JSObject.class) );
		assertEquals("second value", secondObject, array.get(1, JSObject.class) );

	}

}
