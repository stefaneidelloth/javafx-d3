package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.AbstractTestCase;

import netscape.javascript.JSObject;

public class DatumFunctionTest extends AbstractTestCase {

	@Override	
	public void doTest() {
		
		JSObject d3Obj = d3.getJsObject();
		
		DatumFunction<String> datumFunction = new StringDatumFunction();
				
		d3Obj.setMember("temp__function", datumFunction);
		
		int index = 3;
		String command = "d3.temp__function.apply(undefined, d3, "+index+")";
		Object result = d3.eval(command);
		assertEquals(result, "" + index);		
		
	}
	
	public class StringDatumFunction implements DatumFunction<String> {
		@Override
		public String apply(Object context, Object d, int index) {
			String contextType = context.getClass().getName();
			System.out.println("DatumFunctionTest context type:" + contextType);
			
			String datumType = d.getClass().getName();
			System.out.println("DatumFunctionTest datum type:" + datumType);			
			
			String value = "" + index;
			return value;
		}
	}
	
	

}


