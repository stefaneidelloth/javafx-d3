package org.treez.javafxd3.d3.transition;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.core.Transition.EventType;
import org.treez.javafxd3.d3.demo.InputElementFactory;
import org.treez.javafxd3.d3.ease.Easing;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.ConstantDataFunction;
import org.treez.javafxd3.d3.selection.AbstractSelectionTest;
import org.treez.javafxd3.d3.transition.function.InterpolatorTweenFunction;


public class TransitionTest extends AbstractSelectionTest {

	private final DataFunction<String> callback = new ConstantDataFunction<String>("16px");

	@Override	
	public void doTest() {
		
				
		// NOTE: all this is only smoke tests

		Selection selection = givenASimpleNodeFactory(new InputElementFactory("blah"));
		//
		Transition transition = selection.transition().duration(100);
		transition.attr("foo", callback);
		transition.attr("bar", 16);
		transition.attr("bar", "32px");
		transition.attrTween("bar", new InterpolatorTweenFunction(webEngine));
		
		
		transition
				.delay(1000)
				.delay(new ConstantDataFunction<Integer>(100))
				.duration(new ConstantDataFunction<Integer>(123))
				.each(EventType.START, new ConstantDataFunction<Void>(null))
				.each(EventType.END, new ConstantDataFunction<Void>(null))
				.ease(Easing.back(webEngine, 12))
				.style("font-size", new ConstantDataFunction<String>(""));

		transition.size();

		// d3.transition
		assertNotNull(d3.transition());
		
	}

}
