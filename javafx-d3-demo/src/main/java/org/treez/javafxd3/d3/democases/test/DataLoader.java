package org.treez.javafxd3.d3.democases.test;

public class DataLoader {
	
	/*
	public static void loadData(final AsyncCallback<Data[]> callback) {
		throw new IllegalStateException("not yet implemented");
	}
	
	*/
	
	/*

    public static void loadData(final AsyncCallback<Data[]> callback) {
    	
	        D3.csv("demo-data/readme.csv", //
	        
	        new DsvObjectAccessor<Data>() {
	            final TimeFormat format = d3.time().format("%b %Y");
	
	            @Override
	            public Data apply(final DsvRow d, final int index) {
	                Value value = d.get("symbol");
	                if ("S&P 500".equals(value.asString())) {
	                    String symbol = d.get("symbol").asString();
	                    JsDate date = format.parse(d.get("date").asString());
	                    double price = d.get("price").asDouble();
	                    return new Data(symbol, date, price);
	                } else {
	                    return null;
	                }
	            }
	        }, //
	        
	        new DsvCallback<Data>() {
	            @Override
	            public void get(final JavaScriptObject error, final DsvRows<Data> values) {
	                if (error != null) {
	                    XmlHttpRequest xhrError = error.cast();
	                    String message = xhrError.status() + " (" + xhrError.statusText() + ")";
	                    callback.onFailure(new RuntimeException(message));
	                }
	                else {
	                    callback.onSuccess(values);
	                }
	        }
        
        });
    }
    
    */
}
