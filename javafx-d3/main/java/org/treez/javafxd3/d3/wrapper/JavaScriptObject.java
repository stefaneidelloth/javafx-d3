package org.treez.javafxd3.d3.wrapper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.treez.javafxd3.d3.core.Value;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Base class for all JavaScript wrappers
 *
 */
public class JavaScriptObject {

	//#region ATTRIBUTES

	/**
	 * The JavaFx web engine
	 */
	protected WebEngine webEngine;

	/**
	 * The wrapped JavaScript object
	 */
	private JSObject jsObject;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public JavaScriptObject(WebEngine webEngine) {
		this.webEngine = webEngine;
	}
	
	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public JavaScriptObject(WebEngine webEngine, JSObject wrappedJsObject) {
		this.webEngine = webEngine;
		this.jsObject = wrappedJsObject;
	}

	//#end region

	//#region METHODS
	
	protected void setEmptyArrayAsJsObject() {
		setJsObject(createEmptyArray());		
	}

	protected JSObject createEmptyArray() {
		String dummyName = createNewTemporaryInstanceName();
		webEngine.executeScript("var " +dummyName +"=[];");
		JSObject emptyObject = (JSObject) webEngine.executeScript(dummyName);
		return emptyObject;
	}
	
	protected void setEmptyObjectAsJsObject() {
		setJsObject(createEmptyObject());		
	}

	protected JSObject createEmptyObject() {
		String dummyName = createNewTemporaryInstanceName();
		webEngine.executeScript("var " +dummyName +"={};");
		JSObject emptyObject = (JSObject) webEngine.executeScript(dummyName);
		return emptyObject;
	}

	//#region CALL

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as Boolean
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected Boolean callForBoolean(String methodName, Object... args) {
		Boolean result = (Boolean) jsObject.call(methodName, args);
		return result;
	}

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as Boolean
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected Integer callForInteger(String methodName, Object... args) {
		Object resultObject = jsObject.call(methodName, args);
		boolean isInteger = resultObject instanceof Integer;
		if (isInteger) {
			Integer result = (Integer) resultObject;
			return result;
		}

		boolean isString = resultObject instanceof String;
		if (isString) {
			String stringResult = (String) resultObject;
			Integer integerResult;
			try {
				integerResult = Integer.parseInt(stringResult);
			} catch (NumberFormatException exception) {
				integerResult = null;
			}
			return integerResult;
		}

		String message = "The object type" + resultObject.getClass().getName() + " is not yet implemented";
		throw new IllegalStateException(message);
	}

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as String
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected String callForString(String methodName, Object... args) {
		String result = (String) jsObject.call(methodName, args);
		return result;
	}

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as String
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected String callForString(String methodName, JSObject... args) {

		Object[] objectArray = (Object[]) args;
		Object resultObj = jsObject.call(methodName, objectArray);
		String result = (String) resultObj;
		return result;
	}

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as Double
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected Double callForDouble(String methodName, Object... args) {
		Object result = jsObject.call(methodName, args);
		boolean isDouble = result instanceof Double;
		if (isDouble) {
			Double doubleResult = (Double) result;
			return doubleResult;
		}

		boolean isInteger = result instanceof Integer;
		if (isInteger) {
			Double doubleResult = Double.parseDouble("" + result);
			return doubleResult;
		}

		boolean isString = result instanceof String;
		if (isString) {
			String stringResult = (String) result;
			Double doubleResult;
			try {
				doubleResult = Double.parseDouble(stringResult);
			} catch (NumberFormatException exception) {
				doubleResult = Double.NaN;
			}
			return doubleResult;
		}

		String message = "The object type" + result.getClass().getName() + " is not yet implemented";
		throw new IllegalStateException(message);

	}

