package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.functions.DataFunction;

import netscape.javascript.JSObject;

public class DataFunctionTest extends AbstractTestCase {

	@Override	
	public void doTest() {
		
		JSObject d3Obj = d3.getJsObject();
		
		DataFunction<String> datumFunction = new StringDataFunction();
				
		d3Obj.setMember("temp__function", datumFunction);
		
		int index = 3;
		String command = "d3.temp__function.apply(undefined, d3, "+index+")";
		Object result = d3.eval(command);
		assertEquals(result, "" + index);		
		
	}
	
	public class StringDataFunction implements DataFunction<String> {
		@Override
		public String apply(Object context, Object d, int index) {
			String contextType = context.getClass().getName();
			System.out.println("DataFunctionTest context type:" + contextType);
			
			String datumType = d.getClass().getName();
			System.out.println("DataFunctionTest datum type:" + datumType);			
			
			String value = "" + index;
			return value;
		}
	}
	
	

}


