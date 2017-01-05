package org.treez.javafxd3.d3.wrapper;

import netscape.javascript.JSObject;

public class Inspector {


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

	public static void inspect(JSObject jsObject) {
			System.out.print(getInspectionInfo(jsObject));
	}

	public static String getInspectionInfo(JSObject jsObject) {
		String infoString = "";

		infoString += "##############################\n";

		if (jsObject == null) {
			infoString += "null\n";
		} else {

			// toString
			String stringRepresentation = jsObject.toString();
			infoString += "String representation:" + stringRepresentation + "\n";

			// type
			String typeCommand = "typeof this";
			String type = (String) jsObject.eval(typeCommand);
			infoString += "Type: " + type + "\n";

			// methods
			String methodsCommand = "Object.getOwnPropertyNames(this).filter(function (p) {return typeof this[p] === 'function';})";
			String methods = jsObject.eval(methodsCommand).toString();
			infoString += "Methods: " + methods + "\n";

			// attributes
			String attributesCommand = "Object.getOwnPropertyNames(this).filter(function (p) {return typeof this[p] !== 'function';})";
			String attributes = jsObject.eval(attributesCommand).toString();
			infoString += "Attributes: " + attributes + "\n";

			String[] attributeList = attributes.split(",");
			infoString += "Individual attributes: -----------\n";
			for (String attribute : attributeList) {
				Object attributeValue = jsObject.getMember(attribute);
				if (attributeValue == null) {
					infoString += (attribute + ": null\n");
				} else {
					String valueString = attributeValue.toString();
					infoString += (attribute + ": " + valueString + "\n");
				}

			}
			infoString += "----------------------------------\n";

		}

		infoString += "##############################\n";

		return infoString;
	}

}
