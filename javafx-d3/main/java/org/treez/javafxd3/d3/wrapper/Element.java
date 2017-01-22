package org.treez.javafxd3.d3.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;

import javafx.geometry.BoundingBox;
import org.treez.javafxd3.d3.core.JsEngine;
import netscape.javascript.JSException;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 *
 */
public class Element extends Node {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Element(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public Node cloneNode(boolean deep) {
		JsObject result = call("cloneNode", deep);
		if(result==null){
			return null;
		}
		return new Node(engine, result);
	}

	public String getTagName() {
		String result = getMemberForString("tagName");
		return result;
	}

	public Node getParentNode() {		
		String command = "d3.select(this).node().parentNode";
		JsObject result = evalForJsObject(command);
		return new Node(engine, result);
	}

	public int getChildCount() {

		try {
			String countCommand = "this.children.length";
			int result = evalForInteger(countCommand);
			return result;
		} catch (JSException exception) {
			String countCommand = "this.childNodes.length";
			int result = evalForInteger(countCommand);
			return result;
		}
	}

	public String getText() {
		String command = "d3.select(this).text()";
		String result = evalForString(command);
		return result;		
	}	
	
	public String getInnerHtml() {
		String command = "d3.select(this).html();";
		String result =  evalForString(command);
		return result;
	}
	
	public void setInnerHtml(String html) {
		String command = "d3.select(this).html('" + html + "');";
		eval(command);
	}

	public String getAttribute(String attr) {
		String command = "this.getAttribute('" + attr + "')";
		String result = evalForString(command);
		return result;
	}

	public Object getProperty(String property) {
		String command = "this." + property;
		return eval(command);
	}

	public void setPropertyBoolean(String dataProperty, boolean bool) {
		String command = "this." + dataProperty + "= " + bool + ";";
		eval(command);
	}

	public Integer getPropertyInt(String dataProperty) {
		String command = "this." + dataProperty;
		Integer result = evalForInteger(command);
		return result;
	}

	public void setPropertyInt(String dataProperty, int value) {
		String command = "this." + dataProperty + "= " + value + ";";
		eval(command);
	}

	public String getPropertyString(String dataProperty) {
		String command = "this." + dataProperty;
		String result = evalForString(command);
		return result;
	}

	public Element getChild(int index) {
		String command = "this.childNodes[" + index + "];";
		JsObject child = evalForJsObject(command);
		return new Element(engine, child);
	}	

	public Element[] getElementsByTagName(String tagName) {
		
		String command = "d3.select(this).selectAll('"+ tagName+"')";
		JsObject result = evalForJsObject(command);
		Array<JsObject> resultArray = new Array<>(engine, result);
		
		List<Element> elementList = new ArrayList<>();
		
		resultArray.forEach((element)->{
			JsObject jsElement = (JsObject) element;
			elementList.add(new Element(engine, jsElement));
		});
		
		return elementList.toArray(new Element[elementList.size()]);
	}

	public void add(D3NodeFactory nodeFactory) {
		nodeFactory.createInParentSelection(select());
	}

	public void remove(D3NodeFactory nodeFactory) {
		Selection elementSelection = select();
		nodeFactory.remove(elementSelection);
	}

	public Selection select() {
		JsObject d3Obj = this.getD3();
		JsObject selectionObj = (JsObject) d3Obj.call("select", getJsObject());
		Selection elementSelection = new Selection(engine, selectionObj);
		return elementSelection;
	}

	public BoundingBox getBBox() {
		String command = "this.getBBox();";
		JsObject bBox = evalForJsObject(command);

		Double x = Double.parseDouble("" + bBox.eval("this.x"));
		Double y = Double.parseDouble("" + bBox.eval("this.y"));
		Double width = Double.parseDouble("" + bBox.eval("this.width"));
		Double height = Double.parseDouble("" + bBox.eval("this.height"));
		BoundingBox boundinbBox = new BoundingBox(x, y, width, height);
		return boundinbBox;
	}

	public Element getParentElement() {
		String command = "this.parentNode";
		JsObject result = evalForJsObject(command);
		return new Element(engine, result);
	}

	public String getStyle(String identifier) {
		String command = "this.style." + identifier;
		String result = evalForString(command);
		return result;
	}
	
	public String toString(){
		JsObject jsObject = getJsObject();
		if(jsObject==null){
			return "!!Element with missing JsObject!!";
		} else {
			return Inspector.getInspectionInfo(jsObject);
		}
	}

	

	//#end region

}
