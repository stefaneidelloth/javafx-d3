package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.AbstractTestCase;

import netscape.javascript.JSObject;

public class JavaScriptObjectTest extends AbstractTestCase {

	@Override
	public void doTest() {
		testConstructionWihtWebEngine();
		testCreateEmptyObject();
		testCreateEmptyArray();
	}

	private void testConstructionWihtWebEngine() {
		JavaScriptObject javaScriptObject = new JavaScriptObject(webEngine);
		assertNotNull(javaScriptObject);
		assertNull(javaScriptObject.getJsObject());

	}

	private void testCreateEmptyObject() {
		JavaScriptObject javaScriptObject = new JavaScriptObject(webEngine);
		
		JSObject newEmptyObject = javaScriptObject.createEmptyObject();
		assertNotNull(newEmptyObject);
		
		Object thisObj = newEmptyObject.eval("this");
		assertNotNull(thisObj);
		
		//The empty object has has no length attribute
		Object length = newEmptyObject.getMember("length");
		assertEquals(length,"undefined");
		
		//The empty object has no properties at all
		Boolean hasNoProperties = (Boolean) newEmptyObject.eval("Object.getOwnPropertyNames(this).length==0");
		assertTrue(hasNoProperties);
	}

	private void testCreateEmptyArray() {
		JavaScriptObject javaScriptObject = new JavaScriptObject(webEngine);
		
		JSObject newEmptyArray = javaScriptObject.createEmptyArray();		
		assertNotNull(newEmptyArray);
		
		Object thisArrayObj = newEmptyArray.eval("this");
		assertNotNull(thisArrayObj);

		int length = (Integer) newEmptyArray.getMember("length");
		assertEquals(length, 0);

	}	

}
