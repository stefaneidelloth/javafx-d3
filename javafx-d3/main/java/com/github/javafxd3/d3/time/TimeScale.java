package com.github.javafxd3.d3.time;

import java.sql.Time;

import com.github.javafxd3.d3.arrays.Array;
import com.github.javafxd3.d3.core.Formatter;
import com.github.javafxd3.d3.scales.ContinuousQuantitativeScale;
import com.github.javafxd3.d3.scales.LinearScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * D3's time scale is an extension of {@link LinearScale} that uses JavaScript
 * Date objects as the domain representation.
 * <p>
 * Thus, unlike the normal linear scale, domain values are coerced to dates
 * rather than numbers; similarly, the {@link #invert(double)} function returns
 * a date.
 * <p>
 * Most conveniently, the time scale also provides suitable ticks based on time
 * intervals, taking the pain out of generating axes for nearly any time-based
 * domain.
 * <p>
 * A scale object, such as that returned by d3.time.scale, is both an object and
 * a function. That is: you can call the scale like any other function, and the
 * scale has additional methods that change its behavior. Like other classes in
 * D3, scales follow the method chaining pattern where setter methods return the
 * scale itself, allowing multiple setters to be invoked in a concise statement.
 * <p>
 * The domain may be set as an array of two or more dates. If the elements in
 * the given array are not dates, they will be coerced to dates; this coercion
 * happens similarly when the scale is called.
 * <p>
 * Although time scales typically have just two dates in their domain, you can
 * specify more than two dates for a polylinear scale. In this case, there must
 * be an equivalent number of values in the output range.
 * <p> 
 */
public class TimeScale extends ContinuousQuantitativeScale<TimeScale> {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public TimeScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// -- create

	@Override
	public TimeScale createScale(WebEngine webEngine, JSObject result) {
		return new TimeScale(webEngine, result);
	}

	/**
	 * Extends the domain so that it starts and ends on nice round values as
	 * determined by the specified time interval.
	 * <p>
	 * This method typically modifies the scale's domain, and may only extend
	 * the bounds to the nearest round value.
	 * <p>
	 * Nicing is useful if the domain is computed from data and may be
	 * irregular. For example, for a domain of [2009-07-13T00:02,
	 * 2009-07-13T23:48], the nice domain is [2009-07-13,2009-07-14].
	 * <p>
	 * If the domain has more than two values, nicing the domain only affects
	 * the first and last value.
	 * <p>
	 * 
	 * @param interval
	 * @return the current scale
	 */
	public TimeScale nice(Interval interval) {
		JSObject intervalObj = interval.getJsObject();
		JSObject result = call("nice", intervalObj);
		return new TimeScale(webEngine, result);
	}

	/**
	 * Extends the domain so that it starts and ends on nice round values as
	 * determined by the specified time interval and step count.nd optional step
	 * count. As an alternative to specifying an explicit time interval, a
	 * numeric count can be specified, and a time interval will be chosen
	 * automatically to be consistent with scale.ticks.
	 * <p>
	 * This method typically modifies the scale's domain, and may only extend
	 * the bounds to the nearest round value.
	 * <p>
	 * Nicing is useful if the domain is computed from data and may be
	 * irregular. For example, for a domain of [2009-07-13T00:02,
	 * 2009-07-13T23:48], the nice domain is [2009-07-13,2009-07-14].
	 * <p>
	 * If the domain has more than two values, nicing the domain only affects
	 * the first and last value.
	 * <p>
	 * 
	 * @param interval
	 * @param step
	 * @return the current scale
	 */
	public TimeScale nice(Interval interval, int step) {
		JSObject intervalObj = interval.getJsObject();
		JSObject result = call("nice", intervalObj, step);
		return new TimeScale(webEngine, result);
	}

	/**
	 * Returns representative dates from the scale's input domain.
	 * <p>
	 * The returned tick dates are uniformly spaced (modulo irregular time
	 * intervals, such as months and leap years), have human-readable values
	 * (such as midnights), and are guaranteed to be within the extent of the
	 * input domain.
	 * <p>
	 * Ticks are often used to display reference lines, or tick marks, in
	 * conjunction with the visualized data.
	 * <p>
	 * Approximately <code>count</code> ticks will be returned. The specified
	 * count is only a hint; the scale may return more or fewer values depending
	 * on the input domain.
	 * <p>
	 * For example, to create 10 default ticks, say:
	 * <code>scale.ticks(10);</code>
	 * <p>
	 * 
	 * @param count
	 *            the number of ticks to be returned; it is only a hint; the
	 *            scale may return more or fewer values depending on the input
	 *            domain
	 * @return the array of reference ticks
	 */
	public <T> Array<T> ticks(int count) {
		JSObject result = call("ticks", count);
		return new Array<T>(webEngine, result);
	}

	/**
	 * Alias for {@link #ticks(int) ticks(10)}.
	 * 
	 * @return the array of reference ticks
	 */
	public <T> Array<T> ticks() {
		JSObject result = call("ticks");
		return new Array<T>(webEngine, result);
	}

	/**
	 * Returns representative dates from the scale's input domain.
	 * <p>
	 * The returned tick dates are uniformly spaced (modulo irregular time
	 * intervals, such as months and leap years), have human-readable values
	 * (such as midnights), and are guaranteed to be within the extent of the
	 * input domain.
	 * <p>
	 * Ticks are often used to display reference lines, or tick marks, in
	 * conjunction with the visualized data.
	 * <p>
	 * To create ticks at 15-minute intervals, say:
	 * 
	 * <pre>
	 * <code>
	 * scale.ticks(D3.time().minute().utc(), 15);
	 * </code>
	 * </pre>
	 * 
	 * Note: for UTC scales, make sure to use the appropriate UTC range method
	 * (such as d3.time.minutes.utc). The following time intervals are
	 * considered for automatic ticks:
	 * <ul>
	 * <li>1-, 5-, 15- and 30- {@link Time#second()} .
	 * <li>1-, 5-, 15- and 30-{@link Time#minute()}.
	 * <li>1-, 3-, 6- and 12-{@link Time#hour()}.
	 * <li>1- and 2-{@link Time#day()}.
	 * <li>1-{@link Time#week()}.
	 * <li>1- and 3-{@link Time#month()}.
	 * <li>1-{@link Time#year()}.
	 * </ul>
	 * This set of time intervals is somewhat arbitrary and additional values
	 * may be added in the future.
	 * <p>
	 * 
	 * @param interval
	 *            the interval of time
	 * @param steps
	 *            the number of interval between each tick
	 * @return the array of reference ticks
	 */
	public <T> Array<T> ticks(Interval interval, int steps) {
		JSObject intervalObj = interval.getJsObject();
		JSObject result = call("ticks", intervalObj, steps);
		return new Array<T>(webEngine, result);
	}

	// =========== tickFormat ==========

	/**
	 * Returns a time {@link Formatter} function suitable for displaying a tick
	 * value.
	 * <p>
	 * The specified count should have the same value as the count that is used
	 * to generate the tick values. You don't have to use the scale's built-in
	 * tick format, but it automatically computes the appropriate display based
	 * on the input date.
	 * <p>
	 * The following time formats are considered:
	 * <ul>
	 * <li>%Y - for year boundaries, such as "2011".
	 * <li>%B - for month boundaries, such as "February".
	 * <li>%b %d - for week boundaries, such as "Feb 06".
	 * <li>%a %d - for day boundaries, such as "Mon 07".
	 * <li>%I %p - for hour boundaries, such as "01 AM".
	 * <li>%I:%M - for minute boundaries, such as "01:23".
	 * <li>:%S - for second boundaries, such as ":45".
	 * <li>.%L - milliseconds for all other times, such as ".012".
	 * </ul>
	 * By using multi-scale time formats, the default tick format provides both
	 * local and global context for each time interval.
	 * <p>
	 * For example, by showing the sequence [11 PM, Mon 07, 01 AM], the tick
	 * formatter reveals information about hours, dates, and day simultaneously
	 * rather than just the hours.
	 * <p>
	 * If you'd prefer single-scale time formatting, you can always use your own
	 * d3.time.format. You can also roll your own custom <a href="http:scale
	 * time format</a>.
	 * 
	 * 
	 * @param count
	 *            number of ticks to take into account to create the
	 *            {@link Formatter}.
	 * @return a number format
	 */
	public Formatter tickFormat(int count) {
		JSObject result = call("tickFourmat", count);
		return new Formatter(webEngine, result);
	}

	//#end region

}
