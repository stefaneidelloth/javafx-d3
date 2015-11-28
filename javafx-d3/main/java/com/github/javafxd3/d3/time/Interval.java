package com.github.javafxd3.d3.time;


import java.util.Date;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.arrays.Array;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


/**
 * Time class performing simple time arithmetic.
 * <p>
 * Intervals are created using {@link D3#time()} factory methods, such as
 * {@link Time#second()}.
 * <p>
 * Note: intervals created by the factory methods of D3().time() are intervals
 * based on local time. Use the {@link Interval#utc()} method to convert the
 * interval to a UTC based interval.
 * 
 * <p>
 * Time intervals are irregular! For example, there are 60 seconds in a minute,
 * but 24 hours in a day. Even more confusing, some days have 23 or 25 hours due
 * to daylight saving time, and the standard Gregorian calendar uses months of
 * differing lengths. And then there are leap years and leap seconds!
 * <p>
 * To simplify manipulation of and iteration over time intervals, D3 provides a
 * handful of time utilities in addition to the time scale and format.
 * <p>
 * The utilities support both local time and UTC time. Local time is determined
 * by the browser's JavaScript runtime; arbitrary time zone support would be
 * nice, but requires access to the Olson zoneinfo files.
 * <p>
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Interval extends JavaScriptObject {

	//#region CONSTRUCTORS
	
	public Interval(WebEngine webEngine, JSObject wrappedJsObject){
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS

	// ============= UTC ============================

	/**
	 * Returns a corresponding time interval in UTC rather than local time. For
	 * example, D3().time().day().range(start, stop) returns local time days
	 * between start and stop, while D3().time().day().utc().range(start, stop)
	 * returns UTC days between start and stop.
	 * 
	 * @return the UTC interval
	 */
	public  Interval utc(){
		JSObject result = getMember("utc");
		return new Interval(webEngine, result);		
	}

	// ============= apply ============================

	/**
	 * Alias for {@link #floor(JsDate)}.
	 * 
	 * @param date
	 * @return
	 */
	public final JsDate apply(JsDate date) {
		return floor(date);
	}

	/**
	 * Alias for {@link #floor(Date)}.
	 * 
	 * @param date
	 * @return
	 */
	public final Date apply(Date date) {
		return floor(date);
	}

	/**
	 * Alias for {@link #floor(double)}.
	 * 
	 * @param date
	 * @return
	 */
	public final double apply(double date) {
		return floor(date);
	}

	// =============== floor methods =======================

	/**
	 * Return the latest time interval before or equal to the specified date.
	 * For example, D3.time().day().floor(new Date()) returns midnight (12:00
	 * AM) on the current day, in local time.
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date.
	 */
	public JsDate floor(JsDate date){
		JSObject jsDateObj = date.getJsObject();
		JSObject result = call("floor", jsDateObj);
		return new JsDate(webEngine, result);		
	}

	/**
	 * Alias for {@link #floor(JsDate)} for a Java date.
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date
	 */
	public final Date floor(Date date) {
		long time = (long) this.floor(JsDate.create(webEngine, date.getTime())).getTime();
		return new Date(time);
	}

	/**
	 * Alias for {@link #floor(JsDate)} for a timestamp.
	 * 
	 * @param date
	 * @return the date
	 */
	public final double floor(double date) {
		
		JsDate jsDate = JsDate.create(webEngine, date);
		double result =  this.floor(jsDate).getTime();
		return result;
	}

	// ================== round methods ======================

	/**
	 * Returns the closest time interval to the specified date.
	 * <p>
	 * For example, d3.time().day().round(new Date()) returns midnight (12:00
	 * AM) on the current day if it is on or before noon, and midnight of the
	 * following day if it is after noon.
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date
	 */
	public  JsDate round(JsDate date){
		JSObject jsDateObj = date.getJsObject();
		JSObject result = call("round", jsDateObj);
		return new JsDate(webEngine, result);
	}

	/**
	 * Alias for {@link #round(JsDate)} for a Java date.
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date
	 */
	public final Date round(Date date) {
		return new Date((long) this.round(JsDate.create(webEngine, date.getTime())).getTime());
	}

	/**
	 * Alias for {@link #round(JsDate)} for a timestamp.
	 * 
	 * @param date
	 * @return the date
	 */
	public final double round(double date) {
		return this.round(JsDate.create(webEngine, date)).getTime();
	}

	// ================== ceil methods ======================

	/**
	 * Returns the earliest time interval after the specified date.
	 * <p>
	 * For example, d3.time.day.ceil(new Date()) returns midnight (12:00 AM) on
	 * the following day, in local time.
	 * <p>
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date
	 */
	public  JsDate ceil(JsDate date){
		JSObject jsDateObj = date.getJsObject();
		JSObject result = call("ceil", jsDateObj);
		return new JsDate(webEngine, result);
	}

	/**
	 * Alias for {@link #ceil(JsDate)} for a Java date.
	 * 
	 * @param date
	 *            the date to convert
	 * @return the date
	 */
	public final Date ceil(Date date) {
		return new Date((long) this.ceil(JsDate.create(webEngine, date.getTime())).getTime());
	}

	/**
	 * Alias for {@link #round(JsDate)} for a timestamp.
	 * 
	 * @param date
	 * @return the date
	 */
	public final double ceil(double date) {
		return this.ceil(JsDate.create(webEngine, date)).getTime();
	}

	// ================== offset methds ======================

	/**
	 * Returns a new date equal to date plus step intervals.
	 * <p>
	 * If step is negative, then the returned date will be before the specified
	 * date; if step is zero, then a copy of the specified date is returned.
	 * <p>
	 * This method does not round the specified date to the interval. For
	 * example, if it is currently 5:34 PM, then d3.time.day.offset(new Date(),
	 * 1) returns 5:34 PM tomorrow (even if Daylight Savings Time changes!).
	 * 
	 * @param start
	 *            the start date
	 * @param step
	 *            the number of intervals to add to the start date
	 * @return the computed date
	 */
	public  JsDate offset(JsDate start, int step){
		JSObject jsDateObj = start.getJsObject();
		JSObject result = call("offset", jsDateObj, step);
		return new JsDate(webEngine, result);
	}

	/**
	 * Alias for {@link #offset(JsDate, int)} for double.
	 * 
	 * @param start
	 *            the start date
	 * @param step
	 *            the number of intervals to add to the start date
	 * @return the computed date
	 */
	public final double offset(double start, int step) {
		return this.offset(JsDate.create(webEngine, start), step).getTime();
	}

	/**
	 * Alias for {@link #offset(JsDate, int)} for java Date.
	 * 
	 * @param start
	 *            the start date
	 * @param step
	 *            the number of intervals to add to the start date
	 * @return the computed date
	 */
	public final Date offset(Date start, int step) {
		return new Date((long) this.offset(start.getTime(), step));
	}

	// ================== range methods ======================

	/**
	 * Returns every time interval after or equal to start and before stop.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public  Array<JsDate> range(JsDate start, JsDate stop){
		JSObject startObj = start.getJsObject();
		JSObject stopObj = stop.getJsObject();
		JSObject result = call("range", startObj, stopObj);
		return new Array<JsDate>(webEngine, result);
	}

	/**
	 * Alias for {@link #range(JsDate start, JsDate stop)} for java Date.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public final Array<JsDate> range(Date start, Date stop) {
		return range(start.getTime(), stop.getTime());
	}

	/**
	 * Alias for {@link #range(JsDate start, JsDate stop)} for double.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public final Array<JsDate> range(double start, double stop) {
		Array<JsDate> result = range(JsDate.create(webEngine, start), JsDate.create(webEngine, stop));
		return result;
	}

	/**
	 * Returns every time interval after or equal to start and before stop.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public  Array<JsDate> range(JsDate start, JsDate stop, double step){
		JSObject startObj = start.getJsObject();
		JSObject stopObj = stop.getJsObject();
		JSObject result = call("range", startObj, stopObj, step);
		return new Array<JsDate>(webEngine, result);		
	}

	/**
	 * Alias for {@link #range(JsDate start, JsDate stop, double step)} for java Date.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public final Array<JsDate> range(Date start, Date stop, double step) {
		return range(start.getTime(), stop.getTime(), step);
	}

	/**
	 * Alias for {@link #range(JsDate start, JsDate stop, double step)} for double.
	 * <p>
	 * 
	 * @param start
	 *            the start time
	 * @param stop
	 *            the stop time
	 * @return the interval
	 */
	public final Array<JsDate> range(double start, double stop, double step) {
		return range(JsDate.create(webEngine, start), JsDate.create(webEngine, stop), step);
	}

}
