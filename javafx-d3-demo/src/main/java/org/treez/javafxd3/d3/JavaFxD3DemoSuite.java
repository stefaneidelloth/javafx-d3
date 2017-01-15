package org.treez.javafxd3.d3;

import java.util.List;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.democases.arcTween.ArcTween;
import org.treez.javafxd3.d3.democases.axis.AxisComponent;
import org.treez.javafxd3.d3.democases.barchart.BarChart;
import org.treez.javafxd3.d3.democases.behaviors.DragMultiples;
import org.treez.javafxd3.d3.democases.behaviors.ZoomDemo;
import org.treez.javafxd3.d3.democases.chorddiagram.ChordDiagram;
import org.treez.javafxd3.d3.democases.focus.FocusAndContext;
import org.treez.javafxd3.d3.democases.functionplotter.FunctionPlotDemo;
import org.treez.javafxd3.d3.democases.geom.hull.HullDemo;
import org.treez.javafxd3.d3.democases.geom.mitchell.MitchellBestCandidate;
import org.treez.javafxd3.d3.democases.geom.voronoi.VoronoiTessellationDemo;
import org.treez.javafxd3.d3.democases.helloworld.HelloWorldDemo;
import org.treez.javafxd3.d3.democases.layout.ClusterDendogram;
import org.treez.javafxd3.d3.democases.lorenz.LorenzSystem;
import org.treez.javafxd3.d3.democases.svg.brush.ordinal.OrdinalBrushingDemo;
import org.treez.javafxd3.d3.democases.svg.brush.scatter.ScatterPlotMatrixDemo;
import org.treez.javafxd3.d3.democases.svg.brush.slider.BrushAsSliderDemo;
import org.treez.javafxd3.d3.democases.svg.brush.transitions.BrushTransitionsDemo;
import org.treez.javafxd3.d3.democases.svg.line.LineDemo;
import org.treez.javafxd3.d3.democases.svg.symbol.SymbolDemo;
import org.treez.javafxd3.d3.democases.svg.text.TextDemo;
import org.treez.javafxd3.d3.democases.update.GeneralUpdatePattern1;
import org.treez.javafxd3.d3.democases.update.GeneralUpdatePattern2;
import org.treez.javafxd3.d3.democases.update.GeneralUpdatePattern3;
import org.treez.javafxd3.d3.democases.xy.XyDemo;
import org.treez.javafxd3.javafx.JavaFxD3Browser;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JavaFxD3DemoSuite extends Application {

	//#region ATTRIBUTES

	/**
	 * The JavaFx main scene
	 */
	private Scene scene;

	/**
	 * The content of the scene
	 */
	private StackPane sceneContent;

	/**
	 * The java fx browser node
	 */
	private JavaFxD3Browser browser;

	/**
	 * The currently active demo
	 */
	private DemoCase currentDemo;

	private final static int DEMO_BUTTON_WIDTH = 180;

	//#end region

	//#region METHODS

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void start(Stage stage) {

		// create content node
		sceneContent = new StackPane();
		HBox hBox = new HBox();
		sceneContent.getChildren().add(hBox);
		List<Node> hBoxChildren = hBox.getChildren();

		// create box for demo case menu buttons
		VBox demoMenuBox = new VBox();
		demoMenuBox.setPrefWidth(DEMO_BUTTON_WIDTH);
		hBoxChildren.add(demoMenuBox);

		// create box for preferences of active demo
		VBox demoPreferenceBox = new VBox();
		// demoPreferenceBox.setPrefWidth(100);
		demoPreferenceBox.setStyle("-fx-background-color: steelblue");
		hBoxChildren.add(demoPreferenceBox);

		// define what to do after the browser has initialized
		Runnable afterBrowserLoadingHook = () -> {
			runDemoSuite(stage, demoMenuBox, demoPreferenceBox);
		};
		// create browser
		browser = new JavaFxD3Browser(afterBrowserLoadingHook, true);

		// add browser
		hBoxChildren.add(browser);

		// create and show the scene
		final int sceneWidth = 1200;
		final int sceneHeight = 800;
		final Color sceneColor = Color.web("#666970");
		scene = new Scene(sceneContent, sceneWidth, sceneHeight, sceneColor);
		stage.setScene(scene);
		stage.show();

		// note: see method runDemo to continue with the work flow

	}

	private void runDemoSuite(Stage stage, VBox buttonPane, VBox demoPreferenceBox) {
		// get d3 wrapper
		D3 d3 = browser.getD3();

		// set stage title
		String versionString = "D3 API version: " + d3.version();
		String title = "Welcome to javax-d3 : A thin Java wrapper around d3." + versionString;
		stage.setTitle(title);

		// create demo menu buttons
		createDemoSuiteMenu(d3, buttonPane, demoPreferenceBox);

	}

	private void createDemoSuiteMenu(D3 d3, VBox demoMenu, VBox prefBox) {

		List<Node> menuChildren = demoMenu.getChildren();

		//HELLO WORLD
		menuChildren.add(new DemoMenuButton("Hello World", HelloWorldDemo.factory(d3, prefBox)));

		// SVG
		menuChildren.add(new DemoMenuButton("Text", TextDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Symbols", SymbolDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Lines", LineDemo.factory(d3, prefBox)));		
		menuChildren.add(new DemoMenuButton("Arc Tween", ArcTween.factory(d3, prefBox)));

		// GEOM
		menuChildren.add(new DemoMenuButton("Convex Hull", HullDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Mitchell's Best Candidate", MitchellBestCandidate.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Voronoi Tessellation", VoronoiTessellationDemo.factory(d3, prefBox)));

		// CHART
		menuChildren.add(new DemoMenuButton("XY plot", XyDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Bar chart", BarChart.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Function plot", FunctionPlotDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Axis Component", AxisComponent.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("General Update Pattern I", GeneralUpdatePattern1.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("General Update Pattern II", GeneralUpdatePattern2.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("General Update Pattern III", GeneralUpdatePattern3.factory(d3, prefBox)));

		menuChildren.add(new DemoMenuButton("Focus And Context", FocusAndContext.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Chord diagram", ChordDiagram.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Lorenz System", LorenzSystem.factory(d3, prefBox)));		

		// BEHAVIOR
		menuChildren.add(new DemoMenuButton("Drag Multiples", DragMultiples.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Zoom", ZoomDemo.factory(d3, prefBox)));

		//LAYOUT
		menuChildren.add(new DemoMenuButton("Cluster Dendogram", ClusterDendogram.factory(d3, prefBox)));

		// BRUSHES
		menuChildren.add(new DemoMenuButton("Brush As Slider", BrushAsSliderDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Scatterplot Matrix Brushing", ScatterPlotMatrixDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Ordinal Brushing", OrdinalBrushingDemo.factory(d3, prefBox)));
		menuChildren.add(new DemoMenuButton("Brush Transitions", BrushTransitionsDemo.factory(d3, prefBox)));

	}

	/**
	 * Clears the content of the root element and returns the root as Selection
	 * 
	 * @return
	 */
	public Selection clearContent() {

		D3 d3 = browser.getD3();
		d3.selectAll("svg").remove();
		d3.select("#root").selectAll("*").remove();
		d3.select("head").selectAll("link").remove();

		Selection svg = d3.select("#root") //
				.append("svg") //
				.attr("id", "svg");
		return svg;
	}

	/**
	 * @return
	 */
	public Selection getSvg() {
		D3 d3 = browser.getD3();
		Selection root = d3.select("#svg");
		return root;
	}

	//#end region

	//#region DEMO MENU BUTTON CLASS

	/**
	 * A button that starts a corresponding demo
	 */
	public class DemoMenuButton extends Button {

		//#region CONSTRUCTORS

		public DemoMenuButton(final String title, final DemoFactory demoClass) {
			super(title);

			this.onMouseClickedProperty().set((event) -> {
				stopCurrentDemo();
				createAndStartNewDemo(demoClass);
			});
		}

		//#end region

		//#region METHODS

		private void stopCurrentDemo() {
			if (currentDemo != null) {
				currentDemo.stop();
				currentDemo = null;

			}
		}

		private void createAndStartNewDemo(final DemoFactory demoClass) {
			clearContent();
			DemoCase demo = demoClass.newInstance();
			currentDemo = demo;
			demo.start();
		}

		//#end region
	}

	//#end region

}
