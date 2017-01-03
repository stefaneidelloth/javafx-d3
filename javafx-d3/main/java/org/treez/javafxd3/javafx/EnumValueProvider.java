package org.treez.javafxd3.javafx;

import java.util.List;

/**
 * This interface needs to be implemented by enums that should be used with the
 * EnumComboBox
 */
public interface EnumValueProvider<T extends Enum<T>> {

	/**
	 * Returns all enum values as a list of corresponding strings
	 */
	List<String> getValues();

	/**
	 * Returns the enum value that corresponds to the given string.
	 */
	T fromString(String value);

	/**
	 * Returns the string that corresponds to the value
	 */
	@Override
	String toString();

}
