package org.treez.javafxd3.d3.demo;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.Style;

/**
 * A d3 svg label factory
 *
 */
public class InputElementFactory implements D3NodeFactory {
	
	//#region ATTRIBUTES
	
	private String text = "";	
	
	private Style style = new Style();
	
	private String styleClass = "";	
	
	Map<String, String> additionalAttributes = new HashMap<>();
	
	Map<String, String> additionalProperties = new HashMap<>();
	
	Selection inputElementSelection;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public InputElementFactory(){
		
	}	
	
	public InputElementFactory(String text){
		this.text = text;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Selection createInParentSelection(Selection selection) {
		inputElementSelection = selection.append("input")
				.text(text)										
				.attr("class", styleClass);		
		
		style.apply(inputElementSelection);
		
		for(String attr: additionalAttributes.keySet()){
			String value = additionalAttributes.get(attr);
			inputElementSelection = inputElementSelection.attr(attr, value);
		}
		
		for(String property: additionalProperties.keySet()){
			String value = additionalProperties.get(property);
			inputElementSelection = inputElementSelection.property(property, value);
		}
		
		return inputElementSelection;
	}

	@Override
	public Selection remove(Selection selection) {
		return inputElementSelection.remove();		
	}
	
	//#end region
	
	//#region ACCESSORS
	
	public void setStyleClass(String styleClass){
		if (inputElementSelection!=null){
			inputElementSelection.classed(styleClass);
		}
		this.styleClass = styleClass;
	}
	
	public void setAttribute(String attr, String value) {
		if (inputElementSelection!=null){
			inputElementSelection = inputElementSelection.attr(attr, value);
		}
		additionalAttributes.put(attr, value);		
	}	
	
	public void setText(String value) {
		if (inputElementSelection!=null){
			inputElementSelection = inputElementSelection.text(value);
		}
		text = value;	
	}

	public void setProperty(String property, String value) {
		if (inputElementSelection!=null){
			inputElementSelection = inputElementSelection.property(property, value);
		}
		additionalProperties.put(property, value);
	}
	
	public Style getStyle() {		
		return style;
	}		
	
	//#end region
	

}
