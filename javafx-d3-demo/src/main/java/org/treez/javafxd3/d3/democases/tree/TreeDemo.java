package org.treez.javafxd3.d3.democases.tree;




import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.layout.Tree;
import org.treez.javafxd3.d3.svg.Diagonal;
import org.treez.javafxd3.d3.wrapper.Node;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A demonstration of how to build a d3js tree from simple JSON data with
 * collapse functionality and transitions
 * 
 * 
 * 
 */
public class TreeDemo extends AbstractDemoCase {
	
	//#region ATTRIBUTES

	// constants of tree
	final int width = 960;
	final int height = 600;
	final int duration = 750;
	

	// global references for demo
	static int i = 0;
	static TreeDemoNode root = null;
	static Selection svg = null;
	static Tree tree = null;
	static Diagonal diagonal = null;

	//#end region
	
		//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public TreeDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		//Bundle.INSTANCE.css().ensureInjected();@Source("TreeDemoStyles.css")
		// link, node, border
	}
	
	//#end region
	
	//#region METHODS

	
	/**
	 * Factory provider
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new TreeDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		
		/*

		// dummy data
		String data = "{\n\"children\": [\n{\n\"children\": [\n{},\n{},\n{\n\"children\": [\n{}\n]\n},\n{}\n]\n},\n{}\n]\n}";

		// get tree layout
		tree = d3.layout().tree().size(width, height);
		// set the global way to draw paths
		diagonal = d3.svg().diagonal()
				.projection(new DatumFunction<Double[]>() {
					@Override
					public Double[] apply(Element context, Value d,
							int index) {
						TreeDemoNode data = d.<TreeDemoNode> as();
						return Array.fromDoubles(data.x(), data.y());
					}
				});

		// add the SVG
		svg = d3.select("root").append("svg").attr("width", width + 20)
				.attr("height", height + 280).append("g")
				.attr("transform", "translate(10, 140)");

		// get the root of the tree and initialize it
		root = JSONParser.parseLenient(data).isObject().getJavaScriptObject()
				.<TreeDemoNode> cast();
		root.setAttr("x0", (width - 20) / 2);
		root.setAttr("y0", 0);
		if (root.children() != null) {
			root.children().forEach(new Collapse());
		}
		update(root);
		
		*/
	}

	@Override
	public void stop() {
	}

	// follows d3 general update pattern for handling exiting and entering
	// nodes/paths
	private void update(final TreeDemoNode source) {
		
		/*
		
		Node[] nodes = tree.nodes(root).reverse();
		Link[] links = tree.links(nodes);

		// normalize depth
		nodes.forEach(new ForEachCallback<Void>() {
			@Override
			public Void forEach(Object thisArg, Value element, int index,
					Array<?> array) {
				TreeDemoNode datum = element.<TreeDemoNode> as();
				datum.setAttr("y", datum.depth() * 180);
				return null;
			}
		});

		// assign ids to nodes
		UpdateSelection node = svg.selectAll("g." + css.node()).data(nodes,
				new KeyFunction<Integer>() {
					@Override
					public Integer map(Element context, Object[] newDataArray,
							Value datum, int index) {
						TreeDemoNode d = datum.<TreeDemoNode> as();
						return ((d.id() == -1) ? d.id(++i) : d.id());
					}
				});

		// add click function on node click
		Selection nodeEnter = node
				.enter()
				.append("g")
				.attr("class", css.node())
				.attr("transform",
						"translate(" + source.getNumAttr("x0") + ","
								+ source.getNumAttr("y0") + ")")
				.on("click", new Click());

		// add circles to all entering nodes
		nodeEnter.append("circle").attr("r", 1e-6)
				.style("fill", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						JavaScriptObject node = d.<TreeDemoNode> as()
								.getObjAttr("_children");
						return (node != null) ? "lightsteelblue" : "#fff";
					}
				});

		// transition entering nodes
		Transition nodeUpdate = node.transition().duration(duration)
				.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						TreeDemoNode data = d.<TreeDemoNode> as();
						return "translate(" + data.x() + "," + data.y() + ")";
					}
				});

		nodeUpdate.select("circle").attr("r", 4.5)
				.style("fill", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						JavaScriptObject object = d.<TreeDemoNode> as()
								.getObjAttr("_children");
						return (object != null) ? "lightsteelblue" : "#fff";
					}
				});

		// transition exiting nodes
		Transition nodeExit = node.exit().transition().duration(duration)
				.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						return "translate(" + source.x() + "," + source.y()
								+ ")";
					}
				}).remove();

		nodeExit.select("circle").attr("r", 1e-6);

		// update svg paths for new node locations
		UpdateSelection link = svg.selectAll("path." + "link").data(links,
				new KeyFunction<Integer>() {
					@Override
					public Integer map(Element context, Object[] newDataArray,
							Value datum, int index) {
						return datum.<Link> as().target().<TreeDemoNode> cast()
								.id();
					}
				});

		link.enter().insert("svg:path", "g").attr("class", "link")
				.attr("d", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						Coords o = new Coords(webEngine, source.getNumAttr("x0"),
								source.getNumAttr("y0"));
						return diagonal.generate(Link.create(o, o));
					}
				});

		link.transition().duration(duration).attr("d", diagonal);

		link.exit().transition().duration(duration)
				.attr("d", new DatumFunction<String>() {
					@Override
					public String apply(Element context, Value d, int index) {
						Coords o = Coords.create(source.x(), source.y());
						return diagonal.generate(Link.create(o, o));
					}
				}).remove();

		// update locations on node
		nodes.forEach(new ForEachCallback<Void>() {
			@Override
			public Void forEach(Object thisArg, Value element, int index,
					Object[] array) {
				TreeDemoNode data = element.<TreeDemoNode> as();
				data.setAttr("x0", data.x());
				data.setAttr("y0", data.y());
				return null;
			}
		});
		
		*/
	}
	
	//#end region
	
	//#region CLASSES
	
	/*

	private class Collapse implements ForEachCallback<Void> {
		
		@Override
		public Void forEach(Object thisArg, Value element, int index,
				Object[] array) {
			TreeDemoNode datum = element.<TreeDemoNode> as();
			Node[] children = datum.children();
			if (children != null) {
				datum.setAttr("_children", children);
				datum.getObjAttr("_children").<Node[]> cast()
						.forEach(this);
				datum.setAttr("children", null);
			}
			return null;
		}
	}

	private class Click implements DatumFunction<Void> {
		@Override
		public Void apply(Element context, Value d, int index) {
			TreeDemoNode node = d.<TreeDemoNode> as();
			if (node.children() != null) {
				node.setAttr("_children", node.children());
				node.setAttr("children", null);
			} else {
				node.setAttr("children", node.getObjAttr("_children"));
				node.setAttr("_children", null);
			}
			update(node);
			return null;
		}
	}
	
	*/
	

	// Perhaps a mutable JSO class would be a nice feature?
	private static class TreeDemoNode extends Node {
		
		
		public TreeDemoNode(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine, wrappedJsObject);
		}

		/*
		protected final native int id() -{
			return this.id || -1;
		}

		protected final native int id(int id) -{
			return this.id = id;
		}

		protected final native void setAttr(String name, JavaScriptObject value) {
			this[name] = value;
		}

		protected final native double setAttr(String name, double value) {
			return this[name] = value;
		}

		protected final native JavaScriptObject getObjAttr(String name) {
			return this[name];
		}

		protected final native double getNumAttr(String name) {
			return this[name];
		}
		*/
	}
	
	
	
	//#end region
}
