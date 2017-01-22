package org.treez.javafxd3.d3.core;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import netscape.javascript.JSException;
import org.treez.javafxd3.d3.core.JsObject;

public class ConversionUtil {

	@SuppressWarnings("unchecked")
	public synchronized static <T> T convertObjectTo(Object argumentObj, Class<T> classObj, JsEngine engine) {

		if (argumentObj == null) {
			return null;
		}
		
		Object object = engine.toJsObjectIfNotSimpleType(argumentObj);
		

		boolean isUndefined = object.equals("undefined");
		if (isUndefined) {
			return null;
		}
		
		boolean objectAlreadyHasWantedType = object.getClass().equals(classObj);
		if (objectAlreadyHasWantedType) {
			T convertedResult = (T) object;
			return convertedResult;
		}
		
		boolean targetIsValue = classObj.equals(Value.class);
		if (targetIsValue) {
			T result = (T) convertToValue(object, engine);
			return result;
		}

		Object resultObj = extractDataIfObjectIsWrapper(object);
		if (resultObj == null) {
			return null;
		}

		boolean alreadyHasWantedType = resultObj.getClass().equals(classObj);
		if (alreadyHasWantedType) {
			T convertedResult = (T) resultObj;
			return convertedResult;
		}

		boolean targetIsString = classObj.equals(String.class);
		if (targetIsString) {
			T result = (T) convertToString(resultObj);
			return result;
		}

		boolean targetIsDouble = classObj.equals(Double.class);
		if (targetIsDouble) {
			T result = (T) convertToDouble(resultObj);
			return result;
		}

		boolean targetIsFloat = classObj.equals(Float.class);
		if (targetIsFloat) {
			T result = (T) convertToFloat(resultObj);
			return result;
		}

		boolean targetIsInteger = classObj.equals(Integer.class);
		if (targetIsInteger) {
			T result = (T) convertToInteger(resultObj);
			return result;
		}

		boolean targetIsShort = classObj.equals(Short.class);
		if (targetIsShort) {
			T result = (T) convertToShort(resultObj);
			return result;
		}

		boolean targetIsCharacter = classObj.equals(Character.class);
		if (targetIsCharacter) {
			T result = (T) convertToCharacter(resultObj);
			return result;
		}		

		boolean targetIsJavaScriptObject = JavaScriptObject.class.isAssignableFrom(classObj);
		if (targetIsJavaScriptObject) {
			T result = convertToJavaScriptObject(resultObj, classObj, engine);
			return result;
		}

		try {
			T result = classObj.cast(resultObj);
			return result;
		} catch (ClassCastException exception) {
			boolean isNumber = resultObj instanceof Number;
			if (isNumber) {
				T result = tryToCastFromDoubleValue(resultObj, classObj, exception);
				return result;
			}

			String message = "Could not convert item of type '" + resultObj.getClass().getName()
					+ "' to required type '" + classObj.getName() + "'";
			throw new IllegalStateException(message, exception);
		}
	}

	private static Character convertToCharacter(Object resultObj) {

		boolean isInteger = resultObj instanceof Integer;
		if (isInteger) {
			int intValue = (Integer) resultObj;
			return (char) intValue;
		}

		boolean isString = resultObj instanceof String;
		if (isString) {
			String stringValue = (String) resultObj;
			return stringValue.charAt(0);
		}

		String message = "Could not convert item of type '" + resultObj.getClass().getName() + "' to Character.";
		throw new IllegalStateException(message);

	}

	private static <T> Object extractDataIfObjectIsWrapper(Object resultObj) {
		Object result = resultObj;
		boolean isWrappingDatumObject = isJsDataObject(resultObj);
		if (isWrappingDatumObject) {
			result = extractDatum(resultObj);
		}
		return result;
	}

	private static Short convertToShort(Object resultObj) {

		String resultString = resultObj.toString();
		boolean isNaN = resultString.equals("NaN");
		if (isNaN) {
			return null;
		}

		boolean isUndefined = resultString.equals("undefined");
		if (isUndefined) {
			return null;
		}

		boolean isFalse = resultString.equals("false");
		if (isFalse) {
			return 0;
		}

		boolean isTrue = resultString.equals("true");
		if (isTrue) {
			return 1;
		}

		try {
			short result = Short.parseShort("" + resultObj);
			return result;
		} catch (Exception excepiton) {
			double doubleResult = Double.parseDouble("" + resultObj);
			short result = (short) doubleResult;
			return result;
		}
	}

	private static Integer convertToInteger(Object resultObj) {

		String resultString = resultObj.toString();
		boolean isNaN = resultString.equals("NaN");
		if (isNaN) {
			return null;
		}

		boolean isUndefined = resultString.equals("undefined");
		if (isUndefined) {
			return null;
		}

		boolean isFalse = resultString.equals("false");
		if (isFalse) {
			return 0;
		}

		boolean isTrue = resultString.equals("true");
		if (isTrue) {
			return 1;
		}

		try {
			int integerResult = Integer.parseInt(resultString);
			return integerResult;
		} catch (Exception exception) {
			double doubleResult = Double.parseDouble(resultString);

			if (doubleResult > Integer.MAX_VALUE) {
				String message = "The value " + doubleResult + " exceeds the maximum integer value " + Integer.MAX_VALUE
						+ " and can not be returned as integer.";
				throw new IllegalStateException(message);
			}

			if (doubleResult < Integer.MIN_VALUE) {
				String message = "The value " + doubleResult + " exceeds the minimum integer value " + Integer.MIN_VALUE
						+ " and can not be returned as integer.";
				throw new IllegalStateException(message);
			}

			int intResult = (int) doubleResult;
			return intResult;
		}
	}

