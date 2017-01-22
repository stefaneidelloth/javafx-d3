package org.treez.javafxd3.d3.behaviour;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.DragFunction;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * This behavior automatically creates event listeners to handle drag gestures
 * on an element. Both mouse events and touch events are supported.
 * <p>
 */
public class Drag extends JavaScriptObject implements JsFunction {

	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Drag(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".apply(this,d,index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('" + eventName + "', "+varName+");";

		JsObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		
		return new Drag(engine, result);
	}
	
	
	public Drag onDragStart(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDragStart(this,d,index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('dragstart', "+varName+");";
		JsObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		
		return new Drag(engine, result);
	}
	
	public Drag onDrag(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDrag(this,d,index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('drag', "+varName+");";
		JsObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		return new Drag(engine, result);
	}
	
	public Drag onDragEnd(DragFunction listener) {
		assertObjectIsNotAnonymous(listener);
		String listenerName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);
		String command = "var "+varName+" = d3." + listenerName + " == null ? null : " + "function(d, index) {" //		      
				+ "d3." + listenerName + ".handleDragEnd(this,d,index);" //
				+ " }; ";
		eval(command);
		String onCommand = "this.on('dragend', "+varName+");";
		JsObject result = evalForJsObject(onCommand);
		return new Drag(engine, result);
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
	public Drag origin(JsObject o) {
		JsObject result = call("origin", o);
		return new Drag(engine, result);
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
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(originAccessorName, originAccesor);

		String command = "this.origin(function(d, i) { return d3." + originAccessorName
				+ ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(originAccessorName);
		
		if(result==null){
			return null;
		}
		return new Drag(engine, result);
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
		 * @param engine
		 * @param wrappedJsObject
		 */
		public DragEvent(JsEngine engine, JsObject wrappedJsObject) {
			super(engine);
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
