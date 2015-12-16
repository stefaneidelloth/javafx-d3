package org.treez.javafxd3.d3.democases.svg.text;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.wrapper.D3NodeFactory;
import org.treez.javafxd3.d3.wrapper.Style;

/**
 * A d3 svg label factory
 *
 */
public class LabelFactory implements D3NodeFactory {
	
	//#region ATTRIBUTES
	
	private String innerHtml = "";
	
	private String text = "";
	
	private Style style = new Style();
	
	private String styleClass = "";	
	
	Map<String, String> additionalAttributes = new HashMap<>();
	
	Selection labelSelection;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 */
	public LabelFactory(){
		
	}
	
	/**
	 * Constructor with text
	 * @param text
	 */
	public LabelFactory(String text){
		this.text = text;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Selection createInParentSelection(Selection selection) {
		labelSelection = selection.append("text")
				.text(text)	
				.attr("innerText", text)
				.attr("innerHtml", innerHtml)						
				.attr("class", styleClass);
		
		
		//TODO: apply Style
		
		for(String attr: additionalAttributes.keySet()){
			String value = additionalAttributes.get(attr);
			labelSelection = labelSelection.attr(attr, value);
		}
		
		return labelSelection;
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
		if (labelSelection!=null){
			labelSelection.classed(styleClass);
		}
		this.styleClass = styleClass;
	}
	
	/**
	 * Sets an attribute value
	 * 
	 * @param attr
	 * @param value
	 */
	public void setAttribute(String attr, String value) {
		if (labelSelection!=null){
			labelSelection = labelSelection.attr(attr, value);
		}
		additionalAttributes.put(attr, value);		
	}

	/**
	 * @param value
	 */
	public void setInnerHTML(String value) {
		if (labelSelection!=null){
			labelSelection = labelSelection.attr("innerHtml", value);
		}
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
