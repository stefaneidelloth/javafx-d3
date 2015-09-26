package com.github.javafxd3.api.dsv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

/**
 * Tests the class Dsv
 *
 */
public class DsvTest extends AbstractTestCase {

	// #region ATTRIBUTES

	private D3 d3;

	// #end region

	// #region METHODS

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

		DsvRow[] rows = d3.<DsvRow> csv().parse( //
				"Name,Age\n" + //
						"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n");

		assertEquals(5, rows.length);
		DsvRow jane = rows[2];
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, jane.get("Age").asInt());
	}

	private void testCsvParseWithAccessor() {
		Person[] rows = d3.<Person> csv().parse( //
				"Name,Age\n" + //
						"Paul,25\n" + //
						"John,38\n" + //
						"Jane,15\n" + //
						"Bruce,48\n" + //
						"Emma,28\n",
				new PersonAccessor());

		assertEquals(5, rows.length);
		Person jane = rows[2];
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
		PersonAccessor accessor = new PersonAccessor();
		PersonCallback callback = new PersonCallback();
		d3.csv("test-data/test.csv", accessor, callback);
	}

	private void testCsvWithCallback() {

		// FIXME : we are not really sure if callback is actually called
		PersonRowCallback callback = new PersonRowCallback();
		d3.csv("test-data/test.csv", callback);
	}

	private void testCsvWithChainingAccessorAndCallback() {

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor();
		PersonCallback callback = new PersonCallback();
		d3.<Person> csv("test-data/test.csv").row(accessor).get(callback);
	}
}

// #end region

// #region CLASSES

class Person {

	// #region ATTRIBUTES

	private final String name;

	private final int age;

	// #ene region

	// #region CONSTRUCTORS

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	// #end region

	// #region METHODS

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	// #end region

	// #region ACCESSORS

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	// #end region

}

class PersonAccessor implements DsvObjectAccessor<Person> {
	
	@Override
	public Person apply(final DsvRow row, final int index) {
		return new Person(row.get("Name").asString(), row.get("Age").asInt());
	}
}

class PersonCallback implements DsvCallback<Person> {
	
	@Override
	public void get(final JavaScriptObject error, final Person[] rows) {
		assertNull(error);
		assertEquals(5, rows.length);
		Person jane = rows[2];
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
	
	@Override
	public void get(final JavaScriptObject error, final DsvRow[] rows) {
		assertNull(error);
		assertEquals(5, rows.length);
		DsvRow jane = rows[2];
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, jane.get("Age").asInt());
	}
}

// #end region