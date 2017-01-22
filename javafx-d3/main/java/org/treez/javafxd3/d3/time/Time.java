package org.treez.javafxd3.d3.time;

import java.util.Date;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * D3 includes a helper module for parsing and formatting dates modeled after
 * the venerable <a href=
 * "http://pubs.opengroup.org/onlinepubs/009695399/functions/strptime.html" >
 * strptime</a> and
 * <a href="http://pubs.opengroup.org/onlinepubs/007908799/xsh/strftime.html" >
 * strftime</a> C-library standards. These functions are also notably available
 * in Python's <a href="http://docs.python.org/library/time.html">time</a>
 * module.
 * 
 * @author <a href="mailto:eric.citaire@gmail.com">Eric Citaire</a>
 * 
 */
public class Time extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Time(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// ========== scales =============
	/**
	 * Constructs a new time scale with the default domain and range; the ticks
	 * and tick format are configured for local time.
	 * <p>
	 * 
	 * @return the new scale
	 */
	public TimeScale scale() {
		JsObject result = call("scale");
		return new TimeScale(engine, result);
	}

	/**
	 * Constructs a new time scale with the default domain and range; the ticks
	 * and tick format are configured for UTC time.
	 * <p>
	 * 
	 * @return
	 */
	public TimeScale utc() {
		String command = "this.scale.utc()";
		JsObject result = evalForJsObject(command);
		return new TimeScale(engine, result);
	}

	/**
	 * Constructs a new local time formatter using the given <i>specifier</i>.
	 * The specifier string may contain the following directives.
	 * <ul>
	 * <li><code>%a</code> - abbreviated weekday name.
	 * <li><code>%A</code> - full weekday name.
	 * <li><code>%b</code> - abbreviated month name.
	 * <li><code>%B</code> - full month name.
	 * <li><code>%c</code> - date and time, as "%a %b %e %H:%M:%S %Y".
	 * <li><code>%d</code> - zero-padded day of the month as a decimal number
	 * [01,31].
	 * <li><code>%e</code> - space-padded day of the month as a decimal number [
	 * 1,31]; equivalent to %_d.
	 * <li><code>%H</code> - hour (24-hour clock) as a decimal number [00,23].
	 * <li><code>%I</code> - hour (12-hour clock) as a decimal number [01,12].
	 * <li><code>%j</code> - day of the year as a decimal number [001,366].
	 * <li><code>%L</code> - milliseconds as a decimal number [000, 999].
	 * <li><code>%m</code> - month as a decimal number [01,12].
	 * <li><code>%M</code> - minute as a decimal number [00,59].
	 * <li><code>%p</code> - either AM or PM.
	 * <li><code>%S</code> - second as a decimal number [00,61].
	 * <li><code>%U</code> - week number of the year (Sunday as the first day of
	 * the week) as a decimal number [00,53].
	 * <li><code>%w</code> - weekday as a decimal number [0(Sunday),6].
	 * <li><code>%W</code> - week number of the year (Monday as the first day of
	 * the week) as a decimal number [00,53].
	 * <li><code>%x</code> - date, as "%m/%d/%y".
	 * <li><code>%X</code> - time, as "%H:%M:%S".
	 * <li><code>%y</code> - year without century as a decimal number [00,99].
	 * <li><code>%Y</code> - year with century as a decimal number.
	 * <li><code>%Z</code> - time zone offset, such as "-0700".
	 * <li><code>%%</code> - a literal "%" character.
	 * </ul>
	 * For %U, all days in a new year preceding the first Sunday are considered
	 * to be in week 0. For %W, all days in a new year preceding the first
	 * Monday are considered to be in week 0. In some implementations of
	 * strftime and strptime (as in Python), a directive may include an optional
	 * field width or precision; this feature is not yet implemented in D3, but
	 * may be added in the future. Also note that the JavaScript environment
	 * does not provide a standard API for accessing locale-specific date and
	 * time formatters, so D3's implementation is fixed to a locale at compile
	 * time based on the $LOCALE environment variable.
	 * <p>
	 * The % sign indicating a directive may be immediately followed by a
	 * padding modifier:
	 * <p>
	 * <ul>
	 * <li><code>0</code> - zero-padding
	 * <li><code>_</code> - space-padding
	 * <li><code>-</code> - disable padding
	 * </ul>
	 * <p>
	 * If no padding modifier is specified, the default is <code>0</code> for
	 * all directives, except for <code>%e</code> which defaults to
	 * <code>_</code>).
	 * 
	 * @see <a href=
	 *      "https://github.com/mbostock/d3/wiki/Time-Formatting#wiki-format">
	 *      Official API documentation</a>
	 * 
	 * @param specifier
	 *            the specifier string.
	 * @return the formatted string.
	 */
	public TimeFormat format(String specifier) {
		JsObject result = call("format", specifier);
		return new TimeFormat(engine, result);
	}

	/**
	 * Create a TimeFormat builder.
	 * 
	 * @return the builder.
	 */
	public TimeFormat.Builder format() {
		JsObject result = getMember("format");
		return new TimeFormat.Builder(engine, result);
	}

	// ================== Intervals ====================

	/**
	 * Factory for interval of Seconds (e.g., 01:23:45.0000 AM). Always 1,000
	 * milliseconds long.
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval second() {
		JsObject result = getMember("second");
		return new Interval(engine, result);
	}

	/**
	 * Factory for interval of Minutes (e.g., 01:02:00 AM). Most browsers do not
	 * support leap seconds, so minutes are almost always 60 seconds (6e4
	 * milliseconds) long.
	 * 
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval minute() {
		JsObject result = getMember("minute");
		return new Interval(engine, result);
	}

	/**
	 * Factory for interval of Hours (e.g., 01:00 AM). 60 minutes long (36e5
	 * milliseconds). Note that advancing time by one hour can return the same
	 * hour number, or skip an hour number, due to Daylight Savings Time.
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval hour() {
		JsObject result = getMember("hour");
		return new Interval(engine, result);
	}

	/**
	 * Days (e.g., February 7, 2012 at 12:00 AM). Most days are 24 hours long
	 * (864e5 milliseconds); however, with Daylight Savings Time, a day may be
	 * 23 or 25 hours long.
	 * 
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval day() {
		JsObject result = getMember("day");
		return new Interval(engine, result);
	}

	/**
	 * Alias for {@link #sunday}. A week is always 7 days, but ranges between
	 * 167 and 169 hours depending on Daylight Savings Time.
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval week() {
		JsObject result = getMember("week");
		return new Interval(engine, result);
	}

	/**
	 * Sunday-based weeks (e.g., February 5, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval sunday() {
		JsObject result = getMember("sunday");
		return new Interval(engine, result);
	}

	/**
	 * Monday-based weeks (e.g., February 6, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval monday() {
		JsObject result = getMember("monday");
		return new Interval(engine, result);
	}

	/**
	 * Tuesday-based weeks (e.g., February 7, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval tuesday() {
		JsObject result = getMember("tuesday");
		return new Interval(engine, result);
	}

	/**
	 * Wednesday-based weeks (e.g., February 8, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval wednesday() {
		JsObject result = getMember("wednesday");
		return new Interval(engine, result);
	}

	/**
	 * Thursday-based weeks (e.g., February 9, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval thursday() {
		JsObject result = getMember("thursday");
		return new Interval(engine, result);
	}

	/**
	 * Friday-based weeks (e.g., February 10, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval friday() {
		JsObject result = getMember("friday");
		return new Interval(engine, result);
	}

	/**
	 * Saturday-based weeks (e.g., February 11, 2012 at 12:00 AM).
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval saturday() {
		JsObject result = getMember("saturday");
		return new Interval(engine, result);
	}

	/**
	 * Months (e.g., February 1, 2012 at 12:00 AM). Ranges between 28 and 31
	 * days.
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval month() {
		JsObject result = getMember("month");
		return new Interval(engine, result);
	}

	/**
	 * Years (e.g., January 1, 2012 at 12:00 AM). Normal years are 365 days
	 * long; leap years are 366.
	 * <p>
	 * 
	 * @return the {@link Interval}
	 */
	public Interval year() {
		JsObject result = getMember("year");
		return new Interval(engine, result);
	}

	/**
	 * Alias for {@link #second().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final JsDate start, final JsDate stop) {
		return second().range(start, stop);
	}

	/**
	 * Alias for {@link #second().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final Date start, final Date stop) {
		return second().range(start, stop);
	}

	/**
	 * Alias for {@link #second().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final double start, final double stop) {
		return second().range(start, stop);
	}

	/**
	 * Alias for {@link #second().range(JsDate start, JsDate stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final JsDate start, final JsDate stop, final double step) {
		return second().range(start, stop, step);
	}

	/**
	 * Alias for {@link #second().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final Date start, final Date stop, final double step) {
		return second().range(start, stop, step);
	}

	/**
	 * Alias for {@link #second().range(double start, double stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> seconds(final double start, final double stop, final double step) {
		return second().range(start, stop, step);
	}

	/**
	 * Alias for {@link #minute().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final JsDate start, final JsDate stop) {
		return minute().range(start, stop);
	}

	/**
	 * Alias for {@link #minute().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final Date start, final Date stop) {
		return minute().range(start, stop);
	}

	/**
	 * Alias for {@link #minute().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final double start, final double stop) {
		return minute().range(start, stop);
	}

	/**
	 * Alias for {@link #minute().range(JsDate start, JsDate stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final JsDate start, final JsDate stop, final double step) {
		return minute().range(start, stop, step);
	}

	/**
	 * Alias for {@link #minute().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final Date start, final Date stop, final double step) {
		return minute().range(start, stop, step);
	}

	/**
	 * Alias for {@link #minute().range(double start, double stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> minutes(final double start, final double stop, final double step) {
		return minute().range(start, stop, step);
	}

	/**
	 * Alias for {@link #hour().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final JsDate start, final JsDate stop) {
		return hour().range(start, stop);
	}

	/**
	 * Alias for {@link #hour().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final Date start, final Date stop) {
		return hour().range(start, stop);
	}

	/**
	 * Alias for {@link #hour().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final double start, final double stop) {
		return hour().range(start, stop);
	}

	/**
	 * Alias for {@link #hour().range(JsDate start, JsDate stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final JsDate start, final JsDate stop, final double step) {
		return hour().range(start, stop, step);
	}

	/**
	 * Alias for {@link #hour().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final Date start, final Date stop, final double step) {
		return hour().range(start, stop, step);
	}

	/**
	 * Alias for {@link #hour().range(double start, double stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> hours(final double start, final double stop, final double step) {
		return hour().range(start, stop, step);
	}

	/**
	 * Alias for {@link #day().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final JsDate start, final JsDate stop) {
		return day().range(start, stop);
	}

	/**
	 * Alias for {@link #day().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final Date start, final Date stop) {
		return day().range(start, stop);
	}

	/**
	 * Alias for {@link #day().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final double start, final double stop) {
		return day().range(start, stop);
	}

	/**
	 * Alias for {@link #day().range(JsDate start, JsDate stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final JsDate start, final JsDate stop, final double step) {
		return day().range(start, stop, step);
	}

	/**
	 * Alias for {@link #day().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final Date start, final Date stop, final double step) {
		return day().range(start, stop, step);
	}

	/**
	 * Alias for {@link #day().range(double start, double stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> days(final double start, final double stop, final double step) {
		return day().range(start, stop, step);
	}

	/**
	 * Alias for {@link #week().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final JsDate start, final JsDate stop) {
		return week().range(start, stop);
	}

	/**
	 * Alias for {@link #week().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final Date start, final Date stop) {
		return week().range(start, stop);
	}

	/**
	 * Alias for {@link #week().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final double start, final double stop) {
		return week().range(start, stop);
	}

	/**
	 * Alias for {@link #week().range(JsDate start, JsDate stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final JsDate start, final JsDate stop, final double step) {
		return week().range(start, stop, step);
	}

	/**
	 * Alias for {@link #week().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final Date start, final Date stop, final double step) {
		return week().range(start, stop, step);
	}

	/**
	 * Alias for {@link #week().range(double start, double stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> weeks(final double start, final double stop, final double step) {
		return week().range(start, stop, step);
	}

	/**
	 * Alias for {@link #sunday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final JsDate start, final JsDate stop) {
		return sunday().range(start, stop);
	}

	/**
	 * Alias for {@link #sunday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final Date start, final Date stop) {
		return sunday().range(start, stop);
	}

	/**
	 * Alias for {@link #sunday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final double start, final double stop) {
		return sunday().range(start, stop);
	}

	/**
	 * Alias for {@link #sunday().range(JsDate start, JsDate stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final JsDate start, final JsDate stop, final double step) {
		return sunday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #sunday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final Date start, final Date stop, final double step) {
		return sunday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #sunday().range(double start, double stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> sundays(final double start, final double stop, final double step) {
		return sunday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #monday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final JsDate start, final JsDate stop) {
		return monday().range(start, stop);
	}

	/**
	 * Alias for {@link #monday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final Date start, final Date stop) {
		return monday().range(start, stop);
	}

	/**
	 * Alias for {@link #monday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final double start, final double stop) {
		return monday().range(start, stop);
	}

	/**
	 * Alias for {@link #monday().range(JsDate start, JsDate stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final JsDate start, final JsDate stop, final double step) {
		return monday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #monday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final Date start, final Date stop, final double step) {
		return monday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #monday().range(double start, double stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> mondays(final double start, final double stop, final double step) {
		return monday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #tuesday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final JsDate start, final JsDate stop) {
		return tuesday().range(start, stop);
	}

	/**
	 * Alias for {@link #tuesday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final Date start, final Date stop) {
		return tuesday().range(start, stop);
	}

	/**
	 * Alias for {@link #tuesday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final double start, final double stop) {
		return tuesday().range(start, stop);
	}

	/**
	 * Alias for {@link #tuesday().range(JsDate start, JsDate stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final JsDate start, final JsDate stop, final double step) {
		return tuesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #tuesday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final Date start, final Date stop, final double step) {
		return tuesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #tuesday().range(double start, double stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> tuesdays(final double start, final double stop, final double step) {
		return tuesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #wednesday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final JsDate start, final JsDate stop) {
		return wednesday().range(start, stop);
	}

	/**
	 * Alias for {@link #wednesday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final Date start, final Date stop) {
		return wednesday().range(start, stop);
	}

	/**
	 * Alias for {@link #wednesday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final double start, final double stop) {
		return wednesday().range(start, stop);
	}

	/**
	 * Alias for {@link #wednesday().range(JsDate start, JsDate stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final JsDate start, final JsDate stop, final double step) {
		return wednesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #wednesday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final Date start, final Date stop, final double step) {
		return wednesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #wednesday().range(double start, double stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> wednesdays(final double start, final double stop, final double step) {
		return wednesday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #thursday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final JsDate start, final JsDate stop) {
		return thursday().range(start, stop);
	}

	/**
	 * Alias for {@link #thursday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final Date start, final Date stop) {
		return thursday().range(start, stop);
	}

	/**
	 * Alias for {@link #thursday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final double start, final double stop) {
		return thursday().range(start, stop);
	}

	/**
	 * Alias for {@link #thursday().range(JsDate start, JsDate stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final JsDate start, final JsDate stop, final double step) {
		return thursday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #thursday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final Date start, final Date stop, final double step) {
		return thursday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #thursday().range(double start, double stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> thursdays(final double start, final double stop, final double step) {
		return thursday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #friday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final JsDate start, final JsDate stop) {
		return friday().range(start, stop);
	}

	/**
	 * Alias for {@link #friday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final Date start, final Date stop) {
		return friday().range(start, stop);
	}

	/**
	 * Alias for {@link #friday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final double start, final double stop) {
		return friday().range(start, stop);
	}

	/**
	 * Alias for {@link #friday().range(JsDate start, JsDate stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final JsDate start, final JsDate stop, final double step) {
		return friday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #friday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final Date start, final Date stop, final double step) {
		return friday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #friday().range(double start, double stop, double step)}
	 * .
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> fridays(final double start, final double stop, final double step) {
		return friday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #saturday().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final JsDate start, final JsDate stop) {
		return saturday().range(start, stop);
	}

	/**
	 * Alias for {@link #saturday().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final Date start, final Date stop) {
		return saturday().range(start, stop);
	}

	/**
	 * Alias for {@link #saturday().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final double start, final double stop) {
		return saturday().range(start, stop);
	}

	/**
	 * Alias for {@link #saturday().range(JsDate start, JsDate stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final JsDate start, final JsDate stop, final double step) {
		return saturday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #saturday().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final Date start, final Date stop, final double step) {
		return saturday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #saturday().range(double start, double stop, double
	 * step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> saturdays(final double start, final double stop, final double step) {
		return saturday().range(start, stop, step);
	}

	/**
	 * Alias for {@link #month().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final JsDate start, final JsDate stop) {
		return month().range(start, stop);
	}

	/**
	 * Alias for {@link #month().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final Date start, final Date stop) {
		return month().range(start, stop);
	}

	/**
	 * Alias for {@link #month().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final double start, final double stop) {
		return month().range(start, stop);
	}

	/**
	 * Alias for {@link #month().range(JsDate start, JsDate stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final JsDate start, final JsDate stop, final double step) {
		return month().range(start, stop, step);
	}

	/**
	 * Alias for {@link #month().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final Date start, final Date stop, final double step) {
		return month().range(start, stop, step);
	}

	/**
	 * Alias for {@link #month().range(double start, double stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> months(final double start, final double stop, final double step) {
		return month().range(start, stop, step);
	}

	/**
	 * Alias for {@link #year().range(JsDate start, JsDate stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final JsDate start, final JsDate stop) {
		return year().range(start, stop);
	}

	/**
	 * Alias for {@link #year().range(Date start, Date stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final Date start, final Date stop) {
		return year().range(start, stop);
	}

	/**
	 * Alias for {@link #year().range(double start, double stop)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final double start, final double stop) {
		return year().range(start, stop);
	}

	/**
	 * Alias for {@link #year().range(JsDate start, JsDate stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final JsDate start, final JsDate stop, final double step) {
		return year().range(start, stop, step);
	}

	/**
	 * Alias for {@link #year().range(Date start, Date stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final Date start, final Date stop, final double step) {
		return year().range(start, stop, step);
	}

	/**
	 * Alias for {@link #year().range(double start, double stop, double step)}.
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public final Array<JsDate> years(final double start, final double stop, final double step) {
		return year().range(start, stop, step);
	}

	/**
	 * Returns the day number for the given date. The first day of the year
	 * (January 1) is always the 0th day. Unlike the d3.time.format's %j
	 * directive, dayOfYear is 0-based rather than 1-based.
	 * 
	 * @param date
	 *            the given date
	 * @return the day number
	 */
	public int dayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("dayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #dayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int dayOfYear(final Date date) {
		return this.dayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #dayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int dayOfYear(final double date) {
		return this.dayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int weekOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("weekOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #weekOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int weekOfYear(final Date date) {
		return this.weekOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #weekOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int weekOfYear(final double date) {
		return this.weekOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int sundayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("sundayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #sundayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int sundayOfYear(final Date date) {
		return this.sundayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #sundayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int sundayOfYear(final double date) {
		return this.sundayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int mondayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("mondayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #mondayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int mondayOfYear(final Date date) {
		return this.mondayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #mondayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int mondayOfYear(final double date) {
		return this.mondayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int tuesdayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("tuesdayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #tuesdayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int tuesdayOfYear(final Date date) {
		return this.tuesdayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #tuesdayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int tuesdayOfYear(final double date) {
		return this.tuesdayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int wednesdayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("wednesdayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #wednesdayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int wednesdayOfYear(final Date date) {
		return this.wednesdayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #wednesdayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int wednesdayOfYear(final double date) {
		return this.wednesdayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int thursdayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("thursOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #thursdayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int thursdayOfYear(final Date date) {
		return this.thursdayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #thursdayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int thursdayOfYear(final double date) {
		return this.thursdayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int fridayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("fridayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #fridayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int fridayOfYear(final Date date) {
		return this.fridayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #fridayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int fridayOfYear(final double date) {
		return this.fridayOfYear(JsDate.create(engine, date));
	}

	/**
	 * Returns the week number for the given date, where weeks start with the
	 * given day. The first day of the year (January 1) is always the 0th week.
	 * weekOfYear is an alias for sundayOfYear, which is equivalent to
	 * d3.time.format's %U directive. mondayOfYear is equivalent to
	 * d3.time.format's %W directive.
	 * 
	 * @param date
	 *            the given date
	 * @return the week number
	 */
	public int saturdayOfYear(JsDate date) {
		JsObject dateObj = date.getJsObject();
		int result = callForInteger("saturdayOfYear", dateObj);
		return result;
	}

	/**
	 * Alias for {@link #saturdayOfYear(JsDate)} for a Java date.
	 * 
	 * @param date
	 * @return
	 */
	public final int saturdayOfYear(final Date date) {
		return this.saturdayOfYear(JsDate.create(engine, date.getTime()));
	}

	/**
	 * Alias for {@link #saturdayOfYear(JsDate)} for a double.
	 * 
	 * @param date
	 * @return
	 */
	public final int saturdayOfYear(final double date) {
		return this.saturdayOfYear(JsDate.create(engine, date));
	}

	//#end region
}
