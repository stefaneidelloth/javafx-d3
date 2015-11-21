package com.github.javafxd3.api.tsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.dsv.DsvRow;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

public class PersonRowCallback implements DsvCallback<DsvRow> {
	@Override
	public void get(final JavaScriptObject error, final DsvRow[] rows) {
		assertNull(error);
		assertEquals(5, rows.length);
		DsvRow jane = rows[2];
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}
}
