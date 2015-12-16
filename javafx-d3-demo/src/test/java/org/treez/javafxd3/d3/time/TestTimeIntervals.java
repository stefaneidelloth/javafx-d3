package org.treez.javafxd3.d3.time;

import java.util.Date;

import org.junit.Test;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.time.Interval;
import org.treez.javafxd3.d3.time.JsDate;

import org.treez.javafxd3.d3.AbstractTestCase;

public class TestTimeIntervals extends AbstractTestCase {

	@Override	
	public void doTest() {

		D3 d3 = browser.getD3();

		test(d3.time().year(), "January 11, 1979 09:05:18", "January 1, 1979 00:00:00", "January 1, 1980 00:00:00",
				"January 1, 1979 00:00:00", "January 11, 1982 09:05:18");
		test(d3.time().month(), "January 11, 1979 09:05:18", "January 1, 1979 00:00:00", "February 1, 1979 00:00:00",
				"January 1, 1979 00:00:00", "April 11, 1979 09:05:18");
		test(d3.time().hour(), "January 11, 1979 09:05:18", "January 11, 1979 09:00:00", "January 11, 1979 10:00:00",
				"January 11, 1979 09:00:00", "January 11, 1979 12:05:18");
		test(d3.time().minute(), "January 11, 1979 09:05:18", "January 11, 1979 09:05:00", "January 11, 1979 09:06:00",
				"January 11, 1979 09:05:00", "January 11, 1979 09:08:18");
		test(d3.time().second(), "January 11, 1979 09:05:18", "January 11, 1979 09:05:18", "January 11, 1979 09:05:18",
				"January 11, 1979 09:05:18", "January 11, 1979 09:05:21");
		test(d3.time().day(), "January 11, 1979 09:05:18", "January 11, 1979 00:00:00", "January 12, 1979 00:00:00",
				"January 11, 1979 00:00:00", "January 14, 1979 09:05:18");
		test(d3.time().week(), "January 11, 1979 09:05:18", "January 7, 1979 00:00:00", "January 14, 1979 00:00:00",
				"January 14, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().sunday(), "January 11, 1979 09:05:18", "January 7, 1979 00:00:00", "January 14, 1979 00:00:00",
				"January 14, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().monday(), "January 11, 1979 09:05:18", "January 8, 1979 00:00:00", "January 15, 1979 00:00:00",
				"January 8, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().tuesday(), "January 11, 1979 09:05:18", "January 9, 1979 00:00:00", "January 16, 1979 00:00:00",
				"January 9, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().wednesday(), "January 11, 1979 09:05:18", "January 10, 1979 00:00:00",
				"January 17, 1979 00:00:00", "January 10, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().thursday(), "January 11, 1979 09:05:18", "January 11, 1979 00:00:00",
				"January 18, 1979 00:00:00", "January 11, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().friday(), "January 11, 1979 09:05:18", "January 5, 1979 00:00:00", "January 12, 1979 00:00:00",
				"January 12, 1979 00:00:00", "February 1, 1979 09:05:18");
		test(d3.time().saturday(), "January 11, 1979 09:05:18", "January 6, 1979 00:00:00", "January 13, 1979 00:00:00",
				"January 13, 1979 00:00:00", "February 1, 1979 09:05:18");
	}

	private void test(Interval interval, String givenDateStr, String expectedFloorDateStr, String expectedCeilDateStr,
			String expectedRoundDateStr, String expectedOffset3DateStr) {
		
			testLocalTime(interval, givenDateStr, expectedFloorDateStr, expectedCeilDateStr, expectedRoundDateStr,
					expectedOffset3DateStr);
			// testUTC(interval, givenDateStr, expectedFloorDateStr,
			// expectedCeilDateStr, expectedRoundDateStr,
			// expectedOffset3DateStr);	

	}

	private void testLocalTime(Interval interval, String givenDateStr, String expectedFloorDateStr,
			String expectedCeilDateStr, String expectedRoundDateStr, String expectedOffset3DateStr) {

		double givenDoubleDate = parseDate(givenDateStr);

		JsDate givenJsDate = JsDate.create(webEngine, givenDoubleDate);

		Date givenDate = new Date((long) givenDoubleDate);

		double expectedFloorDate = parseDate(expectedFloorDateStr);
		double floorDate = interval.apply(givenDoubleDate);

		assertDateEquals("apply on double", expectedFloorDate, floorDate);
		assertDateEquals("apply on JsDate", expectedFloorDate, interval.apply(givenJsDate).getTime());
		assertDateEquals("apply on Date", expectedFloorDate, interval.apply(givenDate).getTime());

		assertDateEquals("floor on double", expectedFloorDate, interval.floor(givenDoubleDate));
		assertDateEquals("floor on JsDate", expectedFloorDate, interval.floor(givenJsDate).getTime());
		assertDateEquals("floor on Date", expectedFloorDate, interval.floor(givenDate).getTime());

		double expectedCeilDate = parseDate(expectedCeilDateStr);
		assertDateEquals("ceil on double", expectedCeilDate, interval.ceil(givenDoubleDate));
		assertDateEquals("ceil on JsDate", expectedCeilDate, interval.ceil(givenJsDate).getTime());
		assertDateEquals("ceil on Date", expectedCeilDate, interval.ceil(givenDate).getTime());

		double expectedRoundDate = parseDate(expectedRoundDateStr);
		assertDateEquals("round on double", expectedRoundDate, interval.round(givenDoubleDate));
		assertDateEquals("round on JsDate", expectedRoundDate, interval.round(givenJsDate).getTime());
		assertDateEquals("round on Date", expectedRoundDate, interval.round(givenDate).getTime());

		double expectedOffset3Date = parseDate(expectedOffset3DateStr);
		assertDateEquals("offset 3 on double", expectedOffset3Date, interval.offset(givenDoubleDate, 3));
		assertDateEquals("offset 3 on JsDate", expectedOffset3Date, interval.offset(givenJsDate, 3).getTime());
		assertDateEquals("offset 3 on Date", expectedOffset3Date, interval.offset(givenDate, 3).getTime());

		JsDate givenJsStart = givenJsDate;
		JsDate givenJsEnd = JsDate.create(webEngine, expectedOffset3Date);
		assertEquals("range 3 on double", 3, interval.range(givenJsStart.getTime(), givenJsEnd.getTime()).length());
		assertEquals("range 3 on JsDate", 3, interval.range(givenJsStart, givenJsEnd).length());
		Date givenStart = givenDate;
		Date givenEnd = new Date((long) expectedOffset3Date);
		assertEquals("range 3 on Date", 3, interval.range(givenStart, givenEnd).length());

		// FIXME : very strange behaviour on this one
		// assertEquals("range 3 with step on double", 2,
		// interval.range(givenJsStart.getTime(), givenJsEnd.getTime(),
		// 2).length());
		// assertEquals("range 3 with step on JsDate", 2,
		// interval.range(givenJsStart, givenJsEnd, 2).length());
		// assertEquals("range 3 with step on Date", 2,
		// interval.range(givenStart, givenEnd, 2).length());
	}

