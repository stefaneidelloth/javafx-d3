package org.treez.javafxd3.d3.core;

/**
 * Represents an object that has been returned
 * from java script and hides implementation details
 * of specific JavaScript engine implementations.
 * 
 * If you create a custom implementation of this interface please make
 * sure to correctly override toString, equals and hasCode. 
 * 
 */
public interface JsObject  {
	
	  public abstract Object call(String methodName, Object... args);

	    /**
	     * <p> Evaluates a JavaScript expression. The expression is a string of
	     * JavaScript source code which will be evaluated in the context given by
	     * "this".
	     * </p>
	     *
	     * @param s The JavaScript expression.
	     * @return Result of the JavaScript evaluation.
	     */
	    Object eval(String command);

	    /**
	     * <p> Retrieves a named member of a JavaScript object. Equivalent to
	     * "this.name" in JavaScript.
	     * </p>
	     *
	     * @param name The name of the JavaScript property to be accessed.
	     * @return The value of the propery.
	     */
	    Object getMember(String name);

	    /**
	     * <p> Sets a named member of a JavaScript object. Equivalent to
	     * "this.name = value" in JavaScript.
	     * </p>
	     *
	     * @param name The name of the JavaScript property to be accessed.
	     * @param value The value of the propery.
	     */
	    void setMember(String name, Object value);

	    /**
	     * <p> Removes a named member of a JavaScript object. Equivalent
	     * to "delete this.name" in JavaScript.
	     * </p>
	     *
	     * @param name The name of the JavaScript property to be removed.
	     */
	    void removeMember(String name);

	    /**
	     * <p> Retrieves an indexed member of a JavaScript object. Equivalent to
	     * "this[index]" in JavaScript.
	     * </p>
	     *
	     * @param index The index of the array to be accessed.
	     * @return The value of the indexed member.
	     */
	    Object getSlot(int index);

	    /**
	     * <p> Sets an indexed member of a JavaScript object. Equivalent to
	     * "this[index] = value" in JavaScript.
	     * </p>
	     *
	     * @param index The index of the array to be accessed.
	     */
	    void setSlot(int index, Object value);

	    /**
	     * Returns an object that is specific to the
	     * JavaScript engine implementation, e.g. org.treez.javafxd3.d3.core.JsObject 
	     * for JavaFx JsEngine
	     */
		Object unwrap();
		
		String toString();

		/**
		 * Returns true if the wrapped JavaScript object represents
		 * a DOM element
		 */
		boolean isElement();

}
