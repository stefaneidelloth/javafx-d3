package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.event.D3Event;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.scales.ContinuousQuantitativeScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 *
 */
public class Brush extends JavaScriptObject implements JsFunction {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Brush(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Set the brushâ€™s x-scale.
	 * <p>
	 * The scale is typically defined as a {@link QuantitativeScale}, in which
	 * case the {@link #extent()} is in data space from the scale's
	 * {@link Scale#domain()}; however, it may instead be defined as an
	 * {@link OrdinalScale}, where the extent is in pixel space from the scale's
	 * {@link OrdinalScale#rangeExtent()}.
	 * <p>
	 *
	 * @param scale
	 *            the x-scale.
	 * @return the current brush.
	 */
	public Brush x(Scale<?> scale) {
		JsObject result = call("x", scale.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Get the brushs x-scale, which defaults to null.
	 * <p>
	 * The scale is typically defined as a {@link QuantitativeScale}, in which
	 * case the {@link #extent()} is in data space from the scale's
	 * {@link Scale#domain()}; however, it may instead be defined as an
	 * {@link OrdinalScale}, where the extent is in pixel space from the scale's
	 * {@link OrdinalScale#rangeExtent()}.
	 * <p>
	 *
	 * @return the brushs x-scale.
	 */
	public <T extends Scale<T>> T x(Class<T> clazz) {
		
		JsObject jsResult = call("x");
		if(jsResult==null){
			return null;
		}
		T result = ConversionUtil.convertObjectTo(jsResult, clazz, engine);
		return result;				
	}

	/**
	 * Set the brushs y-scale. *
	 * <p>
	 * The scale is typically defined as a {@link QuantitativeScale}, in which
	 * case the {@link #extent()} is in data space from the scale's
	 * {@link Scale#domain()}; however, it may instead be defined as an
	 * {@link OrdinalScale}, where the extent is in pixel space from the scale's
	 * {@link OrdinalScale#rangeExtent()}.
	 * <p>
	 *
	 * @param scale
	 *            the y-scale.
	 * @return the current brush.
	 */
	public Brush y(Scale<?> scale) {
		JsObject result = call("y", scale.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Get the brushâ€™s y-scale, which defaults to null. * *
	 * <p>
	 * The scale is typically defined as a {@link QuantitativeScale}, in which
	 * case the {@link #extent()} is in data space from the scale's
	 * {@link Scale#domain()}; however, it may instead be defined as an
	 * {@link OrdinalScale}, where the extent is in pixel space from the scale's
	 * {@link OrdinalScale#rangeExtent()}.
	 * <p>
	 *
	 * @return the brushs y-scale.
	 */
	public <T extends Scale<T>> T y(Class<T> clazz) {
		JsObject jsResult = call("y");
		if(jsResult==null){
			return null;
		}
		T result = ConversionUtil.convertObjectTo(jsResult, clazz, engine);
		return result;	
	}

	/**
	 * Draws or redraws this brush into the specified selection of elements.
	 * <p>
	 * The brush may be drawn into multiple elements simultaneously, but note
	 * that these brushes would share the same backing extent; typically, a
	 * brush is drawn into only one element at a time.
	 * <p>
	 *
	 * @param selection
	 *            the selection to draw the brush in
	 * @return the current brush
	 */
	public Brush apply(Selection selection) {
		JsObject result = callThisForJsObject(selection.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Draws or redraws this brush into the specified transition of elements.
	 * The brush will perform an automatic transition. Use
	 * {@link #event(Transition)} to dispatch brush events during the transition
	 * for animated brushing.
	 * <p>
	 * The brush may be drawn into multiple elements simultaneously, but note
	 * that these brushes would share the same backing extent; typically, a
	 * brush is drawn into only one element at a time.
	 * <p>
	 *
	 * @param transition
	 *            the transition to draw the brush in
	 * @return the current brush
	 */
	public Brush apply(Transition transition) {
		JsObject result = callThisForJsObject(transition.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Get the current brushs extent.
	 * <p>
	 * The definition of the extent depends on the associated scales. <br>
	 * If both an x- and y-scale are available, then the extent is the
	 * two-dimensional array [[x0, y0], [x1, y1]], where x0 and y0 are the lower
	 * bounds of the extent, and x1 and y1 are the upper bounds of the extent.
	 * If only the x-scale is available, then the extent is defined as the
	 * one-dimensional array [x0, x1]; likewise, if only the y-scale is
	 * available, then the extent is [y0, y1]. If neither scale is available,
	 * then the extent is null.
	 * <p>
	 * When the extent is set to values, the resulting extent is preserved
	 * exactly. However, as soon as the brush is moved by the user (on mousemove
	 * following a mousedown), then the extent will be recomputed by calling
	 * {@link ContinuousQuantitativeScale#invert(double)}. Note that, in this
	 * case, the values may be slightly imprecise due to the limited precision
	 * of pixels.
	 * <p>
	 * Note that this does not automatically redraw the brush or dispatch any
	 * events to listeners. To redraw the brush, call {@link #apply(Selection)}
	 * or {@link #apply(Transition)}; to dispatch events, use
	 * {@link #event(Selection)} or {@link #event(Transition)}.
	 *
	 * @return the current bruss extent.
	 */
	public <T> Array<T> extent() {
		JsObject result = call("extent");
		return new Array<T>(engine, result);
	}

	// to be in according the Scale.domain methods:
	// missing string versions
	// missing Javascript versions

	/**
	 * Set the current brushâ€™s extent.
	 * <p>
	 * The definition of the extent depends on the associated scales. <br>
	 * If both an x- and y-scale are available, then the extent is the
	 * two-dimensional array [â€�â€‹[x0, y0], [x1, y1]â€‹], where x0 and y0 are
	 * the lower bounds of the extent, and x1 and y1 are the upper bounds of the
	 * extent. If only the x-scale is available, then the extent is defined as
	 * the one-dimensional array [x0, x1]; likewise, if only the y-scale is
	 * available, then the extent is [y0, y1]. If neither scale is available,
	 * then the extent is null.
	 * <p>
	 * When the extent is set to values, the resulting extent is preserved
	 * exactly. However, as soon as the brush is moved by the user (on mousemove
	 * following a mousedown), then the extent will be recomputed by calling
	 * {@link ContinuousQuantitativeScale#invert(double)}. Note that, in this
	 * case, the values may be slightly imprecise due to the limited precision
	 * of pixels.
	 * <p>
	 * Note that this does not automatically redraw the brush or dispatch any
	 * events to listeners. To redraw the brush, call {@link #apply(Selection)}
	 * or {@link #apply(Transition)}; to dispatch events, use
	 * {@link #on(BrushEvent, DataFunction)}.
	 * <p>
	 * 
	 * @param array
	 *
	 * @return the current brush
	 */
	public <T> Brush extent(Array<T> array) {
		JsObject arrayObj = array.getJsObject();
		JsObject result = call("extent", arrayObj);
		return new Brush(engine, result);
	}

	public <T> Brush extent(Double[][] array) {
		String arrayString = ArrayUtils.createArrayString(array);
		String command = "this.extent(" + arrayString + ")";
		JsObject result = evalForJsObject(command);
		return new Brush(engine, result);
	}

	/**
	 * Set the current brushâ€™s extent for a one-dimensional brush (defined by
	 * its x scale, or by its y scale, but not both).
	 * <p>
	 * When the extent is set to values, the resulting extent is preserved
	 * exactly. However, as soon as the brush is moved by the user (on mousemove
	 * following a mousedown), then the extent will be recomputed by calling
	 * {@link ContinuousQuantitativeScale#invert(double)}. Note that, in this
	 * case, the values may be slightly imprecise due to the limited precision
	 * of pixels.
	 * <p>
	 * Note that this does not automatically redraw the brush or dispatch any
	 * events to listeners. To redraw the brush, call {@link #apply(Selection)}
	 * or {@link #apply(Transition)}; to dispatch events, use
	 * {@link #event(Selection)} or {@link #event(Transition)}.
	 * <p>
	 *
	 * @param min
	 *            the lowest value of the brush's extent
	 * @param max
	 *            the highest value of the brush's extent
	 * @return the current brush
	 */
	public <T> Brush extent(double min, double max) {
		String command = "this.extent([ " + min + ", " + max + " ]);";
		JsObject result = evalForJsObject(command);
		return new Brush(engine, result);
	}

	/**
	 * Set the current brushâ€™s extent for a two-dimensional brush (defined by
	 * both its x scale and its y scale.
	 * <p>
	 * When the extent is set to values, the resulting extent is preserved
	 * exactly. However, as soon as the brush is moved by the user (on mousemove
	 * following a mousedown), then the extent will be recomputed by calling
	 * {@link ContinuousQuantitativeScale#invert(double)}. Note that, in this
	 * case, the values may be slightly imprecise due to the limited precision
	 * of pixels.
	 * <p>
	 * Note that this does not automatically redraw the brush or dispatch any
	 * events to listeners. To redraw the brush, call {@link #apply(Selection)}
	 * or {@link #apply(Transition)}; to dispatch events, use
	 * {@link #event(Selection)} or {@link #event(Transition)}.
	 * <p>
	 *
	 * @param x0
	 *            the lowest value of the x scale
	 * @param y0
	 *            the lowest value of the y scale
	 * @param x1
	 *            the highest value of the x scale
	 * @param y1
	 *            the highest value of the y scale
	 * @return the current brush
	 */
	public <T> Brush extent(double x0, double y0, double x1, double y1) {
		String command = "this.extent([ [ " + x0 + ", " + y0 + " ], [ " + x1 + ", " + y1 + " ] ]);";
		JsObject result = evalForJsObject(command);
		return new Brush(engine, result);
	}

	/**
	 * Set the listener for the specified event type.
	 * <p>
	 * Brushes support three types of events:
	 * <ul>
	 * <li>{@link BrushEvent#BRUSH_START} - on mousedown
	 * <li>{@link BrushEvent#BRUSH} - on mousemove, if the brush extent has
	 * changed
	 * <li>{@link BrushEvent#BRUSH_END} - on mouseup
	 * </ul>
	 *
	 * <p>
	 * Note that when clicking on the background, a mousedown also triggers a
	 * "brush" event, since the brush extent is immediately cleared to start a
	 * new extent.
	 *
	 * @param event
	 *            the event.
	 * @param listener
	 *            the event listener.
	 * @return the current brush.
	 */
	public Brush on(BrushEvent event, DataFunction<Void> listener) {

		String eventString = event.getValue();

		String memberName = createNewTemporaryInstanceName();
		String varName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, listener);
		
		String command = "d3."+varName+" = function(d, i) {" //		      
				+ "d3." + memberName + ".apply(this,d,i);" //
				+ " }; ";

		eval(command);
		String onCommand = "this.on('" + eventString + "', d3."+varName+");";
		
		

		JsObject result = evalForJsObject(onCommand);
		
		if(result==null){
			return null;
		}
		return new Brush(engine, result);
		
	}

	/**
	 * Dispatch a brush gesture to registered listeners as a three event
	 * sequence: {@link BrushEvent#BRUSH_START}, {@link BrushEvent#BRUSH}, and
	 * {@link BrushEvent#BRUSH_END}.
	 * <p>
	 * This can be useful in triggering listeners after setting the brush extent
	 * programatically. If selection is a transition, registers the appropriate
	 * tweens so that the brush dispatches events over the course of the
	 * transition: a brushstart event when the transition starts from the
	 * previously-set extent, brush events for each tick of the transition, and
	 * finally a brushend event when the transition ends. Note that the
	 * transition will be interrupted if the user starts brushing before the
	 * transition ends.
	 *
	 * @param selection
	 * @return
	 */
	public Brush event(Selection selection) {
		JsObject result = call("event", selection.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Same as {@link #event(Selection)} or {@link #event(Transition)}, but
	 * allows the use of {@link Selection#call(JsFunction)}.
	 * <p>
	 *
	 * @return a function object to pass to {@link Selection#call(JsFunction)}
	 */
	public JsFunction event() {		
		JsObject result = getMember("event");
		return new D3Event(engine, result);

	}

	/**
	 * Registers the appropriate tweens so that the brush dispatches events over
	 * the course of the transition: a {@link BrushEvent#BRUSH_START} event when
	 * the transition starts from the previously-set extent,
	 * {@link BrushEvent#BRUSH} events for each tick of the transition, and
	 * finally a {@link BrushEvent#BRUSH_END} event when the transition ends.
	 * <p>
	 * Note that the transition will be interrupted if the user starts brushing
	 * before the transition ends.
	 *
	 * @param transition
	 * @return the Brush
	 */
	public Brush event(Transition transition) {
		JsObject result = call("event", transition.getJsObject());
		return new Brush(engine, result);
	}

	/**
	 * Clears the extent, making the brush extent {@link #empty()}.
	 *
	 * @return the current {@link Brush}
	 */
	public Brush clear() {
		JsObject result = call("clear");
		return new Brush(engine, result);
	}

	/**
	 * Sets the current clamping behavior., for a brush where only one of the
	 * x-scale and y-scale are available.
	 * <p>
	 *
	 * @param clamp
	 *            true if the one-dimensional extent should be clamped to its
	 *            scale
	 * @return the current brush
	 */
	public Brush clamp(boolean clamp) {
		String command = "this.clamp([ " + clamp + "]);";
		JsObject result = evalForJsObject(command);
		return new Brush(engine, result);
	}

	/**
	 * Sets the current clamping behavior, for a brush where both an x- and
	 * y-scale are available.
	 * <p>
	 *
	 * @param clampX
	 *            true if the x-extent should be clamped to its scale
	 * @param clampY
	 *            true if the Y-extent should be clamped to its scale
	 * @return the current brush
	 */
	public Brush clamp(boolean clampX, boolean clampY) {
		String command = "this.clamp([ " + clampX + ", " + clampY + " ]);";
		JsObject result = evalForJsObject(command);
		return new Brush(engine, result);
	}

	/**
	 * Gets the current clamping behavior.
	 * <p>
	 * The clamping behavior definition depends on the associated scales. <br>
	 * If both an x- and y-scale are available, then the clamping behavior is an
	 * array [ x, y ], where x and y are booleans that determine whether the
	 * each dimension of the two-dimensional extent should be clamped to its
	 * respective x- and y-scale. If only one of the x-scale and y-scale are
	 * available, then the clamping behavior is a boolean referring to whether
	 * the one-dimensional extent should be clamped to that scale. If neither
	 * scale is available, then the clamping behavior is null.
	 *
	 * @return the current brush
	 */
	public Array<Boolean> clamp() {
		JsObject result = call("clamp");
		return new Array<Boolean>(engine, result);		
	}

	/**
	 * Returns true if and only if the brush extent is empty.
	 * <p>
	 * When a brush is created, it is initially empty; the brush may also become
	 * empty with a single click on the background without moving, or if the
	 * extent is {@link #clear()}ed.
	 * <p>
	 * A brush is considered empty if it has zero-width or zero-height. When the
	 * brush is empty, its {@link #extent()} is not strictly defined.
	 * <p>
	 *
	 * @return true if the brush extent is empty.
	 */
	public boolean empty() {
		return callForBoolean("empty");
	}

	//#end region

	//#region ENUM

	/**
	 * 
	 */
	public static enum BrushEvent {

		//#region VALUES
		/**
		 * on mousedown.
		 */
		BRUSH_START("brushstart"),

		/**
		 * on mousemove, if the brush extent has changed.
		 */
		BRUSH("brush"),

		/**
		 * on mouseup.
		 */
		BRUSH_END("brushend");

		//#end region

		//#region ATTRIBUTES

		private final String value;

		//#end region

		//#region CONSTRUCTORS

		private BrushEvent(final String value) {
			this.value = value;
		}

		//#end region

		//#region ACCESSORS

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		//#end region
	}

	//#end region

}