	/**
	 * Invokes the method with the given name and arguments and returns the
	 * result as JSObject
	 * 
	 * @param methodName
	 * @param args
	 * @return
	 */
	protected JSObject call(String methodName, Object... args) {
		Object resultObj = jsObject.call(methodName, args);
		
		if (resultObj==null){
			return null;
		}
		
		boolean isJsObject = resultObj instanceof JSObject;
		if (isJsObject) {
			JSObject result = (JSObject) resultObj;
			return result;
		}

		boolean isString = resultObj instanceof String;
		if (isString) {
			String result = (String) resultObj;
			boolean isUndefined = result.equals("undefined");
			if (isUndefined) {
				return null;
			} else {
				String message = "A result of type String with the value " + result + "' could not be processed.";
				throw new IllegalStateException(message);
			}
		}

		String typeString = "null";
		if (resultObj != null) {
			typeString = resultObj.getClass().getName();
		}
		String message = "The result type '" + typeString + "' is not yet implemented for method '" + methodName + "'";
		throw new IllegalStateException(message);
	}

	protected Object callThis(Object... args) {

		JSObject d3JsObj = getD3();
		
		// store args as member and create variable name list
		List<String> varNames = new ArrayList<>();
		List<String> fullVarNames = new ArrayList<>();

		for (int index = 0; index < args.length; index++) {
			String varName = createNewTemporaryInstanceName();
			varNames.add(varName);
			fullVarNames.add("d3." + varName);
			d3JsObj.setMember(varName, args[index]);
		}

		// eval command
		String command = "this(" + String.join(",", fullVarNames) + ");";
		Object result = eval(command);
		
		for(String varName: varNames){
			d3JsObj.removeMember(varName);
		}

		return result;
	}

	protected Integer callThisForInteger(Object... args) {
		Object result = callThis(args);
		return (Integer) result;
	}

	protected Boolean callThisForBoolean(Object... args) {
		Object result = callThis(args);
		return (Boolean) result;
	}

	protected String callThisForString(Object... args) {
		Object result = callThis(args);
		return (String) result;
	}

	protected Double callThisForDouble(Object... args) {
		Object result = callThis(args);
		return (Double) result;
	}

	protected JSObject callThisForJsObject(Object... args) {
		Object result = callThis(args);
		if (result==null){
			return null;
		}
		
		boolean isJsObject = result instanceof JSObject;
		if(isJsObject){
			return (JSObject) result;			
		} else{
			boolean isUndefined = result.equals("undefined");
			if(isUndefined){
				return null;
			} else {
				String message = "Not yet implemented for result " + result.toString();
				throw new IllegalStateException(message);
			}
			
		}
	}

	//#end region

	//#region GET MEMBER

	/**
	 * Gets the member with the given name
	 * 
	 * @param name
	 * @return
	 */
	public JSObject getMember(String name) {
		Object resultObj = jsObject.getMember(name);
		if (resultObj == null) {
			return null;
		}
		if (resultObj.equals("undefined")) {
			return null;
		}
		JSObject result = (JSObject) resultObj;
		return result;
	}

	/**
	 * Gets the member with the given name as Integer
	 * 
	 * @param name
	 * @return
	 */
	protected Integer getMemberForInteger(String name) {
		Integer result = (Integer) jsObject.getMember(name);
		return result;
	}

	/**
	 * Gets the member with the given name as Boolean
	 * 
	 * @param name
	 * @return
	 */
	protected Boolean getMemberForBoolean(String name) {
		Boolean result = (Boolean) jsObject.getMember(name);
		return result;
	}

	/**
	 * Gets the member with the given name as Double
	 * 
	 * @param name
	 * @return
	 */
	protected Double getMemberForDouble(String name) {
		Object resultObj = jsObject.getMember(name);
		boolean isDouble = resultObj instanceof Double;
		if (isDouble) {
			Double result = (Double) resultObj;
			return result;
		}

		boolean isNumber = resultObj instanceof Number;
		if (isNumber) {
			Double result = Double.parseDouble("" + resultObj);
			return result;
		}

		String message = "Could not retrieve value of type " + resultObj.getClass().getName() + " as double.";
		throw new IllegalStateException(message);

	}

	/**
	 * Gets the member with the given name as Float
	 * 
	 * @param name
	 * @return
	 */
	protected Float getMemberForFloat(String name) {
		Float result = (Float) jsObject.getMember(name);
		return result;
	}

	/**
	 * Gets the member with the given name as String
	 * 
	 * @param name
	 * @return
	 */
	protected String getMemberForString(String name) {
		Object resultObj = jsObject.getMember(name);
		if (resultObj == null) {
			return null;
		}
		String result = (String) resultObj;
		return result;
	}

