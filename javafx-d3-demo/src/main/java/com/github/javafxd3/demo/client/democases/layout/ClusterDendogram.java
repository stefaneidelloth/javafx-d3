package com.github.javafxd3.demo.client.democases.layout;

import java.awt.Window;

import javax.xml.ws.Response;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.PropertyValueFunction;
import com.github.javafxd3.api.layout.Cluster;
import com.github.javafxd3.api.layout.HierarchicalLayout.Node;
import com.github.javafxd3.api.layout.Link;
import com.github.javafxd3.api.svg.Diagonal;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * 
 */
public class ClusterDendogram extends AbstractDemoCase {

	//#region ATTRIBUTES

	private static final String JSON_URL = // GWT.getModuleBaseURL() +
	"demo-data/flare.json";

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ClusterDendogram(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// css = Bundle.INSTANCE.css(); @Source("ClusterDendogram.css")
		// link, node
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new ClusterDendogram(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		int width = 960;
		final int height = 2200;

		final Cluster cluster = d3.layout().cluster().size(height, width - 160);

		final Diagonal diagonal = d3.svg().diagonal().projection(new DatumFunction<Double[]>() {
			@Override
			public Double[] apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return new Double[]{datum.asCoords().y(), datum.asCoords().x()};
			}
		});

		final Selection svg = d3.select("root").append("svg").attr("width", width).attr("height", height).append("g")
				.attr("transform", "translate(40,0)");

		// Send request to server and catch any errors.
		
		/*
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL);

		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onError(final Request request, final Throwable exception) {
					Window.alert("Couldn't retrieve JSON");
				}

				@Override
				public void onResponseReceived(final Request request, final Response response) {
					if (200 == response.getStatusCode()) {
						Node root = JsonUtils.safeEval(response.getText());
						Node[] nodes = cluster.nodes(root);
						Link[] links = cluster.links(nodes);

						Selection link = svg.selectAll("." + "link").data(links).enter().append("path")
								.attr("class", "link").attr("d", diagonal);

						Selection node = svg.selectAll("." + "node").data(nodes).enter().append("g")
								.attr("class", "node").attr("transform", new DatumFunction<String>() {
							@Override
							public String apply(final Element context, final Value value, final int index) {
								return "translate(" + value.asCoords().y() + "," + value.asCoords().x() + ")";
							}
						});

						node.append("circle").attr("r", 4.5);

						node.append("text").attr("dx", new DatumFunction<Integer>() {
							@Override
							public Integer apply(final Element context, final Value d, final int index) {
								return d.<Node> as().children() != null ? -8 : 8;
							}
						}).attr("dy", 3).style("text-anchor", new DatumFunction<String>() {
							@Override
							public String apply(final Element context, final Value d, final int index) {
								return d.<Node> as().children() != null ? "end" : "start";
							}
						}).text(PropertyValueFunction.<String> forProperty("name"));

						d3.select("root").select("svg").style("height", height + "px");

					} else {
						Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
					}
				}
			});
		} catch (Exception e) {
			System.err.println("Couldn't retrieve JSON"+ e.getMessage());
		}
		
		*/

	}

	@Override
	public void stop() {

	}

	//#end region

}
