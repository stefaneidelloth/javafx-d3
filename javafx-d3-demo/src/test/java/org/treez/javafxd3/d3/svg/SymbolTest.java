package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.functions.ConstantDatumFunction;
import org.treez.javafxd3.d3.svg.Symbol;

import org.treez.javafxd3.d3.svg.datumfunction.IndexDatumFunction;
import org.treez.javafxd3.d3.AbstractTestCase;

@SuppressWarnings("javadoc")
public class SymbolTest extends AbstractTestCase {

	

	@Override	
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		// check the symbol types match the Symbol.Type.values
		String[] types = symbolTypes();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			System.out.println("SYMBOL TYPE: " + type);
			SymbolType typeEnum = SymbolType.valueOf(type.toUpperCase().replace('-',
					'_'));
			if (typeEnum == null) {
				fail("the symbol type " + type + " is not implemented");
			}
		}

		Symbol symbol = d3.svg().symbol();
		symbol.size(32);
		symbol.size(new IndexDatumFunction());

		symbol.type(SymbolType.CIRCLE);
		symbol.type(new ConstantDatumFunction<SymbolType>(SymbolType.CIRCLE));
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
		String[] symbolTypes = resultString.split(",");
		return symbolTypes;
		
	};

}
