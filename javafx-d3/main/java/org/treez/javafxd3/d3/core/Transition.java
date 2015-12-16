package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.color.Color;
import org.treez.javafxd3.d3.ease.Easing;
import org.treez.javafxd3.d3.ease.EasingFunction;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.svg.PathDataGenerator;
import org.treez.javafxd3.d3.tweens.TweenFunction;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.functions.JsFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 * A transition is a special type of selection where the operators apply
 * smoothly over time rather than instantaneously. You derive a transition from
 * a selection using the {@link Selection#transition()} operator. While
 * transitions generally support the same operators as selections (such as
 * #attr(String) and #style(String)), not all operators are supported; for
 * example, you must #append(String) elements before a transition starts. A
 * {@link #remove()} operator is provided for convenient removal of elements
 * when the transition ends.
 * <p>
 * Transitions may have per-element #delay and {@link #duration(DatumFunction)},
 * computed using functions of data similar to other operators. This makes it
 * easy to stagger a transition for different elements, either based on data or
 * index. For example, you can sort elements and then stagger the transition for
 * better perception of element reordering during the transition. For more
 * details on these techniques, see
 * "Animated Transitions in Statistical Data Graphics" by Heer & Robertson.
 * <p>
 * D3 has many built-in interpolators to simplify the transitioning of arbitrary
 * values. For instance, you can transition from the font string
 * "500 12px sans-serif" to "300 42px sans-serif", and D3 will find the numbers
 * embedded within the string, interpolating both font size and weight
 * automatically. You can even interpolate arbitrary nested objects and arrays
 * or SVG path data. D3 allows custom interpolators should you find the built-in
 * ones insufficient, using the attrTween and styleTween operators. D3's
 * interpolators provide the basis for scales and can be used outside of
 * transitions; an interpolator is a function that maps a parametric value t in
 * the domain [0,1] to a color, number or arbitrary value.
 * <p>
 * Only one transition may be active on a given element at a given time.
 * However, multiple transitions may be scheduled on the same element; provided
 * they are staggered in time, each transition will run in sequence. If a newer
 * transition runs on a given element, it implicitly cancels any older
 * transitions, including any that were scheduled but not yet run. This allows
 * new transitions, such as those in response to a new user event, to supersede
 * older transitions even if those older transitions are staged or have
 * staggered delays. Multi-stage transitions (transitions that are created
 * during the "end" event of an earlier transition) are considered the same
 * "age" as the original transition; internally this is tracked by
 * monotonically-increasing unique IDs which are inherited when multi-stage
 * transitions are created.
 * <p>
 * For more on transitions, read the Working with Transitions tutorial.
 * 
 */
public class Transition extends JavaScriptObject {

	//#region CONSTUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public Transition(WebEngine webEngine) {
		super(webEngine);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Transition(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// ================ creating transitions ================================

	/**
	 * Specifies the transition delay in milliseconds.
	 * <p>
	 * All elements are given the same delay. The default delay is 0.
	 * <p>
	 * 
	 * @param milliseconds
	 *            the transition duration in milliseconds
	 * @return the current transition
	 */
	public Transition delay(int milliseconds) {
		JSObject result = call("delay", milliseconds);
		return new Transition(webEngine, result);
	}

	/**
	 * Specifies the transition delay of each selected element.
	 * <p>
	 * The function is evaluated for each selected element (in order), being
	 * passed the current datum d and the current index i, with the this context
	 * as the current DOM element.
	 * <p>
	 * The function's return value is then used to set each element's delay. The
	 * default delay is 0.
	 * <p>
	 * Setting the delay to be a multiple of the index i is a convenient way to
	 * stagger transitions for elements. For example, if you used a fixed
	 * duration of duration, and have n elements in the current selection, you
	 * can stagger the transition over 2 * duration by saying:
	 * <p>
	 * 
	 * <pre>
	 * 
	 * .delay(function(d, i) { return i / n * duration; })
	 * 
	 * </pre>
	 * <p>
	 * You may also compute the delay as a function of the data, thereby
	 * creating a data-driven animation.
	 * 
	 * the transition duration in milliseconds
	 * 
	 * @param func
	 * @return the current transition
	 */
	public Transition delay(DatumFunction<Integer> func) {
		
		assertObjectIsNotAnonymous(func);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, func);

		String command = "this.delay(function(d, i) { " //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);"//
				+ " });";

		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(memberName);
		
		return new Transition(webEngine, result);
	}

	/**
	 * Specifies transition duration in milliseconds of all elements. The
	 * default duration is 250ms.
	 * 
	 * @param milliseconds
	 *            the transition duration in milliseconds
	 * @return the current transition
	 */
	public Transition duration(int milliseconds) {
		JSObject result = call("duration", milliseconds);
		return new Transition(webEngine, result);
	}

	/**
	 * Specifies per-element duration in milliseconds of all elements, using the
	 * given function. The default duration is 250ms.
	 * 
	 * @param func
	 *            the function returning a transition duration in milliseconds
	 * @return the current transition
	 */
	public Transition duration(DatumFunction<Integer> func) {
		
		assertObjectIsNotAnonymous(func);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, func);

		String command = "this.duration(function(d, i) { " //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);"//
				+ " });";

		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(memberName);
		
		return new Transition(webEngine, result);
	}

	/**
	 * Specifies the transition {@link EasingFunction} to be used.
	 * <p>
	 * Built-in easing functions can be created using {@link Easing} factory or
	 * you may provide a custom implementation.
	 * <p>
	 * Note that it is not possible to customize the easing function per-element
	 * or per-attribute; however, if you use the "linear" easing function, you
	 * can apply custom easing inside your interpolator using
	 * {@link #attrTween(String, TweenFunction)} or {@link #styleTween()}
	 * <p>
	 * 
	 * @param callback
	 *            the easing function
	 * @return the current transition
	 */
	public Transition ease(EasingFunction callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, callback);

		String command = "this.ease(function(t) { " //
				+ "    d3." + memberName + ".ease(t);" //
				+ "  })";

		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(memberName);
		
		return new Transition(webEngine, result);
	}

	// ==================== attr =================================

	/**
	 * Transition the attribute with the specified name to the specified value
	 * on all selected elements.
	 * <p>
	 * The starting value of the transition is the current attribute value, and
	 * the ending value is the specified value.
	 * <p>
	 * A check is performed to see if the ending value represents a color of the
	 * form /^(#|rgb\(|hsl\()/, or one of the CSS named colors; if so, the
	 * starting value is coerced to an RGB color and
	 * {@link D3#interpolateRgb(Color, Color)} is used. Otherwise,
	 * interpolateString is used, which interpolates numbers embedded within
	 * strings.
	 * <p>
	 * null values are not supported because the interpolator would be
	 * undefined; if you want to remove the attribute after the transition
	 * finishes, listen to the end event.
	 * <p>
	 * 
	 * @param name
	 *            the name of the attribute
	 * @param value
	 *            the new value to transition to, must not be null
	 * @return the current transition
	 */
	public Transition attr(final String name, String value) {
		JSObject result = call("attr", name, value);
		return new Transition(webEngine, result);
	}

	/**
	 * See {@link #attr(String, String)}.
	 * <p>
	 * The startValue will be coerced to a number and interpolateNumber will be
	 * used to transition the attribute value.
	 * <p>
	 * 
	 * @param name
	 * @param value
	 * @return the current transition
	 */
	public Transition attr(final String name, double value) {
		JSObject result = call("attr", name, value);
		return new Transition(webEngine, result);
	}

	/**
	 * Transitions the attribute with the specified name to the value returned
	 * by the specified function on all selected elements.
	 * <p>
	 * The function is evaluated for each selected element (in order), being
	 * passed the current datum d and the current index i. The function's return
	 * value is then used to set each element's attribute.
	 * <p>
	 * Null values are not supported because the interpolator would be
	 * undefined; if you want to remove the attribute after the transition
	 * finishes, listen to the end event.
	 * <p>
	 * An interpolator is selected automatically based on the ending value. If
	 * the ending value is a number, the starting value is coerced to a number
	 * and interpolateNumber is used. If the ending value is a string, a check
	 * is performed to see if the string represents a color of the form
	 * /^(#|rgb\(|hsl\()/, or one of the CSS named colors; if so, the starting
	 * value is coerced to an RGB color and interpolateRgb is used. Otherwise,
	 * interpolateString is used, which interpolates numbers embedded within
	 * strings.
	 * <p>
	 * 
	 * @param name
	 *            the name of the attribute
	 * @param callback
	 *            the function used to compute the new value of the attribute
	 * @return the current transition
	 */
	public Transition attr(final String name, final DatumFunction<?> callback) {

		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, callback);

		String command = "this.attr('" + name + "', function(d, i) {" //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);" //
				+ "});";

		JSObject result = evalForJsObject(command);
		return new Transition(webEngine, result);
	}

	/**
	 * Transitions the attribute with the specified name to the specified
	 * {@link PathDataGenerator} value on all selected elements.
	 * <p>
	 * This method should always been used with a selection containing a svg
	 * &lt;path&gt; element by specifying "d" for the name argument.
	 * <p>
	 * The specified name may have a namespace prefix, such as xlink:href, to
	 * specify an "href" attribute in the XLink namespace. By default, D3
	 * supports svg, xhtml, xlink, xml, and xmlns namespaces. Additional
	 * namespaces can be registered by adding to d3.ns.prefix.
	 * <p>
	 * 
	 * @param name
	 *            the name of the attribute
	 * @param value
	 *            the new value to assign
	 * @return the current transition
	 */
	public Transition attr(final String name, PathDataGenerator pathDataGenerator) {
		JSObject generator = pathDataGenerator.getJsObject();
		JSObject result = call("duration", generator);
		return new Transition(webEngine, result);
	}

	/**
	 * Transitions the value of the attribute with the specified name according
	 * to the specified tween function.
	 * <p>
	 * The starting and ending value of the transition are determined by
	 * tweenFunction; the tween function is invoked when the transition starts
	 * on each element, being passed the current DOM element, the datum d, the
	 * current index i and the current attribute value a.
	 * <p>
	 * The return value of tween must be an interpolator: a function that maps a
	 * parametric value t in the domain [0,1] to a color, number or arbitrary
	 * value.
	 * 
	 * @see <a href="https:attrTween">Offical API</a>
	 * 
	 * @param name
	 *            the name of the attribute to transition
	 * @param tweenFunction
	 *            the function used to create an interpolator
	 */
	public Transition attrTween(String name, TweenFunction<?> tweenFunction) {
		
		assertObjectIsNotAnonymous(tweenFunction);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, tweenFunction);

		String command = "this.attrTween('" + name + "', function(d, i, a) { " + "var interpolator = d3." + memberName
				+ ".apply(this,{datum:d},i,{datum:a});" + "return interpolator;" + "});";

		JSObject result = evalForJsObject(command);
		return new Transition(webEngine, result);

	}

	// ================ style functions ================

	/**
	 * Transitions the value of the CSS style property with the specified name
	 * to the specified value.
	 * <p>
	 * The starting value of the transition is the current computed style
	 * property value, and the ending value is the specified value.
	 * <p>
	 * All elements are transitioned to the same style property value; Null
	 * values are not supported because the interpolator would be undefined; if
	 * you want to remove the style property after the transition finishes,
	 * listen to the end event.
	 * 
	 * An interpolator is selected automatically based on the ending value. If
	 * the ending value is a number, the starting value is coerced to a number
	 * and interpolateNumber is used. If the ending value is a string, a check
	 * is performed to see if the string represents a color of the form
	 * /^(#|rgb\(|hsl\()/, or one of the CSS named colors; if so, the starting
	 * value is coerced to an RGB color and interpolateRgb is used. Otherwise,
	 * interpolateString is used, which interpolates numbers embedded within
	 * strings.
	 * 
	 * Note that the computed starting value may be different than the value
	 * that was previously set, particularly if the style property was set using
	 * a shorthand property (such as the "font" style, which is shorthand for
	 * "font-size", "font-face", etc.). Moreover, computed dimensions such as
	 * "font-size" and "line-height" are always in pixels, so you should specify
	 * the ending value in pixels too if appropriate.
	 * <p>
	 * 
	 * @param name
	 *            the name of the style, such as font-size
	 * @param value
	 *            the ending value
	 * @return the current transition
	 */
	public Transition style(String name, String value) {
		JSObject result = call("style", name, value);
		return new Transition(webEngine, result);
	}

	/**
	 * See {@link Transition#style(String, T, boolean)}.
	 * 
	 * @param name
	 *            the name of the style, such as font-size
	 * @param value
	 *            the ending value
	 * @return the current transition
	 */
	public Transition style(String name, double value) {
		JSObject result = call("style", name, value);
		return new Transition(webEngine, result);
	}

	/**
	 * See {@link Transition#style(String, T, boolean)}.
	 * 
	 * @param name
	 *            the name of the style, such as font-size
	 * @param callback
	 *            the callback to be called
	 * @return the current transition
	 */
	public Transition style(String name, DatumFunction<?> callback) {

		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, callback);
		
		try{
			String command = "this.style('" + name + "', function(d, i) {" //
					+ "             try { "//
					+ "               var r = d3." + memberName + ".apply(this,{datum:d},i);" //
					+ "               return r;" //
					+ "             } catch (e) {"//
					+ "               alert(e); "//
					+ "               return null; "//
					+ "             }"//
					+ "          });";

			JSObject result = evalForJsObject(command);
			
			if (result == null) {
				return null;
			}

			return new Transition(webEngine, result);
			
		} catch (Exception exception){
			System.out.println("Could call style in Transition:\n" + exception.getMessage());
			return null;
		}

		

		
	}

	/**
	 * Transitions the value of the CSS style property with the specified name
	 * to the specified value.
	 * <p>
	 * The starting value of the transition is the current computed style
	 * property value, and the ending value is the specified value.
	 * <p>
	 * The function is evaluated for each selected element (in order), being
	 * passed the current datum d and the current index i, with the this context
	 * as the current DOM element.
	 * <p>
	 * The function's return value is then used to transition each element's
	 * style property. Null values are not supported because the interpolator
	 * would be undefined; if you want to remove the style property after the
	 * transition finishes, listen to the end event.
	 * <p>
	 * An interpolator is selected automatically based on the ending value. If
	 * the ending value is a number, the starting value is coerced to a number
	 * and interpolateNumber is used. If the ending value is a string, a check
	 * is performed to see if the string represents a color of the form
	 * /^(#|rgb\(|hsl\()/, or one of the CSS named colors; if so, the starting
	 * value is coerced to an RGB color and interpolateRgb is used. Otherwise,
	 * interpolateString is used, which interpolates numbers embedded within
	 * strings.
	 * <p>
	 * Note that the computed starting value may be different than the value
	 * that was previously set, particularly if the style property was set using
	 * a shorthand property (such as the "font" style, which is shorthand for
	 * "font-size", "font-face", etc.). Moreover, computed dimensions such as
	 * "font-size" and "line-height" are always in pixels, so you should specify
	 * the ending value in pixels too if appropriate.
	 * <p>
	 * 
	 * @param name
	 *            the name of the style to set
	 * @param callback
	 *            the function to be called on each element and returning the
	 *            value of the style
	 * @param important
	 *            true if the style value should be marked as !important, false
	 *            otherwise
	 * @return the current selection
	 */
	public Selection style(String name, DatumFunction<?> callback, boolean important) {

		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, callback);

		String impCommand = "var imp = " + important + " ? 'important' : null;";
		eval(impCommand);

		String command = "this.style('" + name + "'," //
				+ "  function(d, i) { "//
				+ "    var r = d3." + memberName + ".apply(this,{datum:d},i); "//
				+ "    return r ? r.toString() : null;"//
				+ "  },"//
				+ "imp);";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}

		return new Selection(webEngine, result);
	}

	/**
	 * Transitions the value of the CSS style property with the specified name
	 * according to the specified tween function.
	 * <p>
	 * The starting and ending value of the transition are determined by
	 * tweenFunction; the tween function is invoked when the transition starts
	 * on each element, being passed the current DOM element, the datum d, the
	 * current index i and the current attribute value a.
	 * <p>
	 * The return value of tween must be an interpolator: a function that maps a
	 * parametric value t in the domain [0,1] to a color, number or arbitrary
	 * value.
	 * <p>
	 * 
	 * @param name
	 *            the name of the style to set
	 * @param tweenFunction
	 *            the function to be called on each element and returning the
	 *            value of the style
	 * @param important
	 *            true if the style value should be marked as !important, false
	 *            otherwise
	 * @return the current selection
	 */
	public Selection styleTween(String name, TweenFunction<?> tweenFunction, boolean important) {
		
		assertObjectIsNotAnonymous(tweenFunction);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, tweenFunction);

		String impCommand = "var imp = " + important + " ? 'important' : null;";
		eval(impCommand);

		String command ="this.styleTween('" + name + "', " //
				+ "  function(d, i, a) {" //
				+ "    var interpolator = d3." + memberName + ".apply(this,{datum:d},i,{datum:a});" //
				+ "    return interpolator;" //
				+ "  }, " //
				+ "imp);";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}

		return new Selection(webEngine, result);

	}

	// ==================== text content ==========================
	/**
	 * The text operator is based on the textContent property; setting the text
	 * content will replace any existing child elements.
	 * <p>
	 * Set the text content to the specified value on all selected elements when
	 * the transition starts. All elements are given the same text content; a
	 * null value will clear the content.
	 * 
	 * @param value
	 *            the new text value to set
	 * @return the current transition
	 */
	public <T> Transition text(String value) {
		JSObject result = call("text", value);
		return new Transition(webEngine, result);
	}

	/**
	 * The text operator is based on the textContent property; setting the text
	 * content will replace any existing child elements.
	 * 
	 * Set the text content to the specified value on all selected elements when
	 * the transition starts.
	 * <p>
	 * The function is evaluated for each selected element (in order), being
	 * passed the current datum d and the current index i, with the this context
	 * as the current DOM element.
	 * <p>
	 * The function's return value is then used to set each element's text
	 * content.
	 * <p>
	 * A null value will clear the content.
	 * <p>
	 * 
	 * @param callback
	 *            the function used to compute the new text property
	 * @return the current transition
	 */
	public Transition text(final DatumFunction<String> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, callback);

		String command = "this.text(function(d, i) { " //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);"//
				+ "});";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}

		return new Transition(webEngine, result);

	}

	/**
	 * Registers a custom tween for the specified name.
	 * <p>
	 * When the transition starts, the specified factory function will be
	 * invoked for each selected element in the transition, so as to compute the
	 * tween function. If the factory returns null, then the tween is not run on
	 * the selected element.
	 * <p>
	 * This method is used internally by the attr and style tweens, and can be
	 * used to interpolate other document content.
	 * 
	 * @param name
	 *            the name of the property to register
	 * @param factory
	 *            the function returning an {@link Interpolator}
	 * @return the current transition
	 */
	public Transition tween(String name, DatumFunction<Interpolator<?>> factory) {
		
		assertObjectIsNotAnonymous(factory);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, factory);

		String command = "this.tween('" + name + "', function(d, i) { " //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);" //
				+ "});";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}

		return new Transition(webEngine, result);

	}

	/**
	 * Remove the selected elements at the end of a transition.
	 * <p>
	 * If a newer transition is scheduled on any of the selected elements, these
	 * elements will not be removed; however, the "end" event will still be
	 * dispatched.
	 * 
	 * 
	 * @return the current transition
	 */
	public Transition remove() {
		JSObject result = call("remove");
		return new Transition(webEngine, result);
	}

	// ====================== subtransition ====================

	/**
	 * For each element in the current selection, selects the first descendant
	 * element that matches the specified selector string.
	 * <p>
	 * If no element matches the specified selector for the current element, the
	 * element at the current index will be null in the returned transition;
	 * operators (with the exception of {@link Selection#data}) automatically
	 * skip null elements, thereby preserving the index of the existing
	 * selection.
	 * <p>
	 * If the current element has associated data, this data is inherited by the
	 * returned subselection, and automatically bound to the newly selected
	 * elements.
	 * <p>
	 * If multiple elements match the selector, only the first matching element
	 * in document traversal order will be selected.
	 * <p>
	 * In addition, the returned new transition inherits easing, duration and
	 * delay from the current transition.
	 * 
	 * @param selector
	 *            the selector
	 * @return the returned transition, containing at most only one element,
	 *         inheriting duration, delay and ease from the current transition
	 */
	public Transition select(String selector) {
		JSObject result = call("select", selector);
		return new Transition(webEngine, result);
	}

	/**
	 * For each element in the current transition, selects descendant elements
	 * that match the specified selector string.
	 * <p>
	 * The returned selection is grouped by the ancestor node in the current
	 * selection. If no element matches the specified selector for the current
	 * element, the group at the current index will be empty in the returned
	 * selection.
	 * <p>
	 * The subselection does not inherit data from the current selection;
	 * however, if data was previously bound to the selected elements, that data
	 * will be available to operators.
	 * <p>
	 * In addition, the returned new transition inherits easing, duration and
	 * delay from the current transition. The duration and delay for each
	 * subelement is inherited from the duration and delay of the parent element
	 * in the current transition.
	 * <p>
	 * 
	 * @param selector
	 * @return the sub transition, inheriting
	 */
	public Transition selectAll(String selector) {
		JSObject result = call("selectAll", selector);
		return new Transition(webEngine, result);
	}

	// ================ filter ======================

	/**
	 * Filters the transition, returning a new transition that contains only the
	 * elements for which the specified selector is true.
	 * <p>
	 * Like the built-in array filter method, the returned selection does not
	 * preserve the index of the original selection; it returns a copy with
	 * elements removed.
	 * <p>
	 * If you want to preserve the index, use select instead.
	 * <p>
	 * 
	 * @param selector
	 *            the CSS3 selector to be used as a filter
	 * @return a new transition containing the filtered elements
	 */
	public Transition filter(String selector) {
		JSObject result = call("filter", selector);
		return new Transition(webEngine, result);
	}

	/**
	 * Filters the transition, returning a new transition that contains only the
	 * elements for which the specified selector is true.
	 * <p>
	 * As with other operators, the function is passed the current datum d and
	 * index i, with the this context as the current DOM element.
	 * <p>
	 * Like the built-in array filter method, the returned selection does not
	 * preserve the index of the original selection; it returns a copy with
	 * elements removed. If you want to preserve the index, use select instead.
	 * <p>
	 * 
	 * @param datumFunction
	 *            the function to be used as a filter
	 * @return a new transition containing the filtered elements
	 */
	public Transition filter(final DatumFunction<Element> datumFunction) {
		
		assertObjectIsNotAnonymous(datumFunction);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, datumFunction);

		String command = "this.filter(function(d, i) { " //
				+ "return d3." + memberName + ".apply(this,{datum:d},i);" //
				+ "});";

		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(memberName);

		if (result == null) {
			return null;
		}

		return new Transition(webEngine, result);
	}

	/**
	 * Creates a new transition on the same selected elements that starts with
	 * this transition ends. The new transition inherits this transition’s
	 * duration and easing. This can be used to define chained transitions
	 * without needing to listen for "end" events.
	 * <p>
	 * 
	 * @return the new transition
	 */
	public Transition transition() {
		JSObject result = call("transition");
		return new Transition(webEngine, result);
	}

	/**
	 * Returns true if the current transition is empty; a transition is empty if
	 * it contains no non-null elements.
	 * 
	 * @return true if the transition is empty, false otherwise.
	 */
	public boolean empty() {
		Boolean result = callForBoolean("empty");
		return result;
	}

	/**
	 * Returns the first non-null element in the current transition. If the
	 * selection is empty, returns null.
	 * 
	 * @return the first non-null element in the current transition or null if
	 *         the selection is empty.
	 */
	public Element node() {
		JSObject result = call("node");
		return new Element(webEngine, result);
	}

	/**
	 * @return the total number of elements in the current transition.
	 */
	public int size() {
		int result = callForInteger("size");
		return result;
	}

	/**
	 * Type of transition event.
	 * <p> 
	 */
	public static enum EventType {
		START, END;

		public String getType() {
			return name().toLowerCase();
		}
	}

	/**
	 * Adds a listener for transition events. The listener will be invoked for
	 * each individual element in the transition.
	 * <p>
	 * The start event is invoked during the first asynchronous callback (tick)
	 * of the transition, before any tweens are invoked. For transitions with
	 * zero delay, this is typically about 17ms after the transition is
	 * scheduled. State events are useful for triggering instantaneous changes
	 * to each element, such as changing attributes that cannot be interpolated.
	 * <p>
	 * The end event is invoked during the last asynchronous callback (tick)
	 * after the transition duration and delay expires, after all tweens are
	 * invoked with t=1. Note that if the transition is superseded by a
	 * later-scheduled transition on a given element, no end event will be
	 * dispatched for that element; interrupted transitions do not trigger end
	 * events. For example, transition.remove schedules each element to be
	 * removed when the transition ends, but if the transition is interrupted,
	 * the element will not be removed. End events can be used as an alternative
	 * to transition.transition to create chained transitions by selecting the
	 * current element, this, and deriving a new transition.
	 * <p>
	 * Any transitions created during the end event will inherit the current
	 * transition parameters, including ID, time, easing, delay and duration.
	 * Thus, new transitions created within a parent transition.each will not
	 * the parent transition, similar to subtransitions.
	 * <p>
	 * 
	 * @param type
	 *            the type of event
	 * @param listener
	 *            the listener
	 * @return the current transition
	 */
	public Transition each(EventType type, DatumFunction<Void> listener) {
		
		assertObjectIsNotAnonymous(listener);

		String typeString = type.getType();

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, listener);

		String command = "this.each('" + typeString + "', function(d, i) {" //
				+ "d3." + memberName + ".apply(this,{datum:d},i);"//
				+ "});";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}

		return new Transition(webEngine, result);

	}

	/**
	 * Behaves similarly to {@link Selection#each(DatumFunction)}: immediately
	 * invokes the specified function for each element in the current
	 * transition, passing in the current datum d and index i, with the this
	 * context of the current DOM element.
	 * <p>
	 * Any transitions created during the end event will inherit the current
	 * transition parameters, including ID, time, easing, delay and duration.
	 * <p>
	 * Thus, new transitions created within a parent transition.each will not
	 * the parent transition, similar to subtransitions.
	 * 
	 * The transition.each method can be used to chain transitions and apply
	 * shared timing across a set of transitions.
	 * 
	 * @param listener
	 * @return
	 */
	public Transition each(DatumFunction<Void> listener) {
		
		assertObjectIsNotAnonymous(listener);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(memberName, listener);

		String command = "this.each(function(d, i) {" //
				+ "d3." + memberName + ".apply(this,{datum:d},i);" //
				+ "});";

		JSObject result = evalForJsObject(command);

		if (result == null) {
			return null;
		}
		return new Transition(webEngine, result);
	}

	/**
	 * Invokes the specified function once, passing in the current transition as
	 * a single parameter.
	 * 
	 * @param jsFunction
	 * @return the current transition
	 */
	public Transition call(JsFunction jsFunction) {
		
		assertObjectIsNotAnonymous(jsFunction);

		boolean isJavaScriptObject = jsFunction instanceof JavaScriptObject;
		if (!isJavaScriptObject) {
			String message = "The interface JsFunction must only by implemented "
					+ "by JavaScriptObjects. However its type is" + jsFunction.getClass().getName();
			throw new IllegalStateException(message);
		}
		JavaScriptObject javaScriptObject = (JavaScriptObject) jsFunction;
		JSObject functionJsObject = javaScriptObject.getJsObject();
		JSObject result = call("call", functionJsObject);
		return new Transition(webEngine, result);
	}

	//#end region
}
