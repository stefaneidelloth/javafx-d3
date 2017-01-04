package org.treez.javafxd3.d3.time;

import java.util.Date;

import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeFormat;

import org.treez.javafxd3.d3.AbstractTestCase;

public class TimeFormatTest extends AbstractTestCase {

	@Override
	public void doTest() {
		parse();
		format();
		utc();
		iso();
	}

	@SuppressWarnings("deprecation")
	private int getTimeZoneOffset(double time) {
		Date date = new Date((long) time);
		return date.getTimezoneOffset();
	};

	private void parse() {
		TimeFormat format = d3.time().format("%b %Y");
		JsDate date = format.parse("Feb 2000");

		assertEquals(2000, date.getFullYear());
		assertEquals(1, date.getMonth());
		assertEquals(1, date.getDate());
		assertEquals(0, date.getHours());
		assertEquals(0, date.getMinutes());
		assertEquals(0, date.getSeconds());
		assertEquals(0, date.getMilliseconds());
	}

	private void format() {
		int tzOffset = getTimeZoneOffset(0);
		TimeFormat format = d3.time().format("%Y-%m-%d %H:%M:%S");

		JsDate jsDate = JsDate.create(webEngine, tzOffset * 60 * 1000);
		String strWithJsDate = format.apply(jsDate);

		Date date = new Date(tzOffset * 60 * 1000);
		String strWithDate = format.apply(date);

		assertEquals("1970-01-01 00:00:00", strWithJsDate);
		assertEquals("1970-01-01 00:00:00", strWithDate);
	}

	private void utc() {
		TimeFormat utcFormat = d3.time().format().utc("%Y-%m-%d %H:%M:%S");
		Date date = new Date(0);
		String str = utcFormat.apply(date);

		assertEquals("1970-01-01 00:00:00", str);
	}

	private void iso() {
		TimeFormat isoFormat = d3.time().format().iso();
		Date date = new Date(0);
		String str = isoFormat.apply(date);

		String expected = d3.time().format().utc("%Y-%m-%dT%H:%M:%S.%LZ").apply(date);
		assertEquals(expected, str);
	}

}
