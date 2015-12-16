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

	/**
	 * @return
	 */
	public ResponseType responseType() {
		throw new IllegalStateException("not yet implemented");
		/*
		 * return @com.github.gwtd3.api.xhr.XmlHttpRequest.ResponseType::
		 * fromString(Ljava/lang/String;)(this.responseType());
		 */
	}

	/**
	 * @param type
	 * @return
	 */
	public XmlHttpRequest responseType(ResponseType type) {
		throw new IllegalStateException("not yet implemented");
		/*
		 * if(type==null){ return this.responseType(""); } else{ var stype =
		 * type.@com.github.gwtd3.api.xhr.XmlHttpRequest.ResponseType::name()();
		 * stype = stype.@java.lang.String::toLowerCase()(); return
		 * this.responseType(stype); }
		 */
	}

	//#end region

	//#region ENUM

	/**
	 * 
	 *
	 */
	public static enum ResponseType {
		/**
		 * 
		 */
		ARRAYBUFFER, 
		
		/**
		 * 
		 */
		BLOB, 
		
		/**
		 * 
		 */
		JSON, 
		
		/**
		 * 
		 */
		TEXT, 
		
		/**
		 * 
		 */
		DOCUMENT;

		/**
		 * @param s
		 * @return
		 */
		public static ResponseType fromString(final String s) {
			if ((s == null) || s.trim().isEmpty()) {
				return null;
			}
			return valueOf(s.toUpperCase());
		}
	}

	//#end region

}
