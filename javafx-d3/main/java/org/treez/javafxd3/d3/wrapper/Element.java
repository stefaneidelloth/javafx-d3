package org.treez.javafxd3.d3.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;

import javafx.geometry.BoundingBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSException;
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

	public Node cloneNode(boolean deep) {
		JSObject result = call("cloneNode", deep);
		if(result==null){
			return null;
		}
		return new Node(webEngine, result);
	}

	public String getTagName() {
		String result = getMemberForString("tagName");
		return result;
	}

	public Node getParentNode() {		
		String command = "d3.select(this).node().parentNode";
		JSObject result = evalForJsObject(command);
		return new Node(webEngine, result);
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
		JSObject child = evalForJsObject(command);
		return new Element(webEngine, child);
	}	

	public Element[] getElementsByTagName(String tagName) {
		
		String command = "d3.select(this).selectAll('"+ tagName+"')";
		JSObject result = evalForJsObject(command);
		Array<JSObject> resultArray = new Array<>(webEngine, result);
		
		List<Element> elementList = new ArrayList<>();
		
		resultArray.forEach((element)->{
			JSObject jsElement = (JSObject) element;
			elementList.add(new Element(webEngine, jsElement));
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
		JSObject d3Obj = this.getD3();
		JSObject selectionObj = (JSObject) d3Obj.call("select", getJsObject());
		Selection elementSelection = new Selection(webEngine, selectionObj);
		return elementSelection;
	}

	public BoundingBox getBBox() {
		String command = "this.getBBox();";
		JSObject bBox = evalForJsObject(command);

		Double x = Double.parseDouble("" + bBox.eval("this.x"));
		Double y = Double.parseDouble("" + bBox.eval("this.y"));
		Double width = Double.parseDouble("" + bBox.eval("this.width"));
		Double height = Double.parseDouble("" + bBox.eval("this.height"));
		BoundingBox boundinbBox = new BoundingBox(x, y, width, height);
		return boundinbBox;
	}

	public Element getParentElement() {
		String command = "this.parentNode";
		JSObject result = evalForJsObject(command);
		return new Element(webEngine, result);
	}

	public String getStyle(String identifier) {
		String command = "this.style." + identifier;
		String result = evalForString(command);
		return result;
	}
	
	public String toString(){
		JSObject jsObject = getJsObject();
		if(jsObject==null){
			return "!!Element with missing JSObject!!";
		} else {
			return Inspector.getInspectionInfo(jsObject);
		}
	}

	

	//#end region

}
