package com.github.javafxd3.d3.arrays;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Wraps a one- or two-dimensional JavaScript array. The generic type specifies
 * how the JavaScript array elements are interpreted.
 *
 * @param <T>
 * @param <D>
 */
public class Array<T> extends JavaScriptObject {

	//#region CONSTRUCTORS

	
	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Array(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	

	//#end region

	//#region METHODS

	//#region CREATE

	/**
	 * Creates a one-dimensional Array from the given list of objects. If the
	 * objects are JavaScriptObjects the wrapped JSObjects are extracted and
	 * used instead.
	 * 
	 * @param <L>
	 * @param webEngine
	 * @param data
	 * @return
	 */
	public static <L> Array<L> fromList(WebEngine webEngine, List<L> data) {

		// store data as temporary array
		JSObject d3Obj = (JSObject) webEngine.executeScript("d3");
		String varName = createNewTemporaryInstanceName();

		//initialize temporary array
		int length = data.size();
		String command = "d3." + varName + " = new Array(" + length + ");";
		d3Obj.eval(command);
		JSObject tempArray = (JSObject) d3Obj.getMember(varName);

		//fill temporary array
		for (int index = 0; index < length; index++) {
			Object value = data.get(index);

			boolean isJavaScriptObject = value instanceof JavaScriptObject;
			if (isJavaScriptObject) {
				JavaScriptObject javaScriptObject = (JavaScriptObject) value;
				JSObject wrappedJsObject = javaScriptObject.getJsObject();
				
				//Inspector.inspect(wrappedJsObject);
				
				tempArray.setSlot(index, wrappedJsObject);
			} else {
				tempArray.setSlot(index, value);
			}
		}
		
		//remove temp var
		d3Obj.removeMember(varName);

		// return result as array
		return new Array<L>(webEngine, tempArray);
	}

	/**
	 * Creates a one-dimensional Array from the given Double array
	 * 
	 * @param webEngine
	 * @param data
	 * @return
	 */
	public static Array<Double> fromDoubles(WebEngine webEngine, Double[] data) {

		String varName = createNewTemporaryInstanceName();
		String arrayString = ArrayUtils.createArrayString(data);
		String command = "var " + varName + " = " + arrayString + ";";
		webEngine.executeScript(command);

		// execute command and return result as Array
		JSObject result = (JSObject) webEngine.executeScript(varName);
		
		webEngine.executeScript(varName +" = null;");
		
		return new Array<Double>(webEngine, result);

	}

	/**
	 * Creates a one-dimensional Array from the given two JSObjects
	 * 
	 * @param webEngine
	 * @param first
	 * @param second
	 * @return
	 */
	public static Array<JSObject> fromJavaScriptObjects(WebEngine webEngine, JSObject first, JSObject second) {
		D3 d3 = new D3(webEngine);
		JSObject d3Obj = d3.getJsObject();
		String firstVarName = createNewTemporaryInstanceName();
		String secondVarName = createNewTemporaryInstanceName();

		d3Obj.setMember(firstVarName, first);
		d3Obj.setMember(secondVarName, second);

		String command = "[d3." + firstVarName + ",d3." + secondVarName + "]";
		JSObject result = d3.evalForJsObject(command);

		d3Obj.removeMember(firstVarName);
		d3Obj.removeMember(secondVarName);

		return new Array<JSObject>(webEngine, result);

	}

	//#end region

	//#region RETRIEVE INFO

	/**
	 * Returns the size of the first dimension of the array
	 * 
	 * @return
	 */
	public int length() {
		int result = getMemberForInteger("length");
		return result;
	}

	/**
	 * Returns the number of rows and number of columns of the (maximum
	 * two-dimensional) array as a list, e.g. [2, 3]
	 * 
	 * @return
	 */
	public List<Integer> sizes() {
		int length = length();
		if (length == 0) {
			List<Integer> zeroSizes = createZeroSizes();
			return zeroSizes;
		} else {
			Object firstItem = getAsObject(0);
			boolean firstIsJsObject = firstItem instanceof JSObject;
			if (!firstIsJsObject) {
				List<Integer> sizes = createRowSizes(length);
				return sizes;
			} else {
				Integer firstItemLength = null;
				try {
					JSObject firstJsItem = (JSObject) firstItem;
					firstItemLength = (int) firstJsItem.getMember("length");
				} catch (Exception exception) {
					// first item is not a sub array: return size for row array
					List<Integer> sizes = createRowSizes(length);
					return sizes;
				}

				// check length of remaining items to be the same as the length
				// of the first item
				if (length > 1) {
					checkSizeOfRemainingSubItems(length, firstItemLength);
				}

				// create and return matrix sizes
				List<Integer> matrixSizes = createMatrixSizes(length, firstItemLength);
				return matrixSizes;
			}
		}
	}

	private void checkSizeOfRemainingSubItems(int numberOfRows, Integer numberOfColumns) {
		for (int rowIndex = 1; rowIndex < numberOfRows; rowIndex++) {
			Object rowObj = getAsObject(rowIndex);
			boolean isJsObject = rowObj instanceof JSObject;
			if (isJsObject) {
				JSObject rowJsObject = (JSObject) rowObj;
				Integer rowLength;
				try {
					rowLength = (int) rowJsObject.getMember("length");
				} catch (Exception exception) {
					String message = "Could not retrive length from array object";
					throw new IllegalStateException(message);
				}
				boolean hasDifferentLength = rowLength != numberOfColumns;
				if (hasDifferentLength) {
					String message = "Different item lengths";
					throw new IllegalStateException(message);
				}

			} else {
				String message = "Mixed type array";
				throw new IllegalStateException(message);
			}
		}
	}

	private List<Integer> createMatrixSizes(int numberOfRows, int numberOfColumns) {
		List<Integer> sizes = new ArrayList<>();
		sizes.add(numberOfRows);
		sizes.add(numberOfColumns);
		return sizes;
	}

	private List<Integer> createRowSizes(int length) {
		List<Integer> sizes = new ArrayList<>();
		sizes.add(1);
		sizes.add(length);
		return sizes;
	}

	private List<Integer> createZeroSizes() {
		List<Integer> zeroSizes = new ArrayList<>();
		zeroSizes.add(0);
		zeroSizes.add(0);
		return zeroSizes;
	}

	public int dimension() {
		List<Integer> sizes = sizes();
		int rows = sizes.get(0);
		int columns = sizes.get(1);
		if (rows == 0 && columns == 0) {
			return 0;
		}
		if (rows == 1) {
			return 1;
		}
		if (columns == 1) {
			return 1;
		}
		return 2;
	}

	//#end region

	//#region RETRIVE ITEMS

	public <D> D get(int index, Class<D> classObj) {
		Object resultObj = getAsObject(index);
		D result = convertObjectTo(resultObj, classObj);
		return result;
	}

	public <D> D get(int rowIndex, int columnIndex, Class<D> classObj) {
		Object resultObj = getAsObject(rowIndex, columnIndex);
		D result = convertObjectTo(resultObj, classObj);
		return result;
	}

	private Object getAsObject(int index) {
		JSObject jsObject = getJsObject();
		Object resultObj = jsObject.getSlot(index);
		return resultObj;
	}

	private Object getAsObject(int rowIndex, int columnIndex) {
		String command = "this[" + rowIndex + "][" + columnIndex + "]";
		Object resultObj = eval(command);
		return resultObj;
	}

	public <D> List<? extends D> asList(Class<D> classObj) {
		int size = length();
		List<D> list = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			D element = (D) this.get(index, classObj);
			list.add(element);
		}
		return list;
	}

	//#end region
	
	//#region TO STRING
	
	public String toString(){
				
		int size = length();
		List<String> stringList = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			Object element = this.get(index, Object.class);
			stringList.add(element.toString());			
		}
		String displayString = "[" + String.join(",", stringList) + "]";
		return displayString;
		
	}
	
	//#end region

	//#end region

}
