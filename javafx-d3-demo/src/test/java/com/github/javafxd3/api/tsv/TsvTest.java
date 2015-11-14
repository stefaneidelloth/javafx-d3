package com.github.javafxd3.api.tsv;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.dsv.DsvArrayAccessor;
import com.github.javafxd3.api.dsv.DsvCallback;
import com.github.javafxd3.api.dsv.DsvObjectAccessor;
import com.github.javafxd3.api.dsv.DsvRow;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

/**
 * Tests the class Tsv
 */
public class TsvTest extends AbstractTestCase {
	
	

	@Override
	@Test
	public void doTest() {
		testTsvParse();
		testTsvParseWithAccessor();
		testTsvParseRows();
		testTsvParseRowsWithAccessor();
		testTsvWithAccessorAndCallback();
		testTsvWithCallback();
		testTsvWithChainingAccessorAndCallback();
	}

	private void testTsvParse() {
		
		D3 d3 = new D3(webEngine);
		
		Array<DsvRow> rows = d3.<DsvRow> tsv().parse( //
				"Name\tAge\n" + //
						"Paul\t25\n" + //
						"John\t38\n" + //
						"Jane\t15\n" + //
						"Bruce\t48\n" + //
						"Emma\t28\n");

		assertEquals(5, rows.length());
		DsvRow jane = rows.get(2, DsvRow.class);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}

	private void testTsvParseWithAccessor() {
		
		D3 d3 = new D3(webEngine);
		
		Array<Person> rows = d3.<Person> tsv().parse( //
				"Name\tAge\n" + //
						"Paul\t25\n" + //
						"John\t38\n" + //
						"Jane\t15\n" + //
						"Bruce\t48\n" + //
						"Emma\t28\n",
				new PersonAccessor());
		assertEquals(5, rows.length());
		Person jane = rows.get(2, Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvParseRows() {
		
		D3 d3 = new D3(webEngine);
		
		String[][] rows = d3.tsv().parseRows( //
				"Paul\t25\n" + //
						"John\t38\n" + //
						"Jane\t15\n" + //
						"Bruce\t48\n" + //
						"Emma\t28\n");
		assertEquals(5, rows.length);
		String[] jane = rows[2];
		assertEquals("Jane", jane[0]);
		assertEquals("15", jane[1]);
	}

	private void testTsvParseRowsWithAccessor() {
		
		D3 d3 = new D3(webEngine);
		
		Person[] rows = d3.<Person> tsv().parseRows( //
				"Paul\t25\n" + //
						"John\t38\n" + //
						"Jane\t15\n" + //
						"Bruce\t48\n" + //
						"Emma\t28\n",
				new PersonArrayAccessor());
		assertEquals(5, rows.length);
		Person jane = rows[2];
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvWithAccessorAndCallback() {
		
		D3 d3 = new D3(webEngine);
		
		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor();
		PersonCallback callback = new PersonCallback();
		d3.tsv("test-data/test.tsv", accessor, callback);
	}

	private void testTsvWithCallback() {
		
		D3 d3 = new D3(webEngine);
		
		// FIXME : we are not really sure if callback is actually called
		PersonRowCallback callback = new PersonRowCallback();
		d3.tsv("test-data/test.tsv", callback);
	}

	private void testTsvWithChainingAccessorAndCallback() {
		
		D3 d3 = new D3(webEngine);
		
		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor();
		PersonCallback callback = new PersonCallback();
		d3.<Person> tsv("test-data/test.tsv").row(accessor).get(callback);
	}
}

class Person {
	private final String name;

	private final int age;

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
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
		assertEquals(15, (int) jane.get("Age").asInt());
	}
}