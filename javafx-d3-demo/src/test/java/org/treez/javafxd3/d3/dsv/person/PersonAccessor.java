package org.treez.javafxd3.d3.dsv.person;

import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PersonAccessor implements DsvObjectAccessor<Person> {

	private WebEngine webEngine;

	public PersonAccessor(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public Person apply(final Object row, final int index) {

		JSObject jsRow = (JSObject) row;
		DsvRow csvRow = new DsvRow(webEngine, jsRow);

		String name = csvRow.get("Name").asString();
		int age = csvRow.get("Age").asInt();
		
		return new Person(name,age );
	}	
	
}