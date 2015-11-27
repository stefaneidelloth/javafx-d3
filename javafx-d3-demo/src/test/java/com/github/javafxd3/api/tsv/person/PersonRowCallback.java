package com.github.javafxd3.api.tsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.dsv.DsvRow;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PersonRowCallback implements DsvCallback<DsvRow> {
	
	private WebEngine webEngine;

	public PersonRowCallback(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public void get(final Object error, final Object dsvRowArray) {

		JSObject jsDsvDataArray = (JSObject) dsvRowArray;
		Array<DsvRow> values = new Array<DsvRow>(webEngine, jsDsvDataArray);
		List<? extends DsvRow> valueList = values.asList(DsvRow.class);

		assertNull(error);
		assertEquals(5, valueList.size());
		DsvRow jane = valueList.get(2);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}
	
	
}
