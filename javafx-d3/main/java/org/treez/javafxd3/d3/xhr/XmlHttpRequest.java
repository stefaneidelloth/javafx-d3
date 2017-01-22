package org.treez.javafxd3.d3.xhr;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Overlay type for an XmlHttpRequest error.
 */
public class XmlHttpRequest extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public XmlHttpRequest(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @return
	 */
	public String statusText() {
		String result = getMemberForString("statusText");
		return result;
	}

	/**
	 * @return
	 */
	public int status() {
		Integer result = getMemberForInteger("status");
		return result;
	}

	/**
	 * @return
	 */
	public String response() {
		String result = getMemberForString("response");
		return result;
	}

	public ResponseType responseType() {

		String result = callForString("responseType");
		if (result == null) {
			return null;
		}
		return ResponseType.fromString(result);

	}

	/**
	 * @param type
	 * @return
	 */
	public XmlHttpRequest responseType(ResponseType type) {

		if (type == null) {
			JsObject result = call("responseType", "");
			if (result == null) {
				return null;
			}
			return new XmlHttpRequest(engine, result);

		} else {
			String enumString = type.toString().toLowerCase();
			JsObject result = call("responseType", enumString);
			if (result == null) {
				return null;
			}
			return new XmlHttpRequest(engine, result);
		}

	}

	//#end region

	//#region ENUM

	public static enum ResponseType {
		
		ARRAYBUFFER,		
		BLOB,		
		JSON,	
		TEXT,	
		DOCUMENT;
		
		public static ResponseType fromString(final String s) {
			if ((s == null) || s.trim().isEmpty()) {
				return null;
			}
			return valueOf(s.toUpperCase());
		}
	}

	//#end region

}
