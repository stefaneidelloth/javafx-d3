package org.treez.javafxd3.d3.svg;


import org.treez.javafxd3.d3.svg.Symbol;
import org.treez.javafxd3.d3.svg.datafunction.IndexDataFunction;
import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.functions.data.ConstantDataFunction;


public class SymbolTest extends AbstractTestCase {

	

	@Override	
	public void doTest() {
		
		
		
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
		symbol.size(new IndexDataFunction());

		symbol.type(SymbolType.CIRCLE);
		symbol.type(new ConstantDataFunction<SymbolType>(SymbolType.CIRCLE));
	}
	
	/**
	 * Return the list of supported symbol types, which match the
	 * {@link Type#values()}.
	 * <p>
	 * 
	 * @return the list of supported symbol types
	 */
	public String[] symbolTypes(){
		
		
		Object result = d3.eval("this.svg.symbolTypes");
		String resultString = result.toString();
		String[] symbolTypes = resultString.split(",");
		return symbolTypes;
		
	};

}
