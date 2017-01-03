package org.treez.javafxd3.plotly.data;

import java.util.ArrayList;
import java.util.List;


public enum PlotlyType  {
	
	//#region VALUES
	
	AREA("area"),
	BAR("bar"),
	BOX("box"),
	CHOROPLETH("choropleth"),
	HEATMAP("heatmap"),
	HISTOGRAM("histogram"),
	HISTOGRAM_2D("histogram2d"),
	HISTOGRAM_2D_CONTOUR("histogram2dcountour"),
	MESH("mesh3d"),
	PIE("pie"),
	SCATTER("scatter"),
	SCATTER_3D("scatter3d"),
	SCATTER_GEO("scattergeo"),
	SCATTER_GL("scattergl"),
	SURFACE("surface"),
	CONTOUR("contour");
	
	//#end region
	
	//#region ATTRIBUTES
	
	String value;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	PlotlyType(String value){
		this.value=value;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public String toString() {
		return value;
	}
	
	public static PlotlyType fromString(final String value) {
		return valueOf(value.toUpperCase().replace('-', '_'));
	}
	
	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (PlotlyType enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}
	
	//#end region

}
