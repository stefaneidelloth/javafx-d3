package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * <p>
 * Scales are functions that map from an input domain to an output range. There are 2 kinds of scales:
 * <ul>
 * <li>Quantitative Scales - for continuous input domains, such as numbers.
 * <li>Ordinal Scales - for discrete input domains, such as names or categories.
 * </ul>
 * <p>
 * Scales are an optional feature in D3; you don't have to use them, if you prefer to do the math yourself. However,
 * using scales can greatly simplify the code needed to map a dimension of data to a visual representation.
 * <p>
 *
 * @param <S>
 *            the subclass of scale
 * 
 */
public abstract class Scale<S extends Scale<?>> extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 */
	public Scale(JsEngine engine) {
		super(engine);
	}
	
	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject 
	 */
	public Scale(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS
	
	 /**
	  * Abstract factory method
	 * @param engine
	 * @param result
	 * @return
	 */
	public abstract S createScale(JsEngine engine, JsObject result);

    // ==================== domain ====================

    /**
     * Sets the scale's input domain to the specified array of numbers.
     * <p>
     *
     * @param numbers
     *            the array of numbers
     * @return the current scale
     */
    public final S domain(final double... numbers) {    	    	
    	String arrayString = ArrayUtils.createArrayString(numbers);    	
    	String command = "this.domain("+ arrayString +")";    	
    	JsObject result = evalForJsObject(command);    	
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;    	
    }  
   
	/**
     * Sets the scale's input domain to the specified array of strings.
     * <p>
     *
     * @param strings
     *            the array of strings
     * @return the current scale
     */
    public final S domain(final String... strings) {
    	String arrayString = ArrayUtils.createArrayString(strings);    	
    	String command = "this.domain("+ arrayString +")";    	
    	JsObject result = evalForJsObject(command);    	
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }

    /**
     * Sets the scale's input domain to the specified java script object.
     * <p>
     *
     * @param object              
     * @return the current scale
     */
    public  S domain(JavaScriptObject object){
    	
    	boolean isArray = object instanceof Array;
    	if(isArray){
    		Array<?> array = (Array<?>) object;
    		return domainWithArray(array);
    	}
    	
    	JsObject jsObject = object.getJsObject();    	
    	JsObject result = call("domain", jsObject);
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }

	private S domainWithArray(Array<?> array) {
		
		Object min = array.get(0, Object.class);
		
		Object max = array.get(1,  Object.class);
		
		String minName = createNewTemporaryInstanceName();
		String maxName = createNewTemporaryInstanceName();

		JsObject d3jsObj = getD3();
		d3jsObj.setMember(minName, min);
		d3jsObj.setMember(maxName, max);
		    		
		JsObject result = evalForJsObject("this.domain([d3."+ minName + ", d3." + maxName + "])");
		
		d3jsObj.removeMember(minName);
		d3jsObj.removeMember(maxName);
		
		if(result==null){
			return null;
		}
		
		S scaleResult = createScale(engine, result);   
		return scaleResult;
	}

    /**
     * Returns the current scale's input domain.
     * <p>
     *
     * @return the current domain
     */
    public  <T> Array<T> domain(){
    	JsObject result = call("domain");
    	if(result==null){
    		return null;
    	}
    	return new Array<>(engine, result);
    }

    // ==================== range ====================

    /**
     * Set the scale's output range.
     * <p>
     * Scale subclasses may interpret the output range differently. Please refer to subclass documentation.
     *
     * @param numbers
     *            the array of values.
     * @return the current scale for chaining
     */
    public final S range(final double... numbers) {
    	String arrayString = ArrayUtils.createArrayString(numbers);
    	String command = "this.range(" + arrayString +")";
    	JsObject result = evalForJsObject(command);
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }
    
   
    /**
     * Set the scale's output range.
     * <p>
     * Scale subclasses may interpret the output range differently. Please refer to subclass documentation.
     *
     * @param strings
     *            the array of values.
     * @return the current scale for chaining
     */
    public final S range(final String... strings) {
    	String arrayString = ArrayUtils.createArrayString(strings);
    	String command = "this.range(" + arrayString +")";
    	JsObject result = evalForJsObject(command);
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }

    /**
     * Set the scale's output range.
     * <p>
     * Scale subclasses may interpret the output range differently. Please refer to subclass documentation.
     * @param jsObject 
     *
     * @return the current scale for chaining
     */
    public  S range(JsObject jsObject){
    	JsObject result = call("range", jsObject);
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }

    /**
     * Return the current output range of this scale.
     * <p>
     *
     * @return the current output range
     */
    public  <T> Array<T> range(){
    	JsObject result = call("range");
    	return new Array<T>(engine, result);    	
    }

    // ==================== copy ====================

    /**
     * Returns an exact copy of this scale.
     * <p>
     * Changes to this scale will not affect the returned scale, and vice versa.
     *
     * @return the copy
     */
    public  final S copy(){
    	JsObject jsObject = getJsObject();
    	JsObject result = call("copy", jsObject);
    	S scaleResult = createScale(engine, result);   
    	return scaleResult;
    }

    // ==================== apply ====================
    /**
     * Given a value x in the input domain, returns the corresponding value in
     * the output range.
     * <p>
     * Note: some {@link Interpolator}s reuse return values. For example, if the domain values are arbitrary objects,
     * then {@link D3#interpolateObject(JavaScriptObject, JavaScriptObject)} is automatically applied and the scale
     * reuses the returned object.
     * <p>
     * Often, the return value of a scale is immediately used to set an attribute or style, and you don’t have to worry
     * about this; however, if you need to store the scale’s return value, use string coercion or create a copy as
     * appropriate.
     * <p>
     *
     * @param d
     *            the input value
     * @return the output value
     */
    public  Value apply(JsObject d){    	
    	Object result = callThis(d);
    	if(result==null){
    		return null;
    	}    	
    	boolean isJsObject = result instanceof JsObject;
    	if(isJsObject){
    		JsObject jsResult = (JsObject) result;
    		return new Value(engine, jsResult);	
    	} else {
    		return Value.create(engine, result);
    	}       		
    }
    
    public  Value apply(JavaScriptObject d){
    	return apply(d.getJsObject());    			
    }

    /**
     * Given a value x in the input domain, returns the corresponding value in
     * the output range.
     * <p>
     * Note: some {@link Interpolator}s reuse return values. For example, if the domain values are arbitrary objects,
     * then {@link D3#interpolateObject(JavaScriptObject, JavaScriptObject)} is automatically applied and the scale
     * reuses the returned object.
     * <p>
     * Often, the return value of a scale is immediately used to set an attribute or style, and you don’t have to worry
     * about this; however, if you need to store the scale’s return value, use string coercion or create a copy as
     * appropriate.
     * <p>
     *
     * @param d
     *            the input value
     * @return the output value
     */
    public  Value apply(double d){    	
    	String command = "this(" + d + ")";
    	Object result = eval(command);
    	
    	if (result == null){
    		return null;
    	}
    	
    	Value value = Value.create(engine, result);
    	
    	return value;
    }

    /**
     * Given a value x in the input domain, returns the corresponding value in
     * the output range.
     * <p>
     * Note: some {@link Interpolator}s reuse return values. For example, if the domain values are arbitrary objects,
     * then {@link D3#interpolateObject(JavaScriptObject, JavaScriptObject)} is automatically applied and the scale
     * reuses the returned object.
     * <p>
     * Often, the return value of a scale is immediately used to set an attribute or style, and you don’t have to worry
     * about this; however, if you need to store the scale’s return value, use string coercion or create a copy as
     * appropriate.
     * <p>
     *
     * @param d
     *            the input value
     * @return the output value
     */
    public  Value apply(String d){
    	String command = "this('" + d + "')";
    	Object result = eval(command);
    	
    	Value value = Value.create(engine,  result);
    	return value;    	
    }
    
    public  Value apply(Enum<?> enumValue){
    	String stringValue = enumValue.toString();
    	return apply(stringValue);    			
    }
    
    public  Double applyForDouble(String d){
    	String command = "this('" + d + "')";
    	Object result = eval(command);  
    	String valueString = result.toString();
    	boolean isUndefined = valueString.equals("undefined");
    	if(isUndefined){
    		return null;
    	}
    	Double value = Double.parseDouble(valueString);
    	return value;
    	
    }
    
    public  String applyForString(String d){
    	String command = "this('" + d + "')";
    	Object result = eval(command);    	
    	return result.toString();    	
    }
}
