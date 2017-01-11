package org.treez.javafxd3.d3.arrays.foreach;

/**
 * Wraps a ForEachObjectDelegate in a named class so that it can 
 * be used in the JavaScript world (that would not work with anonymous functions directly).
 */
public class ForEachObjectDelegateWrapper implements ForEachObjectDelegate{
	
	private ForEachObjectDelegate wrappedDelegate;	
	
	public ForEachObjectDelegateWrapper(ForEachObjectDelegate wrappedDelegate){
		this.wrappedDelegate=wrappedDelegate;
	}

	@Override
	public void process(Object element) {
		wrappedDelegate.process(element);		
	}	
	
}
