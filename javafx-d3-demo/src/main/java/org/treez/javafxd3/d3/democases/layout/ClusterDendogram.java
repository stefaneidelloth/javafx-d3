package org.treez.javafxd3.d3.democases.layout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.PropertyValueDataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.layout.Cluster;
import org.treez.javafxd3.d3.layout.HierarchicalLayout.Node;
import org.treez.javafxd3.d3.layout.Link;
import org.treez.javafxd3.d3.svg.Diagonal;

import javafx.scene.layout.VBox;
import org.treez.javafxd3.d3.core.JsObject;

public class ClusterDendogram extends AbstractDemoCase {

	//#region ATTRIBUTES

	private static final String JSON_URL = "https://raw.githubusercontent.com/stefaneidelloth/javafx-d3/master/javafx-d3-demo/src/main/resources/demo-data/flare.json";

	//#end region

	//#region CONSTRUCTORS

	public ClusterDendogram(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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

		final Cluster cluster = d3.layout() //
				.cluster() //
				.size(height, width - 160);

		DataFunction<Double[]> coordsFunction = new DataFunctionWrapper<>(org.treez.javafxd3.d3.coords.Coords.class,
				engine, (coords) -> {
					return new Double[] { coords.y(), coords.x() };
				});

		final Diagonal diagonal = d3.svg() //
				.diagonal() //
				.projection(coordsFunction);

		final Selection svg = d3.select("svg") //
				.attr("width", width) //
				.attr("height", height) //
				.append("g") //
				.attr("transform", "translate(40,0)");

		// Send request to server and catch any errors.

		HttpURLConnection connection = createHttpConnection(JSON_URL);

		int responseCode;
		try {
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			throw new IllegalStateException("Could not get response code", e);
		}

		StringBuffer response = new StringBuffer();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Could not read response", e);
		}

		if (200 == responseCode) {

			String jsonText = response.toString();
			JsObject jsResult = ConversionUtil.createJsObject(jsonText, engine);
			Node root = new Node(engine, jsResult);
			Array<Node> nodes = cluster.nodes(root);
			Array<Link> links = cluster.links(nodes);

			svg.selectAll("." + "link") //
					.data(links) //
					.enter() //
					.append("path") //
					.attr("class", "link") //
					.attr("d", diagonal);

			DataFunction<String> transformFunction = new DataFunctionWrapper<>(
					org.treez.javafxd3.d3.coords.Coords.class, engine, (coords) -> {
						return "translate(" + coords.y() + "," + coords.x() + ")";
					});

			Selection node = svg.selectAll("." + "node") //
					.data(nodes) //
					.enter() //
					.append("g") //
					.attr("class", "node") //
					.attr("transform", transformFunction);

			node.append("circle")//
					.attr("r", 4.5);

			DataFunction<Integer> dxFunction = new DataFunctionWrapper<>(Node.class, engine, (layoutNode) -> {
				return layoutNode.children() != null ? -8 : 8;
			});

			DataFunction<String> styleFunction = new DataFunctionWrapper<>(Node.class, engine, (layoutNode) -> {
				return layoutNode.children() != null ? "end" : "start";
			});

			DataFunction<String> nameFunction = PropertyValueDataFunction.<String> forProperty(engine, "name");

			node.append("text") //
					.attr("dx", dxFunction) //
					.attr("dy", 3) //
					.style("text-anchor", styleFunction) //
					.text(nameFunction);

			d3.select("svg") //
					.style("height", height + "px");

		} else {
			throw new IllegalStateException("Couldn't retrieve JSON");
		}

	}

	private HttpURLConnection createHttpConnection(String url) {
		URL obj;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalStateException("Could not create url", e);
		}

		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			throw new IllegalStateException("Could not open connection", e);
		}
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			throw new IllegalStateException("Could not set request method", e);
		}
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		return connection;
	}

	@Override
	public void stop() {

	}

	//#end region

}
