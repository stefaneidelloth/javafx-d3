package com.github.javafxd3.api.selection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.api.wrapper.Element;


@SuppressWarnings("javadoc")
public class SelectionHtmlTest extends AbstractSelectionTest {

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
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object datum, final int index) {
				return value + index;
			}
		});
		assertEquals(value + "0", getElementInnerHtml(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection.html(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object datum, final int index) {
				return value + index;
			}
		});
		assertEquals(value + "0", getElementInnerHtml(0));
		assertEquals(value + "1", getElementInnerHtml(1));
		assertEquals(value + "2", getElementInnerHtml(2));

	}

	protected void testSetterConstantString() {
		// works with single selection
		Selection selection = givenASimpleSelection(new Label());
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		selection.html(value);
		assertEquals(value, getElementInnerHtml(0));

		// works with multiple selection
		selection = givenAMultipleSelection(new Label(), new Label(), new Label());
		selection.html(value);
		assertEquals(value, getElementInnerHtml(0));
		assertEquals(value, getElementInnerHtml(1));
		assertEquals(value, getElementInnerHtml(2));

	}

	protected void testGetter() {
		// with single selection
		Label label = new Label();
		final String value = "<div name=\"test\" style=\"background-color:red;\"></div>";
		label.setInnerHTML(value);
		Selection selection = givenASimpleSelection(label);
		assertEquals(value, selection.html());

		// with multiple selection, should return the first element
		selection = givenAMultipleSelection(createLabel(value), createLabel(value), createLabel(value));
		assertEquals(value, selection.html());
	}

	private D3NodeFactory createLabel(final String textValue) {
		Label label = new Label();
		label.setInnerHTML(textValue);
		return label;
	}
}