	/*
	 * private void testUTC(Interval interval, String givenDateStr, String
	 * expectedFloorDateStr, String expectedCeilDateStr, String
	 * expectedRoundDateStr, String expectedOffset3DateStr) {
	 * 
	 * 
	 * double givenDoubleDate = parseDate(givenDateStr + " GMT+0000");
	 * 
	 * JsDate givenJsDate = JsDate.create(webEngine, givenDoubleDate); Date
	 * givenDate = new Date((long) givenDoubleDate);
	 * 
	 * double expectedFloorDate = parseDate(expectedFloorDateStr + " GMT+0000");
	 * assertDateEquals("apply on double", expectedFloorDate,
	 * interval.utc().apply(givenDoubleDate)); assertDateEquals(
	 * "apply on JsDate", expectedFloorDate,
	 * interval.utc().apply(givenJsDate).getTime()); assertDateEquals(
	 * "apply on Date", expectedFloorDate,
	 * interval.utc().apply(givenDate).getTime());
	 * 
	 * assertDateEquals("floor on double", expectedFloorDate,
	 * interval.utc().floor(givenDoubleDate)); assertDateEquals(
	 * "floor on JsDate", expectedFloorDate,
	 * interval.utc().floor(givenJsDate).getTime()); assertDateEquals(
	 * "floor on Date", expectedFloorDate,
	 * interval.utc().floor(givenDate).getTime());
	 * 
	 * double expectedCeilDate = parseDate(expectedCeilDateStr + " GMT+0000");
	 * assertDateEquals("ceil on double", expectedCeilDate,
	 * interval.utc().ceil(givenDoubleDate)); assertDateEquals("ceil on JsDate",
	 * expectedCeilDate, interval.utc().ceil(givenJsDate).getTime());
	 * assertDateEquals("ceil on Date", expectedCeilDate,
	 * interval.utc().ceil(givenDate).getTime());
	 * 
	 * double expectedRoundDate = parseDate(expectedRoundDateStr + " GMT+0000");
	 * assertDateEquals("round on double", expectedRoundDate,
	 * interval.utc().round(givenDoubleDate)); assertDateEquals(
	 * "round on JsDate", expectedRoundDate,
	 * interval.utc().round(givenJsDate).getTime()); assertDateEquals(
	 * "round on Date", expectedRoundDate,
	 * interval.utc().round(givenDate).getTime());
	 * 
	 * double expectedOffset3Date = parseDate(expectedOffset3DateStr +
	 * " GMT+0000"); assertDateEquals("offset 3 on double", expectedOffset3Date,
	 * interval.utc().offset(givenDoubleDate, 3)); assertDateEquals(
	 * "offset 3 on JsDate", expectedOffset3Date,
	 * interval.utc().offset(givenJsDate, 3).getTime()); assertDateEquals(
	 * "offset 3 on Date", expectedOffset3Date, interval.utc().offset(givenDate,
	 * 3).getTime());
	 * 
	 * JsDate givenJsStart = givenJsDate; JsDate givenJsEnd =
	 * JsDate.create(webEngine, expectedOffset3Date); assertEquals(
	 * "range 3 on double", 3, interval.utc().range(givenJsStart.getTime(),
	 * givenJsEnd.getTime()).length()); assertEquals("range 3 on JsDate", 3,
	 * interval.utc().range(givenJsStart, givenJsEnd).length()); Date givenStart
	 * = givenDate; Date givenEnd = new Date((long) expectedOffset3Date);
	 * assertEquals("range 3 on Date", 3, interval.utc().range(givenStart,
	 * givenEnd).length());
	 * 
	 * // FIXME : very strange behaviour on this one // assertEquals(
	 * "range 3 with step on double", 2,
	 * interval.utc().range(givenJsStart.getTime(), givenJsEnd.getTime(),
	 * 2).length()); // assertEquals("range 3 with step on JsDate", 2,
	 * interval.utc().range(givenJsStart, givenJsEnd, 2).length()); //
	 * assertEquals("range 3 with step on Date", 2,
	 * interval.utc().range(givenStart, givenEnd, 2).length()); }
	 */

	private double parseDate(String dateString) {
		
		double d = JsDate.parse(webEngine, dateString);
		if (Double.isNaN(d)) {
			throw new IllegalArgumentException("Invalid date : " + dateString);
		}
		
		return d;

	}
}
