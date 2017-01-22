package org.treez.javafxd3.javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Helps to export svg content from the JavaFx JsEngine
 *
 */
public class SaveHelper {

	/**
	 * Save the given svg content as svg file
	 * 
	 * @param svgContent
	 */
	public void saveSvg(String svgContent) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export SVG");
		fileChooser.setInitialFileName("svgExport");		
		ExtensionFilter extensionFilter = new ExtensionFilter("Scalable Vector Graphics (SVG)","*.svg");		
		fileChooser.getExtensionFilters().add(extensionFilter);
		File file = fileChooser.showSaveDialog(null);

		if (file != null) {			
			try {
				PrintWriter out = new PrintWriter(file);
				out.print(svgContent);
				out.close();
			} catch (FileNotFoundException e) {
				// 
			}
		}
	}
}