	//#end region
	
	//#region SET MEMBER
	
	/**
	 * Sets a member of the d3 java script object
	 * @param name
	 * @param value
	 */
	public void setMember(String name, Object value) {
		jsObject.setMember(name, value);		
	}
	
	//#end region

	//#region EVAL

	/**
	 * Evaluates the given java script command and returns the result as Object
	 * 
	 * @param command
	 * @return
	 */
	public Object eval(String command) {
		try{
			Object result = jsObject.eval(command);
			return result;
		} catch (Exception exception){
			String message = "Could not eval command " + command;
			throw new IllegalStateException(message, exception);
		}
		
	};

	/**
	 * Evaluates the given java script command and returns the result as Boolean
	 * 
	 * @param command
	 * @return
	 */
	public Boolean evalForBoolean(String command) {
		Boolean result = (Boolean) jsObject.eval(command);
		return result;
	};

	/**
	 * Evaluates the given java script command and returns the result as Integer
	 * 
	 * @param command
	 * @return
	 */
	public Integer evalForInteger(String command) {
		Integer result = (Integer) jsObject.eval(command);
		return result;
	};

	/**
	 * Evaluates the given java script command and returns the result as Double
	 * 
	 * @param command
	 * @return
	 */
	public Double evalForDouble(String command) {
		Double result = (Double) jsObject.eval(command);
		return result;
	};

	/**
	 * Evaluates the given java script command and returns the result as String
	 * 
	 * @param command
	 * @return
	 */
	public String evalForString(String command) {
		Object resultObj = jsObject.eval(command);
		String result = (String) resultObj;
		return result;
	};

	/**
	 * Evaluates the given java script command and returns the result as
	 * JSObject
	 * 
	 * @param command
	 * @return
	 */
	public JSObject evalForJsObject(String command) {
		Object resultObj = jsObject.eval(command);
		JSObject result = (JSObject) resultObj;
		return result;
	};

	//#end region

	//#region CONVERSION

	@SuppressWarnings("unchecked")
	protected <T> T convertObjectTo(Object resultObj, Class<T> classObj) {
		if (resultObj == null) {
			return null;
		}
		
		boolean targetIsString = classObj.equals(String.class);
		if (targetIsString) {	
			T result = (T) convertToString(resultObj);
			return result;
		}

		boolean targetIsValue = classObj.equals(Value.class);
		if (targetIsValue) {
			T result = (T) convertToValue(resultObj);
			return result;
		}

		boolean targetIsJavaScriptObject = JavaScriptObject.class.isAssignableFrom(classObj);
		if (targetIsJavaScriptObject) {
			T result = convertToJavaScriptObject(resultObj, classObj);
			return result;
		}

		try {
			T result = classObj.cast(resultObj);
			return result;
		} catch (Exception exception) {
			boolean isNumber = resultObj instanceof Number;
			if (isNumber) {
				T result = tryToCastFromDoubleValue(resultObj, classObj, exception);
				return result;
			}

			String message = "Could not cast item of type '" + resultObj.getClass().getName() + "' to required type '"
					+ classObj.getName() + "'";
			throw new IllegalStateException(message, exception);
		}
	}

	private String convertToString(Object source) {
		try {
			String stringResult = source.toString();
			String result = String.class.cast(stringResult);
			return result;
		} catch (Exception exception) {
			String message = "Could not convert item of type " + source.getClass().getName() + " to String.";
			throw new IllegalStateException(message, exception);
		}
	}	

	private Value convertToValue(Object resultObj) {
		boolean resultObjIsValue = resultObj instanceof Value;
		if (resultObjIsValue) {
			Value result = Value.class.cast(resultObj);
			return result;
		} else {
			Value value = Value.create(webEngine, resultObj);			
			return value;
		}
	}	
	
	public <T> T convertToJavaScriptObject(Object resultObj, Class<T> classObj) {		
		Constructor<T> constructor = createConstructorForJavaScriptObject(resultObj, classObj);
		T newJavaScriptObject = tryToCreateNewInstance(resultObj, classObj, constructor);		
		return newJavaScriptObject;
	}

