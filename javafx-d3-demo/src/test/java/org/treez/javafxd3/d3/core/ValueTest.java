package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Value;

import org.treez.javafxd3.d3.AbstractTestCase;

import netscape.javascript.JSObject;

/**
 * Tests the class Value
 *
 */
public class ValueTest extends AbstractTestCase {

	@Override
	public void doTest() {

		// typeof
		assertEquals("function", getFunction().typeof());
		assertEquals("number", getInt().typeof());
		assertEquals("object", getNewNumber().typeof());
		assertEquals("number", getDecimal().typeof());
		assertEquals("number", getInfinityMinus().typeof());
		assertEquals("number", getInfinityPlus().typeof());
		assertEquals("number", getZero().typeof());
		assertEquals("string", getStringWithInt().typeof());// should we ?
		assertEquals("number", getNaN().typeof());
		assertEquals("string", getStringEmpty().typeof());
		assertEquals("object", getNewStringObject().typeof());
		assertEquals("string", getString().typeof());
		assertEquals("string", getStringWithFalse().typeof());
		assertEquals("string", getStringWithTrue().typeof());
		assertEquals("object", getNull().typeof());
		assertEquals("undefined", getUndefined().typeof());
		assertEquals("boolean", getTrue().typeof());
		assertEquals("boolean", getFalse().typeof());
		assertEquals("object", getNewBooleanTrue().typeof());
		assertEquals("object", getNewBooleanFalse().typeof());

		// is boolean
		assertTrue(getTrue().isBoolean());
		assertTrue(getFalse().isBoolean());
		assertTrue(getNewBooleanTrue().isBoolean());
		assertTrue(getNewBooleanFalse().isBoolean());
		assertTrue(getZero().isBoolean());
		assertTrue(getStringEmpty().isBoolean());

		assertFalse(getString().isBoolean());
		assertFalse(getStringWithFalse().isBoolean());
		assertFalse(getStringWithTrue().isBoolean());

		assertFalse(getInt().isBoolean());
		assertFalse(getNewNumber().isBoolean());
		assertFalse(getDecimal().isBoolean());
		assertFalse(getInfinityMinus().isBoolean());
		assertFalse(getInfinityPlus().isBoolean());
		assertFalse(getStringWithInt().isBoolean());// should we ?
		assertFalse(getNaN().isBoolean());

		assertFalse(getFunction().isBoolean());
		assertFalse(getNull().isBoolean());
		assertFalse(getUndefined().isBoolean());

		// is number
		// assertTrue(getInt().isNumber());
		// assertTrue(getNewNumber().isNumber());
		// assertTrue(getDecimal().isNumber());
		// assertTrue(getInfinityMinus().isNumber());
		// assertTrue(getInfinityPlus().isNumber());
		// assertTrue(getZero().isNumber());
		// assertTrue(getStringWithInt().isNumber());// should we ?
		// assertFalse(getNaN().isNumber());
		// assertFalse(getStringEmpty().isNumber());
		// assertFalse(getFunction().isNumber());
		// assertFalse(getString().isNumber());
		// assertFalse(getStringWithFalse().isNumber());
		// assertFalse(getStringWithTrue().isNumber());
		// assertFalse(getNull().isNumber());
		// assertFalse(getUndefined().isNumber());
		// assertFalse(getTrue().isNumber());
		// assertFalse(getFalse().isNumber());
		// assertTrue(getNewBooleanTrue().isNumber());
		// assertTrue(getNewBooleanFalse().isNumber());

		// is String
		assertFalse(getFunction().isString());
		assertFalse(getInt().isString());
		assertFalse(getNewNumber().isString());
		assertFalse(getDecimal().isString());
		assertFalse(getInfinityMinus().isString());
		assertFalse(getInfinityPlus().isString());
		assertFalse(getZero().isString());
		assertTrue(getStringWithInt().isString());// should we ?
		assertFalse(getNaN().isString());
		assertTrue(getStringEmpty().isString());
		assertTrue(getNewStringObject().isString());
		assertTrue(getString().isString());
		assertTrue(getStringWithFalse().isString());
		assertTrue(getStringWithTrue().isString());
		assertFalse(getNull().isString());
		assertFalse(getUndefined().isString());
		assertFalse(getTrue().isString());
		assertFalse(getFalse().isString());
		assertFalse(getNewBooleanTrue().isString());
		assertFalse(getNewBooleanFalse().isString());

		// is function
		assertTrue(getFunction().isFunction());
		assertFalse(getInt().isFunction());
		assertFalse(getNewNumber().isFunction());
		assertFalse(getDecimal().isFunction());
		assertFalse(getInfinityMinus().isFunction());
		assertFalse(getInfinityPlus().isFunction());
		assertFalse(getZero().isFunction());
		assertFalse(getStringWithInt().isFunction());// should we ?
		assertFalse(getNaN().isFunction());
		assertFalse(getStringEmpty().isFunction());
		assertFalse(getString().isFunction());
		assertFalse(getStringWithFalse().isFunction());
		assertFalse(getStringWithTrue().isFunction());
		assertFalse(getNull().isFunction());
		assertFalse(getUndefined().isFunction());
		assertFalse(getTrue().isFunction());
		assertFalse(getFalse().isFunction());
		assertFalse(getNewBooleanTrue().isFunction());
		assertFalse(getNewBooleanFalse().isFunction());

		// casting from number
		Value value = getNewNumber();
		assertEquals(55, (int) value.asInt());
		assertEquals(55, (byte) value.asByte());
		assertEquals(55F, value.asFloat(), 1e-6);
		assertEquals(55, value.asLong());
		assertEquals(55.0, value.asDouble(), 1e-6);
		assertEquals(55, value.asChar());
		assertEquals(55, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("55", value.asString());
		// assertEquals(55D, value.asJsDate().getTime());

		value = getInt();
		assertEquals(5, (int) value.asInt());
		assertEquals(5, (byte) value.asByte());
		assertEquals(5F, value.asFloat(), 1e-6);
		assertEquals(5, value.asLong());
		assertEquals(5.0, value.asDouble(), 1e-6);
		assertEquals(5, value.asChar());
		assertEquals(5, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("5", value.asString());
		// assertEquals(5D, value.asJsDate().getTime());

		value = getZero();
		assertEquals(0, (int) value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertEquals(0F, value.asFloat(), 1e-6);
		assertEquals(0, value.asLong());
		assertEquals(0D, value.asDouble(), 1e-6);
		assertEquals(0, value.asChar());
		assertEquals(0, (short) value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals("0", value.asString());
		// assertEquals(0D, value.asJsDate().getTime());

		value = getNaN();
		assertEquals(null, value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertTrue(Float.isNaN(value.asFloat()));
		assertEquals(0, value.asLong());
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals(0, value.asChar());
		assertEquals(null, value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals("NaN", value.asString());
		// assertTrue(Double.isNaN(value.asJsDate().getTime()));

		value = getInfinityPlus();
		// int n = (int) Double.POSITIVE_INFINITY;
		// assertEquals(n, value.asInt());
		// assertEquals(n, value.asByte());
		assertEquals(Float.POSITIVE_INFINITY, value.asFloat(), 1e-6);
		assertEquals(Long.MAX_VALUE, value.asLong());
		assertEquals(Double.POSITIVE_INFINITY, value.asDouble(), 1e-6);
		// assertEquals(n, value.asChar());
		// assertEquals(n, value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("Infinity", value.asString());
		// assertTrue(Double.isNaN(value.asJsDate().getTime()));

		value = getDecimal();
		int n = (int) value.asDouble();
		assertEquals(n, (int) value.asInt());
		assertEquals(n, (byte) value.asByte());
		assertEquals(12.5F, value.asFloat(), 1e-6);
		assertEquals(12L, value.asLong());
		assertEquals(12.5D, value.asDouble(), 1e-6);
		assertEquals(n, value.asChar());
		assertEquals(n, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("12.5", value.asString());
		// assertEquals(12.0D, value.asJsDate().getTime());

		// casting from null
		value = getNull();
		assertEquals(null, value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertEquals(0F, value.asFloat(), 1e-6);
		assertEquals(0L, value.asLong());
		assertEquals(0D, value.asDouble(), 1e-6);
		assertEquals(0, value.asChar());
		assertEquals(null, value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals(null, value.asString());
		// assertEquals(0D, value.asJsDate().getTime());

		// casting from undefined
		value = getUndefined();
		assertEquals(null, value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertTrue(Float.isNaN(value.asFloat()));
		assertEquals(0L, value.asLong());
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals(0, value.asChar());
		assertEquals(null, value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals(null, value.asString());
		// assertTrue(Double.isNaN(value.asJsDate().getTime()));

		// casting from boolean
		value = getFalse();
		assertEquals(0, (int) value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertEquals(0F, value.asFloat(), 1e-6);
		assertEquals(0L, value.asLong());
		assertEquals(0D, value.asDouble(), 1e-6);
		assertEquals(0, value.asChar());
		assertEquals(0, (short) value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals("false", value.asString());
		// assertEquals(0D, value.asJsDate().getTime());

		value = getNewBooleanFalse();
		assertEquals(0, (int) value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertEquals(0F, value.asFloat(), 1e-6);
		assertEquals(0L, value.asLong());
		assertEquals(0D, value.asDouble(), 1e-6);
		assertEquals(0, value.asChar());
		assertEquals(0, (short) value.asShort());
		assertEquals(false, value.asBoolean());
		assertEquals("false", value.asString());
		// assertEquals(0D, value.asJsDate().getTime());

		value = getTrue();
		assertEquals(1, (int) value.asInt());
		assertEquals(1, (byte) value.asByte());
		assertEquals(1F, value.asFloat(), 1e-6);
		assertEquals(1L, value.asLong());
		assertEquals(1D, value.asDouble(), 1e-6);
		assertEquals(1, value.asChar());
		assertEquals(1, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("true", value.asString());
		// assertEquals(1D, value.asJsDate().getTime());

		value = getNewBooleanTrue();
		assertEquals(1, (int) value.asInt());
		assertEquals(1, (byte) value.asByte());
		assertEquals(1F, value.asFloat(), 1e-6);
		assertEquals(1L, value.asLong());
		assertEquals(1D, value.asDouble(), 1e-6);
		assertEquals(1, value.asChar());
		assertEquals(1, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("true", value.asString());
		// assertEquals(1D, value.asJsDate().getTime());

		// casting from string
		value = getStringWithInt();
		assertEquals(5, (int) value.asInt());
		assertEquals(5, (byte) value.asByte());
		assertEquals(5F, value.asFloat(), 1e-6);
		assertEquals(5, value.asLong());
		assertEquals(5D, value.asDouble(), 1e-6);
		assertEquals(5, value.asChar());
		assertEquals(5, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("5", value.asString());
		// FF: ok, Chrome: ko
		// see : https://code.google.com/p/v8/issues/detail?id=2602
		// assertTrue(Double.isNaN(value.asJsDate().getTime()));

		value = getStringWithDate();

		assertEquals(0, value.asLong());
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals("October 13, 1975 11:13:00", value.asString());

		value = getStringEmpty();
		assertEquals("", value.asString());
		value = getString();
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals("foobar", value.asString());

		value = getStringWithFalse();
		assertEquals(0, (int) value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertTrue(Float.isNaN(value.asFloat()));
		assertEquals(0L, value.asLong());
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals(0, value.asChar());
		assertEquals(0, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("false", value.asString());

		value = getStringWithTrue();
		assertEquals(1, (int) value.asInt());
		assertEquals(0, (byte) value.asByte());
		assertTrue(Float.isNaN(value.asFloat()));
		assertEquals(0L, value.asLong());
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals(0, value.asChar());
		assertEquals(1, (short) value.asShort());
		assertEquals(true, value.asBoolean());
		assertEquals("true", value.asString());

		// casting from function
		value = getFunction();
		assertTrue(Double.isNaN(value.asDouble()));
		assertEquals(0, value.asChar());
		assertEquals(true, value.asBoolean());
		assertTrue(value.asString().contains("function"));

		// object wrapper
		value = Value.create(webEngine, getCoords(10, 12));
		assertTrue(value.getProperty("x").isDefined());
		assertEquals(10, (int) value.getProperty("x").asInt());
		assertEquals(12, (int) value.getProperty("y").asInt());

		Value fake = value.getProperty("fake");

		assertTrue(fake.isUndefined());
		assertFalse(value.getProperty("x").isBoolean());
		assertFalse(value.getProperty("x").isString());
		assertFalse(value.getProperty("x").isFunction());

	}

	private final Coords getCoords(final int x, final int y) {
		return new Coords(webEngine, x, y);
	}

	// private static boolean isFinite(JsDate date) {
	// return isFinite(date);
	// }

	// strings
	private Value getStringWithInt() {
		String expression = "{ datum : '5' };";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getStringEmpty() {
		String expression = " {	datum : ''};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getString() {
		String expression = " {	datum : 'foobar'};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getNewStringObject() {
		String expression = " {	datum : new String('foobar')};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getStringWithTrue() {
		String expression = " {	datum : 'true'};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getStringWithFalse() {
		String expression = " {	datum : 'false'	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getStringWithDate() {
		String expression = " {	datum : \"October 13, 1975 11:13:00\"	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	// function
	private Value getFunction() {
		String expression = " {	datum : function() {	return;	} };";
		Value result = createValueFromExpression(expression);
		return result;
	}

	// numbers
	private Value getInt() {
		String expression = " {	datum : 5};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getZero() {
		String expression = " {	datum : 0};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getDecimal() {
		String expression = " {	datum : 12.5};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getInfinityPlus() {
		String expression = " {	datum : Number.POSITIVE_INFINITY};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getInfinityMinus() {
		String expression = " {	datum : Number.NEGATIVE_INFINITY};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getNaN() {
		String expression = " {	datum : Number.NaN	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getNewNumber() {
		String expression = " {	datum : new Number(55)	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	// null
	private Value getNull() {
		String expression = " {	datum : null	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	// undefined
	private Value getUndefined() {
		String expression = " {	datum : undefined	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	// boolean
	private Value getTrue() {
		String expression = " {	datum : true	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getFalse() {
		String expression = " {	datum : false	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getNewBooleanTrue() {
		String expression = " {	datum : new Boolean(true)	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value getNewBooleanFalse() {
		String expression = " {	datum : new Boolean(false)	};";
		Value result = createValueFromExpression(expression);
		return result;
	}

	private Value createValueFromExpression(String expression) {
		D3 d3 = browser.getD3();

		String command = "var dummy_temp_var = " + expression;
		d3.eval(command);
		JSObject result = (JSObject) d3.eval("dummy_temp_var");
		return new Value(webEngine, result);
	}

	//#end region
}
