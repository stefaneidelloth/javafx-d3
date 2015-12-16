package org.treez.javafxd3.d3;

/**
 * Creates a new instance of a javafx-d3 demo that is used with the JavaFxD3DemoSuite
 */
public abstract class DemoFactory {

	/**
	 * Creates a new demo case instance
	 * @return
	 */
	public abstract DemoCase newInstance();

	/**
	 * Creates an id for the demo case
	 * @return
	 */
	public String id() {
		String name = this.getClass().getName();
		name = name.substring(name.lastIndexOf('.') + 1);
		// inner class
		if (name.contains("$")) {
			name = name.substring(0, name.lastIndexOf("$"));
		}
		return name;
	}
}
