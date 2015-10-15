package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.api.wrapper.Element;



@SuppressWarnings("javadoc")
public class SelectionTextTest extends AbstractSelectionTest {

	@Override
	@Test
	public void doTest() {
		testGetter();
		testSetterConstantString();
		testSetterFunction();

	}

	protected void testSetterFunction() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		final String value = "foo bar";
		selection.text(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object datum, final int index) {
				return value + index;
			}
		});
		assertEquals(value + "0", getElementInnerText(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection.text(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object datum, final int index) {
				return value + index;
			}
		});
		assertEquals(value + "0", getElementInnerText(0));
		assertEquals(value + "1", getElementInnerText(1));
		assertEquals(value + "2", getElementInnerText(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		String value = "foo bar";
		selection.text(value);
		assertEquals(value, getElementInnerText(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection.text(value);
		assertEquals(value, getElementInnerText(0));
		assertEquals(value, getElementInnerText(1));
		assertEquals(value, getElementInnerText(2));

	}

	protected void testGetter() {
		// with single selection
		Label label = new Label();
		String value = "foo";
		label.setInnerText(value);
		Selection selection = givenASimpleSelection(label);
		assertEquals(value, selection.text());

		// with multiple selection, should return the first element
		selection = givenAMultipleSelection(createLabel(value), createLabel(value), createLabel(value));
		assertEquals(value, selection.text());
	}

	private D3NodeFactory createLabel(final String textValue) {
		Label l = new Label();
		l.setInnerText(textValue);
		return l;
	}
}
