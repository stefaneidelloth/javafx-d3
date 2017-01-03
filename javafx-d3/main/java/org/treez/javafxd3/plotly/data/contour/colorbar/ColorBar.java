package org.treez.javafxd3.plotly.data.contour.colorbar;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.plotly.data.Font;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * https://plot.ly/javascript/reference/#contour-colorbar
 *
 */
public class ColorBar extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public ColorBar(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public ColorBar(WebEngine webEngine) {
		super(webEngine);
		setEmptyObjectAsJsObject();	
	}	

	//#end region

	//#region ACCESSORS	
	
	//#region POSITION
	
	public void setXAnchor(HorizontalPosition position) {
		setMember("xanchor", position.toString());		
	}
	
	public void setYAnchor(VerticalPosition position) {
		setMember("yanchor", position.toString());		
	}
	
	public void setX(double relativeX) {
		setMember("x", relativeX);		
	}

	public void setY(double relativeY) {
		setMember("y", relativeY);		
	}
	
	//#end region
	
	//#region SIZE

	public void setLen(double relativeLength) {
		setMember("len", relativeLength);		
	}
	
	public void setLenMode(LenMode lenMode){
		setMember("lenmode", lenMode.toString());
	}
	
	public void setThickness(double thickness){
		setMember("thickness", thickness);
	}
	
	public void setThicknessMode(LenMode thicknessMode){
		setMember("thicknessmode", thicknessMode.toString());
	}
	
	//#end region	
	
	
	//#region SCALE 
	
	public void setShowExponent(ShowDetail expontentOption){
		setMember("showexponent", expontentOption.toString());
	}
	
	public void setExponentFormat(ExponentFormat exponentFormat){
		setMember("exponentformat", exponentFormat.toString());
	}
	
	//#end region
	
	//#region TICKS
	
	public void setShowTickLabels(Boolean showTickLabels){
		setMember("showTickLabels", showTickLabels);
	}
	
	public void setShowTickSuffix(ShowDetail suffixOption){
		setMember("showticksuffix", suffixOption.toString());
	}
	
	public void setShowTickPrefix(ShowDetail prefixOption){
		setMember("showtickprefix", prefixOption.toString());
	}	
	
		
	public void setTicks(TickPosition tickPosition){
		setMember("ticks", tickPosition.toString());
	}
	
	public void setTicks(String tickPosition){
		setMember("ticks", tickPosition);
	}
	
	public void setNTicks(int numberOfTicks){
		setMember("nticks", numberOfTicks);
	}	
	
	public void setTickLen(double tickLength){
		setMember("ticklen", tickLength);
	}
	
	public void setTickColor(String color){
		setMember("tickcolor", color);
	}
	
	public void setTickWidth(double width){
		setMember("tickwidth", width);
	}
	
	public void setTickMode(TickMode tickMode){
		setMember("tickmode", tickMode.toString());
	}
	
	public void setTickMode(String tickMode){
		setMember("tickmode", tickMode);
	}
	
	public void setTickText(String[] tickTextArray){
		setMember("ticktext", tickTextArray);
	}
	
	public void setTickVals(double[] tickValues){
		setMember("tickvals", tickValues);
	}
	
	public void setTick0(double tick0){
		setMember("tick0", tick0);
	}
	
	public void setDTick(double dTick){
		setMember("dtick", dTick);
	}
	
	public void setTickAngle(double tick0){
		setMember("tickangle", tick0);
	}
	
	public void setXPad(double xPad){
		setMember("cpad", xPad);
	}
	
	public void setYPad(double yPad){
		setMember("ypad", yPad);
	}
		
	
	//#end region
	
	public void setBorderWidth(double width){
		setMember("borderwidth", width);
	}
	
	public void setTitle(String title) {
		setMember("title", title);		
	}
	
	public void setTitleSide(TitleSide titleSide){
		setMember("titleside", titleSide.toString());
	}
	
	public void setTitleSide(String titleSide){
		setMember("titleside", titleSide);
	}
	
	public void setTitleFont(Font titleFont) {
		setMember("titlefont", titleFont.getJsObject());		
	}	
	
	public void setTickFont(Font tickFont) {
		setMember("tickfont", tickFont.getJsObject());		
	}

	//#end region

}
