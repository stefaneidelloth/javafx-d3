package org.treez.javafxd3.d3.selection;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.svg.text.LabelFactory;
import org.treez.javafxd3.d3.functions.ConstantDatumFunction;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

@SuppressWarnings("javadoc")
public class SelectionPropertyTest extends AbstractSelectionTest {

	private static final String PROPERTY = "checked";

	private static final Double DELTA = 1e-4;

	@Override
	public void doTest() {
		testGetter();
		testSetterConstantBoolean();
		testSetterConstantDouble();
		testSetterConstantString();
		testSetterConstantJavascriptObject();
		testSetterFunction();
		testSetterGetter();
	}

	/**
	 * 
	 */
	private void testSetterGetter() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		final double value = 1.56;
		selection.property(SelectionPropertyTest.PROPERTY, new ConstantDatumFunction<Double>(value));
		assertEquals(value, selection.property(SelectionPropertyTest.PROPERTY).asDouble(), DELTA);

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(SelectionPropertyTest.PROPERTY, new ConstantDatumFunction<Double>(value));
		assertEquals(value, selection.property(SelectionPropertyTest.PROPERTY).asDouble(), DELTA);

	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		final String value = "1";
		selection.property(SelectionPropertyTest.PROPERTY, new ConstantDatumFunction<String>(value));
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asString());

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(SelectionPropertyTest.PROPERTY, new ConstantDatumFunction<String>(value));
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asString());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.PROPERTY).asString());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.PROPERTY).asString());
	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		String value = "1";
		selection.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asString());

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asString());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.PROPERTY).asString());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.PROPERTY).asString());

	}

	protected void testSetterConstantDouble() {
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		double value = 3.56;
		selection.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asDouble(), DELTA);

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asDouble(), DELTA);
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.PROPERTY).asDouble(), DELTA);
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.PROPERTY).asDouble(), DELTA);
	}

	protected void testSetterConstantBoolean() {
		boolean value = true;
		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());
		selection.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asBoolean());

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(SelectionPropertyTest.PROPERTY, value);
		assertEquals(value, getElementProperty(0, SelectionPropertyTest.PROPERTY).asBoolean());
		assertEquals(value, getElementProperty(1, SelectionPropertyTest.PROPERTY).asBoolean());
		assertEquals(value, getElementProperty(2, SelectionPropertyTest.PROPERTY).asBoolean());

	}

	protected void testSetterConstantJavascriptObject() {

		// works with single selection
		Selection selection = givenASimpleSelection(new LabelFactory());

		// any object
		JavaScriptObject value = new Coords(webEngine, 1.0, 2.0);

		String propName = "__test__";
		selection.property(propName, value);
		assertEquals(value, getElementProperty(0, propName).as());

		// works with multiple selection
		Selection selection2 = givenAMultipleSelection(new LabelFactory(), new LabelFactory(), new LabelFactory());
		selection2.property(propName, value);
		assertEquals(value, getElementProperty(0, propName).as(Object.class));
		assertEquals(value, getElementProperty(1, propName).as(Object.class));
		assertEquals(value, getElementProperty(2, propName).as(Object.class));

	}

	protected void testGetter() {
		// try a boolean
		LabelFactory cb = new LabelFactory();
		Selection sel = givenASimpleSelection(cb);
		sel.node().setPropertyBoolean("checked", true);
		assertEquals(true, sel.property("checked").asBoolean());

		// with multiple selection, should return the first element
		Selection selection2 = givenAMultipleSelection(createLabel(SelectionPropertyTest.PROPERTY, "true"),
				createLabel(SelectionPropertyTest.PROPERTY, "false"),
				createLabel(SelectionPropertyTest.PROPERTY, "false"));
		assertEquals("true", selection2.property(SelectionPropertyTest.PROPERTY).asString());
	}

	private D3NodeFactory createLabel(final String attr, final String value) {
		LabelFactory label = new LabelFactory();
		label.setPropertyString(attr, value);
		return label;
	}
}
