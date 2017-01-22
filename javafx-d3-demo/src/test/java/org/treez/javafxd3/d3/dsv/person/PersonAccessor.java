package org.treez.javafxd3.d3.dsv.person;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

public class PersonAccessor implements DsvObjectAccessor<Person> {

	private JsEngine engine;

	public PersonAccessor(JsEngine engine) {
		this.engine = engine;
	}

	@Override
	public Person apply(final Object row, final int index) {

		
		DsvRow csvRow = ConversionUtil.convertObjectTo(row,  DsvRow.class, engine);

		String name = csvRow.get("Name").asString();
		int age = csvRow.get("Age").asInt();
		
		return new Person(name,age );
	}	
	
}