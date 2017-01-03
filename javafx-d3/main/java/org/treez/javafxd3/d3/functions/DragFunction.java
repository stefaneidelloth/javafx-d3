package org.treez.javafxd3.d3.functions;

public interface DragFunction {

	void handleDragStart(Object context, Object d, int index);

	void handleDrag(Object context, Object d, int index);

	void handleDragEnd(Object context, Object d, int index);

}
