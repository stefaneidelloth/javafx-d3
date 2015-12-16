package org.treez.javafxd3.d3.tsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvCallback;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PersonCallback implements DsvCallback<Person> {
	
	private WebEngine webEngine;

	public PersonCallback(WebEngine webEngine) {
		this.webEngine = webEngine;
	}
	
	@Override
	public void get(final Object error, final Object personArray) {
		
		JSObject jsDsvDataArray = (JSObject) personArray;
		Array<Person> values = new Array<Person>(webEngine, jsDsvDataArray);
		List<? extends Person> valueList = values.asList(Person.class);
		
		assertNull(error);
		assertEquals(5, valueList.size());
		Person jane = valueList.get(2);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());		
		
	}
}
