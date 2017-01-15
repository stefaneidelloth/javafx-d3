package org.treez.javafxd3.d3.democases.barchart;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class BarChartCallback implements DsvCallback<BarChartData> {

	private WebEngine webEngine;
	private BarChart barChart;

	public BarChartCallback(WebEngine webEngine, BarChart barChart) {
		this.webEngine = webEngine;
		this.barChart = barChart;
	}

	@Override
	public void get(final Object error, final Object dataArray) {

		Platform.runLater(() -> {

			JSObject jsDsvDataArray = (JSObject) dataArray;
			Array<BarChartData> values = new Array<BarChartData>(webEngine, jsDsvDataArray);
			List<? extends BarChartData> valueList = values.asList(BarChartData.class);			
			
			
			String[] letters = new String[valueList.size()];			
			
			double maxFrequency = valueList.get(0).getFrequency();
			for (int index = 0; index < valueList.size(); index++) {
				BarChartData dataEntry = valueList.get(index);
				
				letters[index] = dataEntry.getLetter();
				
				Double frequency = dataEntry.getFrequency();
				if (frequency > maxFrequency) {
					maxFrequency = frequency;
				}
			}	
			
			OrdinalScale x = barChart.getXScale();
			x.domain(letters);
			
			double[] frequencies = new double[]{0.0, maxFrequency};
			
			LinearScale y = barChart.getYScale();	
			y.domain(frequencies);

			Selection svgGroup = barChart.getSvgGroup();
			int height = barChart.getHeight();
			Axis xAxis = barChart.getXAxis();

			svgGroup.append("g") //
					.attr("class", "x axis") //
					.attr("transform", "translate(0," + height + ")") //
					.call(xAxis);

			Axis yAxis = barChart.getYAxis();

			svgGroup.append("g") //
					.attr("class", "y axis") //
					.call(yAxis) //
					.append("text") //
					.attr("transform", "rotate(-90)") //
					.attr("y", 6) //
					.attr("dy", ".71em") //
					.style("text-anchor", "end") //
					.text("Frequency");

			List<Object> objectCollection = values.asList(Object.class);
			
			DataFunction<Double> xDataFunction = new DataFunctionWrapper<>(BarChartData.class, webEngine, (data)->{
				String letter = data.getLetter();					
				OrdinalScale xScale = barChart.getXScale();
				return xScale.apply(letter).asDouble();
			});
			
			DataFunction<Double> yDataFunction = new DataFunctionWrapper<>(BarChartData.class, webEngine, (data)->{
				String letter = data.getLetter();					
				LinearScale yScale = barChart.getYScale();
				return yScale.apply(letter).asDouble();
			});
			
			DataFunction<Double> heightDataFunction = new DataFunctionWrapper<>(BarChartData.class, webEngine, (data)->{
				Double frequency = data.getFrequency();		
				
				LinearScale yScale = barChart.getYScale();			
				Value scaledValue = yScale.apply(frequency);			
				Double scaledDoubleValue = scaledValue.asDouble();	
				
				int totalHeight = barChart.getHeight();
				double result = totalHeight -scaledDoubleValue ;
				return result;
			});

			svgGroup.selectAll(".bar") //
					.dataObjectCollection(objectCollection) //
					.enter() //
					.append("rect") //
					.attr("class", "bar") //
					.attr("x", xDataFunction) //
					.attr("width", x.rangeBand()) //
					.attr("y", yDataFunction)
					.attr("height", heightDataFunction);

		});

	}

}
