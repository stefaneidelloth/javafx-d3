package com.github.javafxd3.api.core;

import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.time.JsDate;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A {@link Value} is an object wrapping a primitive or a complex value.
 * <p>
 * A {@link Value} is returned from many gwt-d3 functions.
 * <p>
 * The wrapped value can be retrieved from the numerous as*() methods.
 * <p>
 */
public class Value extends JavaScriptObject {

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	protected Value(WebEngine webEngine) {
		super(webEngine);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Value(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	// #end region

	// #region METHODS

	/**
	 * Wraps the given object into a {@link Value}.
	 * 
	 * @param webEngine
	 * @param object
	 *            the object to be wrapped as a Value
	 * @return the value
	 */
	public static Value create(WebEngine webEngine, Object object) {

		String dummyTempAttributeName = "dummy__object__attr";
		String dummyTempVariableName = "dummy_temp_var";

		JSObject d3 = (JSObject) webEngine.executeScript("d3");

		d3.setMember(dummyTempAttributeName, object);

		String command = "var " + dummyTempVariableName + " = { datum: d3.dummy_object_attr};";
		d3.eval(command);
		JSObject result = (JSObject) d3.eval(dummyTempVariableName);

		d3.removeMember(dummyTempAttributeName);

		return new Value(webEngine, result);

	}

	/**
	 * Create a {@link Value} instance from the value of the named property of
	 * the given object.
	 *
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static Value create(JavaScriptObject object, String propertyName) {
		JSObject jsObject = object.getJsObject();
		Object datum = jsObject.eval("this[" + propertyName + "]");
		WebEngine webEngine = object.getWebEngine();
		Value value = create(webEngine, datum);
		return value;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public boolean asBoolean() {
		String command = "this.datum instanceof Boolean ? this.datum.valueOf() : !!this.datum;";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public byte asByte() {
		String command = "~~this.datum;";
		Object result = eval(command);
		return (byte) result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public char asChar() {
		String command = "~~this.datum;";
		Object result = eval(command);
		return (char) result;
	}

	public JsDate asJsDate(){
		
		// return this.datum instanceof JsDate ? this.datum : new JsDate(this.datum);		
		JSObject result = getMember("datum");
		return new JsDate(webEngine, result);			  
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public double asDouble() {
		String command = "this.datum-0;";
		Object result = eval(command);
		return (double) result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public float asFloat() {
		String command = "this.datum-0;";
		Object result = eval(command);
		return (float) result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public int asInt() {
		String command = "this.datum;";
		Object result = eval(command);
		String resultString = result.toString();
		int integerResult = Integer.parseInt(resultString);
		return integerResult;
	}

	/**
	 * Cast and return the value.
	 * <p>
	 *
	 * @return the value
	 */
	public final long asLong() {
		String command = "(long) asDouble();";
		Object result = eval(command);
		return (long) result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public short asShort() {
		String command = "this.datum";
		Object result = eval(command);
		return (short) result;
	}

	/**
	 * Return the value casted to a String.
	 *
	 * @return the value
	 */
	public String asString() {
		String command = "this.datum == null ? null : '' + this.datum;";
		String result = evalForString(command);
		return result;
	}

	/**
	 * Return the value casted to a {@link Coords} object.
	 * <p>
	 *
	 * @return
	 */
	public Coords asCoords() {
		String command = "this.datum";
		JSObject result = evalForJsObject(command);
		return new Coords(webEngine, result);
	}

	/**
	 * Cast and return the wrapped value, if possible.
	 * <p>
	 *
	 * @throws ClassCastException
	 *             if the value cannot be converted in T
	 *
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	public <T> T as() {
		String command = "this.datum";
		Object result = eval(command);
		return (T) result;
	}

	/**
	 * Cast and return the wrapped value.
	 *
	 * @param clazz
	 *            the clazz to cast to
	 * @return the casted instance
	 */
	public final <T> T as(final Class<T> clazz) {
		String command = "this.datum";
		Object result = eval(command);
		return clazz.cast(result);
	}

	/**
	 *
	 * @return true if the value is not undefined in the Javascript sense
	 *
	 */
	public boolean isDefined() {
		String command = "typeof (this.datum) != \"undefined\";";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * @return true if the value is undefined in the Javascript sense
	 */
	public boolean isUndefined() {
		String command = "typeof (this.datum) == \"undefined\";";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * @return
	 */
	public boolean isNull() {
		String command = "this.datum === null;";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * @return
	 */
	public boolean isString() {
		String command = "(typeof this.datum == 'string' || this.datum instanceof String);";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * @return
	 */
	public boolean isFunction() {
		String command = "typeof (this.datum) == 'function';";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * @return
	 */
	public boolean isBoolean() {
		String command = "this.datum == true || this.datum == false;";
		Boolean result = evalForBoolean(command);
		return result;
	}

	/**
	 * Return the property of this object as a {@link Value}.
	 * <p>
	 * This method result is never non-null. The returned value may then be
	 * tested for nullity (with {@link #isNull()}) or for undefinition (with
	 * {@link #isUndefined()}).
	 * <p>
	 *
	 * @param propertyName
	 *            the name of the property to get
	 * @return the property value as a value.
	 */
	public Value getProperty(String propertyName) {
		String command = "this.datum[" + propertyName + "];";
		Object datum = eval(command);
		Value value = create(webEngine, datum);
		return value;
	}

	/**
	 * The result of the typeof operator.
	 * <p>
	 *
	 * @return the String returned by a call to typeof
	 */
	public String typeof() {
		String command = "typeof this.datum";
		String result = evalForString(command);
		return result;
	}

	// #end region
}