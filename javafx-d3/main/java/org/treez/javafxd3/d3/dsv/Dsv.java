package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Dsv<T> extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Dsv(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
     * Defines a callback to invoke on request response.
     * <p>
     * When the CSV data is available, the specified callback will be invoked with the parsed rows as the argument. If
     * an error occurs, the callback function will instead be invoked with null.
     * 
     * @param callback
     *            the callback.
     * @return the CSV module.
     */
    public  Dsv<T> get(DsvCallback<T> callback) {
    	
    	assertObjectIsNotAnonymous(callback);
    	
    	throw new IllegalStateException("not yet implemented");
		//return this
		//		.get(function(error, rows) {
		//			callback.@com.github.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/github/gwtd3/api/dsv/DsvRows;)(error, rows);
		//		});
    }

	/**
     * Defines an accessor to invoke on each rows.
     * <p>
     * An optional accessor function may be specified, which is then passed to d3.csv.parse; the accessor may also be
     * specified by using the return request object’s row function.
     * 
     * @param accessor
     *            the accessor.
     * @return the CSV module.
     */
    public  Dsv<T> row(DsvObjectAccessor<T> accessor) {
    	
    	assertObjectIsNotAnonymous(accessor);
    	
    	throw new IllegalStateException("not yet implemented");
		/*
		return this
				.row(function(row, index) {
					return accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(row, index);
				});
				
		*/
    }

	/**
	 * Parse a CSV string into objects using the header row.
	 * <p>
	 * Parses the specified string, which is the contents of a CSV file,
	 * returning an array of objects representing the parsed rows. The string is
	 * assumed to be RFC4180-compliant. Unlike the parseRows method, this method
	 * requires that the first line of the CSV file contain a comma-separated
	 * list of column names; these column names become the attributes on the
	 * returned objects.
	 * 
	 * @param csvContent
	 *            the content of a CSV file.
	 * @return the rows.
	 */
	public Array<DsvRow> parse(String csvContent) {
		
		
		JSObject result = call("parse", csvContent);
		
		
		Object child =  result.eval("this[0].Names");
		
		
		if (result == null){
			return null;
		}
		return new Array<DsvRow>(webEngine, result);		
	}

	/**
     * Parse a CSV string into objects using the header row.
     * <p>
     * Parses the specified string, which is the contents of a CSV file, returning an array of objects representing the
     * parsed rows. The string is assumed to be RFC4180-compliant. Unlike the parseRows method, this method requires
     * that the first line of the CSV file contain a comma-separated list of column names; these column names become the
     * attributes on the returned objects.
     * 
     * @param csvContent
     *            the content of a CSV file.
     * @param accessor
     *            the accessor.
     * @return the rows.
     */
    public Array<T> parse(String csvContent, DsvObjectAccessor<T> accessor) {   
    	
    	assertObjectIsNotAnonymous(accessor);
    	
    	String accessorName = createNewTemporaryInstanceName();
    	JSObject d3JsObject = getD3();
    	d3JsObject.setMember(accessorName,  accessor);
    	
    	String command = "this.parse('" + csvContent + "', function(row, index) {"
    			+ "return d3." + accessorName + ".apply(row, index);" //
    			+ "})";
		JSObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(accessorName);
		
		return new Array<T>(webEngine, result);		
    }

	/**
	 * Parses the specified string, which is the contents of a CSV file,
	 * returning an array of arrays representing the parsed rows.
	 * <p>
	 * The string is assumed to be RFC4180-compliant. Unlike the parse method,
	 * this method treats the header line as a standard row, and should be used
	 * whenever the CSV file does not contain a header. Each row is represented
	 * as an array rather than an object. Rows may have variable length.
	 * 
	 * @param csvContent
	 *            the contents of a CSV file
	 * @return CSV rows
	 */
	public String[][] parseRows(String csvContent) {
		throw new IllegalStateException("not yet implemented");
		//return this.parseRows(csvContent);
	}

	/**
     * Parses the specified string, which is the contents of a CSV file,
     * returning an array of arrays representing the parsed rows.
     * <p>
     * The string is assumed to be RFC4180-compliant. Unlike the parse method, this method treats the header line as a
     * standard row, and should be used whenever the CSV file does not contain a header. Each row is represented as an
     * array rather than an object. Rows may have variable length.
     * 
     * @param csvContent
     *            the contents of a CSV file
     * @param accessor
     *            the accessor
     * @return CSV rows
     */
    public  T[] parseRows(String csvContent,
            DsvArrayAccessor<T> accessor) {
    	
    	assertObjectIsNotAnonymous(accessor);
    	
    	throw new IllegalStateException("not yet implemented");
		/*
		return this
				.parseRows(
						csvContent,
						function(row, i) {
							return accessor.@com.github.gwtd3.api.dsv.DsvArrayAccessor::parse(Lcom/google/gwt/core/client/JsArrayString;I)(row, i);
						});
						
		*/
    }
}
