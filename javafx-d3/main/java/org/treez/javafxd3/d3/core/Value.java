package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

/**
 * A {@link Value} is an object wrapping a primitive or a complex value.
 * <p>
 * A {@link Value} is returned from many gwt-d3 functions.
 * <p>
 * The wrapped value can be retrieved from the numerous as*() methods.
 * <p>
 */
public class Value extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 */
	protected Value(JsEngine engine) {
		super(engine);
	}

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Value(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Wraps the given JavaScriptObject into a {@link Value}.
	 * 
	 * @param engine
	 * @param object
	 *            the object to be wrapped as a Value
	 * @return the value
	 */
	public static Value create(JsEngine engine, JavaScriptObject javaScriptObject) {

		Object wrappedObject = null;
		if (javaScriptObject != null) {
			wrappedObject = javaScriptObject.getJsObject();
		}

		String dummyTempAttributeName = createNewTemporaryInstanceName();
		String dummyTempVariableName = createNewTemporaryInstanceName();

		JsObject d3 = (JsObject) engine.executeScript("d3");

		d3.setMember(dummyTempAttributeName, wrappedObject);

		String command = "var " + dummyTempVariableName + " = { datum: d3." + dummyTempAttributeName + "};";
		d3.eval(command);
		JsObject result = (JsObject) d3.eval(dummyTempVariableName);

		d3.removeMember(dummyTempAttributeName);
		d3.eval(dummyTempVariableName + "= undefined;");

		return new Value(engine, result);

	}

	/**
	 * Wraps the given JavaScriptObject into a {@link Value}.
	 * 
	 * @param engine
	 * @param object
	 *            the object to be wrapped as a Value
	 * @return the value
	 */
	public static Value create(JsEngine engine, Object object) {

		String dummyTempAttributeName = "dummy__object__attr";
		String dummyTempVariableName = "dummy_temp_var";

		JsObject d3 = (JsObject) engine.executeScript("d3");

		d3.setMember(dummyTempAttributeName, object);

		String command = "var " + dummyTempVariableName + " = { datum: d3." + dummyTempAttributeName + "};";
		d3.eval(command);
		JsObject result = (JsObject) d3.eval(dummyTempVariableName);

		d3.removeMember(dummyTempAttributeName);

		return new Value(engine, result);

	}

	/**
	 * Creates a Value that wraps 'undefined'
	 * 
	 * @param engine
	 * @param object
	 *            the object to be wrapped as a Value
	 * @return the value
	 */
	public static Value createUndefined(JsEngine engine) {

		String dummyTempVariableName = "dummy_temp_var";

		JsObject d3 = (JsObject) engine.executeScript("d3");

		String command = "var " + dummyTempVariableName + " = { datum: undefined};";
		d3.eval(command);
		JsObject result = (JsObject) d3.eval(dummyTempVariableName);

		return new Value(engine, result);

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
		JsObject jsObject = object.getJsObject();
		Object datum = jsObject.eval("this['" + propertyName + "']");
		JsEngine engine = object.getJsEngine();
		Value value = create(engine, datum);
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
	public Byte asByte() {
		String command = "~~this.datum;";
		Object result = eval(command);

		boolean isUndefined = result.equals("undefined");
		if (isUndefined) {
			return null;
		}

		int intResult = (int) result;
		return (byte) intResult;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public char asChar() {
		String command = "~~this.datum;";
		Object resultObj = eval(command);
		int intResult = (int) resultObj;
		char result = Character.toChars(intResult)[0];
		return result;
	}

	public JsDate asJsDate() {
		JsObject result = getMember("datum");
		if (result == null) {
			return null;
		}
		return new JsDate(engine, result);
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public Double asDouble() {
		String command = "this.datum-0;";
		Object resultObj = eval(command);
		return ConversionUtil.convertObjectTo(resultObj, Double.class, engine);
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public float asFloat() {
		String command = "this.datum-0;";
		Object resultObj = eval(command);
		return ConversionUtil.convertObjectTo(resultObj, Float.class, engine);
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public Integer asInt() {
		String command = "this.datum;";
		Object resultObj = eval(command);
		return ConversionUtil.convertObjectTo(resultObj, Integer.class, engine);
	}

	/**
	 * Cast and return the value.
	 * <p>
	 *
	 * @return the value
	 */
	public final long asLong() {
		long result = (long) (double) asDouble();
		return result;
	}

	/**
	 * Cast and return the wrapped value.
	 * <p>
	 *
	 * @return the value
	 */
	public Short asShort() {
		String command = "this.datum";
		Object resultObj = eval(command);
		return ConversionUtil.convertObjectTo(resultObj, Short.class, engine);
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
		Object resultObj = eval(command);
		return ConversionUtil.convertObjectTo(resultObj, Coords.class, engine);
	}

	/**
	 * Cast and return the wrapped value, if possible. (Does not work for
	 * "conversion" of JsObject to JavaScriptObject)
	 *
	 * @throws ClassCastException
	 *             if the value cannot be converted in T
	 *
	 * @return the value
	 */

	public <T> T as() {
		String command = "this.datum";
		Object result = eval(command);
		try {
			@SuppressWarnings("unchecked")
			T castedResult = (T) result;
			return castedResult;
		} catch (ClassCastException exception) {
			String message = "Could not directly cast the value. Please try to use method as(Class<?> class) instead of as(). ";
			throw new IllegalStateException(message, exception);
		}
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
		String command = "this.datum";
		Object resultObj = eval(command);
		boolean isUndefined = resultObj.equals("undefined");
		return isUndefined;
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

		String command = "this.datum." + propertyName + ";";
		Object datum = eval(command);
		boolean isUndefined = datum.equals("undefined");
		if (isUndefined) {
			Value value = createUndefined(engine);
			return value;
		} else {
			Value value = create(engine, datum);
			return value;
		}
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

	//#end region
}