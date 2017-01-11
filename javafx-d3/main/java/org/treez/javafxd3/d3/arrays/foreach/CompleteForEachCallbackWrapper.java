package org.treez.javafxd3.d3.arrays.foreach;

public class CompleteForEachCallbackWrapper<T> implements ForEachCallback<T>{
	
	private ForEachCallback<T> wrappedCallback;	
	
	public CompleteForEachCallbackWrapper(ForEachCallback<T> wrappedCallback){
		this.wrappedCallback=wrappedCallback;
	}

	@Override
	public T forEach(Object context, Object element, int index, Object array){
	
		return wrappedCallback.forEach(context, element, index, array);		
	}	
	
}