	private static Float convertToFloat(Object resultObj) {

		boolean isNumber = resultObj instanceof Number;
		if (isNumber) {
			Float result = Float.parseFloat("" + resultObj);
			return result;
		}

		String message = "Could not convert result of type " + resultObj.getClass().getName() + " to float.";
		throw new IllegalStateException(message);
	}

	private static Double convertToDouble(Object resultObj) {

		boolean isNumber = resultObj instanceof Number;
		if (isNumber) {
			Double result = Double.parseDouble("" + resultObj);
			return result;
		}

		String message = "Could not convert result of type " + resultObj.getClass().getName() + " to Double.";
		throw new IllegalStateException(message);
	}

	public static String convertToString(Object source) {
		try {
			String stringResult = source.toString();
			String result = String.class.cast(stringResult);
			return result;
		} catch (Exception exception) {
			String message = "Could not convert item of type " + source.getClass().getName() + " to String.";
			throw new IllegalStateException(message, exception);
		}
	}

	public static Value convertToValue(Object resultObj, JsEngine engine) {

		boolean isJsObject = resultObj instanceof JsObject;
		if (isJsObject) {
			JsObject jsValue = (JsObject) resultObj;			
			Value value = new Value(engine, jsValue);
			return value;
		} else {
			Value value = Value.create(engine, resultObj);
			return value;
		}
	}

	public static <T> T convertToJavaScriptObject(Object resultObj, Class<T> classObj, JsEngine engine) {

		Constructor<T> constructor = createConstructorForJavaScriptObject(resultObj, classObj);
		T newJavaScriptObject = tryToCreateNewInstance(resultObj, classObj, constructor, engine);
		return newJavaScriptObject;
	}

	private static Object extractDatum(Object resultObj) {
		JsObject jsObject = (JsObject) resultObj;
		return jsObject.eval("this.datum");
	}

	private static boolean isJsDataObject(Object resultObj) {

		boolean isJsObject = resultObj instanceof JsObject;
		if (!isJsObject) {
			return false;
		}

		JsObject jsObject = (JsObject) resultObj;

		try {
			String command = "this.datum != undefined && Object.keys(this).length ==1";
			return (boolean) jsObject.eval(command);
		} catch (JSException exception) {
			return false;
		}

	}

	public static <T> T tryToCreateNewInstance(Object resultObj, Class<T> classObj, Constructor<T> constructor,
			JsEngine engine) {
		T newJavaScriptObject;
		try {
			newJavaScriptObject = constructor.newInstance(engine, resultObj);
		} catch (Exception exception) {
			String message = "Could not construct new instance of type '" + classObj.getName() + "' with "
					+ "object of type " + resultObj.getClass().getName();
			throw new IllegalStateException(message, exception);
		}
		return newJavaScriptObject;
	}

	private static <T> Constructor<T> createConstructorForJavaScriptObject(Object resultObj, Class<T> classObj) {

		Class<?> resultObjClass = resultObj.getClass();

		Constructor<T> constructor;

		boolean resultIsJsObject = resultObj instanceof JsObject;
		if (resultIsJsObject) {
			try {
				constructor = classObj.getConstructor(new Class<?>[] { JsEngine.class, JsObject.class });
			} catch (Exception exception) {
				String message = "Could not get constructor for JavaScriptObject of " + "type '" + classObj.getName()
						+ "' with parameters of type JsEngine and '" + resultObjClass.getName() + "'.";
				throw new IllegalStateException(message, exception);
			}

		} else {
			try {
				constructor = classObj.getConstructor(new Class<?>[] { JsEngine.class, resultObjClass });
			} catch (Exception exception) {
				String message = "Could not get constructor for JavaScriptObject of " + "type '" + classObj.getName()
						+ "' with parameters of type JsEngine and '" + resultObjClass.getName() + "'.";
				throw new IllegalStateException(message, exception);
			}
		}
		return constructor;
	}

	public static <T> T tryToCastFromDoubleValue(Object resultObj, Class<T> classObj, Exception exception) {
		Number number = (Number) resultObj;
		Object doubleValue = number.doubleValue();
		try {
			T result = classObj.cast(doubleValue);
			return result;
		} catch (Exception numberCastException) {
			String message = "Could not cast item of type " + resultObj.getClass().getName() + " to required type "
					+ classObj.getName();
			throw new IllegalStateException(message, exception);
		}
	}

	public static JsDate createJsDate(Date date, JsEngine engine) {
		long time = date.getTime();
		return JsDate.create(engine, time);
	}

	public static JsObject createJsObject(String objectCommand, JsEngine engine) {

		D3 d3 = new D3(engine);

		String command = "this.temp_obj_var=" + objectCommand + ";";
		d3.eval(command);
		JsObject result = d3.evalForJsObject("this.temp_obj_var");

		d3.eval("this.temp_obj_var=undefined");

		return result;

	}

}
