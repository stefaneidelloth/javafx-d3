package com.github.javafxd3.d3.wrapper;

import netscape.javascript.JSObject;

public class Inspector {

	/**
	 * Inspects a JavaScriptObject
	 * 
	 * @param javaScriptObject
	 */
	public static void inspect(JavaScriptObject javaScriptObject) {

		if (javaScriptObject == null) {
			System.out.println("##############################");
			System.out.println("null");
			System.out.println("##############################");
		} else {
			String className = javaScriptObject.getClass().getName();
			System.out.println("Inspecting object of type " + className);
			JSObject jsObject = javaScriptObject.getJsObject();
			inspect(jsObject);
		}
	}

	/**
	 * Inspects a JSObject
	 * 
	 * @param jsObject
	 */
	public static void inspect(JSObject jsObject) {
		System.out.println("##############################");

		if (jsObject == null) {
			System.out.println("null");
		} else {

			// toString
			String stringRepresentation = jsObject.toString();
			System.out.println("String representation:" + stringRepresentation);

			// type
			String typeCommand = "typeof this";
			String type = (String) jsObject.eval(typeCommand);
			System.out.println("Type: " + type);

			// methods
			String methodsCommand = "Object.getOwnPropertyNames(this).filter(function (p) {return typeof this[p] === 'function';})";
			String methods = jsObject.eval(methodsCommand).toString();
			System.out.println("Methods: " + methods);

			// attributes
			String attributesCommand = "Object.getOwnPropertyNames(this).filter(function (p) {return typeof this[p] !== 'function';})";
			String attributes = jsObject.eval(attributesCommand).toString();
			System.out.println("Attributes: " + attributes);

			String[] attributeList = attributes.split(",");
			System.out.println("Individual attributes: -----------");
			for (String attribute : attributeList) {
				Object attributeValue = jsObject.getMember(attribute);
				if (attributeValue == null) {
					System.out.println(attribute + ": null");
				} else {
					String valueString = attributeValue.toString();
					System.out.println(attribute + ": " + valueString);
				}

			}
			System.out.println("----------------------------------");

		}

		System.out.println("##############################");
	}

}
