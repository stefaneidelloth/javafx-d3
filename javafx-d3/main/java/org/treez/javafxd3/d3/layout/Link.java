package org.treez.javafxd3.d3.layout;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

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
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Link(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
	public static Link create(JsEngine engine, Coords source, Coords target) {
		JsObject sourceObj = source.getJsObject();
		JsObject targetObj = target.getJsObject();
		D3 d3 = new D3(engine);
		JsObject d3Obj = d3.getJsObject();
		
		String sourceVarName = createNewTemporaryInstanceName();
		String targetVarName = createNewTemporaryInstanceName();
		d3Obj.setMember(sourceVarName, sourceObj);
		d3Obj.setMember(targetVarName, targetObj);
		String command = "{ source : d3."+ sourceVarName +", target : d3."+ targetVarName +" }";
		JsObject result = d3.evalForJsObject(command);
		
		d3Obj.removeMember(sourceVarName);
		d3Obj.removeMember(targetVarName);
		
		if(result==null){
			return null;
		}
		
		return new Link(engine, result);		
	}

	/**
	 * @return the end node
	 */
	public Node target() {
		JsObject result = getMember("target");
		return new Node(engine, result);
	}

	/**
	 * @return the start node
	 */
	public Node source() {
		JsObject result = getMember("source");
		return new Node(engine, result);
	}
}
