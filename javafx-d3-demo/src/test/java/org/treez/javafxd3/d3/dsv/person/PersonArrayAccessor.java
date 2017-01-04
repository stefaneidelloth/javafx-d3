package org.treez.javafxd3.d3.dsv.person;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvArrayAccessor;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PersonArrayAccessor implements DsvArrayAccessor<Person> {
	
	private WebEngine webEngine;
	
	public PersonArrayAccessor(WebEngine webEngine){
		this.webEngine = webEngine;
	}

	@Override
	public Person parse(final Object rowArray, final int index) {		
		Array<String> row = new Array<>(webEngine, (JSObject) rowArray);
		String age = row.get(1, String.class);
		return new Person(row.get(0, String.class), Integer.parseInt(age));
	}
}
