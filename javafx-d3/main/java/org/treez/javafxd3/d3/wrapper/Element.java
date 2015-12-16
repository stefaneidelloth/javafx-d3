package org.treez.javafxd3.d3.wrapper;

import javafx.geometry.BoundingBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Element extends Node {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Element(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @param b
	 * @return
	 */
	public Node cloneNode(boolean b) {
		throw new IllegalStateException("not yet implemented");
	}

	/**
	 * @return
	 */
	public String getTagName() {
		String result = getMemberForString("tagName");
		return result;
	}

	/**
	 * @return
	 */
	public Node getParentNode() {
		String command = "d3.select(this).parentNode";
		JSObject result = evalForJsObject(command);
		return new Node(webEngine, result);
	}

	/**
	 * @return
	 */
	public int getChildCount() {
				
		String countCommand = "this.childNodes.length";
		int result = evalForInteger(countCommand);		
		return result;
	}

	/**
	 * @return
	 */
	public Object getInnerText() {
		
		String result = getMemberForString("textContent");
		
		if (result.equals("undefined")){
			result = getMemberForString("text");
		}
		return result;
	}

	/**
	 * @param string
	 */
	public void setInnerHtml(String string) {
		throw new IllegalStateException("not yet implemented");
		// return null;

	}

	/**
	 * @param attr
	 * @return
	 */
	public String getAttribute(String attr) {
		String result = getMemberForString(attr);
		return result;
	}

	/**
	 * @param string
	 * @param b
	 */
	public void setPropertyBoolean(String string, boolean b) {
		throw new IllegalStateException("not yet implemented");

	}

	/**
	 * @param dataProperty
	 * @return
	 */
	public Integer getPropertyInt(String dataProperty) {
		throw new IllegalStateException("not yet implemented");
		// return null;
	}

	/**
	 * @param dataProperty
	 * @param i
	 */
	public void setPropertyInt(String dataProperty, int i) {
		throw new IllegalStateException("not yet implemented");

	}

	/**
	 * @param dataProperty
	 * @return
	 */
	public String getPropertyString(String dataProperty) {
		throw new IllegalStateException("not yet implemented");
		// return null;
	}

	/**
	 * @param i
	 * @return
	 */
	public Element getChild(int i) {
		throw new IllegalStateException("not yet implemented");
		// return null;
	}

	/**
	 * @return
	 */
	public String getChildNodes() {
		throw new IllegalStateException("not yet implemented");
	}

	/**
	 * @param string
	 * @return
	 */
	public Element[] getElementsByTagName(String string) {
		throw new IllegalStateException("not yet implemented");
	}

	/**
	 * @param nodeFactory
	 */
	public void add(D3NodeFactory nodeFactory) {
		throw new IllegalStateException("not yet implemented");

	}

	/**
	 * @param nodeFactory
	 */
	public void remove(D3NodeFactory nodeFactory) {
		throw new IllegalStateException("not yet implemented");

	}

	public BoundingBox getBBox() {
		throw new IllegalStateException("not yet implemented");
	}

	public Element getParentElement() {
		
		String command = "this.parentNode";
		JSObject result = evalForJsObject(command);
		return new Element(webEngine, result);		
		
	}

	//#end region

}
