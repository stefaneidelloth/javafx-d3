package org.treez.javafxd3.d3.dsv.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvCallback;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class PersonCallback implements DsvCallback<Person> {

	private JsEngine engine;

	public PersonCallback(JsEngine engine) {
		this.engine = engine;
	}

	@Override
	public void get(final Object error, final Object personArray) {

		JsObject jsDsvDataArray = (JsObject) engine.toJsObjectIfNotSimpleType(personArray);
		Array<Person> values = new Array<Person>(engine, jsDsvDataArray);
		List<? extends Person> valueList = values.asList(Person.class);

		assertNull(error);
		assertEquals(5, valueList.size());
		Person jane = valueList.get(2);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}
}