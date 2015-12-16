package org.treez.javafxd3.d3.tsv.person;

import org.treez.javafxd3.d3.dsv.DsvArrayAccessor;

public class PersonArrayAccessor implements DsvArrayAccessor<Person> {
	@Override
	public Person parse(final String[] row, final int index) {
		return new Person(row[0], Integer.parseInt(row[1]));
	}
}
