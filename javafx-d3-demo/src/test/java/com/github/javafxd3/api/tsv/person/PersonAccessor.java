package com.github.javafxd3.api.tsv.person;

import com.github.javafxd3.api.dsv.DsvObjectAccessor;
import com.github.javafxd3.api.dsv.DsvRow;

public class PersonAccessor implements DsvObjectAccessor<Person> {
	@Override
	public Person apply(final DsvRow row, final int index) {
		return new Person(row.get("Name").asString(), row.get("Age").asInt());
	}
}
