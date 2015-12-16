package org.treez.javafxd3.d3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.behaviour.Behavior;
import org.treez.javafxd3.d3.behaviour.Drag;
import org.treez.javafxd3.d3.behaviour.Zoom;
import org.treez.javafxd3.d3.behaviour.Drag.DragEvent;
import org.treez.javafxd3.d3.behaviour.Zoom.ZoomEvent;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Formatter;
import org.treez.javafxd3.d3.core.Prefix;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.dsv.Dsv;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.event.D3Event;
import org.treez.javafxd3.d3.event.Event;
import org.treez.javafxd3.d3.functions.TimerFunction;
import org.treez.javafxd3.d3.geo.Geography;
import org.treez.javafxd3.d3.geom.Geometry;
import org.treez.javafxd3.d3.interpolators.Interpolators;
import org.treez.javafxd3.d3.layout.Layout;
import org.treez.javafxd3.d3.scales.Scales;
import org.treez.javafxd3.d3.svg.SVG;
import org.treez.javafxd3.d3.time.Time;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.d3.wrapper.JsArrayMixed;
import org.treez.javafxd3.d3.wrapper.Node;
import org.treez.javafxd3.d3.wrapper.NodeList;
import org.treez.javafxd3.d3.wrapper.Widget;
import org.treez.javafxd3.d3.wrapper.WidgetCollection;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Entry point for D3 api modules. A lot of methods of this class allow access
 * to other classes:
 * <ul>
 * <li>{@link D3#select(Element)} methods and {@link Selection} - manipulate
 * elements in the current document.
 * <li>Interpolation methods are in {@link Interpolators}.
 * <p>
 *
 *
 *
 * 
 *
 */
public class D3 extends JavaScriptObject {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public D3(WebEngine webEngine) {
		super(webEngine);
		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		setJsObject(d3);
	}

	//#end region

	//#region METHODS

	/**
	 * @return the version of the d3 API
	 */
	public String version() {
		String result = callForString("version");
		return result;
	};

	/**
	 * The scale factory module.
	 * 
	 * @return
	 */
	public Scales scale() {
		JSObject result = getMember("scale");
		return new Scales(webEngine, result);
	};

	// =========== select ==============
	/**
	 * Selects the first element that matches the specified selector string,
	 * returning a single-element selection. If no elements in the current
	 * document match the specified selector, returns the empty selection. If
	 * multiple elements match the selector, only the first matching element (in
	 * document traversal order) will be selected.
	 * <p>
	 * The selector is a valid CSS3 selector. For example, you can select by tag
	 * ("div"), class (".awesome"), unique identifier ("#foo"), attribute
	 * ("[color=red]"), or containment ("parent child"). Selectors can also be
	 * intersected (".this.that" for logical AND) or unioned (".this, .that" for
	 * logical OR)
	 *
	 * @param selector
	 *            a CSS3 selector
	 * @return the {@link Selection}
	 */
	public Selection select(String selector) {
		JSObject result = call("select", selector);
		return new Selection(webEngine, result);
	};

	/**
	 * Selects the specified element. This is useful if you already have a
	 * reference to an element, or a global such as Document#getBody()
	 *
	 * @param element
	 *            the element to select
	 * @return the {@link Selection}
	 */
	public Selection select(Element element) {
		JSObject jsElement = element.getJsObject();
		JSObject result = call("select", jsElement);
		return new Selection(webEngine, result);
	};

	/**
	 * Selects the specified widget. This is useful if you already have a
	 * reference to a widget, or a global such as RootPanel#get()
	 *
	 * @param widget
	 *            the widget to select
	 * @return the {@link Selection}
	 */
	public Selection select(final Widget widget) {
		Element element = widget.getElement();
		return select(element);

	}

	// ================ selectAll ================
	/**
	 * Selects all elements that match the specified selector. The elements will
	 * be selected in document traversal order (top-to-bottom). If no elements
	 * in the current document match the specified selector, returns the empty
	 * selection.
	 *
	 * @param selector
	 * @return
	 */
	public Selection selectAll(String selector) {
		JSObject result = call("selectAll", selector);
		return new Selection(webEngine, result);
	};

	/**
	 * Selects the list of elements.
	 *
	 * @param nodes
	 *            the elements
	 * @return the selection
	 */
	public Selection selectAll(NodeList<?> nodes) {
		JSObject result = call("selectAll", nodes);
		return new Selection(webEngine, result);
	};

	/**
	 * Selects the specified array of elements.
	 *
	 * @param nodes
	 *            the elements
	 * @return the selection
	 */
	public Selection selectAll(Element... nodes) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call("selectAll", (Object[]) nodes);
		//return new Selection(webEngine, result);
	};

	/**
	 * Selects the specified elementMatrices.
	 *
	 * @param elementMatrices
	 *            the elements
	 * @return the selection
	 */
	public Selection selectAll(Element[][]... elementMatrices) {
		throw new IllegalStateException("not yet implemented");
		//JSObject result = call("selectAll", (Object[]) nodes);
		//return new Selection(webEngine, result);
	};

	/**
	 * Selects the specified collection of elements.
	 *
	 * @param nodes
	 *            the elements
	 * @return the selection
	 */
	public final Selection selectAll(final Collection<Element> nodes) {
		Element[] nodeArray = nodes.toArray(new Element[nodes.size()]);
		return selectAll(nodeArray);
	}

	/**
	 * Selects the elements corresponding to the root elements of the widgets in
	 * the specified array.
	 *
	 * @param nodes
	 *            the elements
	 * @return the selection
	 */
	public final Selection selectAll(final Widget... nodes) {
		List<Element> elements = new ArrayList<Element>();
		for (Widget widget : nodes) {
			elements.add(widget.getElement());
		}
		return selectAll(elements);
	}

	/**
	 * Selects the specified collection of elements.
	 *
	 * @param widgets
	 *            the elements
	 * @return the selection
	 */
	public final Selection selectAll(final WidgetCollection widgets) {
		List<Element> elements = new ArrayList<Element>();
		for (Widget widget : widgets) {
			elements.add(widget.getElement());
		}
		return selectAll(elements);
	}

	/**
	 * Create an animated transition.
	 * <p>
	 * This is equivalent to {@link Selection#transition()
	 * D3.select(document).transition()}. This method is used rarely, as it is
	 * typically easier to derive a transition from an existing selection,
	 * rather than deriving a selection from an existing transition.
	 *
	 *
	 * @return the transition the new transition
	 */
	public Transition transition() {
		JSObject result = call("transition");
		return new Transition(webEngine, result);
	};

	// /**
	// * Create an animated transition. In the context of
	// * {@link Transition#each(com.github.gwtd3.api.functions.DatumFunction)},
	// * this method will create a new transition for the specified selection
	// that
	// * inherits the delay, duration and other properties of the parent
	// * transition. This is useful for implementing reusable components that
	// can
	// * be called either on selections or on transitions, in the latter case
	// * supporting deriving concurrent transitions. An example of this is	
	// return $wnd.d3.transition(selection);
	// };

	// =========== Math ==============

	// =========== shuffle ==============

	/**
	 * Randomly shuffle the list of objects provided.
	 * <p>
	 * <i>Note: {@link Collections#shuffle(List)} is not GWT compatible</i>
	 *
	 * @param objects
	 *            the list to shuffle
	 */
	public void shuffle(final List<?> objects) {
		Random random = new Random();
		for (int i = objects.size(); i > 1; i--) {
			swap(objects, i - 1, random.nextInt(i));
		}
	}

	private static final <T> void swap(final List<T> list, final int idx1, final int idx2) {
		T temp = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, temp);
	}

	/**
	 * @param input
	 */
	public void shuffle(final int[] input) {
		//Shuffle by exchanging each element randomly
		Random random = new Random();

		for (int i = 0; i < input.length; i++) {
			int randomPosition = random.nextInt(input.length);
			int temp = input[i];
			input[i] = input[randomPosition];
			input[randomPosition] = temp;
		}
	}

	/**
	 * @param input
	 */
	public void shuffle(final char[] input) {
		//Shuffle by exchanging each element randomly
		Random random = new Random();

		for (int i = 0; i < input.length; i++) {
			int randomPosition = random.nextInt(input.length);
			char temp = input[i];
			input[i] = input[randomPosition];
			input[randomPosition] = temp;
		}
	}

	// ================ Colors ================

	// =========== svg ==============
	/**
	 * @return the svg module
	 */
	public SVG svg() {
		JSObject result = getMember("svg");
		return new SVG(webEngine, result);
	};

	// =========== layouts ==============
	/**
	 * @return the layout module
	 */
	public Layout layout() {
		JSObject result = call("layout");
		return new Layout(webEngine, result);
	};

	/**
	 * @return the {@link Geometry} module
	 */
	public Geometry geom() {
		JSObject result = getMember("geom");
		return new Geometry(webEngine, result);
	};

	/**
	 * @return the {@link Geography} module
	 */
	public Geography geo() {
		JSObject result = getMember("geo");
		return new Geography(webEngine, result);
	};

	// =========== interpolation ==============

	// =========== ease ==============
	// cf Easing

	// =========== timer ==============

	/**
	 * Alias for {@link #timer(TimerFunction, int)} with a delay equals to 0.
	 *
	 * @param command
	 *            the command to be executed until it returns true.
	 */
	public void timer(TimerFunction timerFunction) {

		assertObjectIsNotAnonymous(timerFunction);

		String methodName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();

		Object method = timerFunction;
		boolean isJavaScriptObject = timerFunction instanceof JavaScriptObject;
		if (isJavaScriptObject) {
			JavaScriptObject javaScriptObject = (JavaScriptObject) timerFunction;
			method = javaScriptObject.getJsObject();
		}

		d3JsObject.setMember(methodName, method);

		String command = "d3.timer(function() { " //
				+ "return d3." + methodName + ".execute();" //
				+ "});";
		eval(command);
	};

	/**
	 * Alias for {@link #timer(TimerFunction, int, int)} with a mark equals to
	 * the "now" timestamp (i.e <code>new Date().getTime()</code>).
	 *
	 * @param command
	 *            the command to be executed until it returns true.
	 * @param delayMillis
	 *            the delay to expires before the command should start being
	 *            invoked (may be negative if markMillis is in the future)
	 */
	public void timer(TimerFunction command, int delayMillis) {

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .timer( function() { return
		 * command.@com.github.gwtd3.api.functions.TimerFunction::execute()();
		 * }, delayMillis);
		 * 
		 */
	};

	/**
	 * Start a custom animation timer, invoking the specified
	 * {@link TimerFunction} repeatedly until it returns true.
	 * <p>
	 * There is no way to cancel the timer after it starts, so make sure your
	 * timer function returns true when done!
	 *
	 * The optional numeric delay [unit: integer, millisecond] may be specified
	 * when the given function should only start to be invoked after delay
	 * milliseconds have expired after the specified mark timestamp [unit:
	 * integer, milliseconds since epoch].
	 * <p>
	 * When mark is omitted, Date.now() is assumed instead. Otherwise, you may
	 * use Date.getTime to convert your Date object to a suitable mark
	 * timestamp.
	 * <p>
	 * You may use delay and mark to specify relative and absolute moments in
	 * time when the function should start being invoked, e.g. a calendar-based
	 * event might be coded as
	 *
	 * <pre>
	 * {@code
	 * 	Date appointment = new Date(2012, 09, 29, 14, 0, 0); // @ 29/sep/2012, 1400 hours
	 * ...
	 * // flash appointment on screen when it's due in 4 hours or less: note that negative (delay) is okay!
	 * d3.timer(flash_appointments_due, -4 * 3600 * 1000, appointment);
	 * }
	 * </pre>
	 *
	 * @param command
	 *            the command to be executed until it returns true.
	 * @param delayMillis
	 *            the delay to expires before the command should start being
	 *            invoked (may be negative if markMillis is in the future)
	 * @param markMillis
	 *            the timestamp from which the delay starts
	 */
	public void timer(TimerFunction command, int delayMillis, int markMillis) {

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .timer( function() { return
		 * command.@com.github.gwtd3.api.functions.TimerFunction::execute()();
		 * }, delayMillis, markMillis);
		 * 
		 */
	};

	/**
	 * Immediately execute (invoke once) any active timers.
	 * <p>
	 * Normally, zero-delay transitions are executed after an instantaneous
	 * delay (<10ms).
	 * <p>
	 * This can cause a brief flicker if the browser renders the page twice:
	 * once at the end of the first event loop, then again immediately on the
	 * first timer callback.
	 * <p>
	 * By flushing the timer queue at the end of the first event loop, you can
	 * run any zero-delay transitions immediately and avoid the flicker.
	 * <p>
	 * Note: the original method is d3.timer.flush() but has been pushed here
	 * because the timer API is limited to this method
	 *
	 */
	public void timerFlush() {
		call("timer.flush");
	};

	// =========== time ==============

	/**
	 * @return the time module
	 */
	public Time time() {
		JSObject result = getMember("time");
		return new Time(webEngine, result);
	};

	// ========= events and interactions ============
	/**
	 * Retrieve the current event, if any.
	 * <p>
	 * This global variable exists during an event listener callback registered
	 * with the on operator. The current event is reset after the listener is
	 * notified in a finally block. This allows the listener function to have
	 * the same form as other operator functions, being passed the current datum
	 * d and index i.
	 * <p>
	 * The {@link D3#event()} object is a DOM event and implements the standard
	 * event fields like timeStamp and keyCode as well as methods like
	 * preventDefault() and stopPropagation(). While you can use the native
	 * event's pageX and pageY, it is often more convenient to transform the
	 * event position to the local coordinate system of the container that
	 * received the event. For example, if you embed an SVG in the normal flow
	 * of your page, you may want the event position relative to the top-left
	 * corner of the SVG image. If your SVG contains transforms, you might also
	 * want to know the position of the event relative to those transforms.
	 * <p>
	 * Use the d3.mouse operator for the standard mouse pointer, and use
	 * d3.touches for multitouch events on iOS.
	 * <p>
	 * You may get an instance of {@link D3Event} by an auto-casting call:
	 *
	 * <pre>
	 * <code>
	 *     D3Event d3e = D3.event();
	 *     //allow a call to d3e.sourceEvent();
	 * </code>
	 * </pre>
	 *
	 *
	 * @return the instance of {@link Event}
	 */
	public Event event() {		
		JSObject result = getMember("event");
		return new Event(webEngine, result);
	};

	/**
	 * Retrieve the current event if any, as a {@link Coords} object containing
	 * the x and y of the mouse. This is useful when using {@link Drag}
	 * behavior.
	 *
	 * @return the current event as a Coords object
	 */
	public Coords eventAsCoords() {
								
		Object xObj =  eval("d3.event.x");
		Object yObj =  eval("d3.event.y");
		Double x = Double.parseDouble(xObj.toString());
		Double y = Double.parseDouble(yObj.toString());
		
		return new Coords(webEngine, x,y);
	};

	/**
	 * Retrieve the current event if any, as a {@link Coords} object containing
	 * the dx and dy representing the element's coordinates relative to its
	 * position at the beginning of the gesture. This is useful when using
	 * {@link Drag} behavior.
	 *
	 * @return the current event as a Coords object
	 */
	public Coords eventAsDCoords() {
		Double x = callForDouble("event.dx");
		Double y = callForDouble("event.dy");
		Coords coords = new Coords(webEngine, x, y);
		return coords;
	};

	/**
	 * Returns the x and y coordinates of the current d3.event, relative to the
	 * specified container. The container may be an HTML or SVG container
	 * element, such as an svg:g or svg:svg. The coordinates are returned as a
	 * two-element array [ x, y].
	 *
	 * @param container
	 * @return
	 */
	public Array<Double> mouse(Node container) {
		JSObject jsContainer = container.getJsObject();
		JSObject result = call("mouse", jsContainer);
		return new Array<Double>(webEngine, result);
	};

	/**
	 * Returns the x and y coordinates of the current d3.event, relative to the
	 * specified container. The container may be an HTML or SVG container
	 * element, such as an svg:g or svg:svg. The coordinates are returned as a
	 * two-element array [ x, y].
	 *
	 * @param container
	 * @return
	 */
	public Coords mouseAsCoords(Node container) {
		JSObject containerObject = container.getJsObject();
		JSObject coordObj = call("mouse", containerObject);
		Double x = (Double) coordObj.call("m", 0);
		Double y = (Double) coordObj.call("m", 1);
		return new Coords(webEngine, x, y);
	};

	/**
	 * Returns the x coordinate of the current d3.event, relative to the
	 * specified container. The container may be an HTML or SVG container
	 * element, such as an svg:g or svg:svg. The coordinates are returned as a
	 * two-element array [ x, y].
	 *
	 * @param container
	 * @return
	 */
	public double mouseX(Node container) {
		JSObject containerObject = container.getJsObject();
		JSObject coordObj = call("mouse", containerObject);
		//Inspector.inspect(coordObj);

		Object result = coordObj.getMember("0");

		Double x = Double.parseDouble("" + result);
		return x;
	};

	/**
	 * Returns the y coordinate of the current d3.event, relative to the
	 * specified container. The container may be an HTML or SVG container
	 * element, such as an svg:g or svg:svg. The coordinates are returned as a
	 * two-element array [ x, y].
	 *
	 * @param container
	 * @return
	 */
	public double mouseY(Node container) {
		JSObject containerObject = container.getJsObject();
		JSObject coordObj = call("mouse", containerObject);
		Object result = coordObj.getMember("1");
		Double y = Double.parseDouble("" + result);
		return y;
	};

	/**
	 * Returns the x and y coordinates of each touch associated with the current
	 * d3.event, based on the touches attribute, relative to the specified
	 * container.
	 * <p>
	 * The container may be an HTML or SVG container element, such as an svg:g
	 * or svg:svg.
	 * <p>
	 * The coordinates are returned as an array of two-element arrays [ [ x1,
	 * y1], [ x2, y2], … ].
	 * <p>
	 *
	 * @param container
	 *            the node to get the coords relative to
	 * @return an array of array of 2 elements.
	 */
	public JsArrayMixed touches(Node container) {
		JSObject containerObject = container.getJsObject();
		JSObject result = call("touches", containerObject);
		return new JsArrayMixed(webEngine, result);
	};

	// =========== csv ==============

	/**
	 * @return the CSV module
	 */
	public <T> Dsv<T> csv() {
		JSObject result = getMember("csv");
		return new Dsv<T>(webEngine, result);
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/csv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the CSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null.
	 * 
	 * @param url
	 * @param callback
	 * @return
	 */
	public <T> Dsv<T> csv(String url, DsvCallback<T> callback) {

		assertObjectIsNotAnonymous(callback);

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .csv( url, function(error, rows) {
		 * callback.@com.github.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/
		 * core/client/JavaScriptObject;Lcom/github/gwtd3/api/dsv/DsvRows;)(
		 * error, rows); });
		 * 
		 */
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/csv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the CSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null. The accessor may be
	 * specified, which is then passed to d3.csv.parse; the accessor may also be
	 * specified by using the return request object’s row function.
	 * 
	 * @param url
	 * @param accessor
	 * @param callback
	 * @return
	 */
	public <T> Dsv<T> csv(String url, DsvObjectAccessor<T> accessor, DsvCallback<T> callback) {

		assertObjectIsNotAnonymous(callback);

		String accessorMemberName = createNewTemporaryInstanceName();
		String callbackName = createNewTemporaryInstanceName();

		JSObject jsObj = getJsObject();
		jsObj.setMember(accessorMemberName, accessor);
		jsObj.setMember(callbackName, callback);

		String command = "this.csv('" + url + "', function(row, index) { " //
				+ "  return this." + accessorMemberName + ".apply(row, index);" //
				+ " }, " + "function(error, rows) { " //
				+ "  this." + callbackName + ".get(error, rows); " //
				+ "});";
		JSObject result = evalForJsObject(command);
		return new Dsv<T>(webEngine, result);

	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/csv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the CSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null. The accessor may be
	 * specified, which is then passed to d3.csv.parse; the accessor may also be
	 * specified by using the return request object’s row function.
	 * 
	 * @param url
	 * @param accessor
	 * @return
	 */
	public <T> Dsv<T> csv(String url, DsvObjectAccessor<T> accessor) {

		assertObjectIsNotAnonymous(accessor);

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .csv( url, function(row, index) {
		 * return
		 * accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/
		 * Object;I)(row, index); });
		 * 
		 */
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/csv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request.
	 * 
	 * @param url
	 * @return
	 */
	public <T> Dsv<T> csv(String url) {
		JSObject result = call("csv", url);
		return new Dsv<T>(webEngine, result);
	};

	// =========== tsv ==============

	/**
	 * @return the TSV module
	 */
	public <T> Dsv<T> tsv() {
		JSObject result = getMember("tsv");
		return new Dsv<T>(webEngine, result);
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/tsv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the TSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null.
	 * 
	 * @param url
	 * @param callback
	 * @return
	 */
	public <T> Dsv<T> tsv(String url, DsvCallback<T> callback) {

		assertObjectIsNotAnonymous(callback);

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .tsv( url, function(error, rows) {
		 * callback.@com.github.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/
		 * core/client/JavaScriptObject;Lcom/github/gwtd3/api/dsv/DsvRows;)(
		 * error, rows); });
		 * 
		 */
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/tsv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the TSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null. The accessor may be
	 * specified, which is then passed to d3.tsv.parse; the accessor may also be
	 * specified by using the return request object’s row function.
	 * 
	 * @param url
	 * @param accessor
	 * @param callback
	 * @return
	 */
	public <T> Dsv<T> tsv(String url, DsvObjectAccessor<T> accessor, DsvCallback<T> callback) {

		assertObjectIsNotAnonymous(callback);

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .tsv( url, function(row, index) {
		 * return
		 * accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/
		 * Object;I)(row, index); }, function(error, rows) {
		 * callback.@com.github.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/
		 * core/client/JavaScriptObject;Lcom/github/gwtd3/api/dsv/DsvRows;)(
		 * error, rows); });
		 * 
		 */
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/tsv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request. When
	 * the TSV data is available, the specified callback will be invoked with
	 * the parsed rows as the argument. If an error occurs, the callback
	 * function will instead be invoked with null. The accessor may be
	 * specified, which is then passed to d3.tsv.parse; the accessor may also be
	 * specified by using the return request object’s row function.
	 * 
	 * @param url
	 * @param accessor
	 * @return
	 */
	public <T> Dsv<T> tsv(String url, DsvObjectAccessor<T> accessor) {

		assertObjectIsNotAnonymous(accessor);

		throw new IllegalStateException("not yet implemented");

		/*
		 * JSObject result = call($wnd.d3 .tsv( url, function(row, index) {
		 * return
		 * accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/
		 * Object;I)(row, index); });
		 * 
		 */
	};

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at
	 * the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of
	 * the request will be "text/tsv". The request is processed asynchronously,
	 * such that this method returns immediately after opening the request.
	 * 
	 * @param url
	 * @return
	 */
	public <T> Dsv<T> tsv(String url) {
		JSObject result = call("tsv", url);
		return new Dsv<T>(webEngine, result);
	};

	// ============= json ============

	// ============= array ============

	/**
	 * Returns an array containing the property names of the specified object
	 * (an associative array). The order of the returned array is undefined.
	 * <p>
	 *
	 * @param object
	 *            the object to convert to an array
	 * @return an array containing the property names.
	 */
	public <T> String[] keys(JavaScriptObject object) {
		//JSObject result = call("keys", object);
		throw new IllegalStateException("not yet implemented");
		//return new Array<String>(webEngine, result);
	};

	// =================== format methods ====================

	/**
	 * Returns a new {@link Formatter} function with the given string specifier.
	 * A format function takes a number as the only argument, and returns a
	 * string representing the formatted number. Please see {@link Formatter}
	 * javadoc for the specifier specification.
	 *
	 * @see <a href= "https:d3_format">D3. js official documentation</a>
	 * @param specifier
	 *            the given string specifier.
	 * @return the format function.
	 */
	public Formatter format(String specifier) {
		JSObject result = call("format", specifier);
		return new Formatter(webEngine, result);
	};

	/**
	 * Return the SI Prefix for the specified value at the specified precision.
	 * <p>
	 * This method is used by {@link #format(String)} for the %s format.
	 *
	 * @param value
	 *            the value to be prefixed
	 * @param precision
	 *            the precision
	 * @return the prefix
	 */
	public Prefix formatPrefix(double value, double precision) {
		JSObject result = call("formatPrefix", value, precision);
		return new Prefix(webEngine, result);
	};

	/**
	 * Returns the value x rounded to n digits after the decimal point. If n is
	 * omitted, it defaults to zero. The result is a number. Values are rounded
	 * to the closest multiple of 10 to the power minus n; if two multiples are
	 * equally close, the value is rounded up in accordance with the built-in
	 * round function. Note that the resulting number when converted to a string
	 * may be imprecise due to IEEE floating point precision; to format a number
	 * to a string with a fixed number of decimal points, use d3.format instead.
	 *
	 * @param value
	 * @param digits
	 * @return
	 */
	public double round(double value, int digits) {
		Double result = callForDouble("round", value, digits);
		return result;
	};

	/**
	 * Returns a quoted (escaped) version of the specified string such that the
	 * string may be embedded in a regular expression as a string literal.
	 *
	 * @param string
	 *            the input string
	 * @return the regexp escaped string
	 */
	public String requote(String string) {
		String result = callForString("requote", string);
		return result;
	};

	// =========== range ===================

	/**
	 * @param stop
	 * @return
	 */
	public Selection range(double stop) {
		JSObject result = call("range", stop);
		return new Selection(webEngine, result);
	}

	// =========== behaviours ==============
	/**
	 * @return the behaviour module
	 */
	public Behavior behavior() {
		JSObject result = getMember("behavior");
		return new Behavior(webEngine, result);
	};

	/**
	 *
	 * Get the ZoomEvent from within a {@link Zoom} listener.
	 *
	 * @return the zoom event
	 */

	public final ZoomEvent zoomEvent() {
		
		Event event = event();
		if (event==null){
			return null;
		}		
		
		JSObject jsEvent = event.getJsObject();

		return new ZoomEvent(webEngine, jsEvent);		
	}

	/**
	 *
	 * Get the DragEvent from within a {@link Drag} listener.
	 *
	 * @return the drag event
	 */

	public final DragEvent dragEvent() {

		Event event = event();
		if (event==null){
			return null;
		}		
		
		JSObject jsEvent = event.getJsObject();

		return new DragEvent(webEngine, jsEvent);

	}

	// =========== misc ==========
	/**
	 * Return the identity function:
	 * <p>
	 * <code>function(d) { return d; }</code>
	 *
	 * @return the identity function
	 */
	public JSObject identity() {

		String command = "var identity = function(d) { return d; }";
		eval(command);
		JSObject result = evalForJsObject("identity");		
		return result;
	}

	/**
	 * Creates a new variable in the JavaScript space and applies the given
	 * JSObject to assign a corresponding value
	 * 
	 * @param variableName
	 * @param value
	 */
	public void createJsVariable(String variableName, JavaScriptObject value) {
		JSObject valueObject = value.getJsObject();
		createJsVariable(variableName, valueObject);
	}

	/**
	 * Creates a new variable in the JavaScript space and applies the given
	 * JSObject to assign a corresponding value
	 * 
	 * @param variableName
	 * @param value
	 */
	public void createJsVariable(String variableName, JSObject value) {
		//store value in temporary dummy attribute
		String tempAttributeName = "tempDummyStorageAttribute";
		JSObject window = (JSObject) webEngine.executeScript("window");
		window.setMember(tempAttributeName, value);

		//create new variable and assign value to it    	
		String createCommand = "var " + variableName + " = window.tempDummyStorageAttribute;";
		eval(createCommand);

		//delete temporary dummy attribute
		window.removeMember(tempAttributeName);

		//Object val = eval(variableName);
		//boolean isOk = val.equals(value);
	}

	//#end region

	//#region ACCESSORS

	/**
	 * @return
	 */
	public WebEngine getWebEngine() {
		return webEngine;
	}

	//#end region

}
