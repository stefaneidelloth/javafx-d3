package org.treez.javafxd3.d3.dsv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvArrayAccessor;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

import org.treez.javafxd3.d3.AbstractTestCase;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Tests the class Dsv
 *
 */
public class DsvTest extends AbstractTestCase {

	//#region ATTRIBUTES

	private D3 d3;

	//#end region

	//#region METHODS

	@Override
	public void doTest() {

		d3 = new D3(webEngine);

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
		String[][] rows = d3.csv().parseRows( //
				"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n");

		assertEquals(5, rows.length);
		String[] jane = rows[2];
		assertEquals("Jane", jane[0]);
		assertEquals("15", jane[1]);
	}

	private void testCsvParseRowsWithAccessor() {
		Person[] rows = d3.<Person> csv().parseRows( //
				"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n",
				new PersonArrayAccessor());

		assertEquals(5, rows.length);
		Person jane = rows[2];
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testCsvWithAccessorAndCallback() {

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.csv("test-data/test.csv", accessor, callback);
	}

	private void testCsvWithCallback() {

		// FIXME : we are not really sure if callback is actually called
		PersonRowCallback callback = new PersonRowCallback(webEngine);
		d3.csv("test-data/test.csv", callback);
	}

	private void testCsvWithChainingAccessorAndCallback() {

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.<Person> csv("test-data/test.csv").row(accessor).get(callback);
	}
}

//#end region

//#region CLASSES

class Person {

	//#region ATTRIBUTES

	private final String name;

	private final int age;

	//#ene region

	//#region CONSTRUCTORS

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	//#end region

	//#region ACCESSORS

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	//#end region

}

class PersonAccessor implements DsvObjectAccessor<Person> {

	private WebEngine webEngine;

	public PersonAccessor(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public Person apply(final Object row, final int index) {

		JSObject jsRow = (JSObject) row;
		DsvRow dsvRow = new DsvRow(webEngine, jsRow);

		return new Person(dsvRow.get("Name").asString(), dsvRow.get("Age").asInt());
	}
}

class PersonCallback implements DsvCallback<Person> {

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

class PersonArrayAccessor implements DsvArrayAccessor<Person> {

	@Override
	public Person parse(final String[] row, final int index) {
		return new Person(row[0], Integer.parseInt(row[1]));
	}
}

class PersonRowCallback implements DsvCallback<DsvRow> {
	
	private WebEngine webEngine;

	public PersonRowCallback(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	@Override
	public void get(final Object error, final Object dsvRowArray) {

		JSObject jsDsvDataArray = (JSObject) dsvRowArray;
		Array<DsvRow> values = new Array<DsvRow>(webEngine, jsDsvDataArray);
		List<? extends DsvRow> valueList = values.asList(DsvRow.class);

		assertNull(error);
		assertEquals(5, valueList.size());
		DsvRow jane = valueList.get(2);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}
}

//#end region