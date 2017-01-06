package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A generator to create an arc by defining the inner radius, the outer radius,
 * the start angle, and the end angle.
 * <p>
 * Four forms are possible:
 * <ul>
 * <li>a circle, when the inner radius is zero and the angular span is greater
 * than or equal to 2π
 * <li>a circular sector (when the inner radius is zero and the angular span is
 * less than 2π),
 * <li>an annulus (when the inner radius is non-zero and the angular span is
 * greater than or equal to 2π),
 * <li>an annular sector (when the inner radius is non-zero and the angular span
 * is less than 2π).
 * </ul>
 * <p>
 * Default innerRadius-, outerRadius-, startAngle- and endAngle-accessor
 * functions assume the input data is an object with named attributes matching
 * the accessors.
 * <p>
 * While the default accessors assume that the arc dimensions are all specified
 * dynamically, it is very common to set one or more of the dimensions as a
 * constant, such as setting the inner radius to zero for a pie chart.
 * <p>
 * The Arc function generates path data for a closed solid arc, as in a pie or
 * donut chart.
 * 
 * @see <a href="https:arc">Official API</a>
 * 
 * 
 */
public class Arc extends PathDataGenerator {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Arc(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @return the inner radius of the arc.
	 */
	public double innerRadius() {
		Double innerRadius = getMemberForDouble("innerRadius");
		String command = "d3.functor(" + innerRadius + ")();";
		Double result = evalForDouble(command);
		return result;
	}

	/**
	 * Set the inner radius of the Arc.
	 * 
	 * @param innerRadius
	 * @return the current arc generator
	 */
	public final Arc innerRadius(final double innerRadius) {
		return setOrInvokeSetter("innerRadius", innerRadius);
	}

	/**
	 * @return the outerRadius of the arc.
	 */
	public double outerRadius() {
		Double outerRadius = getMemberForDouble("outerRadius");
		String command = "d3.functor(" + outerRadius + ")();";
		Double result = evalForDouble(command);
		return result;
	}

	/**
	 * Set the outerRadius of the Arc.
	 * 
	 * @param outerRadius
	 * @return the current arc generator
	 */
	public final Arc outerRadius(final double outerRadius) {
		return setOrInvokeSetter("outerRadius", outerRadius);
	}

	/**
	 * @return the startAngle of the arc.
	 */
	public double startAngle() {
		Double value = getMemberForDouble("startAngle");
		String command = "d3.functor(" + value + ")();";
		Double result = evalForDouble(command);
		return result;
	}

	/**
	 * Set the start angle in radians of the Arc.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * The angle 0 corresponds to 12 o'clock (negative y) and continues
	 * clockwise repeating at 2xPI.
	 * 
	 * @param startAngle
	 *            the angle in radians
	 * @return the current arc generator
	 */
	public final Arc startAngle(final double startAngle) {
		return setOrInvokeSetter("startAngle", startAngle);
	}

	/**
	 * @return the endAngle of the arc.
	 */
	public double endAngle() {
		Double value = getMemberForDouble("endAngle");
		String command = "d3.functor(" + value + ")();";
		Double result = evalForDouble(command);
		return result;
	}

	/**
	 * Set the end angle in radians of the Arc.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * The angle 0 corresponds to 12 o'clock (negative y) and continues
	 * clockwise repeating at 2xPI.
	 * 
	 * @param endAngle
	 *            the angle in radians
	 * @return the current arc generator
	 */
	public final Arc endAngle(final double endAngle) {
		return setOrInvokeSetter("endAngle", endAngle);
	}

	/**
	 * @param propName
	 * @param value
	 * @return
	 */
	public Arc setOrInvokeSetter(String propName, double value) {
		String isFunctionCommand = "typeof this['" + propName + "'] === 'function'";
		Boolean isFunction = evalForBoolean(isFunctionCommand);

		if (isFunction) {
			String retrieveCommand = "this['" + propName + "'](" + value + ");";
			JSObject result = evalForJsObject(retrieveCommand);
			return new Arc(webEngine, result);
		} else {
			String assignCommand = "this['" + propName + "'] = " + value + ";";
			eval(assignCommand);
			JSObject result = evalForJsObject("this");
			return new Arc(webEngine, result);
		}
	}

	/**
	 * Computes the centroid of the arc that would be generated from the
	 * specified input arguments; typically, the arguments are the current datum
	 * (d), and optionally the current index (i).
	 * <p>
	 * The centroid is defined as the midpoint in polar coordinates of the inner
	 * and outer radius, and the start and end angle. This provides a convenient
	 * location for arc labels.
	 * <p>
	 * 
	 * @param datum
	 * @param index
	 * @return
	 */
	public Array<Double> centroid(Arc arc, int index) {
		JSObject result = call("centroid", arc.getJsObject(), index);
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Create an instance of {@link Arc} with all properties initialized to 0.
	 * 
	 * Useful to define an arc with constants.
	 * @param injectedWebEngine 
	 * 
	 * @return an arc object with defaults.
	 */
	public static Arc constantArc(WebEngine injectedWebEngine) {
		D3 d3 = new D3(injectedWebEngine);
		
		String command = "d3.new_arc = {innerRadius: 0, outerRadius: 1, startAngle: 0, endAngle: 0 };";
		d3.eval(command);
		
		JSObject result = d3.evalForJsObject("d3.new_arc");
		
		//d3.eval("d3.new_arc=undefined");
		
		return new Arc(injectedWebEngine, result);
	}

	/**
	 * Create a new arc with properties initialized with the given arc
	 * 
	 * @param oldArc
	 *            the arc to copy
	 * @return the new copy
	 */
	public static Arc copy(WebEngine webEngine, Arc oldArc){
		Double innerRadius = oldArc.innerRadius();
		Double outerRadius = oldArc.outerRadius();
		Double startAngle = oldArc.startAngle();
		Double endAngle = oldArc.endAngle();
	
		String command = "d3.copy_arc = { innerRadius : "+innerRadius+", outerRadius : "+outerRadius+", startAngle : "+startAngle+", endAngle : "+endAngle + " };";
		webEngine.executeScript(command);
		
		
		JSObject result = (JSObject) webEngine.executeScript("d3.copy_arc");
		//webEngine.executeScript("d3.copy_arc=undefined");
		
		return new Arc(webEngine, result);
		
	}
	
	public String toString(){
		String valueString = getJsObject().call("toString").toString();
		return valueString;
	}

	//#end region

}
