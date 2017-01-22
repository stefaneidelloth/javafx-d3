package org.treez.javafxd3.d3.dsv.person;

import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class PersonAccessor implements DsvObjectAccessor<Person> {

	private JsEngine engine;

	public PersonAccessor(JsEngine engine) {
		this.engine = engine;
	}

	@Override
	public Person apply(final Object row, final int index) {

		JsObject jsRow = (JsObject) engine.toJsObjectIfNotSimpleType(row);
		DsvRow csvRow = new DsvRow(engine, jsRow);

		String name = csvRow.get("Name").asString();
		int age = csvRow.get("Age").asInt();
		
		return new Person(name,age );
	}	
	
}