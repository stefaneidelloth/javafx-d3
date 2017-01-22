package org.treez.javafxd3.d3.dsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvRow;

public class PersonRowCallback implements DsvCallback<DsvRow> {
	
	private JsEngine engine;

	public PersonRowCallback(JsEngine engine) {
		this.engine = engine;
	}

	@Override
	public void get(final Object error, final Object dsvRowArray) {

	
		@SuppressWarnings("unchecked")
		Array<DsvRow> values = (Array<DsvRow>) ConversionUtil.convertObjectTo(dsvRowArray,  Array.class, engine);
		List<? extends DsvRow> valueList = values.asList(DsvRow.class);

		assertNull(error);
		assertEquals(5, valueList.size());
		DsvRow jane = valueList.get(2);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}	
}