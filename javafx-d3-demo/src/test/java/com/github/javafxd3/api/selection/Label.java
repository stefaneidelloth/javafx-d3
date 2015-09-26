package com.github.javafxd3.api.selection;

import java.util.HashMap;
import java.util.Map;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.wrapper.D3NodeFactory;
import com.github.javafxd3.api.wrapper.Style;

/**
 * A d3 svg label factory
 *
 */
public class Label implements D3NodeFactory {
	
	//#region ATTRIBUTES
	
	private String innerHtml = "";
	
	private String text = "";
	
	private Style style = new Style();
	
	private String styleClass = "";
	
	
	
	Map<String, String> additionalAttributes = new HashMap<>();
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 */
	public Label(){
		
	}
	
	/**
	 * Constructor with text
	 * @param text
	 */
	public Label(String text){
		this.text = text;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Selection create(Selection selection) {
		Selection label = selection.append("text")
				.attr("innerHtml", innerHtml)
				.attr("value", text)			
				.attr("class", styleClass);
		
		
		//TODO: apply Style
		
		for(String attr: additionalAttributes.keySet()){
			String value = additionalAttributes.get(attr);
			label = label.attr(attr, value);
		}
		
		return null;
	}

	@Override
	public Selection remove(Selection selection) {
		throw new IllegalStateException("not yet implemented");
		
	}
	
	//#end region
	
	//#region ACCESSORS
	
	/**
	 * @param styleClass
	 */
	public void setStyleClass(String styleClass){
		this.styleClass = styleClass;
	}
	
	/**
	 * Sets an attribute value
	 * 
	 * @param attr
	 * @param value
	 */
	public void setAttribute(String attr, String value) {
		additionalAttributes.put(attr, value);		
	}

	/**
	 * @param value
	 */
	public void setInnerHTML(String value) {
		innerHtml = value;		
	}
	
	/**
	 * @param value
	 */
	public void setInnerText(String value) {
		throw new IllegalStateException("not yet implemented");
		
	}

	/**
	 * @param attr
	 * @param value
	 */
	public void setPropertyString(String attr, String value) {
		throw new IllegalStateException("not yet implemented");
		
	}

	/**
	 * @return
	 */
	public Style getStyle() {		
		return style;
	}

		
	
	//#end region
	

}
