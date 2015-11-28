package com.github.javafxd3.d3.layout;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.coords.Coords;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A link in d3js' tree layout, see <a href="https:links">d3 docs on link</a>.
 * Provides accessors and setters for a link's two key attributes source and
 * target.
 * 
 * 
 * 
 */
public class Link extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Link(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Create a basic link object starting at one coordinate and ending at
	 * another
	 * 
	 * @param source
	 *            starting coordinates
	 * @param target
	 *            ending coordinates
	 * 
	 * @return the link object
	 */
	public static Link create(WebEngine webEngine, Coords source, Coords target) {
		JSObject sourceObj = source.getJsObject();
		JSObject targetObj = target.getJsObject();
		D3 d3 = new D3(webEngine);
		JSObject d3Obj = d3.getJsObject();
		
		String sourceVarName = createNewTemporaryInstanceName();
		String targetVarName = createNewTemporaryInstanceName();
		d3Obj.setMember(sourceVarName, sourceObj);
		d3Obj.setMember(targetVarName, targetObj);
		String command = "{ source : d3."+ sourceVarName +", target : d3."+ targetVarName +" }";
		JSObject result = d3.evalForJsObject(command);
		
		d3Obj.removeMember(sourceVarName);
		d3Obj.removeMember(targetVarName);
		
		return new Link(webEngine, result);		
	}

	/**
	 * @return the end node
	 */
	public Node target() {
		JSObject result = getMember("target");
		return new Node(webEngine, result);
	}

	/**
	 * @return the start node
	 */
	public Node source() {
		JSObject result = getMember("source");
		return new Node(webEngine, result);
	}
}
