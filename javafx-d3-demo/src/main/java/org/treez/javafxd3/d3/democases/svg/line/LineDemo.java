package org.treez.javafxd3.d3.democases.svg.line;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.EnteringSelection;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.svg.Line;
import org.treez.javafxd3.d3.svg.Line.InterpolationMode;
import org.treez.javafxd3.d3.wrapper.Inspector;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 */
public class LineDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private boolean showPoints = true;

	private final Stack<Coords> points = new Stack<>();

	private Selection svg;

	private Selection path;

	private Line line;

	protected InterpolationMode mode = InterpolationMode.LINEAR;

	protected int width = 450;
	protected int height = 320;

	protected double tension;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public LineDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}	

	//#end region

	//#region METHODS

	/**
	 * Provides a factory for this demo case
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new LineDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		System.out.println("Starting Line Demo");

		// create initial d3 content

		DatumFunction<Double> xAccessor = CustomCoords.xAccessor(webEngine);
		DatumFunction<Double> yAcccessor = CustomCoords.yAccessor(webEngine);
		DatumFunction<Boolean> isDefinedAccessor = CustomCoords.definedAccessor(webEngine);
		line = d3.svg().line().x(xAccessor).y(yAcccessor).defined(isDefinedAccessor);

		svg = d3.select("svg").attr("width", width).attr("height", height).append("g");

		String cssClassName = "linedemo";
		path = svg.append("path").classed(cssClassName, true);
		
		deleteOldPreferenceChildren();

		// create preferences and buttons
		createButton("Add point", (event) -> {
			addPoint();
		});

		createButton("Remove point", (event) -> {
			removePoint();
		});

		createButton("Add undefined point", (event) -> {
			addPoint(false);
		});

		InterpolationMode[] interpolationModes = Line.InterpolationMode.values();
		createInterpolationModeWidget(interpolationModes);

		createTensionWidget();

		createShowPointsCheckBox();

	}

	@Override
	public void stop() {
		points.clear();
		demoPreferenceBox.getChildren().clear();
	}

	private void createTensionWidget() {
		HBox horizontalBox = new HBox();
		horizontalBox.setAlignment(Pos.BASELINE_CENTER);
		List<Node> children = horizontalBox.getChildren();

		// title
		Label title = new Label("Tension:");
		children.add(title);

		// tension field
		TextField tentsionField = new TextField();
		Double tensionValue = line.tension();
		tentsionField.setText("" + tensionValue);

		// add listener
		tentsionField.onInputMethodTextChangedProperty().set((event) -> {
			String tensionInput = tentsionField.getText();
			tension = Double.parseDouble(tensionInput);
			updateD3Content();
		});
		horizontalBox.getChildren().add(tentsionField);

		demoPreferenceBox.getChildren().add(horizontalBox);
	}

	private void createShowPointsCheckBox() {

		// create check box
		CheckBox cb = new CheckBox("Show points");
		cb.setSelected(true);

		// add listener
		cb.onMouseClickedProperty().set((event) -> {
			showPoints = cb.isSelected();
			updateD3Content();
		});

		// add to preferences box
		demoPreferenceBox.getChildren().add(cb);

	}

	private void createInterpolationModeWidget(InterpolationMode[] values) {
		
		ToggleGroup rbGroup = new ToggleGroup();

		boolean first = true;
		for (final InterpolationMode mode : values) {

			// create button
			RadioButton button = new RadioButton();
			button.setToggleGroup(rbGroup);
			button.setText(mode.name());

			// add listener
			button.onActionProperty().set((event) -> {
				LineDemo.this.mode = mode;
				updateD3Content();
			});

			// add to preferences box
			demoPreferenceBox.getChildren().add(button);

			// set selection state of first button to true
			if (first) {
				button.setSelected(true);
				first = false;
			}
		}
	}

	protected void addPoint() {
		addPoint(true);
	}

	protected void addPoint(boolean defined) {

		System.out.println("Adding point");

		Random random = new Random();
		double x = random.nextInt(width);
		double y = random.nextInt(height);
		CustomCoords coords = new CustomCoords(webEngine, x, y, defined);
		points.push(coords);

		updateD3Content();
	}

	protected void removePoint() {
		if (!points.empty())
			points.pop();
		updateD3Content();
	}

	/**
	 * 
	 */
	public void updateD3Content() {

		System.out.println("Updating content");

		line = line.interpolate(mode);
		line = line.tension(tension);

		List<Coords> coordsList = new ArrayList<>(points);

		// Double[] values = new Double[]{20.0,20.0};

		String coordinates = line.generate(coordsList);
		path.attr("d", coordinates);

		ArrayList<Coords> data;
		if (showPoints) {
			data = new ArrayList<>(points);
		} else {
			data = new ArrayList<>();
		}

		UpdateSelection updateSelection = getSvg().selectAll("circle").data(data);

		DatumFunction<Double> cxFunction = new CxDatumFunction(webEngine);

		DatumFunction<Double> cyFunction = new CyDatumFunction(webEngine);

		EnteringSelection enter = updateSelection.enter();
		if (enter != null) {
			Selection result = enter.append("circle").attr("cx", cxFunction).attr("cy", cyFunction).attr("r", 10);
						
			//Inspector.inspect(result);
			updateSelection.exit().remove();
		}

	}

	//#end region
	
}
