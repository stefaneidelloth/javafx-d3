package org.treez.javafxd3.d3.behaviour;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.DragFunction;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * This behavior automatically creates event listeners to handle drag gestures
 * on an element. Both mouse events and touch events are supported.
 * <p>
 */
public class Drag extends JavaScriptObject implements JsFunction {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Drag(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Type of drag event to listen to.
	 * 
	 * 
	 */
	public static enum DragEventType {
		/**
		 * Fired when a drag gesture is started
		 */
		DRAGSTART,
		/**
		 * Fired when the element is dragged. d3.event will contain "x" and "y"
		 * properties representing the current absolute drag coordinates of the
		 * element. It will also contain "dx" and "dy" properties representing
		 * the element's coordinates relative to its position at the beginning
		 * of the gesture.
		 */
		DRAG,
		/**
		 * Fired when the drag gesture has finished
		 */
		DRAGEND;
	}

	/**
	 * Registers the specified listener to receive events of the specified type
	 * from the drag behavior.
	 * <p>
	 * See {@link DragEventType} for more information.
	 * 
	 * @param type
	 * @param listener
	 * @return
	 */
	public Drag on(DragEventType type, DataFunction<Void> listener) {

		String eventName = type.name().toLowerCase().replace("_", "-");
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".apply(this,{datum:d},index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('" + eventName + "', "+varName+");";

		JSObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		
		return new Drag(webEngine, result);
	}
	
	
	public Drag onDragStart(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDragStart(this,{datum:d},index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('dragstart', "+varName+");";
		JSObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		
		return new Drag(webEngine, result);
	}
	
	public Drag onDrag(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDrag(this,{datum:d},index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('drag', "+varName+");";
		JSObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		return new Drag(webEngine, result);
	}
	
	public Drag onDragEnd(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDragEnd(this,{datum:d},index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('dragend', "+varName+");";
		JSObject result = evalForJsObject(onCommand);
		return new Drag(webEngine, result);
	}

	/**
	 * Set the origin accessor to the specified function.
	 * <p>
	 * An origin accessor is used to determine the starting position (the
	 * “origin?) of the element being dragged; this allows the drag behavior to
	 * preserve the offset between the mouse position and the starting element
	 * position during drag. If the origin accessor is null, then the element
	 * position is set to the mouse position on drag; this can cause a
	 * noticeable jump on large elements. If an origin accessor is specified,
	 * the function is called on mousedown. The function is invoked in the same
	 * manner as other operator functions, being passed the current datum d and
	 * index i, with the this context as the clicked-on DOM element. To access
	 * the current event, use the global {@link D3#event()}. The origin accessor
	 * must return an object with x and y properties representing the starting
	 * coordinates of the element being dragged.
	 * <p>
	 * Frequently the origin accessor is specified as Object, or
	 * {@link D3#identity()}, which is equivalent to the identity function. This
	 * is suitable when the datum bound to the dragged element is already an
	 * object with x and y attributes representing its current position.
	 * <p>
	 * For example:
	 * <a href="http://bl.ocks.org/1557377">http://bl.ocks.org/1557377</a>
	 * 
	 * @param o
	 *            the origin accessor function, returning an object with x and y
	 *            attributes.
	 * @return the object Drag.
	 */
	public Drag origin(JSObject o) {
		JSObject result = call("origin", o);
		return new Drag(webEngine, result);
	}

	/**
	 * Set the origin accessor to the specified function.
	 * <p>
	 * An origin accessor is used to determine the starting position (the
	 * “origin?) of the element being dragged; this allows the drag behavior to
	 * preserve the offset between the mouse position and the starting element
	 * position during drag. If the origin accessor is null, then the element
	 * position is set to the mouse position on drag; this can cause a
	 * noticeable jump on large elements. If an origin accessor is specified,
	 * the function is called on mousedown. The function is invoked in the same
	 * manner as other operator functions, being passed the current datum d and
	 * index i, with the this context as the clicked-on DOM element. To access
	 * the current event, use the global {@link D3#event()}. The origin accessor
	 * must return an object with x and y properties representing the starting
	 * coordinates of the element being dragged.
	 * <p>
	 * Frequently the origin accessor is specified as Object, or
	 * {@link D3#identity()}, which is equivalent to the identity function. This
	 * is suitable when the datum bound to the dragged element is already an
	 * object with x and y attributes representing its current position.
	 * <p>
	 * For example:
	 * <a href="http://bl.ocks.org/1557377">http://bl.ocks.org/1557377</a>
	 * 
	 * @param o
	 *            the origin accessor function, returning an object with x and y
	 *            attributes.
	 * @return the object Drag.
	 */
	public Drag origin(DataFunction<Coords> originAccesor) {

		String originAccessorName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(originAccessorName, originAccesor);

		String command = "this.origin(function(d, i) { return d3." + originAccessorName
				+ ".apply(this,{datum:d},i); });";
		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(originAccessorName);
		
		if(result==null){
			return null;
		}
		return new Drag(webEngine, result);
	}

	/**
	 * Provide access to the properties of a drag event.
	 * <p>
	 * Use {@link D3#dragEvent()} from within a
	 * {@link Drag#on(DragEventType, DataFunction)} listener.
	 * <p>
	 * 
	 * 
	 * 
	 */
	public static class DragEvent extends JavaScriptObject {

		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param webEngine
		 * @param wrappedJsObject
		 */
		public DragEvent(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine);
			setJsObject(wrappedJsObject);
		}

		//#end region

		//#region METHODS

		/**
		 * @return the element’s x coordinate relative to its position at the
		 *         beginning of the gesture, which is occasionally more
		 *         convenient than specifying an explicit origin.
		 */
		public double dx() {
			Double result = getMemberForDouble("dx");
			return result;
		}

		/**
		 * @return the element’s x coordinate relative to its position at the
		 *         beginning of the gesture, which is occasionally more
		 *         convenient than specifying an explicit origin.
		 */
		public double dy() {
			Double result = getMemberForDouble("dy");
			return result;
		}

		//#end region
	}

	//#end region

}
