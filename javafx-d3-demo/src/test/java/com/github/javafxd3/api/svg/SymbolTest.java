package com.github.javafxd3.api.svg;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.Symbol.Type;
import com.github.javafxd3.api.wrapper.Element;

import netscape.javascript.JSObject;

@SuppressWarnings("javadoc")
public class SymbolTest extends AbstractTestCase {

	

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// check the symbol types match the Symbol.Type.values
		String[] types = symbolTypes();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			System.out.println("SYMBOL TYPE " + type);
			Type typeEnum = Symbol.Type.valueOf(type.toUpperCase().replace('-',
					'_'));
			if (typeEnum == null) {
				fail("the symbol type " + type + " is not implemented");
			}
		}

		Symbol symbol = d3.svg().symbol();
		symbol.size(32);
		symbol.size(new DatumFunction<Integer>() {
			@Override
			public Integer apply(Object context, Object d, int index) {
				return index;
			}
		});

		symbol.type(Type.CIRCLE);
		symbol.type(new DatumFunction<Type>() {
			@Override
			public Type apply(Object context, Object d, int index) {
				return Type.CIRCLE;
			}
		});
	}
	
	/**
	 * Return the list of supported symbol types, which match the
	 * {@link Type#values()}.
	 * <p>
	 * 
	 * @return the list of supported symbol types
	 */
	public String[] symbolTypes(){
		
		D3 d3 = new D3(webEngine);
		Object result = d3.eval("this.svg.symbolTypes");
		String resultString = result.toString();
		return new String[]{"Error"};
		
	};

}
