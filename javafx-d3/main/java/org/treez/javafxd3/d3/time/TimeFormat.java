package org.treez.javafxd3.d3.time;



import java.util.Date;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class TimeFormat extends JavaScriptObject {

	//#region CONSTUCTORS
    /**
     * Constructor
     * @param engine
     * @param wrappedJsObject
     */
    public TimeFormat(JsEngine engine, JsObject wrappedJsObject){
    	super(engine);
    	setJsObject(wrappedJsObject);
    }

    //#end region
    
    //#region METHODS   

    /**
     * Parse a string into a date.
     * <p>
     * Parses the specified string, returning the corresponding date object. If the parsing fails, returns null. Unlike
     * "natural language" date parsers (including JavaScript's built-in parse), this method is strict: if the specified
     * string does not exactly match the associated format specifier, this method returns null.
     * 
     * @param s the string to parse.
     * @return the corresponding date object.
     */
    public JsDate parse(String s) {
    	JsObject result = call("parse", s);
    	return new JsDate(engine, result);		
    }

    /**
     * Formats the specified date, returning the corresponding string.
     * 
     * @param date The date to format.
     * @return The formatted string.
     */
    public String apply(JsDate date) {
    	JsObject jsDateObj = date.getJsObject();
    	
    	String varName = createNewTemporaryInstanceName();
    	JsObject d3JsObject = getD3();    	
    	d3JsObject.setMember(varName, jsDateObj);
    	
    	String command = "this(d3." + varName +")";    	
    	String result = (String) eval(command);    	 
    	
    	d3JsObject.removeMember(varName);
    	
		return result;
    }

    /**
     * Formats the specified date, returning the corresponding string.
     * 
     * @param date The date to format.
     * @return The formatted string.
     */
    public String apply(Date date) {
        JsDate jsDate = JsDate.create(engine, date.getTime());
        return apply(jsDate);
    }
    
    //#region CLASES
    
    public static class Builder extends JavaScriptObject {
    	
    	//#region CONSTUCTORS
        /**
         * Constructor
         * @param engine
         * @param wrappedJsObject
         */
        public Builder(JsEngine engine, JsObject wrappedJsObject){
        	super(engine);
        	setJsObject(wrappedJsObject);
        }

        //#end region
        
        //#region METHODS

        /**
         * Constructs a new UTC time formatter using the given specifier. The specifier may contain the same directives
         * as the local time format. Internally, this time formatter is implemented using the UTC methods on the Date
         * object, such as <a
         * href="https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Date/getUTCDate">getUTCDate</a>
         * and <a
         * href="https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Date/setUTCDate">setUTCDate</a> in
         * place of <a
         * href="https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Date/getDate">getDate</a> and <a
         * href="https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Date/setDate">setDate</a>.
         * 
         * @param specifier
         *            the specifier string.
         * @return the format.
         */
        public TimeFormat utc(String specifier) {
        	JsObject result = call("utc", specifier);
        	return new TimeFormat(engine, result);			
        }

        /**
         * The full <a href="http://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> UTC time format:
         * "%Y-%m-%dT%H:%M:%S.%LZ". Where available, this method will use
         * <a href="https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Date/toISOString">Date.
         * toISOString</a> to format and the Date constructor to parse strings. If you depend on strict validation of
         * the input format according to ISO 8601, you should construct a time format explicitly instead:<br>
         * <code>TimeFormat iso = D3.time().format().utc("%Y-%m-%dT%H:%M:%S.%LZ");</code>
         * 
         * @return the format.
         */
        public TimeFormat iso() {
        	JsObject result = getMember("iso");
        	return new TimeFormat(engine, result);	
        }
        
        //#end region
    }
    
    //#end region

}
