package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.functions.data.ConstantDataFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

@SuppressWarnings("javadoc")
public class SelectionPropertyTest extends AbstractSelectionTest {

	private static final String BOOLEAN_PROPERTY = "checked";	
	private static final String STRING_PROPERTY = "value";	

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantBoolean();		
		testSetterConstantString();
		testSetterConstantJavascriptObject();
		testSetterFunction();
		testSetterGetter();
	}
	
	protected void testGetter() {
		// try a boolean
		InputElementFactory cb = new InputElementFactory();
		Selection sel = givenASimpleNodeFactory(cb);
		sel.node().setPropertyBoolean("checked", true);
		assertEquals(true, sel.property("checked").asBoolean());

		// with multiple selection, should return the first element
		Selection selection2 = givenMultipleNodeFactories(createInputElement(SelectionPropertyTest.BOOLEAN_PROPERTY, "true"),
				createInputElement(SelectionPropertyTest.BOOLEAN_PROPERTY, "false"),
				createInputElement(SelectionPropertyTest.BOOLEAN_PROPERTY, "false"));
		
		assertEquals("true", selection2.property(SelectionPropertyTest.BOOLEAN_PROPERTY).asString());
	}
	
	protected void testSetterConstantBoolean() {
		boolean value = true;
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		selection.property(SelectionPropertyTest.BOOLEAN_PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.BOOLEAN_PROPERTY).asBoolean());

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.property(SelectionPropertyTest.BOOLEAN_PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.BOOLEAN_PROPERTY).asBoolean());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.BOOLEAN_PROPERTY).asBoolean());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.BOOLEAN_PROPERTY).asBoolean());
	}
	
	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		String value = "1";
		selection.property(SelectionPropertyTest.STRING_PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.STRING_PROPERTY).asString());

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.property(SelectionPropertyTest.STRING_PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.STRING_PROPERTY).asString());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.STRING_PROPERTY).asString());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.STRING_PROPERTY).asString());

	}
	
	protected void testSetterConstantJavascriptObject() {

		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());

		// any object
		JavaScriptObject value = new Coords(engine, 1.0, 2.0);

		String propName = "__test__";
		selection.property(propName, value);
		assertEquals(value, getElementProperty(0, propName).as());

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.property(propName, value);
		assertEquals(value, getElementProperty(0, propName).as());
		assertEquals(value, getElementProperty(1, propName).as());
		assertEquals(value, getElementProperty(2, propName).as());
	}
	
	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		final String value = "1";
		selection.property(SelectionPropertyTest.STRING_PROPERTY, new ConstantDataFunction<String>(value));
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.STRING_PROPERTY).asString());

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.property(SelectionPropertyTest.STRING_PROPERTY, new ConstantDataFunction<String>(value));
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.STRING_PROPERTY).asString());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.STRING_PROPERTY).asString());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.STRING_PROPERTY).asString());
	}
	
	private void testSetterGetter() {
		// works with single selection
		Selection selection = givenASimpleNodeFactory(new InputElementFactory());
		final String value = "1.56";
		selection.property(SelectionPropertyTest.STRING_PROPERTY, new ConstantDataFunction<String>(value));
		assertEquals(value, selection.property(SelectionPropertyTest.STRING_PROPERTY).asString());

		// works with multiple selection
		Selection selection2 = givenMultipleNodeFactories(new InputElementFactory(), new InputElementFactory(), new InputElementFactory());
		selection2.property(SelectionPropertyTest.STRING_PROPERTY, new ConstantDataFunction<String>(value));
		assertEquals(value, selection.property(SelectionPropertyTest.STRING_PROPERTY).asString());
	}
	

	private D3NodeFactory createInputElement(final String attr, final String value) {
		InputElementFactory inputElementFactory = new InputElementFactory();
		inputElementFactory.setProperty(attr, value);
		return inputElementFactory;
	}
}
