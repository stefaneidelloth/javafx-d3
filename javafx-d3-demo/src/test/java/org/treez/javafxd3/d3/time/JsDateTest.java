package org.treez.javafxd3.d3.time;

import java.util.Date;

import org.treez.javafxd3.d3.time.JsDate;

import org.treez.javafxd3.d3.AbstractTestCase;

public class JsDateTest extends AbstractTestCase {

	@Override	
	@SuppressWarnings("deprecation")
	public void doTest() {
		
			long time = 283906800000L; 

			Date date = new Date(time);

			JsDate jsDate = JsDate.create(engine, time);

			//year			
			int expectedYear = date.getYear() + 1900;
			int year = jsDate.getFullYear();
			assertEquals("Year", expectedYear, year);
			
			//month
			int expectedMonth = date.getMonth();
			int month = jsDate.getMonth();			
			assertEquals("Month", expectedMonth, month);
			
			//day
			int expectedDay = date.getDay();
			int day = jsDate.getDay();			
			assertEquals("Day", expectedDay, day);
			
			//hour
			int expectedHour = date.getHours();
			int hour = jsDate.getHours();			
			assertEquals("Hour", expectedHour, hour);
			
			//minute
			int expectedMinute = date.getMinutes();
			int minute = jsDate.getMinutes();			
			assertEquals("Month", expectedMinute, minute);
			
			//second
			int expectedSecond = date.getSeconds();
			int second = jsDate.getSeconds();			
			assertEquals("Month", expectedSecond, second);
			
			//millisecond
			//int expectedMilliSeconds = date.getMilliseconds();
			//int milliSeconds = jsDate.getMilliseconds();			
			//assertEquals("Month", expectedMilliSeconds, milliSeconds);		

	}

}
