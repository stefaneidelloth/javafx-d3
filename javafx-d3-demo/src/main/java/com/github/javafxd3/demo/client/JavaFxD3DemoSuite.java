package com.github.javafxd3.demo.client;

import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.demo.client.democases.svg.LineDemo;

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

	// #region ATTRIBUTES
	
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
	

	// #end region

	// #region METHODS

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
		demoMenuBox.setPrefWidth(100);
		hBoxChildren.add(demoMenuBox);
		
		//create box for preferences of active demo
		VBox demoPreferenceBox = new VBox();
		//demoPreferenceBox.setPrefWidth(100);
		demoPreferenceBox.setStyle("-fx-background-color: steelblue");
		hBoxChildren.add(demoPreferenceBox);

		//define what to do after the browser has initialized
		Runnable afterBrowserLoadingHook = () -> {
			runDemoSuite(stage, demoMenuBox, demoPreferenceBox);
		};
		//create browser
		browser = new JavaFxD3Browser(afterBrowserLoadingHook);

		// add browser
		hBoxChildren.add(browser);

		// create and show the scene
		final int sceneWidth = 1000;
		final int sceneHeight = 800;
		final Color sceneColor = Color.web("#666970");
		scene = new Scene(sceneContent, sceneWidth, sceneHeight, sceneColor);
		stage.setScene(scene);
		stage.show();
		
		//note: see method runDemo to continue with  the work flow

	}

	private void runDemoSuite(Stage stage, VBox buttonPane, VBox demoPreferenceBox) {
		// get d3 wrapper
		D3 d3 = browser.getD3();

		// set stage title
		String versionString = "D3 API version: " + d3.version();
		String title = "Welcome to javax-d3 : A thin Java wrapper around d3." + versionString;
		stage.setTitle(title);

		//create demo menu buttons
		createDemoSuiteMenu(d3, buttonPane, demoPreferenceBox);

	}

	private void createDemoSuiteMenu(D3 d3, VBox demoMenu, VBox demoPreferenceBox) {

		List<Node> menuChildren = demoMenu.getChildren();		
		
		// children.add(new DemoMenuButton("Arc", ArcDemo.factory()));
		// children.add(new DemoMenuButton("Axis Component",
		// AxisComponent.factory()));
		menuChildren.add(new DemoMenuButton("Line Demo", LineDemo.factory(d3, demoPreferenceBox)));
		// children.add(new DemoMenuButton("Symbol Demo", SymbolDemo.factory()));
		// children.add(new DemoMenuButton("General Update Pattern I",
		// GeneralUpdatePattern1.factory()));
		// children.add(new DemoMenuButton("General Update Pattern II",
		// GeneralUpdatePattern2.factory()));
		// children.add(new DemoMenuButton("General Update Pattern III",
		// GeneralUpdatePattern3.factory()));
		// children.add(new DemoMenuButton("Arc Tween", ArcTween.factory()));
		// children.add(new DemoMenuButton("Focus And Context",
		// FocusAndContext.factory()));
		// children.add(new DemoMenuButton("Chord diagram",
		// ChordDiagram.factory()));
		// children.add(new DemoMenuButton("Lorenz System",
		// LorenzSystem.factory()));

		
		// children.add(new DemoMenuButton("Shape Tweening",
		// ShapeTweeningDemo.factory()));
		// GEOM
		// children.add(new DemoMenuButton("Convex Hull", HullDemo.factory()));
		// children.add(new DemoMenuButton("Mitchell's Best Candidate",
		// MitchellBestCandidate.factory()));
		// children.add(new DemoMenuButton("Voronoi Tessellation",
		// VoronoiTessellationDemo.factory()));

		// children.add(new DemoMenuButton("Drag Multiples",
		// DragMultiples.factory()));
		// children.add(new DemoMenuButton("Zoom", ZoomDemo.factory()));
		// children.add(new DemoMenuButton("Collapsible Tree", TreeDemo.factory()));

		// children.add(new DemoMenuButton("Cluster Dendogram",
		// ClusterDendogram.factory()));
		// BRUSHES
		// children.add(new DemoMenuButton("Brush As Slider",
		// BrushAsSliderDemo.factory()));
		// children.add(new DemoMenuButton("Scatterplot Matrix Brushing",
		// ScatterplotMatrixDemo.factory()));
		// children.add(new DemoMenuButton("Ordinal Brushing",
		// OrdinalBrushingDemo.factory()));
		// children.add(new DemoMenuButton("Brush Transitions",
		// BrushTransitionsDemo.factory()));

	}

	
	// #end region

	// #region DEMO MENU BUTTON CLASS

	/**
	 * A button that starts a corresponding demo
	 */
	public class DemoMenuButton extends Button {
		
		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param title
		 * @param demoClass
		 */
		public DemoMenuButton(final String title, final DemoFactory demoClass) {
			super(title);

			this.onMouseClickedProperty().set((event)->{
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
			DemoCase demo = demoClass.newInstance();				
			currentDemo = demo;
			demo.start();
		}
		
		//#end region
	}

	// #end region

}