	private <T> T tryToCreateNewInstance(Object resultObj, Class<T> classObj, Constructor<T> constructor) {
		T newJavaScriptObject;
		try {
			newJavaScriptObject = constructor.newInstance(webEngine, resultObj);
		} catch (Exception exception) {
			String message = "Could not construct new instance of type '" + classObj.getName() + "' with "
					+ "object of type " + resultObj.getClass().getName();
			throw new IllegalStateException(message, exception);
		}
		return newJavaScriptObject;
	}

	private <T> Constructor<T> createConstructorForJavaScriptObject(Object resultObj, Class<T> classObj) {
		
		Class<?> resultObjClass = resultObj.getClass();
		
		Constructor<T> constructor;

		boolean resultIsJsObject = resultObj instanceof JSObject;
		if (resultIsJsObject) {
			try {
				constructor = classObj.getConstructor(new Class<?>[] { WebEngine.class, JSObject.class });
			} catch (Exception exception) {
				String message = "Could not get constructor for JavaScriptObject of " + "type '"
						+ classObj.getName() + "' with parameters of type WebEngine and '"
						+ resultObjClass.getName() + "'.";
				throw new IllegalStateException(message, exception);
			}

		} else {
			try {
				constructor = classObj.getConstructor(new Class<?>[] { WebEngine.class, resultObjClass });
			} catch (Exception exception) {
				String message = "Could not get constructor for JavaScriptObject of " + "type '"
						+ classObj.getName() + "' with parameters of type WebEngine and '"
						+ resultObjClass.getName() + "'.";
				throw new IllegalStateException(message, exception);
			}
		}
		return constructor;
	}
			
	private <T> T tryToCastFromDoubleValue(Object resultObj, Class<T> classObj, Exception exception) {
		Number number = (Number) resultObj;
		Object doubleValue = number.doubleValue();
		try {
			T result = classObj.cast(doubleValue);
			return result;
		} catch (Exception numberCastException) {
			String message = "Could not cast item of type " + resultObj.getClass().getName()
					+ " to required type " + classObj.getName();
			throw new IllegalStateException(message, exception);
		}
	}

	//#end region
	
	//#region EQUALS
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jsObject == null) ? 0 : jsObject.hashCode());
		result = prime * result + ((webEngine == null) ? 0 : webEngine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaScriptObject other = (JavaScriptObject) obj;
		if (jsObject == null) {
			if (other.jsObject != null)
				return false;
		} else if (!jsObject.equals(other.jsObject))
			return false;
		if (webEngine == null) {
			if (other.webEngine != null)
				return false;
		} else if (!webEngine.equals(other.webEngine))
			return false;
		return true;
	}
	
	//#end region
	
	//#region UTILS
	
	/**
	 * Creates a unique callback name (includes the current time in ms and a random number)
	 * @return
	 */
	protected static String createNewTemporaryInstanceName() {
		double random = Math.random(); 
		String randomString = ("" + random).substring(2,5);
		String name =  "temp__instance__" + System.currentTimeMillis() + "_" + randomString;
		return name;
	}
	
	
	protected JSObject getD3() {
		JSObject d3jsObj = (JSObject) webEngine.executeScript("d3");
		return d3jsObj;
	}
	
	protected void assertObjectIsNotAnonymous(final Object callback) {
		Objects.requireNonNull(callback, "Callback must not be null");
		Class<?> callbackClass = callback.getClass();
		boolean isAnonymeous = callbackClass.isAnonymousClass();
		if (isAnonymeous) {
			String message = "Anonymous callback implementations are not supported. "
					+ "Please create a named class for '" + callbackClass.getName() + "'";
			throw new IllegalArgumentException(message);
		}
	}
	
	//#end region

	//#end region

	//#region ACCESSORS

	/**
	 * Returns the wrapped JSObject
	 * 
	 * @return
	 */
	public JSObject getJsObject() {
		return jsObject;
	}

	/**
	 * Sets the wrapped JSObject
	 * 
	 * @param wrappedJsObject
	 */
	public void setJsObject(JSObject wrappedJsObject) {
		this.jsObject = wrappedJsObject;
	}

	/**
	 * @return
	 */
	public WebEngine getWebEngine() {
		return webEngine;
	}

	//#end region

}
