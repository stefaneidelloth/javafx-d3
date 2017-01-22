package org.treez.javafxd3.d3.core;

public interface JsEngine {
	
    /**
     * Executes a script in the context of the current page.
     * @return execution result, converted to a Java object using the following
     * rules:
     * <ul>
     * <li>JavaScript Int32 is converted to {@code java.lang.Integer}
     * <li>Other JavaScript numbers to {@code java.lang.Double}
     * <li>JavaScript string to {@code java.lang.String}
     * <li>JavaScript boolean to {@code java.lang.Boolean}
     * <li>JavaScript {@code null} to {@code null}
     * <li>Most JavaScript objects get wrapped as
     *     JsObject    
     * </ul>
     */
    Object executeScript(String script);

	/**
	 * Wraps a java script object argument if it is not 
	 * a simple type
	 * @return
	 */
	Object toJsObjectIfNotSimpleType(Object argument);       

}
