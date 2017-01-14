package org.treez.javafxd3.d3.xhr;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Overlay type for an XmlHttpRequest error.
 */
public class XmlHttpRequest extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public XmlHttpRequest(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
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
			JSObject result = call("responseType", "");
			if (result == null) {
				return null;
			}
			return new XmlHttpRequest(webEngine, result);

		} else {
			String enumString = type.toString().toLowerCase();
			JSObject result = call("responseType", enumString);
			if (result == null) {
				return null;
			}
			return new XmlHttpRequest(webEngine, result);
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
