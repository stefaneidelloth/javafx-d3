package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.person.Person;
import org.treez.javafxd3.d3.dsv.person.PersonAccessor;
import org.treez.javafxd3.d3.dsv.person.PersonArrayAccessor;
import org.treez.javafxd3.d3.dsv.person.PersonCallback;
import org.treez.javafxd3.d3.dsv.person.PersonRowCallback;

/**
 * Tests the class Tsv
 */
public class TsvTest extends AbstractTestCase {

	private static final String COL_SEP = "\t";
	private static final String ROW_SEP = "\n";	

	private static final String ROW_EXAMPLE_DATA =  //
			"Paul" + COL_SEP + "25" + ROW_SEP + //
			"John" + COL_SEP + "38" + ROW_SEP + //
			"Jane" + COL_SEP + "15" + ROW_SEP + //
			"Bruce" + COL_SEP + "48" + ROW_SEP + //
			"Emma" + COL_SEP + "28" + ROW_SEP;
	
	private static final String EXAMPLE_DATA = "Name" + COL_SEP + "Age" + ROW_SEP + //
			ROW_EXAMPLE_DATA;
	
	

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

		Dsv<DsvRow> tsv = d3.<DsvRow>tsv();
		Array<DsvRow> rows = tsv.parse(EXAMPLE_DATA);

		assertNotNull("Could not parse rows", rows);

		assertEquals(5, rows.length());
		DsvRow jane = rows.get(2, DsvRow.class);
		assertEquals("Jane", jane.get("Name").asString());
		assertEquals(15, (int) jane.get("Age").asInt());
	}

	private void testTsvParseWithAccessor() {		

		Array<Person> rows = d3.<Person> tsv().parse(EXAMPLE_DATA, new PersonAccessor(webEngine));
		assertEquals(5, rows.length());
		Person jane = rows.get(2, Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvParseRows() {
		
		Array<Array<String>> rows = d3.tsv().parseRows(ROW_EXAMPLE_DATA);
		assertEquals(5, rows.length());
		Array<?> jane = rows.get(2, Array.class);
		assertEquals("Jane", jane.get(0,  String.class));
		assertEquals("15", jane.get(1,  String.class));
	}

	private void testTsvParseRowsWithAccessor() {		

		Array<Person> rows = d3.<Person> tsv().parseRows(ROW_EXAMPLE_DATA, new PersonArrayAccessor(webEngine));
		assertEquals(5, rows.length());
		Person jane = rows.get(2,  Person.class);
		assertEquals("Jane", jane.getName());
		assertEquals(15, jane.getAge());
	}

	private void testTsvWithAccessorAndCallback() {		

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.tsv("https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.tsv", accessor, callback);
	}

	private void testTsvWithCallback() {	

		// FIXME : we are not really sure if callback is actually called
		PersonRowCallback callback = new PersonRowCallback(webEngine);
		d3.tsv("https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.tsv", callback);
	}

	private void testTsvWithChainingAccessorAndCallback() {		

		// FIXME : we are not really sure if accessor and callback are actually
		// called
		PersonAccessor accessor = new PersonAccessor(webEngine);
		PersonCallback callback = new PersonCallback(webEngine);
		d3.<Person> tsv("https://github.com/stefaneidelloth/javafx-d3/blob/master/javafx-d3-demo/src/main/resources/test-data/test.tsv").row(accessor).get(callback);
	}
}









