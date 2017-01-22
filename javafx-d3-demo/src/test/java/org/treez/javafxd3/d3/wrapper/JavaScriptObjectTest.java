package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.AbstractTestCase;

import org.treez.javafxd3.d3.core.JsObject;

public class JavaScriptObjectTest extends AbstractTestCase {

	@Override
	public void doTest() {
		testConstructionWihtJsEngine();
		testCreateEmptyObject();
		testCreateEmptyArray();
	}

	private void testConstructionWihtJsEngine() {
		JavaScriptObject javaScriptObject = new JavaScriptObject(engine);
		assertNotNull(javaScriptObject);
		assertNull(javaScriptObject.getJsObject());

	}

	private void testCreateEmptyObject() {
		JavaScriptObject javaScriptObject = new JavaScriptObject(engine);
		
		JsObject newEmptyObject = javaScriptObject.createEmptyObject();
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
		JavaScriptObject javaScriptObject = new JavaScriptObject(engine);
		
		JsObject newEmptyArray = javaScriptObject.createEmptyArray();		
		assertNotNull(newEmptyArray);
		
		Object thisArrayObj = newEmptyArray.eval("this");
		assertNotNull(thisArrayObj);

		int length = (Integer) newEmptyArray.getMember("length");
		assertEquals(length, 0);

	}	

}
