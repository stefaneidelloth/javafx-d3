package org.treez.javafxd3.d3.tsv;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvRow;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.tsv.person.Person;
import org.treez.javafxd3.d3.tsv.person.PersonAccessor;
import org.treez.javafxd3.d3.tsv.person.PersonArrayAccessor;
import org.treez.javafxd3.d3.tsv.person.PersonCallback;
import org.treez.javafxd3.d3.tsv.person.PersonRowCallback;

/**
 * Tests the class Tsv
 */
public class TsvTest extends AbstractTestCase {

	private static final String COL_SEP = ",";
	private static final String ROW_SEP = "\n";	

	private static final String EXAMPLE_DATA = "Name" + COL_SEP + "Age" + ROW_SEP + //
			"Paul" + COL_SEP + "25" + ROW_SEP + //
			"John" + COL_SEP + "38" + ROW_SEP + //
			"Jane" + COL_SEP + "15" + ROW_SEP + //
			"Bruce" + COL_SEP + "48" + ROW_SEP + //
			"Emma" + COL_SEP + "28" + ROW_SEP;
	
	

	@Override
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

		Array<DsvRow> rows = d3.<DsvRow> tsv().parse(EXAMPLE_DATA);

		assertNotNull("Could not parse rows", rows);

		assertEquals(5, rows.length());
		DsvRow jane = rows.get(2, DsvRow.class);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}

	private void testTsvParseWithAccessor() {

		D3 d3 = new D3(webEngine);

		Array<Person> rows = d3.<Person> tsv().parse(EXAMPLE_DATA, new PersonAccessor(webEngine));
		assertEquals(5, rows.length());
		Person jane = rows.get(2, Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvParseRows() {

		D3 d3 = new D3(webEngine);

		String[][] rows = d3.tsv().parseRows(EXAMPLE_DATA);
		assertEquals(5, rows.length);
		String[] jane = rows[2];
		assertEquals("Jane", jane[0]);
		assertEquals("15", jane[1]);
	}

	private void testTsvParseRowsWithAccessor() {

		D3 d3 = new D3(webEngine);

		Person[] rows = d3.<Person> tsv().parseRows(EXAMPLE_DATA, new PersonArrayAccessor());
		assertEquals(5, rows.length);
		Person jane = rows[2];
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvWithAccessorAndCallback() {

		D3 d3 = new D3(webEngine);

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.tsv("test-data/test.tsv", accessor, callback);
	}

	private void testTsvWithCallback() {

		D3 d3 = new D3(webEngine);

		// FIXME : we are not really sure if callback is actually called
		PersonRowCallback callback = new PersonRowCallback(webEngine);
		d3.tsv("test-data/test.tsv", callback);
	}

	private void testTsvWithChainingAccessorAndCallback() {

		D3 d3 = new D3(webEngine);

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.<Person> tsv("test-data/test.tsv").row(accessor).get(callback);
	}
}









