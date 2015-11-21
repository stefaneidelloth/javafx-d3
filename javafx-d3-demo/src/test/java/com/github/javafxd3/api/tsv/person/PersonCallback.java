package com.github.javafxd3.api.tsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

public class PersonCallback implements DsvCallback<Person> {
	@Override
	public void get(final JavaScriptObject error, final Person[] rows) {
		assertNull(error);
		assertEquals(5, rows.length);
		Person jane = rows[2];
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}
}
