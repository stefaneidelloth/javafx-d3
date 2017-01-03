package org.treez.javafxd3.d3.svg;


import java.util.Arrays;
import java.util.List;



/**
 * Shape of the symbol.
 * <p>
 * Types are normalized to have the same area in square pixels, according to
 * the specified size. However, note that different types' sizes may be
 * affected by the stroke and stroke width in different ways. All of the
 * types are designed to be visible when only a fill style is used (unlike
 * the Protovis cross), although they generally look better when both a fill
 * and stroke is used.
 * 
 * Also see
 * https://github.com/mbostock/d3/wiki/SVG-Shapes#symbol_type
 */
public enum SymbolType {
	/**
	 * A circle
	 */
	CIRCLE("circle"),
	/**
	 * A greek cross or plus sign
	 */
	CROSS("cross"),
	/**
	 * A rhombus
	 */
	DIAMOND("diamond"),
	/**
	 * An axis-aligned square.
	 */
	SQUARE("square"),
	/**
	 * A downward-pointing equilateral triangle.
	 */
	TRIANGLE_DOWN("triangle-down"),
	/**
	 * a upward-pointing equilateral triangle.
	 */
	TRIANGLE_UP("triangle-up");

	private final String value;

	private SymbolType(final String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	public static SymbolType fromString(String value){
		for(SymbolType currentSymbolType: values()){
			String currentValue = currentSymbolType.getValue();
			boolean isWantedType = currentValue.equals(value);
			if(isWantedType){
				return currentSymbolType;
			}
		}
		String message = "The value '"+value+"' is not known.";
		throw new IllegalArgumentException(message);
	}

	public static List<String> jsValues() {
		return Arrays.asList(CIRCLE.value, CROSS.value, DIAMOND.value, SQUARE.value, TRIANGLE_DOWN.value,
				TRIANGLE_UP.value);
	}
}
