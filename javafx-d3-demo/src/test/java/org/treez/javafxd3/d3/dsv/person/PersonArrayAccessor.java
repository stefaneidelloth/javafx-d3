package org.treez.javafxd3.d3.dsv.person;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvArrayAccessor;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class PersonArrayAccessor implements DsvArrayAccessor<Person> {
	
	private JsEngine engine;
	
	public PersonArrayAccessor(JsEngine engine){
		this.engine = engine;
	}

	@Override
	public Person parse(final Object rowArray, final int index) {		
		Array<String> row = new Array<>(engine, (JsObject) engine.toJsObjectIfNotSimpleType(rowArray));
		String age = row.get(1, String.class);
		return new Person(row.get(0, String.class), Integer.parseInt(age));
	}
}
