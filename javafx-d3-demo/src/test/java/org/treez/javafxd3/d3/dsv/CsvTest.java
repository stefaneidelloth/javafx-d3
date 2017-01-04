package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.person.Person;
import org.treez.javafxd3.d3.dsv.person.PersonAccessor;
import org.treez.javafxd3.d3.dsv.person.PersonArrayAccessor;
import org.treez.javafxd3.d3.dsv.person.PersonCallback;
import org.treez.javafxd3.d3.dsv.person.PersonRowCallback;

public class CsvTest extends AbstractTestCase {

	//#region METHODS

	@Override
	public void doTest() {

		testCsvParse();
		testCsvParseWithAccessor();
		testCsvParseRows();
		testCsvParseRowsWithAccessor();
		testCsvWithAccessorAndCallback();
		testCsvWithCallback();
		testCsvWithChainingAccessorAndCallback();

	}

	private void testCsvParse() {

		Array<DsvRow> rows = d3.<DsvRow> csv().parse( //
				"Name,Age\n" + //
						"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n");

		assertEquals(5, rows.length());
		DsvRow jane = rows.get(2, DsvRow.class);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}

	private void testCsvParseWithAccessor() {
		Array<Person> rows = d3.<Person> csv().parse( //
				"Name,Age\n" + //
						"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n",
				new PersonAccessor(webEngine));

		assertEquals(5, rows.length());
		Person jane = rows.get(2, Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testCsvParseRows() {
		Array<Array<String>> rows = d3.csv().parseRows( //
				"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n");

		int size = rows.sizes().get(0);
		assertEquals(5, size);
		Array<?> jane = rows.get(2, Array.class);
		assertEquals("Jane", jane.get(0, String.class));
		assertEquals("15", jane.get(1, String.class));
	}

	private void testCsvParseRowsWithAccessor() {
		Array<Person> rows = d3.<Person> csv().parseRows( //
				"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n",
				new PersonArrayAccessor(webEngine));

		int size = rows.length();
		assertEquals(5, size);
		Person jane = rows.get(2, Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testCsvWithAccessorAndCallback() {

		// FIXME : we are not really sure if accessor and callback are actually
		// called

		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.csv("https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.csv",
				accessor, callback);

	}

	private void testCsvWithCallback() {

		// FIXME : we are not really sure if callback is actually called		
		PersonRowCallback callback = new PersonRowCallback(webEngine);
		d3.csv("https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.csv",
				callback);
	}

	private void testCsvWithChainingAccessorAndCallback() {

		// FIXME : we are not really sure if accessor and callback are actually
		// called

		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		Dsv<Person> personCsv = d3.<Person> csv(
				"https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.csv");
		personCsv.row(accessor).get(callback);
	}
}

//#end region
